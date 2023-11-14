package com.cp.content.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cp.base.constant.course.CourseAuditStatusConstant;
import com.cp.base.constant.course.CourseChargeStatusConstant;
import com.cp.base.constant.course.CourseExceptionConstant;
import com.cp.base.constant.course.CourseReleaseStatusConstant;
import com.cp.base.exception.CpException;
import com.cp.base.model.PageParams;
import com.cp.base.model.PageResult;
import com.cp.content.model.dto.AddCourseDto;
import com.cp.content.model.dto.CourseBaseInfoDto;
import com.cp.content.model.dto.EditCourseDto;
import com.cp.content.model.dto.QueryCourseParamsDto;
import com.cp.content.model.po.CourseBase;
import com.cp.content.model.po.CourseMarket;
import com.cp.content.service.mapper.CourseBaseMapper;
import com.cp.content.service.mapper.CourseCategoryMapper;
import com.cp.content.service.mapper.CourseMarketMapper;
import com.cp.content.service.service.CourseBaseInfoService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class CourseBaseInfoServiceImpl implements CourseBaseInfoService {
    @Resource
    private CourseBaseMapper courseBaseMapper;
    @Resource
    private CourseMarketMapper courseMarketMapper;
    @Resource
    private CourseCategoryMapper courseCategoryMapper;
    @Override
    public PageResult<CourseBase>  queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto) {
        //query condition
        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();
        //fuzzy query by name : course_base.name like = `%value%`
        queryWrapper.like(StringUtils.isNotEmpty(queryCourseParamsDto.getCourseName()),CourseBase::getName, queryCourseParamsDto.getCourseName());
        //auditStatus = ?
        queryWrapper.like(StringUtils.isNotEmpty(queryCourseParamsDto.getAuditStatus()),CourseBase::getAuditStatus, queryCourseParamsDto.getAuditStatus());
        //course release status
        queryWrapper.like(StringUtils.isNotEmpty(queryCourseParamsDto.getPublishStatus()),CourseBase::getStatus, queryCourseParamsDto.getPublishStatus());

        //create page object ,Params:current pageNo,number of records per page
        Page<CourseBase> page = new Page<>(pageParams.getPageNo(), pageParams.getPageNo());
        //start paging query
        Page<CourseBase> pageResult = courseBaseMapper.selectPage(page,queryWrapper);
        //data list
        List<CourseBase> items = pageResult.getRecords();
        //total records
        long total = pageResult.getTotal();
        //enveloped data :List<T> items,Long total,Long pageNo
        PageResult<CourseBase> courseBasePageResult = new PageResult<>(items,total,pageParams.getPageNo(),pageParams.getPageSize());

        return courseBasePageResult;
    }

    /**
     * 此方法为两个表的查询,先保存信息,后对两表查询
     * @param companyId 公司id
     * @param addCourseDto data
     * @return
     */
    @Transactional
    @Override
    public CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto addCourseDto) {
        CourseBase courseBaseNew = CourseBase.builder()
                .companyId(companyId)
                .createDate(LocalDateTime.now())
                //Audit Status
                .auditStatus(CourseAuditStatusConstant.NOT_SUBMITTED)
                //publish Status
                .status(CourseReleaseStatusConstant.UNPUBLISHED)
                .build();
        BeanUtils.copyProperties(addCourseDto,courseBaseNew);
        //save new courseBase
        int insertStatus = courseBaseMapper.insert(courseBaseNew);
        if(insertStatus<0){
             CpException.cast(CourseExceptionConstant.ADD_COURSE_FAIL);
        }
        CourseMarket courseMarketNew = new CourseMarket();
        courseMarketNew.setId(courseBaseNew.getId());
        BeanUtils.copyProperties(addCourseDto,courseMarketNew);
        saveCourseMarket(courseMarketNew);
        CourseBaseInfoDto courseBaseInfo = getCourseBaseInfo(courseBaseNew.getId());
        return courseBaseInfo;
    }

    /**
     * 联合查询
     * @param courseId 课程id
     * @return
     */
    @Override
    public  CourseBaseInfoDto getCourseBaseInfo(Long courseId) {
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if (courseBase == null) {
            return null;
        }
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);
        CourseBaseInfoDto courseBaseInfoDto = new CourseBaseInfoDto();
        BeanUtils.copyProperties(courseBase,courseBaseInfoDto);
        if (courseMarket != null) {
            BeanUtils.copyProperties(courseMarket,courseBaseInfoDto);
        }
        return courseBaseInfoDto;
    }

    @Override
    public CourseBaseInfoDto updateCourseBase(Long companyId, EditCourseDto editCourseDto) {
        Long courseId = editCourseDto.getId();
        //查询课程信息
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if (courseBase == null) {
            CpException.cast(CourseExceptionConstant.COURSE_NOT_EXISTS);
        }
        return  null;
    }

    /**
     * 储存营销信息
     * @param courseMarket
     * @return
     */
    private int saveCourseMarket(CourseMarket courseMarket){
        //参数校验
        String charge = courseMarket.getCharge();
        if (StringUtils.isEmpty(charge)) {
            CpException.cast(CourseExceptionConstant.COURSE_PRICE_EMPTY);
        }
        if (charge.equals(CourseChargeStatusConstant.CHARGE)) {
            if (courseMarket.getPrice() == null || courseMarket.getPrice()<=0) {
                CpException.cast(CourseExceptionConstant.COURSE_PRICE_INVALID);
            }
        }
        //查询数据库,存在更新,否则插入
        Long id = courseMarket.getId();
        CourseMarket marketBefore = courseMarketMapper.selectById(id);
        if (marketBefore == null) {
            return courseMarketMapper.insert(courseMarket);
        }else {
            BeanUtils.copyProperties(courseMarket,marketBefore);
            marketBefore.setId(courseMarket.getId());
            return courseMarketMapper.updateById(courseMarket);
        }
    }
}

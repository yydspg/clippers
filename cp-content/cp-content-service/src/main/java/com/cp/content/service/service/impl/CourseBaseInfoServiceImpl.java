package com.cp.content.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cp.base.model.PageParams;
import com.cp.base.model.PageResult;
import com.cp.content.model.dto.AddCourseDto;
import com.cp.content.model.dto.CourseBaseInfoDto;
import com.cp.content.model.dto.QueryCourseParamDto;
import com.cp.content.model.po.CourseBase;
import com.cp.content.service.mapper.CourseBaseMapper;
import com.cp.content.service.mapper.CourseCategoryMapper;
import com.cp.content.service.mapper.CourseMarketMapper;
import com.cp.content.service.service.CourseBaseInfoService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class CourseBaseInfoServiceImpl implements CourseBaseInfoService {
    @Resource
    private CourseBaseMapper courseBaseMapper;
    @Resource
    private CourseMarketMapper courseMarketMapper;
    @Resource
    private CourseCategoryMapper courseCategoryMapper;
    @Override
    public PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamDto queryCourseParamDto) {
        //query condition
        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();
        //fuzzy query by name : course_base.name like = `%value%`
        queryWrapper.like(StringUtils.isNotEmpty(queryCourseParamDto.getCourseName()),CourseBase::getName,queryCourseParamDto.getCourseName());
        //auditStatus = ?
        queryWrapper.like(StringUtils.isNotEmpty(queryCourseParamDto.getAuditStatus()),CourseBase::getAuditStatus,queryCourseParamDto.getAuditStatus());
        //course release status
        queryWrapper.like(StringUtils.isNotEmpty(queryCourseParamDto.getPublishStatus()),CourseBase::getStatus,queryCourseParamDto.getPublishStatus());

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

    @Override
    public CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto addCourseDto) {
        return null;
    }
}

package com.cp.content.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cp.base.constant.course.TeachPlanGradeConstant;
import com.cp.base.constant.teachPlan.SectionOrderOpConstant;
import com.cp.base.constant.teachPlan.TeachPlanErrorConstant;
import com.cp.content.model.dto.SaveTeachPlanDto;
import com.cp.content.model.dto.TeachPlanDto;
import com.cp.content.model.po.TeachPlan;
import com.cp.content.service.exception.CpException;
import com.cp.content.service.mapper.TeachPlanMapper;
import com.cp.content.service.service.TeachPlanService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程计划 服务实现类
 * </p>
 *
 * @author paul
 */
@Slf4j
@Service
public class TeachPlanServiceImpl extends ServiceImpl<TeachPlanMapper, TeachPlan> implements TeachPlanService {
    @Resource
    private TeachPlanMapper teachPlanMapper;

    /**
     * 树形查询
     * @param courseId 课程标识
     * @return dto
     */
    @Override
    public List<TeachPlanDto> findTeachPlanTree(Long courseId) {
        return teachPlanMapper.selectTreeNodes(courseId);
    }

    /**
     * 保存课程计划
     * @param saveTeachPlanDto dto
     */
    @Override
    public void saveTeachPlan(SaveTeachPlanDto saveTeachPlanDto) {
        //判断操作是新增还是修改
        Long id = saveTeachPlanDto.getId();
        TeachPlan teachPlan;
        if(id==null) {
            teachPlan = new TeachPlan();
            BeanUtils.copyProperties(saveTeachPlanDto,teachPlan);
            Long parentId = saveTeachPlanDto.getParentid();
            Long courseId = saveTeachPlanDto.getCourseId();
            long teachPlanCount = getTeachPlanCount(courseId,parentId);
            teachPlan.setOrderby(teachPlanCount+1);
        }else{
            teachPlan = teachPlanMapper.selectById(id);
            BeanUtils.copyProperties(saveTeachPlanDto,teachPlan);
        }
        teachPlanMapper.insert(teachPlan);
    }

    @Override
    public void delete(long id) {
        TeachPlan teachPlan = teachPlanMapper.selectById(id);
        if(teachPlan == null){
            CpException.cast("null point error");
        }
        if(teachPlan.getGrade() == TeachPlanGradeConstant.PARENT_CHAPTER && getTeachPlanCount(teachPlan.getCourseId(),teachPlan.getParentid())==0){
            CpException.cast(TeachPlanErrorConstant.EXIST_SUB_LEVEL_COURSE);
        }else if(teachPlan.getGrade() == TeachPlanGradeConstant.CHILD_CHAPTER){
            //TODO 关联信息未处理
        }
        teachPlanMapper.deleteById(id);
    }

    /**
     * 查询同级课程计划数目
     * @param courseId 课程标识
     * @param parentId 此课程计划的父节点
     * @return 同级的计划数+1
     */
    private long getTeachPlanCount(Long courseId,Long parentId){
        LambdaQueryWrapper<TeachPlan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper = queryWrapper.eq(TeachPlan::getCourseId, courseId).eq(TeachPlan::getParentid, parentId);
        Long count = teachPlanMapper.selectCount(queryWrapper);
        return  count;
    }

    @Override
    public void changeOrder(String orderOperation, long id) {
        TeachPlan teachPlan = teachPlanMapper.selectById(id);
        if (teachPlan == null) {
            CpException.cast(TeachPlanErrorConstant.PLAN_NOT_EXIST);
        }
        int t = (orderOperation == SectionOrderOpConstant.MOVING_UP)?-1:+1;
        teachPlanMapper.changeOrderOp(teachPlan.getCourseId(),teachPlan.getGrade(),teachPlan.getOrderby(),teachPlan.getOrderby()+t);
    }
}

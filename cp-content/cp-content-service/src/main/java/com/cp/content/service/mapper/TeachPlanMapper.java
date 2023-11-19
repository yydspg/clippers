package com.cp.content.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cp.content.model.dto.TeachPlanDto;
import com.cp.content.model.po.TeachPlan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 课程计划 Mapper 接口
 * </p>
 *
 * @author paul
 */
public interface TeachPlanMapper extends BaseMapper<TeachPlan> {
    public List<TeachPlanDto> selectTreeNodes(Long courseId);
    public void changeOrderOp(@Param("courseId") long courseId,
                              @Param("grade") int grade,
                              @Param("o1") long o1,
                              @Param("o2")long o2);
}

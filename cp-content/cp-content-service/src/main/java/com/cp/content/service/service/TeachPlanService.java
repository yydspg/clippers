package com.cp.content.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cp.content.model.dto.SaveTeachPlanDto;
import com.cp.content.model.dto.TeachPlanDto;
import com.cp.content.model.po.TeachPlan;

import java.util.List;

/**
 * <p>
 * 课程计划 服务类
 * </p>
 *
 * @author paul
 * @since 2023-11-02
 */
public interface TeachPlanService extends IService<TeachPlan> {
    public List<TeachPlanDto> findTeachPlanTree(Long courseId);

    public void saveTeachPlan(SaveTeachPlanDto saveTeachPlanDto);

    void delete(long id);

    void changeOrder(String orderOperation, long id);
}

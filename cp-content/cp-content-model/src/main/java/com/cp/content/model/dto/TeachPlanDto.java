package com.cp.content.model.dto;


import com.cp.content.model.po.TeachPlan;
import com.cp.content.model.po.TeachPlanMedia;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author Mr.M
 * @version 1.0
 * @description 课程计划信息模型类
 * @date 2023/2/14 11:23
 */
@Data
@ToString
public class TeachPlanDto extends TeachPlan {
    //与媒资管理的信息
    private TeachPlanMedia teachPlanMedia;

    //小章节list
    private List<TeachPlanDto> TeachPlanTreeNodes;
}

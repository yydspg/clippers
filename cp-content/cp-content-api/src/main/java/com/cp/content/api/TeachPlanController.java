package com.cp.content.api;

import com.cp.content.model.dto.SaveTeachPlanDto;
import com.cp.content.model.dto.TeachPlanDto;
import com.cp.content.service.service.TeachPlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程计划 前端控制器
 * </p>
 *
 * @author paul
 */
@Slf4j
@RestController
@RequestMapping("/teachplan")
@Tag(name = "课程计划模块")
public class TeachPlanController {

    @Resource
    private TeachPlanService teachPlanService;
    @Operation(summary = "查询课程计划集合")
    @GetMapping("/{courseId}/tree-nodes")
    public List<TeachPlanDto> getTreeNodes(@PathVariable Long courseId){
        return teachPlanService.findTeachPlanTree(courseId);
    }
    @Operation(summary = "保存或修改课程计划")
    @PostMapping
    public void saveTeachPlan( @RequestBody SaveTeachPlanDto TeachPlan){
        teachPlanService.saveTeachPlan(TeachPlan);
    }
    @Operation(summary = "删除课程计划")
    @DeleteMapping("/{id}")
    public void deleteTeachPlan(@PathVariable long id){
        teachPlanService.delete(id);
    }
    @Operation(summary = "修改课程结点顺序")
    @PostMapping("/{orderOperation}/{id}")
    public void orderOp(@PathVariable String orderOperation,@PathVariable long id){
        teachPlanService.changeOrder(orderOperation,id);
    }
}

package com.cp.content.api;

import com.cp.content.model.dto.CourseCategoryTreeDto;
import com.cp.content.service.service.CourseCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 课程分类 前端控制器
 * </p>
 *
 * @author paul
 */
@Slf4j
@RestController
@RequestMapping("/course-category")
@Tag(name = "课程分类查询")
public class CourseCategoryController {

    @Resource
    private CourseCategoryService  courseCategoryService;
    @GetMapping("/tree-nodes")
    @Operation(summary = "树型查询")
    public List<CourseCategoryTreeDto> queryTreeNodes(){
        return courseCategoryService.queryTreeNodes("1");
    }
}

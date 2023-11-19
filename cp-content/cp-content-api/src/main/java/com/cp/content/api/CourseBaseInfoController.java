package com.cp.content.api;



import com.cp.base.model.PageParams;
import com.cp.base.model.PageResult;
import com.cp.content.model.dto.AddCourseDto;
import com.cp.content.model.dto.CourseBaseInfoDto;
import com.cp.content.model.dto.EditCourseDto;
import com.cp.content.model.dto.QueryCourseParamsDto;
import com.cp.content.model.po.CourseBase;
import com.cp.content.service.service.CourseBaseInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/course")
@Tag(name = "课程信息管理")
public class CourseBaseInfoController {
    @Autowired
    CourseBaseInfoService courseBaseInfoService;
    @Operation(summary = "分页查询课程")
    @PostMapping("/list")
    public PageResult<CourseBase> list(PageParams pageParams, @RequestBody(required = false) QueryCourseParamsDto queryCourseParamsDto){
        PageResult<CourseBase> courseBasePageResult = courseBaseInfoService.queryCourseBaseList(pageParams, queryCourseParamsDto);
        return courseBasePageResult;
    }
    @PostMapping
    @Operation(summary = "新增课程信息")
    public CourseBaseInfoDto saveWithMarket(Long companyId, AddCourseDto addCourseDto){
        return courseBaseInfoService.createCourseBase(companyId,addCourseDto);
    }
    @GetMapping("{courseId}")
    @Operation(summary = "查询课程信息")
    public CourseBaseInfoDto getWithMarket(@PathVariable Long courseId){
        return courseBaseInfoService.getCourseBaseInfo(courseId);
    }
    @PutMapping
    @Operation(summary = "修改课程信息")
    public CourseBaseInfoDto updateWithMarket(@RequestBody @Validated EditCourseDto editCourseDto){
//        硬编码
        Long companyId = 1232141425L;
        return courseBaseInfoService.updateCourseBase(companyId, editCourseDto);
    }

}

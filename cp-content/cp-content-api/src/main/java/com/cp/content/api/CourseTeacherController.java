package com.cp.content.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cp.content.model.dto.AddCourseTeacherDto;
import com.cp.content.model.dto.CourseTeacherDto;
import com.cp.content.model.po.CourseTeacher;
import com.cp.content.service.service.CourseTeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程-教师关系表 前端控制器
 * </p>
 *
 * @author paul
 */
@Slf4j
@RestController
@RequestMapping("/courseTeacher")
@Tag(name = "课程教师模块")
public class CourseTeacherController {

    @Resource
    private CourseTeacherService  courseTeacherService;
    @DeleteMapping("/course/{courseId}/{teacherId}")
    @Operation(summary = "删除教师信息")
    public void delete(@PathVariable Long courseId,@PathVariable Long teacherId){
        LambdaQueryWrapper<CourseTeacher> courseTeacherLambdaQueryWrapper = new LambdaQueryWrapper<>();
        courseTeacherLambdaQueryWrapper.eq(CourseTeacher::getCourseId,courseId)
                        .eq(CourseTeacher::getId,teacherId);
        courseTeacherService.remove(courseTeacherLambdaQueryWrapper);
    }
    @Operation(summary = "查询教师信息")
    @GetMapping("/list/{id}")
    public CourseTeacherDto getInfo(@PathVariable long id){
        CourseTeacherDto courseTeacherDto = new CourseTeacherDto();
        CourseTeacher one = courseTeacherService.getById(id);
        BeanUtils.copyProperties(one,courseTeacherDto);
        return courseTeacherDto;
    }
    @Operation(summary = "添加教师信息")
    @PostMapping
    public CourseTeacher saveInfo(@RequestBody AddCourseTeacherDto addCourseTeacherDto){
        CourseTeacher courseTeacher = new CourseTeacher();
        BeanUtils.copyProperties(addCourseTeacherDto,courseTeacher);
        courseTeacherService.save(courseTeacher);
        return courseTeacher;
    }
    @Operation(summary = "修改教师信息")
    @PutMapping
    public CourseTeacherDto update(@RequestBody CourseTeacherDto courseTeacherDto){
        courseTeacherService.updateById(courseTeacherDto);
        return courseTeacherDto;
    }
}

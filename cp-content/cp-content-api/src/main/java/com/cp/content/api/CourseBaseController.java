package com.cp.content.api;


import com.cp.content.service.service.CourseBaseService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 课程基本信息 前端控制器
 * </p>
 *
 * @author paul
 */
@Slf4j
@RestController
@RequestMapping("/courseBase")
public class CourseBaseController {

    @Resource
    private CourseBaseService courseBaseService;

}

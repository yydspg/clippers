package com.cp.content.api;

import com.cp.content.service.service.CoursePublishService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 课程发布 前端控制器
 * </p>
 *
 * @author paul
 */
@Slf4j
@RestController
@RequestMapping("coursePublish")
public class CoursePublishController {

    @Resource
    private CoursePublishService  coursePublishService;
}

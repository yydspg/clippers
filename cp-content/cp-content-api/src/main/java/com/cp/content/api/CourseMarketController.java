package com.cp.content.api;

import com.cp.content.service.service.CourseMarketService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 课程营销信息 前端控制器
 * </p>
 *
 * @author paul
 */
@Slf4j
@RestController
@RequestMapping("courseMarket")
public class CourseMarketController {
    @Resource
    private CourseMarketService  courseMarketService;
}

package com.cp.content.service.service;

import com.cp.base.model.PageParams;
import com.cp.base.model.PageResult;
import com.cp.content.model.dto.AddCourseDto;
import com.cp.content.model.dto.CourseBaseInfoDto;
import com.cp.content.model.dto.QueryCourseParamDto;
import com.cp.content.model.po.CourseBase;

public interface CourseBaseInfoService {

    /**
     * 课程分页查询
     * @param pageParams 分页查询参数
     * @param queryCourseParamDto query condition
     * @return
     */
    public PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamDto queryCourseParamDto);

    /**
     * 新增课程
     * @param companyId
     * @param addCourseDto
     * @return
     */
    public CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto addCourseDto);
}

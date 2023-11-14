package com.cp.content.service.service;

import com.cp.base.model.PageParams;
import com.cp.base.model.PageResult;
import com.cp.content.model.dto.AddCourseDto;
import com.cp.content.model.dto.CourseBaseInfoDto;
import com.cp.content.model.dto.EditCourseDto;
import com.cp.content.model.dto.QueryCourseParamsDto;
import com.cp.content.model.po.CourseBase;

public interface CourseBaseInfoService {

    /**
     * 课程分页查询
     * @param pageParams 分页查询参数
     * @param queryCourseParamsDto query condition
     * @return
     */
    public PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto);

    /**
     * 新增课程
     * @param companyId 公司id
     * @param addCourseDto data
     * @return
     */
    public CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto addCourseDto);

    /**
     * 获取课程信息
     * @param courseId 课程id
     * @return
     */
    public CourseBaseInfoDto getCourseBaseInfo(Long courseId);

    /**
     * 更新
     * @param companyId 公司id
     * @param editCourseDto 主键id
     * @return
     */
    public CourseBaseInfoDto updateCourseBase(Long companyId, EditCourseDto editCourseDto);
}

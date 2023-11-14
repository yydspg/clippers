package com.cp.content.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cp.content.model.dto.CourseCategoryTreeDto;
import com.cp.content.model.po.CourseCategory;

import java.util.List;

/**
 * <p>
 * 课程分类 服务类
 * </p>
 *
 * @author paul
 * @since 2023-11-02
 */
public interface CourseCategoryService extends IService<CourseCategory> {
    public abstract List<CourseCategoryTreeDto> queryTreeNodes(String id);

}

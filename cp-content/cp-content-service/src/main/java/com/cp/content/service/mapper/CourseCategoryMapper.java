package com.cp.content.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cp.content.model.dto.CourseCategoryTreeDto;
import com.cp.content.model.po.CourseCategory;

import java.util.List;

/**
 * <p>
 * 课程分类 Mapper 接口
 * </p>
 *
 * @author paul
 */
public interface CourseCategoryMapper extends BaseMapper<CourseCategory> {
    public List<CourseCategoryTreeDto> selectTreeNodes(String id);

}

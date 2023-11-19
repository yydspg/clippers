package com.cp.content.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cp.content.model.dto.CourseCategoryTreeDto;
import com.cp.content.service.mapper.CourseCategoryMapper;
import com.cp.content.model.po.CourseCategory;
import com.cp.content.service.service.CourseCategoryService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程分类 服务实现类
 * </p>
 *
 * @author paul
 */
@Slf4j
@Service
public class CourseCategoryServiceImpl extends ServiceImpl<CourseCategoryMapper, CourseCategory> implements CourseCategoryService {

    @Resource
    private CourseCategoryMapper courseCategoryMapper;

    @Override
    //TODO 理解如下代码
    public List<CourseCategoryTreeDto> queryTreeNodes(String id) {
        log.info("tree query,id:{}",id);
        List<CourseCategoryTreeDto> courseCategoryTreeDtos = courseCategoryMapper.selectTreeNodes(id);
        Map<String,CourseCategoryTreeDto> mapTemp = courseCategoryTreeDtos.stream().filter(t->!id.equals(t.getId())).collect(Collectors.toMap(k->k.getId(),v->v,(k1,k2)->k2));
        List<CourseCategoryTreeDto> courseCategoryList = new ArrayList<>();
        courseCategoryTreeDtos.stream().filter(t->!id.equals(t.getId())).forEach(item->{
            if(item.getParentid().equals(id)){
                courseCategoryList.add(item);
            }
            CourseCategoryTreeDto courseCategoryParent = mapTemp.get(item.getParentid());
            if(courseCategoryParent!=null){
                if(courseCategoryParent.getChildrenTreeNodes()==null){
                    courseCategoryParent.setChildrenTreeNodes(new ArrayList<CourseCategoryTreeDto>());
                }
                courseCategoryParent.getChildrenTreeNodes().add(item);
            }
        });
        return courseCategoryList;
    }
}

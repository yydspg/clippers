package com.cp.content.model.dto;

import lombok.Data;

@Data
public class AddCourseTeacherDto {
    private long courseId;
    private String teacherName;
    private String position;
    private String introduction;
}

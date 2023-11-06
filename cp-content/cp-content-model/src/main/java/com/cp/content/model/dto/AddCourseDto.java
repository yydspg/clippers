package com.cp.content.model.dto;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data

public class AddCourseDto {



    private String name;


    private String users;
    private String tags;


    private String mt;

    private String st;

    private String grade;

    private String teachmode;

    private String description;

    private String pic;

    private String charge;

    private Float price;
    private Float originalPrice;


    private String qq;

    private String wechat;
    private String phone;

    private Integer validDays;
}

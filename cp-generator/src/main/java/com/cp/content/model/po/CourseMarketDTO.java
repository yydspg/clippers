package com.cp.content.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 课程营销信息
 * </p>
 *
 * @author paul
 */
@Data
@ApiModel(value="CourseMarketDTO", description="课程营销信息")
public class CourseMarketDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键，课程id")
    private Long id;

    @ApiModelProperty(value = "收费规则，对应数据字典")
    private String charge;

    @ApiModelProperty(value = "现价")
    private Float price;

    @ApiModelProperty(value = "原价")
    private Float originalPrice;

    @ApiModelProperty(value = "咨询qq")
    private String qq;

    @ApiModelProperty(value = "微信")
    private String wechat;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "有效期天数")
    private Integer validDays;


}

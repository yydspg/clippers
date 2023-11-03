package com.cp.content.model.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 课程基本信息
 * </p>
 *
 * @author paul
 */
@Data
@ApiModel(value="CourseBaseDTO", description="课程基本信息")
public class CourseBaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "机构ID")
    private Long companyId;

    @ApiModelProperty(value = "机构名称")
    private String companyName;

    @ApiModelProperty(value = "课程名称")
    private String name;

    @ApiModelProperty(value = "适用人群")
    private String users;

    @ApiModelProperty(value = "课程标签")
    private String tags;

    @ApiModelProperty(value = "大分类")
    private String mt;

    @ApiModelProperty(value = "小分类")
    private String st;

    @ApiModelProperty(value = "课程等级")
    private String grade;

    @ApiModelProperty(value = "教育模式(common普通，record 录播，live直播等）")
    private String teachmode;

    @ApiModelProperty(value = "课程介绍")
    private String description;

    @ApiModelProperty(value = "课程图片")
    private String pic;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime changeDate;

    @ApiModelProperty(value = "创建人")
    private String createPeople;

    @ApiModelProperty(value = "更新人")
    private String changePeople;

    @ApiModelProperty(value = "审核状态")
    private String auditStatus;

    @ApiModelProperty(value = "课程发布状态 未发布  已发布 下线")
    private String status;


}

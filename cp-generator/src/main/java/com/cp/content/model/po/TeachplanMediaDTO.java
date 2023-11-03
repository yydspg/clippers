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
 * 
 * </p>
 *
 * @author paul
 */
@Data
@ApiModel(value="TeachplanMediaDTO", description="")
public class TeachplanMediaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "媒资文件id")
    private String mediaId;

    @ApiModelProperty(value = "课程计划标识")
    private Long teachplanId;

    @ApiModelProperty(value = "课程标识")
    private Long courseId;

    @ApiModelProperty(value = "媒资文件原始名称")
    private String mediaFilename;

    private LocalDateTime createDate;

    @ApiModelProperty(value = "创建人")
    private String createPeople;

    @ApiModelProperty(value = "修改人")
    private String changePeople;


}

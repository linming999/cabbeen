package com.kabin.goods.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.kabin.common.annotation.Excel;
import com.kabin.common.core.domain.BaseEntity;

/**
 * 商品款式图片对象 tb_goods_image
 * 
 * @author kabin
 * @date 2025-07-17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("商品款式图片实体")
public class TbGoodsImage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**
    * 主键ID
    */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
    * 商品ID
    */
    @Excel(name = "商品ID")
    @ApiModelProperty(value = "商品ID")
    private Long goodId;

    /**
    * 款式名称
    */
    @Excel(name = "款式名称")
    @ApiModelProperty(value = "款式名称")
    private String style;

    /**
    * 图片URL
    */
    @Excel(name = "图片URL")
    @ApiModelProperty(value = "图片URL")
    private String image;

    /**
    * 是否主图（0否，1是）
    */
    @Excel(name = "是否主图", readConverterExp = "0=否，1是")
    @ApiModelProperty(value = "是否主图")
    private Integer isMain;

    /**
    * 排序值
    */
    @Excel(name = "排序值")
    @ApiModelProperty(value = "排序值")
    private Long sort;

}

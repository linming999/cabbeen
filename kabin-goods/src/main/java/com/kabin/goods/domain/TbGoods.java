package com.kabin.goods.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.kabin.common.annotation.Excel;
import com.kabin.common.core.domain.BaseEntity;

/**
 * 商品信息对象 tb_goods
 * 
 * @author kabin
 * @date 2025-07-16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("商品信息实体")
public class TbGoods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**
    * 商品ID
    */
    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
    * 商品封面
    */
    @Excel(name = "商品封面")
    @ApiModelProperty(value = "商品封面")
    private String goodCover;

    /**
    * 商品名称
    */
    @Excel(name = "商品名称")
    @ApiModelProperty(value = "商品名称")
    private String goodName;

    /**
    * 商品描述
    */
    @Excel(name = "商品描述")
    @ApiModelProperty(value = "商品描述")
    private String goodDescribe;

    /**
    * 商品类别
    */
    @Excel(name = "商品类别")
    @ApiModelProperty(value = "商品类别")
    private String type;

    /**
    * 商品价格
    */
    @Excel(name = "商品价格")
    @ApiModelProperty(value = "商品价格")
    private Double goodPrice;


}

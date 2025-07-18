package com.kabin.goods.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.kabin.common.annotation.Excel;
import com.kabin.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 商品类型对象 tb_goods_type
 * 
 * @author kabin
 * @date 2025-07-16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("商品类型实体")
public class TbGoodsType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**
    * 类型ID
    */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
    * 商品类型
    */
    @Excel(name = "商品类型")
    @ApiModelProperty(value = "商品类型")
    private String type;

    /**
    * 父ID
    */
    @Excel(name = "父ID")
    @ApiModelProperty(value = "父ID")
    private Long parentId;

    /**
     * 子节点（树结构用）
     */
    @TableField(exist = false)
    private List<TbGoodsType> children;

}

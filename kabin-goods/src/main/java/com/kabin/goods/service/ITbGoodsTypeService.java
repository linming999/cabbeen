package com.kabin.goods.service;

import java.util.List;
import com.kabin.goods.domain.TbGoodsType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 商品类型Service接口
 * 
 * @author kabin
 * @date 2025-07-16
 */
public interface ITbGoodsTypeService extends IService<TbGoodsType>
{
    /**
     * 查询商品类型
     * 
     * @param id 商品类型主键
     * @return 商品类型
     */
    public TbGoodsType selectTbGoodsTypeById(Long id);

    /**
     * 查询商品类型列表
     * 
     * @param tbGoodsType 商品类型
     * @return 商品类型集合
     */
    public List<TbGoodsType> selectTbGoodsTypeList(TbGoodsType tbGoodsType);

    /**
     * 新增商品类型
     * 
     * @param tbGoodsType 商品类型
     * @return 结果
     */
    public int insertTbGoodsType(TbGoodsType tbGoodsType);

    /**
     * 修改商品类型
     * 
     * @param tbGoodsType 商品类型
     * @return 结果
     */
    public int updateTbGoodsType(TbGoodsType tbGoodsType);

    /**
     * 批量删除商品类型
     * 
     * @param ids 需要删除的商品类型主键集合
     * @return 结果
     */
    public int deleteTbGoodsTypeByIds(Long[] ids);

    /**
     * 删除商品类型信息
     * 
     * @param id 商品类型主键
     * @return 结果
     */
    public int deleteTbGoodsTypeById(Long id);

    /**
     * 查询商品类型树
     * @return 商品类型树结构
     */
    public List<TbGoodsType> selectGoodsTypeTree();
}

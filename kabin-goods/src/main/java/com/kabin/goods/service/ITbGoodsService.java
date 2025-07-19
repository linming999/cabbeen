package com.kabin.goods.service;

import java.util.List;
import com.kabin.goods.domain.TbGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kabin.goods.domain.dto.GoodsDetailDto;
import com.kabin.goods.domain.dto.GoodsDto;

/**
 * 商品信息Service接口
 * 
 * @author kabin
 * @date 2025-07-16
 */
public interface ITbGoodsService extends IService<TbGoods>
{
    /**
     * 查询商品信息
     * 
     * @param id 商品信息主键
     * @return 商品信息
     */
    public TbGoods selectTbGoodsById(Long id);

    /**
     * 查询商品信息列表
     * 
     * @param tbGoods 商品信息
     * @return 商品信息集合
     */
    public List<TbGoods> selectTbGoodsList(TbGoods tbGoods);

    /**
     * 新增商品信息
     * 
     * @param tbGoods 商品信息
     * @return 结果
     */
    public int insertTbGoods(TbGoods tbGoods);

    /**
     * 修改商品信息
     * 
     * @param tbGoods 商品信息
     * @return 结果
     */
    public int updateTbGoods(TbGoods tbGoods);

    /**
     * 批量删除商品信息
     * 
     * @param ids 需要删除的商品信息主键集合
     * @return 结果
     */
    public int deleteTbGoodsByIds(Long[] ids);

    /**
     * 删除商品信息信息
     * 
     * @param id 商品信息主键
     * @return 结果
     */
    public int deleteTbGoodsById(Long id);

    int updateStatusBatch(List<Long> ids, Integer status);


    List<GoodsDto> getGoodsWithImagesByCategory(String category);

    GoodsDetailDto getGoodsDetailById(Long goodsId);

}

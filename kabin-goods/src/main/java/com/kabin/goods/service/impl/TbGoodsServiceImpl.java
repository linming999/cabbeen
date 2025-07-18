package com.kabin.goods.service.impl;

import java.util.List;
import com.kabin.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kabin.goods.mapper.TbGoodsMapper;
import com.kabin.goods.domain.TbGoods;
import com.kabin.goods.service.ITbGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.Arrays;

/**
 * 商品信息Service业务层处理
 * 
 * @author kabin
 * @date 2025-07-16
 */
@Service
public class TbGoodsServiceImpl extends ServiceImpl<TbGoodsMapper, TbGoods> implements ITbGoodsService
{
    @Autowired
    private TbGoodsMapper tbGoodsMapper;

    /**
     * 查询商品信息
     * 
     * @param id 商品信息主键
     * @return 商品信息
     */
    @Override
    public TbGoods selectTbGoodsById(Long id)
    {
        return getById(id);
    }

    /**
     * 查询商品信息列表
     * 
     * @param tbGoods 商品信息
     * @return 商品信息
     */
    @Override
    public List<TbGoods> selectTbGoodsList(TbGoods tbGoods)
    {
        return tbGoodsMapper.selectTbGoodsList(tbGoods);
    }

    /**
     * 新增商品信息
     * 
     * @param tbGoods 商品信息
     * @return 结果
     */
    @Override
    public int insertTbGoods(TbGoods tbGoods)
    {
        return save(tbGoods) ? 1 : 0;
    }

    /**
     * 修改商品信息
     * 
     * @param tbGoods 商品信息
     * @return 结果
     */
    @Override
    public int updateTbGoods(TbGoods tbGoods)
    {
        return tbGoodsMapper.updateTbGoods(tbGoods);
    }

    /**
     * 批量删除商品信息
     * 
     * @param ids 需要删除的商品信息主键
     * @return 结果
     */
    @Override
    public int deleteTbGoodsByIds(Long[] ids)
    {
        return removeByIds(Arrays.asList(ids)) ? 1 : 0;
    }

    /**
     * 删除商品信息信息
     * 
     * @param id 商品信息主键
     * @return 结果
     */
    @Override
    public int deleteTbGoodsById(Long id)
    {
        return removeById(id) ? 1 : 0;
    }
}

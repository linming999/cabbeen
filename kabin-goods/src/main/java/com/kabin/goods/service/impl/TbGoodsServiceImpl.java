package com.kabin.goods.service.impl;

import java.util.Collections;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kabin.common.utils.DateUtils;
import com.kabin.goods.domain.TbGoodsImage;
import com.kabin.goods.domain.TbGoodsType;
import com.kabin.goods.domain.dto.GoodsDto;
import com.kabin.goods.mapper.TbGoodsImageMapper;
import com.kabin.goods.mapper.TbGoodsTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kabin.goods.mapper.TbGoodsMapper;
import com.kabin.goods.domain.TbGoods;
import com.kabin.goods.service.ITbGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 商品信息Service业务层处理
 *
 * @author kabin
 * @date 2025-07-16
 */
@Service
public class TbGoodsServiceImpl extends ServiceImpl<TbGoodsMapper, TbGoods> implements ITbGoodsService {
    @Autowired
    private TbGoodsMapper tbGoodsMapper;

    @Autowired
    private TbGoodsImageMapper tbGoodsImageMapper;

    @Autowired
    private TbGoodsTypeMapper tbGoodsTypeMapper;

    /**
     * 查询商品信息
     *
     * @param id 商品信息主键
     * @return 商品信息
     */
    @Override
    public TbGoods selectTbGoodsById(Long id) {
        return getById(id);
    }

    /**
     * 查询商品信息列表
     *
     * @param tbGoods 商品信息
     * @return 商品信息
     */
    @Override
    public List<TbGoods> selectTbGoodsList(TbGoods tbGoods) {
        return tbGoodsMapper.selectTbGoodsList(tbGoods);
    }

    /**
     * 新增商品信息
     *
     * @param tbGoods 商品信息
     * @return 结果
     */
    @Override
    public int insertTbGoods(TbGoods tbGoods) {
        return save(tbGoods) ? 1 : 0;
    }

    /**
     * 修改商品信息
     *
     * @param tbGoods 商品信息
     * @return 结果
     */
    @Override
    public int updateTbGoods(TbGoods tbGoods) {
        return tbGoodsMapper.updateTbGoods(tbGoods);
    }

    /**
     * 批量删除商品信息
     *
     * @param ids 需要删除的商品信息主键
     * @return 结果
     */
    @Override
    public int deleteTbGoodsByIds(Long[] ids) {
        return removeByIds(Arrays.asList(ids)) ? 1 : 0;
    }

    /**
     * 删除商品信息信息
     *
     * @param id 商品信息主键
     * @return 结果
     */
    @Override
    public int deleteTbGoodsById(Long id) {
        return removeById(id) ? 1 : 0;
    }

    /**
     * 修改商品信息状态
     *
     * @param ids 商品信息主键
     * @return 结果
     */
    @Override
    public int updateStatusBatch(List<Long> ids, Integer status) {
        return tbGoodsMapper.updateStatusBatch(ids, status);
    }

    /**
     * 获取商品列表
     *
     * @param category 分类名称
     *
     */
    @Override
    public List<GoodsDto> getGoodsWithImagesByCategory(String category) {
        // 1. 查询类型
        TbGoodsType type = tbGoodsTypeMapper.selectOne(
                new LambdaQueryWrapper<TbGoodsType>()
                        .eq(TbGoodsType::getType, category)
        );

        if (type == null) {
            return Collections.emptyList();
        }

        // 2. 查询商品（状态为0=上架）
        List<TbGoods> goodsList = this.list(
                new LambdaQueryWrapper<TbGoods>()
                        .eq(TbGoods::getType, type.getId())
                        .eq(TbGoods::getStatus, 0)
        );

        // 3. 查询图片并封装 DTO
        return goodsList.stream().map(good -> {
            GoodsDto dto = new GoodsDto();
            dto.setId(good.getId());
            dto.setName(good.getGoodName());
            dto.setPrice(good.getGoodPrice());

            List<TbGoodsImage> images = tbGoodsImageMapper.selectList(
                    new LambdaQueryWrapper<TbGoodsImage>()
                            .eq(TbGoodsImage::getGoodId, good.getId())
            );
            List<String> urls = images.stream()
                    .map(TbGoodsImage::getImage)
                    .collect(Collectors.toList());

            dto.setImageList(urls);
            return dto;
        }).collect(Collectors.toList());
    }
}

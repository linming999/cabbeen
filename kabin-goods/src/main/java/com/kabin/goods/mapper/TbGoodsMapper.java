package com.kabin.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.kabin.goods.domain.TbGoods;
import org.apache.ibatis.annotations.Param;

/**
 * 商品信息Mapper接口
 * 
 * @author kabin
 * @date 2025-07-16
 */
@Mapper
public interface TbGoodsMapper extends BaseMapper<TbGoods>
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
     * 删除商品信息
     * 
     * @param id 商品信息主键
     * @return 结果
     */
    public int deleteTbGoodsById(Long id);

    /**
     * 批量删除商品信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbGoodsByIds(Long[] ids);

    int updateStatusBatch(@Param("ids") List<Long> ids, @Param("status") Integer status);

}

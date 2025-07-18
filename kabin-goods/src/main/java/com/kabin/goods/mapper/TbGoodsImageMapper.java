package com.kabin.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.kabin.goods.domain.TbGoodsImage;
import org.apache.ibatis.annotations.Param;

/**
 * 商品款式图片Mapper接口
 * 
 * @author kabin
 * @date 2025-07-17
 */
@Mapper
public interface TbGoodsImageMapper extends BaseMapper<TbGoodsImage>
{
    /**
     * 查询商品款式图片
     * 
     * @param id 商品款式图片主键
     * @return 商品款式图片
     */
    public TbGoodsImage selectTbGoodsImageById(Long id);

    /**
     * 查询商品款式图片列表
     * 
     * @param tbGoodsImage 商品款式图片
     * @return 商品款式图片集合
     */
    public List<TbGoodsImage> selectTbGoodsImageList(TbGoodsImage tbGoodsImage);

    /**
     * 新增商品款式图片
     * 
     * @param tbGoodsImage 商品款式图片
     * @return 结果
     */
    public int insertTbGoodsImage(TbGoodsImage tbGoodsImage);

    /**
     * 修改商品款式图片
     * 
     * @param tbGoodsImage 商品款式图片
     * @return 结果
     */
    public int updateTbGoodsImage(TbGoodsImage tbGoodsImage);

    /**
     * 删除商品款式图片
     * 
     * @param id 商品款式图片主键
     * @return 结果
     */
    public int deleteTbGoodsImageById(Long id);

    /**
     * 批量删除商品款式图片
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbGoodsImageByIds(Long[] ids);

    List<TbGoodsImage> selectByGoodId(@Param("goodId") Long goodId);
}

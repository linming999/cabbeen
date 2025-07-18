package com.kabin.goods.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kabin.common.utils.DateUtils;
import com.kabin.oss.AliyunOSSOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kabin.goods.mapper.TbGoodsImageMapper;
import com.kabin.goods.domain.TbGoodsImage;
import com.kabin.goods.service.ITbGoodsImageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * 商品款式图片Service业务层处理
 * 
 * @author kabin
 * @date 2025-07-17
 */
@Service
public class TbGoodsImageServiceImpl extends ServiceImpl<TbGoodsImageMapper, TbGoodsImage> implements ITbGoodsImageService
{
    @Autowired
    private TbGoodsImageMapper tbGoodsImageMapper;

    @Resource
    private AliyunOSSOperator aliyunOSSOperator;

    /**
     * 查询商品款式图片
     * 
     * @param id 商品款式图片主键
     * @return 商品款式图片
     */
    @Override
    public TbGoodsImage selectTbGoodsImageById(Long id)
    {
        return getById(id);
    }

    /**
     * 查询商品款式图片列表
     * 
     * @param tbGoodsImage 商品款式图片
     * @return 商品款式图片
     */
    @Override
    public List<TbGoodsImage> selectTbGoodsImageList(TbGoodsImage tbGoodsImage)
    {
        return tbGoodsImageMapper.selectTbGoodsImageList(tbGoodsImage);
    }

    /**
     * 新增商品款式图片
     * 
     * @param tbGoodsImage 商品款式图片
     * @return 结果
     */
    @Override
    public int insertTbGoodsImage(TbGoodsImage tbGoodsImage)
    {
        return save(tbGoodsImage) ? 1 : 0;
    }

    /**
     * 修改商品款式图片
     * 
     * @param tbGoodsImage 商品款式图片
     * @return 结果
     */
    @Override
    public int updateTbGoodsImage(TbGoodsImage tbGoodsImage)
    {
        return updateById(tbGoodsImage) ? 1 : 0;
    }

    /**
     * 批量删除商品款式图片
     * 
     * @param ids 需要删除的商品款式图片主键
     * @return 结果
     */
    @Override
    public int deleteTbGoodsImageByIds(Long[] ids)
    {
        return removeByIds(Arrays.asList(ids)) ? 1 : 0;
    }

    /**
     * 删除商品款式图片信息
     * 
     * @param id 商品款式图片主键
     * @return 结果
     */
    @Override
    public int deleteTbGoodsImageById(Long id)
    {
        return removeById(id) ? 1 : 0;
    }

    @Override
    public List<TbGoodsImage> selectByGoodId(Long goodId) {
        return tbGoodsImageMapper.selectByGoodId(goodId);
    }

    @Override
    public boolean replaceStylesByGoodId(Long goodId, List<TbGoodsImage> imageList) {
        // 删除旧图片
        LambdaQueryWrapper<TbGoodsImage> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TbGoodsImage::getGoodId, goodId);
        this.remove(queryWrapper);

        // 批量保存新图片（带 style、isMain）
        for (TbGoodsImage img : imageList) {
            img.setGoodId(goodId);
            img.setCreateTime(DateUtils.getNowDate());
        }
        return this.saveBatch(imageList);
    }
    @Override
    public String uploadToOSS(MultipartFile file) throws Exception {
        return aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
    }

}

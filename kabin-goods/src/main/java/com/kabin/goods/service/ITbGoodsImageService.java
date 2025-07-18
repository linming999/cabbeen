package com.kabin.goods.service;

import java.util.List;
import com.kabin.goods.domain.TbGoodsImage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 商品款式图片Service接口
 * 
 * @author kabin
 * @date 2025-07-17
 */
public interface ITbGoodsImageService extends IService<TbGoodsImage>
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
     * 批量删除商品款式图片
     * 
     * @param ids 需要删除的商品款式图片主键集合
     * @return 结果
     */
    public int deleteTbGoodsImageByIds(Long[] ids);

    /**
     * 删除商品款式图片信息
     * 
     * @param id 商品款式图片主键
     * @return 结果
     */
    public int deleteTbGoodsImageById(Long id);

    List<TbGoodsImage> selectByGoodId(Long goodId);

    boolean replaceStylesByGoodId(Long goodId, List<TbGoodsImage> imageList);

    /**
     * 上传图片至OSS并返回地址
     * @param file 文件对象
     * @return 图片访问URL
     */
    String uploadToOSS(MultipartFile file) throws Exception;

}

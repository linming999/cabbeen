package com.kabin.goods.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.kabin.common.core.domain.R;
import com.kabin.oss.AliyunOSSOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.kabin.common.annotation.Log;
import com.kabin.common.core.controller.BaseController;
import com.kabin.common.core.domain.AjaxResult;
import com.kabin.common.enums.BusinessType;
import com.kabin.goods.domain.TbGoodsImage;
import com.kabin.goods.service.ITbGoodsImageService;
import com.kabin.common.utils.poi.ExcelUtil;
import com.kabin.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 商品款式图片Controller
 * 
 * @author kabin
 * @date 2025-07-17
 */
@RestController
@RequestMapping("/goods/style")
@Api(tags = "商品款式图片相关接口")
public class TbGoodsImageController extends BaseController
{
    @Autowired
    private ITbGoodsImageService tbGoodsImageService;

    @Resource
    private AliyunOSSOperator aliyunOSSOperator;

    /**
     * 查询商品款式图片列表
     */
    @PreAuthorize("@ss.hasPermi('goods:style:list')")
    @GetMapping("/list")
    @ApiOperation(value = "获取商品款式图片列表")
    public TableDataInfo<List<TbGoodsImage>> list(TbGoodsImage tbGoodsImage)
    {
        startPage();
        List<TbGoodsImage> list = tbGoodsImageService.selectTbGoodsImageList(tbGoodsImage);
        return getDataTable(list);
    }

    /**
     * 导出商品款式图片列表
     */
    @PreAuthorize("@ss.hasPermi('goods:style:export')")
    @Log(title = "商品款式图片", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("导出商品款式图片列表")
    public void export(HttpServletResponse response, TbGoodsImage tbGoodsImage)
    {
        List<TbGoodsImage> list = tbGoodsImageService.selectTbGoodsImageList(tbGoodsImage);
        ExcelUtil<TbGoodsImage> util = new ExcelUtil<TbGoodsImage>(TbGoodsImage.class);
        util.exportExcel(response, list, "商品款式图片数据");
    }

    /**
     * 获取商品款式图片详细信息
     */
    @PreAuthorize("@ss.hasPermi('goods:style:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation("获取商品款式图片详细信息")
    public R<TbGoodsImage> getInfo(@ApiParam(value = "商品款式图片ID", required = true)
            @PathVariable("id") Long id)
    {
        return R.ok(tbGoodsImageService.selectTbGoodsImageById(id));
    }

    /**
     * 新增商品款式图片
     */
    @PreAuthorize("@ss.hasPermi('goods:style:add')")
    @Log(title = "商品款式图片", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增商品款式图片")
    public AjaxResult add(@ApiParam(value = "商品款式图片实体", required = true) @RequestBody TbGoodsImage tbGoodsImage)
    {
        return toAjax(tbGoodsImageService.insertTbGoodsImage(tbGoodsImage));
    }

    /**
     * 修改商品款式图片
     */
    @PreAuthorize("@ss.hasPermi('goods:style:edit')")
    @Log(title = "商品款式图片", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改商品款式图片")
    public AjaxResult edit(@ApiParam(value = "商品款式图片实体", required = true) @RequestBody TbGoodsImage tbGoodsImage)
    {
        return toAjax(tbGoodsImageService.updateTbGoodsImage(tbGoodsImage));
    }

    /**
     * 删除商品款式图片
     */
    @PreAuthorize("@ss.hasPermi('goods:style:remove')")
    @Log(title = "商品款式图片", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation("删除商品款式图片")
    public AjaxResult remove(@ApiParam(value = "要删除的数据id的数组") @PathVariable Long[] ids)
    {
        return toAjax(tbGoodsImageService.deleteTbGoodsImageByIds(ids));
    }

    /**
     * 替换指定商品的所有款式图
     */
    @PreAuthorize("@ss.hasPermi('goods:style:edit')")
    @Log(title = "商品款式图片", businessType = BusinessType.UPDATE)
    @PostMapping("/replace/{goodId}")
    @ApiOperation("替换指定商品的所有款式图片")
    public AjaxResult replaceStyles(
            @ApiParam(value = "商品ID", required = true) @PathVariable Long goodId,
            @ApiParam(value = "新的款式图片列表", required = true) @RequestBody List<TbGoodsImage> imageList
    ) {
        boolean result = tbGoodsImageService.replaceStylesByGoodId(goodId, imageList);
        return result ? AjaxResult.success("商品款式图已更新") : AjaxResult.error("更新商品款式图失败");
    }

    @ApiOperation("上传商品款式图至OSS")
    @PostMapping("/upload")
    public AjaxResult uploadStyleImage(@RequestParam("file") MultipartFile file) {
        try {
            // 上传图片字节流和原始文件名
            String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());

            // 返回统一 AjaxResult
            AjaxResult ajax = AjaxResult.success("上传成功");
            ajax.put("url", url);  // 关键字段：必须是 el-upload 可识别的 url
            ajax.put("fileName", url);
            ajax.put("originalFilename", file.getOriginalFilename());

            return ajax;
        } catch (Exception e) {
            return AjaxResult.error("上传失败: " + e.getMessage());
        }
    }



}

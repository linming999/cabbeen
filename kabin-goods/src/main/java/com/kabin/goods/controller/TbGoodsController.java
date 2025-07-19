package com.kabin.goods.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.kabin.common.core.domain.R;
import com.kabin.goods.domain.dto.GoodsDto;
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
import com.kabin.goods.domain.TbGoods;
import com.kabin.goods.service.ITbGoodsService;
import com.kabin.common.utils.poi.ExcelUtil;
import com.kabin.common.core.page.TableDataInfo;

/**
 * 商品信息Controller
 * 
 * @author kabin
 * @date 2025-07-16
 */
@RestController
@RequestMapping("/goods/good")
@Api(tags = "商品信息相关接口")
public class TbGoodsController extends BaseController
{
    @Autowired
    private ITbGoodsService tbGoodsService;

    /**
     * 查询商品信息列表
     */
    @PreAuthorize("@ss.hasPermi('goods:good:list')")
    @GetMapping("/list")
    @ApiOperation(value = "获取商品信息列表")
    public TableDataInfo<List<TbGoods>> list(TbGoods tbGoods)
    {
        startPage();
        List<TbGoods> list = tbGoodsService.selectTbGoodsList(tbGoods);
        return getDataTable(list);
    }

    /**
     * 导出商品信息列表
     */
    @PreAuthorize("@ss.hasPermi('goods:good:export')")
    @Log(title = "商品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("导出商品信息列表")
    public void export(HttpServletResponse response, TbGoods tbGoods)
    {
        List<TbGoods> list = tbGoodsService.selectTbGoodsList(tbGoods);
        ExcelUtil<TbGoods> util = new ExcelUtil<TbGoods>(TbGoods.class);
        util.exportExcel(response, list, "商品信息数据");
    }

    /**
     * 获取商品信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('goods:good:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation("获取商品信息详细信息")
    public R<TbGoods> getInfo(@ApiParam(value = "商品信息ID", required = true)
            @PathVariable("id") Long id)
    {
        return R.ok(tbGoodsService.selectTbGoodsById(id));
    }

    /**
     * 新增商品信息
     */
    @PreAuthorize("@ss.hasPermi('goods:good:add')")
    @Log(title = "商品信息", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增商品信息")
    public AjaxResult add(@ApiParam(value = "商品信息实体", required = true)
                          @RequestBody TbGoods tbGoods)
    {
        int row = tbGoodsService.insertTbGoods(tbGoods);
        if (row > 0) {
            // ✅ 关键点：返回插入成功后的主键ID
            return AjaxResult.success(tbGoods.getId());
        }
        return AjaxResult.error("新增商品失败");
    }

    /**
     * 修改商品信息
     */
    @PreAuthorize("@ss.hasPermi('goods:good:edit')")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改商品信息")
    public AjaxResult edit(@ApiParam(value = "商品信息实体", required = true) @RequestBody TbGoods tbGoods)
    {
        return toAjax(tbGoodsService.updateTbGoods(tbGoods));
    }

    /**
     * 删除商品信息
     */
    @PreAuthorize("@ss.hasPermi('goods:good:remove')")
    @Log(title = "商品信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation("删除商品信息")
    public AjaxResult remove(@ApiParam(value = "要删除的数据id的数组") @PathVariable Long[] ids)
    {
        return toAjax(tbGoodsService.deleteTbGoodsByIds(ids));
    }

    /**
     * 批量商品状态
     */
    @PutMapping("/updateStatus")
    public AjaxResult updateStatus(@RequestBody Map<String, Object> params) {
        List<Long> ids = (List<Long>) params.get("ids");
        Integer status = (Integer) params.get("status");
        return toAjax(tbGoodsService.updateStatusBatch(ids, status));
    }

    /**
     * 获取某一分类下所有商品（包含轮播图）
     */
    @GetMapping("/listByCategory")
    @ApiOperation("根据分类名称获取商品及图片")
    public R<List<GoodsDto>> listByCategory(
            @ApiParam("分类名称，如“衬衫”") @RequestParam String category) {
        List<GoodsDto> list = tbGoodsService.getGoodsWithImagesByCategory(category);
        return R.ok(list);
    }

}

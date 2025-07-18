package com.kabin.goods.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.kabin.common.core.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kabin.common.annotation.Log;
import com.kabin.common.core.controller.BaseController;
import com.kabin.common.core.domain.AjaxResult;
import com.kabin.common.enums.BusinessType;
import com.kabin.goods.domain.TbGoodsType;
import com.kabin.goods.service.ITbGoodsTypeService;
import com.kabin.common.utils.poi.ExcelUtil;
import com.kabin.common.core.page.TableDataInfo;

/**
 * 商品类型Controller
 * 
 * @author kabin
 * @date 2025-07-16
 */
@RestController
@RequestMapping("/goods/type")
@Api(tags = "商品类型相关接口")
public class TbGoodsTypeController extends BaseController
{
    @Autowired
    private ITbGoodsTypeService tbGoodsTypeService;

    /**
     * 查询商品类型列表
     */
    @PreAuthorize("@ss.hasPermi('goods:type:list')")
    @GetMapping("/list")
    @ApiOperation(value = "获取商品类型列表")
    public TableDataInfo<List<TbGoodsType>> list(TbGoodsType tbGoodsType)
    {
        startPage();
        List<TbGoodsType> list = tbGoodsTypeService.selectTbGoodsTypeList(tbGoodsType);
        return getDataTable(list);
    }

    /**
     * 导出商品类型列表
     */
    @PreAuthorize("@ss.hasPermi('goods:type:export')")
    @Log(title = "商品类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("导出商品类型列表")
    public void export(HttpServletResponse response, TbGoodsType tbGoodsType)
    {
        List<TbGoodsType> list = tbGoodsTypeService.selectTbGoodsTypeList(tbGoodsType);
        ExcelUtil<TbGoodsType> util = new ExcelUtil<TbGoodsType>(TbGoodsType.class);
        util.exportExcel(response, list, "商品类型数据");
    }

    /**
     * 获取商品类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('goods:type:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation("获取商品类型详细信息")
    public R<TbGoodsType> getInfo(@ApiParam(value = "商品类型ID", required = true)
            @PathVariable("id") Long id)
    {
        return R.ok(tbGoodsTypeService.selectTbGoodsTypeById(id));
    }

    /**
     * 新增商品类型
     */
    @PreAuthorize("@ss.hasPermi('goods:type:add')")
    @Log(title = "商品类型", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增商品类型")
    public AjaxResult add(@ApiParam(value = "商品类型实体", required = true) @RequestBody TbGoodsType tbGoodsType)
    {
        return toAjax(tbGoodsTypeService.insertTbGoodsType(tbGoodsType));
    }

    /**
     * 修改商品类型
     */
    @PreAuthorize("@ss.hasPermi('goods:type:edit')")
    @Log(title = "商品类型", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改商品类型")
    public AjaxResult edit(@ApiParam(value = "商品类型实体", required = true) @RequestBody TbGoodsType tbGoodsType)
    {
        return toAjax(tbGoodsTypeService.updateTbGoodsType(tbGoodsType));
    }

    /**
     * 删除商品类型
     */
    @PreAuthorize("@ss.hasPermi('goods:type:remove')")
    @Log(title = "商品类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation("删除商品类型")
    public AjaxResult remove(@ApiParam(value = "要删除的数据id的数组") @PathVariable Long[] ids)
    {
        return toAjax(tbGoodsTypeService.deleteTbGoodsTypeByIds(ids));
    }

    /**
     * 获取商品类型树
     */
    @GetMapping("/tree")
    @ApiOperation("获取商品类型树")
    public AjaxResult getTypeTree() {
        return AjaxResult.success(tbGoodsTypeService.selectGoodsTypeTree());
    }
}

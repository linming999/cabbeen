package com.kabin.file.controller;

import com.kabin.common.core.domain.R;
import com.kabin.oss.AliyunOSSOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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
import com.kabin.file.domain.TbFinancialFile;
import com.kabin.file.service.ITbFinancialFileService;
import com.kabin.common.utils.poi.ExcelUtil;
import com.kabin.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 财务报信息Controller
 *
 * @author lichuan
 * @date 2025-07-08
 */
@RestController
@RequestMapping("/file/finance")
@Api(tags = "财务报信息相关接口")
public class TbFinancialFileController extends BaseController
{
    @Autowired
    private ITbFinancialFileService tbFinancialFileService;

    @Resource
    private AliyunOSSOperator aliyunOSSOperator;


    /**
     * 查询财务报信息列表
     */
//    @PreAuthorize("@ss.hasPermi('file:finance:list')")
    @GetMapping("/list")
    @ApiOperation("查询财务报信息列表")
    public TableDataInfo<List<TbFinancialFile>> list(@ApiParam(value = "财务报信息查询条件") TbFinancialFile tbFinancialFile)
    {

        List<TbFinancialFile> list = tbFinancialFileService.selectTbFinancialFileList(tbFinancialFile);
        return getDataTable(list);
    }

    /**
     * 导出财务报信息列表
     */
    @PreAuthorize("@ss.hasPermi('file:finance:export')")
    @Log(title = "财务报信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("导出财务报信息列表")
    public void export(HttpServletResponse response, @ApiParam(value = "财务报信息查询条件") TbFinancialFile tbFinancialFile)
    {
        List<TbFinancialFile> list = tbFinancialFileService.selectTbFinancialFileList(tbFinancialFile);
        ExcelUtil<TbFinancialFile> util = new ExcelUtil<TbFinancialFile>(TbFinancialFile.class);
        util.exportExcel(response, list, "财务报信息数据");
    }

    /**
     * 获取财务报信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('file:finance:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation("获取财务报信息详细信息")
    public R<TbFinancialFile> getInfo(@ApiParam(value = "财务报信息ID", required = true)
                                   @PathVariable("id") Long id)
    {
                return R.ok(tbFinancialFileService.selectTbFinancialFileById(id));
    }

    /**
     * 新增财务报信息
     */
    @PreAuthorize("@ss.hasPermi('file:finance:add')")
    @Log(title = "财务报信息", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("新增财务报信息")
    public AjaxResult add(@ApiParam(value = "财务报信息实体", required = true) @RequestBody TbFinancialFile tbFinancialFile)
    {
        return toAjax(tbFinancialFileService.insertTbFinancialFile(tbFinancialFile));
    }

    /**
     * 修改财务报信息
     */
    @PreAuthorize("@ss.hasPermi('file:finance:edit')")
    @Log(title = "财务报信息", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("修改财务报信息")
    public AjaxResult edit(@ApiParam(value = "财务报信息实体", required = true)  @RequestBody TbFinancialFile tbFinancialFile)
    {
        return toAjax(tbFinancialFileService.updateTbFinancialFile(tbFinancialFile));
    }

    /**
     * 删除财务报信息
     */
    @PreAuthorize("@ss.hasPermi('file:finance:remove')")
    @Log(title = "财务报信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation("删除财务报信息")
    public AjaxResult remove(@ApiParam(value = "财务报信息ID数组", required = true) @PathVariable Long[] ids)
    {
        return toAjax(tbFinancialFileService.deleteTbFinancialFileByIds(ids));
    }

    /**
     * 通用上传请求（单个）
     */
    @ApiOperation("财务报表上传")
    @PostMapping("/upload")
    public AjaxResult uploadFile(MultipartFile file) throws Exception
    {
        try {
            // 上传到OSS
            String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
            AjaxResult ajax = AjaxResult.success();
            ajax.put("url", url);
            ajax.put("fileName", url);
            // ajax.put("newFileName", url.substring(url.lastIndexOf("/")));
            ajax.put("originalFilename", file.getOriginalFilename());

            return ajax;
        }catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }
}

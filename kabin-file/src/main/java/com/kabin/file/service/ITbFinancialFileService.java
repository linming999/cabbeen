package com.kabin.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import com.kabin.file.domain.TbFinancialFile;

/**
 * 财务报信息Service接口
 * 
 * @author lichuan
 * @date 2025-07-08
 */
public interface ITbFinancialFileService extends IService<TbFinancialFile>
{
    /**
     * 查询财务报信息
     * 
     * @param id 财务报信息主键
     * @return 财务报信息
     */
    public TbFinancialFile selectTbFinancialFileById(Long id);

    /**
     * 查询财务报信息列表
     * 
     * @param tbFinancialFile 财务报信息
     * @return 财务报信息集合
     */
    public List<TbFinancialFile> selectTbFinancialFileList(TbFinancialFile tbFinancialFile);

    /**
     * 新增财务报信息
     * 
     * @param tbFinancialFile 财务报信息
     * @return 结果
     */
    public int insertTbFinancialFile(TbFinancialFile tbFinancialFile);

    /**
     * 修改财务报信息
     * 
     * @param tbFinancialFile 财务报信息
     * @return 结果
     */
    public int updateTbFinancialFile(TbFinancialFile tbFinancialFile);

    /**
     * 批量删除财务报信息
     * 
     * @param ids 需要删除的财务报信息主键集合
     * @return 结果
     */
    public int deleteTbFinancialFileByIds(Long[] ids);

    /**
     * 删除财务报信息信息
     * 
     * @param id 财务报信息主键
     * @return 结果
     */
    public int deleteTbFinancialFileById(Long id);
}

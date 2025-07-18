package com.kabin.file.service.impl;

import java.util.List;
import com.kabin.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kabin.file.mapper.TbFinancialFileMapper;
import com.kabin.file.domain.TbFinancialFile;
import com.kabin.file.service.ITbFinancialFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.Arrays;

import static com.kabin.common.utils.SecurityUtils.getUsername;

/**
 * 财务报信息Service业务层处理
 * 
 * @author lichuan
 * @date 2025-07-08
 */
@Service
public class TbFinancialFileServiceImpl extends ServiceImpl<TbFinancialFileMapper, TbFinancialFile> implements ITbFinancialFileService
{
    @Autowired
    private TbFinancialFileMapper tbFinancialFileMapper;

    /**
     * 查询财务报信息
     * 
     * @param id 财务报信息主键
     * @return 财务报信息
     */
    @Override
    public TbFinancialFile selectTbFinancialFileById(Long id)
    {
                return getById(id);
    }

    /**
     * 查询财务报信息列表
     * 
     * @param tbFinancialFile 财务报信息
     * @return 财务报信息
     */
    @Override
    public List<TbFinancialFile> selectTbFinancialFileList(TbFinancialFile tbFinancialFile)
    {

        List<TbFinancialFile> list = tbFinancialFileMapper.selectTbFinancialFileList(tbFinancialFile);
        // 按照创建时间倒序排列
        list.sort((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()));
        return list;
    }

    /**
     * 新增财务报信息
     * 
     * @param tbFinancialFile 财务报信息
     * @return 结果
     */
    @Override
    public int insertTbFinancialFile(TbFinancialFile tbFinancialFile)
    {
        tbFinancialFile.setCreateBy(getUsername());
        tbFinancialFile.setCreateTime(DateUtils.getNowDate());
                return save(tbFinancialFile) ? 1 : 0;
    }

    /**
     * 修改财务报信息
     * 
     * @param tbFinancialFile 财务报信息
     * @return 结果
     */
    @Override
    public int updateTbFinancialFile(TbFinancialFile tbFinancialFile)
    {
        tbFinancialFile.setUpdateBy(getUsername());
        tbFinancialFile.setUpdateTime(DateUtils.getNowDate());
                return updateById(tbFinancialFile) ? 1 : 0;
    }

    /**
     * 批量删除财务报信息
     * 
     * @param ids 需要删除的财务报信息主键
     * @return 结果
     */
    @Override
    public int deleteTbFinancialFileByIds(Long[] ids)
    {
                return removeByIds(Arrays.asList(ids)) ? 1 : 0;
    }

    /**
     * 删除财务报信息信息
     * 
     * @param id 财务报信息主键
     * @return 结果
     */
    @Override
    public int deleteTbFinancialFileById(Long id)
    {
                return removeById(id) ? 1 : 0;
    }
}

package com.kabin.goods.service.impl;

import java.util.List;
import com.kabin.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kabin.goods.mapper.TbGoodsTypeMapper;
import com.kabin.goods.domain.TbGoodsType;
import com.kabin.goods.service.ITbGoodsTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.Arrays;

/**
 * 商品类型Service业务层处理
 * 
 * @author kabin
 * @date 2025-07-16
 */
@Service
public class TbGoodsTypeServiceImpl extends ServiceImpl<TbGoodsTypeMapper, TbGoodsType> implements ITbGoodsTypeService
{
    @Autowired
    private TbGoodsTypeMapper tbGoodsTypeMapper;

    /**
     * 查询商品类型
     * 
     * @param id 商品类型主键
     * @return 商品类型
     */
    @Override
    public TbGoodsType selectTbGoodsTypeById(Long id)
    {
        return getById(id);
    }

    /**
     * 查询商品类型列表
     * 
     * @param tbGoodsType 商品类型
     * @return 商品类型
     */
    @Override
    public List<TbGoodsType> selectTbGoodsTypeList(TbGoodsType tbGoodsType)
    {
        return tbGoodsTypeMapper.selectTbGoodsTypeList(tbGoodsType);
    }

    /**
     * 新增商品类型
     * 
     * @param tbGoodsType 商品类型
     * @return 结果
     */
    @Override
    public int insertTbGoodsType(TbGoodsType tbGoodsType)
    {
        return save(tbGoodsType) ? 1 : 0;
    }

    /**
     * 修改商品类型
     * 
     * @param tbGoodsType 商品类型
     * @return 结果
     */
    @Override
    public int updateTbGoodsType(TbGoodsType tbGoodsType)
    {
        return updateById(tbGoodsType) ? 1 : 0;
    }

    /**
     * 批量删除商品类型
     * 
     * @param ids 需要删除的商品类型主键
     * @return 结果
     */
    @Override
    public int deleteTbGoodsTypeByIds(Long[] ids)
    {
        return removeByIds(Arrays.asList(ids)) ? 1 : 0;
    }

    /**
     * 删除商品类型信息
     * 
     * @param id 商品类型主键
     * @return 结果
     */
    @Override
    public int deleteTbGoodsTypeById(Long id)
    {
        return removeById(id) ? 1 : 0;
    }

    /**
     * 查询商品类型树
     * @return 商品类型树结构
     */
    @Override
    public List<TbGoodsType> selectGoodsTypeTree() {
        List<TbGoodsType> all = tbGoodsTypeMapper.selectTbGoodsTypeList(new TbGoodsType());
        return buildTree(all, 0L);
    }

    private List<TbGoodsType> buildTree(List<TbGoodsType> all, Long parentId) {
        List<TbGoodsType> tree = new java.util.ArrayList<>();
        for (TbGoodsType type : all) {
            if ((type.getParentId() == null && parentId == null) || (type.getParentId() != null && type.getParentId().equals(parentId))) {
                List<TbGoodsType> children = buildTree(all, type.getId());
                if (!children.isEmpty()) {
                    type.setChildren(children);
                }
                tree.add(type);
            }
        }
        return tree;
    }
}

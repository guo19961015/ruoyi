package com.ruoyi.activiti.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.activiti.mapper.RyProductMapper;
import com.ruoyi.activiti.domain.RyProduct;
import com.ruoyi.activiti.service.IRyProductService;
import com.ruoyi.common.core.text.Convert;

/**
 * 产品Service业务层处理
 * 
 * @author xiaoguo
 * @date 2020-09-25
 */
@Service
public class RyProductServiceImpl implements IRyProductService 
{
    @Autowired
    private RyProductMapper ryProductMapper;

    /**
     * 查询产品
     * 
     * @param id 产品ID
     * @return 产品
     */
    @Override
    public RyProduct selectRyProductById(Long id)
    {
        return ryProductMapper.selectRyProductById(id);
    }

    /**
     * 查询产品列表
     * 
     * @param ryProduct 产品
     * @return 产品
     */
    @Override
    public List<RyProduct> selectRyProductList(RyProduct ryProduct)
    {
        return ryProductMapper.selectRyProductList(ryProduct);
    }

    /**
     * 新增产品
     * 
     * @param ryProduct 产品
     * @return 结果
     */
    @Override
    public int insertRyProduct(RyProduct ryProduct)
    {
        return ryProductMapper.insertRyProduct(ryProduct);
    }

    /**
     * 修改产品
     * 
     * @param ryProduct 产品
     * @return 结果
     */
    @Override
    public int updateRyProduct(RyProduct ryProduct)
    {
        return ryProductMapper.updateRyProduct(ryProduct);
    }

    /**
     * 删除产品对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteRyProductByIds(String ids)
    {
        return ryProductMapper.deleteRyProductByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除产品信息
     * 
     * @param id 产品ID
     * @return 结果
     */
    @Override
    public int deleteRyProductById(Long id)
    {
        return ryProductMapper.deleteRyProductById(id);
    }
}

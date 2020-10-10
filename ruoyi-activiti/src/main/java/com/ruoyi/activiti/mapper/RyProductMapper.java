package com.ruoyi.activiti.mapper;

import java.util.List;
import com.ruoyi.activiti.domain.RyProduct;

/**
 * 产品Mapper接口
 * 
 * @author xiaoguo
 * @date 2020-09-25
 */
public interface RyProductMapper 
{
    /**
     * 查询产品
     * 
     * @param id 产品ID
     * @return 产品
     */
    public RyProduct selectRyProductById(Long id);

    /**
     * 查询产品列表
     * 
     * @param ryProduct 产品
     * @return 产品集合
     */
    public List<RyProduct> selectRyProductList(RyProduct ryProduct);

    /**
     * 新增产品
     * 
     * @param ryProduct 产品
     * @return 结果
     */
    public int insertRyProduct(RyProduct ryProduct);

    /**
     * 修改产品
     * 
     * @param ryProduct 产品
     * @return 结果
     */
    public int updateRyProduct(RyProduct ryProduct);

    /**
     * 删除产品
     * 
     * @param id 产品ID
     * @return 结果
     */
    public int deleteRyProductById(Long id);

    /**
     * 批量删除产品
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRyProductByIds(String[] ids);
}

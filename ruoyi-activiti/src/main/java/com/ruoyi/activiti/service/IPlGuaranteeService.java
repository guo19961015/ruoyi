package com.ruoyi.activiti.service;

import java.util.List;
import com.ruoyi.activiti.domain.PlGuarantee;

/**
 * 担保公司信息Service接口
 * 
 * @author xiaoguo
 * @date 2020-08-30
 */
public interface IPlGuaranteeService 
{
    /**
     * 查询担保公司信息
     * 
     * @param guaranteeId 担保公司信息ID
     * @return 担保公司信息
     */
    public PlGuarantee selectPlGuaranteeById(Long guaranteeId);

    /**
     * 查询担保公司信息列表
     * 
     * @param plGuarantee 担保公司信息
     * @return 担保公司信息集合
     */
    public List<PlGuarantee> selectPlGuaranteeList(PlGuarantee plGuarantee);
    public List<PlGuarantee> selectPlGuaranteeListDouble(PlGuarantee plGuarantee);
    /**
     * 新增担保公司信息
     * 
     * @param plGuarantee 担保公司信息
     * @return 结果
     */
    public int insertPlGuarantee(PlGuarantee plGuarantee);

    /**
     * 修改担保公司信息
     * 
     * @param plGuarantee 担保公司信息
     * @return 结果
     */
    public int updatePlGuarantee(PlGuarantee plGuarantee);

    /**
     * 批量删除担保公司信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePlGuaranteeByIds(String ids);

    /**
     * 删除担保公司信息信息
     * 
     * @param guaranteeId 担保公司信息ID
     * @return 结果
     */
    public int deletePlGuaranteeById(Long guaranteeId);
}

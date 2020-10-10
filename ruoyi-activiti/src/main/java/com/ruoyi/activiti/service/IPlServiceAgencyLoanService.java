package com.ruoyi.activiti.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.activiti.domain.PlComment;
import com.ruoyi.activiti.domain.PlServiceAgencyLoan;
import com.ruoyi.activiti.domain.PlServiceAgencyLoanVo;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * 7大板款信息Service接口
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
public interface IPlServiceAgencyLoanService 
{
    /**
     * 查询7大板款信息
     * 
     * @param id 7大板款信息ID
     * @return 7大板款信息
     */
    public PlServiceAgencyLoanVo selectPlServiceAgencyLoanById(Long id);

    /**
     * 查询7大板款信息列表
     * 
     * @param plServiceAgencyLoan 7大板款信息
     * @return 7大板款信息集合
     */
    public List<PlServiceAgencyLoan> selectPlServiceAgencyLoanList(PlServiceAgencyLoan plServiceAgencyLoan);

    /**
     * 新增7大板款信息
     * 
     * @param plServiceAgencyLoan 7大板款信息
     * @return 结果
     */
    public int insertPlServiceAgencyLoan(PlServiceAgencyLoanVo plServiceAgencyLoan);

    /**
     * 修改7大板款信息
     * 
     * @param plServiceAgencyLoan 7大板款信息
     * @return 结果
     */
    public int updatePlServiceAgencyLoan(PlServiceAgencyLoan plServiceAgencyLoan);
    public int updatePlServiceAgencyLoanAndComment(Long id,String comment,Long productId);

    /**
     * 批量删除7大板款信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePlServiceAgencyLoanByIds(String ids);

    /**
     * 删除7大板款信息信息
     * 
     * @param id 7大板款信息ID
     * @return 结果
     */
    public int deletePlServiceAgencyLoanById(Long id);

    public PlServiceAgencyLoanVo selectBizLeaveById(Long id);

    ProcessInstance submitApply(PlServiceAgencyLoanVo entity, String applyUserId, String key, Map<String, Object> variables);

    List<PlServiceAgencyLoanVo> findDoneTasks(PlServiceAgencyLoanVo bizLeave, String loginName);

    List<PlComment> selectComment(Long productId);
}

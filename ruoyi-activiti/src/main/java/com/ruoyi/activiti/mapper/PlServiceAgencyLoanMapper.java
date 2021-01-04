package com.ruoyi.activiti.mapper;

import java.util.List;

import com.ruoyi.activiti.domain.BizLeaveVo;
import com.ruoyi.activiti.domain.PlComment;
import com.ruoyi.activiti.domain.PlServiceAgencyLoan;
import com.ruoyi.activiti.domain.PlServiceAgencyLoanVo;

/**
 * 7大板款信息Mapper接口
 *
 * @author ruoyi
 * @date 2020-09-21
 */
public interface PlServiceAgencyLoanMapper
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
    public int insertPlServiceAgencyLoan(PlServiceAgencyLoan plServiceAgencyLoan);

    /**
     * 修改7大板款信息
     *
     * @param plServiceAgencyLoan 7大板款信息
     * @return 结果
     */
    public int updatePlServiceAgencyLoan(PlServiceAgencyLoan plServiceAgencyLoan);
    public int insertComment(PlComment plComment);

    /**
     * 删除7大板款信息
     *
     * @param id 7大板款信息ID
     * @return 结果
     */
    public int deletePlServiceAgencyLoanById(Long id);

    /**
     * 批量删除7大板款信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePlServiceAgencyLoanByIds(String[] ids);


    List<PlServiceAgencyLoan> selectPlServiceAgencyLoanListDouble(PlServiceAgencyLoan plServiceAgencyLoan);

    public String selectProductIdList(String userId);

    public List<PlComment> selectComment(PlComment plComment);

    List<PlServiceAgencyLoan> selectApplicitionIsNotNull();

    List<PlServiceAgencyLoan> selectInstanceIdIsNotNull();
}

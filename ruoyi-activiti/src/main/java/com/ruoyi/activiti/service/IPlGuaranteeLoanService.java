package com.ruoyi.activiti.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.activiti.domain.PlGuaranteeLoan;
import com.ruoyi.activiti.domain.PlGuaranteeLoanVo;
import com.ruoyi.system.domain.SysDictData;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * 助保贷信息Service接口
 *
 * @author ruoyi
 * @date 2020-08-23
 */
public interface IPlGuaranteeLoanService
{
    /**
     * 查询助保贷信息
     *
     * @param id 助保贷信息ID
     * @return 助保贷信息
     */
    public PlGuaranteeLoanVo selectPlGuaranteeLoanById(Long id);

    /**
     * 查询助保贷信息列表
     *
     * @param plGuaranteeLoan 助保贷信息
     * @return 助保贷信息集合
     */
    public List<PlGuaranteeLoanVo> selectPlGuaranteeLoanList(PlGuaranteeLoanVo plGuaranteeLoan);
    public List<PlGuaranteeLoanVo> selectPlGuaranteeLoanListGroupBank(PlGuaranteeLoanVo plGuaranteeLoan);
    public List<PlGuaranteeLoanVo> selectPlGuaranteeLoanListGroupGuarantee(PlGuaranteeLoanVo plGuaranteeLoan);
    public List<PlGuaranteeLoan> selectPlGuaranteeLoanListGroupDate(PlGuaranteeLoanVo plGuaranteeLoan);
    public List<PlGuaranteeLoan> selectPlGuaranteeLoanListGroupDept(PlGuaranteeLoanVo plGuaranteeLoan);
    public List<PlGuaranteeLoan> selectPlGuaranteeLoanListGroupFour(PlGuaranteeLoanVo plGuaranteeLoan);
    /**
     * 新增助保贷信息
     *
     * @param plGuaranteeLoan 助保贷信息
     * @return 结果
     */
    public int insertPlGuaranteeLoan(PlGuaranteeLoan plGuaranteeLoan);

    /**
     * 修改助保贷信息
     *
     * @param plGuaranteeLoan 助保贷信息
     * @return 结果
     */
    public int updatePlGuaranteeLoan(PlGuaranteeLoan plGuaranteeLoan);
    public int updatePlGuaranteeLoanSaveLoan(PlGuaranteeLoan plGuaranteeLoan);

    /**
     * 批量删除助保贷信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePlGuaranteeLoanByIds(String ids);

    /**
     * 删除助保贷信息信息
     *
     * @param id 助保贷信息ID
     * @return 结果
     */
    public int deletePlGuaranteeLoanById(Long id);


    public PlGuaranteeLoanVo selectBizLeaveById(Long id);

    /**
     * 启动流程
     * @param entity
     * @param applyUserId
     * @return
     */
    ProcessInstance submitApply(PlGuaranteeLoanVo entity, String applyUserId, String key, Map<String, Object> variables);
    /**
     * 待审核
     * @return
     */

    List<PlGuaranteeLoanVo> findTodoTasks(PlGuaranteeLoanVo bizLeave, String loginName);

    List<PlGuaranteeLoanVo> findDoneTasks(PlGuaranteeLoanVo bizLeave, String loginName);

    List<SysDictData> selectplatesEght();

    PlGuaranteeLoan selectStatistics();
}

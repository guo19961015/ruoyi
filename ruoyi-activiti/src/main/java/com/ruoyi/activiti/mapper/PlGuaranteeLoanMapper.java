package com.ruoyi.activiti.mapper;

import java.util.List;

import com.ruoyi.activiti.domain.BizLeaveVo;
import com.ruoyi.activiti.domain.PlGuaranteeLoan;
import com.ruoyi.activiti.domain.PlGuaranteeLoanVo;
import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.system.domain.SysDictData;
import org.apache.ibatis.annotations.Param;

/**
 * 助保贷信息Mapper接口
 *
 * @author ruoyi
 * @date 2020-08-23
 */
public interface PlGuaranteeLoanMapper
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
     * 删除助保贷信息
     *
     * @param id 助保贷信息ID
     * @return 结果
     */
    public int deletePlGuaranteeLoanById(Long id);

    /**
     * 批量删除助保贷信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePlGuaranteeLoanByIds(String[] ids);

    public PlGuaranteeLoanVo selectBizLeaveById(PlGuaranteeLoan plGuaranteeLoan);

    public PlGuaranteeLoanVo selectBizLeaveByIdBankId(PlGuaranteeLoan plGuaranteeLoan);

    public PlGuaranteeLoanVo selectBizLeaveByIdGuaranteeId(PlGuaranteeLoan plGuaranteeLoan);

    List<PlGuaranteeLoanVo> selectPlGuaranteeLoanListGroupBank(PlGuaranteeLoanVo plGuaranteeLoan);
    List<PlGuaranteeLoanVo> selectPlGuaranteeLoanListGroupGuarantee(PlGuaranteeLoanVo plGuaranteeLoan);
    List<PlGuaranteeLoan> selectPlGuaranteeLoanListGroupDate(PlGuaranteeLoanVo plGuaranteeLoan);
    List<PlGuaranteeLoan> selectPlGuaranteeLoanListGroupDept(PlGuaranteeLoanVo plGuaranteeLoan);
    List<PlGuaranteeLoan> selectPlGuaranteeLoanListGroupFour(PlGuaranteeLoanVo plGuaranteeLoan);

    List<SysDictData> selectplatesEght();

    PlGuaranteeLoan selectStatistics();
}

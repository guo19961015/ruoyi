package com.ruoyi.activiti.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.PlBank;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.mapper.PlBankMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.service.IPlBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.activiti.mapper.PlGuaranteeMapper;
import com.ruoyi.activiti.domain.PlGuarantee;
import com.ruoyi.activiti.service.IPlGuaranteeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 担保公司信息Service业务层处理
 * 
 * @author xiaoguo
 * @date 2020-08-30
 */
@Service
public class PlGuaranteeServiceImpl implements IPlGuaranteeService 
{
    @Autowired
    private PlGuaranteeMapper plGuaranteeMapper;
    @Autowired
    private PlBankMapper plBankMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    /**
     * 查询担保公司信息
     * 
     * @param guaranteeId 担保公司信息ID
     * @return 担保公司信息
     */
    @Override
    public PlGuarantee selectPlGuaranteeById(Long guaranteeId)
    {
        return plGuaranteeMapper.selectPlGuaranteeById(guaranteeId);
    }

    /**
     * 查询担保公司信息列表
     * 
     * @param plGuarantee 担保公司信息
     * @return 担保公司信息
     */
    @Override
    public List<PlGuarantee> selectPlGuaranteeList(PlGuarantee plGuarantee)
    {
        String bankId = plGuarantee.getBankId();
        PlBank plBank = new PlBank();
        if (bankId != null) {
            plBank = plBankMapper.selectPlBankById(Long.valueOf(bankId));
        }

        List<PlGuarantee> plGuaranteeList = plGuaranteeMapper.selectPlGuaranteeList(plGuarantee);
        if (plGuaranteeList != null) {
            for (PlGuarantee plGuarante : plGuaranteeList) {
                String guaranteeId = plBank.getGuaranteeId();
                if (guaranteeId != null) {
                    if(String.valueOf(plGuarante.getGuaranteeId()).equals(guaranteeId)){
                        plGuarante.setFlag(true);
                        break;
                    }
                }

            }
        }
        return plGuaranteeList;
    }

    @Override
    public List<PlGuarantee> selectPlGuaranteeListDouble(PlGuarantee plGuarantee)
    {
        SysUser sysUser = new SysUser();
        String userId = plGuarantee.getUserId();
        if (userId != null) {
            sysUser = sysUserMapper.selectUserById(Long.valueOf(userId));
        }

        List<PlGuarantee> plGuaranteeList = plGuaranteeMapper.selectPlGuaranteeList(plGuarantee);
        if (plGuaranteeList != null) {
            for (PlGuarantee plGuarante : plGuaranteeList) {
                String guaranteeId = sysUser.getGuaranteeId();
                if (guaranteeId != null) {
                    if(String.valueOf(plGuarante.getGuaranteeId()).equals(sysUser.getGuaranteeId())){
                        plGuarante.setFlag(true);
                        break;
                    }
                }

            }
        }
        return plGuaranteeList;
    }
    /**
     * 新增担保公司信息
     * 
     * @param plGuarantee 担保公司信息
     * @return 结果
     */
    @Override
    public int insertPlGuarantee(PlGuarantee plGuarantee)
    {
        plGuarantee.setCreateTime(DateUtils.getNowDate());
        return plGuaranteeMapper.insertPlGuarantee(plGuarantee);
    }

    /**
     * 修改担保公司信息
     * 
     * @param plGuarantee 担保公司信息
     * @return 结果
     */
    @Override
    public int updatePlGuarantee(PlGuarantee plGuarantee)
    {
        plGuarantee.setUpdateTime(DateUtils.getNowDate());
        return plGuaranteeMapper.updatePlGuarantee(plGuarantee);
    }

    /**
     * 删除担保公司信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePlGuaranteeByIds(String ids)
    {
        return plGuaranteeMapper.deletePlGuaranteeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除担保公司信息信息
     * 
     * @param guaranteeId 担保公司信息ID
     * @return 结果
     */
    @Override
    public int deletePlGuaranteeById(Long guaranteeId)
    {
        return plGuaranteeMapper.deletePlGuaranteeById(guaranteeId);
    }
}

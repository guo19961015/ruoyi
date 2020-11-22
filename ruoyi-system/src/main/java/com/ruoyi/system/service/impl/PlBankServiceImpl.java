package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.PlBank;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.mapper.PlBankMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.service.IPlBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.core.text.Convert;

/**
 * 银行信息Service业务层处理
 *
 * @author ruoyi
 * @date 2020-08-29
 */
@Service
public class PlBankServiceImpl implements IPlBankService
{
    @Autowired
    private PlBankMapper plBankMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    /**
     * 查询银行信息
     *
     * @param id 银行信息ID
     * @return 银行信息
     */
    @Override
    public PlBank selectPlBankById(Long id)
    {
        return plBankMapper.selectPlBankById(id);
    }

    /**
     * 查询银行信息列表
     *
     * @param plBank 银行信息
     * @return 银行信息
     */
    @Override
    public List<PlBank> selectPlBankList(PlBank plBank)
    {
        SysUser sysUser = new SysUser();
        String userId = plBank.getUserId();
        if (userId != null) {
            sysUser = sysUserMapper.selectUserById(Long.valueOf(userId));
        }
        List<PlBank> plBanks = plBankMapper.selectPlBankList(plBank);

        Long id = plBank.getId();
        if (id != null && id != 0) {
            PlBank plBank1 = plBankMapper.selectPlBankById(plBank.getId());
            if (plBanks != null) {
                for (PlBank bank : plBanks) {
                    if(plBank1 != null){
                        if(plBank1.getId().equals(bank.getId())){
                            bank.setFlag(true);
                            break;
                        }
                    }
                }
            }

        }else {

            if (plBanks != null) {
                for (PlBank bank : plBanks) {
                    String bankId = sysUser.getBankId();
                    if (bankId != null) {
                        if(String.valueOf(bank.getId()).equals(bankId)){
                            bank.setFlag(true);
                            break;
                        }
                    }
                }
            }
        }

        return plBanks;
    }
    /**
     * 编辑页面查询银行信息列表并且根据用户选择flage赋值
     *
     * @param bankId 用户所选银行信息
     * @return 银行信息
     */
    @Override
    public List<PlBank> selectPlBankListEdit(String bankId)
    {
        List<PlBank> plBanks = plBankMapper.selectPlBankList(new PlBank());
        if (bankId != null) {
            if (plBanks != null) {
                for (PlBank bank : plBanks) {
                        if(bankId.equals(String.valueOf(bank.getId()))){
                            bank.setFlag(true);
                            break;
                        }
                }
            }

        }

        return plBanks;
    }

    /**
     * 新增银行信息
     *
     * @param plBank 银行信息
     * @return 结果
     */
    @Override
    public int insertPlBank(PlBank plBank)
    {
        plBank.setCreateTime(DateUtils.getNowDate());
        return plBankMapper.insertPlBank(plBank);
    }

    /**
     * 修改银行信息
     *
     * @param plBank 银行信息
     * @return 结果
     */
    @Override
    public int updatePlBank(PlBank plBank)
    {
        plBank.setUpdateTime(DateUtils.getNowDate());
        return plBankMapper.updatePlBank(plBank);
    }

    /**
     * 删除银行信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePlBankByIds(String ids)
    {
        return plBankMapper.deletePlBankByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除银行信息信息
     *
     * @param id 银行信息ID
     * @return 结果
     */
    @Override
    public int deletePlBankById(Long id)
    {
        return plBankMapper.deletePlBankById(id);
    }

    @Override
    public int changeStatus(PlBank plBank)
    {
        return plBankMapper.updatePlBank(plBank);
    }
}

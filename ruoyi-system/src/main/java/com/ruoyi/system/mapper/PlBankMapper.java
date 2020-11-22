package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.PlBank;

import java.util.List;

/**
 * 银行信息Mapper接口
 *
 * @author ruoyi
 * @date 2020-08-29
 */
public interface PlBankMapper
{
    /**
     * 查询银行信息
     *
     * @param id 银行信息ID
     * @return 银行信息
     */
    public PlBank selectPlBankById(Long id);

    /**
     * 查询银行信息列表
     *
     * @param plBank 银行信息
     * @return 银行信息集合
     */
    public List<PlBank> selectPlBankList(PlBank plBank);

    /**
     * 新增银行信息
     *
     * @param plBank 银行信息
     * @return 结果
     */
    public int insertPlBank(PlBank plBank);

    /**
     * 修改银行信息
     *
     * @param plBank 银行信息
     * @return 结果
     */
    public int updatePlBank(PlBank plBank);

    /**
     * 删除银行信息
     *
     * @param id 银行信息ID
     * @return 结果
     */
    public int deletePlBankById(Long id);

    /**
     * 批量删除银行信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePlBankByIds(String[] ids);


}

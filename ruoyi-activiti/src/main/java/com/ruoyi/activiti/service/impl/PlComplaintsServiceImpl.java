package com.ruoyi.activiti.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.PlComplaints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.activiti.mapper.PlComplaintsMapper;
import com.ruoyi.activiti.service.IPlComplaintsService;
import com.ruoyi.common.core.text.Convert;

/**
 * 投诉意见Service业务层处理
 *
 * @author xiaoguo
 * @date 2020-11-22
 */
@Service
public class PlComplaintsServiceImpl implements IPlComplaintsService
{
    @Autowired
    private PlComplaintsMapper plComplaintsMapper;

    /**
     * 查询投诉意见
     *
     * @param id 投诉意见ID
     * @return 投诉意见
     */
    @Override
    public PlComplaints selectPlComplaintsById(Long id)
    {
        return plComplaintsMapper.selectPlComplaintsById(id);
    }

    /**
     * 查询投诉意见列表
     *
     * @param plComplaints 投诉意见
     * @return 投诉意见
     */
    @Override
    public List<PlComplaints> selectPlComplaintsList(PlComplaints plComplaints)
    {
        return plComplaintsMapper.selectPlComplaintsList(plComplaints);
    }

    /**
     * 新增投诉意见
     *
     * @param plComplaints 投诉意见
     * @return 结果
     */
    @Override
    public int insertPlComplaints(PlComplaints plComplaints)
    {
        plComplaints.setCreateBy(ShiroUtils.getLoginName());
        plComplaints.setCreateTime(DateUtils.getNowDate());
        return plComplaintsMapper.insertPlComplaints(plComplaints);
    }

    /**
     * 修改投诉意见
     *
     * @param plComplaints 投诉意见
     * @return 结果
     */
    @Override
    public int updatePlComplaints(PlComplaints plComplaints)
    {
        return plComplaintsMapper.updatePlComplaints(plComplaints);
    }

    /**
     * 删除投诉意见对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePlComplaintsByIds(String ids)
    {
        return plComplaintsMapper.deletePlComplaintsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除投诉意见信息
     *
     * @param id 投诉意见ID
     * @return 结果
     */
    @Override
    public int deletePlComplaintsById(Long id)
    {
        return plComplaintsMapper.deletePlComplaintsById(id);
    }
}

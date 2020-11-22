package com.ruoyi.activiti.service;

import java.util.List;
import com.ruoyi.system.domain.PlComplaints;

/**
 * 投诉意见Service接口
 *
 * @author xiaoguo
 * @date 2020-11-22
 */
public interface IPlComplaintsService
{
    /**
     * 查询投诉意见
     *
     * @param id 投诉意见ID
     * @return 投诉意见
     */
    public PlComplaints selectPlComplaintsById(Long id);

    /**
     * 查询投诉意见列表
     *
     * @param plComplaints 投诉意见
     * @return 投诉意见集合
     */
    public List<PlComplaints> selectPlComplaintsList(PlComplaints plComplaints);

    /**
     * 新增投诉意见
     *
     * @param plComplaints 投诉意见
     * @return 结果
     */
    public int insertPlComplaints(PlComplaints plComplaints);

    /**
     * 修改投诉意见
     *
     * @param plComplaints 投诉意见
     * @return 结果
     */
    public int updatePlComplaints(PlComplaints plComplaints);

    /**
     * 批量删除投诉意见
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePlComplaintsByIds(String ids);

    /**
     * 删除投诉意见信息
     *
     * @param id 投诉意见ID
     * @return 结果
     */
    public int deletePlComplaintsById(Long id);
}

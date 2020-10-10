package com.ruoyi.activiti.service;

import java.util.List;
import com.ruoyi.activiti.domain.PlServiceAgencyItem;

/**
 * 待办事项Service接口
 * 
 * @author xiaoguo
 * @date 2020-09-21
 */
public interface IPlServiceAgencyItemService
{
    /**
     * 查询待办事项
     * 
     * @param id 待办事项ID
     * @return 待办事项
     */
    public PlServiceAgencyItem selectPlServiceAgencyItemById(Long id);

    /**
     * 查询待办事项列表
     * 
     * @param plServiceAgencyItem 待办事项
     * @return 待办事项集合
     */
    public List<PlServiceAgencyItem> selectPlServiceAgencyItemList(PlServiceAgencyItem plServiceAgencyItem);

    /**
     * 新增待办事项
     * 
     * @param plServiceAgencyItem 待办事项
     * @return 结果
     */
    public int insertPlServiceAgencyItem(PlServiceAgencyItem plServiceAgencyItem);

    /**
     * 修改待办事项
     * 
     * @param plServiceAgencyItem 待办事项
     * @return 结果
     */
    public int updatePlServiceAgencyItem(PlServiceAgencyItem plServiceAgencyItem);

    /**
     * 批量删除待办事项
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePlServiceAgencyItemByIds(String ids);

    /**
     * 删除待办事项信息
     * 
     * @param id 待办事项ID
     * @return 结果
     */
    public int deletePlServiceAgencyItemById(Long id);

    public int insertTodoItem(String instanceId, String title, String legal, String module);

}

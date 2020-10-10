package com.ruoyi.activiti.service;

import com.ruoyi.activiti.domain.PlTodoItem;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 待办事项Service接口
 *
 * @author ruoyi
 * @date 2020-08-24
 */
public interface IPlTodoItemService
{
    /**
     * 查询待办事项
     *
     * @param id 待办事项ID
     * @return 待办事项
     */
    public PlTodoItem selectPlTodoItemById(Long id);

    /**
     * 查询待办事项列表
     *
     * @param plTodoItem 待办事项
     * @return 待办事项集合
     */
    public List<PlTodoItem> selectPlTodoItemList(PlTodoItem plTodoItem);

    /**
     * 新增待办事项
     *
     * @param plTodoItem 待办事项
     * @return 结果
     */
    public int insertPlTodoItem(PlTodoItem plTodoItem);

    /**
     * 修改待办事项
     *
     * @param plTodoItem 待办事项
     * @return 结果
     */
    public int updatePlTodoItem(PlTodoItem plTodoItem);

    /**
     * 批量删除待办事项
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePlTodoItemByIds(String ids);

    /**
     * 删除待办事项信息
     *
     * @param id 待办事项ID
     * @return 结果
     */
    public int deletePlTodoItemById(Long id);

    int insertTodoItem(Date realityStartTime, Date realityEndTime, BigDecimal actualLoanAmount, String instanceId, String title, String legal, String module);
    int insertTodoItem2(String instanceId, String title, String legal, String module);

    int insertTodoItem3(String processInstanceId, String title, String legal, String module);
}

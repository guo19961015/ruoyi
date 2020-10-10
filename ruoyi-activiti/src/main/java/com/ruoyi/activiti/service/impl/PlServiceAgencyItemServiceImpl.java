package com.ruoyi.activiti.service.impl;

import java.util.List;

import com.ruoyi.activiti.domain.BizTodoItem;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.mapper.SysUserMapper;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.activiti.mapper.PlServiceAgencyItemMapper;
import com.ruoyi.activiti.domain.PlServiceAgencyItem;
import com.ruoyi.activiti.service.IPlServiceAgencyItemService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.util.CollectionUtils;

/**
 * 待办事项Service业务层处理
 * 
 * @author xiaoguo
 * @date 2020-09-21
 */
@Service
public class PlServiceAgencyItemServiceImpl implements IPlServiceAgencyItemService 
{
    @Autowired
    private PlServiceAgencyItemMapper plServiceAgencyItemMapper;
    @Autowired
    private TaskService taskService;
    @Autowired
    private SysUserMapper userMapper;
    /**
     * 查询待办事项
     * 
     * @param id 待办事项ID
     * @return 待办事项
     */
    @Override
    public PlServiceAgencyItem selectPlServiceAgencyItemById(Long id)
    {
        return plServiceAgencyItemMapper.selectPlServiceAgencyItemById(id);
    }

    /**
     * 查询待办事项列表
     * 
     * @param plServiceAgencyItem 待办事项
     * @return 待办事项
     */
    @Override
    public List<PlServiceAgencyItem> selectPlServiceAgencyItemList(PlServiceAgencyItem plServiceAgencyItem)
    {
        return plServiceAgencyItemMapper.selectPlServiceAgencyItemList(plServiceAgencyItem);
    }

    /**
     * 新增待办事项
     * 
     * @param plServiceAgencyItem 待办事项
     * @return 结果
     */
    @Override
    public int insertPlServiceAgencyItem(PlServiceAgencyItem plServiceAgencyItem)
    {
        return plServiceAgencyItemMapper.insertPlServiceAgencyItem(plServiceAgencyItem);
    }

    /**
     * 修改待办事项
     * 
     * @param plServiceAgencyItem 待办事项
     * @return 结果
     */
    @Override
    public int updatePlServiceAgencyItem(PlServiceAgencyItem plServiceAgencyItem)
    {
        return plServiceAgencyItemMapper.updatePlServiceAgencyItem(plServiceAgencyItem);
    }

    /**
     * 删除待办事项对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePlServiceAgencyItemByIds(String ids)
    {
        return plServiceAgencyItemMapper.deletePlServiceAgencyItemByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除待办事项信息
     * 
     * @param id 待办事项ID
     * @return 结果
     */
    @Override
    public int deletePlServiceAgencyItemById(Long id)
    {
        return plServiceAgencyItemMapper.deletePlServiceAgencyItemById(id);
    }

    @Override
    public int insertTodoItem(String instanceId, String itemName, String itemContent, String module) {
        PlServiceAgencyItem todoItem = new PlServiceAgencyItem();
        todoItem.setItemName(itemName);
        todoItem.setItemContent(itemContent);
        todoItem.setIsView("0");
        todoItem.setIsHandle("0");
        todoItem.setModule(module);
        todoItem.setTodoTime(DateUtils.getNowDate());
        List<Task> taskList = taskService.createTaskQuery().processInstanceId(instanceId).active().list();
        int counter = 0;
        for (Task task: taskList) {

            // todoitem 去重
            PlServiceAgencyItem bizTodoItem = plServiceAgencyItemMapper.selectTodoItemByTaskId(task.getId());
            if (bizTodoItem != null) continue;

            PlServiceAgencyItem newItem = new PlServiceAgencyItem();
            BeanUtils.copyProperties(todoItem, newItem);
            newItem.setInstanceId(instanceId);
            newItem.setTaskId(task.getId());
            newItem.setTaskName("task" + task.getTaskDefinitionKey().substring(0, 1).toUpperCase() + task.getTaskDefinitionKey().substring(1));
            newItem.setNodeName(task.getName());
            String assignee = task.getAssignee();
            if (StringUtils.isNotBlank(assignee)) {
                newItem.setTodoUserId(assignee);
                SysUser user = userMapper.selectUserByLoginName(assignee);
                newItem.setTodoUserName(user.getUserName());
                plServiceAgencyItemMapper.insertPlServiceAgencyItem(newItem);
                counter++;
            } else {
                // 查询候选用户组
                List<String> todoUserIdList = plServiceAgencyItemMapper.selectTodoUserListByTaskId(task.getId());
                if (!CollectionUtils.isEmpty(todoUserIdList)) {
                    for (String todoUserId: todoUserIdList) {
                        SysUser todoUser = userMapper.selectUserByLoginName(todoUserId);
                        newItem.setTodoUserId(todoUser.getLoginName());
                        newItem.setTodoUserName(todoUser.getUserName());
                        plServiceAgencyItemMapper.insertPlServiceAgencyItem(newItem);
                        counter++;
                    }
                } else {
                    // 查询候选用户
                    String todoUserId = plServiceAgencyItemMapper.selectTodoUserByTaskId(task.getId());
                    SysUser todoUser = userMapper.selectUserByLoginName(todoUserId);
                    newItem.setTodoUserId(todoUser.getLoginName());
                    newItem.setTodoUserName(todoUser.getUserName());
                    plServiceAgencyItemMapper.insertPlServiceAgencyItem(newItem);
                    counter++;
                }
            }
        }
        return counter;
    }
}

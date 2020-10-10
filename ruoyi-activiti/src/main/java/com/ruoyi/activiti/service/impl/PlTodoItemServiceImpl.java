package com.ruoyi.activiti.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ruoyi.activiti.domain.BizTodoItem;
import com.ruoyi.activiti.domain.PlServiceAgencyItem;
import com.ruoyi.activiti.mapper.PlServiceAgencyItemMapper;
import com.ruoyi.activiti.service.IPlServiceAgencyItemService;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.mapper.SysUserMapper;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.activiti.mapper.PlTodoItemMapper;
import com.ruoyi.activiti.domain.PlTodoItem;
import com.ruoyi.activiti.service.IPlTodoItemService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.util.CollectionUtils;

/**
 * 待办事项Service业务层处理
 *
 * @author ruoyi
 * @date 2020-08-24
 */
@Service
public class PlTodoItemServiceImpl implements IPlTodoItemService
{
    @Autowired
    private PlTodoItemMapper plTodoItemMapper;
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
    public PlTodoItem selectPlTodoItemById(Long id)
    {
        return plTodoItemMapper.selectPlTodoItemById(id);
    }

    /**
     * 查询待办事项列表
     *
     * @param plTodoItem 待办事项
     * @return 待办事项
     */
    @Override
    public List<PlTodoItem> selectPlTodoItemList(PlTodoItem plTodoItem)
    {
        return plTodoItemMapper.selectPlTodoItemList(plTodoItem);
    }

    /**
     * 新增待办事项
     *
     * @param plTodoItem 待办事项
     * @return 结果
     */
    @Override
    public int insertPlTodoItem(PlTodoItem plTodoItem)
    {
        return plTodoItemMapper.insertPlTodoItem(plTodoItem);
    }

    /**
     * 修改待办事项
     *
     * @param plTodoItem 待办事项
     * @return 结果
     */
    @Override
    public int updatePlTodoItem(PlTodoItem plTodoItem)
    {
        return plTodoItemMapper.updatePlTodoItem(plTodoItem);
    }

    /**
     * 删除待办事项对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePlTodoItemByIds(String ids)
    {
        return plTodoItemMapper.deletePlTodoItemByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除待办事项信息
     *
     * @param id 待办事项ID
     * @return 结果
     */
    @Override
    public int deletePlTodoItemById(Long id)
    {
        return plTodoItemMapper.deletePlTodoItemById(id);
    }
    @Override
    public int insertTodoItem(Date realityStartTime, Date realityEndTime, BigDecimal actualLoanAmount, String instanceId, String title, String legal, String module) {
        PlTodoItem todoItem = new PlTodoItem();
        todoItem.setTitle(title);
        todoItem.setLegal(legal);
        todoItem.setRealityStartTime(realityStartTime);
        todoItem.setRealityEndTime(realityEndTime);
        todoItem.setActualLoanAmount(actualLoanAmount);
        todoItem.setIsView("0");
        todoItem.setIsHandle("0");
        todoItem.setModule(module);
        todoItem.setTodoTime(DateUtils.getNowDate());
        List<Task> taskList = taskService.createTaskQuery().processInstanceId(instanceId).active().list();
        int counter = 0;
        for (Task task: taskList) {

            // todoitem 去重
            PlTodoItem bizTodoItem = plTodoItemMapper.selectTodoItemByTaskId(task.getId());
            if (bizTodoItem != null) continue;

            PlTodoItem newItem = new PlTodoItem();
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
                plTodoItemMapper.insertPlTodoItem(newItem);
                counter++;
            } else {
                // 查询候选用户组
                List<String> todoUserIdList = plTodoItemMapper.selectTodoUserListByTaskId(task.getId());
                if (!CollectionUtils.isEmpty(todoUserIdList)) {
                    for (String todoUserId: todoUserIdList) {
                        SysUser todoUser = userMapper.selectUserByLoginName(todoUserId);
                        newItem.setTodoUserId(todoUser.getLoginName());
                        newItem.setTodoUserName(todoUser.getUserName());
                        plTodoItemMapper.insertPlTodoItem(newItem);
                        counter++;
                    }
                } else {
                    // 查询候选用户
                    String todoUserId = plTodoItemMapper.selectTodoUserByTaskId(task.getId());
                    SysUser todoUser = userMapper.selectUserByLoginName(todoUserId);
                    newItem.setTodoUserId(todoUser.getLoginName());
                    newItem.setTodoUserName(todoUser.getUserName());
                    plTodoItemMapper.insertPlTodoItem(newItem);
                    counter++;
                }
            }
        }
        return counter;
    }
    @Override
    public int insertTodoItem2( String instanceId, String title, String legal, String module) {
        PlTodoItem todoItem = new PlTodoItem();
        todoItem.setTitle(title);
        todoItem.setLegal(legal);
        todoItem.setIsView("0");
        todoItem.setIsHandle("0");
        todoItem.setModule(module);
        todoItem.setTodoTime(DateUtils.getNowDate());
        List<Task> taskList = taskService.createTaskQuery().processInstanceId(instanceId).active().list();
        int counter = 0;
        for (Task task: taskList) {

            // todoitem 去重
            PlTodoItem bizTodoItem = plTodoItemMapper.selectTodoItemByTaskId(task.getId());
            if (bizTodoItem != null) continue;

            PlTodoItem newItem = new PlTodoItem();
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
                plTodoItemMapper.insertPlTodoItem(newItem);
                counter++;
            } else {
                // 查询候选用户组
                List<String> todoUserIdList = plTodoItemMapper.selectTodoUserListByTaskId(task.getId());
                if (!CollectionUtils.isEmpty(todoUserIdList)) {
                    for (String todoUserId: todoUserIdList) {
                        SysUser todoUser = userMapper.selectUserByLoginName(todoUserId);
                        newItem.setTodoUserId(todoUser.getLoginName());
                        newItem.setTodoUserName(todoUser.getUserName());
                        plTodoItemMapper.insertPlTodoItem(newItem);
                        counter++;
                    }
                } else {
                    // 查询候选用户
                    String todoUserId = plTodoItemMapper.selectTodoUserByTaskId(task.getId());
                    SysUser todoUser = userMapper.selectUserByLoginName(todoUserId);
                    newItem.setTodoUserId(todoUser.getLoginName());
                    newItem.setTodoUserName(todoUser.getUserName());
                    plTodoItemMapper.insertPlTodoItem(newItem);
                    counter++;
                }
            }
        }
        return counter;
    }
    @Override
    public int insertTodoItem3( String instanceId, String title, String legal, String module) {
        PlTodoItem todoItem = new PlTodoItem();
        todoItem.setTitle(title);
        todoItem.setLegal(legal);
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

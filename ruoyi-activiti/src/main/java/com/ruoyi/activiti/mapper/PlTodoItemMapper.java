package com.ruoyi.activiti.mapper;

import java.util.List;

import com.ruoyi.activiti.domain.BizTodoItem;
import com.ruoyi.activiti.domain.PlTodoItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 待办事项Mapper接口
 *
 * @author ruoyi
 * @date 2020-08-24
 */
public interface PlTodoItemMapper
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
     * 删除待办事项
     *
     * @param id 待办事项ID
     * @return 结果
     */
    public int deletePlTodoItemById(Long id);

    /**
     * 批量删除待办事项
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePlTodoItemByIds(String[] ids);

    @Select("SELECT * FROM PL_TODO_ITEM WHERE TASK_ID = #{taskId}")
    PlTodoItem selectTodoItemByTaskId(@Param(value = "taskId") String taskId);


    @Select("SELECT USER_ID_ FROM ACT_ID_MEMBERSHIP WHERE GROUP_ID_ = (SELECT GROUP_ID_ FROM ACT_RU_IDENTITYLINK WHERE TASK_ID_ = #{taskId})")
    List<String> selectTodoUserListByTaskId(@Param(value = "taskId") String taskId);

    @Select("SELECT USER_ID_ FROM ACT_ID_MEMBERSHIP WHERE USER_ID_ = (SELECT USER_ID_ FROM ACT_RU_IDENTITYLINK WHERE TASK_ID_ = #{taskId})")
    String selectTodoUserByTaskId(String id);
}

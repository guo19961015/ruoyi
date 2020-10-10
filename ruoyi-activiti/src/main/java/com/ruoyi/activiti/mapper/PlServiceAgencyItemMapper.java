package com.ruoyi.activiti.mapper;

import java.util.List;

import com.ruoyi.activiti.domain.BizTodoItem;
import com.ruoyi.activiti.domain.PlServiceAgencyItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 待办事项Mapper接口
 * 
 * @author xiaoguo
 * @date 2020-09-21
 */
public interface PlServiceAgencyItemMapper 
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
     * 删除待办事项
     * 
     * @param id 待办事项ID
     * @return 结果
     */
    public int deletePlServiceAgencyItemById(Long id);

    /**
     * 批量删除待办事项
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePlServiceAgencyItemByIds(String[] ids);

    @Select("SELECT * FROM pl_service_agency_item WHERE TASK_ID = #{taskId}")
    PlServiceAgencyItem selectTodoItemByTaskId(@Param(value = "taskId") String taskId);

    @Select("SELECT USER_ID_ FROM ACT_ID_MEMBERSHIP WHERE GROUP_ID_ in (SELECT GROUP_ID_ FROM ACT_RU_IDENTITYLINK WHERE TASK_ID_ = #{taskId})")
    List<String> selectTodoUserListByTaskId(@Param(value = "taskId") String taskId);

    @Select("SELECT * FROM pl_service_agency_item WHERE TASK_ID = #{taskId} AND TODO_USER_ID = #{todoUserId}")
    BizTodoItem selectTodoItemByCondition(@Param(value = "taskId") String taskId, @Param(value = "todoUserId") String todoUserId);

    @Select("SELECT USER_ID_ FROM ACT_ID_MEMBERSHIP WHERE USER_ID_ = (SELECT USER_ID_ FROM ACT_RU_IDENTITYLINK WHERE TASK_ID_ = #{taskId})")
    String selectTodoUserByTaskId(String id);
}

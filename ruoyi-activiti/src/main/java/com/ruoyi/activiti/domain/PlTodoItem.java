package com.ruoyi.activiti.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 待办事项对象 pl_todo_item
 * 
 * @author ruoyi
 * @date 2020-08-24
 */
public class PlTodoItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 ID */
    private Long id;

    /** 企业名称 */
    @Excel(name = "企业名称")
    private String title;

    /** 企业法人 */
    @Excel(name = "企业法人")
    private String legal;

    /** 模块名称 (必须以 uri 一致) */
    @Excel(name = "模块名称 (必须以 uri 一致)")
    private String module;

    /** 任务 ID */
    @Excel(name = "任务 ID")
    private String taskId;

    /** 流程实例 ID */
    @Excel(name = "流程实例 ID")
    private String instanceId;

    /** 任务名称 (必须以表单页面名称一致) */
    @Excel(name = "任务名称 (必须以表单页面名称一致)")
    private String taskName;

    /** 节点名称 */
    @Excel(name = "节点名称")
    private String nodeName;

    /** 是否查看 default 0 (0 否 1 是) */
    @Excel(name = "是否查看 default 0 (0 否 1 是)")
    private String isView;

    /** 是否处理 default 0 (0 否 1 是) */
    @Excel(name = "是否处理 default 0 (0 否 1 是)")
    private String isHandle;

    /** 待办人 ID */
    @Excel(name = "待办人 ID")
    private String todoUserId;

    /** 待办人名称 */
    @Excel(name = "待办人名称")
    private String todoUserName;

    /** 处理人 ID */
    @Excel(name = "处理人 ID")
    private String handleUserId;

    /** 处理人名称 */
    @Excel(name = "处理人名称")
    private String handleUserName;

    /** 通知时间 */
    @Excel(name = "通知时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date todoTime;

    /** 处理时间 */
    @Excel(name = "处理时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date handleTime;
    private String bankId;
    private Date realityStartTime;
    private Date realityEndTime;
    private BigDecimal actualLoanAmount;
    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setLegal(String legal) 
    {
        this.legal = legal;
    }

    public String getLegal() 
    {
        return legal;
    }
    public void setModule(String module) 
    {
        this.module = module;
    }

    public String getModule() 
    {
        return module;
    }
    public void setTaskId(String taskId) 
    {
        this.taskId = taskId;
    }

    public String getTaskId() 
    {
        return taskId;
    }
    public void setInstanceId(String instanceId) 
    {
        this.instanceId = instanceId;
    }

    public String getInstanceId() 
    {
        return instanceId;
    }
    public void setTaskName(String taskName) 
    {
        this.taskName = taskName;
    }

    public String getTaskName() 
    {
        return taskName;
    }
    public void setNodeName(String nodeName) 
    {
        this.nodeName = nodeName;
    }

    public String getNodeName() 
    {
        return nodeName;
    }
    public void setIsView(String isView) 
    {
        this.isView = isView;
    }

    public String getIsView() 
    {
        return isView;
    }
    public void setIsHandle(String isHandle) 
    {
        this.isHandle = isHandle;
    }

    public String getIsHandle() 
    {
        return isHandle;
    }
    public void setTodoUserId(String todoUserId) 
    {
        this.todoUserId = todoUserId;
    }

    public String getTodoUserId() 
    {
        return todoUserId;
    }
    public void setTodoUserName(String todoUserName) 
    {
        this.todoUserName = todoUserName;
    }

    public String getTodoUserName() 
    {
        return todoUserName;
    }
    public void setHandleUserId(String handleUserId) 
    {
        this.handleUserId = handleUserId;
    }

    public String getHandleUserId() 
    {
        return handleUserId;
    }
    public void setHandleUserName(String handleUserName) 
    {
        this.handleUserName = handleUserName;
    }

    public String getHandleUserName() 
    {
        return handleUserName;
    }
    public void setTodoTime(Date todoTime) 
    {
        this.todoTime = todoTime;
    }

    public Date getTodoTime() 
    {
        return todoTime;
    }
    public void setHandleTime(Date handleTime) 
    {
        this.handleTime = handleTime;
    }

    public Date getHandleTime() 
    {
        return handleTime;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public Date getRealityStartTime() {
        return realityStartTime;
    }

    public void setRealityStartTime(Date realityStartTime) {
        this.realityStartTime = realityStartTime;
    }

    public Date getRealityEndTime() {
        return realityEndTime;
    }

    public void setRealityEndTime(Date realityEndTime) {
        this.realityEndTime = realityEndTime;
    }

    public BigDecimal getActualLoanAmount() {
        return actualLoanAmount;
    }

    public void setActualLoanAmount(BigDecimal actualLoanAmount) {
        this.actualLoanAmount = actualLoanAmount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("legal", getLegal())
            .append("module", getModule())
            .append("taskId", getTaskId())
            .append("instanceId", getInstanceId())
            .append("taskName", getTaskName())
            .append("nodeName", getNodeName())
            .append("isView", getIsView())
            .append("isHandle", getIsHandle())
            .append("todoUserId", getTodoUserId())
            .append("todoUserName", getTodoUserName())
            .append("handleUserId", getHandleUserId())
            .append("handleUserName", getHandleUserName())
            .append("todoTime", getTodoTime())
            .append("handleTime", getHandleTime())
            .append("bankId", getBankId())
            .append("realityStartTime", getRealityStartTime())
            .append("realityEndTime", getRealityEndTime())
            .append("actualLoanAmount", getActualLoanAmount())
            .toString();
    }
}

package com.ruoyi.activiti.domain;

import com.ruoyi.common.annotation.Log;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 服务角色表对象 pl_loan_role
 * 
 * @author xiaoguo
 * @date 2020-09-22
 */
public class PlLoanRole extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 角色id */
    @Excel(name = "角色id")
    private Long roleId;

    /** 服务id */
    @Excel(name = "服务id")
    private Long loanId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("roleId", getRoleId())
            .append("loanId", getLoanId())
            .toString();
    }
}

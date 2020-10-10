package com.ruoyi.activiti.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 担保公司信息对象 pl_guarantee
 * 
 * @author xiaoguo
 * @date 2020-08-30
 */
public class PlGuarantee extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 岗位ID */
    private Long guaranteeId;

    /** 岗位名称 */
    @Excel(name = "岗位名称")
    private String guaranteeName;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;
    private String bankId;
    /** 用户是否存在此岗位标识 默认不存在 */
    private boolean flag = false;

    private String userId;
    public void setGuaranteeId(Long guaranteeId) 
    {
        this.guaranteeId = guaranteeId;
    }

    public Long getGuaranteeId() 
    {
        return guaranteeId;
    }
    public void setGuaranteeName(String guaranteeName) 
    {
        this.guaranteeName = guaranteeName;
    }

    public String getGuaranteeName() 
    {
        return guaranteeName;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("guaranteeId", getGuaranteeId())
            .append("guaranteeName", getGuaranteeName())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("bankId", getBankId())
            .append("userId", getUserId())
            .toString();
    }
}

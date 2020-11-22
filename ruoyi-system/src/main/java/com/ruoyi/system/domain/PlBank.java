package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 银行信息对象 pl_bank
 *
 * @author ruoyi
 * @date 2020-08-29
 */
public class PlBank extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 银行名称 */
    @Excel(name = "银行名称")
    private String bankName;

    /** 图片路径 */
    @Excel(name = "图片路径")
    private String bankImageUrl;

    /** 贷款额度 */
    @Excel(name = "贷款额度")
    private String loanLimit;

    /** 贷款期限 */
    @Excel(name = "贷款期限")
    private String loanTerm;

    /** 担保方式 */
    @Excel(name = "担保方式")
    private String guaranteeMethod;

    /** 参考利率 */
    @Excel(name = "参考利率")
    private Double bankInterestRate;

    /** 适用客户 */
    @Excel(name = "适用客户")
    private String applicableUsers;

    /** 申请条件 */
    @Excel(name = "申请条件")
    private String applicationConditions;

    /** 申请材料 */
    @Excel(name = "申请材料")
    private String applicationMaterials;
    /** 用户是否存在此岗位标识 默认不存在 */
    private boolean flag = false;

    private String userId;

    private String guaranteeId;
    /** 帐号状态（0正常 1停用） */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String status;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setBankName(String bankName)
    {
        this.bankName = bankName;
    }

    public String getBankName()
    {
        return bankName;
    }
    public void setBankImageUrl(String bankImageUrl)
    {
        this.bankImageUrl = bankImageUrl;
    }

    public String getBankImageUrl()
    {
        return bankImageUrl;
    }
    public void setLoanLimit(String loanLimit)
    {
        this.loanLimit = loanLimit;
    }

    public String getLoanLimit()
    {
        return loanLimit;
    }
    public void setLoanTerm(String loanTerm)
    {
        this.loanTerm = loanTerm;
    }

    public String getLoanTerm()
    {
        return loanTerm;
    }
    public void setGuaranteeMethod(String guaranteeMethod)
    {
        this.guaranteeMethod = guaranteeMethod;
    }

    public String getGuaranteeMethod()
    {
        return guaranteeMethod;
    }
    public void setBankInterestRate(Double bankInterestRate)
    {
        this.bankInterestRate = bankInterestRate;
    }

    public Double getBankInterestRate()
    {
        return bankInterestRate;
    }
    public void setApplicableUsers(String applicableUsers)
    {
        this.applicableUsers = applicableUsers;
    }

    public String getApplicableUsers()
    {
        return applicableUsers;
    }
    public void setApplicationConditions(String applicationConditions)
    {
        this.applicationConditions = applicationConditions;
    }

    public String getApplicationConditions()
    {
        return applicationConditions;
    }
    public void setApplicationMaterials(String applicationMaterials)
    {
        this.applicationMaterials = applicationMaterials;
    }

    public String getApplicationMaterials()
    {
        return applicationMaterials;
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

    public String getGuaranteeId() {
        return guaranteeId;
    }

    public void setGuaranteeId(String guaranteeId) {
        this.guaranteeId = guaranteeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bankName", getBankName())
            .append("bankImageUrl", getBankImageUrl())
            .append("loanLimit", getLoanLimit())
            .append("loanTerm", getLoanTerm())
            .append("guaranteeMethod", getGuaranteeMethod())
            .append("bankInterestRate", getBankInterestRate())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("applicableUsers", getApplicableUsers())
            .append("applicationConditions", getApplicationConditions())
            .append("applicationMaterials", getApplicationMaterials())
            .append("guaranteeId", getGuaranteeId())
            .append("status", getStatus())
            .toString();
    }
}

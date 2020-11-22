package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 投诉意见对象 pl_complaints
 *
 * @author ruoyi
 * @date 2020-11-22
 */
public class PlComplaints extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 名字 */
    @Excel(name = "名字")
    private String username;

    /** 电话 */
    @Excel(name = "电话")
    private String telephone;

    /** 主题 */
    @Excel(name = "主题")
    private String theme;

    /** 投诉意见内容 */
    @Excel(name = "投诉意见内容")
    private String comment;

    /** null */
    @Excel(name = "null")
    private String serviceId;

    /** 服务信息id */
    @Excel(name = "服务信息id")
    private String loanId;

    /** 产品id */
    @Excel(name = "产品id")
    private String productId;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }
    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    public String getTelephone()
    {
        return telephone;
    }
    public void setTheme(String theme)
    {
        this.theme = theme;
    }

    public String getTheme()
    {
        return theme;
    }
    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public String getComment()
    {
        return comment;
    }
    public void setServiceId(String serviceId)
    {
        this.serviceId = serviceId;
    }

    public String getServiceId()
    {
        return serviceId;
    }
    public void setLoanId(String loanId)
    {
        this.loanId = loanId;
    }

    public String getLoanId()
    {
        return loanId;
    }
    public void setProductId(String productId)
    {
        this.productId = productId;
    }

    public String getProductId()
    {
        return productId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("username", getUsername())
                .append("telephone", getTelephone())
                .append("theme", getTheme())
                .append("comment", getComment())
                .append("serviceId", getServiceId())
                .append("loanId", getLoanId())
                .append("productId", getProductId())
                .toString();
    }
}

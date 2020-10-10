package com.ruoyi.activiti.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 产品对象 ry_product
 * 
 * @author xiaoguo
 * @date 2020-09-25
 */
public class RyProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 产品id */
    private Long id;

    /** 产品介绍 */
    @Excel(name = "产品介绍")
    private String introduce;

    /** 服务机构（用户的一种）id */
    @Excel(name = "服务机构", readConverterExp = "用=户的一种")
    private String userId;

    private String serviceProductsId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setIntroduce(String introduce) 
    {
        this.introduce = introduce;
    }

    public String getIntroduce() 
    {
        return introduce;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }

    public String getServiceProductsId() {
        return serviceProductsId;
    }

    public void setServiceProductsId(String serviceProductsId) {
        this.serviceProductsId = serviceProductsId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("introduce", getIntroduce())
            .append("userId", getUserId())
            .append("serviceProductsId", getServiceProductsId())
            .toString();
    }
}

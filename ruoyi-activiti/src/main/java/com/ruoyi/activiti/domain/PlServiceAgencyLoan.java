package com.ruoyi.activiti.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;
import java.util.List;

/**
 * 7大板款信息对象 pl_service_agency_loan
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
public class PlServiceAgencyLoan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    @Excel(name = "服务类型")
    private String plateType;

    /** 开始时间 */
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 贷款时长，单位秒 */
    @Excel(name = "贷款时长，单位秒")
    private Long totalTime;

    /** 流程实例ID */
    @Excel(name = "流程实例ID")
    private String instanceId;

    /** 申请人 */
    @Excel(name = "申请人")
    private String applyUser;

    /** 申请时间 */
    @Excel(name = "申请时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date applyTime;

    /** 实际开始时间 */
    @Excel(name = "实际开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date realityStartTime;

    /** 实际结束时间 */
    @Excel(name = "实际结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date realityEndTime;

    /** 企业名称 */
    @Excel(name = "企业名称")
    private String title;

    /** 主营业务 */
    @Excel(name = "主营业务")
    private String reason;

    /** 统一社会信用代码 */
    @Excel(name = "统一社会信用代码")
    private String creditaCode;

    /** 企业法人 */
    @Excel(name = "企业法人")
    private String legal;

    /** 电话号码 */
    @Excel(name = "电话号码")
    private String legalNumber;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contacts;

    /** 联系人电话 */
    @Excel(name = "联系人电话")
    private String contactsNumber;

    private String roleLoanId;
    private String[] roleIdList;
    private Long userId;

    private Long address;

    private Long serviceTime;

    private Long serviceProducts;

    private Long qualificationDisplay;

    private String productId;

    private String[] productIdList;

    private String application;
    private String flage;
    private String status;
    public String getPlateType() {
        return plateType;
    }

    public void setPlateType(String plateType) {
        this.plateType = plateType;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setTotalTime(Long totalTime) 
    {
        this.totalTime = totalTime;
    }

    public Long getTotalTime() 
    {
        return totalTime;
    }
    public void setInstanceId(String instanceId) 
    {
        this.instanceId = instanceId;
    }

    public String getInstanceId() 
    {
        return instanceId;
    }
    public void setApplyUser(String applyUser) 
    {
        this.applyUser = applyUser;
    }

    public String getApplyUser() 
    {
        return applyUser;
    }
    public void setApplyTime(Date applyTime) 
    {
        this.applyTime = applyTime;
    }

    public Date getApplyTime() 
    {
        return applyTime;
    }
    public void setRealityStartTime(Date realityStartTime) 
    {
        this.realityStartTime = realityStartTime;
    }

    public Date getRealityStartTime() 
    {
        return realityStartTime;
    }
    public void setRealityEndTime(Date realityEndTime) 
    {
        this.realityEndTime = realityEndTime;
    }

    public Date getRealityEndTime() 
    {
        return realityEndTime;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setReason(String reason) 
    {
        this.reason = reason;
    }

    public String getReason() 
    {
        return reason;
    }
    public void setCreditaCode(String creditaCode) 
    {
        this.creditaCode = creditaCode;
    }

    public String getCreditaCode() 
    {
        return creditaCode;
    }
    public void setLegal(String legal) 
    {
        this.legal = legal;
    }

    public String getLegal() 
    {
        return legal;
    }
    public void setLegalNumber(String legalNumber) 
    {
        this.legalNumber = legalNumber;
    }

    public String getLegalNumber() 
    {
        return legalNumber;
    }
    public void setContacts(String contacts) 
    {
        this.contacts = contacts;
    }

    public String getContacts() 
    {
        return contacts;
    }
    public void setContactsNumber(String contactsNumber) 
    {
        this.contactsNumber = contactsNumber;
    }

    public String getContactsNumber() 
    {
        return contactsNumber;
    }

    public String getRoleLoanId() {
        return roleLoanId;
    }

    public void setRoleLoanId(String roleLoanId) {
        this.roleLoanId = roleLoanId;
    }

    public String[] getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(String[] roleIdList) {
        this.roleIdList = roleIdList;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAddress() {
        return address;
    }

    public void setAddress(Long address) {
        this.address = address;
    }

    public Long getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Long serviceTime) {
        this.serviceTime = serviceTime;
    }

    public Long getServiceProducts() {
        return serviceProducts;
    }

    public void setServiceProducts(Long serviceProducts) {
        this.serviceProducts = serviceProducts;
    }

    public Long getQualificationDisplay() {
        return qualificationDisplay;
    }

    public void setQualificationDisplay(Long qualificationDisplay) {
        this.qualificationDisplay = qualificationDisplay;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String[] getProductIdList() {
        return productIdList;
    }

    public void setProductIdList(String[] productIdList) {
        this.productIdList = productIdList;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getFlage() {
        return flage;
    }

    public void setFlage(String flage) {
        this.flage = flage;
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
            .append("type", getType())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("totalTime", getTotalTime())
            .append("instanceId", getInstanceId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("applyUser", getApplyUser())
            .append("applyTime", getApplyTime())
            .append("realityStartTime", getRealityStartTime())
            .append("realityEndTime", getRealityEndTime())
            .append("title", getTitle())
            .append("reason", getReason())
            .append("creditaCode", getCreditaCode())
            .append("legal", getLegal())
            .append("legalNumber", getLegalNumber())
            .append("contacts", getContacts())
            .append("contactsNumber", getContactsNumber())
            .append("plateType", getPlateType())
            .append("roleLoanId", getRoleLoanId())
            .append("userId", getUserId())
            .append("address", getAddress())
            .append("serviceTime", getServiceTime())
            .append("serviceProducts", getServiceProducts())
            .append("qualificationDisplay", getQualificationDisplay())
            .append("productId", getProductId())
            .append("productIdList", getProductIdList())
            .append("application", getApplication())
            .append("flage", getFlage())
            .append("status", getStatus())
            .toString();
    }
}

package com.ruoyi.activiti.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * 助保贷信息对象 pl_guarantee_loan
 *
 * @author ruoyi
 * @date 2020-08-23
 */
public class PlGuaranteeLoan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 申请人姓名 */
    private String applyUserName;

    /** 贷款类型 */
    @Excel(name = "贷款类型")
    private String type;

    /** 开始时间 */
    private Date startTime;

    /** 结束时间 */
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

    /** 企业法人 */
    /** 主营业务 */
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

    /** 企业简介 */
    @Excel(name = "企业简介")
    private String synopsis;

    /** 资产总额 */
    @Excel(name = "资产总额")
    private BigDecimal totalAssets;

    /** 销售额 */
    @Excel(name = "销售额")
    private BigDecimal salesVolume;

    /** 负债总额 */
    @Excel(name = "负债总额")
    private BigDecimal totalLiabilities;

    /** 利润总额 */
    @Excel(name = "利润总额")
    private BigDecimal totalProfit;

    /** 净资产 */
    @Excel(name = "净资产")
    private BigDecimal netAssets;

    /** 纳税额 */
    @Excel(name = "纳税额")
    private BigDecimal taxAmount;

    /** 固定资产 */
    @Excel(name = "固定资产")
    private BigDecimal fixedAssets;

    /** 一年内到期债务 */
    @Excel(name = "一年内到期债务")
    private BigDecimal oneYearDebtDue;

    /** 贷款余额 */
    @Excel(name = "贷款余额")
    private BigDecimal loanBalance;

    /** 其他债务 */
    @Excel(name = "其他债务")
    private BigDecimal otherLiabilities;

    /** 关联公司情况 */
    @Excel(name = "关联公司情况")
    private String relatedCompanies;

    /** 贷款银行 */
    @Excel(name = "贷款银行")
    private String lendingBank;

    /** 短期贷款逾期状态（0，无；1，有） */
    @Excel(name = "短期贷款逾期状态", readConverterExp = "0=，无；1，有")
    private String shortLoanOverdueRecords;

    /** 中长期贷款逾期状态（0，无；1，有） */
    @Excel(name = "中长期贷款逾期状态", readConverterExp = "0=，无；1，有")
    private String longInLoanOverdueRecords;
    /** 拟贷款银行 */
    @Excel(name = "申请银行")
    private String proposedBank;
    /** 申请助保贷金额 */
    @Excel(name = "申请助保贷金额")
    private BigDecimal applicationAmount;

    /** 贷款资金用途 */
    @Excel(name = "贷款资金用途")
    private String useOfFunds;

    /** 自有资产抵押（字典） */
    @Excel(name = "自有资产抵押", readConverterExp = "字典")
    private String mortgageOfOwnAssets;
    private ArrayList<LinkedHashMap<String, String>> mortgageOfOwnAssetsJson;

    /** 第三方资产抵押 */
    @Excel(name = "第三方资产抵押")
    private String thirdPartyAssetMortgage;
    private ArrayList<LinkedHashMap<String, String>> thirdPartyAssetMortgageJson;

    /** 保证担保 */
    @Excel(name = "保证担保")
    private String guarantee;
    private ArrayList<LinkedHashMap<String, String>> guaranteeJson;
    /** 反担保抵押物详细情况 */
    @Excel(name = "反担保抵押物详细情况")
    private String detailsCollateral;
    /*信用评级报告*/
    private String creditRatingReport;
    /** 旗区所属id */
    private String deptId;
    /** 银行所属id */
    private String bankId;
    /** 银行所属id */
    private String bankName;
    /*担保公司所属id*/
    private String  guaranteeId;
    /*担保公司所属id*/
    private String  guaranteeName;
    /*实际放款金额*/
    private BigDecimal actualLoanAmount;

    // 数据统计所需的字段
    private String number;

    private String date;
    private String deptName;
    private String numOne;
    private String numTwo;
    private String numThree;
    private String numFour;
    private String numFive;
    private String numSeven;
    private String numEight;
    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
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
    public void setSynopsis(String synopsis)
    {
        this.synopsis = synopsis;
    }

    public String getSynopsis()
    {
        return synopsis;
    }

    public BigDecimal getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(BigDecimal totalAssets) {
        this.totalAssets = totalAssets;
    }

    public BigDecimal getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(BigDecimal salesVolume) {
        this.salesVolume = salesVolume;
    }

    public BigDecimal getTotalLiabilities() {
        return totalLiabilities;
    }

    public void setTotalLiabilities(BigDecimal totalLiabilities) {
        this.totalLiabilities = totalLiabilities;
    }

    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
    }

    public BigDecimal getNetAssets() {
        return netAssets;
    }

    public void setNetAssets(BigDecimal netAssets) {
        this.netAssets = netAssets;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getFixedAssets() {
        return fixedAssets;
    }

    public void setFixedAssets(BigDecimal fixedAssets) {
        this.fixedAssets = fixedAssets;
    }

    public BigDecimal getOneYearDebtDue() {
        return oneYearDebtDue;
    }

    public void setOneYearDebtDue(BigDecimal oneYearDebtDue) {
        this.oneYearDebtDue = oneYearDebtDue;
    }

    public BigDecimal getLoanBalance() {
        return loanBalance;
    }

    public void setLoanBalance(BigDecimal loanBalance) {
        this.loanBalance = loanBalance;
    }

    public BigDecimal getOtherLiabilities() {
        return otherLiabilities;
    }

    public void setOtherLiabilities(BigDecimal otherLiabilities) {
        this.otherLiabilities = otherLiabilities;
    }

    public void setRelatedCompanies(String relatedCompanies)
    {
        this.relatedCompanies = relatedCompanies;
    }

    public String getRelatedCompanies()
    {
        return relatedCompanies;
    }
    public void setLendingBank(String lendingBank)
    {
        this.lendingBank = lendingBank;
    }

    public String getLendingBank()
    {
        return lendingBank;
    }
    public void setShortLoanOverdueRecords(String shortLoanOverdueRecords)
    {
        this.shortLoanOverdueRecords = shortLoanOverdueRecords;
    }

    public String getShortLoanOverdueRecords()
    {
        return shortLoanOverdueRecords;
    }
    public void setLongInLoanOverdueRecords(String longInLoanOverdueRecords)
    {
        this.longInLoanOverdueRecords = longInLoanOverdueRecords;
    }

    public String getLongInLoanOverdueRecords()
    {
        return longInLoanOverdueRecords;
    }

    public void setUseOfFunds(String useOfFunds)
    {
        this.useOfFunds = useOfFunds;
    }

    public String getUseOfFunds()
    {
        return useOfFunds;
    }
    public void setMortgageOfOwnAssets(String mortgageOfOwnAssets)
    {
        this.mortgageOfOwnAssets = mortgageOfOwnAssets;
    }

    public String getMortgageOfOwnAssets()
    {
        return mortgageOfOwnAssets;
    }
    public void setThirdPartyAssetMortgage(String thirdPartyAssetMortgage)
    {
        this.thirdPartyAssetMortgage = thirdPartyAssetMortgage;
    }

    public BigDecimal getApplicationAmount() {
        return applicationAmount;
    }

    public void setApplicationAmount(BigDecimal applicationAmount) {
        this.applicationAmount = applicationAmount;
    }

    public String getThirdPartyAssetMortgage()
    {
        return thirdPartyAssetMortgage;
    }
    public void setGuarantee(String guarantee)
    {
        this.guarantee = guarantee;
    }

    public String getGuarantee()
    {
        return guarantee;
    }
    public void setProposedBank(String proposedBank)
    {
        this.proposedBank = proposedBank;
    }

    public String getProposedBank()
    {
        return proposedBank;
    }

    public String getCreditRatingReport() {
        return creditRatingReport;
    }

    public void setCreditRatingReport(String creditRatingReport) {
        this.creditRatingReport = creditRatingReport;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getGuaranteeId() {
        return guaranteeId;
    }

    public void setGuaranteeId(String guaranteeId) {
        this.guaranteeId = guaranteeId;
    }

    public String getGuaranteeName() {
        return guaranteeName;
    }

    public void setGuaranteeName(String guaranteeName) {
        this.guaranteeName = guaranteeName;
    }

    public String getDetailsCollateral() {
        return detailsCollateral;
    }

    public void setDetailsCollateral(String detailsCollateral) {
        this.detailsCollateral = detailsCollateral;
    }

    public ArrayList<LinkedHashMap<String, String>> getMortgageOfOwnAssetsJson() {
        return mortgageOfOwnAssetsJson;
    }

    public void setMortgageOfOwnAssetsJson(ArrayList<LinkedHashMap<String, String>> mortgageOfOwnAssetsJson) {
        this.mortgageOfOwnAssetsJson = mortgageOfOwnAssetsJson;
    }

    public ArrayList<LinkedHashMap<String, String>> getThirdPartyAssetMortgageJson() {
        return thirdPartyAssetMortgageJson;
    }

    public void setThirdPartyAssetMortgageJson(ArrayList<LinkedHashMap<String, String>> thirdPartyAssetMortgageJson) {
        this.thirdPartyAssetMortgageJson = thirdPartyAssetMortgageJson;
    }

    public ArrayList<LinkedHashMap<String, String>> getGuaranteeJson() {
        return guaranteeJson;
    }

    public void setGuaranteeJson(ArrayList<LinkedHashMap<String, String>> guaranteeJson) {
        this.guaranteeJson = guaranteeJson;
    }

    public BigDecimal getActualLoanAmount() {
        return actualLoanAmount;
    }

    public void setActualLoanAmount(BigDecimal actualLoanAmount) {
        this.actualLoanAmount = actualLoanAmount;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getNumOne() {
        return numOne;
    }

    public void setNumOne(String numOne) {
        this.numOne = numOne;
    }

    public String getNumTwo() {
        return numTwo;
    }

    public void setNumTwo(String numTwo) {
        this.numTwo = numTwo;
    }

    public String getNumThree() {
        return numThree;
    }

    public void setNumThree(String numThree) {
        this.numThree = numThree;
    }

    public String getNumFour() {
        return numFour;
    }

    public void setNumFour(String numFour) {
        this.numFour = numFour;
    }

    public String getNumFive() {
        return numFive;
    }

    public void setNumFive(String numFive) {
        this.numFive = numFive;
    }

    public String getNumSeven() {
        return numSeven;
    }

    public void setNumSeven(String numSeven) {
        this.numSeven = numSeven;
    }

    public String getNumEight() {
        return numEight;
    }

    public void setNumEight(String numEight) {
        this.numEight = numEight;
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
            .append("legal", getLegal())
            .append("legalNumber", getLegalNumber())
            .append("contacts", getContacts())
            .append("contactsNumber", getContactsNumber())
            .append("synopsis", getSynopsis())
            .append("totalAssets", getTotalAssets())
            .append("salesVolume", getSalesVolume())
            .append("totalLiabilities", getTotalLiabilities())
            .append("totalProfit", getTotalProfit())
            .append("netAssets", getNetAssets())
            .append("taxAmount", getTaxAmount())
            .append("fixedAssets", getFixedAssets())
            .append("oneYearDebtDue", getOneYearDebtDue())
            .append("loanBalance", getLoanBalance())
            .append("otherLiabilities", getOtherLiabilities())
            .append("relatedCompanies", getRelatedCompanies())
            .append("lendingBank", getLendingBank())
            .append("shortLoanOverdueRecords", getShortLoanOverdueRecords())
            .append("longInLoanOverdueRecords", getLongInLoanOverdueRecords())
            .append("applicationAmount", getApplicationAmount())
            .append("useOfFunds", getUseOfFunds())
            .append("mortgageOfOwnAssets", getMortgageOfOwnAssets())
            .append("thirdPartyAssetMortgage", getThirdPartyAssetMortgage())
            .append("guarantee", getGuarantee())
            .append("proposedBank", getProposedBank())
            .append("creditRatingReport", getCreditRatingReport())
            .append("deptId", getDeptId())
            .append("bankId", getBankId())
            .append("guaranteeId", getGuaranteeId())
            .append("detailsCollateral", getDetailsCollateral())
            .append("actualLoanAmount", getActualLoanAmount())
            .append("number", getNumber())
            .append("date", getDate())
            .append("deptName", getDeptName())
            .append("numOne", getNumOne())
            .append("numTwo", getNumTwo())
            .append("numThree", getNumThree())
            .append("numFour", getNumFour())
            .toString();
    }
}

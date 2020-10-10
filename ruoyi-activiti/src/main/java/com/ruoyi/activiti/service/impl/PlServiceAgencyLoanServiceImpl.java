package com.ruoyi.activiti.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.ruoyi.activiti.domain.*;
import com.ruoyi.activiti.mapper.PlLoanRoleMapper;
import com.ruoyi.activiti.service.IProcessService;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.mapper.SysUserMapper;
import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.activiti.mapper.PlServiceAgencyLoanMapper;
import com.ruoyi.activiti.service.IPlServiceAgencyLoanService;
import com.ruoyi.common.core.text.Convert;

/**
 * 7大板款信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
@Service
public class PlServiceAgencyLoanServiceImpl implements IPlServiceAgencyLoanService 
{
    @Autowired
    private PlServiceAgencyLoanMapper plServiceAgencyLoanMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private IProcessService processService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private PlLoanRoleMapper plLoanRoleMapper;

    /**
     * 查询7大板款信息
     * 
     * @param id 7大板款信息ID
     * @return 7大板款信息
     */
    @Override
    public PlServiceAgencyLoanVo selectPlServiceAgencyLoanById(Long id)
    {

        return plServiceAgencyLoanMapper.selectPlServiceAgencyLoanById(id);
    }

    /**
     * 查询7大板款信息列表
     * 
     * @param plServiceAgencyLoan 7大板款信息
     * @return 7大板款信息
     */
    @Override
    public List<PlServiceAgencyLoan> selectPlServiceAgencyLoanList(PlServiceAgencyLoan plServiceAgencyLoan)
    {
        List<PlServiceAgencyLoan> plServiceAgencyLoans = new ArrayList<PlServiceAgencyLoan>();
        SysUser sysUser = ShiroUtils.getSysUser();
        plServiceAgencyLoan.setUserId(sysUser.getUserId());
        // 当普通企业进入时，用户状态为01，其他为00.sql为本人数据，不进入板块分别中，其他用户进入，需要按照当前用户所属角色（板块）进行查询
        if(sysUser.getUserType().equals("01")){
            plServiceAgencyLoans = plServiceAgencyLoanMapper.selectPlServiceAgencyLoanListDouble(plServiceAgencyLoan);
        }else {
            plServiceAgencyLoans = plServiceAgencyLoanMapper.selectPlServiceAgencyLoanList(plServiceAgencyLoan);
        }
        plServiceAgencyLoans.removeAll(Collections.singleton(null));
        return plServiceAgencyLoans;
    }

    /**
     * 新增7大板款信息
     * 
     * @param plServiceAgencyLoan 7大板款信息
     * @return 结果
     */
    @Override
    public int insertPlServiceAgencyLoan(PlServiceAgencyLoanVo plServiceAgencyLoan)
    {
        plServiceAgencyLoan.setCreateTime(DateUtils.getNowDate());
        plServiceAgencyLoan.setCreateBy(ShiroUtils.getLoginName());
        int i1 = plServiceAgencyLoanMapper.insertPlServiceAgencyLoan(plServiceAgencyLoan);
      /*  SysUser sysUser = ShiroUtils.getSysUser();
        List<SysRole> roles = sysUser.getRoles();
        if (roles != null) {
            for (SysRole role : roles) {
                PlLoanRole plLoanRole = new PlLoanRole();
                plLoanRole.setLoanId(plServiceAgencyLoan.getId());
                plLoanRole.setRoleId(role.getRoleId());
                plLoanRoleMapper.insertPlLoanRole(plLoanRole);
            }
        }*/

        return i1;
    }

    /**
     * 修改7大板款信息
     * 
     * @param plServiceAgencyLoan 7大板款信息
     * @return 结果
     */
    @Override
    public int updatePlServiceAgencyLoan(PlServiceAgencyLoan plServiceAgencyLoan)
    {
        plServiceAgencyLoan.setUpdateTime(DateUtils.getNowDate());
        plServiceAgencyLoan.setStatus("1");
        return plServiceAgencyLoanMapper.updatePlServiceAgencyLoan(plServiceAgencyLoan);
    }

    @Override
    public int updatePlServiceAgencyLoanAndComment(Long id, String comment,Long productId)
    {
        PlServiceAgencyLoan plServiceAgencyLoan = new PlServiceAgencyLoan();
        plServiceAgencyLoan.setId(id);
        plServiceAgencyLoan.setStatus("2");
        int i = plServiceAgencyLoanMapper.updatePlServiceAgencyLoan(plServiceAgencyLoan);
        PlComment plComment = new PlComment();
        plComment.setComment(comment);
        plComment.setLoanId(id);
        plComment.setProductId(productId);
        int i1 = plServiceAgencyLoanMapper.insertComment(plComment);

        return i;
    }

    /**
     * 删除7大板款信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePlServiceAgencyLoanByIds(String ids)
    {
        String[] strings = Convert.toStrArray(ids);
        return plServiceAgencyLoanMapper.deletePlServiceAgencyLoanByIds(strings);
    }

    /**
     * 删除7大板款信息信息
     * 
     * @param id 7大板款信息ID
     * @return 结果
     */
    @Override
    public int deletePlServiceAgencyLoanById(Long id)
    {
        return plServiceAgencyLoanMapper.deletePlServiceAgencyLoanById(id);
    }
    /**
     * 查询请假业务
     *
     * @param id 请假业务ID
     * @return 请假业务
     */
    @Override
    public PlServiceAgencyLoanVo selectBizLeaveById(Long id) {
        PlServiceAgencyLoanVo psalv = plServiceAgencyLoanMapper.selectPlServiceAgencyLoanById(id);
        SysUser sysUser = userMapper.selectUserByLoginName(psalv.getApplyUser());
        if (sysUser != null) {
            psalv.setApplyUserName(sysUser.getUserName());
        }
        return psalv;
    }
    /**
     * 启动流程
     * @param entity
     * @param applyUserId
     * @return
     */
    @Override
    public ProcessInstance submitApply(PlServiceAgencyLoanVo entity, String applyUserId, String key, Map<String, Object> variables) {
        entity.setApplyUser(applyUserId);
        entity.setApplyTime(DateUtils.getNowDate());
        entity.setUpdateBy(applyUserId);
        plServiceAgencyLoanMapper.updatePlServiceAgencyLoan(entity);
        String businessKey = entity.getId().toString(); // 实体类 ID，作为流程的业务 key

        ProcessInstance processInstance = processService.submitApplyPlate(applyUserId, businessKey, entity.getTitle(), entity.getReason(), key, variables);

        String processInstanceId = processInstance.getId();
        entity.setInstanceId(processInstanceId); // 建立双向关系
        plServiceAgencyLoanMapper.updatePlServiceAgencyLoan(entity);

        return processInstance;
    }

    /**
     * 查询已办列表
     * @param bizLeave
     * @param userId
     * @return
     */
    @Override
    public Page<PlServiceAgencyLoanVo> findDoneTasks(PlServiceAgencyLoanVo bizLeave, String userId) {
        // 手动分页
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        Page<PlServiceAgencyLoanVo> list = new Page<>();

        List<PlServiceAgencyLoanVo> results = new ArrayList<>();
        List<HistoricTaskInstance> hisList = processService.findDoneTasks(userId, bizLeave.getType());
        // 根据流程的业务ID查询实体并关联
        for (HistoricTaskInstance instance : hisList) {
            String processInstanceId = instance.getProcessInstanceId();
            // 条件过滤 1
            if (StringUtils.isNotBlank(bizLeave.getInstanceId()) && !bizLeave.getInstanceId().equals(processInstanceId)) {
                continue;
            }
            HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
            String businessKey = processInstance.getBusinessKey();
            PlServiceAgencyLoanVo leave2 = plServiceAgencyLoanMapper.selectPlServiceAgencyLoanById(new Long(businessKey));
            PlServiceAgencyLoanVo newLeave = new PlServiceAgencyLoanVo();
            if(leave2 != null){
                BeanUtils.copyProperties(leave2, newLeave);
                // 条件过滤 2
                if (StringUtils.isNotBlank(bizLeave.getType()) && !bizLeave.getType().equals(leave2.getType())) {
                    continue;
                }
                newLeave.setTaskId(instance.getId());
                newLeave.setTaskName(instance.getName());
                newLeave.setDoneTime(instance.getEndTime());
                SysUser sysUser = userMapper.selectUserByLoginName(leave2.getApplyUser());
                newLeave.setApplyUserName(sysUser.getUserName());
                results.add(newLeave);
            }

        }

        List<PlServiceAgencyLoanVo> tempList;
        if (pageNum != null && pageSize != null) {
            int maxRow = (pageNum - 1) * pageSize + pageSize > results.size() ? results.size() : (pageNum - 1) * pageSize + pageSize;
            tempList = results.subList((pageNum - 1) * pageSize, maxRow);
            list.setTotal(results.size());
            list.setPageNum(pageNum);
            list.setPageSize(pageSize);
        } else {
            tempList = results;
        }

        list.addAll(tempList);

        return list;
    }
    @Override
    public List<PlComment> selectComment (Long productId){
        PlComment plComment = new PlComment();
        plComment.setProductId(productId);
        return plServiceAgencyLoanMapper.selectComment(plComment);
    }
}

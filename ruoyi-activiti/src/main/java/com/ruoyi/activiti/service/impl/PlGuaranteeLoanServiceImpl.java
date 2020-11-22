package com.ruoyi.activiti.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.ruoyi.activiti.domain.BizLeaveVo;
import com.ruoyi.activiti.domain.PlGuaranteeLoan;
import com.ruoyi.activiti.domain.PlGuaranteeLoanVo;
import com.ruoyi.activiti.service.IProcessService;
import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.PermissionUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.mapper.SysUserMapper;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.persistence.entity.TaskEntityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.activiti.mapper.PlGuaranteeLoanMapper;

import com.ruoyi.activiti.service.IPlGuaranteeLoanService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


/**
 * 助保贷信息Service业务层处理
 *
 * @author ruoyi
 * @date 2020-08-23
 */
@Service
@Transactional
public class PlGuaranteeLoanServiceImpl implements IPlGuaranteeLoanService
{
    @Autowired
    private PlGuaranteeLoanMapper plGuaranteeLoanMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private IProcessService processService;
    @Autowired
    private HistoryService historyService;

    /**
     * 查询助保贷信息
     *
     * @param id 助保贷信息ID
     * @return 助保贷信息
     */
    @Override
    public PlGuaranteeLoanVo selectPlGuaranteeLoanById(Long id)
    {
        return plGuaranteeLoanMapper.selectPlGuaranteeLoanById(id);
    }



    /**
     * 查询助保贷信息列表
     *
     * @param plGuaranteeLoan 助保贷信息
     * @return 助保贷信息
     */
    @Override
    public List<PlGuaranteeLoanVo> selectPlGuaranteeLoanList(PlGuaranteeLoanVo plGuaranteeLoan)
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();

        // PageHelper 仅对第一个 List 分页
        Page<PlGuaranteeLoanVo> list = (Page<PlGuaranteeLoanVo>) plGuaranteeLoanMapper.selectPlGuaranteeLoanList(plGuaranteeLoan);
        Page<PlGuaranteeLoanVo> returnList = new Page<>();
        for (PlGuaranteeLoanVo leave: list) {
            SysUser sysUser = userMapper.selectUserByLoginName(leave.getCreateBy());
            if (sysUser != null) {
                leave.setCreateUserName(sysUser.getUserName());
            }
            SysUser sysUser2 = userMapper.selectUserByLoginName(leave.getApplyUser());
            if (sysUser2 != null) {
                leave.setApplyUserName(sysUser2.getUserName());
            }
            // 当前环节
            if (StringUtils.isNotBlank(leave.getInstanceId())) {
                List<Task> taskList = taskService.createTaskQuery()
                        .processInstanceId(leave.getInstanceId())
//                        .singleResult();
                        .list();    // 例如请假会签，会同时拥有多个任务
                if (!CollectionUtils.isEmpty(taskList)) {
                    TaskEntityImpl task = (TaskEntityImpl) taskList.get(0);
                    leave.setTaskId(task.getId());
                    if (task.getSuspensionState() == 2) {
                        leave.setTaskName("已挂起");
                        leave.setSuspendState("2");
                    } else {
                        leave.setTaskName(task.getName());
                        leave.setSuspendState("1");
                    }
                } else {
                    // 已办结或者已撤销
                    leave.setTaskName("已结束");
                }
            } else {
                leave.setTaskName("未启动");
            }
            returnList.add(leave);
        }
        returnList.setTotal(CollectionUtils.isEmpty(list) ? 0 : list.getTotal());
        returnList.setPageNum(pageNum);
        returnList.setPageSize(pageSize);
        return returnList;
       /* return plGuaranteeLoanMapper.selectPlGuaranteeLoanList(plGuaranteeLoan);*/
    }

    @Override
    public List<PlGuaranteeLoanVo> selectPlGuaranteeLoanListGroupBank(PlGuaranteeLoanVo plGuaranteeLoan)
    {
        List<PlGuaranteeLoanVo> plGuaranteeLoanVos = plGuaranteeLoanMapper.selectPlGuaranteeLoanListGroupBank(plGuaranteeLoan);

        return plGuaranteeLoanVos;
    }
    @Override
    public List<PlGuaranteeLoanVo> selectPlGuaranteeLoanListGroupGuarantee(PlGuaranteeLoanVo plGuaranteeLoan)
    {
        List<PlGuaranteeLoanVo> plGuaranteeLoanVos = plGuaranteeLoanMapper.selectPlGuaranteeLoanListGroupGuarantee(plGuaranteeLoan);

        return plGuaranteeLoanVos;
    }
    @Override
    public List<PlGuaranteeLoan> selectPlGuaranteeLoanListGroupDate(PlGuaranteeLoanVo plGuaranteeLoan)
    {
        List<PlGuaranteeLoan> plGuaranteeLoanVos = plGuaranteeLoanMapper.selectPlGuaranteeLoanListGroupDate(plGuaranteeLoan);

        return plGuaranteeLoanVos;
    }
    @Override
    public List<PlGuaranteeLoan> selectPlGuaranteeLoanListGroupDept(PlGuaranteeLoanVo plGuaranteeLoan)
    {
        List<PlGuaranteeLoan> plGuaranteeLoanVos = plGuaranteeLoanMapper.selectPlGuaranteeLoanListGroupDept(plGuaranteeLoan);

        return plGuaranteeLoanVos;
    }
    @Override
    public List<PlGuaranteeLoan> selectPlGuaranteeLoanListGroupFour(PlGuaranteeLoanVo plGuaranteeLoan)
    {
        List<PlGuaranteeLoan> plGuaranteeLoanVos = plGuaranteeLoanMapper.selectPlGuaranteeLoanListGroupFour(plGuaranteeLoan);

        return plGuaranteeLoanVos;
    }
    /**
     * 新增助保贷信息
     *
     * @param plGuaranteeLoan 助保贷信息
     * @return 结果
     */
    @Override
    public int insertPlGuaranteeLoan(PlGuaranteeLoan plGuaranteeLoan)
    {

        plGuaranteeLoan.setCreateBy(ShiroUtils.getLoginName());
        plGuaranteeLoan.setCreateTime(DateUtils.getNowDate());
        return plGuaranteeLoanMapper.insertPlGuaranteeLoan(plGuaranteeLoan);
    }

    /**
     * 修改助保贷信息
     *
     * @param plGuaranteeLoan 助保贷信息
     * @return 结果
     */
    @Override
    public int updatePlGuaranteeLoan(PlGuaranteeLoan plGuaranteeLoan)
    {
        plGuaranteeLoan.setUpdateTime(DateUtils.getNowDate());
        return plGuaranteeLoanMapper.updatePlGuaranteeLoan(plGuaranteeLoan);
    }
    @Override
    public int updatePlGuaranteeLoanSaveLoan(PlGuaranteeLoan plGuaranteeLoan)
    {
        return plGuaranteeLoanMapper.updatePlGuaranteeLoanSaveLoan(plGuaranteeLoan);
    }

    /**
     * 删除助保贷信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePlGuaranteeLoanByIds(String ids)
    {
        return plGuaranteeLoanMapper.deletePlGuaranteeLoanByIds(Convert.toStrArray(ids));
    }

    @Override
    public int deletePlGuaranteeLoanById(Long id) {
        return 0;
    }

    /**
     * 删除助保贷信息信息
     *
     * @param id 助保贷信息ID
     * @return 结果
     */
    /**
     * 查询助保贷业务
     *
     * @param id 请假业务ID
     * @return 请假业务
     */
    @Override
    public PlGuaranteeLoanVo selectBizLeaveById(Long id) {
        PlGuaranteeLoan plGuaranteeLoan = new PlGuaranteeLoan();
        plGuaranteeLoan.setId(id);
        PlGuaranteeLoanVo leave = plGuaranteeLoanMapper.selectBizLeaveById(plGuaranteeLoan);
        SysUser sysUser = userMapper.selectUserByLoginName(leave.getApplyUser());
        if (sysUser != null) {
            leave.setApplyUserName(sysUser.getUserName());
        }
        return leave;
    }

    @Override
    public ProcessInstance submitApply(PlGuaranteeLoanVo entity, String applyUserId, String key, Map<String, Object> variables) {
        entity.setApplyUser(applyUserId);
        entity.setApplyTime(DateUtils.getNowDate());
        entity.setUpdateBy(applyUserId);
        plGuaranteeLoanMapper.updatePlGuaranteeLoan(entity);
        String businessKey = entity.getId().toString(); // 实体类 ID，作为流程的业务 key

        ProcessInstance processInstance = processService.submitApplyPl(applyUserId, businessKey, entity.getTitle(), entity.getLegal(), key, variables);

        String processInstanceId = processInstance.getId();
        entity.setInstanceId(processInstanceId); // 建立双向关系
        plGuaranteeLoanMapper.updatePlGuaranteeLoan(entity);

        return processInstance;
    }
    /**
     * 待审核
     * @return
     */
    @DataScope(deptAlias = "pl")
    public Page<PlGuaranteeLoanVo> findTodoTasks(PlGuaranteeLoanVo leave, String userId) {
        // 手动分页
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        Page<PlGuaranteeLoanVo> list = new Page<>();

        List<PlGuaranteeLoanVo> results = new ArrayList<>();
        List<Task> tasks = processService.findTodoTasks(userId, leave.getType());
        // 根据流程的业务ID查询实体并关联
        for (Task task : tasks) {
            TaskEntityImpl taskImpl = (TaskEntityImpl) task;
            String processInstanceId = taskImpl.getProcessInstanceId();
            // 条件过滤 1
            if (StringUtils.isNotBlank(leave.getInstanceId()) && !leave.getInstanceId().equals(processInstanceId)) {
                continue;
            }
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
            String businessKey = processInstance.getBusinessKey();

            //当银行角色进入时，自动获取流程所属银行id，添加检索条件，将用户所属的流程查出
            SysUser currentUser = ShiroUtils.getSysUser();
            List<SysRole> roles = currentUser.getRoles();
            int i = 0;
            if (roles != null) {
                for (SysRole role : roles) {
                   if(String.valueOf(role.getRoleId()).equals("114") || String.valueOf(role.getRoleId()).equals("116") || String.valueOf(role.getRoleId()).equals("117")){
                       i = i+1;
                   }
                }
            }
            // 当担保公司进入时
            int m = 0;
            if (roles != null) {
                for (SysRole role : roles) {
                    if(String.valueOf(role.getRoleId()).equals("113")){
                        m = m+1;
                    }
                }
            }

            String bankId = currentUser.getBankId();
            String bankIdFlage = "";
            if (bankId != null) {
                bankIdFlage = bankId;
            }else{
                bankIdFlage = leave.getBankId();
            }
            PlGuaranteeLoanVo leave2 = new PlGuaranteeLoanVo();
            leave.setId(Long.valueOf(businessKey));
            if(i != 0){
                leave.setBankId(bankIdFlage);
                leave2 = plGuaranteeLoanMapper.selectBizLeaveByIdBankId(leave);
            }else{
                if(m == 0){
                    leave2 = plGuaranteeLoanMapper.selectBizLeaveById(leave);
                }else{
                    String guaranteeId = currentUser.getGuaranteeId();
                    if (guaranteeId != null) {
                        leave.setGuaranteeId(guaranteeId);
                    }

                    leave2 = plGuaranteeLoanMapper.selectBizLeaveByIdGuaranteeId(leave);
                }

            }
            if(leave2 != null){
                // 条件过滤 2
                if (StringUtils.isNotBlank(leave.getType()) && !leave.getType().equals(leave2.getType())) {
                    continue;
                }
                leave2.setTaskId(taskImpl.getId());
                if (taskImpl.getSuspensionState() == 2) {
                    leave2.setTaskName("已挂起");
                } else {
                    leave2.setTaskName(taskImpl.getName());
                }
                SysUser sysUser = userMapper.selectUserByLoginName(leave2.getApplyUser());
                leave2.setApplyUserName(sysUser.getUserName());
                results.add(leave2);
            }

        }

        List<PlGuaranteeLoanVo> tempList;
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

    /**
     * 查询已办列表
     * @param bizLeave
     * @param userId
     * @return
     */
    @Override
    public Page<PlGuaranteeLoanVo> findDoneTasks(PlGuaranteeLoanVo bizLeave, String userId) {
        // 手动分页
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        Page<PlGuaranteeLoanVo> list = new Page<>();

        List<PlGuaranteeLoanVo> results = new ArrayList<>();
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
            PlGuaranteeLoan plGuaranteeLoan = new PlGuaranteeLoan();
            plGuaranteeLoan.setId(Long.valueOf(businessKey));
            PlGuaranteeLoanVo leave2 = plGuaranteeLoanMapper.selectBizLeaveById(plGuaranteeLoan);
            PlGuaranteeLoanVo newLeave = new PlGuaranteeLoanVo();
            if (leave2 != null) {
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

        List<PlGuaranteeLoanVo> tempList;
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
    public List<SysDictData> selectplatesEght(){

        return plGuaranteeLoanMapper.selectplatesEght();
    }

}

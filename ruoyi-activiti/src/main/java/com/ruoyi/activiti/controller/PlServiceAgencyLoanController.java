package com.ruoyi.activiti.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.ruoyi.activiti.domain.*;
import com.ruoyi.activiti.service.IBizLeaveService;
import com.ruoyi.activiti.service.IProcessService;
import com.ruoyi.activiti.service.IRyProductService;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.PlBank;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.ISysUserService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.activiti.service.IPlServiceAgencyLoanService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 7大板款信息Controller
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
@Controller
@RequestMapping("/system/loan")
public class PlServiceAgencyLoanController extends BaseController
{
    private String prefix = "plateLoan";

    @Autowired
    private IPlServiceAgencyLoanService plServiceAgencyLoanService;
    @Autowired
    private IBizLeaveService bizLeaveService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private IProcessService processService;
    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private ISysUserService iSysUserService;
    @Autowired
    private IRyProductService iRyProductService;
    @Autowired
    private ISysDictDataService iSysDictDataService;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @RequiresPermissions("system:loan:view")
    @GetMapping()
    public String loan(ModelMap mmap)
    {
        mmap.put("currentUser",ShiroUtils.getSysUser());
        return prefix + "/loan";
    }

    /**
     * 查询7大板款信息列表
     */
    @RequiresPermissions("system:loan:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PlServiceAgencyLoan plServiceAgencyLoan)
    {
        startPage();
        List<PlServiceAgencyLoan> list = plServiceAgencyLoanService.selectPlServiceAgencyLoanList(plServiceAgencyLoan);
        return getDataTable(list);
    }

    /**
     * 导出7大板款信息列表
     */
    @RequiresPermissions("system:loan:export")
    @Log(title = "7大板块信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PlServiceAgencyLoan plServiceAgencyLoan)
    {
        List<PlServiceAgencyLoan> list = plServiceAgencyLoanService.selectPlServiceAgencyLoanList(plServiceAgencyLoan);
        ExcelUtil<PlServiceAgencyLoan> util = new ExcelUtil<PlServiceAgencyLoan>(PlServiceAgencyLoan.class);
        return util.exportExcel(list, "loan");
    }

    /**
     * 新增7大板款信息
     */
    @GetMapping("/add")
    public String add(HttpServletRequest request, ModelMap mmap)
    {
        PlServiceAgencyLoan plServiceAgencyLoan = new PlServiceAgencyLoan();
        List<SysDictData> dict = new ArrayList<SysDictData>();
        /*获取产品id*/
        HttpSession session = request.getSession();
        String productId = (String)session.getAttribute("productId");
        if (productId != null) {
            // 获取服务类型
            RyProduct ryProduct = iRyProductService.selectRyProductById(Long.valueOf(productId));

            SysDictData sysDictData = new SysDictData();
            sysDictData.setDictType("plate");
            dict = iSysDictDataService.selectDictDataList(sysDictData);
            if (dict != null) {
                if (ryProduct != null) {
                    for (SysDictData dictData : dict) {
                        String serviceProductsId = ryProduct.getServiceProductsId();
                        if (serviceProductsId != null) {
                            if(serviceProductsId.equals(dictData.getDictValue())){
                                dictData.setFlage("true");
                            }
                        }


                    }
                }

            }

        }
        // 根据当前登录用户自动查询对应数据
        SysUser sysUser = ShiroUtils.getSysUser();
            if (sysUser != null) {
                plServiceAgencyLoan.setTitle(sysUser.getUserName());
                plServiceAgencyLoan.setReason(sysUser.getReason());
                plServiceAgencyLoan.setCreditaCode(sysUser.getCreditaCode());
                plServiceAgencyLoan.setLegal(sysUser.getLegal());
                plServiceAgencyLoan.setLegalNumber(sysUser.getLegalNumber());
                plServiceAgencyLoan.setContacts(sysUser.getContacts());
                plServiceAgencyLoan.setContactsNumber(sysUser.getContactsNumber());
            }
        mmap.put("plServiceAgencyLoan",plServiceAgencyLoan);
        mmap.put("dict",dict);
        return prefix + "/add";
    }

    /**
     * 新增保存7大板款信息
     */
    @RequiresPermissions("system:loan:add")
    @Log(title = "7大板块信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PlServiceAgencyLoanVo plServiceAgencyLoan, HttpServletRequest request)
    {  Long userId = ShiroUtils.getUserId();
        if (SysUser.isAdmin(userId)) {
            return error("提交申请失败：不允许管理员提交申请！");
        }

        plServiceAgencyLoan.setType("eightPlate");
        plServiceAgencyLoan.setUserId(ShiroUtils.getUserId());
        return toAjax(plServiceAgencyLoanService.insertPlServiceAgencyLoan(plServiceAgencyLoan));
    }

    /**
     * 修改7大板款信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PlServiceAgencyLoanVo plServiceAgencyLoan = plServiceAgencyLoanService.selectPlServiceAgencyLoanById(id);
        mmap.put("plServiceAgencyLoan", plServiceAgencyLoan);
        return prefix + "/edit";
    }

    /**
     * 修改保存7大板款信息
     */
    @RequiresPermissions("system:loan:edit")
    @Log(title = "7大板块信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PlServiceAgencyLoan plServiceAgencyLoan)
    {
        return toAjax(plServiceAgencyLoanService.updatePlServiceAgencyLoan(plServiceAgencyLoan));
    }

    /**
     * 删除7大板款信息
     */
    @RequiresPermissions("system:loan:remove")
    @Log(title = "7大板块信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(plServiceAgencyLoanService.deletePlServiceAgencyLoanByIds(ids));
    }

    /**
     * 提交申请
     */
    @Log(title = "新增服務信息", businessType = BusinessType.UPDATE)
    @PostMapping( "/submitApply")
    @ResponseBody
    public AjaxResult submitApply(Long id) {
        PlServiceAgencyLoanVo psalv = plServiceAgencyLoanService.selectPlServiceAgencyLoanById(id);
        String applyUserId = ShiroUtils.getLoginName();
        plServiceAgencyLoanService.submitApply(psalv, applyUserId, "eightPlate", new HashMap<>());
        return success();
    }

    @GetMapping("/leaveTodo")
    public String todoView() {
        return prefix + "/leaveTodo";
    }

    /**
     * 我的待办列表
     * @return
     */
    @PostMapping("/taskList")
    @ResponseBody
    public TableDataInfo taskList(PlServiceAgencyLoanVo plServiceAgencyLoanVo) {
        plServiceAgencyLoanVo.setType("eightPlate");
        List<PlServiceAgencyLoanVo> list = bizLeaveService.findPlateTasks(plServiceAgencyLoanVo, ShiroUtils.getLoginName());
        return getDataTable(list);
    }

    /**
     * 加载审批弹窗
     * @param taskId
     * @param mmap
     * @return
     */
    @GetMapping("/showVerifyDialog/{taskId}")
    public String showVerifyDialog(@PathVariable("taskId") String taskId, ModelMap mmap) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstanceId = task.getProcessInstanceId();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        PlServiceAgencyLoanVo plsal = plServiceAgencyLoanService.selectPlServiceAgencyLoanById(new Long(processInstance.getBusinessKey()));
        mmap.put("plsal", plsal);
        mmap.put("taskId", taskId);
        String verifyName = task.getTaskDefinitionKey().substring(0, 1).toUpperCase() + task.getTaskDefinitionKey().substring(1);
        return prefix + "/task" + verifyName;
    }
    /**
     * 完成任务
     *
     * @return
     */
    @RequestMapping(value = "/complete/{taskId}", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public AjaxResult complete(@PathVariable("taskId") String taskId, @RequestParam(value = "saveEntity", required = false) String saveEntity,
                               @ModelAttribute("preloadLeave") PlServiceAgencyLoanVo leave, HttpServletRequest request) {
        boolean saveEntityBoolean = BooleanUtils.toBoolean(saveEntity);
        processService.completePlate(taskId, leave.getInstanceId(), leave.getTitle(), leave.getReason(), "eightPlate", new HashMap<String, Object>(), request);
        if (saveEntityBoolean) {
            plServiceAgencyLoanService.updatePlServiceAgencyLoan(leave);
        }
        return success("任务已完成");
    }
    @GetMapping("/leaveDone")
    public String doneView() {
        return prefix + "/leaveDone";
    }

    /**
     * 我的已办列表
     * @param bizLeave
     * @return
     */
    @PostMapping("/taskDoneList")
    @ResponseBody
    public TableDataInfo taskDoneList(PlServiceAgencyLoanVo bizLeave) {
        bizLeave.setType("eightPlate");
        List<PlServiceAgencyLoanVo> list = plServiceAgencyLoanService.findDoneTasks(bizLeave, ShiroUtils.getLoginName());
        return getDataTable(list);
    }
    //审批时间轴
    @GetMapping("/timeline")
    public String timeline(@RequestParam(value = "instanceId", required = false) String instanceId,@RequestParam(value = "id", required = false) Long id, ModelMap mmap, HistoricActivity historicActivity) {
        List<HistoricActivity> list = processService.selectHistoryList(instanceId, historicActivity);
        Collections.reverse(list);
        PlServiceAgencyLoanVo plServiceAgencyLoanVo = plServiceAgencyLoanService.selectPlServiceAgencyLoanById(id);
        mmap.put("list",list);
        mmap.put("plServiceAgencyLoanVo",plServiceAgencyLoanVo);
        return prefix + "/timeline";
    }
    @PostMapping(value = "/uploadFile")
    @ResponseBody
    public AjaxResult uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        try
        {
            // 上传文件路径
            String filePath = Global.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 本地资源通用下载
     */
    @GetMapping("/resource")
    public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        // 本地资源路径
        String localPath = Global.getProfile();
        // 数据库资源地址
        String downloadPath = localPath + StringUtils.substringAfter(resource, Constants.RESOURCE_PREFIX);
        // 下载名称
        String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, downloadName));
        FileUtils.writeBytes(downloadPath, response.getOutputStream());
    }
    @PostMapping("/service")
    @ResponseBody
    public ArrayList<SysUser> service(String serviceId,ModelMap map)
    {
        ArrayList<SysUser> usersList = new ArrayList<>();
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectUserRoleByRoleId(Long.valueOf(serviceId));
        if (sysUserRoles != null) {
            for (SysUserRole sysUserRole : sysUserRoles) {
                SysUser sysUser = iSysUserService.selectUserRoleProductsById(sysUserRole.getUserId(), sysUserRole.getRoleId());
                if (sysUser != null) {
                    usersList.add(sysUser);
                }
            }
        }

        return usersList;
    }
    /**
     * 提交申请
     */
    @PostMapping( "/submitComment")
    @ResponseBody
    public AjaxResult submitComment(Long id, String comment,Long productId) {

        plServiceAgencyLoanService.updatePlServiceAgencyLoanAndComment(id,comment,productId);
        return success();
    }
}

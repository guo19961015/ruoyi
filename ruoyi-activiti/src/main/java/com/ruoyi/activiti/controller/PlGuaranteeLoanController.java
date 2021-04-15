package com.ruoyi.activiti.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


import com.alibaba.druid.support.json.JSONUtils;
import com.ruoyi.activiti.domain.*;
import com.ruoyi.activiti.mapper.PlGuaranteeLoanMapper;
import com.ruoyi.activiti.service.IPlGuaranteeService;
import com.ruoyi.activiti.service.IPlTodoItemService;
import com.ruoyi.activiti.service.IProcessService;
import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.framework.web.service.DictService;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.*;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.activiti.service.IPlGuaranteeLoanService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 助保贷信息Controller
 *
 * @author ruoyi
 * @date 2020-08-23
 */
@Controller
@RequestMapping("/loan")
public class PlGuaranteeLoanController extends BaseController
{
    private String prefix = "loan";

    @Autowired
    private IPlGuaranteeLoanService plGuaranteeLoanService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private IProcessService processService;
    @Autowired
    private IPlBankService plBankService;
    @Autowired
    private ISysPostService postService;
    @Autowired
    private IPlGuaranteeService plGuaranteeService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private DictService dictService;
    @Autowired
    private IPlTodoItemService plTodoItemService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private ISysDeptService deptService;
    @Autowired
    private ISysRoleService iSysRoleService;
    @Autowired
    private PlGuaranteeLoanMapper plGuaranteeLoanMapper;
    @RequiresPermissions("pl:loan:view")
    @GetMapping()
    public String loan(ModelMap mmap)
    {
        mmap.put("currentUser",ShiroUtils.getSysUser());
        return prefix + "/loan";
    }

    /**
     * 查询助保贷信息列表
     */
    @RequiresPermissions("pl:loan:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PlGuaranteeLoanVo plGuaranteeLoan)
    {
        if (!SysUser.isAdmin(ShiroUtils.getUserId()) && !SysUser.isSystem(ShiroUtils.getUserId())) {
            plGuaranteeLoan.setCreateBy(ShiroUtils.getLoginName());
        }
        plGuaranteeLoan.setType("process");
        startPage();
        List<PlGuaranteeLoanVo> list = plGuaranteeLoanService.selectPlGuaranteeLoanList(plGuaranteeLoan);
        if (list != null) {
            for (PlGuaranteeLoanVo plGuaranteeLoanVo : list) {
                String zycchdy = codeSelectName(plGuaranteeLoanVo.getMortgageOfOwnAssets(), "zycchdy");
                if (zycchdy != null && !zycchdy.equals("") ) {
                    plGuaranteeLoanVo.setMortgageOfOwnAssets(zycchdy);
                }
                String dsfzchdy = codeSelectName(plGuaranteeLoanVo.getThirdPartyAssetMortgage(), "dsfzchdy");
                if (dsfzchdy != null && !dsfzchdy.equals("")) {
                    plGuaranteeLoanVo.setThirdPartyAssetMortgage(dsfzchdy);
                }
                String bzhdb = codeSelectName(plGuaranteeLoanVo.getGuarantee(), "bzhdb");
                if (bzhdb != null && !bzhdb.equals("")) {
                    plGuaranteeLoanVo.setGuarantee(bzhdb);
                }
                // 查询地区名字
                String deptId = plGuaranteeLoanVo.getDeptId();
                if (deptId != null) {
                    plGuaranteeLoanVo.setDeptName(deptService.selectDeptById(Long.valueOf(deptId)).getDeptName());
                }

            }
        }
        return getDataTable(list);
    }
    /*
     *   用于从，号分割字符查找字典对应的name
     *      码值转换name显示(下拉框数据集合(1,2,3)，下拉框字典名字)
     * */
    public String codeSelectName(String list,String labelName){
        String StrNum = "";
        if (list != null) {
            int i = 0;
            String[] split = list.split(",");
            if (split != null) {
                for (String s : split) {
                    if (i == 0){
                        StrNum = StrNum + dictService.getLabel(labelName, s);
                    }else{
                        StrNum = StrNum +"、"+ dictService.getLabel(labelName, s);
                    }
                    i++;
                }
            }
        }
        return StrNum;
    }

    /**
     * 导出助保贷信息列表
     */
    @RequiresPermissions("pl:loan:export")
    @Log(title = "助保贷信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PlGuaranteeLoanVo plGuaranteeLoan)
    {
        List<PlGuaranteeLoanVo> list = plGuaranteeLoanMapper.selectPlGuaranteeLoanList(plGuaranteeLoan);
        ExcelUtil<PlGuaranteeLoanVo> util = new ExcelUtil<PlGuaranteeLoanVo>(PlGuaranteeLoanVo.class);
        return util.exportExcel(list, "助保贷信息");
    }

    /**
     * 新增助保贷信息
     */
    @GetMapping("/add")
    public String add(ModelMap mmap,HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        String bankId = (String)session.getAttribute("bankId");
        PlBank plBank = new PlBank();
        if (bankId != null && bankId != "0") {
            plBank.setId(Long.valueOf(bankId));
        }
        SysUser sysUser = userService.selectUserById(ShiroUtils.getUserId());
        mmap.put("sysUser",sysUser);
        mmap.put("banks", plBankService.selectPlBankList(plBank));
        mmap.put("posts", postService.selectPostAll());
        return prefix + "/add";
    }

    /**
     * 新增保存助保贷信息
     */
    @RequiresPermissions("pl:loan:add")
    @Log(title = "助保贷信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PlGuaranteeLoan plGuaranteeLoan,HttpServletRequest request)
    { HttpSession session = request.getSession();
        Long userId = ShiroUtils.getUserId();
        if (SysUser.isAdmin(userId)) {
            return error("提交申请失败：不允许管理员提交申请！");
        }
        plGuaranteeLoan.setType("process");
        String proposedBank = plGuaranteeLoan.getProposedBank();
        if (proposedBank != null) {
            plGuaranteeLoan.setBankId(proposedBank);
            PlBank plBank = plBankService.selectPlBankById(Long.valueOf(proposedBank));
            if (plBank != null) {
                plGuaranteeLoan.setGuaranteeId(plBank.getGuaranteeId());
            }
        }else{

        }
        SysUser sysUser = ShiroUtils.getSysUser();
        Long deptId = sysUser.getDeptId();
        if (deptId != null) {
            plGuaranteeLoan.setDeptId(String.valueOf(deptId));
        }

        return toAjax(plGuaranteeLoanService.insertPlGuaranteeLoan(plGuaranteeLoan));
    }

    /**
     * 修改助保贷信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PlGuaranteeLoan plGuaranteeLoan = plGuaranteeLoanService.selectPlGuaranteeLoanById(id);
        ArrayList<LinkedHashMap<String, String>> zycchdy = selectJsonDate(plGuaranteeLoan.getMortgageOfOwnAssets(), "zycchdy");
        ArrayList<LinkedHashMap<String, String>> dsfzchdy = selectJsonDate(plGuaranteeLoan.getThirdPartyAssetMortgage(), "dsfzchdy");
        ArrayList<LinkedHashMap<String, String>> bzhdb = selectJsonDate(plGuaranteeLoan.getGuarantee(), "bzhdb");
        plGuaranteeLoan.setMortgageOfOwnAssetsJson(zycchdy);
        plGuaranteeLoan.setThirdPartyAssetMortgageJson(dsfzchdy);
        plGuaranteeLoan.setGuaranteeJson(bzhdb);
        mmap.put("plGuaranteeLoan", plGuaranteeLoan);
        mmap.put("banks", plBankService.selectPlBankListEdit(plGuaranteeLoan.getBankId()));
        return prefix + "/edit";
    }
    /*
    *   用于格式化下拉框数据(下拉框数据集合，下拉框字典名字)
    * */
    public ArrayList<LinkedHashMap<String, String>> selectJsonDate(String date,String labelName){
        String[] split = new String[0];
        if(date != null){
            split = date.split(",");
        }
        ArrayList<LinkedHashMap<String, String>> listHashMap = new ArrayList<LinkedHashMap<String, String>>();
        List<SysDictData> type = dictService.getType(labelName);
        if (type != null) {
            for (SysDictData sysDictData : type) {
                LinkedHashMap<String, String> map = new LinkedHashMap<>();
                map.put("id",sysDictData.getDictValue());
                map.put("text",sysDictData.getDictLabel());
                map.put("selected","false");
                map.put("disabled","false");
                if(date != null){
                    for (String s : split) {
                        if(sysDictData.getDictValue().equals(s)){
                            map.put("selected","true");
                        }
                    }
                }
                listHashMap.add(map);
            }
        }
        return  listHashMap;
    }
    /**
     * 修改保存助保贷信息
     */
    @RequiresPermissions("pl:loan:edit")
    @Log(title = "助保贷信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PlGuaranteeLoan plGuaranteeLoan)
    {
        Long id = plGuaranteeLoan.getId();
        if (id != null) {
            PlGuaranteeLoanVo plGuaranteeLoanVo = plGuaranteeLoanService.selectPlGuaranteeLoanById(id);
            String instanceId = plGuaranteeLoanVo.getInstanceId();
            if (instanceId != null && !SysUser.isAdmin(ShiroUtils.getUserId())) {
                return error("修改失败：不允许对已提交数据进行修改！");
            }
        }
        return toAjax(plGuaranteeLoanService.updatePlGuaranteeLoan(plGuaranteeLoan));
    }

    /**
     * 删除助保贷信息
     */
    @RequiresPermissions("pl:loan:remove")
    @Log(title = "助保贷信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        if (ids != null) {
            String[] split = ids.split(",");
            if (split.length>0) {
                for (String s : split) {
                    PlGuaranteeLoanVo plGuaranteeLoanVo = plGuaranteeLoanService.selectPlGuaranteeLoanById(Long.valueOf(s));
                    if (plGuaranteeLoanVo != null) {
                        String instanceId = plGuaranteeLoanVo.getInstanceId();
                        if (instanceId != null && !SysUser.isAdmin(ShiroUtils.getUserId())) {
                            return error("删除失败：不允许对已提交数据进行删除！");
                        }
                    }
                }
            }
        }
        return toAjax(plGuaranteeLoanService.deletePlGuaranteeLoanByIds(ids));
    }

    /**
     * 提交申请
     */
    @Log(title = "助保贷业务", businessType = BusinessType.UPDATE)
    @PostMapping( "/submitApply")
    @ResponseBody
    public AjaxResult submitApply(Long id) {
        PlGuaranteeLoanVo leave = plGuaranteeLoanService.selectPlGuaranteeLoanById(id);
        String applyUserId = ShiroUtils.getLoginName();
        plGuaranteeLoanService.submitApply(leave, applyUserId, "process", new HashMap<>());
        return success();
    }
    @GetMapping("/leaveTodo")
    public String todoView() {
        return prefix + "/leaveTodo";
    }
    /**
     * 待审核
     * @return
     */
    @PostMapping("/taskList")
    @ResponseBody
    public TableDataInfo taskList(PlGuaranteeLoanVo bizLeave) {
        bizLeave.setType("process");
        List<PlGuaranteeLoanVo> list = plGuaranteeLoanService.findTodoTasks(bizLeave, ShiroUtils.getLoginName());
        if (list != null) {
            for (PlGuaranteeLoanVo plGuaranteeLoanVo : list) {
                String zycchdy = codeSelectName(plGuaranteeLoanVo.getMortgageOfOwnAssets(), "zycchdy");
                if (zycchdy != null && !zycchdy.equals("") ) {
                    plGuaranteeLoanVo.setMortgageOfOwnAssets(zycchdy);
                }
                String dsfzchdy = codeSelectName(plGuaranteeLoanVo.getThirdPartyAssetMortgage(), "dsfzchdy");
                if (dsfzchdy != null && !dsfzchdy.equals("")) {
                    plGuaranteeLoanVo.setThirdPartyAssetMortgage(dsfzchdy);
                }
                String bzhdb = codeSelectName(plGuaranteeLoanVo.getGuarantee(), "bzhdb");
                if (bzhdb != null && !bzhdb.equals("")) {
                    plGuaranteeLoanVo.setGuarantee(bzhdb);
                }

                plGuaranteeLoanVo.setDeptName(deptService.selectDeptById(Long.valueOf(plGuaranteeLoanVo.getDeptId())).getDeptName());

            }

        }

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
        PlGuaranteeLoanVo bizLeave = plGuaranteeLoanService.selectBizLeaveById(new Long(processInstance.getBusinessKey()));

        // 将字典码值变为name
        ArrayList<LinkedHashMap<String, String>> zycchdy = selectJsonDate(bizLeave.getMortgageOfOwnAssets(), "zycchdy");
        ArrayList<LinkedHashMap<String, String>> dsfzchdy = selectJsonDate(bizLeave.getThirdPartyAssetMortgage(), "dsfzchdy");
        ArrayList<LinkedHashMap<String, String>> bzhdb = selectJsonDate(bizLeave.getGuarantee(), "bzhdb");
        bizLeave.setMortgageOfOwnAssetsJson(zycchdy);
        bizLeave.setThirdPartyAssetMortgageJson(dsfzchdy);
        bizLeave.setGuaranteeJson(bzhdb);

        mmap.put("bizLeave", bizLeave);
        mmap.put("taskId", taskId);
        String verifyName = task.getTaskDefinitionKey().substring(0, 1).toUpperCase() + task.getTaskDefinitionKey().substring(1);
        List<PlBank> plBanks = plBankService.selectPlBankList(new PlBank());
        if (plBanks != null) {
            for (PlBank plBank : plBanks) {
                if(bizLeave != null){
                   if(bizLeave.getBankId().equals(String.valueOf(plBank.getId()))){
                       plBank.setFlag(true);
                       break;
                   }
                }
            }
        }
        mmap.put("banks",plBanks);
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
                               @ModelAttribute("preloadLeave") PlGuaranteeLoanVo leave, HttpServletRequest request) {
        boolean saveEntityBoolean = BooleanUtils.toBoolean(saveEntity);
        String realityStartTime = request.getParameter("realityStartTime");
        String realityEndTime = request.getParameter("realityEndTime");
        String actualLoanAmount = request.getParameter("actualLoanAmount");
        if (realityStartTime != null) {
            try {
                Date parse = new SimpleDateFormat("yyyy-MM-dd hh-mm").parse(realityStartTime);
                leave.setRealityStartTime(parse);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (realityEndTime != null) {
            try {
                Date parse = new SimpleDateFormat("yyyy-MM-dd hh-mm").parse(realityEndTime);
                leave.setRealityEndTime(parse);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        if (actualLoanAmount != null && !actualLoanAmount.equals("") ) {
            BigDecimal bigDecimal = new BigDecimal(actualLoanAmount);
            leave.setActualLoanAmount(bigDecimal);
        }
        processService.completePl(leave.getRealityStartTime(),leave.getRealityEndTime(), leave.getActualLoanAmount(),taskId, leave.getInstanceId(), leave.getTitle(), leave.getReason(), "process", new HashMap<String, Object>(), request);
        if (saveEntityBoolean) {
            plGuaranteeLoanService.updatePlGuaranteeLoan(leave);
        }/*else{
            // 当流程是放款角色时，将贷款时间及贷款额度存入流程表中
            plGuaranteeLoanService.updatePlGuaranteeLoanSaveLoan(leave);
        }*/
        return success("任务已完成");
    }

    /**
     * 自动绑定页面字段
     */
    @ModelAttribute("preloadLeave")
    public PlGuaranteeLoanVo getLeave(@RequestParam(value = "id", required = false) Long id, HttpSession session) {
        if (id != null) {
            return plGuaranteeLoanService.selectBizLeaveById(id);
        }
        return new PlGuaranteeLoanVo();
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
    public TableDataInfo taskDoneList(PlGuaranteeLoanVo bizLeave) {
        bizLeave.setType("process");
        List<PlGuaranteeLoanVo> list = plGuaranteeLoanService.findDoneTasks(bizLeave, ShiroUtils.getLoginName());
        if (list != null) {
            for (PlGuaranteeLoanVo plGuaranteeLoanVo : list) {
                String zycchdy = codeSelectName(plGuaranteeLoanVo.getMortgageOfOwnAssets(), "zycchdy");
                if (zycchdy != null && !zycchdy.equals("") ) {
                    plGuaranteeLoanVo.setMortgageOfOwnAssets(zycchdy);
                }
                String dsfzchdy = codeSelectName(plGuaranteeLoanVo.getThirdPartyAssetMortgage(), "dsfzchdy");
                if (dsfzchdy != null && !dsfzchdy.equals("")) {
                    plGuaranteeLoanVo.setThirdPartyAssetMortgage(dsfzchdy);
                }
                String bzhdb = codeSelectName(plGuaranteeLoanVo.getGuarantee(), "bzhdb");
                if (bzhdb != null && !bzhdb.equals("")) {
                    plGuaranteeLoanVo.setGuarantee(bzhdb);
                }
                // 查询地区名字
                String deptId = plGuaranteeLoanVo.getDeptId();
                if (deptId != null) {
                    plGuaranteeLoanVo.setDeptName(deptService.selectDeptById(Long.valueOf(deptId)).getDeptName());
                }
            }
        }
        return getDataTable(list);
    }
    //审批时间轴
    @GetMapping("/timeline")
    public String timeline(@RequestParam(value = "instanceId", required = false) String instanceId,@RequestParam(value = "id", required = false) Long id, ModelMap mmap, HistoricActivity historicActivity) {
        List<HistoricActivity> list = processService.selectHistoryList(instanceId, historicActivity);
        Collections.reverse(list);
        PlGuaranteeLoanVo plGuaranteeLoanVo = plGuaranteeLoanService.selectBizLeaveById(id);
        mmap.put("list",list);
        mmap.put("plGuaranteeLoanVo",plGuaranteeLoanVo);
        return prefix + "/timeline";
    }

    // 数据统计
    @GetMapping("/metrics")
    public String metrics(ModelMap map)
    {
        // 各银行助保贷数量
        PlGuaranteeLoanVo plGuaranteeLoan = new PlGuaranteeLoanVo();
        List<PlGuaranteeLoanVo> groupBankList = plGuaranteeLoanService.selectPlGuaranteeLoanListGroupBank(plGuaranteeLoan);
        ArrayList<LinkedHashMap<String, String>> mapList = new ArrayList<>();
        LinkedList<String> bankList = new LinkedList<>();
        if (groupBankList != null) {
            for (PlGuaranteeLoanVo plGuaranteeLoanVo : groupBankList) {
                LinkedHashMap<String, String> map1 = new LinkedHashMap<>();
                map1.put("value",plGuaranteeLoanVo.getNumber());
                map1.put("name",plGuaranteeLoanVo.getBankName());
                bankList.add(plGuaranteeLoanVo.getBankName());
                mapList.add(map1);
            }
        }

        // 各担保公司保贷数量
        List<PlGuaranteeLoanVo> groupGuaranteeGList = plGuaranteeLoanService.selectPlGuaranteeLoanListGroupGuarantee(plGuaranteeLoan);
        //
        LinkedList<String> dateYearList = new LinkedList<>();
        LinkedList<String> dateNumberList = new LinkedList<>();
        List<PlGuaranteeLoan> groupDateGList = plGuaranteeLoanService.selectPlGuaranteeLoanListGroupDate(plGuaranteeLoan);
        if (groupDateGList != null) {
            for (PlGuaranteeLoan guaranteeLoan : groupDateGList) {
                dateYearList.add(guaranteeLoan.getDate());
                dateNumberList.add(guaranteeLoan.getNumber());
            }
        }
        // 各旗区助保贷数量
        List<PlGuaranteeLoan> groupDeptList = plGuaranteeLoanService.selectPlGuaranteeLoanListGroupDept(plGuaranteeLoan);
        ArrayList<String> deptNameList = new ArrayList<>();
        ArrayList<String> deptNumberList = new ArrayList<>();
        if (groupDeptList != null) {
            for (PlGuaranteeLoan guaranteeLoan : groupDeptList) {
                deptNameList.add(guaranteeLoan.getDeptName());
                deptNumberList.add(guaranteeLoan.getNumber());
            }
        }
        // 申请企业数、放款企业数、放款总金额、已合作银行
        List<PlGuaranteeLoan> groupFourList = plGuaranteeLoanService.selectPlGuaranteeLoanListGroupFour(plGuaranteeLoan);

        //八大板块对应处理数
        ArrayList<String> platesEghtNameList = new ArrayList<>();
        ArrayList<LinkedHashMap<String, String>> platesEghtMapList = new ArrayList<>();
        List<SysDictData> dictList = plGuaranteeLoanService.selectplatesEght();
        if (dictList != null) {
            for (SysDictData sysDictData : dictList) {
                platesEghtNameList.add(sysDictData.getDictLabel());
                LinkedHashMap<String, String> stringStringLinkedHashMap = new LinkedHashMap<>();
                stringStringLinkedHashMap.put("value",sysDictData.getNumber());
                stringStringLinkedHashMap.put("name",sysDictData.getDictLabel());
                platesEghtMapList.add(stringStringLinkedHashMap);
            }
        }
        //各旗区企业注册数量
        ArrayList<String> enterpriseNameList = new ArrayList<>();
        ArrayList<String> enterpriseNumberList = new ArrayList<>();
        List<SysDept> enterpriseDept = deptService.selectEnterprise();

        if (enterpriseDept != null) {
            for (SysDept sysDept : enterpriseDept) {
                enterpriseNameList.add(sysDept.getDeptName());
                enterpriseNumberList.add(sysDept.getNumber());
            }
        }
        // 八大板块入驻服务机构数量统计
        ArrayList<String> mechanismNameList = new ArrayList<>();
        ArrayList<String> mechanismNumberList = new ArrayList<>();
       List<SysRole> mechanismLists = iSysRoleService.selectMechanism();
        if (mechanismLists != null) {
            for (SysRole sysRole : mechanismLists) {
                mechanismNameList.add(sysRole.getRoleName());
                mechanismNumberList.add(sysRole.getNumber());
            }
        }

        map.put("bankList",JSONUtils.toJSONString(bankList));
        map.put("jsonBankList",JSONUtils.toJSONString(mapList));
        map.put("dateYearList",JSONUtils.toJSONString(dateYearList));
        map.put("dateNumberList",JSONUtils.toJSONString(dateNumberList));
        map.put("deptNameList",JSONUtils.toJSONString(deptNameList));
        map.put("deptNumberList",JSONUtils.toJSONString(deptNumberList));
        map.put("platesEghtNameList",JSONUtils.toJSONString(platesEghtNameList));
        map.put("platesEghtMapList",JSONUtils.toJSONString(platesEghtMapList));
        map.put("enterpriseNameList",JSONUtils.toJSONString(enterpriseNameList));
        map.put("enterpriseNumberList",JSONUtils.toJSONString(enterpriseNumberList));
        map.put("mechanismNameList",JSONUtils.toJSONString(mechanismNameList));
        map.put("mechanismNumberList",JSONUtils.toJSONString(mechanismNumberList));

        map.put("groupFourList",groupFourList.get(0));
        return prefix + "/metrics";
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
}

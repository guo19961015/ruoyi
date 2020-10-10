package com.ruoyi.web.controller.system;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.activiti.domain.PlGuarantee;
import com.ruoyi.activiti.service.IPlGuaranteeService;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.PlBank;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.IPlBankService;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * 银行信息Controller
 * 
 * @author ruoyi
 * @date 2020-08-29
 */
@Controller
@RequestMapping("/system/bank")
public class PlBankController extends BaseController
{
    private static final Logger log = LoggerFactory.getLogger(SysProfileController.class);

    @Autowired
    private IPlBankService plBankService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private IPlGuaranteeService plGuaranteeService;
    @RequiresPermissions("system:bank:view")
    @GetMapping()
    public String bank()
    {
        return "system/bank/bank";
    }

    /**
     * 查询银行信息列表
     */
    @RequiresPermissions("system:bank:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PlBank plBank)
    {
        startPage();
        SysUser sysUser = ShiroUtils.getSysUser();
        String bankId = sysUser.getBankId();
        if (bankId != null && !bankId.equals("")) {
            PlBank plBank1 = plBankService.selectPlBankById(Long.valueOf(bankId));
            plBank.setBankName(plBank1.getBankName());
        }
        List<PlBank> list = plBankService.selectPlBankList(plBank);

        return getDataTable(list);
    }

    /**
     * 导出银行信息列表
     */
    @RequiresPermissions("system:bank:export")
    @Log(title = "银行信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PlBank plBank)
    {
        List<PlBank> list = plBankService.selectPlBankList(plBank);
        ExcelUtil<PlBank> util = new ExcelUtil<PlBank>(PlBank.class);
        return util.exportExcel(list, "bank");
    }

    /**
     * 新增银行信息
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        SysUser user = ShiroUtils.getSysUser();
        mmap.put("user", user);
        mmap.put("plGuarantees", plGuaranteeService.selectPlGuaranteeList(new PlGuarantee()));
        return "system/bank/add";
    }

    /**
     * 新增保存银行信息
     */
    @RequiresPermissions("system:bank:add")
    @Log(title = "银行信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PlBank plBank)
    {
        return toAjax( plBankService.insertPlBank(plBank));
    }
    /**
     * 保存照片
     */
    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult updateAvatar(@RequestParam("bankImage") MultipartFile bankImage) throws IOException {

        try
        {
            // 上传文件路径
            String filePath = Global.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, bankImage);
            String url = "http://ordoszxqy.org.cn/ry" + fileName;
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
     * 修改银行信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PlBank plBank = plBankService.selectPlBankById(id);
        PlGuarantee plGuarantee = new PlGuarantee();
        plGuarantee.setBankId(String.valueOf(id));
        mmap.put("plBank", plBank);
        mmap.put("plGuarantees", plGuaranteeService.selectPlGuaranteeList(plGuarantee));
        return  "system/bank/edit";
    }

    /**
     * 修改保存银行信息
     */
    @RequiresPermissions("system:bank:edit")
    @Log(title = "银行信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PlBank plBank)
    {
        return toAjax(plBankService.updatePlBank(plBank));
    }

    /**
     * 删除银行信息
     */
    @RequiresPermissions("system:bank:remove")
    @Log(title = "银行信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(plBankService.deletePlBankByIds(ids));
    }
}

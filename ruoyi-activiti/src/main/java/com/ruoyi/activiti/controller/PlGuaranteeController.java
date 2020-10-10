package com.ruoyi.activiti.controller;

import java.util.List;

import com.ruoyi.activiti.domain.PlGuarantee;
import com.ruoyi.activiti.service.IPlGuaranteeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 担保公司信息Controller
 * 
 * @author xiaoguo
 * @date 2020-08-30
 */
@Controller
@RequestMapping("/pl/guarantee")
public class PlGuaranteeController extends BaseController
{
    private String prefix = "guarantee";

    @Autowired
    private IPlGuaranteeService plGuaranteeService;

    @RequiresPermissions("pl:guarantee:view")
    @GetMapping()
    public String guarantee()
    {
        return prefix + "/guarantee";
    }

    /**
     * 查询担保公司信息列表
     */
    @RequiresPermissions("pl:guarantee:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PlGuarantee plGuarantee)
    {
        startPage();
        List<PlGuarantee> list = plGuaranteeService.selectPlGuaranteeList(plGuarantee);
        return getDataTable(list);
    }

    /**
     * 导出担保公司信息列表
     */
    @RequiresPermissions("pl:guarantee:export")
    @Log(title = "担保公司信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PlGuarantee plGuarantee)
    {
        List<PlGuarantee> list = plGuaranteeService.selectPlGuaranteeList(plGuarantee);
        ExcelUtil<PlGuarantee> util = new ExcelUtil<PlGuarantee>(PlGuarantee.class);
        return util.exportExcel(list, "guarantee");
    }

    /**
     * 新增担保公司信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存担保公司信息
     */
    @RequiresPermissions("pl:guarantee:add")
    @Log(title = "担保公司信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PlGuarantee plGuarantee)
    {
        return toAjax(plGuaranteeService.insertPlGuarantee(plGuarantee));
    }

    /**
     * 修改担保公司信息
     */
    @GetMapping("/edit/{guaranteeId}")
    public String edit(@PathVariable("guaranteeId") Long guaranteeId, ModelMap mmap)
    {
        PlGuarantee plGuarantee = plGuaranteeService.selectPlGuaranteeById(guaranteeId);
        mmap.put("plGuarantee", plGuarantee);
        return prefix + "/edit";
    }

    /**
     * 修改保存担保公司信息
     */
    @RequiresPermissions("pl:guarantee:edit")
    @Log(title = "担保公司信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PlGuarantee plGuarantee)
    {
        return toAjax(plGuaranteeService.updatePlGuarantee(plGuarantee));
    }

    /**
     * 删除担保公司信息
     */
    @RequiresPermissions("pl:guarantee:remove")
    @Log(title = "担保公司信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(plGuaranteeService.deletePlGuaranteeByIds(ids));
    }
}

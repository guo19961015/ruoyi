package com.ruoyi.activiti.controller;

import java.util.List;

import com.ruoyi.system.domain.PlComplaints;
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
import com.ruoyi.activiti.service.IPlComplaintsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 投诉意见Controller
 *
 * @author xiaoguo
 * @date 2020-11-22
 */
@Controller
@RequestMapping("/activiti/complaints")
public class PlComplaintsController extends BaseController
{
    private String prefix = "activiti/complaints";

    @Autowired
    private IPlComplaintsService plComplaintsService;

    @RequiresPermissions("activiti:complaints:view")
    @GetMapping()
    public String complaints()
    {
        return prefix + "/complaints";
    }

    /**
     * 查询投诉意见列表
     */
    @RequiresPermissions("activiti:complaints:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PlComplaints plComplaints)
    {
        startPage();
        List<PlComplaints> list = plComplaintsService.selectPlComplaintsList(plComplaints);
        return getDataTable(list);
    }

    /**
     * 导出投诉意见列表
     */
    @RequiresPermissions("activiti:complaints:export")
    @Log(title = "投诉意见", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PlComplaints plComplaints)
    {
        List<PlComplaints> list = plComplaintsService.selectPlComplaintsList(plComplaints);
        ExcelUtil<PlComplaints> util = new ExcelUtil<PlComplaints>(PlComplaints.class);
        return util.exportExcel(list, "complaints");
    }

    /**
     * 新增投诉意见
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存投诉意见
     */
    @RequiresPermissions("activiti:complaints:add")
    @Log(title = "投诉意见", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PlComplaints plComplaints)
    {
        return toAjax(plComplaintsService.insertPlComplaints(plComplaints));
    }

    /**
     * 修改投诉意见
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PlComplaints plComplaints = plComplaintsService.selectPlComplaintsById(id);
        mmap.put("plComplaints", plComplaints);
        return prefix + "/edit";
    }

    /**
     * 修改保存投诉意见
     */
    @RequiresPermissions("activiti:complaints:edit")
    @Log(title = "投诉意见", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PlComplaints plComplaints)
    {
        return toAjax(plComplaintsService.updatePlComplaints(plComplaints));
    }

    /**
     * 删除投诉意见
     */
    @RequiresPermissions("activiti:complaints:remove")
    @Log(title = "投诉意见", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(plComplaintsService.deletePlComplaintsByIds(ids));
    }
}

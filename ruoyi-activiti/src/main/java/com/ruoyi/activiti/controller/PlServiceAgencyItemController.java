package com.ruoyi.activiti.controller;

import java.util.List;

import com.ruoyi.activiti.domain.PlServiceAgencyItem;
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
import com.ruoyi.activiti.service.IPlServiceAgencyItemService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 待办事项Controller
 * 
 * @author xiaoguo
 * @date 2020-09-21
 */
@Controller
@RequestMapping("/system/item")
public class PlServiceAgencyItemController extends BaseController
{
    private String prefix = "/plate";

    @Autowired
    private IPlServiceAgencyItemService plServiceAgencyItemService;

    @RequiresPermissions("system:item:view")
    @GetMapping()
    public String item()
    {
        return prefix + "/item";
    }

    /**
     * 查询待办事项列表
     */
    @RequiresPermissions("system:item:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PlServiceAgencyItem plServiceAgencyItem)
    {
        startPage();
        List<PlServiceAgencyItem> list = plServiceAgencyItemService.selectPlServiceAgencyItemList(plServiceAgencyItem);
        return getDataTable(list);
    }

    /**
     * 导出待办事项列表
     */
    @RequiresPermissions("system:item:export")
    @Log(title = "待办事项", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PlServiceAgencyItem plServiceAgencyItem)
    {
        List<PlServiceAgencyItem> list = plServiceAgencyItemService.selectPlServiceAgencyItemList(plServiceAgencyItem);
        ExcelUtil<PlServiceAgencyItem> util = new ExcelUtil<PlServiceAgencyItem>(PlServiceAgencyItem.class);
        return util.exportExcel(list, "item");
    }

    /**
     * 新增待办事项
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存待办事项
     */
    @RequiresPermissions("system:item:add")
    @Log(title = "待办事项", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PlServiceAgencyItem plServiceAgencyItem)
    {
        return toAjax(plServiceAgencyItemService.insertPlServiceAgencyItem(plServiceAgencyItem));
    }

    /**
     * 修改待办事项
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PlServiceAgencyItem plServiceAgencyItem = plServiceAgencyItemService.selectPlServiceAgencyItemById(id);
        mmap.put("plServiceAgencyItem", plServiceAgencyItem);
        return prefix + "/edit";
    }

    /**
     * 修改保存待办事项
     */
    @RequiresPermissions("system:item:edit")
    @Log(title = "待办事项", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PlServiceAgencyItem plServiceAgencyItem)
    {
        return toAjax(plServiceAgencyItemService.updatePlServiceAgencyItem(plServiceAgencyItem));
    }

    /**
     * 删除待办事项
     */
    @RequiresPermissions("system:item:remove")
    @Log(title = "待办事项", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(plServiceAgencyItemService.deletePlServiceAgencyItemByIds(ids));
    }
}

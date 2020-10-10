package com.ruoyi.activiti.controller;

import java.util.List;
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
import com.ruoyi.activiti.domain.PlTodoItem;
import com.ruoyi.activiti.service.IPlTodoItemService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 待办事项Controller
 *
 * @author ruoyi
 * @date 2020-08-24
 */
@Controller
@RequestMapping("/pl/item")
public class PlTodoItemController extends BaseController
{
    private String prefix = "pl/item";

    @Autowired
    private IPlTodoItemService plTodoItemService;

    @RequiresPermissions("pl:item:view")
    @GetMapping()
    public String item()
    {
        return prefix + "/item";
    }

    /**
     * 查询待办事项列表
     */
    @RequiresPermissions("pl:item:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PlTodoItem plTodoItem)
    {
        startPage();
        List<PlTodoItem> list = plTodoItemService.selectPlTodoItemList(plTodoItem);
        return getDataTable(list);
    }

    /**
     * 导出待办事项列表
     */
    @RequiresPermissions("pl:item:export")
    @Log(title = "待办事项", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PlTodoItem plTodoItem)
    {
        List<PlTodoItem> list = plTodoItemService.selectPlTodoItemList(plTodoItem);
        ExcelUtil<PlTodoItem> util = new ExcelUtil<PlTodoItem>(PlTodoItem.class);
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
    @RequiresPermissions("pl:item:add")
    @Log(title = "待办事项", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PlTodoItem plTodoItem)
    {
        return toAjax(plTodoItemService.insertPlTodoItem(plTodoItem));
    }

    /**
     * 修改待办事项
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        PlTodoItem plTodoItem = plTodoItemService.selectPlTodoItemById(id);
        mmap.put("plTodoItem", plTodoItem);
        return prefix + "/edit";
    }

    /**
     * 修改保存待办事项
     */
    @RequiresPermissions("pl:item:edit")
    @Log(title = "待办事项", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PlTodoItem plTodoItem)
    {
        return toAjax(plTodoItemService.updatePlTodoItem(plTodoItem));
    }

    /**
     * 删除待办事项
     */
    @RequiresPermissions("pl:item:remove")
    @Log(title = "待办事项", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(plTodoItemService.deletePlTodoItemByIds(ids));
    }
}

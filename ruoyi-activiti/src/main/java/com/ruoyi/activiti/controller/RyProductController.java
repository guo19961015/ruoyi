package com.ruoyi.activiti.controller;

import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
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
import com.ruoyi.activiti.domain.RyProduct;
import com.ruoyi.activiti.service.IRyProductService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.validation.constraints.Size;

/**
 * 产品Controller
 *
 * @author xiaoguo
 * @date 2020-09-25
 */
@Controller
@RequestMapping("/product")
public class RyProductController extends BaseController
{
    private String prefix = "product";

    @Autowired
    private IRyProductService ryProductService;
    @Autowired
    private ISysUserService iSysUserService;

    @RequiresPermissions("product:product:view")
    @GetMapping()
    public String product()
    {
        return prefix + "/product";
    }

    /**
     * 查询产品列表
     */
    @RequiresPermissions("product:product:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(RyProduct ryProduct)
    {
        startPage();
        if (!SysUser.isAdmin(ShiroUtils.getUserId()) && !SysUser.isSystem(ShiroUtils.getUserId())) {
            ryProduct.setUserId(String.valueOf(ShiroUtils.getUserId()));
        }
        List<RyProduct> list = ryProductService.selectRyProductList(ryProduct);
        if (list != null) {
            for (RyProduct product : list) {
                String userId = product.getUserId();
                if (userId != null) {
                    SysUser sysUser = iSysUserService.selectUserById(Long.valueOf(userId));
                    product.setUserName(sysUser.getUserName());
                }
            }
        }
        return getDataTable(list);
    }

    /**
     * 导出产品列表
     */
    @RequiresPermissions("product:product:export")
    @Log(title = "产品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(RyProduct ryProduct)
    {
        List<RyProduct> list = ryProductService.selectRyProductList(ryProduct);
        ExcelUtil<RyProduct> util = new ExcelUtil<RyProduct>(RyProduct.class);
        return util.exportExcel(list, "product");
    }

    /**
     * 新增产品
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存产品
     */
    @RequiresPermissions("product:product:add")
    @Log(title = "产品", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(RyProduct ryProduct)
    {
        return toAjax(ryProductService.insertRyProduct(ryProduct));
    }

    /**
     * 修改产品
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        RyProduct ryProduct = ryProductService.selectRyProductById(id);
        mmap.put("ryProduct", ryProduct);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品
     */
    @RequiresPermissions("product:product:edit")
    @Log(title = "产品", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(RyProduct ryProduct)
    {
        return toAjax(ryProductService.updateRyProduct(ryProduct));
    }

    /**
     * 删除产品
     */
    @RequiresPermissions("product:product:remove")
    @Log(title = "产品", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(ryProductService.deleteRyProductByIds(ids));
    }
}

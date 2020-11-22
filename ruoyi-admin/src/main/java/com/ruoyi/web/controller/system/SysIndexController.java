package com.ruoyi.web.controller.system;

import java.util.Enumeration;
import java.util.List;

import com.ruoyi.activiti.domain.PlComplaints;
import com.ruoyi.activiti.domain.PlServiceAgencyLoan;
import com.ruoyi.activiti.domain.RyProduct;
import com.ruoyi.activiti.mapper.PlServiceAgencyLoanMapper;
import com.ruoyi.activiti.service.IPlComplaintsService;
import com.ruoyi.activiti.service.IPlServiceAgencyLoanService;
import com.ruoyi.activiti.service.IRyProductService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysMenu;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysMenuService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 首页 业务处理
 *
 * @author ruoyi
 */
@Controller
public class SysIndexController extends BaseController
{
    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysConfigService configService;
    @Autowired
    private IPlComplaintsService plComplaintsService;
    @Autowired
    private IPlServiceAgencyLoanService iPlServiceAgencyLoanService;
    @Autowired
    private PlServiceAgencyLoanMapper plServiceAgencyLoanMapper;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap,String bankId,HttpServletRequest request)
    {
        // 取身份信息
        SysUser user = ShiroUtils.getSysUser();
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("sideTheme", configService.selectConfigByKey("sys.index.sideTheme"));
        mmap.put("skinName", configService.selectConfigByKey("sys.index.skinName"));
        mmap.put("copyrightYear", Global.getCopyrightYear());
        mmap.put("demoEnabled", Global.isDemoEnabled());
        HttpSession session = request.getSession();
        if (bankId != null) {
            session.setAttribute("bankId",bankId);
        }

        return "index";
    }

    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin(ModelMap mmap)
    {
        return "skin";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        mmap.put("version", Global.getVersion());
//        return "main";
        return "main_v2";
    }

    // 投诉意见
    @GetMapping("/complaints")
    public String complaints(ModelMap mmap,HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        Long productId =  (Long)session.getAttribute("productIdFlage");
        mmap.put("productId", productId);
        return "complaints";
    }
    // 投诉意见保存
    @Log(title = "投诉意见", businessType = BusinessType.INSERT)
    @PostMapping("/complaintsSave")
    @ResponseBody
    public AjaxResult complaintsSave(PlComplaints plComplaints, HttpServletRequest request)
    {
        return AjaxResult.success(plComplaintsService.insertPlComplaints(plComplaints));
    }
    // 查询当前用户是否有该业务
    @GetMapping("/selectComplaints")
    @ResponseBody
    public AjaxResult selectComplaints(ModelMap mmap,HttpServletRequest request)
    {
        AjaxResult ajaxResult = new AjaxResult();
        HttpSession session = request.getSession();
        // 判断当前用户是否登录
        SysUser sysUser = ShiroUtils.getSysUser();
        if (sysUser == null) {
            ajaxResult.put("success","login");
        }else {
            Long productId =  (Long)session.getAttribute("productIdFlage");
            PlServiceAgencyLoan plServiceAgencyLoan = new PlServiceAgencyLoan();
            if (productId != null) {
                plServiceAgencyLoan.setUserId(sysUser.getUserId());
                plServiceAgencyLoan.setProductId(String.valueOf(productId));
            }
            //单表查询pl_service_agency_loan
            List<PlServiceAgencyLoan> plServiceAgencyLoans = plServiceAgencyLoanMapper.selectPlServiceAgencyLoanListDouble(plServiceAgencyLoan);
            if (plServiceAgencyLoans != null && plServiceAgencyLoans.size()>0) {
                ajaxResult.put("success","true");
            }else {
                ajaxResult.put("success","false");
            }
        }


        return ajaxResult;
    }

}

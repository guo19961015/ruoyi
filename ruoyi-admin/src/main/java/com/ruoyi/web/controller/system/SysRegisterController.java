package com.ruoyi.web.controller.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.activiti.domain.PlComment;
import com.ruoyi.activiti.domain.PlGuaranteeLoan;
import com.ruoyi.activiti.domain.PlGuaranteeLoanVo;
import com.ruoyi.activiti.domain.RyProduct;
import com.ruoyi.activiti.service.IPlGuaranteeLoanService;
import com.ruoyi.activiti.service.IPlServiceAgencyLoanService;
import com.ruoyi.activiti.service.IRyProductService;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.sql.SqlUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.framework.web.service.DictService;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.service.*;
import com.ruoyi.system.utils.PageInfoUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.framework.shiro.service.SysRegisterService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 注册验证
 *
 * @author ruoyi
 */
@Controller
public class SysRegisterController extends BaseController
{
    @Autowired
    private SysRegisterService registerService;

    @Autowired
    private ISysConfigService configService;
    @Autowired
    private ISysDeptService deptService;
    @Autowired
    private IPlBankService plBankService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private DictService dictService;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private ISysUserService iSysUserService;
    @Autowired
    private ISysRoleService iSysRoleService;
    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private IRyProductService ryProductService;
    @Autowired
    private IPlGuaranteeLoanService plGuaranteeLoanService;

    @Autowired
    private IPlServiceAgencyLoanService plServiceAgencyLoanService;

    @GetMapping("/register")
    public String register(String bankId, ModelMap mmap)
    {
        mmap.put("bankId",bankId);

        return "register";
    }
    @GetMapping("/clause")
    public String clause(String bankId, ModelMap mmap)
    {
        return "clause";
    }
    @PostMapping("/register")
    @ResponseBody
    public AjaxResult ajaxRegister(SysUser user)
    {
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser"))))
        {
            return error("当前系统没有开启注册功能！");
        }
        String msg = registerService.register(user);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }


    /**
     * 选择部门树
     */
    @GetMapping("/selectDeptTree1/{deptId}")
    public String selectDeptTree(@PathVariable("deptId") Long deptId, ModelMap mmap)
    {
        mmap.put("dept", deptService.selectDeptById(deptId));
        return "tree";
    }
    /**
     * 加载部门列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = deptService.selectDeptTree(new SysDept());
        return ztrees;
    }
    @GetMapping("/home")
    public String homepage(ModelMap mmap)
    {
        return "home";
    }
    @GetMapping("/list")
    public String list(ModelMap mmap)
    {
        return "list";
    }

    @CrossOrigin
    @GetMapping("/homeAjax")
    @ResponseBody
    public List<PlBank>  homeAjax(ModelMap mmap)
    {
        PlBank plBank = new PlBank();
        plBank.setStatus("0");
        return plBankService.selectPlBankList(plBank);
    }
    @CrossOrigin
    @GetMapping("/details")
    public String details(String bankId,ModelMap map)
    {
        map.put("plbank",plBankService.selectPlBankById(Long.valueOf(bankId)));
        return "details";
    }
    @GetMapping("/mechanismRegister")
    public String mechanismRegister(String bankId, ModelMap mmap)
    {
        mmap.put("bankId",bankId);
        return "mechanismRegister";
    }

    @PostMapping("/mechanismRegister")
    @ResponseBody
    public AjaxResult ajaxMechanismRegister(@RequestBody SysUser user)
    {
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser"))))
        {
            return error("当前系统没有开启注册功能！");
        }
        user.setStatus("4");
        String msg = registerService.register(user);
        Long userId = user.getUserId();
        int i = 0;
        if (userId != null) {
            String[] ryProductList = user.getRyProductList();
            if (ryProductList != null) {
                for (String s : ryProductList) {
                    RyProduct ryProduct = new RyProduct();
                    ryProduct.setIntroduce(s);
                    ryProduct.setUserId(String.valueOf(user.getUserId()));
                    ryProduct.setServiceProductsId(user.getServiceProducts().split(",")[i]);
                    ryProductService.insertRyProduct(ryProduct);
                    i++;
                }
            }
        }
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }

    @CrossOrigin
    @GetMapping("/service")
    public String service(String serviceId,ModelMap map)
    {

        map.put("serviceId",serviceId);
        map.put("fontText",iSysRoleService.selectRoleById(Long.valueOf(serviceId)));
        // 成功案例
        PlGuaranteeLoanVo plGuaranteeLoan = new PlGuaranteeLoanVo();
        plGuaranteeLoan.setStatus("1");
        List<PlGuaranteeLoanVo> plGuaranteeLoanVos = plGuaranteeLoanService.selectPlGuaranteeLoanList(plGuaranteeLoan);
        return "service";
    }

    @PostMapping("/serviceAjax")
    @ResponseBody
    public  AjaxResult serviceAjax(String serviceId,ModelMap map,Integer pageNum, Integer pageSize)
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
        pageNum = pageNum == null ? 1: pageNum;
        PageInfo<SysUser> pageInfo = PageInfoUtils.listPageInfo(usersList, pageNum, 8);
        // 手动清理分页缓存，不然会出现页面数据时好时坏
        PageHelper.clearPage();
        AjaxResult ajax = AjaxResult.success();
        ajax.put("pageInfo",pageInfo);
        ajax.put("serviceId",serviceId);
        return ajax;
    }

    @PostMapping("/bankAjax")
    @ResponseBody
    public  AjaxResult bankAjax(ModelMap map,Integer pageNum, Integer pageSize)
    {
        PlBank plBank = new PlBank();
        List<PlBank> plBanksList = plBankService.selectPlBankList(plBank);

        pageNum = pageNum == null ? 1: pageNum;
        PageInfo<PlBank> pageInfo = PageInfoUtils.listPageInfo(plBanksList, pageNum, 8);
        // 手动清理分页缓存，不然会出现页面数据时好时坏
        PageHelper.clearPage();
        AjaxResult ajax = AjaxResult.success();
        ajax.put("pageInfo",pageInfo);
        return ajax;
    }
    @CrossOrigin
    @GetMapping("/serviceConten")
    public String serviceConten(Long userId, Long productId,Long serviceId, ModelMap map, HttpServletRequest request)
    {
        SysUser sysUser = new SysUser();
        HttpSession session = request.getSession();
        if(userId != null && productId != null){
             sysUser = iSysUserService.selectUserRoleProductsId(userId,productId);
             // 将userId以及产品id传入session，申请服务时需要使用，自动选择用户所选产品
            session.setAttribute("userIdFlage",userId);
            session.setAttribute("productIdFlage",productId);
            session.setAttribute("serviceIdFlage",serviceId);
        }
        sysUser.setProductId(productId);
        // 查看评论
        List<PlComment> plComments = plServiceAgencyLoanService.selectComment(productId);
        map.put("sysUser",sysUser);
        map.put("plComments",plComments);
        return "serviceConten";
    }
    /*跨域获取当前用户登录信息*/
    @CrossOrigin
    @GetMapping("/userInformation")
    @ResponseBody
    public AjaxResult userInformation()
    {
        // 获取当前的用户信息
        SysUser currentUser = ShiroUtils.getSysUser();
        AjaxResult ajax = AjaxResult.success();
        if (currentUser != null) {
            ajax.put("currentUser",currentUser);
        }
        return ajax;
    }
    @GetMapping("/wizard")
    public String wizard(ModelMap mmap)
    {
        return "wizard";
    }
    /**
     * 通用上传请求
     */
    @PostMapping("register/common/upload")
    @ResponseBody
    public AjaxResult uploadFile(MultipartFile file) throws Exception
    {
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


}

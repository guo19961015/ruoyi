package com.ruoyi.web.controller.system;

import com.alibaba.druid.support.json.JSONUtils;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.core.text.Convert;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import oshi.util.StringUtil;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 个人信息 业务处理
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/user/profile")
public class SysProfileController extends BaseController
{
    private static final Logger log = LoggerFactory.getLogger(SysProfileController.class);

    private String prefix = "system/user/profile";

    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPasswordService passwordService;
    @Autowired
    private ServerConfig serverConfig;
    /**
     * 个人信息
     */
    @GetMapping()
    public String profile(ModelMap mmap)
    {
        SysUser user = ShiroUtils.getSysUser();
        mmap.put("user", user);
        mmap.put("roleGroup", userService.selectUserRoleGroup(user.getUserId()));
        mmap.put("postGroup", userService.selectUserPostGroup(user.getUserId()));
        return prefix + "/profile";
    }
    @GetMapping("/serviceUser")
    public String serviceUser()
    {
        return  prefix + "/serviceUser";
    }

    @GetMapping("/profileEdite/{userId}")
    public String profileEdite(@PathVariable("userId") Long userId,ModelMap mmap, String[] split)
    {
        SysUser sysUser = userService.selectUserById(userId);
        // 其它资质上传String转list
        String other = sysUser.getOther();
        LinkedList<HashMap<String, String>> hashMaps = new LinkedList<>();
        if (other != null) {
            mmap.put("other", other);
            String[] split1 = other.split(",");
            HashMap<String, String> map = new HashMap<String, String>();
            for (int i = 0; i< split1.length ;i++) {
                map.put("caption", split1[i].substring(split1[i] .lastIndexOf("/")+1));
                map.put("url",split1[i]);
                hashMaps.add(map);
            }
        }

        mmap.put("hashMaps", hashMaps);
        mmap.put("user", sysUser);

        mmap.put("roleGroup", userService.selectUserRoleGroup(sysUser.getUserId()));
        mmap.put("postGroup", userService.selectUserPostGroup(sysUser.getUserId()));
        return prefix + "/profileEdite";
    }
    /**
     * 注册时企业log
     */
    @GetMapping("/avatarRegisterEdite")
    public String avatarRegisterEdite(ModelMap mmap)
    {

        return prefix + "/avatarRegisterEdite";
    }
    /**
     * 修改企业log
     */
    @GetMapping("/avatarEdite/{userId}")
    public String avatarEdite(@PathVariable("userId") Long userId,ModelMap mmap)
    {
        mmap.put("user", userService.selectUserById(userId));
        mmap.put("userId", userId);
        return prefix + "/avatarEdite";
    }

    /**
     * 保存头像
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PostMapping("/updateAvatarEdite")
    @ResponseBody
    public AjaxResult updateAvatarEdite(@RequestParam("avatarfile") MultipartFile file,@RequestParam("userId") Long userId)
    {
        SysUser currentUser = new SysUser();
        currentUser.setUserId(userId);
        try
        {
            if (!file.isEmpty())
            {
                String avatar = FileUploadUtils.upload(Global.getAvatarPath(), file);
                currentUser.setOrganizationPicture(avatar);
                if (userService.updateUserInfo(currentUser) > 0)
                {
                    /*ShiroUtils.setSysUser(userService.selectUserById(currentUser.getUserId()));*/
                    return success();
                }
            }
            return error();
        }
        catch (Exception e)
        {
            log.error("修改头像失败！", e);
            return error(e.getMessage());
        }
    }

    @GetMapping("/checkPassword")
    @ResponseBody
    public boolean checkPassword(String password)
    {
        SysUser user = ShiroUtils.getSysUser();
        if (passwordService.matches(user, password))
        {
            return true;
        }
        return false;
    }

    @GetMapping("/resetPwd")
    public String resetPwd(ModelMap mmap)
    {
        SysUser user = ShiroUtils.getSysUser();
        mmap.put("user", userService.selectUserById(user.getUserId()));
        return prefix + "/resetPwd";
    }

    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwd(String oldPassword, String newPassword)
    {
        SysUser user = ShiroUtils.getSysUser();
        if (StringUtils.isNotEmpty(newPassword) && passwordService.matches(user, oldPassword))
        {
            user.setSalt(ShiroUtils.randomSalt());
            user.setPassword(passwordService.encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
            if (userService.resetUserPwd(user) > 0)
            {
                ShiroUtils.setSysUser(userService.selectUserById(user.getUserId()));
                return success();
            }
            return error();
        }
        else
        {
            return error("修改密码失败，旧密码错误");
        }
    }

    /**
     * 修改用户
     */
    @GetMapping("/edit")
    public String edit(ModelMap mmap)
    {
        SysUser user = ShiroUtils.getSysUser();
        mmap.put("user", userService.selectUserById(user.getUserId()));
        return prefix + "/edit";
    }
    /**
     * 其它资质删除指定的图片
     */
    @RequiresPermissions({"otherDelete"})
    @PostMapping("/otherDelete")
    @ResponseBody
    public String otherDelete(@RequestParam("key") String key,@RequestParam("userId") Long userId,@RequestParam("other") String other,ModelMap mmap)
    {
        SysUser sysUser = userService.selectUserById(userId);
        String otherValues = "";
        ArrayList lists = new ArrayList();
        if (sysUser != null) {
            String otherList = sysUser.getOther();
            if (otherList != null) {
                lists = new ArrayList(Arrays.asList(otherList.split(",")));
                for (int i = 0; i < lists.size(); i++) {
                    boolean b = lists.get(i).toString().contains(other);
                    if(b){
                        lists.remove(i);
                    }
                }
            }
        }
        System.out.println(111);
        return prefix + "/profileEdite";
    }
    /**
     * 修改头像
     */
    @GetMapping("/avatar")
    public String avatar(ModelMap mmap)
    {
        SysUser user = ShiroUtils.getSysUser();
        mmap.put("user", userService.selectUserById(user.getUserId()));
        return prefix + "/avatar";
    }

    /**
     * 修改用户
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(SysUser user)
    {
        SysUser currentUser = ShiroUtils.getSysUser();
        currentUser.setUserName(user.getUserName());
        currentUser.setEmail(user.getEmail());
        currentUser.setPhonenumber(user.getPhonenumber());
        currentUser.setSex(user.getSex());
        if (userService.updateUserInfo(currentUser) > 0)
        {
            ShiroUtils.setSysUser(userService.selectUserById(currentUser.getUserId()));
            return success();
        }
        return error();
    }

    /**
     * 保存头像
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PostMapping("/updateAvatar")
    @ResponseBody
    public AjaxResult updateAvatar(@RequestParam("avatarfile") MultipartFile file)
    {
        SysUser currentUser = ShiroUtils.getSysUser();
        try
        {
            if (!file.isEmpty())
            {
                String avatar = FileUploadUtils.upload(Global.getAvatarPath(), file);
                currentUser.setAvatar(avatar);
                if (userService.updateUserInfo(currentUser) > 0)
                {
                    ShiroUtils.setSysUser(userService.selectUserById(currentUser.getUserId()));
                    return success();
                }
            }
            return error();
        }
        catch (Exception e)
        {
            log.error("修改头像失败！", e);
            return error(e.getMessage());
        }
    }
}

package com.ruoyi.activiti.service.impl;

import java.util.List;

import com.ruoyi.activiti.domain.PlLoanRole;
import com.ruoyi.activiti.mapper.PlLoanRoleMapper;
import com.ruoyi.activiti.service.IPlLoanRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.core.text.Convert;

/**
 * 服务角色表Service业务层处理
 * 
 * @author xiaoguo
 * @date 2020-09-22
 */
@Service
public class PlLoanRoleServiceImpl implements IPlLoanRoleService
{
    @Autowired
    private PlLoanRoleMapper plLoanRoleMapper;

    /**
     * 查询服务角色表
     * 
     * @param id 服务角色表ID
     * @return 服务角色表
     */
    @Override
    public PlLoanRole selectPlLoanRoleById(Long id)
    {
        return plLoanRoleMapper.selectPlLoanRoleById(id);
    }

    /**
     * 查询服务角色表列表
     * 
     * @param plLoanRole 服务角色表
     * @return 服务角色表
     */
    @Override
    public List<PlLoanRole> selectPlLoanRoleList(PlLoanRole plLoanRole)
    {
        return plLoanRoleMapper.selectPlLoanRoleList(plLoanRole);
    }

    /**
     * 新增服务角色表
     * 
     * @param plLoanRole 服务角色表
     * @return 结果
     */
    @Override
    public int insertPlLoanRole(PlLoanRole plLoanRole)
    {
        return plLoanRoleMapper.insertPlLoanRole(plLoanRole);
    }

    /**
     * 修改服务角色表
     * 
     * @param plLoanRole 服务角色表
     * @return 结果
     */
    @Override
    public int updatePlLoanRole(PlLoanRole plLoanRole)
    {
        return plLoanRoleMapper.updatePlLoanRole(plLoanRole);
    }

    /**
     * 删除服务角色表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePlLoanRoleByIds(String ids)
    {
        return plLoanRoleMapper.deletePlLoanRoleByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除服务角色表信息
     * 
     * @param id 服务角色表ID
     * @return 结果
     */
    @Override
    public int deletePlLoanRoleById(Long id)
    {
        return plLoanRoleMapper.deletePlLoanRoleById(id);
    }
}

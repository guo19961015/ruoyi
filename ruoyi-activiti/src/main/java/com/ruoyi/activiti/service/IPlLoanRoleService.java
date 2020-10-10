package com.ruoyi.activiti.service;

import com.ruoyi.activiti.domain.PlLoanRole;

import java.util.List;

/**
 * 服务角色表Service接口
 * 
 * @author xiaoguo
 * @date 2020-09-22
 */
public interface IPlLoanRoleService 
{
    /**
     * 查询服务角色表
     * 
     * @param id 服务角色表ID
     * @return 服务角色表
     */
    public PlLoanRole selectPlLoanRoleById(Long id);

    /**
     * 查询服务角色表列表
     * 
     * @param plLoanRole 服务角色表
     * @return 服务角色表集合
     */
    public List<PlLoanRole> selectPlLoanRoleList(PlLoanRole plLoanRole);

    /**
     * 新增服务角色表
     * 
     * @param plLoanRole 服务角色表
     * @return 结果
     */
    public int insertPlLoanRole(PlLoanRole plLoanRole);

    /**
     * 修改服务角色表
     * 
     * @param plLoanRole 服务角色表
     * @return 结果
     */
    public int updatePlLoanRole(PlLoanRole plLoanRole);

    /**
     * 批量删除服务角色表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePlLoanRoleByIds(String ids);

    /**
     * 删除服务角色表信息
     * 
     * @param id 服务角色表ID
     * @return 结果
     */
    public int deletePlLoanRoleById(Long id);
}

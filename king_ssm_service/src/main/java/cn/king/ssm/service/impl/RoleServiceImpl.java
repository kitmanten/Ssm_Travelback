package cn.king.ssm.service.impl;

import cn.king.ssm.dao.RoleDao;
import cn.king.ssm.domain.Permission;
import cn.king.ssm.domain.Role;
import cn.king.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//角色业务层
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    //查询所有角色
    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    //添加角色
    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    //查询角色详情
    @Override
    public Role findById(String roleId) throws Exception {
        return roleDao.findById(roleId);
    }

    //查询未关联的角色
    @Override
    public List<Permission> findOtherPermission(String roleId) throws Exception {
        return roleDao.findOtherPermission(roleId);
    }

    //添加关联角色
    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) throws Exception {
        for (String permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}

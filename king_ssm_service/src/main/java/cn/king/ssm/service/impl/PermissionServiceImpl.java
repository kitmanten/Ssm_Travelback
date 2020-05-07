package cn.king.ssm.service.impl;

import cn.king.ssm.dao.PermissionDao;
import cn.king.ssm.domain.Permission;
import cn.king.ssm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//权限业务层
@Service("permissionService")
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    //查询所有权限
    @Override
    public List<Permission> findALl() throws Exception {
        return permissionDao.finAll();
    }

    //权限添加
    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }
}

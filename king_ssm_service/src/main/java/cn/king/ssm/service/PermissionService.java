package cn.king.ssm.service;

import cn.king.ssm.domain.Permission;

import java.util.List;

public interface PermissionService {

    public List<Permission> findALl() throws Exception;

    public void save(Permission permission) throws Exception;
}

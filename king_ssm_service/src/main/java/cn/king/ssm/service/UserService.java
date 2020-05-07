package cn.king.ssm.service;

import cn.king.ssm.domain.Role;
import cn.king.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {


    public List<UserInfo> finaAll() throws Exception;

    public void save(UserInfo userInfo) throws Exception;

    public UserInfo findById(String id) throws Exception;

    public List<Role> findOtherRoles(String userId) throws Exception;

    void addRoleToUser(String userId, String[] roleIds) throws Exception;
}


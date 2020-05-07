package cn.king.ssm.dao;

import cn.king.ssm.domain.Permission;
import cn.king.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleDao {

    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "cn.king.ssm.dao.PermissionDao.findByRoleId"))
    })
    public List<Role> findByUserId(String userId) throws Exception;

    @Select("select * from role")
    List<Role> findAll() throws Exception;

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc}) ")
    void save(Role role) throws Exception;

    @Select("select * from role where id=#{roleId}")
    Role findById(String roleId) throws Exception;

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermission(String roleId) throws Exception;

    @Insert("insert into role_permission values(#{permissionId},#{roleId},)")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);

}

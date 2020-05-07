package cn.king.ssm.controller;

import cn.king.ssm.domain.Permission;
import cn.king.ssm.domain.Role;
import cn.king.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//角色控制器
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    //用户添加角色
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId" ,required = true)String roleId,@RequestParam(name = "ids",required = true)String[] permissionIds ) throws Exception {
        roleService.addPermissionToRole(roleId,permissionIds);

        return "redirect:findAll.do";
    }
    //查询可添加的角色
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true)String roleId) throws Exception {
        ModelAndView mv = new ModelAndView();
        //根据roleId查询角色
         Role role = roleService.findById(roleId);
        //根据roleId查询可以添加的权限
        List<Permission> permissions = roleService.findOtherPermission(roleId);
        mv.addObject("role",role);
        mv.addObject("permissionList",permissions);
        mv.setViewName("role-permission-add");
        return mv;
    }

    //查询角色
    @RequestMapping("/finAll.do")
    public ModelAndView finAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Role> roles = roleService.findAll();
        mv.addObject("roleList",roles);
        mv.setViewName("role-list");
        return mv;
    }
    //添加角色
    @RequestMapping("/save.do")
    public String save(Role role) throws Exception {
        roleService.save(role);
        return "redirect:findAll.do";
    }
}

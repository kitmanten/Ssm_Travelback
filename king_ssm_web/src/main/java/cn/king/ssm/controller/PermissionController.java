package cn.king.ssm.controller;

import cn.king.ssm.domain.Permission;
import cn.king.ssm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
//权限控制器
@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    //添加权限
    @RequestMapping("save.dao")
    public String save(Permission permission) throws Exception {
        permissionService.save(permission);
        return "redirect:findAll.do";
    }

    //查询权限
    @RequestMapping("findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissions = permissionService.findALl();
        mv.addObject("permissionList",permissions);
        mv.setViewName("permission-list");
        return mv;
    }
}

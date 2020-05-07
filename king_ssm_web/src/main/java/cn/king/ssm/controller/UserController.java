package cn.king.ssm.controller;

import cn.king.ssm.domain.Role;
import cn.king.ssm.domain.UserInfo;
import cn.king.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * 用户控制器
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    //添加用户的角色
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,@RequestParam(name = "ids",required = true) String[] roleIds) throws Exception {
        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }
    //查询可以添加的角色
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole (@RequestParam(name = "id",required = true)String userId) throws Exception {
        ModelAndView mv = new ModelAndView();
        //根据用户id查询用户
        UserInfo userInfo = userService.findById(userId);
        //根据用户id查询可以添加的角色
        List<Role> otherRoles = userService.findOtherRoles(userId);
        mv.addObject("user",userInfo);
        mv.addObject("rolelist",otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }
    //查询用户的详情
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }
    //添加新的用户
    @RequestMapping("/save.do")
    @RolesAllowed("ADMIN")
    public String saveUser(UserInfo userInfo) throws Exception {
        userService.save(userInfo);
        return "redirect:findAll.do";
    }
    //查询所有用户
    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
       List<UserInfo> userList = userService.finaAll();
       mv.addObject("userList",userList);
       mv.setViewName("user-list");
        return mv;
    }
}

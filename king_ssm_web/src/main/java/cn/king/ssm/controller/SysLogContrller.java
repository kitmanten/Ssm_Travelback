package cn.king.ssm.controller;

import cn.king.ssm.domain.SysLog;
import cn.king.ssm.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.crypto.dsig.XMLValidateContext;
import java.util.List;
//日志控制器
@Controller
@RequestMapping("/sysLog")
public class SysLogContrller {
    @Autowired
    private SysLogService sysLogService;

    //查询日志
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<SysLog> sysLogs = sysLogService.findAll();
        mv.addObject("sysLogs",sysLogs);
        mv.setViewName("syslog-list");
        return mv;
    }
}

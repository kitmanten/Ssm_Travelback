package cn.king.ssm.controller;

import cn.king.ssm.domain.SysLog;
import cn.king.ssm.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

//记录日志信息
@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogService sysLogService;

    private Date visitTime;//执行开始时间
    private Class clazz;//执行的类
    private Method method;//执行的方法

    //前置通知:获取执行开始时间、执行的类、执行的方法
    @Before("execution(* cn.king.ssm.controller.*.*(..))")
    public void doBefore (JoinPoint joinPoint) throws NoSuchMethodException {
        visitTime = new Date();//当前访问的时间
        clazz = joinPoint.getTarget().getClass();//执行的类
        String methodName = joinPoint.getSignature().getName();//执行的方法名
        Object[] args = joinPoint.getArgs();//方法的参数列表
        //获取执行方法
        if (args == null||args.length == 0){
            method = clazz.getMethod(methodName);//获取执行的无参方法
        }else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName,classArgs);
        }
    }

    //后置通知
    @After("execution(* cn.king.ssm.controller.*.*(..))")
    public void doAfter (JoinPoint joinPoint) throws Exception {
        //获取访问时长
        long time = new Date().getTime() - visitTime.getTime();

        String url = "" ;
        //获取url
        if (clazz != null && method != null && clazz != LogAop.class){
            //获取requestMapping的值
            RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (clazzAnnotation != null) {
                String[] classValue = clazzAnnotation.value();

                //获取method的url
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null){
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];


                    //获取访问ip
                    String ip = request.getRemoteAddr();

                    //获取用户
                    SecurityContext context = SecurityContextHolder.getContext();//从上下文获取当前登录的用户
                    User user = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();


                    //封装日志
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setIp(ip);
                    sysLog.setUsername(username);
                    sysLog.setUrl(url);
                    sysLog.setMethod("[类名] "+clazz.getName()+"[方法名] "+method.getName());
                    sysLog.setVisitTime(visitTime);

                    //添加数据库
                    sysLogService.save(sysLog);
                }

            }
        }

    }
}

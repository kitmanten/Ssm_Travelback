package cn.king.ssm.service;

import cn.king.ssm.domain.SysLog;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SysLogService {


    public void save (SysLog sysLog)throws Exception;

    List<SysLog> findAll() throws Exception;

}

package cn.king.ssm.dao;


import cn.king.ssm.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;
//日志持久层
public interface SysLogDao {

    //日志存储
    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog) throws Exception;
    //查询日志
    @Select("select * from syslog")
    List<SysLog> findAll() throws Exception;
}

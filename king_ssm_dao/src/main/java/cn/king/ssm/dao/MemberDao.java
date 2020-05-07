package cn.king.ssm.dao;

import org.apache.ibatis.annotations.Select;

import java.lang.reflect.Member;

public interface MemberDao  {

    @Select("select * from member where id=#{memberId}")
    public Member findById(String memberId) throws Exception;
}

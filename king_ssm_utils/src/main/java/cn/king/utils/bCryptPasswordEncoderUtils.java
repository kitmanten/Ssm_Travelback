package cn.king.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//密码加密
public class bCryptPasswordEncoderUtils {

    public static BCryptPasswordEncoder bCryptPasswordEncoder;

    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }
}

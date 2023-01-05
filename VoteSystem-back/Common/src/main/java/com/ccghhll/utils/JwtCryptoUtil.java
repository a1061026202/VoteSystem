package com.ccghhll.utils;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSignerUtil;
import org.springframework.util.StringUtils;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtCryptoUtil {
    // 设置token有效期
    private static long tokenExpiration = 60*60*24*15*1000;//60*60*24*15*1000;
    // 密钥
    private static String tokenSign = "HG36s36d2KD6d";


    public static String createToken(String account){
        byte[] key = DatatypeConverter.parseBase64Binary(tokenSign);

        //拼接参数
        Map<String, Object> payLoad = new HashMap<String, Object>();
        payLoad.put("account",account);
        payLoad.put(JWTPayload.EXPIRES_AT,new Date(System.currentTimeMillis()+tokenExpiration));
        payLoad.put(JWTPayload.ISSUED_AT,new Date());
        payLoad.put(JWTPayload.NOT_BEFORE,new Date());

        String token = JWTUtil.createToken(payLoad,JWTSignerUtil.hs256(key));
        return token;
    }

    /**
     * 判断token是否有效
     * @param token
     * @return
     */
    public static boolean checkToken(String token){
        if(StringUtils.isEmpty(token)) {
            return false;
        }
        byte[] key = DatatypeConverter.parseBase64Binary(tokenSign);
        // 检验是否过期或者是否验签成功
        try {
            JWT jwt = JWTUtil.parseToken(token).setSigner(JWTSignerUtil.hs256(key));
            if(jwt.validate(0)){
                return true;
            };
        }catch (Exception e){
            return false;
        }
        return false;
    }

    public static JWTPayload getPayload(String token){
        if(checkToken(token)){
            JWTPayload payload = JWTUtil.parseToken(token).getPayload();
            return payload;
        }
        return null;
    }


}

package com.lhrlyn.cn.lhrlynadmin.user.util.tokenJWT;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *  jwt工具类
 * @author lhr
 * @date  2021/12/30 10:49 PM
 * @param
 * @return:
 */
public class JwtUtil {

    /**
     * 过期时间
     **/
    private static final long EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;
    /**
     * 私钥
     **/
    private static final String TOKEN_SECRET = "privateKey";

    /**
     *  生成签名一周后过期
     * @author lhr
     * @date  2021/12/30 10:20 PM
     * @param
     * @return:
     */
    public static String sign(Integer userId) {
        try{
            // 设置过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            // 私钥的加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String,Object> header = new HashMap<>();
            header.put("Type","Jwt");
            header.put("alg","HS256");
            // 返回token字符串
            return JWT.create()
                    .withHeader(header)
                    .withClaim("userId", userId)
                    .withExpiresAt(date)
                    .sign(algorithm);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     *  检验token是否正确
     * @author lhr
     * @date  2021/12/30 10:45 PM
     * @param
     * @return:
     */
    public static int verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            int userId = jwt.getClaim("userId").asInt();
            return userId;
        }catch (Exception e ) {
            return 0;
        }
    }
}

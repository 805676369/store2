package cn.tedu.store2.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {
	/**
	 * 过期时间为15分钟
	 */
	private static final long EXPIRE_TIME=15*60*1000;

	/**
	 * token私密钥匙
	 */
	private static final String TOKEN_SECRET="abcf34345jijfiejfiejf";

	/**
	 * 
	 */
	public static String sign(String username,String userId) {
		try {
			//过期时间
			Date date=new Date(System.currentTimeMillis()+EXPIRE_TIME);
			//加密
			Algorithm algorithm=Algorithm.HMAC256(TOKEN_SECRET);
			//设置头部信息
			Map<String, Object> headerMap=new HashMap<String, Object>(2);
			headerMap.put("typ", "JWT");
			headerMap.put("alg", "Hs256");
			//附带username，userId信息，生成签名
			return JWT.create()
					.withHeader(headerMap)
					.withClaim("loginName", username)
					.withClaim("userId", userId)
					.withExpiresAt(date)
					.sign(algorithm)
					;


		} catch (UnsupportedEncodingException e) {
			return null;
		}






	}

	public static boolean verify(String token) {
		try {
			Algorithm algorithm=Algorithm.HMAC256(TOKEN_SECRET);
			JWTVerifier verifier=JWT.require(algorithm)
					.build();
			DecodedJWT jwt=verifier.verify(token);
			return true;

		} catch (Exception e) {
			return false;
		}

	}
}

package com.expo.expoapp.config.jwt;

import io.jsonwebtoken.Jwts;
import javax.crypto.SecretKey;

public class JWT {
	public static String AUTH_HEADER = "Authorization";
	public static String BEARER_TOKEN = "Bearer ";
	public static final SecretKey LLAVE_SECRETA = Jwts.SIG.HS256.key().build();
	public static String CONTENT_TYPE = "application/json";
}

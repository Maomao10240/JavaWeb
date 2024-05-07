package com.example.testproject.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static String signKey = "maohua";
    private static Long expire = 43200000L;

    public static String generateJwt(Map<String, Object> claims) {
        System.out.println("jwt");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, signKey)//签名算法
                .setClaims(claims)//self defined content
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
        System.out.println(jwt);
        return jwt;
    }

    public static Claims parseJwt(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt).getBody();
        return claims;
    }
}

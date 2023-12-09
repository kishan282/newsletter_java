package com.nks.identity.service.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private final static String SECRET = "nUOY4ccjnXCTwA6ikOFN5AyQULJBxT0fg+3ELHImxhivvOGK8gnIMUh36unsEECCnsaPBwJIMBgXTI+arscLgBMy1NTDmy9AUuIdLnPhHYWs34z47XROAbmLBHEeQj/lbH6sI+uusmyRClBPnRjeOxgwpO8EB9u24EgE9pp5foNMcbY1vA01W2e/o8frD0a+9eqNgvO1M5LDFFqYydahtLM4x68ozXtPnw15O+bbk25BfzHgXasP+tFR9pV7nVvW1XlFUXMLiJqiaA2i/IhenQ5qDj5R0fUiohPxygfoZ1kvlUx4zVQj0SDeTdpCx3B46gc703uYZpSBu/h83s09WsVsWIjklp6EvnDyGFf9nMs=";
	
	
	public String generateToken(String userName) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userName);
	}
	
	public void validatToken(final String token){
		Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
	}


	private String createToken(Map<String, Object> claims, String userName) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userName)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}


	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
}

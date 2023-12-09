package com.nks.admin.validation;

import java.security.Key;

import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {

	private final static String SECRET = "nUOY4ccjnXCTwA6ikOFN5AyQULJBxT0fg+3ELHImxhivvOGK8gnIMUh36unsEECCnsaPBwJIMBgXTI+arscLgBMy1NTDmy9AUuIdLnPhHYWs34z47XROAbmLBHEeQj/lbH6sI+uusmyRClBPnRjeOxgwpO8EB9u24EgE9pp5foNMcbY1vA01W2e/o8frD0a+9eqNgvO1M5LDFFqYydahtLM4x68ozXtPnw15O+bbk25BfzHgXasP+tFR9pV7nVvW1XlFUXMLiJqiaA2i/IhenQ5qDj5R0fUiohPxygfoZ1kvlUx4zVQj0SDeTdpCx3B46gc703uYZpSBu/h83s09WsVsWIjklp6EvnDyGFf9nMs=";

	private static String userid;

	private static Boolean tokeValid;

	public Boolean getTokeValid() {
		return tokeValid;
	}

	public static void setTokeValid(Boolean tokeValid) {
		JwtUtil.tokeValid = tokeValid;
	}

	public String extractUsername(String token) {
		setUserid(extractClaim(token, Claims::getSubject));
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token.replaceAll("[\\\\/]", ""))
				.getBody();
	}

	public Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		setTokeValid(username.equals(userDetails.getUsername()) && !isTokenExpired(token));
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	@Autowired
	public static String getUserid() {
		return userid;
	}

	public static void setUserid(String userid) {
		JwtUtil.userid = userid;
	}

}

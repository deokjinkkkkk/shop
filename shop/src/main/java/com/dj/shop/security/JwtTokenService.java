package com.dj.shop.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenService {
	private static final String SECRET_KEY = "your_secret_key";
	/*JWT email 에 따라 토큰을 생성*/
	public String generateToken(UserDetails userDetails) {
		Map<String,Object> claims = new HashMap<>();
		
		return createToken(claims,userDetails.getUsername()); 
	}
	/*받은 데이터를 통해 토큰을 생성 하는 메소드*/
	private String createToken(Map<String, Object> claims, String username) {
		
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 *60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY )
				.compact();
	}
	
	/*토큰의 유효성 검사를 함
	 * 1. 만료 여부
	 * 2. email 일치 여부
	 */
	public Boolean validateToken(String token, UserDetails userDetails) {
	    final String username = getUsernameFromToken(token);
	    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	/*토큰에서 email 주소를 가지고 온다 */
	
	public String getUsernameFromToken(String token) {
	    return getClaimFromToken(token, Claims::getSubject);
	}
	/*
	 * 주어진 토큰의 만료 날짜를 가지고 온다
	 */
	private Date getExpirationDateFromToken(String token) {
	    return getClaimFromToken(token, Claims::getExpiration);
	}
	/*특정 클레임을 추출 한다. */
	private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
	    final Claims claims = getAllClaimsFromToken(token);
	    return claimsResolver.apply(claims);
	}
	/*모든 클레임을 추출 한다. */
	private Claims getAllClaimsFromToken(String token) {
	    return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
	/*토큰이 만료 되었는지 확인한다.*/
	private Boolean isTokenExpired(String token) {
	    final Date expiration = getExpirationDateFromToken(token);
	    return expiration.before(new Date());
	}
}

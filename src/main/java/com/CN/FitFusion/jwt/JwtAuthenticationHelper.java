package com.CN.FitFusion.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtAuthenticationHelper {

	
	String secret = "thisIsSecrectKeyForJwtAuthenticationForGymApplicationnoowwwwwwwwwwwtogingintnntnsinthsihehsisizeeeeeeeeeeeeeeeeeeeeeeeeeee";
	
	private static final long JWT_PRIVATE_TOKEN_EXP = 60*60;

	public String getUsernameFromToken(String token) {
		Claims claim = getClaims(token);
		return claim.getSubject();
	}
	
	public Claims getClaims(String token) {
		
		Claims claims = Jwts.parserBuilder().setSigningKey(secret.getBytes()).build().parseClaimsJws(token).getBody();
		return claims;
	}
	
	public Boolean isTokenExpired(String token) {
		Claims claim = getClaims(token);
		Date expDate = claim.getExpiration();
		Date current = new Date();
		return expDate.before(current);
	}

	public String generateToken(UserDetails userDetails) {
		Map<String , Object> Claims = new HashMap<>();
		
		return Jwts.builder().addClaims(Claims)
		.setSubject(userDetails.getUsername())
		.setExpiration(new Date(System.currentTimeMillis() + JWT_PRIVATE_TOKEN_EXP*1000))
		.signWith(new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS512.getJcaName()), SignatureAlgorithm.HS512)
		.compact();
	}


}

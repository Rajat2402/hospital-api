package com.regency.api.configuration;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.regency.api.exception.DoctorException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

//This is the utility class for JWT configuration
@Component
public class JWTTokenProvider {

	@Value("${app.jwt.key}")
	public String key;

	@Value("${app.jwt.expiration-millis}")
	public long expiredDate;

	public String generatedToken(Authentication auth) {
		String name = auth.getName();
		Date currentDate = new Date();
		Date expDate = new Date(currentDate.getTime() + expiredDate);
		return Jwts.builder()
				.subject(name).signWith(Key()).issuedAt(currentDate).expiration(expDate).compact();
	}

	private Key Key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
	}

	public String getUserNameFromToken(String token) {
		return Jwts.parser().verifyWith((SecretKey) Key()).build().parseSignedClaims(token).getPayload().getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().verifyWith((SecretKey) Key()).build().parse(token);
			return true;
		} catch (MalformedJwtException e) {
			throw new DoctorException(HttpStatus.BAD_GATEWAY, "Invalid JWT Token");
		} catch (ExpiredJwtException e) {
			throw new DoctorException(HttpStatus.BAD_GATEWAY, "Expired JWT token");
		} catch (UnsupportedJwtException e) {
			throw new DoctorException(HttpStatus.BAD_GATEWAY, "Unsupported JWT token");
		} catch (IllegalArgumentException e) {
			throw new DoctorException(HttpStatus.BAD_GATEWAY, "Illegal argument passed");
		}
	}

}

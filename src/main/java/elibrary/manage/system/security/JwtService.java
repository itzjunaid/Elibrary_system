package elibrary.manage.system.security;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import elibrary.manage.system.entities.Admin;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private final String SECRET_KEY="4747e78e0514aeb0be8d6b674370bdd3be1e357471d05c1e9d4329176f4287f6";

	
	
	 public String extractUsername(String token) {
	        return extractClaim(token, Claims::getSubject);
	    }
	 
	 
	 public boolean isValid(String token, UserDetails admin) {
	        String username = extractUsername(token);
	        return (username.equals(admin.getUsername())) && !isTokenExpired(token);
	    }
	 
	 private boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }
	 
	 private Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }
	 	
	
	 public <T> T extractClaim(String token, Function<Claims, T> resolver) {
	        Claims claims = extractAllClaims(token);
	        return resolver.apply(claims);
	    }
	 
	
	 private Claims extractAllClaims(String token) {
	        return Jwts
	                .parser()
	                .verifyWith(getSigninKey())
	                .build()
	                .parseSignedClaims(token)
	                .getPayload();
	    }
	
	
	public String generateToken(Admin admin) {
        String token = Jwts
                .builder()
                .subject(admin.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 24*60*60*1000 ))
                .signWith(getSigninKey())
                .compact();

        return token;
    }
	
	

    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
	
	
	
	
	
	
	
}

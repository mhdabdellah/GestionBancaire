package RIM.Banque.GestionBancaire.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component 
public class JwtUtil {
	
	private static final String SECRET_KEY = "learn_programming_yourself";
	private static final int TOKEN_VALIDITY = 3600 * 5;
	
	public String getUserNameFromToken(String token) {
		return getClaimFromToken(token,Claims::getSubject);
	}

	private <T> T getClaimFromToken(String token, Function<Claims,T> claimResolver) {
		final Claims claims = getAllClaimsFormToken(token);
		return claimResolver.apply(claims);
	}

	private Claims getAllClaimsFormToken(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJwt(token).getBody();
	}
	
	
	public boolean validateToken(String token, UserDetails userDetails) {
		String userName = getUserNameFromToken(token); 
		
		return ( userName.equals(userDetails.getUsername()) && !isTokenExpired(token) );
	}
	
	private boolean isTokenExpired(String token) {
		final Date expirationDate = getExpirationDateFromToken(token);
		return expirationDate.before(new Date());
	}
	
	private Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	   // generate token
    public String generateToken(UserDetails userDetails){
    	Map<String, Object> claims = new HashMap<>();
        String username = userDetails.getUsername();
        Date currentDate = new Date();
        Date issuedAtDate = new Date(System.currentTimeMillis());
        Date expireDate = new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(issuedAtDate)
                .setExpiration(expireDate )
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
        return token;
    }

	
}

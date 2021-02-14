package tezea.si.utils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Contains all actions on JWT tokens
 * 
 * @author Nils Richard
 *
 */
@Component
public class JwtTokenUtil implements Serializable {
    
    Logger logger = LogManager.getLogger(getClass());
    
    private static final long serialVersionUID = -7588967588818311029L;
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    public static final long JWT_REFRESH_TOKEN_VALIDITY = 24 * 60 * 60;
    
    private enum TokenType {
        ACCESS_TOKEN,
        REFRESH_TOKEN
    }
    
    private abstract class Claim {
        private final static String TOKEN_TYPE = "token_type";
    }

    @Value("${jwt.secret}")
    private String secret;

    /**
     * Retrieves username from JWT token
     * 
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * Retrieves expiration date from JWT token
     * 
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Parses the JWT token with the secret key
     * 
     * @param token
     * @return
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * Checks if the token has expired
     * 
     * @param token
     * @return
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * Generates token for user
     * 
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(Claim.TOKEN_TYPE, TokenType.ACCESS_TOKEN);
        return doGenerateToken(claims, userDetails.getUsername(), JWT_TOKEN_VALIDITY);
    }

    /**
     * Generates token for user
     * 
     * @param userDetails
     * @return
     */
    public String generateRefreshToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(Claim.TOKEN_TYPE, TokenType.REFRESH_TOKEN);
        return doGenerateToken(claims, userDetails.getUsername(), JWT_REFRESH_TOKEN_VALIDITY);
    }

    /**
     * Generates JWT token
     * 
     * @param claims
     * @param subject
     * @param expiration expiration time in seconds
     * @return
     */
    private String doGenerateToken(Map<String, Object> claims, String subject, long expiration) {
//      While creating the token 
//      * 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
//      * 2. Sign the JWT using the HS512 algorithm and secret key.
//      * 3. According to JWS Compact
//      * Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
//      * compaction of the JWT to a URL-safe string
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * Validates token
     * 
     * @param token
     * @param userDetails
     * @return
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        final Claims claims = getAllClaimsFromToken(token);
        if(!claims.containsKey(Claim.TOKEN_TYPE) || !claims.get(Claim.TOKEN_TYPE).equals(TokenType.ACCESS_TOKEN.name())) return false;
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    
}
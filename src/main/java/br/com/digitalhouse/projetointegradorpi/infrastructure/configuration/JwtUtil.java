package br.com.digitalhouse.projetointegradorpi.infrastructure.configuration;

import br.com.digitalhouse.projetointegradorpi.domain.entity.Usuario;
import br.com.digitalhouse.projetointegradorpi.domain.exceptions.ExpiredException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private String SECRET_KEY = "secret";

    public String extractUserName(String token) {
        return extractClaimUsername(token);
    }

    public Date extractExpiration(String token) {
        return extractClaimDate(token);
    }

    public Date extractClaimDate(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getExpiration();
    }

    public String extractClaimUsername(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    private Claims extractAllClaims(String token) {
        try{
            return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();

        } catch (ExpiredJwtException e) {
            throw new ExpiredException();
        }
    }

    public String generateToken(UserDetails userDetails) {
        Usuario usuario = (Usuario) userDetails;
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", usuario.getId());
        claims.put("nome", usuario.getNome());
        claims.put("sobrenome", usuario.getSobrenome());
        claims.put("funcao", usuario.getFuncao());

        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 100000 * 600 + 600 * 4000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

}

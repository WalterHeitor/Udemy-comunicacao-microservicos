package br.com.curso.udemy.productapi.security.jwt.service;

import br.com.curso.udemy.productapi.security.jwt.dto.JwtResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.util.Strings;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class JwtService {

    private static final String EMPTY_SPACE = " ";
    private static final Integer TOKEN_INDEX = 1;

    @Value("${app-config.secrets.api-secret}")
    private String apiSecret;

    public void validateAuthorization(String token) throws AuthenticationException {

        var accessToken = extractToken(token);

        try {
            var claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(apiSecret.getBytes()))
                    .build()
                    .parseClaimsJws(accessToken).getBody();
            var user  = JwtResponse.getUser(claims);
            if (ObjectUtils.isEmpty(user) || ObjectUtils.isEmpty(user.getId())) {
                throw new AuthenticationException("The user is not valid");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthenticationException("Error while trying to process the Access Token.");
        }
    }

    private String extractToken(String token) throws AuthenticationException {
        if (ObjectUtils.isEmpty(token)) {
            throw new AuthenticationException("The access token was not infomed.");
        }
        if (token.contains(EMPTY_SPACE)) {

            return token.split(EMPTY_SPACE)[TOKEN_INDEX];
        }
        return token;
    }
}

package helpers;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;
import exceptions.AccessTokenException;
import exceptions.AuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

import javax.inject.Inject;

public class AuthenticationHelper {

    private final Config config;
    private String secretKey;

    @Inject
    public AuthenticationHelper(Config config) {
        this.config = config;

        try {
            this.secretKey =  config.getString("config.secret");
        } catch (ConfigException e) {
            System.out.println("Config exception when retrieving config.secret. ");
            e.printStackTrace();
        }

    }

    public String generateAccessToken(String userId) {

        return Jwts.builder()
                .setSubject(userId)
                .signWith(SignatureAlgorithm.HS512, this.secretKey)
                .compact();

    }


    public String decodeAccessToken(String accessToken) {

        String tokenSubject;

        try {
            tokenSubject = Jwts.parser().setSigningKey(this.secretKey)
                    .parseClaimsJws(accessToken)
                    .getBody()
                    .getSubject();

        } catch (SignatureException e) {
            throw new AccessTokenException(e.getMessage());
        }
        return tokenSubject;
    }

    public boolean verifyPassword(String inputPassword, String storedPassword) {

        if(!inputPassword.equals(storedPassword)) {
            throw new AuthenticationException("Wrong password");
        }

        return true;
    }
}

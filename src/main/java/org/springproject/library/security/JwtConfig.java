package org.springproject.library.security;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import java.util.Calendar;
import java.util.Date;

@Configuration
@ConfigurationProperties(prefix="jwt")
public class JwtConfig {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}") // Ensure this property is set in your application.properties or application.yml
    private Long expiration;

    public String getSecret() {return secret;}
    public void setSecret(String secret) {this.secret = secret;}

    public Date getExpirationDate(){
        long expirationTimeMilliseconds = Calendar.getInstance().getTimeInMillis()+expiration;
        return new Date(expirationTimeMilliseconds);
    }
    public void setExpirationDate(long expiration){
        this.expiration = expiration;
    }

    public SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
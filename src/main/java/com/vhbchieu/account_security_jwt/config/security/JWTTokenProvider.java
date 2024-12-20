package com.vhbchieu.account_security_jwt.config.security;

import com.vhbchieu.account_security_jwt.config.constant.AccountError;
import com.vhbchieu.account_security_jwt.exception.AccountException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class JWTTokenProvider {

    private final String SECRET_KEY = "AA5099C4BEFE0FDF7009E4F3426F450746204AD27B977E40A057DF9BC522CDCD";
    //1h
    private final long expirationTime = 3600000L;


    //Tao jwt
    public String generateToken(Long accountId, String roles) {
        //
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(accountId + "")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .claim("roles", roles)
                .signWith(getSigningKey())
                .compact();
    }

    //get id (sub)
    public Long getUserId(String token) {
        return Long.parseLong(getClaimValid(token).getSubject());
    }

    //get JwtId
    public String getJwtId(String token) {
        return getClaimValid(token).getId();
    }

    //get issue
    public long getIssueAt(String token) {
        return getClaimValid(token).getIssuedAt().getTime();
    }

    //get Key
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }

    //
    public Claims getClaimValid(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (MalformedJwtException ex) {
            throw new AccountException(AccountError.TOKEN_INVALID);
        } catch (ExpiredJwtException ex) {
            throw new AccountException(AccountError.TOKEN_EXPIRED);
        } catch (UnsupportedJwtException ex) {
            throw new AccountException(AccountError.TOKEN_UNSUPPORTED);
        } catch (IllegalArgumentException ex) {
            throw new AccountException(AccountError.TOKEN_CLAIMS_EMPTY);
        }
    }
}

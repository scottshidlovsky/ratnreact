package com.scott.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;

public class TokenService {
    private String superSecret = "shipit";
    private JWTVerifier verifier;
    Algorithm algorithm;

    TokenService() throws UnsupportedEncodingException {
         this.algorithm = Algorithm.HMAC256(this.superSecret);
         this.verifier = JWT.require(algorithm)
                .withIssuer("ratnreact")
                .build(); //Reusable verifier instance
    }

    public String generateToken(User user) {
        try {
            return JWT.create()
                    .withIssuer("ratnreact")
                    .withSubject(user.getUsername())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            return null;
        }
    }

    public String getUsernameFromToken(String token) {
        try {
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getSubject();
        } catch (JWTVerificationException exception){
            //Invalid signature/claims
            return null;
        }
    }
}

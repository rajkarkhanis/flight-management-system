package com.flights.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class CustomTokenParser {
    private CustomTokenParser(){}
    public static String parseJwt(String bearerToken){
    String jwtToken = bearerToken.substring("Bearer ".length());
    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
    JWTVerifier verifier = JWT.require(algorithm).build();
    DecodedJWT decodedJWT = verifier.verify(jwtToken);
    return decodedJWT.getSubject();

    }
}

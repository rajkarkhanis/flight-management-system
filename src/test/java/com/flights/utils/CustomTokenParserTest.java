package com.flights.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class CustomTokenParserTest {

    @Test
    void parseTokenTest(){
        String username = "Tom";
        String url ="localhost:8080/customer/addCustomer";
        Algorithm algorithm  = Algorithm.HMAC256("secret".getBytes());
        String accessToken = JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis()+10*60*1000*24*10))
                .withIssuer(url)
                .withClaim("role", "Customer")
                .sign(algorithm);
        String bearerToken = "Bearer " + accessToken.toString();
        assertThat(CustomTokenParser.parseJwt(bearerToken).equals(username));


    }

}

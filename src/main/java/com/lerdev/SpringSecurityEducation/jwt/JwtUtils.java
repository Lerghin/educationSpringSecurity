package com.lerdev.SpringSecurityEducation.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    @Value("${security.jwt.private.key}")
    private String privateKey;

    @Value("${security.jwt.user.generator}")
    private String userGenerator;
     //To create, let's use the secret key ang algorithm
    public String createToken (Authentication authentication){
        Algorithm algorithm= Algorithm.HMAC256(this.privateKey);
        ///this is inside the security context holder
        String username= authentication.getPrincipal().toString();
        //Also we have to get the permissions/authorizations
        //The idea is to get the permissions separated by comma
        String authorities= authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        //From this we generate the token
        String jwtToken= JWT.create()
                .withIssuer(this.userGenerator) //Here goes the user that generates the token
                .withSubject(username) //To whom the token is generated
                .withClaim("authorities", authorities) //Claims are the data contained in the JWT
                .withIssuedAt(new Date()) //Date of token generation
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600000)) //Expiration date, time in milliseconds equivalent to 1 hour
                .withJWTId(UUID.randomUUID().toString()) //Token id - generate a random one
                .withNotBefore(new Date(System.currentTimeMillis())) //From when it is valid (from now in this case)
                .sign(algorithm); //Our signature is the one we create with the secret key

     return jwtToken;
    }

    //Method to decode
    public DecodedJWT validateToken(String token){
       try {
          Algorithm algorithm= Algorithm.HMAC256(this.privateKey);//We use the secret key to decode
           JWTVerifier verifier= JWT.require(algorithm).withIssuer(this.userGenerator).build();
          ///If the token is valid, it will return the decoded token
       DecodedJWT decodedJWT = verifier.verify(token);
       return decodedJWT;

       }catch  (JWTVerificationException exception){

           throw new JWTVerificationException("Invalid token. Not authorized");
        }

    }

public String extractUsername(DecodedJWT decodedJWT){
      // We extract the username from the decoded token
    return decodedJWT.getSubject().toString();

}
    //devuelvo un claim en particular
    public Claim getSpecificClaim (DecodedJWT decodedJWT, String claimName) {
        return decodedJWT.getClaim(claimName);
    }

public Map<String, Claim> returnClaims(DecodedJWT decodedJWT){
    //We extract the claims from the decoded token
    return decodedJWT.getClaims();
}



}

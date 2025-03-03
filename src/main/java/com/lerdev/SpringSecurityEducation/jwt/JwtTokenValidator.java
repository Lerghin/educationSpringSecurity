package com.lerdev.SpringSecurityEducation.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

//throws we can establish a filter that will be executed once per request
public class JwtTokenValidator extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;
    public JwtTokenValidator(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(jwtToken != null) {
           //we should remove the bearer word from the token
            jwtToken = jwtToken.substring(7); //son 7 letras + 1 espacio
            DecodedJWT decodedJWT = jwtUtils.validateToken(jwtToken);

            //if token is valid? : access proccedd
            String username = jwtUtils.extractUsername(decodedJWT);
            // the claim get, we need to convert it to string
            String authorities = jwtUtils.getSpecificClaim(decodedJWT, "authorities").asString();

            //If it's all ok, i should set it in the Context Holder
            //then i have to convert them to GrantedAuthority
            Collection authoritiesList = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

            //if the token is validated, we give access to the user in the context holder
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authoritiesList);
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);

        }

        // If the token does not come,  go to the next filter
        //but if token is not valid, then is an error

        filterChain.doFilter(request,response);
    }


}

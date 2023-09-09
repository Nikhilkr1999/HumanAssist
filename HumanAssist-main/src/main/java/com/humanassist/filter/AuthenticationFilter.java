package com.humanassist.filter;

import com.humanassist.jwt.service.TokenValidatorService;
import com.humanassist.domain.JWTClaims;
import com.humanassist.scope.HumanAssistThreadLocal;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Order(1)
public class AuthenticationFilter implements  Filter {
    private final Logger LOGGER = Logger.getLogger(AuthenticationFilter.class.getCanonicalName());

    @Autowired
    private TokenValidatorService tokenValidatorService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(jakarta.servlet.ServletRequest request, jakarta.servlet.ServletResponse response, jakarta.servlet.FilterChain chain) throws IOException, jakarta.servlet.ServletException {
        jakarta.servlet.http.HttpServletRequest httpServletRequest = (jakarta.servlet.http.HttpServletRequest) request;
        LOGGER.log(Level.INFO, "In AuthenticationFilter Jakarta...");
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String uri = httpServletRequest.getRequestURI();
        LOGGER.log(Level.INFO, "Request URI ::: " + uri);
        try {
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String token = authorizationHeader.substring(7); // Remove "Bearer " prefix
                JWTClaims jwtClaims = tokenValidatorService.validateJWToken(token);
                LOGGER.log(Level.INFO, "Successfully validated jwt token EmailId ::: {0}, Schema ::: {1}",
                        new String[]{jwtClaims.getOwnerEmail(), jwtClaims.getSchema()});
                Object oldTL = HumanAssistThreadLocal.getThreadLocal();
                LOGGER.log(Level.INFO," Old TL is ::: {0}", oldTL);
                HumanAssistThreadLocal.setThreadLocal(jwtClaims.getSchema());
                chain.doFilter(request, response);
                HumanAssistThreadLocal.setThreadLocal(oldTL);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while validating jwt token ::: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

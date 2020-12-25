package com.arllain.agcscaruserapi.security;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author arllain
 */

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        final Map<String, Object> mapBodyException = new HashMap<>() ;

        mapBodyException.put("message"   , "Unauthorized") ;
        mapBodyException.put("status"    , HttpServletResponse.SC_UNAUTHORIZED) ;
        mapBodyException.put("timestamp", (new Date()).getTime()) ;

        response.setContentType("application/json") ;
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED) ;

        final ObjectMapper mapper = new ObjectMapper() ;
        mapper.writeValue(response.getOutputStream(), mapBodyException) ;
    }
}

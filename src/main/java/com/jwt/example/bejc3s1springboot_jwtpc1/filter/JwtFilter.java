/*
 * Author Name:
 * Date: 11/29/2022
 * Created With: IntelliJ IDEA Community Edition
 */


package com.jwt.example.bejc3s1springboot_jwtpc1.filter;
        import io.jsonwebtoken.Claims;
        import io.jsonwebtoken.Jwts;
        import org.springframework.web.filter.GenericFilterBean;

        import javax.servlet.*;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter (ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
            IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String authHeader = httpServletRequest.getHeader("authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            throw new ServletException("Missing or Invalid exception");
        }

        String token = authHeader.substring(7);
        Claims claims = Jwts.parser().setSigningKey("mykey")
                .parseClaimsJws(token).getBody();
        System.out.println("\nclaims : " + claims);

        httpServletRequest.setAttribute("claims", claims);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
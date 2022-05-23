package com.ivan.weather.station.web.filter;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ivan.weather.station.web.util.JwtUtil;

@Component
public class JwtFilter extends OncePerRequestFilter {

    public static final Logger LOGGER = LoggerFactory.getLogger(JwtFilter.class);

    private final UserDetailsService userService;

    @Autowired
    public JwtFilter(UserDetailsService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!StringUtils.startsWithIgnoreCase(authorizationHeader, "Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }
        String jwt = authorizationHeader.substring(7);
        Optional<String> username = JwtUtil.extractUsername(jwt);
        if (username.isPresent() && SecurityContextHolder.getContext()
                                                         .getAuthentication() == null) {
            UserDetails userDetails;
            try {
                userDetails = userService.loadUserByUsername(username.get());
            } catch (UsernameNotFoundException e) {
                LOGGER.error(e.getMessage(), e);
                filterChain.doFilter(request, response);
                return;
            }
            if (JwtUtil.validateToken(jwt, userDetails)) {
                Map<String, Object> attributes = Map.of("token", jwt, "username", userDetails.getUsername());
                OAuth2User oAuth2User = new DefaultOAuth2User(userDetails.getAuthorities(), attributes, "username");
                OAuth2AuthenticationToken oAuth2AuthenticationToken = new OAuth2AuthenticationToken(oAuth2User,
                                                                                                    userDetails.getAuthorities(),
                                                                                                    "username");
                oAuth2AuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext()
                                     .setAuthentication(oAuth2AuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}

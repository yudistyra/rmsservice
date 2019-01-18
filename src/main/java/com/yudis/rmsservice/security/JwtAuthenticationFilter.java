package com.yudis.rmsservice.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private JwtTokenProvider tokenProvider;
	private CustomUserDetailsService customUserDetailService;

	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	@Autowired
	public JwtAuthenticationFilter(JwtTokenProvider tokenProvider, CustomUserDetailsService customUserDetailService) {
		this.tokenProvider = tokenProvider;
		this.customUserDetailService = customUserDetailService;
	}

	/*
	 * This method is used to set authentication with token filter
	 * get jwt from HttpServletRequest
	 * check if jwt has text and validate the token
	 * get user id from jwt
	 * load UserDetails by user id
	 * set UsernamePasswordAuthenticationToken using userDetails information
	 * set the SecurityContextHolder authentication
	 * set FilterChain with HttpServletResponse and HttpServletRequest
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			String jwt = getJwtFromRequest(request);
			
			if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                Long userId = tokenProvider.getUserIdFromJWT(jwt);

                UserDetails userDetails = customUserDetailService.loadUserById(userId);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
		} catch (Exception e) {
			logger.error("Could not set user authentication in security context", e);
		}
		
		filterChain.doFilter(request, response);
	}
	
	/*
	 * This method is used to get the bearer token from HttpServletRequest
	 * get header with "Authorization" argument
	 * check if has text and starts with "Bearer "
	 * remove "Bearer " text to get token value only
	 * @return Bearer token from Authorization header
	 */
	private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}

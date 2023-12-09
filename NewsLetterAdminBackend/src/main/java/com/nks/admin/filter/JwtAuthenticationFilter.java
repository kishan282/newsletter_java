package com.nks.admin.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nks.admin.customexceptionhandler.JavaJsonMapperException;
import com.nks.admin.service.CustomUserDetailService;
import com.nks.admin.validation.JwtUtil;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtutil;

	@Autowired
	private CustomUserDetailService customUserService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// get header
		// Start with Bearer
		// validate
		String reqTokenHeader = request.getHeader("Authorization");
		String userName = null;

		try {

			if (reqTokenHeader != null && reqTokenHeader.startsWith("Bearer ")) {
				String tokenWithoutBearer = reqTokenHeader.substring(7);

				userName = this.jwtutil.extractUsername(tokenWithoutBearer);
				UserDetails user = this.customUserService.loadUserByUsername(userName);
				if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null
						&& jwtutil.validateToken(tokenWithoutBearer, user)) {

					// jwtutil.validateToken(tokenWithoutBearer, user);
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							user, null, user.getAuthorities());
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}

				filterChain.doFilter(request, response);
			} else {
				throw new JavaJsonMapperException("Authentication is not provided");
			}
		} catch (RuntimeException mx) {
			String errorMessage = mx.getMessage();
			if (errorMessage.contains("Malformed")) {
				errorMessage = errorMessage.split("[:]")[0];
			} else if (errorMessage.contains("JWT")) {
				errorMessage = errorMessage.split("[.]")[0];
			}
			response.sendError(403, errorMessage);

		}

	}

}

package security;

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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.MalformedJwtException;

@Service
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService  userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// get token from header

		String header = request.getHeader("Authorization");
		this.logger.info("Header: {}", header);

		String jwtToken = null;

		String username = null;

		// Bearer 23523423523sdfgsdgsgsfgwergsegasetg
		if (header != null && header.startsWith("Bearer")) {

			// header is right
			// get token
			// get username

			jwtToken = header.substring(7);

			try {
				username = this.jwtTokenHelper.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				this.logger.info("Unable to get the token {}", e.getMessage());
			} catch (MalformedJwtException e) {
				this.logger.info("Invalid Jwt Token {} ", e.getMessage());

			}
			
			
			

		} else {
			this.logger.info("No header is present");
		}

		
		
		//////username: value
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			
			UserDetails userDetail = this.userDetailsService.loadUserByUsername(username);
			
			if(this.jwtTokenHelper.validateToken(jwtToken, userDetail)) {
				
				//token is validated
				//set the authentication
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetail, null,userDetail.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request) );
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
				
				
				
			}else {
				logger.info("token validation failed !!");
			}
			
		}
		
		filterChain.doFilter(request, response);
		
	}

}

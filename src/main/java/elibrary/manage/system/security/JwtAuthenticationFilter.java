package elibrary.manage.system.security;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	    private final JwtService jwtService;
	    private final UserdetailsServiceImple userdetailsServiceImple;
	    

	public JwtAuthenticationFilter(JwtService jwtService, UserdetailsServiceImple userdetailsServiceImple) {
			this.jwtService = jwtService;
			this.userdetailsServiceImple = userdetailsServiceImple;
		}



	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			             FilterChain filterChain) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		 String authHeader = request.getHeader("Authorization");

	        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
	            filterChain.doFilter(request,response);
	            return;
	        }

	        String token = authHeader.substring(7);
	        String username = jwtService.extractUsername(token);

	        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

	            UserDetails userDetails = userdetailsServiceImple.loadUserByUsername(username);


	            if(jwtService.isValid(token, userDetails)) {
	                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
	                        userDetails, null, userDetails.getAuthorities()
	                );

	                authToken.setDetails(
	                        new WebAuthenticationDetailsSource().buildDetails(request)
	                );

	                SecurityContextHolder.getContext().setAuthentication(authToken);
	            }
	        }
	        filterChain.doFilter(request, response);

		
		
	}
	
	
	
	

}

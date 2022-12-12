package RIM.Banque.GestionBancaire.configuration;

import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import RIM.Banque.GestionBancaire.service.JwtService;
import RIM.Banque.GestionBancaire.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//import com.springboot.blog.security.CustomUserDetailsService;
//import com.springboot.blog.security.JwtTokenProvider;

public class JwtRequestFilter extends OncePerRequestFilter {

//    // inject dependencies
//    @Autowired
//    private JwtTokenProvider tokenProvider;
//
//    @Autowired
//    private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
    	
    	final String header = request.getHeader("Authorization");
    	String jwtToken = null;
    	String userName = null;
    	if(header != null && header.startsWith("Token ")) {
    		
    		jwtToken = header.substring(6);
    		
    		try {
    			userName = jwtUtil.getUserNameFromToken(jwtToken);
    		}catch(IllegalArgumentException e) {
    			System.out.println("Unable to get JWT token");
    		}catch(ExpiredJwtException e) {
    			System.out.println("JWT token is Expired");
    			
    		}
    	}else {
    		System.out.println("JWT token does not start with Token ");
    	}
    	
    	
    	if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
    		
    		UserDetails userDetails = jwtService.loadUserByUsername(userName);
    		
    		if(jwtUtil.validateToken(jwtToken,userDetails)) {
    			
    			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
    					new UsernamePasswordAuthenticationToken(
    							userDetails,
    							null,
    							userDetails.getAuthorities()
    						);
    			usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    			
    			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    		}
    	}
    	
    	filterChain.doFilter(request, response);
    	
    	
    	
//        // get JWT (token) from http request
//        String token = getJWTfromRequest(request);
//        // validate token
//        if(StringUtils.hasText(token) && tokenProvider.validateToken(token)){
//            // get username from token
//            String username = tokenProvider.getUsernameFromJWT(token);
//            // load user associated with token
//            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
//
//            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                    userDetails, null, userDetails.getAuthorities()
//            );
//            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            // set spring security
//            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        }
//        filterChain.doFilter(request, response);
    }

    // Bearer <accessToken>
//    private String getJWTfromRequest(HttpServletRequest request){
//            String bearerToken = request.getHeader("Authorization");
//            if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
//                return bearerToken.substring(7, bearerToken.length());
//            }
//            return null;
//    }

}

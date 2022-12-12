package RIM.Banque.GestionBancaire.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import RIM.Banque.GestionBancaire.entity.JwtRequest;
import RIM.Banque.GestionBancaire.entity.JwtResponse;
import RIM.Banque.GestionBancaire.entity.User;
import RIM.Banque.GestionBancaire.repository.UserRepository;
import RIM.Banque.GestionBancaire.util.JwtUtil;

@Service
public class JwtService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
		String userName = jwtRequest.getUserName();
		String userassword = jwtRequest.getPassword();
		authenticate(userName,userassword);
		
		final UserDetails userDetails =  loadUserByUsername(userName);
		String newGeneratedToken = jwtUtil.generateToken(userDetails);
		User user = userRepository.findByUsername(userName).get();
		return new JwtResponse(user, newGeneratedToken);
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//		userRepository.findById(username).get();
//		User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).get();
		User user = userRepository.findByUsername(userName).get();
		
		if(user != null) {
			return new org.springframework.security.core.userdetails.User(
						user.getUsername(),
						user.getPassword(),
						getAuthorities(user)
					);
		}else {
			throw new UsernameNotFoundException("User not found with username :" + userName);
		}
		
	}
	
//     return new org.springframework.security.core.userdetails.User(user.getEmail(),
//             user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	
	private Set getAuthorities(User user) {
		Set authorities = new HashSet();
		
		user.getRoles().forEach( role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
		});
		
		return authorities;
	}
	
	
	private void authenticate(String userName, String userPassword) throws Exception{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,userPassword ));
		} catch(DisabledException e){
			throw new Exception("User is disable");
		}catch(BadCredentialsException e){
			throw new Exception("Bad credencials From User");
		}
	}
	

}

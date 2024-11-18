package thanhluu.service.Impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import thanhluu.entity.Users;
import thanhluu.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService{

	
	private UserRepository userRepository;
	
	
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Users user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail) 
									.orElseThrow(() -> 
												new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail));
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(),
				user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}

}

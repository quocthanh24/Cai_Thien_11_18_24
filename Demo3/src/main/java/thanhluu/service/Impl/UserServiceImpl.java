package thanhluu.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import thanhluu.entity.Users;
import thanhluu.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Users user = userRepository.getUserByUsername(username);
		
		if (user != null) {
			throw new UsernameNotFoundException("Could not find user");
		}
		return new MyUserService(user);
	}
	
	
}

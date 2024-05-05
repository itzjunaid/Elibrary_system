package elibrary.manage.system.security;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import elibrary.manage.system.repositieris.AdminRepo;

@Service
public class UserdetailsServiceImple implements UserDetailsService {

	@Autowired
	private AdminRepo adminRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return adminRepo.findByUsername(username)
				.orElseThrow(()->new UsernameNotFoundException("user not found"));
	}
	
	


}

 package elibrary.manage.system.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import elibrary.manage.system.entities.Admin;
import elibrary.manage.system.entities.Student;
import elibrary.manage.system.payload.AuthenticationResponse;
import elibrary.manage.system.repositieris.AdminRepo;
import elibrary.manage.system.security.JwtService;

@Service
public class AdminAuthenticationService {
	
	private AdminRepo adminRepo;
	
	private PasswordEncoder passwordEncoder;
	
	private JwtService jwtService;
	
	private AuthenticationManager authenticationManager;

	
	
	public AdminAuthenticationService(AdminRepo adminRepo, PasswordEncoder passwordEncoder, JwtService jwtService,
			AuthenticationManager authenticationManager) {
		this.adminRepo = adminRepo;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}



	public AuthenticationResponse register(Admin request) {

		    Admin admin =new Admin();
	        admin.setUsername(request.getUsername());
	        admin.setEmail(request.getEmail());
	        admin.setPassword(passwordEncoder.encode(request.getPassword()));
	        admin.setRole(request.getRole());
	        admin= adminRepo.save(admin);

	        String jwt = jwtService.generateToken(admin);
	        return new AuthenticationResponse(jwt);

        }
	
	public AuthenticationResponse authenticate(Admin admin) {
		authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        admin.getUsername(),
                        admin.getPassword()
                )
        );

        Admin adm = adminRepo.findByUsername(admin.getUsername()).orElseThrow();
        String jwt = jwtService.generateToken(adm);
        return new AuthenticationResponse(jwt);
	}

       
    }


package elibrary.manage.system.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import elibrary.manage.system.entities.Admin;
import elibrary.manage.system.payload.AuthenticationResponse;
import elibrary.manage.system.services.AdminAuthenticationService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminAuthenticationController {
	
	 private final AdminAuthenticationService authService;

	    public AdminAuthenticationController(AdminAuthenticationService authService) {
	        this.authService = authService;
	    }


	    @PostMapping("/register")
	    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody Admin admin) {
	        return ResponseEntity.ok(authService.register(admin));
	    }

	    @PostMapping("/login")
	    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody Admin admin) {
	        return ResponseEntity.ok(authService.authenticate(admin));
	    }

}

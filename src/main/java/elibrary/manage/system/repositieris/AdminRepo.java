package elibrary.manage.system.repositieris;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import elibrary.manage.system.entities.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer>{

	Optional<Admin> findByUsername(String username);
	

}

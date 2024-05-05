package elibrary.manage.system.repositieris;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import elibrary.manage.system.entities.Admin;
import elibrary.manage.system.entities.Student;


@Repository
public interface SudentRepo extends JpaRepository<Student, Long>{

	Optional<Student> findByUsername(String username);
	
	

}

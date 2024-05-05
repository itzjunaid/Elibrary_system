package elibrary.manage.system.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import elibrary.manage.system.entities.Student;
import elibrary.manage.system.exception.ResourceNotFoundException;
import elibrary.manage.system.payload.DaoStudent;
import elibrary.manage.system.repositieris.SudentRepo;
@Service
public class StudentService {

	@Autowired
	private SudentRepo studentRepo;
	
	
	public DaoStudent registerStudent(DaoStudent daoStudent) {
		Student studentTobeRegisterd=new Student();
		studentTobeRegisterd.setUsername(daoStudent.getName());
		studentTobeRegisterd.setEmail(daoStudent.getEmail());
		studentTobeRegisterd.setPassword(daoStudent.getPassword());
		studentTobeRegisterd.setRole("USER");
		Student registerdStudent=this.studentRepo.save(studentTobeRegisterd);
		daoStudent.setEmail(registerdStudent.getEmail());
		daoStudent.setName(registerdStudent.getUsername());
		daoStudent.setId(registerdStudent.getId());
		return daoStudent;
	}
	
	public Student getStudentById(long id) {
		Student returnStudent=this.studentRepo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("student","student_id",Long.toString(id)));
		return returnStudent;
	}

}
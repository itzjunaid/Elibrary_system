package elibrary.manage.system.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import elibrary.manage.system.payload.DaoStudent;
import elibrary.manage.system.services.StudentService;
@RestController
@RequestMapping("/Student")
public class StudentController {
	
	@Autowired
	 private StudentService studentService;

	@PostMapping("/register")
	public ResponseEntity<DaoStudent> register(@RequestBody DaoStudent student){
	    	DaoStudent registerdStudent=this.studentService.registerStudent(student);
	    	return new ResponseEntity<DaoStudent>(registerdStudent,HttpStatus.OK);
	    }
	
	

}

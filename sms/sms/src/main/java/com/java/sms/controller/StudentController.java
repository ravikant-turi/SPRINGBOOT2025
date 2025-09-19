package com.java.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.sms.modal.Student;
import com.java.sms.service.StudentService;

//http://localhost:8080/swagger-ui/index.html
@RestController
@RequestMapping("/api/students")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@PostMapping
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {

		Student saveStudent = this.studentService.createStudents(student);

		return new ResponseEntity<>(saveStudent, HttpStatus.CREATED);

	}

	@GetMapping
	public ResponseEntity<List<Student>> findAllStudents() {
		List<Student> students = this.studentService.findAllStudents();

		return new ResponseEntity<>(students, HttpStatus.OK);
	}

	@GetMapping("/{rollNumber}")
	public ResponseEntity<Student> findStudentByRollNumber(@PathVariable("rollNumber") Long rollNumber) {
		Student studentFound = this.studentService.findStudentByRollNumber(rollNumber);
		return new ResponseEntity<>(studentFound, HttpStatus.OK);
	}

	/*
	 * this is restApi to updated the student
	 */
	@PutMapping("/{rollNumber}")
	public ResponseEntity<Student> updateStudentByRollNumber(@RequestBody Student student,
			@PathVariable("rollNumber") Long rollNUmber) {
		Student updatedStudent = this.studentService.updateStudentByRollNumber(rollNUmber, student);
		return new ResponseEntity<>(updatedStudent, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("{rollNumber}")
	public ResponseEntity<String> deleteStudentByRollNumber(@PathVariable("rollNumber") Long rollNumber) {
		String deletedMessage = this.studentService.deleteStudentByRollNumber(rollNumber);
		return new ResponseEntity<>(deletedMessage, HttpStatus.OK);
	}
}

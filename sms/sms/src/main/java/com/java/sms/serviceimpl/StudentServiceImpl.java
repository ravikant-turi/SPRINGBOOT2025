package com.java.sms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.sms.modal.Student;
import com.java.sms.repository.StudentRepository;
import com.java.sms.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student createStudents(Student student) {
		Student saveStudent = this.studentRepository.save(student);
		return saveStudent;
	}

	@Override
	public Student findStudentByRollNumber(long rollNumber) {
		Student student = this.studentRepository.findById(rollNumber)
				.orElseThrow(() -> new RuntimeException("Student Not Found with roll number: " + rollNumber));

		return student;
	}

	@Override
	public List<Student> findAllStudents() {
		List<Student> students = this.studentRepository.findAll();
		return students;
	}

	@Override
	public Student updateStudentByRollNumber(Long rollNumber, Student student) {
		Student studentFound = this.studentRepository.findById(rollNumber)
				.orElseThrow(() -> new RuntimeException("Student Not Found with roll number: " + rollNumber));

		studentFound.setName(student.getName());
		studentFound.setAge(student.getAge());
		studentFound.setCity(student.getCity());
		studentFound.setEmail(student.getEmail());
		studentFound.setMobileNo(student.getMobileNo());

		Student updatedStudent = this.studentRepository.save(studentFound);
		return updatedStudent;
	}

	@Override
	public String deleteStudentByRollNumber(Long rollNumber) {
		Student student = this.studentRepository.findById(rollNumber)
				.orElseThrow(() -> new RuntimeException("Student Not Found with roll number: " + rollNumber));

		this.studentRepository.delete(student);

		return "deleted successfully";
	}

}

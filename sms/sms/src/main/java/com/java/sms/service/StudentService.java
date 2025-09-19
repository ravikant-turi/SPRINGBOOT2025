package com.java.sms.service;

import java.util.List;

import com.java.sms.modal.Student;

public interface StudentService {

	Student createStudents(Student student);

	Student findStudentByRollNumber(long rollNumber);

	List<Student> findAllStudents();

	Student  updateStudentByRollNumber(Long rollNumber,Student student);

	String deleteStudentByRollNumber(Long rollNumber);

}

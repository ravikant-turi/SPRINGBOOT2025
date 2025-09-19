package com.java.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.sms.modal.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}

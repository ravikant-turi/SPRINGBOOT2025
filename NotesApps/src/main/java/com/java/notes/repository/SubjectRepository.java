package com.java.notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.notes.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

	boolean existsByName(String name);
}

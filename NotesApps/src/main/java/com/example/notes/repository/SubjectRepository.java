package com.example.notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.notes.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
	boolean existsByName(String name);
}

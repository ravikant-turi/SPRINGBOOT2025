package com.java.notes.service;

import java.util.List;

import com.java.notes.dto.SubjectDTO;
import com.java.notes.payloads.ApiResponse;

public interface SubjectService {

    ApiResponse<SubjectDTO> createSubject(SubjectDTO subjectDTO);

    ApiResponse<SubjectDTO> updateSubject(Long id, SubjectDTO subjectDTO);

    ApiResponse<Void> deleteSubject(Long id);

    ApiResponse<List<SubjectDTO>> getAllSubjects();

    ApiResponse<SubjectDTO> getSubjectById(Long id);
}

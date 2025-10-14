package com.java.notes.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.notes.dto.SubjectDTO;
import com.example.notes.entity.Subject;
import com.example.notes.exceptions.ResourceNotFoundException;
import com.example.notes.payloads.ApiResponse;
import com.example.notes.repository.SubjectRepository;
import com.example.notes.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectRepository subjectRepository;

	@Override
	public ApiResponse<SubjectDTO> createSubject(SubjectDTO subjectDTO) {
		if (subjectRepository.existsByName(subjectDTO.getName())) {
			return new ApiResponse<>("error", "Subject name already exists", null);
		}

		Subject subject = new Subject();
		subject.setName(subjectDTO.getName());
		subject.setDescription(subjectDTO.getDescription());

		Subject savedSubject = subjectRepository.save(subject);
		return new ApiResponse<>("success", "Subject created successfully", mapToDTO(savedSubject));
	}

	@Override
	public ApiResponse<SubjectDTO> updateSubject(Long id, SubjectDTO subjectDTO) {
		Subject subject = subjectRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Subject not found with ID: " + id));

		subject.setName(subjectDTO.getName());
		subject.setDescription(subjectDTO.getDescription());

		Subject updatedSubject = subjectRepository.save(subject);
		return new ApiResponse<>("success", "Subject updated successfully", mapToDTO(updatedSubject));
	}

	@Override
	public ApiResponse<Void> deleteSubject(Long id) {
		Subject subject = subjectRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Subject not found with ID: " + id));

		subjectRepository.delete(subject);
		return new ApiResponse<>("success", "Subject deleted successfully", null);
	}

	@Override
	public ApiResponse<List<SubjectDTO>> getAllSubjects() {
		List<SubjectDTO> subjects = subjectRepository.findAll().stream().map(this::mapToDTO)
				.collect(Collectors.toList());

		return new ApiResponse<>("success", "Subjects retrieved successfully", subjects);
	}

	@Override
	public ApiResponse<SubjectDTO> getSubjectById(Long id) {
		Subject subject = subjectRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Subject not found with ID: " + id));

		return new ApiResponse<>("success", "Subject retrieved successfully", mapToDTO(subject));
	}

	private SubjectDTO mapToDTO(Subject subject) {
		SubjectDTO dto = new SubjectDTO();
		dto.setId(subject.getId());
		dto.setName(subject.getName());
		dto.setDescription(subject.getDescription());
		return dto;
	}
}

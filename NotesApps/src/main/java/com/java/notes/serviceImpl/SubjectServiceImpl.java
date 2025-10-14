package com.java.notes.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.java.notes.dto.SubjectDTO;
import com.java.notes.entity.Subject;
import com.java.notes.exceptions.ResourceNotFoundException;
import com.java.notes.payloads.ApiResponse;
import com.java.notes.repository.SubjectRepository;
import com.java.notes.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public ApiResponse<SubjectDTO> createSubject(SubjectDTO subjectDTO) {
        if (subjectRepository.existsByName(subjectDTO.getName())) {
            return new ApiResponse<>("error", "Subject name already exists", null);
        }

        Subject subject = modelMapper.map(subjectDTO, Subject.class);
        Subject savedSubject = subjectRepository.save(subject);

        return new ApiResponse<>("success", "Subject created successfully", modelMapper.map(savedSubject, SubjectDTO.class));
    }

    @Override
    @Transactional
    public ApiResponse<SubjectDTO> updateSubject(Long id, SubjectDTO subjectDTO) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with ID: " + id));

        subject.setName(subjectDTO.getName());
        subject.setDescription(subjectDTO.getDescription());

        Subject updatedSubject = subjectRepository.save(subject);
        return new ApiResponse<>("success", "Subject updated successfully", modelMapper.map(updatedSubject, SubjectDTO.class));
    }

    @Override
    @Transactional
    public ApiResponse<Void> deleteSubject(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with ID: " + id));

        subjectRepository.delete(subject);
        return new ApiResponse<>("success", "Subject deleted successfully", null);
    }

    @Override
    public ApiResponse<List<SubjectDTO>> getAllSubjects() {
        List<SubjectDTO> subjects = subjectRepository.findAll().stream()
                .map(subject -> modelMapper.map(subject, SubjectDTO.class))
                .collect(Collectors.toList());

        return new ApiResponse<>("success", "Subjects retrieved successfully", subjects);
    }

    @Override
    public ApiResponse<SubjectDTO> getSubjectById(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with ID: " + id));

        return new ApiResponse<>("success", "Subject retrieved successfully", modelMapper.map(subject, SubjectDTO.class));
    }
}

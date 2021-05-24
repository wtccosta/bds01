package com.devsuperior.bds01.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds01.controllers.DepartmentRepository;
import com.devsuperior.bds01.dto.DepartmentDTO;
import com.devsuperior.bds01.entities.Department;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository repository;

	public List<DepartmentDTO> findAll(){
		List<Department> departments = repository.findAll(Sort.by("name"));
		return departments.stream()
				.map(x -> new DepartmentDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public DepartmentDTO findById(Long id) {
		Department department =  repository.findById(id).orElseThrow(() -> {
			return new ResourceNotFoundException("Entity not found");
		});
		return new DepartmentDTO(department);
	}
}

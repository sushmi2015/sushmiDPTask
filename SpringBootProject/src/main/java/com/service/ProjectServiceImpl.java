package com.service;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ProjectRepo;
import com.domain.ProjectDetailsDO;
import com.exception.ProjectException;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired private ProjectRepo projectManagementRepo;

	@Override
	public ProjectDetailsDO saveOrUpdateProjectDetails(ProjectDetailsDO detailsDO) throws ProjectException{
		return projectManagementRepo.save(detailsDO);
	}

	@Override
	public void deleteProjectDetails(Integer projectId) throws ProjectException{
		projectManagementRepo.deleteById(projectId);
	}

	@Override
	public List<ProjectDetailsDO> listProjectDetails() {
		return projectManagementRepo.findAll();
	}

	@Override
	public Optional<ProjectDetailsDO> findById(Integer projectId) {
		return projectManagementRepo.findById(projectId);
	}

	
	
}
package com.service;

import java.util.List;
import java.util.Optional;

import com.domain.ProjectDetailsDO;
import com.exception.ProjectException;

public interface ProjectService {

	ProjectDetailsDO saveOrUpdateProjectDetails(ProjectDetailsDO detailsDO) throws ProjectException;

	void deleteProjectDetails(Integer projectId) throws ProjectException;

	List<ProjectDetailsDO> listProjectDetails();

	Optional<ProjectDetailsDO> findById(Integer projectId);
}

package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.domain.ProjectDetailsDO;
import com.dto.ProjectDetailsDTO;
import com.exception.ProjectException;
import com.service.ProjectService;

@RestController
public class MainController {

	@Autowired
	private ProjectService projectManagementService;

	@GetMapping("/findAllProjects")
	public ResponseEntity<List<ProjectDetailsDO>> findAllProjects() {
		return new ResponseEntity<>(projectManagementService.listProjectDetails(), HttpStatus.OK);
	}

	@PostMapping("/addProject")
	public ResponseEntity<String> addProjectDetails(@RequestBody ProjectDetailsDTO detailsDTO)
			throws ProjectException {
		ProjectDetailsDO detailsDO = new ProjectDetailsDO(null, detailsDTO.getTitle(), detailsDTO.getBudget(),
				detailsDTO.getTeamSize(), detailsDTO.getDuration(), detailsDTO.getEndDate(),
				detailsDTO.getManagerName());
		projectManagementService.saveOrUpdateProjectDetails(detailsDO);
		return new ResponseEntity<>("Data Saved Successfully", HttpStatus.OK);

	}

	@PutMapping("/updateProject")
	public ResponseEntity<String> updateProjectDetails(@RequestBody ProjectDetailsDTO detailsDTO)
			throws ProjectException {
		ProjectDetailsDO detailsDO = new ProjectDetailsDO(detailsDTO.getProjectId(), detailsDTO.getTitle(),
				detailsDTO.getBudget(), detailsDTO.getTeamSize(), detailsDTO.getDuration(), detailsDTO.getEndDate(),
				detailsDTO.getManagerName());
		projectManagementService.saveOrUpdateProjectDetails(detailsDO);
		return new ResponseEntity<>("Data Updated Successfully", HttpStatus.OK);
	}

	@DeleteMapping("/deleteProject/{projectId}")
	public ResponseEntity<String> deleteProject(@PathVariable(required = true, name = "projectId") Integer projectId)
			throws ProjectException {
		projectManagementService.deleteProjectDetails(projectId);
		return new ResponseEntity<>("Project Data deleted Successfully", HttpStatus.OK);
	}

	@GetMapping("/projectDetail/{projectId}")
	public ResponseEntity<ProjectDetailsDTO> findProjectById(
			@PathVariable(required = true, name = "projectId") Integer projectId) {
		Optional<ProjectDetailsDO> detailsDO = projectManagementService.findById(projectId);

		return detailsDO.isPresent()
				? new ResponseEntity<>(new ProjectDetailsDTO(detailsDO.get().getProjectId(), detailsDO.get().getTitle(),
						detailsDO.get().getBudget(), detailsDO.get().getTeamSize(), detailsDO.get().getDuration(),
						detailsDO.get().getEndDate(), detailsDO.get().getManagerName()), HttpStatus.OK)
				: new ResponseEntity<>(new ProjectDetailsDTO(), HttpStatus.NO_CONTENT);
	}
}
package com.afourathon.report_notification_rest_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.afourathon.report_notification_rest_api.data.entity.Project;
import com.afourathon.report_notification_rest_api.data.payloads.repository.ProjectRepository;

@Service
public class JpaProjectService implements ProjectService {

	@Autowired
	ProjectRepository projectRepository;

	@Override
	public boolean checkIfProjectExistsById(Long projectId) {
		return projectRepository.existsById(projectId);
	}

	@Override
	public Optional<Project> findProjectById(Long projectId) {
		return projectRepository.findById(projectId);
	}

	@Override
	public List<Project> findAllProjects() {
		return projectRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}

}

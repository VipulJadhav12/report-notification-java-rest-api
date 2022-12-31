package com.afourathon.report_notification_rest_api.service;

import java.util.List;
import java.util.Optional;

import com.afourathon.report_notification_rest_api.data.entity.Project;

public interface ProjectService {
	
	public boolean checkIfProjectExistsById(Long projectId);
	
	public Optional<Project> findProjectById(Long projectId);
	
	List<Project> findAllProjects();

}

package com.afourathon.report_notification_rest_api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.afourathon.report_notification_rest_api.configuration.Configurations;
import com.afourathon.report_notification_rest_api.data.entity.Project;
import com.afourathon.report_notification_rest_api.data.entity.WeeklyStatus;
import com.afourathon.report_notification_rest_api.data.payloads.response.ApiResponse;
import com.afourathon.report_notification_rest_api.exception.ProjectNotFoundException;
import com.afourathon.report_notification_rest_api.exception.WeeklyStatusNotFoundException;
import com.afourathon.report_notification_rest_api.service.ProjectService;
import com.afourathon.report_notification_rest_api.service.ReportNotificationService;
import com.afourathon.report_notification_rest_api.service.WeeklyStatusService;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportNotificationController {
	
	@Autowired
	WeeklyStatusService weeklyStatusService;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	ReportNotificationService reportNotificationService;
	
	@Autowired
	Configurations configurations;
	
	@GetMapping(value = {"", "/"})
	public ResponseEntity<String> defaultHealthCheck() {
		return new ResponseEntity<>("Default HTTP Status: OK", HttpStatus.OK);
	}
	
	@GetMapping("/sendBy=PROJECT_ID/project/{projectId}/weekly_status/{weeklyStatusId}")
	public ResponseEntity<ApiResponse> sendEmailReport(@PathVariable Long projectId, @PathVariable Long weeklyStatusId) 
			throws WeeklyStatusNotFoundException, ProjectNotFoundException {
		if(!projectService.checkIfProjectExistsById(projectId)) {
			throw new ProjectNotFoundException(String.format(Configurations.INVALID_PROJECT_ID, projectId));
		}

		if(!weeklyStatusService.checkIfWeeklyStatusExistsById(weeklyStatusId)) {
			throw new WeeklyStatusNotFoundException(String.format(Configurations.INVALID_WEEKLY_STATUS_ID, weeklyStatusId));
		}
		
		// Retrieving the wrapper Project object stored in the database
		Optional<Project> objProject = projectService.findProjectById(projectId);
		
		if(!objProject.isPresent())
			throw new ProjectNotFoundException(String.format(Configurations.INVALID_PROJECT_ID, projectId));

		// Retrieving the actual Project object from the wrapper Optional<Project> object
		Project project = objProject.get();
		
		// Retrieving the existing Weekly-Status List attached to the given Project
		List<WeeklyStatus> weeklyStatusList = project.getWeeklyStatuses();
		
		// Retrieving the actual WeeklyStatus object from the existing Weekly-Status List
		// attached to the given Project object
		WeeklyStatus weeklyStatus = weeklyStatusList.stream()
		  .filter(existingWeeklyStatus -> existingWeeklyStatus.getId().equals(weeklyStatusId))
		  .findAny()
		  .orElse(null);
		
		if(null == weeklyStatus) {
			throw new WeeklyStatusNotFoundException(String.format(Configurations.INVALID_WEEKLY_STATUS_ID, weeklyStatusId));
		}
		
		boolean isShared = reportNotificationService.shareReport(project, weeklyStatus);
		
		ApiResponse apiResponse = null;
		if(!isShared) {
			apiResponse = new ApiResponse(String.format(Configurations.EMAIL_NOTIFICATION_ON_FAILURE, weeklyStatus.getId(), project.getId()), HttpStatus.INTERNAL_SERVER_ERROR);
			return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		apiResponse = new ApiResponse(String.format(Configurations.EMAIL_NOTIFICATION_ON_SUCCESS, weeklyStatus.getId(), project.getId()), HttpStatus.OK);
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

}

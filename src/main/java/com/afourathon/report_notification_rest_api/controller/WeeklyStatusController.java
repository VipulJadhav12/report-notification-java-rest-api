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
import com.afourathon.report_notification_rest_api.data.entity.WeeklyStatus;
import com.afourathon.report_notification_rest_api.exception.ProjectNotFoundException;
import com.afourathon.report_notification_rest_api.exception.WeeklyStatusNotFoundException;
import com.afourathon.report_notification_rest_api.service.ProjectService;
import com.afourathon.report_notification_rest_api.service.WeeklyStatusService;

@RestController
@RequestMapping("/api/v1/weekly_statuses")
public class WeeklyStatusController {
	
	@Autowired
	WeeklyStatusService weeklyStatusService;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	Configurations configurations;
	
	@GetMapping(value = {"", "/"})
	public ResponseEntity<String> defaultHealthCheck() {
		return new ResponseEntity<>("Default HTTP Status: OK", HttpStatus.OK);
	}
	
	@GetMapping("/getBy=ID/weekly_status/{weeklyStatusId}")
	public ResponseEntity<WeeklyStatus> getWeeklyStatus(@PathVariable Long weeklyStatusId) throws WeeklyStatusNotFoundException {
		if(!weeklyStatusService.checkIfWeeklyStatusExistsById(weeklyStatusId)) {
			throw new WeeklyStatusNotFoundException(String.format(Configurations.INVALID_WEEKLY_STATUS_ID, weeklyStatusId));
		}
		
		Optional<WeeklyStatus> objWeeklyStatus = weeklyStatusService.findWeeklyStatusById(weeklyStatusId);
		
		if(!objWeeklyStatus.isPresent())
			throw new WeeklyStatusNotFoundException(String.format(Configurations.INVALID_WEEKLY_STATUS_ID, weeklyStatusId));
		
		return ResponseEntity.ok(objWeeklyStatus.get());
	}
	
	@GetMapping("/getBy=PROJECT_ID/project/{projectId}/weekly_status/{weeklyStatusId}")
	public ResponseEntity<WeeklyStatus> getWeeklyStatusByIdAndProjectId(@PathVariable Long projectId, @PathVariable Long weeklyStatusId) 
			throws WeeklyStatusNotFoundException, ProjectNotFoundException {
		if(!projectService.checkIfProjectExistsById(projectId)) {
			throw new ProjectNotFoundException(String.format(Configurations.INVALID_PROJECT_ID, projectId));
		}

		if(!weeklyStatusService.checkIfWeeklyStatusExistsById(weeklyStatusId)) {
			throw new WeeklyStatusNotFoundException(String.format(Configurations.INVALID_WEEKLY_STATUS_ID, weeklyStatusId));
		}
		
		Optional<WeeklyStatus> objWeeklyStatus = weeklyStatusService.findWeeklyStatusByIdAndByProjectId(weeklyStatusId, projectId);
		
		if(!objWeeklyStatus.isPresent())
			throw new WeeklyStatusNotFoundException(String.format(Configurations.INVALID_WEEKLY_STATUS_ID, weeklyStatusId));
		
		return ResponseEntity.ok(objWeeklyStatus.get());
	}
	
	@GetMapping("/getAllBy=PROJECT_ID/pagination=FALSE/project/{projectId}")
	public ResponseEntity<List<WeeklyStatus>> getAllWeeklyStatusesByProjectId(@PathVariable Long projectId) throws ProjectNotFoundException {
		if(!projectService.checkIfProjectExistsById(projectId)) {
			throw new ProjectNotFoundException(String.format(Configurations.INVALID_PROJECT_ID, projectId));
		}
		
		List<WeeklyStatus> weeklyStatuses = weeklyStatusService.findAllWeeklyStatusesByProjectId(projectId);
		
		return ResponseEntity.ok(weeklyStatuses);
	}
	
}

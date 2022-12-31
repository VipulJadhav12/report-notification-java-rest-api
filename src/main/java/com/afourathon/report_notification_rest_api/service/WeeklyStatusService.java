package com.afourathon.report_notification_rest_api.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.afourathon.report_notification_rest_api.data.entity.WeeklyStatus;

public interface WeeklyStatusService {
	
	// List of service fns to CHECK Weekly-Status
	public boolean checkIfWeeklyStatusExistsById(Long id);
	
	/* ========================================
	 * List of service fns to FIND Weekly-Status(es)
	 * based on conditions.
	 * ========================================
	 */
	public Optional<WeeklyStatus> findWeeklyStatusById(Long id);
	
	public Optional<WeeklyStatus> findWeeklyStatusByIdAndByProjectId(Long statusId, Long projectId);
	
	public Optional<WeeklyStatus> findWeeklyStatusByWeeklyEndDateAndByProjectId(Long projectId, LocalDate date);

	public List<WeeklyStatus> findAllWeeklyStatusesByProjectId(Long projectId);
	
}

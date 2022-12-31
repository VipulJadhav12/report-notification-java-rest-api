package com.afourathon.report_notification_rest_api.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afourathon.report_notification_rest_api.data.entity.WeeklyStatus;
import com.afourathon.report_notification_rest_api.data.payloads.repository.WeeklyStatusRepository;

@Service
public class JpaWeeklyStatusService implements WeeklyStatusService {

	@Autowired
	ProjectService projectService;

	@Autowired
	WeeklyStatusRepository weeklyStatusRepository;

	// List of service fns to CHECK Weekly-Status
	@Override
	public boolean checkIfWeeklyStatusExistsById(Long id) {
		return weeklyStatusRepository.existsById(id);
	}

	/* ========================================
	 * List of service fns to FIND Weekly-Status(es)
	 * based on conditions.
	 * ========================================
	 */

	@Override
	public Optional<WeeklyStatus> findWeeklyStatusById(Long id) {
		return weeklyStatusRepository.findById(id);
	}

	@Override
	public Optional<WeeklyStatus> findWeeklyStatusByIdAndByProjectId(Long statusId, Long projectId) {
		return weeklyStatusRepository.findWeeklyStatusByIdAndByProjectId(statusId, projectId);
	}

	@Override
	public Optional<WeeklyStatus> findWeeklyStatusByWeeklyEndDateAndByProjectId(Long projectId, LocalDate date) {
		return weeklyStatusRepository.findWeeklyStatusByWeeklyEndDateAndByProjectId(date, projectId);
	}

	@Override
	public List<WeeklyStatus> findAllWeeklyStatusesByProjectId(Long projectId) {
		return weeklyStatusRepository.findAllWeeklyStatusesByProjectId(projectId);
	}

}

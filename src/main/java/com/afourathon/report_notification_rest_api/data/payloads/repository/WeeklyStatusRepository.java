package com.afourathon.report_notification_rest_api.data.payloads.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.afourathon.report_notification_rest_api.data.entity.WeeklyStatus;

public interface WeeklyStatusRepository extends JpaRepository<WeeklyStatus, Long> {
	
	public boolean existsById(Long id);
	
	public Optional<WeeklyStatus> findById(Long id);
	
	@Query(
			  value = "SELECT * FROM weekly_status ws WHERE ws.weekly_status_id = ?1 AND ws.project_id = ?2", 
			  nativeQuery = true
		  )
	public Optional<WeeklyStatus> findWeeklyStatusByIdAndByProjectId(Long id, Long projectId);
	
	@Query(
			  value = "SELECT * FROM weekly_status ws WHERE ws.weekly_end_date = ?1 AND ws.project_id = ?2", 
			  nativeQuery = true
		  )
	public Optional<WeeklyStatus> findWeeklyStatusByWeeklyEndDateAndByProjectId(LocalDate date, Long projectId);
	
	@Query(
			  value = "SELECT COUNT(weekly_status_id) FROM weekly_status ws WHERE ws.project_id = ?1", 
			  nativeQuery = true
		  )
	public long countOfAllWeeklyStatusesByProjectId(Long projectId);
	
	@Query(
			  value = "SELECT * FROM weekly_status ws WHERE ws.project_id = ?1 ORDER BY ws.created_on", 
			  nativeQuery = true
		  )
	public List<WeeklyStatus> findAllWeeklyStatusesByProjectId(Long projectId);

}

package com.afourathon.report_notification_rest_api.data.payloads.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.afourathon.report_notification_rest_api.data.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	
	public boolean existsById(Long id);

	public Optional<Project> findById(Long id);
	
}

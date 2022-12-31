package com.afourathon.report_notification_rest_api.service;

import com.afourathon.report_notification_rest_api.data.entity.Project;
import com.afourathon.report_notification_rest_api.data.entity.WeeklyStatus;

public interface ReportNotificationService {
	
	public boolean shareReport(Project project, WeeklyStatus weeklyStatus);

}

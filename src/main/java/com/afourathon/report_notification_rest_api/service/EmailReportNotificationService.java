package com.afourathon.report_notification_rest_api.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.afourathon.report_notification_rest_api.configuration.Configurations;
import com.afourathon.report_notification_rest_api.data.entity.EmailDetails;
import com.afourathon.report_notification_rest_api.data.entity.MailingList;
import com.afourathon.report_notification_rest_api.data.entity.Project;
import com.afourathon.report_notification_rest_api.data.entity.WeeklyStatus;

@Service
public class EmailReportNotificationService implements ReportNotificationService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Configurations configurations;

	@Value("${spring.mail.username}")
	private String sender;

	@Override
	public boolean shareReport(Project project, WeeklyStatus weeklyStatus) {
		EmailDetails emailDetails = buildEmailTemplate(project, weeklyStatus);
		return sendEmailReport(emailDetails);
	}

	private EmailDetails buildEmailTemplate(Project project, WeeklyStatus weeklyStatus) {
		EmailDetails emailDetails = new EmailDetails();

		// Set the email recipient list
		Set<MailingList> mailingList = project.getMailingList();

		if(null != mailingList && !mailingList.isEmpty()) {
			Set<String> emailSet = new HashSet<>(mailingList.size());
			for(MailingList email : mailingList) {
				emailSet.add(email.getEmail());
			}

			emailDetails.setRecipient(String.join(", ", emailSet));
		}

		// Set the email subject
		String subject = String.format(Configurations.EMAIL_SUBJECT, project.getName(), weeklyStatus.getWeeklyEndDate().toString());
		emailDetails.setSubject(subject);

		// Set email message template
		StringBuffer buffer = new StringBuffer();
		buffer.append("Hello Team, \n\n");
		buffer.append(String.format(Configurations.EMAIL_SUBJECT, project.getName(), weeklyStatus.getWeeklyEndDate().toString()) + "\n");

		buffer.append("Project Details: \n");
		buffer.append(String.format("Project ID: %d", project.getId()) + "\n");
		buffer.append(String.format("Project Name: %s", project.getName()) + "\n");
		buffer.append(String.format("Project Manager: %s", project.getManagerName()) + "\n\n");

		buffer.append("Wekly-Status Details: \n");
		buffer.append(String.format("Wekly-Status ID: %d", weeklyStatus.getId()) + "\n");
		buffer.append(String.format("Wekly-Status End date: %s", weeklyStatus.getWeeklyEndDate().toString()) + "\n");
		buffer.append(String.format("Status: %s", weeklyStatus.getStatus()) + "\n");
		buffer.append(String.format("Highlight(s): %s", weeklyStatus.getHighlight()) + "\n");
		buffer.append(String.format("Risk(s): %s", weeklyStatus.getRisk()) + "\n\n");

		emailDetails.setMsgBody(buffer.toString());

		return emailDetails;
	}

	private boolean sendEmailReport(EmailDetails emailDetails) {
		try {
			// Creating a simple mail object
			SimpleMailMessage message = new SimpleMailMessage();

			// Setting up all the attributes retrieved from EmailDetails object
			message.setFrom(sender);
			message.setTo(emailDetails.getRecipient());
			message.setSubject(emailDetails.getSubject());
			message.setText(emailDetails.getMsgBody());

			// Send the actual email using JavaMailSender object
			javaMailSender.send(message);
		}
		catch (Exception e) {
			return false;
		}
		return true;
	}


}

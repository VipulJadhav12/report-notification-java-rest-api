package com.afourathon.report_notification_rest_api.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDetails {
	
	private String recipient;
    private String msgBody;
    private String subject;

}

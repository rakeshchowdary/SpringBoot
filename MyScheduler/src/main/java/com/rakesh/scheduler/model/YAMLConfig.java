package com.rakesh.scheduler.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "inputs")
public class YAMLConfig {
	private String weeklyExpression;

	public String getWeeklyExpression() {
		return weeklyExpression;
	}

	public void setWeeklyExpression(String weeklyExpression) {
		this.weeklyExpression = weeklyExpression;
	}

}

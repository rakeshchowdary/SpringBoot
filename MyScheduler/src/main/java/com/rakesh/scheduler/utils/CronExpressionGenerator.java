package com.rakesh.scheduler.utils;

public class CronExpressionGenerator {

	public String generateWeeklyExpression(String expression, String min, String hours, String weekDays) {
		expression = expression.replaceAll("m", min);
		expression = expression.replaceAll("h", hours);
		expression = expression.replaceAll("d", weekDays).toUpperCase();
	    System.out.println(expression);

		return expression;
	}
//	public static void main(String...strings) {
//	CronExpressionGenerator bhaskar = new CronExpressionGenerator();
//	bhaskar.generateWeeklyExpression("0 m h ? * d *","10","10","Mon,Fri,Wed");
//	}


}

package com.jeancatarina.angleclockapi.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class AngleClock {
	
	@JsonInclude(Include.NON_NULL)
	private int hour;
	@JsonInclude(Include.NON_NULL)
	private int minutes;
	
	public AngleClock() {};
	
	public AngleClock(int hour, int minutes){
		this.hour = hour;
		this.minutes = minutes;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	
	public int getAngleMinute(int minute) {
		return (minute%60)*6;
	}
		

}

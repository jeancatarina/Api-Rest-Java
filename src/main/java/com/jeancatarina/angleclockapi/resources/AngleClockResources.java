package com.jeancatarina.angleclockapi.resources;

import java.util.concurrent.TimeUnit;

import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "rest/clock/{hour}", produces = { MediaType.APPLICATION_JSON_VALUE}, headers = "Accept=application/json")
public class AngleClockResources {
	

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getAngleHour(@PathVariable("hour") Integer hour){
		
		return getAngle(hour, 00);
	}

	@RequestMapping(value = "/{minutes}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE}, headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<?> getAngleHourMinutes(@PathVariable Integer hour, @PathVariable Integer minutes){
				
		return getAngle(hour, minutes);
		
	}
	
	private ResponseEntity<?> getAngle(Integer hour, Integer minutes){
		
		String finalAngle;
		int angleMinutes;
		double angleHours;
		double lowerAngle;
		
		if((hour < 0 || hour > 23) || (minutes < 0 || minutes > 59)){
			return ResponseEntity.badRequest().body("Please, enter a valid time! " + hour + " hours and " + minutes + " minutes is not valid");		
		}

		if (hour > 12){
			hour = hour - 12;
		}
		
		angleMinutes = 6 * minutes;
		angleHours = (30 * hour) + (0.5 * minutes);
		 
		lowerAngle = angleHours - angleMinutes;
		 
		if (lowerAngle < 0){
			lowerAngle = lowerAngle - (lowerAngle * 2);
		}
		if (lowerAngle > 180){
			lowerAngle = 360 - lowerAngle;
		}
		
		finalAngle = "{\"angle\":" + String.format("%.0f", lowerAngle) + "}\n";
		
		/* Result Cache */
		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.MINUTES);
		
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(finalAngle);
		
	}
	

}

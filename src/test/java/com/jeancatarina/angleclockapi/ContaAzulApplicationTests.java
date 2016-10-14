package com.jeancatarina.angleclockapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jeancatarina.angleclockapi.domain.AngleClock;
import com.jeancatarina.angleclockapi.resources.AngleClockResources;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContaAzulApplicationTests extends TestCase {
	
	/* Testar a classe que pega o angulo quando informado apenas horas */ 
	@Test
	public void testGetAngleHour() {
		
		int hour = 6;
		int angleExpected = 180;
		
		try {
			AngleClockResources angleClockResources = new AngleClockResources();
			
			assertEquals(angleExpected, angleClockResources.getAngleHour(hour));
			
		} catch (IllegalArgumentException e) {
            assertTrue(false);
        }						
		
	}
	
	/* Testar a classe que pega o angulo quando informado horas e minutos */ 
	@Test
	public void testGetAngleHourMinutes() {
		
		int hour = 2;
		int minutes = 50;
		int angleExpected = 145;
		
		try {
			AngleClockResources angleClockResources = new AngleClockResources();
			
			assertEquals(angleExpected, angleClockResources.getAngleHourMinutes(hour, minutes));
			
		} catch (IllegalArgumentException e) {
            assertTrue(false);
        }						
		
	}
}

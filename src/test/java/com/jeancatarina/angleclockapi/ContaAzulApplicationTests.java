package com.jeancatarina.angleclockapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jeancatarina.angleclockapi.domain.AngleClock;
import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContaAzulApplicationTests extends TestCase {

	@Test
	public void testGetAngleMinute() {
		
		int minute = 50;
		int expected = 300;
		
		try {
			AngleClock angleClock = new AngleClock(25, 6);
			
			assertEquals(expected, angleClock.getAngleMinute(minute));
			
		} catch (IllegalArgumentException e) {
            assertTrue(false);
        }						
		

	}
	

}

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
		
		/* Pega o angulo quando informado apenas horas */
		return getAngle(hour, 00);
	}

	@RequestMapping(value = "/{minutes}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE}, headers = "Accept=application/json")
	@ResponseBody
	public ResponseEntity<?> getAngleHourMinutes(@PathVariable Integer hour, @PathVariable Integer minutes){
				
		/* Pega o angulo quando informado horas e minutos */
		return getAngle(hour, minutes);
		
	}
	
	private ResponseEntity<?> getAngle(Integer hour, Integer minutes){
				
		double angleMinutes;
		double angleHours;
		double lowerAngle;
		String finalAngle;
		
		/* Caso o usuário digite uma hora ou minuto inválido, retorna um erro */
		if((hour < 0 || hour > 23) || (minutes < 0 || minutes > 59)){
			return ResponseEntity.badRequest().body("Please, enter a valid time! " + hour + " hours and " + minutes + " minutes is not valid in this planet, it might be valid in the realm of mordor ");		
		}

		// Chama o calculo da hora quando ela é maior que 12
		hour = getCalcHourGreaterThan12(hour);
		
		// Chama o calculo do angulo dos minutos
		angleMinutes = getCalcAngleMinutes(minutes);
		
		// Chama o calculo do angulo das horas
		angleHours = getCalcAngleHours(hour, minutes);
		
		/* Subtraimos o angulo da hora com o de minutos para obtermos o menor angulo */
		lowerAngle = angleHours - angleMinutes;
		 
		/* Caso o menor angulo seja menor que 0 */
		lowerAngle = getCalcAngleLessThan0(lowerAngle);
		
		/* Caso o angulo seja maior que 180 */
		lowerAngle = getCalcAngleGreaterThan180(lowerAngle);
		
		/* Retorna o angulo final em formato json */
		finalAngle = "{\"angle\":" + String.format("%.0f", lowerAngle) + "}\n";
		
		/* Guardamos o resultado em cache para não precisar processar o mesmo resultado*/
		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.DAYS);
		
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(finalAngle);
		
		
	}
	
	// Quando a hora for maior que 12 ela deve ser zerada
	private int getCalcHourGreaterThan12(int hour){
		if (hour > 12){
			hour = hour - 12;
		}
		return hour;
	}
	
	/* Um relógio tem 360º, cada hora tem 30°, cada minuto tem 6° */
	private double getCalcAngleMinutes(int minutes){
		int angleMinutes;
		
		angleMinutes = 6 * minutes;
		
		return angleMinutes;
	}
	
	/* Sabemos que quando o ponteiro de minutos anda o ponteiro da hora anda também, 
	 * então pegamos 30º e dividimos por 60min para encontrarmos quantos graus o
	 * ponteiro da hora se moveu (0.5º) */
	private double getCalcAngleHours(int hour, int minutes){
		double angleHours;
		
		angleHours = (30 * hour) + (0.5 * minutes);
		
		return angleHours;
	}
	
	/* Caso o angulo seja menor que 0 */
	private double getCalcAngleLessThan0(double lowerAngle){
		if (lowerAngle < 0){
			lowerAngle = lowerAngle - (lowerAngle * 2);
		}
		return lowerAngle; 
	}
	
	/* Caso o angulo seja maior que 180 */
	private double getCalcAngleGreaterThan180(double lowerAngle){
		if (lowerAngle > 180){
			lowerAngle = 360 - lowerAngle;
		}
		return lowerAngle; 
	}

}

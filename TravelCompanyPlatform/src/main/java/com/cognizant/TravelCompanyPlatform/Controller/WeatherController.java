package com.cognizant.TravelCompanyPlatform.Controller;

import java.security.PublicKey;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.cognizant.TravelCompanyPlatform.DAO.WeatherRepo;
import com.cognizant.TravelCompanyPlatform.Model.Weather;
import com.cognizant.TravelCompanyPlatform.Service.WeatherService;

@Controller
public class WeatherController {
	
	@Autowired
	WeatherService wService;
	@PostMapping(value = "/weather")
	private ResponseEntity<?> WeatherInsert(@RequestBody Map<String, String>body) 
	{
		if (wService.insert(body))
			return new ResponseEntity<>(HttpStatus.CREATED);
		else 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping(value = "/weather/update/state/{id}")
	private ResponseEntity<?> WeatherUpdate(@PathVariable int id,@RequestBody Map<String, String> change)
	{
		if(wService.updateStateCity(id,change))
		{
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else 
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping(value = "/weather/{id}")
	private ResponseEntity<?> WeatherDelete(@PathVariable int id)
	{
		if (wService.delete(id))
		{
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else 
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}	
	}
	
	@GetMapping({"/weather/{str}","/weather"})
	private ResponseEntity<?> WeatherReport(@PathVariable(required = false)String str)
	{
		if(str == null)
		{
			return new ResponseEntity<List<Weather>>(wService.reportAll(),HttpStatus.OK);
		}
		else 
		{
			int id = Integer.parseInt(str);
			Optional<Weather>weather = wService.reportById(id);
			if (weather.isPresent()) 
			{
				return new ResponseEntity<Weather>(weather.get(), HttpStatus.OK);
			}
			else 
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		}
	}
}	
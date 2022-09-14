package com.cognizant.TravelCompanyPlatform.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognizant.TravelCompanyPlatform.DAO.WeatherRepo;
import com.cognizant.TravelCompanyPlatform.Model.Weather;
import com.mysql.cj.x.protobuf.MysqlxCrud.Delete;
import com.mysql.cj.x.protobuf.MysqlxCrud.Insert;
import com.mysql.cj.xdevapi.UpdateStatement;

@Service
public class WeatherService {
	@Autowired
	WeatherRepo wRepo;

	public boolean insert(Map<String, String> body) {
		
		String date = body.get("date");
		float latitude = Float.parseFloat(body.get("latitude"));
		float longitude = Float.parseFloat(body.get("longitude"));
		float temperature = Float.parseFloat(body.get("temperature"));
		String city = body.get("city");
		String state = body.get("state");

		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date_new = LocalDate.parse(date, df);
		Weather weather = new Weather();
		weather.setDate(date_new);
		weather.setLatitude(latitude);
		weather.setLongitude(longitude);
		weather.setState(state);
		weather.setTemperature(temperature);
		weather.setCity(city);

		wRepo.saveAndFlush(weather);
		return true;
	}

	public List<Weather> reportAll() {
		List<Weather> lst = new ArrayList<Weather>();
		lst = wRepo.findAll();
		return lst;
	}
	
	public Optional<Weather> reportById(int id) {
		Optional<Weather> weather = Optional.empty();
		if (wRepo.existsById(id)) 
		{
			weather = wRepo.findById(id);
			wRepo.flush();
			return weather;
		} else {
			return weather;
		}
	}
	
	public boolean updateStateCity(int id,Map<String, String> change) 
	{
		if(!wRepo.existsById(id))
		{
			return false;
		}	
		Weather weather = wRepo.findById(id).get();
		weather.setId(id);
		
		if (change.containsKey("state"))
			weather.setState(change.get("state"));
		
		if (change.containsKey("city"))
			weather.setCity(change.get("city"));
		
		wRepo.saveAndFlush(weather);
			return true;
	}

	public boolean delete(int id) {
		if (wRepo.existsById(id)) {
			wRepo.deleteById(id);
			wRepo.flush();
			return true;
		} else {
			return false;
		}
	}

}

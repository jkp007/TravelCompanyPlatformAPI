package com.cognizant.TravelCompanyPlatform.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class WeatherServiceTest {
	
	Map<String, String> map = new HashMap<>();
	@BeforeEach
	public void setup() 
	{
		map.put("date", "2022-10-15");
		map.put("latitude", "56.348");
		map.put("longitude", "32.324");
		map.put("temperature", "45.54");
		map.put("city", "Kol");
		map.put("state", "WB");
	}
	
	@Autowired
	WeatherService wService;

	
	
	@Test
	void testInsert() {
		assertTrue(wService.insert(map));
	}

	@Test
	void testReportAll() {
		wService.insert(map);
		assertThat(wService.reportAll().size()).isGreaterThan(0);
	}

	@Test
	void testReportById() {
		wService.insert(map);
		assertTrue(wService.reportById(1).isPresent()); // only 1 insertion is done here so ReportById is 1
	}

	@Test
	void testUpdateStateCity() {
		wService.insert(map);
		Map<String,String> updateMap = new HashMap<>();
		updateMap.put("state", "MH");
		updateMap.put("city", "Mumbai");
		assertTrue(wService.updateStateCity(1, updateMap));
		}

	@Test
	void testDelete() {
		wService.insert(map);
		assertTrue(wService.delete(1));
		}

}

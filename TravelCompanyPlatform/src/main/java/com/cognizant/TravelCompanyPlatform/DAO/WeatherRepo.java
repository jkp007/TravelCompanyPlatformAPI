package com.cognizant.TravelCompanyPlatform.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.TravelCompanyPlatform.Model.Weather;

@Repository
public interface WeatherRepo extends JpaRepository<Weather,Integer> {
	// all CRUD database methods
}

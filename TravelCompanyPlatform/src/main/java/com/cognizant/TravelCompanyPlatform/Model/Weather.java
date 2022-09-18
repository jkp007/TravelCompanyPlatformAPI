package com.cognizant.TravelCompanyPlatform.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity		
public class Weather {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int id;
	private String date;
	private float latitude;
	private float longitude;
	private String city;
	private String state;
	private float temperature;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getDate() {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date_new = LocalDate.parse(date, df);
		return date_new;
	}
	public void setDate(LocalDate date) {
		this.date = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public float getTemperature() {
		return temperature;
	}
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
}

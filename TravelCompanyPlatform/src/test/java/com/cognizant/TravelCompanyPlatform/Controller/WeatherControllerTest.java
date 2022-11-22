package com.cognizant.TravelCompanyPlatform.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.cognizant.TravelCompanyPlatform.DAO.WeatherRepo;
import com.cognizant.TravelCompanyPlatform.Model.Weather;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@RunWith(SpringRunner.class)
//@WebMvcTest(controllers = WeatherController.class)

class WeatherControllerTest {

	private MockMvc mockMvc;

	@Autowired
	WeatherRepo wRepo;

	Weather weatherTest = new Weather();
	ObjectMapper omMapper = new ObjectMapper();

	String jsonStr = "";
	JSONObject jsonObject ;

	@BeforeEach
	void setup(WebApplicationContext context) throws JSONException {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date_new = LocalDate.parse("2021-01-06", df);
		weatherTest.setTemperature(40.00f);
		weatherTest.setDate(date_new);
		weatherTest.setLatitude(66.1189f);
		weatherTest.setLongitude(26.6892f);
		weatherTest.setCity("Mumbai");
		weatherTest.setState("MH");

		Gson gson = new Gson();
		// JsonObject object = gson.;
		jsonStr = gson.toJson(weatherTest);
		jsonObject = new JSONObject(jsonStr);
		jsonObject.remove("id");
	}

	@Test
//	@Disabled
//	@PrepareForTest(WeatherController.class) // This annotation tells powerMockito to prepare certain classes for testing
	void weatherInsertTest() throws Exception {


//		System.out.println(jsonObject.toString());

//		json.remove("email");
//		System.out.println(json);

		mockMvc.perform(post("/weather").content(jsonObject.toString())
				.contentType("application/json")).andExpect(status().isCreated());

		mockMvc.perform(post("/weather").content(jsonObject.toString())
				.contentType("application/json")).andExpect(status().isCreated());
		
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/weather").contentType("application/json")).andExpect(status().isOk());
		String content = result.andReturn().getResponse().getContentAsString();


		JSONArray jsonOb = new JSONArray(content);
		System.out.println(jsonOb.get(0));
		System.out.println(jsonOb.get(1));
		
		result = mockMvc.perform(MockMvcRequestBuilders.get("/weather/2").contentType("application/json")).andExpect(status().isOk());
		content = result.andReturn().getResponse().getContentAsString();
//		System.out.println(content);


	}

//	@Test
//	@Disabled
//	void weatheDelete() throws Exception {
//		mockMvc.perform(post("/weather").content(jsonObject.toString())
//				.contentType("application/json")).andExpect(status().isCreated());
//
//		mockMvc.perform(post("/weather").content(jsonObject.toString())
//				.contentType("application/json")).andExpect(status().isCreated());
//
//		mockMvc.perform(delete("/weather/2")).andExpect(status().isOk());
//
//		mockMvc.perform(delete("/weather/2")).andExpect(status().isNotFound());
//	}

//	@Test
//	@Disabled
//	void weatherUpdateTest() throws Exception {
//		mockMvc.perform(put("/weather/{id}").contentType("application/json")).andExpect(status().isOk());
//	}
//
//	@Test
//	@Disabled
//	void weatherReportTest() throws Exception {
//		mockMvc.perform(put("/weather/{str}").contentType("application/json")).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
//
//	}
}
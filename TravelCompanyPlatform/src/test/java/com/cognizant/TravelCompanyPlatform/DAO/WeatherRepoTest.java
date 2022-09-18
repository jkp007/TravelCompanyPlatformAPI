package com.cognizant.TravelCompanyPlatform.DAO;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import com.cognizant.TravelCompanyPlatform.Model.Weather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
//@DataJpaTest
//@RunWith(SpringRunner.class)
class WeatherRepoTest {

	
	@Autowired
	WeatherRepo wRepo;

	
	Weather weatherTest = new Weather();
	
	@BeforeEach
	void setup() 
	{
		weatherTest.setDate(LocalDate.of(2021,01,06));
		weatherTest.setLatitude(66.1189f);
		weatherTest.setLongitude(26.6892f);
		weatherTest.setCity("Mumbai");
		weatherTest.setState("MH");
	}
	
	@Test
	@Order(1)
	void testSave() {
		
		wRepo.save(weatherTest);
		System.out.println(weatherTest.getId());
		assertThat(wRepo.existsById(weatherTest.getId())).isTrue();
	}

	@Test
	@Order(2)
	void testFindById() {
//		weatherTest.setDate(LocalDate.of(2021,01,06));
//		weatherTest.setLatitude(66.1189f);
//		weatherTest.setLongitude(26.6892f);
//		weatherTest.setCity("Mumbai");
//		weatherTest.setState("MH");
		
		wRepo.save(weatherTest);
		
		System.out.println(weatherTest.getId());
		assertThat(wRepo.findById(weatherTest.getId()).isPresent()).isTrue();
	}

	@Test
	@Order(3)
	void testDeleteById() {
		int id = weatherTest.getId();
		wRepo.save(weatherTest);
		wRepo.deleteById(weatherTest.getId());
		assertThat(wRepo.existsById(id));
	}

}

package core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "core.config", "core.controllers" })
public class Config {

	@Bean("student")
	public Student student() {
		Student student = new Student();
		student.setCity("Delhi");
		student.setName("Rahul");
		return student;

	}

	@Bean
	public Address address() {
		Address address = new Address();
		address.setPinCode("1421");
		address.setStreet("1521/4524 Sector 7  Noida");
		return address;

	}

	@Bean
	public College getCollege() {
		return new College();
	}
}

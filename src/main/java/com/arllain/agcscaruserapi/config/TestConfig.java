package com.arllain.agcscaruserapi.config;

import com.arllain.agcscaruserapi.domain.Car;
import com.arllain.agcscaruserapi.domain.User;
import com.arllain.agcscaruserapi.repository.CarRepository;
import com.arllain.agcscaruserapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {

		User user1 = new User();
		user1.setFirstName("João");
		user1.setLastName("Leite");
		user1.setEmail("joao@gmail.com");
		user1.setBirthday(LocalDate.parse("1980-12-25"));
		user1.setLogin("joao");
		user1.setPassword(passwordEncoder.encode("123456"));
		user1.setPhone("988888888");
		user1.setCreated_at(Instant.now());
		user1 = userRepository.save(user1);

		Car car1 = new Car();
		car1.setYear(2014);
		car1.setLicensePlate("KMV-2878");
		car1.setModel("Ford");
		car1.setColor("White");
		car1.setUser(user1);
		carRepository.save(car1);

		User user2 = new User();
		user2.setFirstName("Maria");
		user2.setLastName("Silva");
		user2.setEmail("maria@gmail.com");
		user2.setBirthday(LocalDate.parse("1985-02-10"));
		user2.setLogin("maria");
		user2.setPassword(passwordEncoder.encode("123456"));
		user2.setPhone("988888888");
		user2.setCreated_at(Instant.now());
		user2 = userRepository.save(user2);

		Car car2 = new Car();
		car2.setYear(2010);
		car2.setLicensePlate("MOR-1497");
		car2.setModel("Chevrolet");
		car2.setColor("Yellow");
		car2.setUser(user2);

		Car car3 = new Car();
		car3.setYear(2017);
		car3.setLicensePlate("AMY-1614");
		car3.setModel("Ford");
		car3.setColor("Blue");
		car3.setUser(user2);

		carRepository.saveAll(Arrays.asList(car2, car3));

		User user3 = new User();
		user3.setFirstName("Michele");
		user3.setLastName("Calvalcante");
		user3.setEmail("michele@gmail.com");
		user3.setBirthday(LocalDate.parse("1978-06-18"));
		user3.setLogin("michele");
		user3.setPassword(passwordEncoder.encode("123456"));
		user3.setPhone("988888888");
		user3.setCreated_at(Instant.now());
		userRepository.save(user3);
	}
}

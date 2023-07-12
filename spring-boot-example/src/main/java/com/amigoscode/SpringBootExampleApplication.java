package com.amigoscode;

import com.amigoscode.customer.Customer;
import com.amigoscode.customer.CustomerRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@SpringBootApplication
public class SpringBootExampleApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBootExampleApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(CustomerRepository customerRepository){
		return args ->{
			Faker faker = new Faker();
			var name = faker.name();
			String firstName = name.firstName();
			String lastName = name.lastName();
			Random random = new Random();
			Customer customer= new Customer(
					firstName+" "+lastName,
					firstName.toLowerCase()+"."+lastName.toLowerCase()+"@amigoscode.com",
					random.nextInt(16,99)

			);

			customerRepository.save(customer);
		};
	}

//	@GetMapping("/greet")
//	public GreetResponse greet(@RequestParam(value = "name",required = false) String name){
//		String greetMessage = name== null ||  name.isBlank() ? "Hello":"Hello "+ name ;
//		return new GreetResponse(greetMessage,
//				List.of("Java","Go","JS"),
//				new Person("Matias",28,30_000));
//	}

//	record GreetResponse(String greet,
//						List<String> favProgrammingLanguages,
//						Person person){ }//return a json
//	record Person(String name,int age, double savings){
//
//	}
//	class GreetResponse {
//		private final String greet;
//
//		GreetResponse(String greet) {
//			this.greet = greet;
//		}
//
//		public String getGreet() {
//			return greet;
//		}
//
//		@Override
//		public String toString() {
//			return super.toString();
//		}
//
//		@Override
//		public boolean equals(Object obj) {
//			return super.equals(obj);
//		}
//
//		@Override
//		public int hashCode() {
//			return Objects.hash(greet);
//		}
//	}


}

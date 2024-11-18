package thanhluu.controller;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import thanhluu.model.Customer;



@RestController
@EnableMethodSecurity
public class ControllerCustomer {
	final private List<Customer> customers = List.of(
			Customer.builder()
				.id("001")
				.name("Lưu Quốc Thành")
				.email("quocthanhnt2004@gmail.com")
				.build(),
			Customer.builder()
				.id("002")
				.name("Quốc Thành")
				.email("quocthanhnt2004@gmail.com")
				.build()	
			);
	
	@GetMapping("/hello")
	public ResponseEntity<String> hello(){
		return ResponseEntity.ok("Hello is Guest");
	}
	
	@GetMapping("/customer/all")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<Customer>> getCustomerList(){
		List<Customer> list = this.customers;
		return ResponseEntity.ok(list);
	}
	
	
	@GetMapping("/customer/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")	
	public ResponseEntity<Customer> getCustomerList(@PathVariable("id") String id){
		List<Customer> customers = this.customers.stream()
										.filter(
												customer -> customer.getId().equals(id)).toList();
		return ResponseEntity.ok(customers.get(0));
	}
	
	
	@Bean
	public SecurityFilterChain securityFilerChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/").permitAll()
						.requestMatchers("/customers/**").authenticated()
						
						)
				.formLogin(Customizer.withDefaults())
				.build();
	}
	
}

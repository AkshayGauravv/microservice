package com.springboot.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@EnableJpaRepositories
@SpringBootApplication
@EnableCaching
public class SpringBootMicroServices {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMicroServices.class, args);
		
		

	/*	RestTemplate retrievalRequest = new RestTemplate();
		Family family = new Family();
		family.setParents("dona/ganguly");
		family.setBrother("aditya");
		String addFamily = "addfamily";
		String getFamily = "getAllFamily";*/
         
		/*RestUtility r=new RestUtility();
		r.internalServiceCallget(getFamily, null);*/
		/*
		 * Family familyadded = retrievalRequest.postForObject(addFamily, family,
		 * Family.class); System.out.println(familyadded);
		 */
		/*Collection<Family> familylist =retrievalRequest.getForObject(getFamily,Collection.class);
		System.out.println(familylist);
*/
	}
	
	@Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

}

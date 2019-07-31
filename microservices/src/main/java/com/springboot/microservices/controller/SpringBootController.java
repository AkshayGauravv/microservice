package com.springboot.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.microservices.utility.ApplicationProperties;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/rest")
@Api(value = "SpringBootController", description = "SpringBoot Microservices")
public class SpringBootController {

	@Autowired
	ApplicationProperties apppropes;

	@ApiOperation(value = "Just Returning the SpringBoot Rest", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })

	@RequestMapping(value = "/springboot")
	public String product() {
		System.out.println(apppropes.getQueueName()+""+apppropes.getExchangeName()+""+apppropes.getRoutingkey());
		return "Design for Microservices Communication";
	}

}

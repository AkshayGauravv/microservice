package com.springboot.microservices.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.springboot.microservices.entity.Family;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.cloud.client.ServiceInstance;

@RestController
@RequestMapping("/rest")
@Api(value = "SpringBootInstances", description = "SpringBoot Instances")
class ServiceInstanceRestController {

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private EurekaClient eurekaClient;

	@ApiOperation(value = "Just Returning the SpringBoot Instance", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/service-instances/{applicationName}", method = RequestMethod.GET, produces = "application/json")
	public List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String applicationName) {
		return this.discoveryClient.getInstances(applicationName);
	}

	@ApiOperation(value = "Internal Service get call to VirtualNuggets", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/getData/{url}/{applicationName}", method = RequestMethod.GET, produces = "application/json")
	public Collection<Family> internalServiceCallget(@PathVariable String applicationName, @PathVariable String url) {

		Application application = eurekaClient.getApplication(applicationName);
		InstanceInfo instanceInfo = application.getInstances().get(0);
		String ultimateUrl = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "rest" + "/"
				+ url;
		System.out.println("URL" + url);
		Collection<Family> family = restTemplate.getForObject(ultimateUrl, Collection.class);
		System.out.println("RESPONSE " + family);
		return family;

	}

	@ApiOperation(value = "Internal Service post call to VirtualNuggets", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/getData/{url}/{applicationName}", method = RequestMethod.POST, produces = "application/json")
	public Family internalServiceCallpost(@RequestBody Family family, @PathVariable String applicationName,
			@PathVariable String url) {

		Application application = eurekaClient.getApplication(applicationName);
		InstanceInfo instanceInfo = application.getInstances().get(0);
		String ultimateUrl = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "rest" + "/"
				+ url;
		System.out.println("URL" + url);
		Family familypost = restTemplate.postForObject(ultimateUrl, family, Family.class);
		System.out.println("RESPONSE " + familypost);
		return familypost;

	}

}
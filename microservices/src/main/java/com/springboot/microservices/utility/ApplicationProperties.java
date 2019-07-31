package com.springboot.microservices.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@PropertySource("classpath:message.properties")
@Service
public class ApplicationProperties {
	
	
	@Value("${QueueName}")
	String QueueName;
	
	@Value("${ExchangeName}")
	String ExchangeName;
	
	@Value("${Routingkey}")
	String Routingkey;

	public String getQueueName() {
		return QueueName;
	}

	public void setQueueName(String queueName) {
		QueueName = queueName;
	}

	public String getExchangeName() {
		return ExchangeName;
	}

	public void setExchangeName(String exchangeName) {
		ExchangeName = exchangeName;
	}

	public String getRoutingkey() {
		return Routingkey;
	}

	public void setRoutingkey(String routingkey) {
		Routingkey = routingkey;
	}
	
	
	

}

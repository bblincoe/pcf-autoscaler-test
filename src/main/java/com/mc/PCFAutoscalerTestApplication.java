package com.mc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PCFAutoscalerTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(PCFAutoscalerTestApplication.class, args);
	}
	
	@Bean
	public FibCPUBoundAlgo fib(){
			return new FibCPUBoundAlgo();
	}
}

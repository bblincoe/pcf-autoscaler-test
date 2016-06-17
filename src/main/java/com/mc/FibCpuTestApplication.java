package com.mc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FibCpuTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(FibCpuTestApplication.class, args);
	}
	
	@Bean
	public FibCPUBoundAlgo fib(){
			return new FibCPUBoundAlgo();
	}


}

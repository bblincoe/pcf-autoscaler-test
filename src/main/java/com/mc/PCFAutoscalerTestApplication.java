package com.mc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PCFAutoscalerTestApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(PCFAutoscalerTestApplication.class, args);
		context.getBean(FibCPUBoundAlgo.class).doFib();
	}
	
	@Bean
	public FibCPUBoundAlgo fib(@Value("${cpu.algo.reset:5}") int cpuAlgoReset){
			return new FibCPUBoundAlgo(cpuAlgoReset);
	}
}

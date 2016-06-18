package com.mc;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PCFAutoscalerTestApplication.class)
public class FibCpuTestApplicationTests {

	@Test
	@Ignore // Our cpu abuse is on start up of spring context so don't remove this
	public void contextLoads() {
	}

}

package com.mc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mc.FibCPUBoundAlgo;
import com.mc.model.CPUState;

@RestController
public class CPUAlgoController {
	
	@Autowired
	private FibCPUBoundAlgo cpuAlgo;
	
    @Value("${vcap.application.instance_index:}")
    private String appInstanceIndex;

	@RequestMapping(value = "/cpu", method = RequestMethod.POST)
	public CPUState controlCpu(){
		cpuAlgo.doAsyncFib();
		return new CPUState(true, appInstanceIndex);
	}


}

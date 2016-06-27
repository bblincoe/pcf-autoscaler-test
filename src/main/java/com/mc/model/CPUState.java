package com.mc.model;

public class CPUState {

	private final boolean isCpuAlgoRunning;

	public CPUState(boolean isCpuAlgoRunning) {
		this.isCpuAlgoRunning = isCpuAlgoRunning;
	}

	public boolean isCpuAlgoRunning() {
		return isCpuAlgoRunning;
	}
}

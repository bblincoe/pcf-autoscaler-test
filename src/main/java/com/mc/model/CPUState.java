package com.mc.model;

public class CPUState {

	private final boolean isCpuAlgoRunning;
	private final String appInstanceIndex;

	public CPUState(boolean isCpuAlgoRunning, String appInstanceIndex) {
		this.isCpuAlgoRunning = isCpuAlgoRunning;
		this.appInstanceIndex = appInstanceIndex;
	}

	public boolean isCpuAlgoRunning() {
		return isCpuAlgoRunning;
	}

	public String getAppInstanceIndex() {
		return appInstanceIndex;
	}
}

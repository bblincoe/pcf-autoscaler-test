package com.mc;

public class FibCPUBoundAlgo{
	
	private int cpuAlgoReset;
	
	public FibCPUBoundAlgo(int cpuAlgoReset) {
		this.cpuAlgoReset = cpuAlgoReset;
		System.out.format("Algorithm will reset after %d loops%n", cpuAlgoReset);
	}
	
	public void doFib(){
		int number = 6000;
		System.out.println("Fibonacci style cpu abuse commencing..");
        for(int i=1; i<=number; i++){
            System.out.print(fibonacciRecursion(i) +" ");
            if(i==cpuAlgoReset){
            	nightNight();
            	System.out.println();
            	System.out.println("Restarting CPU intensive algo..");
            	i=1;
            }
        }
	}

	private void nightNight() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public static int fibonacciRecursion(int number){
        if(number == 1 || number == 2){
            return 1;
        }
        return fibonacciRecursion(number-1) + fibonacciRecursion(number-2); //tail recursion
    }
}

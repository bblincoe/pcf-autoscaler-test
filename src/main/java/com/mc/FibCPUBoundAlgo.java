package com.mc;

import org.springframework.scheduling.annotation.Async;

public class FibCPUBoundAlgo {

	@Async
	public void doAsyncFib() {
		doFib();
	}
	
	public void doFib() {
		int number = 6000;
		System.out.println("Fibonacci style cpu load commencing..");
		for (int i = 1; i <= number; i++) {
			System.out.print(fibonacciRecursion(i) + " ");
		}
	}

	public static int fibonacciRecursion(int number) {
		if (number == 1 || number == 2) {
			return 1;
		}
		return fibonacciRecursion(number - 1) + fibonacciRecursion(number - 2); // tail																				// recursion
	}
}

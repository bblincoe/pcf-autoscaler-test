package com.mc;

public class FibCPUBoundAlgo {
// http://crunchify.com/write-java-program-to-print-fibonacci-series-upto-n-number/
	
	public FibCPUBoundAlgo(){
		int number = 6000;
		System.out.println("\n\nFibonacci series upto " + number +" numbers : ");
        //printing Fibonacci series upto number
        for(int i=1; i<=number; i++){
            System.out.print(fibonacciRecursion(i) +" ");
        }
	}
	
    // Java program for Fibonacci number using recursion.
    public static int fibonacciRecursion(int number){
        if(number == 1 || number == 2){
            return 1;
        }
 
        return fibonacciRecursion(number-1) + fibonacciRecursion(number -2); //tail recursion
    }
 
    // Java program for Fibonacci number using Loop.
    public static int fibonacciLoop(int number){
        if(number == 1 || number == 2){
            return 1;
        }
        int fibo1=1, fibo2=1, fibonacci=1;
        for(int i= 3; i<= number; i++){
            fibonacci = fibo1 + fibo2; //Fibonacci number is sum of previous two Fibonacci number
            fibo1 = fibo2;
            fibo2 = fibonacci;
 
        }
        return fibonacci; //Fibonacci number
    } 
}

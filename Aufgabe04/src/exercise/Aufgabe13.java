package exercise;

/*
 *  Copyright (C) 2022 Christian Knorr, Simon Lenz.
 *  All rights reserved.
 */

public class Aufgabe13 {
	
	public static int fibo(int i) {
			
        if (i == 0) {
            System.out.println("fibo(0) = 0 \n");
            
            return 0;
        } 
        
        else if (i == 1) {
        		
                System.out.println("fibo(1) = 1 \n");
            
            return 1;
        }
        
        else {
           
            int result = fibo(i - 2) + fibo(i - 1) ;
            
            System.out.println("fibo(" + i + ") = fibo(" + (i - 2) + ") + fibo(" + (i - 1) + ")" + " = " + result) ;
            
            System.out.print( "\n") ;
            
            return result;
        }
    }

	public static void main(String[] args) {
		// *** variable declaration
		int i;

		// *** program header ***
		System.out.println("Fibonacci numbers");
		System.out.println("-----------------");

		i = 5;
		
		// *** data input ***
		System.out.print("Enter the starting number: ");
		i = SystemInReader.readInt();

		// *** processing and output ***
		System.out.println("Fibonacci number of " + i + ": " + fibo(i));
	}

}

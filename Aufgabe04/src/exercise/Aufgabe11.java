package exercise;

/*
 *  Copyright (C) 2022 Christian Knorr, Simon Lenz.
 *  All rights reserved.
 */

public class Aufgabe11 {
	
	public static void printTriangle(int height, char c) {
		
		for (int i = 0 ; i < height ; i++) {
            
            for (int j = 0 ; j < height - i - 1 ; j++) {
            	
                System.out.print(" ") ;
            }
            
            for (int t = 0 ; t < 2 * i + 1 ; t++) {
            	
                System.out.print(c) ;
            } 
            
            System.out.print("\n") ; 
        }
    }

	public static void main(String[] args) throws Exception {
		// *** variable declaration
		int height;
		char c;

		// *** program header ***
		System.out.println("Triangle printing");
		System.out.println("-----------------");

		// *** data input ***
		System.out.print("Enter the number of lines for the triangle: ");
		height = SystemInReader.readInt();
		System.out.print("Enter the character to be used as drawing character: ");
		c = SystemInReader.readChar();

		// *** processing and output ***
		printTriangle(height, c);
	}

}
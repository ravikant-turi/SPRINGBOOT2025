package com.micro.plot;

public class Main {
	public static void main(String[] args) {
		{
			int i=0;
	        while (true) {
	            System.out.println("Hello World");
	            try {
	                Thread.sleep(120000); // Sleep for 2 minutes (120,000 milliseconds)
	                System.out.println(i++);
	            } catch (InterruptedException e) {
	                System.out.println("Sleep interrupted ");
	            }
	        }
	
		}
 }
}

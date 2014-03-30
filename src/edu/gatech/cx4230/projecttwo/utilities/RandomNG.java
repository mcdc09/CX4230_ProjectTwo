package edu.gatech.cx4230.projecttwo.utilities;

import java.util.Random;

public class RandomNG extends Random {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2211792377938356472L;
	private Random rand;
	
	public RandomNG() {
		rand = new Random();
	}
	
	
	
	/**
	 * Returns member of normal distribution of specified mean and standard
	 * deviation. Based on Box-Muller transform.
	 */
	public double nextInNormal(double mean, double sd) {
		double u = 2 * ((rand.nextDouble() % 100000) / 100000) - 1;
		double v = 2 * ((rand.nextDouble() % 100000) / 100000) - 1;
		double r = u * u + v * v;
		
		//reject
		if (r == 0 || r > 1) return nextInNormal(mean, sd);
		
		//accept
		double c = Math.sqrt(-2 * Math.log(r)/r);
		return mean + (u * c) * sd;
	}
}

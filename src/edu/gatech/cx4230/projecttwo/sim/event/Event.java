package edu.gatech.cx4230.projecttwo.sim.event;

import edu.gatech.cx4230.projecttwo.sim.objects.Flight;

public abstract class Event {

	private Flight flight;
	private int creationTime;
	private int processTime;
	
	public abstract void process();
}

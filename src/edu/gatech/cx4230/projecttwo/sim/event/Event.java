package edu.gatech.cx4230.projecttwo.sim.event;

import edu.gatech.cx4230.projecttwo.sim.objects.Flight;

public abstract class Event {

	protected Flight flight;
	protected int creationTime;
	protected int processTime;
	
	public abstract void process();
}

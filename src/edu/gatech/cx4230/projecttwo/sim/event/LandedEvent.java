package edu.gatech.cx4230.projecttwo.sim.event;

import edu.gatech.cx4230.projecttwo.sim.objects.Flight;

public class LandedEvent extends Event {

	public LandedEvent(Flight flight, int creationTime, int processTime) {
		this.flight = flight;
		this.creationTime = creationTime;
		this.processTime = processTime;
	}
	
	@Override
	public void process() {
		// TODO Auto-generated method stub
		
	}

}

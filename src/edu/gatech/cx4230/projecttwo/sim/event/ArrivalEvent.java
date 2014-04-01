package edu.gatech.cx4230.projecttwo.sim.event;

import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.Flight;
import edu.gatech.cx4230.projecttwo.sim.objects.Runway;

public class ArrivalEvent extends Event {

	public ArrivalEvent(Flight flight, int creationTime, int processTime) {
		this.flight = flight;
		this.creationTime = creationTime;
		this.processTime = processTime;
	}
	
	@Override
	public void process() {
		// TODO Auto-generated method stub
		Airport destination = flight.getDestination();
		int inAir = destination.getInTheAir();
		destination.setInTheAir(++inAir);
		
		Runway runway = destination.getFreeRunway();
		if(runway != null) {
			runway.setRunwayFree(false);
			runway.setAircraft(flight.getAircraft());
			
			int currSimTime = 0; // TODO get current sim time
			int processTime = currSimTime + 1; // TODO when should the landing event be processed?
			LandedEvent landEvent = new LandedEvent(flight, currSimTime, processTime);
			destination.addPendingEvent(landEvent);
		}
	}

}

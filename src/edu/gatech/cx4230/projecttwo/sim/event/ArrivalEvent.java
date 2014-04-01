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
		Airport destination = flight.getDestination();
		
		// increment inTheAir counter for destination airport
		int inAir = destination.getInTheAir();
		destination.setInTheAir(++inAir);
		
		// TODO options: 
		// option 1: add this flight to an "intTheAir" queue
		// option 2: go ahead and schedule a LandedEvent with this flight
		// and let the airport handle when to create/process the LandedEvent 
		
		/* check if destination airport has a free runway
		Runway runway = destination.getFreeRunway();
		if(runway != null) {
			runway.setRunwayFree(false);
			runway.setAircraft(flight.getAircraft());
		*/
		
		// calculate simulation times
		int currSimTime = 0; // TODO get current sim time
		int landTime = currSimTime; // TODO when should the landing event be processed?
		
		// schedule LandedEvent for destination airport
		LandedEvent landEvent = new LandedEvent(flight, currSimTime, landTime);
		destination.addPendingEvent(landEvent);
	}

}

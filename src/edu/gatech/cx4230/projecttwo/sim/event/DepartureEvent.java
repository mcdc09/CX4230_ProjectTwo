package edu.gatech.cx4230.projecttwo.sim.event;

import edu.gatech.cx4230.projecttwo.sim.objects.Aircraft;
import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.Flight;
import edu.gatech.cx4230.projecttwo.sim.objects.Runway;

public class DepartureEvent extends Event {

	int currSimTime;
	
	public DepartureEvent(Flight flight) {
		this.flight = flight;
	
		currSimTime = 0; //TODO get current simulation time
		
		this.creationTime = currSimTime;
		this.processTime = flight.getTimeOfDeparture();
	}
	
	@Override
	public void process() {
		Airport origin = flight.getOrigin();
		Airport destination = flight.getDestination();
		Aircraft aircraft = flight.getAircraft();
		
		// remove aircraft from onTheGround at origin airport
		origin.removeAircraftOnTheGround(aircraft);
		
		// TODO update runway's "runwayFree" and "aircraft"?
		
		// schedule ArrivalEvent for destination airport
		ArrivalEvent arriveEvent = new ArrivalEvent(flight);
		destination.addPendingEvent(arriveEvent);
	}

}

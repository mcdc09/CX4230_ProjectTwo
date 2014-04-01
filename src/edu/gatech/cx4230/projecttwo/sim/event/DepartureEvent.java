package edu.gatech.cx4230.projecttwo.sim.event;

import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.Flight;
import edu.gatech.cx4230.projecttwo.sim.objects.Runway;

public class DepartureEvent extends Event {

	public DepartureEvent(Flight flight, int creationTime, int processTime) {
		this.flight = flight;
		this.creationTime = creationTime;
		this.processTime = processTime;
	}
	
	@Override
	public void process() {
		Airport origin = flight.getOrigin();
		Airport destination = flight.getDestination();
		
		// decrement onTheGround counter for origin airport
		int onGround = origin.getOnTheGround();
		origin.setInTheAir(--onGround);
		
		// TODO update runway's "runwayFree" and "aircraft"?
		
		// calculate simulation times
		int currSimTime = 0; // TODO get current sim time
		double flightDuration = flight.getDistance() / flight.getAircraft().getSpeed();
		int arriveTime = currSimTime + (int)flightDuration;
		
		// update flight - is this info already specified when the flight is generated?
		// flight.setTimeOfDeparture(currSimTime);
		// flight.setEstimatedTimeArrival(arriveTime);
		
		// schedule ArrivalEvent for destination airport
		ArrivalEvent arriveEvent = new ArrivalEvent(flight, currSimTime, arriveTime);
		destination.addPendingEvent(arriveEvent);
	}

}

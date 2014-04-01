package edu.gatech.cx4230.projecttwo.sim.event;

import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.Flight;

public class LandedEvent extends Event {

	public LandedEvent(Flight flight, int creationTime, int processTime) {
		this.flight = flight;
		this.creationTime = creationTime;
		this.processTime = processTime;
	}
	
	@Override
	public void process() {
		Airport destination = flight.getDestination();
		
		// decrement inTheAir counter for destination airport
		int inAir = destination.getInTheAir();
		destination.setInTheAir(--inAir);
				
		// increment onTheGround counter for destination airport
		int onGround = destination.getOnTheGround();
		destination.setInTheAir(++onGround);
		
		// TODO update runway's "runwayFree" and "aircraft"?
				
		// calculate simulation times
		int currSimTime = 0; // TODO get current sim time
		
		// update flight
		flight.setActualTimeArrival(currSimTime);
		
		// TODO schedule DepartureEvent
		// we need a new flight with the same aircraft - is this done with the FlightGenerator?
		
		// TODO options: 
		// option 1: if flight is added to inTheAir queue during ArrivalEvent
		// if(inTheAir > 0) { schedule LandedEvent for next aircraft in inTheAir queue } 
		// else { update runway's "runwayFree" and "aircraft" }
		
		// option 2: if LandedEvent is scheduled during ArrivalEvent
		// update runway's "runwayFree" and "aircraft"
		// let airport decide when to process next landed event that has already been scheduled
	}

}

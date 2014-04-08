package edu.gatech.cx4230.projecttwo.sim.event;

import edu.gatech.cx4230.projecttwo.sim.objects.Aircraft;
import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.Flight;

public class LandedEvent extends Event {

	int currSimTime;

	public LandedEvent(Flight flight) {
		this.flight = flight;
		
		currSimTime = 0; //TODO get current simulation time

		this.creationTime = currSimTime;
		this.processTime = flight.getEstimatedTimeArrival();
	}
	
	@Override
	public void process() {
		Airport destination = flight.getDestination();
		Aircraft aircraft = flight.getAircraft();
		
		// decrement inTheAir counter for destination airport
		int inAir = destination.getInTheAir();
		destination.setInTheAir(--inAir);
				
		// add aircraft to onTheGround at destination airport
		destination.addAircraftOnTheGround(aircraft);
				
		// update flight
		flight.setActualTimeArrival(currSimTime);
				
		// calculate simulation times
		int runwayTime = flight.getAircraft().getRunwayTime();
		int onGroundTime = flight.getAircraft().getGroundTime();
		
		// TODO update runway's "runwayFree" and "aircraft"?

		// TODO schedule DepartureEvent with destination airport as new origin airport
		// we need a new flight with the same aircraft - FlightGenerator?
		
	}

}

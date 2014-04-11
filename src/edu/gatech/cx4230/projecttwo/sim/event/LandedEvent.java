package edu.gatech.cx4230.projecttwo.sim.event;

import edu.gatech.cx4230.projecttwo.sim.objects.Aircraft;
import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.Flight;

public class LandedEvent extends FlightEvent {

	public LandedEvent(Flight flight) {
		this.flight = flight;
		
		int currSimTime = 0; //TODO get current simulation time

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
		int currSimTime = 0; //TODO get current simulation time
		flight.setActualTimeArrival(currSimTime);
		
		// TODO schedule DepartureEvent with destination airport as new origin airport
		// we need a new flight with the same aircraft - FlightGenerator?
		
	}

}

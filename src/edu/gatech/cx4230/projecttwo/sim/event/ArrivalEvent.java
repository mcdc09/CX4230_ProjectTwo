package edu.gatech.cx4230.projecttwo.sim.event;

import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.Flight;
import edu.gatech.cx4230.projecttwo.sim.objects.Runway;

public class ArrivalEvent extends Event {

	int currSimTime;

	public ArrivalEvent(Flight flight) {
		this.flight = flight;
		
		currSimTime = 0; //TODO get current simulation time

		this.creationTime = currSimTime;
		this.processTime = flight.getEstimatedTimeArrival();
	}
	
	@Override
	public void process() {
		Airport destination = flight.getDestination();
		
		// increment inTheAir counter for destination airport
		int inAir = destination.getInTheAir();
		destination.setInTheAir(++inAir);
		
		// go ahead and schedule a LandedEvent with this flight for destination airport
		// and let the airport handle when to create/process the LandedEvent 
		LandedEvent landEvent = new LandedEvent(flight);
		destination.addPendingEvent(landEvent);
	}

}

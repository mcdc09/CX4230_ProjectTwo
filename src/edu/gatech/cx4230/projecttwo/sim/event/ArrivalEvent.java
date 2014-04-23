package edu.gatech.cx4230.projecttwo.sim.event;

import edu.gatech.cx4230.projecttwo.sim.main.SimulationThread;
import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.Flight;

public class ArrivalEvent extends FlightEvent {

	public ArrivalEvent(Flight flight) {
		this.flight = flight;
		
		int currSimTime = SimulationThread.getCurrTimeStep();

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
		// and let the airport handle when to process the LandedEvent 
		LandedEvent landEvent = new LandedEvent(flight);
		destination.addPendingEvent(landEvent);
	}
	
	public String toString() {
		return "Arrival " + flight.getFlightNumber() +" " + flight.getOrigin().getIcaoCode() + " to " + flight.getDestination().getIcaoCode() + " at " + processTime;
	}

}

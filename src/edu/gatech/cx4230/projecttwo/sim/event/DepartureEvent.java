package edu.gatech.cx4230.projecttwo.sim.event;

import edu.gatech.cx4230.projecttwo.sim.main.AirportSimulation;
import edu.gatech.cx4230.projecttwo.sim.main.SimulationThread;
import edu.gatech.cx4230.projecttwo.sim.objects.Aircraft;
import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.Flight;

public class DepartureEvent extends FlightEvent {
	
	public DepartureEvent(Flight flight) {
		this.flight = flight;
	
		int currSimTime = SimulationThread.getCurrTimeStep();
		
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
				
		// schedule ArrivalEvent for destination airport
		ArrivalEvent arriveEvent = new ArrivalEvent(flight);
		destination.addPendingEvent(arriveEvent);
		
		// add flight to list of active flights
		AirportSimulation.addActiveFlight(flight);
	}
	
	public String toString() {
		return "Departed " + flight.getOrigin().getIcaoCode() + " to " + flight.getDestination().getIcaoCode() + " at " + processTime;
	}

}

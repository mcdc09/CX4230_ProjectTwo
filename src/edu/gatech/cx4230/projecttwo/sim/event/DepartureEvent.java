package edu.gatech.cx4230.projecttwo.sim.event;

import edu.gatech.cx4230.projecttwo.sim.main.AirportSimulation;
import edu.gatech.cx4230.projecttwo.sim.main.SimulationThread;
import edu.gatech.cx4230.projecttwo.sim.objects.Aircraft;
import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.Flight;
import edu.gatech.cx4230.projecttwo.sim.objects.Runway;

public class DepartureEvent extends FlightEvent {
	
	public DepartureEvent(Flight flight) {
		this.flight = flight;
	
		int currSimTime = SimulationThread.getCurrTimeStep();
		
		this.creationTime = currSimTime;
		this.processTime = flight.getEstTimeOfDeparture();
	}
	
	@Override
	public void process(int currSimTime) {
		Airport origin = flight.getOrigin();
		Airport destination = flight.getDestination();
		Aircraft aircraft = flight.getAircraft();
		
		// Update new arrival time
		double t = (flight.getDistance() / aircraft.getSpeed()) * 3600.0;
		flight.setActualTimeArrival(currSimTime + (int) (t / SimulationThread.TIME_PER_STEP));
		flight.setActualTimeOfDeparture(currSimTime);
		
		// remove aircraft from onTheGround at origin airport
		origin.removeAircraftOnTheGround(aircraft);
		
		Runway r = origin.getFreeRunway(aircraft.getMinRunwayLength());
		r.setAircraft(aircraft);
		r.setTimeNextAvailable(currSimTime + aircraft.getRunwayTime());
				
		// schedule ArrivalEvent for destination airport
		ArrivalEvent arriveEvent = new ArrivalEvent(flight);
		destination.addPendingEvent(arriveEvent);
		
		// add flight to list of active flights
		AirportSimulation.addActiveFlight(flight);
	}
	
	public String toString() {
		return "Departure " + flight.getFlightNumber() +" " + flight.getOrigin().getIcaoCode() + " to " + flight.getDestination().getIcaoCode() + " at " + processTime;
	}

}

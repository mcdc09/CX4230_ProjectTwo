package edu.gatech.cx4230.projecttwo.sim.event;

import edu.gatech.cx4230.projecttwo.sim.main.AirportSimulation;
import edu.gatech.cx4230.projecttwo.sim.main.SimulationThread;
import edu.gatech.cx4230.projecttwo.sim.objects.Aircraft;
import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.Flight;
import edu.gatech.cx4230.projecttwo.sim.objects.Runway;
import edu.gatech.cx4230.projecttwo.sim.objects.World;

public class LandedEvent extends FlightEvent {

	public LandedEvent(Flight flight) {
		this.flight = flight;

		int currSimTime = SimulationThread.getCurrTimeStep();

		this.creationTime = currSimTime;
		this.processTime = flight.getEstimatedTimeArrival();
	}

	@Override
	public void process(int currTime) {
		Airport destination = flight.getDestination();
		Aircraft aircraft = flight.getAircraft();

		// decrement inTheAir counter for destination airport
		int inAir = destination.getInTheAir();
		destination.setInTheAir(--inAir);
		
		Runway r = destination.getFreeRunway(aircraft.getMinRunwayLength());
		r.setAircraft(aircraft);
		r.setTimeNextAvailable(currTime + aircraft.getRunwayTime());

		// add aircraft to onTheGround at destination airport
		destination.addAircraftOnTheGround(aircraft);

		// update flight
		int currSimTime = SimulationThread.getCurrTimeStep();
		flight.setActualTimeArrival(currSimTime);

		// consult timetable to get new flight for this aircraft
		Flight newFlight = World.getTimetable().scheduleFlight(destination, aircraft);
		// schedule DepartureEvent with destination airport as new origin airport
		if(newFlight != null) {
			DepartureEvent departEvent = new DepartureEvent(newFlight);
			destination.addPendingEvent(departEvent);
		}

		// remove flight from list of active flights
		AirportSimulation.removeActiveFlight(flight);
	}

	public String toString() {
		return "Landing " + flight.getFlightNumber() + " at " + flight.getDestination().getIcaoCode() + " at " + processTime;
	}

}

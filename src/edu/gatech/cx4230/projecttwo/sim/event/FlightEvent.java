package edu.gatech.cx4230.projecttwo.sim.event;

import edu.gatech.cx4230.projecttwo.sim.objects.Flight;

/**
 * Abstract class for an event related to a flight (aircraft)
 * @author tbowling3
 *
 */
public abstract class FlightEvent extends Event {
	protected Flight flight;
	
	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

}

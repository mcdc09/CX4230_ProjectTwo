package edu.gatech.cx4230.projecttwo.sim.event;

import edu.gatech.cx4230.projecttwo.sim.objects.Airport;

public abstract class AirportEvent extends Event{
	protected Airport airport;

	/**
	 * @return the airport
	 */
	public Airport getAirport() {
		return airport;
	}

	/**
	 * @param airport the airport to set
	 */
	public void setAirport(Airport airport) {
		this.airport = airport;
	}

}

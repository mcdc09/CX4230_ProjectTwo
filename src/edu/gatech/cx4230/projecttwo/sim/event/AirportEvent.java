package edu.gatech.cx4230.projecttwo.sim.event;


public abstract class AirportEvent extends Event{
	protected String airportICAO;

	/**
	 * @return the airportICAO
	 */
	public String getAirportICAO() {
		return airportICAO;
	}

	/**
	 * @param airportICAO the airportICAO to set
	 */
	public void setAirportICAO(String airportICAO) {
		this.airportICAO = airportICAO;
	}

}

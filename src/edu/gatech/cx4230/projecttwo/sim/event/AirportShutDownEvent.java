package edu.gatech.cx4230.projecttwo.sim.event;

import edu.gatech.cx4230.projecttwo.sim.objects.Airport;

public class AirportShutDownEvent extends AirportEvent {
	
	private int shutDownTimeDuration;
	
	public AirportShutDownEvent(Airport a, int shutDownTime) {
		this.airport = a;
		this.shutDownTimeDuration = shutDownTime;
		
		this.creationTime = 0; // TODO
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return the shutDownTimeDuration
	 */
	public int getShutDownTimeDuration() {
		return shutDownTimeDuration;
	}

	/**
	 * @param shutDownTimeDuration the shutDownTimeDuration to set
	 */
	public void setShutDownTimeDuration(int shutDownTimeDuration) {
		this.shutDownTimeDuration = shutDownTimeDuration;
	}

}
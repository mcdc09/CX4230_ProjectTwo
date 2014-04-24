package edu.gatech.cx4230.projecttwo.sim.event;

import edu.gatech.cx4230.projecttwo.sim.main.SimulationThread;
import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.Runway;

public class AirportShutDownEvent extends AirportEvent {
	
	private int shutDownTimeDuration;
	
	public AirportShutDownEvent(Airport a, int shutDownTime, int beginTime) {
		this.airport = a;
		this.shutDownTimeDuration = shutDownTime;
		
		this.processTime = beginTime; // TODO
		this.creationTime = SimulationThread.getCurrTimeStep();
	}

	@Override
	public void process(int currTime) {
		// TODO Auto-generated method stub
		airport.setGroundStop(true);
		
		for(Runway r : airport.getRunways()) {
			r.setTimeNextAvailable(processTime + shutDownTimeDuration);
		}
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

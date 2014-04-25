package edu.gatech.cx4230.projecttwo.sim.event;

import edu.gatech.cx4230.projecttwo.sim.main.SimulationThread;
import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.Runway;
import edu.gatech.cx4230.projecttwo.sim.objects.World;
import edu.gatech.cx4230.projecttwo.sim.testing.AirportSimulationLoggerMaster;

public class AirportShutDownEvent extends AirportEvent {
	
	private int shutDownTimeDuration;
	
	public AirportShutDownEvent(String aICAO, int shutDownTime, int beginTime) {
		this.airportICAO = aICAO;
		this.shutDownTimeDuration = shutDownTime;
		
		this.processTime = beginTime; // TODO
		this.creationTime = SimulationThread.getCurrTimeStep();
	}

	@Override
	public void process(int currTime) {
		AirportSimulationLoggerMaster.logLineEvent("AShutDownEvent<process> at " + currTime);
		Airport airport = World.getAirport(airportICAO);
		airport.setGroundStop(true);
		
		for(Runway r : airport.getRunways()) {
			r.setTimeNextAvailable(processTime + shutDownTimeDuration);
		}
		
		AirportOpenEvent openEvent = new AirportOpenEvent(airportICAO, processTime + shutDownTimeDuration, processTime);
		airport.addPendingEvent(openEvent);
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

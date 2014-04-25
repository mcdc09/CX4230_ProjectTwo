package edu.gatech.cx4230.projecttwo.sim.event;

import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.Runway;
import edu.gatech.cx4230.projecttwo.sim.objects.World;
import edu.gatech.cx4230.projecttwo.sim.testing.AirportSimulationLoggerMaster;


/**
 * Object paired with an AirportShutDownEvent to simulate a ground stop at a particular
 * airport.  This will open the runways and all the airport to operate.
 * @author tbowling3
 *
 */
public class AirportOpenEvent extends AirportEvent {
	
	/**
	 * Creates object
	 * @param a Airport for the event
	 * @param timeProcess Time step at which the ground stop is lifted
	 * @param timeNow The creation time step
	 */
	public AirportOpenEvent(String aICAO, int timeProcess, int timeNow) {
		this.airportICAO = aICAO;
		this.processTime = timeProcess;
		this.creationTime = timeNow;
	}

	@Override
	public void process(int currTime) {
		AirportSimulationLoggerMaster.logLineEvent("AirportOpenEvent<process> at " + currTime);
		Airport airport = World.getAirport(airportICAO);
		airport.setGroundStop(false);
		for(Runway r: airport.getRunways()) {
			r.setTimeNextAvailable(processTime);
		}
		
	}

}

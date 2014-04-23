package edu.gatech.cx4230.projecttwo.sim.objects;

import edu.gatech.cx4230.projecttwo.sim.main.SimulationThread;

public class RegionalAircraft extends Aircraft {
	private static final String TYPE = Aircraft.TYPE_REGIONAL;
	private static final int PASS = 100;
	private static final int SPEED = 830;
	private static final int RUNWAY_L = 0; // TODO
	private static final int RUNWAY_T = 60 / SimulationThread.TIME_PER_STEP; // TODO
	
	public RegionalAircraft() {
		super(TYPE, PASS, SPEED, RUNWAY_L, RUNWAY_T);
	}

}

package edu.gatech.cx4230.projecttwo.sim.objects;

import edu.gatech.cx4230.projecttwo.sim.main.SimulationThread;

public class LargeAircraft extends Aircraft{
	private static final String TYPE = Aircraft.TYPE_LARGE;
	private static final int PASS = 300;
	private static final int SPEED = 850;
	private static final int RUNWAY_L = 0; // TODO
	private static final int RUNWAY_T = 46 / SimulationThread.TIME_PER_STEP; // TODO
	private static final int GROUND_T = 0; // TODO
	
	public LargeAircraft() {
		super(TYPE, PASS, SPEED, RUNWAY_L, RUNWAY_T, GROUND_T);
	}

}

package edu.gatech.cx4230.projecttwo.sim.objects;

import edu.gatech.cx4230.projecttwo.sim.main.SimulationThread;

public class SmallAircraft extends Aircraft {
	private static final String TYPE = Aircraft.TYPE_SMALL;
	private static final int PASS = 150;
	private static final int SPEED = 780;
	private static final int RUNWAY_L = 0; // TODO
	private static final int RUNWAY_T = 60 / SimulationThread.TIME_PER_STEP; // TODO
	
	public SmallAircraft() {
		super(TYPE, PASS, SPEED, RUNWAY_L, RUNWAY_T);
	}
}

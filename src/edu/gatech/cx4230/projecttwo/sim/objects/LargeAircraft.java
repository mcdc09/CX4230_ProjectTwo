package edu.gatech.cx4230.projecttwo.sim.objects;

public class LargeAircraft extends Aircraft{
	private static final String TYPE = Aircraft.TYPE_REGIONAL;
	private static final int PASS = 300;
	private static final int SPEED = 850;
	private static final int RUNWAY_L = 0; // TODO
	private static final int RUNWAY_T = 0; // TODO
	private static final int GROUND_T = 0; // TODO
	
	public LargeAircraft() {
		super(TYPE, PASS, SPEED, RUNWAY_L, RUNWAY_T, GROUND_T);
	}

}

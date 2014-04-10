package edu.gatech.cx4230.projecttwo.sim.main;


/**
 * Execution thread for the simulation.  This communicates with the animation thread as needed.
 * @author tbowling3
 *
 */
public class SimulationThread extends Thread {
	private AirportSimulation as;
	private boolean running;
	private int timeStep;
	private boolean timeChanged;
	private static boolean DEBUG = false;
	private int wait;
	private String id;
	
	public SimulationThread(AirportSimulation as, int w, String s) {
		this.as = as;
		this.wait = w;
		this.id = s;
		timeStep = 0;
	}
	
	@Override
	public synchronized void start() {
		timeStep = 0;

		running = true;
		super.start();
	}
	
	/**
	 * handles tasks that need to be done at each time step
	 * maintains crosswalk timing, spawns new people into simulation, moves active people in simulation
	 */
	public void run() {
		while(as.continueSimulation()) {
			if(running) {
				timeStep++;
				as.setTimeChanged(true);

				

				try {
					sleep((long) wait);
				} catch(Exception e) {
					e.printStackTrace();
				}
				if(!as.continueSimulation()) {
					quit();
				}
			}
		}
	}


	public void quit() {
		if(DEBUG) System.out.println("Quitting Thread...");
		
		// TODO Elaborate on Terminating condition
		if(DEBUG) System.out.println("Time steps: " + timeStep);
		
		running = false;
		interrupt();
	}

	public String toString() {
		return "(" + id + "\tRunning: " + running + ")";
	}

	/**
	 * @return the timeStep
	 */
	public int getTimeStep() {
		return timeStep;
	}

	/**
	 * @param timeStep the timeStep to set
	 */
	public void setTimeStep(int timeStep) {
		this.timeStep = timeStep;
	}

	/**
	 * @return the timeChanged
	 */
	public boolean isTimeChanged() {
		return timeChanged;
	}

	/**
	 * @param timeChanged the timeChanged to set
	 */
	public void setTimeChanged(boolean timeChanged) {
		this.timeChanged = timeChanged;
	}

}

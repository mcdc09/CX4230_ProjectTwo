package edu.gatech.cx4230.projecttwo.sim.main;


/**
 * Execution thread for the simulation.  This communicates with the animation thread as needed.
 * @author tbowling3
 *
 */
public class SimulationThread extends Thread {
	private AirportSimulation as;
	private boolean running;
	private static int currTimeStep;
	public static final int timeStep = 10; // seconds per time step
	private static boolean DEBUG = false;
	private int wait;
	private String id;

	public SimulationThread(AirportSimulation as, int w, String s) {
		this.as = as;
		this.wait = w;
		this.id = s;
		currTimeStep = 0;
	}

	@Override
	public synchronized void start() {
		currTimeStep = 0;

		running = true;
		super.start();
	}


	public void run() {
		while(as.continueSimulation()) {
			if(running) {
				currTimeStep++;
				as.setTimeChanged(true);

				as.processEventsForTimeStep(timeStep);

				if(wait > 0) {
					try {
						sleep((long) wait);
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				if(!as.continueSimulation()) {
					quit();
				}
			}
		}
	}


	public void quit() {
		if(DEBUG) System.out.println("Quitting Thread...");

		as.quitSimulation();
		if(DEBUG) System.out.println("Time steps: " + currTimeStep);

		running = false;
		interrupt();
	}

	public String toString() {
		return "(" + id + "\tRunning: " + running + ")";
	}

	/**
	 * @return the timeStep
	 */
	public static int getCurrTimeStep() {
		return currTimeStep;
	}

	/**
	 * @param timeStep the timeStep to set
	 */
	public static void setCurrTimeStep(int timeStep) {
		SimulationThread.currTimeStep = timeStep;
	}

	/**
	 * @return the running
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * @param running the running to set
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public void triggerRunning() {
		running = !running;
	}

}

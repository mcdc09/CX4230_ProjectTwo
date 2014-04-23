package edu.gatech.cx4230.projecttwo.sim.main;

import edu.gatech.cx4230.projecttwo.sim.testing.AirportSimulationLoggerMaster;
import edu.gatech.cx4230.projecttwo.sim.testing.SimulationScenario;
import edu.gatech.cx4230.projecttwo.sim.testing.TrialResult;
import edu.gatech.cx4230.projecttwo.sim.testing.TrialResultList;


/**
 * Object to use for the conducting experiments of the simulation
 * @author tbowling3
 *
 */
public class SimNoVisTrial {
	private SimulationScenario scenario;
	
	public SimNoVisTrial(SimulationScenario scen) {
		this.scenario = scen;
	}
	
	/**
	 * Runs the simulation a given number of times
	 * @param count
	 * @return
	 */
	public TrialResultList runTrials(int count) {
		TrialResultList results = new TrialResultList();
		
		long start = System.currentTimeMillis();
		for(int i = 0; i < count; i++) {
			TrialResult trial = runTrial();
			results.addTrialResult(trial);
			
			// Save Logs
			AirportSimulationLoggerMaster.save(scenario.getName() + "_" + i, true);
			
		}
		long stop = System.currentTimeMillis();
		long dt = stop - start;
		results.setTrials(count);
		results.setTotalTime(dt);
		
		return results;
	}
	
	/**
	 * Runs a trial of the simulation
	 * @return
	 */
	public TrialResult runTrial() {
		AirportSimulation as = new AirportSimulation(scenario);
		
		long start = System.currentTimeMillis();
		while(scenario.continueSimulation(as)) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long stop = System.currentTimeMillis();
		long dt = stop - start;
		
		TrialResult out = as.getSimulationResults();
		out.setSimulationWallClockDuration(dt);
		
		return out;
	}
	
	/**
	 * Runs the actual experiment
	 * @param args
	 */
	public static void main(String[] args) {
		SimulationScenario scenario = null; // TODO
		int trials = 10;
		
		
		SimNoVisTrial snvt = new SimNoVisTrial(scenario);
		TrialResultList trialRL = snvt.runTrials(trials);
		// TODO
		
	}

}
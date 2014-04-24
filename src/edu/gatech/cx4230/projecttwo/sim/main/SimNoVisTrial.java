package edu.gatech.cx4230.projecttwo.sim.main;

import edu.gatech.cx4230.projecttwo.sim.objects.World;
import edu.gatech.cx4230.projecttwo.sim.testing.AirportSimulationLoggerMaster;
import edu.gatech.cx4230.projecttwo.sim.testing.DefaultScenario;
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
		System.out.println("SimNoVisTrial<runTrials> Running " + count + " trials...");
		TrialResultList results = new TrialResultList();
		
		long start = System.currentTimeMillis();
		for(int i = 0; i < count; i++) {
			System.out.print("Trial " + i + " running...");
			TrialResult trial = runTrial();
			results.addTrialResult(trial);
			
			// Save Logs
			AirportSimulationLoggerMaster.save(scenario.getName() + "_" + i, true);
			
		}
		System.out.println("SimNoVisTrial<runTrials> DONE!");
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
		long dt = System.currentTimeMillis() - start;
		System.out.print("done in " + dt + " ms");
		
		TrialResult out = as.getSimulationResults();
		out.setSimulationWallClockDuration(dt);
		
		World.getTimetable().timetableStatus();
		
		return out;
	}
	
	/**
	 * Runs the actual experiment
	 * @param args
	 */
	public static void main(String[] args) {
		AirportSimulationLoggerMaster.setPrint(false);
		SimulationScenario scenario = new DefaultScenario(false, null, 1); // TODO
		int trials = 1;
		
		
		SimNoVisTrial snvt = new SimNoVisTrial(scenario);
		TrialResultList trialRL = snvt.runTrials(trials);
		System.out.println("Trial run in: " + trialRL.getTotalTime());
		// TODO
		
	}

}

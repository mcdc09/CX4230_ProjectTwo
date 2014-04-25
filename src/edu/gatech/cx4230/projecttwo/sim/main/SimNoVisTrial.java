package edu.gatech.cx4230.projecttwo.sim.main;

import edu.gatech.cx4230.projecttwo.sim.objects.World;
import edu.gatech.cx4230.projecttwo.sim.testing.AirportSimulationLoggerMaster;
import edu.gatech.cx4230.projecttwo.sim.testing.KATLShutdownScenario;
import edu.gatech.cx4230.projecttwo.sim.testing.KSTLShutdownScenario;
import edu.gatech.cx4230.projecttwo.sim.testing.SimulationScenario;
import edu.gatech.cx4230.projecttwo.sim.testing.TimedScenario;
import edu.gatech.cx4230.projecttwo.sim.testing.TrialResult;
import edu.gatech.cx4230.projecttwo.sim.testing.TrialResultList;
import edu.gatech.cx4230.projecttwo.utilities.FileHelper;


/**
 * Object to use for the conducting experiments of the simulation
 * @author tbowling3
 *
 */
public class SimNoVisTrial {
	private SimulationScenario scenario;
	private boolean saveLogFiles;

	public SimNoVisTrial(SimulationScenario scen, String testName, boolean saveLogFiles) {
		this.scenario = scen;
		this.saveLogFiles = saveLogFiles;
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
			TrialResult trial = runTrial(i);
			results.addTrialResult(trial);
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
	 * @param i The number of the current trial
	 * @return
	 */
	public TrialResult runTrial(int i) {
		// Run and time Trial
		long start = System.currentTimeMillis();
		AirportSimulation as = new AirportSimulation(scenario);
		while(scenario.continueSimulation(as)) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long dt = System.currentTimeMillis() - start;
		System.out.println("done in " + dt + " ms");

		// Process results from this trial run
		TrialResult out = as.getSimulationResults();
		out.setSimulationWallClockDuration(dt);

		World.getTimetable().timetableStatus();

		// Save Logs
		if(saveLogFiles) {
			AirportSimulationLoggerMaster.save(scenario.getName() + "_" + i, true);
		} else {
			AirportSimulationLoggerMaster.clear();
		}
		return out;
	}

	/**
	 * Runs the actual experiment
	 * @param args
	 */
	public static void main(String[] args) {
		AirportSimulationLoggerMaster.setPrint(false);
		String testName;
		SimulationScenario scenario;
		
		// Timed Scenario
//		testName = "Timed_Scenario";
//		int hours = 24;
//		scenario = new TimedScenario(false, null, 4000, testName, hours * 3600);
		
		
		// KATL Shutdown Scenario
//		testName = "KATL_Scenario";
//		scenario = new KATLShutdownScenario(false, null, 4000);
		
		
		// KSTL Shutdown Scenario
		testName = "KSTL_Scenario";
		scenario = new KSTLShutdownScenario(false, null, 4000);

		
		
		
		int trials = 30;
		SimNoVisTrial snvt = new SimNoVisTrial(scenario, testName, false);
		TrialResultList trialRL = snvt.runTrials(trials);
		System.out.println("Trials run in: " + trialRL.getTotalTime() + " ms");
		System.out.print("Saving data...");
		trialRL.calcAllAcrossTrials();
		String filename = FileHelper.getPathToResource("Output/" + testName);
		trialRL.saveDataToCSV(filename);
		System.out.println("done!");

	}

}

package edu.gatech.cx4230.projecttwo.sim.testing;

/**
 * Keeps track of the different logger outputs from the simulation
 * @author tbowling3
 *
 */
public class AirportSimulationLoggerMaster {
	private static AirportSimulationLogger flightEventLogger = new AirportSimulationLogger(true, "Flight_Events");
	private static AirportSimulationLogger simulationLogger = new AirportSimulationLogger(true, "Simulation_Logger");
	
	// Event Logger
	public static void logLineEvent(String in) {
		flightEventLogger.logLine(in);
	}
	
	public static void logEvent(String in) {
		flightEventLogger.log(in);
	}
	
	public static void logLineEvent(Object in) {
		flightEventLogger.logLine(in);
	}
	
	public static void logEvent(Object in) {
		flightEventLogger.log(in);
	}
	
	// Simulation Logger
	public static void logLineSim(String in) {
		simulationLogger.logLine(in);
	}
	
	public static void logSim(String in) {
		simulationLogger.log(in);
	}
	
	public static void logLineSim(Object in) {
		simulationLogger.logLine(in);
	}
	
	public static void logSim(Object in) {
		simulationLogger.log(in);
	}
	
	// Overall Methods
	public static void clear() {
		simulationLogger.clear();
		flightEventLogger.clear();
	}
	
	public static void save(String filename, boolean clear) {
		simulationLogger.save("Sim_" + filename, clear);
		flightEventLogger.save("Events_" + filename, clear);
	}
	
	public static void save(boolean clear) {
		simulationLogger.save(clear);
		flightEventLogger.save(clear);
	}
	
	public static void save() {
		save(true);
	}

}

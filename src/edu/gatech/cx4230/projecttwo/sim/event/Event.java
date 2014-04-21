package edu.gatech.cx4230.projecttwo.sim.event;


public abstract class Event {

	protected int creationTime;
	protected int processTime;
	
	public abstract void process();


	public int getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(int creationTime) {
		this.creationTime = creationTime;
	}

	public int getProcessTime() {
		return processTime;
	}

	public void setProcessTime(int processTime) {
		this.processTime = processTime;
	}
	
	public String toString() {
		return "Event to process at: " + processTime;
	}
}

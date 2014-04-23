package edu.gatech.cx4230.projecttwo.sim.testing;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Structure to hold a set of trial results
 * @author tbowling3
 *
 */
public class TrialResultList {
	private List<TrialResult> list;
	private int trials;
	private long totalTime;
	
	public TrialResultList() {
		list = new ArrayList<TrialResult>();
	}
	
	/**
	 * Adds the given TrialResult to the list
	 * @param rt
	 */
	public void addTrialResult(TrialResult rt) {
		if(rt != null) {
			list.add(rt);
		}
	}

	/**
	 * @return the list
	 */
	public List<TrialResult> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<TrialResult> list) {
		this.list = list;
	}

	/**
	 * @return the trials
	 */
	public int getTrials() {
		return trials;
	}

	/**
	 * @param trials the trials to set
	 */
	public void setTrials(int trials) {
		this.trials = trials;
	}

	/**
	 * @return the totalTime
	 */
	public long getTotalTime() {
		return totalTime;
	}

	/**
	 * @param totalTime the totalTime to set
	 */
	public void setTotalTime(long totalTime) {
		this.totalTime = totalTime;
	}

}

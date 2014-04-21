package edu.gatech.cx4230.projecttwo.sim.event;

import java.util.ArrayList;

import edu.gatech.cx4230.projecttwo.utilities.ListHelper;

public class EventPriorityQueue<T> {
	
	ArrayList<Event> events;
	
	public EventPriorityQueue() {
		events = new ArrayList<Event>();
	}
	
	public void addInOrder(Event e) {
		int insertIndex = events.size();
		for(int i = 0; i < events.size(); i++) {
			if(e.getProcessTime() < events.get(i).getProcessTime()) {
				insertIndex = i;
				break;
			}
		}
		events.add(insertIndex, e);
	}
	
	public Event removeMin() {
		Event out = null;
		if(events != null && !events.isEmpty()) {
			out = events.remove(0);
		}
		return out;
	}
	
	public Event getMin() {
		Event out = null;
		if(events != null && !events.isEmpty()) {
			out = events.get(0);
		}
		return out;
	}

	public int getMinValue() {
		Event minEvent = getMin();
		int out = Integer.MAX_VALUE;
		if(minEvent != null) {
			out = minEvent.getProcessTime();
		}
		return out;
	}
	
	public String toString() {
		return ListHelper.listToString(events);
	}
	
	public int size() {
		return events.size();
	}
}

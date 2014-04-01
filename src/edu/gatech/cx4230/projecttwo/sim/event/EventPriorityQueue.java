package edu.gatech.cx4230.projecttwo.sim.event;

import java.util.ArrayList;

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
		return events.remove(0);
	}

	public int getMinValue() {
		return events.get(0).getProcessTime();
	}
}

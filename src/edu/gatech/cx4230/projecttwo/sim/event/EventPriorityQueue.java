package edu.gatech.cx4230.projecttwo.sim.event;

import java.util.ArrayList;
import java.util.PriorityQueue;

import edu.gatech.cx4230.projecttwo.utilities.ArrayManipulation;
import edu.gatech.cx4230.projecttwo.utilities.ListHelper;

public class EventPriorityQueue<T> {
	
	PriorityQueue<Event> events;
	
	public EventPriorityQueue() {
		events = new PriorityQueue<Event>();
	}
	
	public void addInOrder(Event e) {
		events.add(e);
	}
	
	public Event removeMin() {
		Event out = events.poll();
		return out;
	}
	
	public Event getMin() {
		Event out = events.peek();
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
		return ArrayManipulation.ArrayToString(events.toArray());
	}
	
	public int size() {
		return events.size();
	}
}

package edu.gatech.cx4230.projecttwo.vis.map;

import processing.core.PApplet;
import processing.core.PFont;
import edu.gatech.cx4230.projecttwo.sim.objects.Aircraft;
import edu.gatech.cx4230.projecttwo.sim.objects.Airport;
import edu.gatech.cx4230.projecttwo.sim.objects.Flight;
import edu.gatech.cx4230.projecttwo.sim.objects.Runway;

public class ControlsView {
	private int x, y;
	private int width, height;
	private static final int V_AIRPORT = 346553, V_AIRCRAFT = 6576543;
	private int viewInt;
	private Flight flight;
	private Airport airport;
	
	public ControlsView(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void updateForAirport(Airport a) {
		viewInt = V_AIRPORT;
		this.airport = a;
		this.flight = null;
	}
	
	public void updateForFlight(Flight f) {
		viewInt = V_AIRCRAFT;
		this.airport = null;
		this.flight = f;
	}
	
	public void draw(PApplet p) {
		PFont f = p.createFont("Arial", 14, true);
		p.textFont(f);
		p.color(0, 0, 0);
		p.fill(160);
		p.rect(x, y, width, height);
		p.fill(0);
		
		int offset = 10;
		
		// Draw the info for the selected object
		switch(viewInt) {
		case V_AIRPORT:
			if(airport != null) {
				p.text("Airport", x + offset, y + 20);
				p.text("ICAO: " + airport.getIcaoCode(), x + offset, y + 35);
				p.text(airport.getName(), x + offset, y + 50);
				p.text(airport.getCity() + ", " + airport.getState(), x + offset, y + 65);
				p.text("Max capacity: " + airport.getMaxAircraftCapacity(), x + offset, y + 80);
				p.text("In the air: " + airport.getInTheAir(), x + offset, y + 95);
				p.text("On the ground: " + airport.getNumOnTheGround(), x + offset, y + 110);
				p.text("Pending events: " + airport.getPendingEvents().size(), x+ offset, y + 125);
				p.text("Processed events: " + airport.getProcessedEvents().size(), x+ offset, y + 140);
				
				p.text("Runways", x+offset, y + 165);
				int yStart = y + 180;
				for(Runway r: airport.getRunways()) {
					p.text(r.getLength() + " ft  Available at: " + r.getTimeNextAvailable(), x+offset, yStart);
					yStart += 15;
					
				} // close runway for
				
				p.text("Pending Events", x + offset, yStart+=25);
				p.text(airport.getPendingEvents().toString(), x + offset, yStart+=15);
			}
			break;
		case V_AIRCRAFT:
			if(f != null) {
				p.text("Aircraft Flight: " + flight.getFlightNumber(), x + offset, y + 20);
				String originName = flight.getOrigin().getName();
				String destName = flight.getDestination().getName();
				String originDest = originName.substring(0, Math.min(20, originName.length())) + " => " + destName.substring(0, Math.min(20, destName.length()));
				p.text(originDest, x + offset, y + 35);
				p.text("Estimated Time of Departure: " + flight.getEstTimeOfDeparture(), x+offset, y+50);
				p.text("Actual Time of Departure: " + flight.getActualTimeOfDeparture(), x+offset, y+65);
				p.text("ETA: " + flight.getEstimatedTimeArrival(), x+offset, y+80);
				p.text("ATA: " + flight.getActualTimeArrival() , x+offset, y+95);
				
				Aircraft a = flight.getAircraft();
				p.text("Type: " + a.getAircraftType(), x+offset, y+110);
				p.text("Speed: " + a.getSpeed(), x+offset, y+125);
			}
			break;
		}
	}

}

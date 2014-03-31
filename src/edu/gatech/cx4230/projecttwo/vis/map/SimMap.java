package edu.gatech.cx4230.projecttwo.vis.map;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.utils.MapUtils;

public class SimMap {
	private UnfoldingMap map;
	public static final int MAP_X = 25, MAP_Y = 25, MAP_WIDTH = 600, MAP_HEIGHT = 500;
	
	public SimMap(VisPApplet vis) {
		map = new UnfoldingMap(vis, MAP_X, MAP_Y, MAP_WIDTH, MAP_HEIGHT);
		
		MapUtils.createDefaultEventDispatcher(vis, map);
	}
	
	public void draw() {
		map.draw();
	}

}

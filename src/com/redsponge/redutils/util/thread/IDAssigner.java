package com.redsponge.redutils.util.thread;

public class IDAssigner {

	private int baseID;

	public IDAssigner(int baseID) {
		this.baseID = baseID; 
	}
	
	public int next() {
		return baseID++;
	}
	
	public int getCurrentID() {
		return baseID;
	}
	
}

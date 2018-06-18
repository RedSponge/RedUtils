package com.redsponge.redutils.exceptions;

import com.redsponge.redutils.GraphicsApp;

public class NotEnoughThreadsPresentException extends RuntimeException {

	public NotEnoughThreadsPresentException(int numThreads) {
		super("Not enough threads (" + numThreads + ") are present! you should have at least " + GraphicsApp.minThreads);
	}

}

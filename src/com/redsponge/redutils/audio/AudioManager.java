package com.redsponge.redutils.audio;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AudioManager {

	public static class Ogg {		
		
		private static final Map<String, OggClip> oggClips = new HashMap<String, OggClip>();
		
		public static OggClip getClip(String path) {
			if(oggClips.get(path) != null) {
				return oggClips.get(path);
			}
			System.out.println("Loading Ogg File: " + path);
			try {
				OggClip newClip = new OggClip(Ogg.class.getResourceAsStream(path));
				oggClips.put(path, newClip);
				return newClip;
			} catch(IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
	}
	
}

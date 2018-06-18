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

	public static class Wave {
		private static final Map<String, WaveClip> waveClips = new HashMap<String, WaveClip>();

		public static WaveClip getClip(String path) {
			if(waveClips.get(path) != null) {
				return waveClips.get(path);
			}
			System.out.println("Loading Wave File: " + path);
			try {
				WaveClip newClip = new WaveClip(Wave.class.getResourceAsStream(path));
				waveClips.put(path, newClip);
				return newClip;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	
}

package com.redsponge.redutils.audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.FloatControl.Type;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.io.InputStream;

public class WaveClip implements IAudioClip{

  private Clip clip;
  private boolean paused;

  WaveClip(InputStream in) throws IOException {
      try {
          AudioInputStream ais = AudioSystem.getAudioInputStream(in);
          clip = AudioSystem.getClip();
          clip.open(ais);
      } catch (UnsupportedAudioFileException | LineUnavailableException e) {
          e.printStackTrace();
      }
  }

  @Override
  public void play() {
      clip.setFramePosition(0);
      paused = false;
      clip.start();
  }

  @Override
  public void setGain(float gain) {
    FloatControl c = (FloatControl) clip.getControl(Type.VOLUME);
    c.setValue(gain);
  }

  @Override
  public void stop() {
    clip.stop();
  }

  @Override
  public boolean stopped() {
    return !clip.isActive();
  }

  @Override
  public void setBalance(float balance) {
    FloatControl c = (FloatControl) clip.getControl(Type.BALANCE);
    c.setValue(balance);
  }

  @Override
  public void close() {
    clip.close();
  }

  @Override
  public boolean isPaused() {
    return paused;
  }

  @Override
  public void loop() {
    clip.loop(Clip.LOOP_CONTINUOUSLY);
  }

  @Override
  public void pause() {
    clip.stop();
    paused = true;
  }

  @Override
  public void resume() {
    clip.start();
  }

  @Override
  public void setDefaultGain() {
    setGain(-1);
  }
}

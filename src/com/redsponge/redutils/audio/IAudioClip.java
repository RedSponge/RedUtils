package com.redsponge.redutils.audio;

public interface IAudioClip {

    void play();

    void setGain(float gain);

    void stop();

    boolean stopped();

    void setBalance(float balance);

    void close();

    boolean isPaused();

    void loop();

    void pause();

    void resume();

    void setDefaultGain();



}

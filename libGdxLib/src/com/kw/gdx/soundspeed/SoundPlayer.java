package com.kw.gdx.soundspeed;
public interface SoundPlayer {
    void play();

    void loop();

    void stop();

    float getVolume();

    void setVolume(float volume);

    float getPitch();

    void setPitch(float pitch);

    boolean isLooping();
}


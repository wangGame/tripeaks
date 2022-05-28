package com.kw.gdx.screentransition;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Transtition {
    public float getDuration();
    public abstract void render(SpriteBatch batch, Texture currScreen, Texture nextScreen, float alpha);
}

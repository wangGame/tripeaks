package com.kw.gdx.utils;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ImageUtils {
    public static void changeImage(Image image, TextureRegion atlas){
        Drawable drawable = image.getDrawable();
        if (drawable instanceof TextureRegionDrawable) {
            ((TextureRegionDrawable)drawable).setRegion(new TextureRegion(atlas));
        }else if (drawable instanceof SpriteDrawable){
            ((SpriteDrawable)drawable).setSprite(new Sprite(atlas));
        }
    }
}

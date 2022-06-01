package com.kw.gdx.i18;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;

public class I18Resource {
    public void loadResource(String path){
        FileHandle internal = Gdx.files.internal(path);
        I18NBundle bundle = I18NBundle.createBundle(internal, Locale.US);
//        System.out.println(bundle.get("levelCompleted.currentScore"));
        System.out.println(bundle.format("levelCompleted.currentScore", 1));
    }

}

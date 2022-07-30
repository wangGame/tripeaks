package com.kw.gdx.resource.i18;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;

/**
 *
 */
public class I18Resource {
    private I18NBundle bundle;
    public void loadResource(String path){
        FileHandle internal = Gdx.files.internal(path);
        bundle = I18NBundle.createBundle(internal,Locale.CHINA);
    }

    public static void main(String[] args) {
        I18Resource resource = new I18Resource();
        resource.loadResource("i8n/Bundle.properties");
    }

    public String findValue(String key){
        if (bundle == null) {
            throw new RuntimeException();
        }
        return bundle.get(key);
    }

    public String findValue(String key,Object... v){
        if (bundle == null) {
            throw new RuntimeException();
        }
        return bundle.format(key,v);
    }

}

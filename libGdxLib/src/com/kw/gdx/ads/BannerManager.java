package com.kw.gdx.ads;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class BannerManager {
    private static BannerView view;
    public void init(){
        if (view == null) {
            view = new BannerView();
        }
    }

    public static void showBanner(Stage stage){
        if (view == null) {
            view = new BannerView();
            stage.addActor(view);
        }
        view.setVisible(true);
    }

}

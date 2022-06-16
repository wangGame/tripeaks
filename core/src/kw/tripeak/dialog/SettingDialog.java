package kw.tripeak.dialog;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kw.gdx.view.dialog.base.BaseDialog;

import kw.tripeak.pref.TripeakPreferece;

public class SettingDialog extends BaseDialog {
    public SettingDialog() {
        super("cocos/setting.json");
        show();
    }

    @Override
    public void show() {
        super.show();
        setDebug(true);
        Group musicGroup = findActor("music");
        Group musicOffon = musicGroup.findActor("offon");
        musicOffon.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println("--------------");
                boolean music = TripeakPreferece.getInstance().isMusic();
                if (music){
                    musicOffon.findActor("btn_on_15").setVisible(false);
                    musicOffon.findActor("btn_off_8").setVisible(true);
                }else {
                    musicOffon.findActor("btn_on_15").setVisible(true);
                    musicOffon.findActor("btn_off_8").setVisible(false);
                }
                TripeakPreferece.getInstance().updateMusic();
            }
        });
        Group soundGroup = findActor("sound");
        Group soundOffon = soundGroup.findActor("offon");
        soundOffon.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                boolean music = TripeakPreferece.getInstance().isMusic();
                TripeakPreferece.getInstance().updateMusic();
                if (music){
                    soundOffon.findActor("btn_on_9").setVisible(true);
                    soundOffon.findActor("btn_off_19").setVisible(false);
                }else {
                    soundOffon.findActor("btn_on_9").setVisible(false);
                    soundOffon.findActor("btn_off_19").setVisible(true);
                }
            }
        });


        Actor panel_9 = findActor("Panel_9");
        panel_9.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.app.exit();
            }
        });

        Actor panel_8 = findActor("Panel_8");
        panel_8.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println("语言");
            }
        });
    }
}

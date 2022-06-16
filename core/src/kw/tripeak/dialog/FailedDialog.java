package kw.tripeak.dialog;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import com.kw.gdx.listener.ButtonListener;
import com.kw.gdx.view.dialog.base.BaseDialog;

public class FailedDialog extends BaseDialog {
    private Runnable runnable;
    public FailedDialog(Runnable runnable){
        super("cocos/failedSceen.json");
        this.runnable = runnable;
        show();
    }

    @Override
    public void show() {
        super.show();
        Actor btn_bg_6 = findActor("next_1");
        btn_bg_6.setTouchable(Touchable.enabled);
        btn_bg_6.addListener(new ButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                runnable.run();
            }
        });

    }
}

package ogz.tripeaks.dialog;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.dialog.base.BaseDialog;

public class FailedDialog extends BaseDialog {
    public FailedDialog(){
        super(null);
        show();
    }

    @Override
    public void show() {
        super.show();
        Image windowBg = new Image(new NinePatch(new Texture("images/ui/window_dark.png"),5,5,5,5));
        dialogGroup.addActor(windowBg);
        windowBg.setSize(200,700);
        windowBg.setPosition(getWidth()/2,getHeight()/2, Align.center);


    }
}

package ogz.tripeaks.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.view.dialog.base.BaseDialog;
import com.kw.gdx.listener.ButtonListener;

import ogz.tripeaks.btn.MyImageButton;

public class SuccessDialog extends BaseDialog {
    private Runnable runnable;
    public SuccessDialog(Runnable runnable){
        super(null);
        this.runnable = runnable;
        show();
    }

    @Override
    public void show() {
        super.show();
        Image windowBg = new Image(new NinePatch(new Texture("images/ui/window_dark.png"),5,5,5,5));
        dialogGroup.addActor(windowBg);
        windowBg.setSize(1200,700);
        windowBg.setPosition(getWidth()/2,getHeight()/2, Align.center);
        Table table = new Table(){{
            MyImageButton new_game = getBtn("NEW GAME");
            new_game.addListener(new ButtonListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    runnable.run();
                }
            });
            add(new_game);
            row().pad(30);
            MyImageButton exit = getBtn("EXIT");
            add(exit);
            pack();
        }};
        dialogGroup.addActor(table);
        table.setPosition(dialogGroup.getWidth()/2,dialogGroup.getHeight()/2,Align.center);
    }

    private MyImageButton getBtn(String text) {
        Image btnStartImage = new Image(new NinePatch(new Texture("images/ui/buttonUp.png"),5,5,5,5));
        btnStartImage.setSize(400,100);
        Label label = new Label(text,new Label.LabelStyle(){{
            font = new BitmapFont(Gdx.files.internal("main/fonts/gamefont_proportional.fnt"));
        }});
        label.setAlignment(Align.center);
        label.setFontScale(6);
        return new MyImageButton(btnStartImage,label);
    }
}

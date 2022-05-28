package ogz.tripeaks.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.kw.gdx.BaseGame;
import com.kw.gdx.screen.BaseScreen;

public class LoadingScreen extends BaseScreen {

    public LoadingScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
//        Pixmap pixmap = new Pixmap(10,10,Pixmap.Format.RGBA8888);
//        for (int i = 0; i < 10; i++) {
//            for (int i1 = 0; i1 < 10; i1++) {
//                pixmap.drawPixel(i,i1, Color.BLUE.toIntBits());
//            }
//        }
//        Image image = new Image(new Texture(pixmap));
////        addActor(image);
        stage.addAction(Actions.delay(3,Actions.run(()->{
            setScreen(MainScreen.class);
        })));

    }

}


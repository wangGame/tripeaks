package kw.tripeak.group;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.label.Label4;
import com.kw.gdx.listener.ButtonListener;

import kw.tripeak.TripeakGame;
import kw.tripeak.asset.FontResource;
import kw.tripeak.constant.LevelConfig;
import kw.tripeak.pref.TripeakPreferece;

public class GameActor extends Group {
    private Label4 levelNum;
    public GameActor(Actor image, int index,GameActorListener listener){
        addActor(image);
        setName("level"+index);
        setSize(image.getWidth(),image.getHeight());
        levelNum = new Label4(index+"",new Label.LabelStyle(){{
            font = FontResource.getInstance().aoutline_30;
        }});
        levelNum.setAlignment(Align.center);
        levelNum.setPosition(getWidth()/2,getHeight()/2 + 10,Align.center);
        addActor(levelNum);
        setPosition(image.getX(Align.center),image.getY(Align.center), Align.center);
        image.setPosition(getWidth()/2,getHeight()/2,Align.center);
        addListener(new ButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                LevelConfig.levelNum = index;
                listener.click();
            }
        });
        setOrigin(Align.center);
        if (TripeakPreferece.getInstance().getCurrentLevel()<index) {
            image.setColor(Color.valueOf("999999ff"));
        }
    }

    public interface GameActorListener{
        public void click();
    }
}

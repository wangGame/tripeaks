package kw.tripeak.group;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.label.Label4;

import kw.tripeak.asset.FontResource;

public class GameActor extends Group {
    private Label4 levelNum;
    public GameActor(Actor image, int index){
        addActor(image);
        setSize(image.getWidth(),image.getHeight());
        levelNum = new Label4(index+"",new Label.LabelStyle(){{
            font = FontResource.getInstance().aoutline_30;
        }});
        levelNum.setAlignment(Align.center);
        levelNum.setPosition(getWidth()/2,getHeight()/2 + 10,Align.center);
        addActor(levelNum);
        setPosition(image.getX(Align.center),image.getY(Align.center), Align.center);
        image.setPosition(getWidth()/2,getHeight()/2,Align.center);
        setDebug(true);
    }
}

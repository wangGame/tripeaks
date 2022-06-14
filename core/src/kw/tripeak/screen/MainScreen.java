package kw.tripeak.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.SnapshotArray;
import com.kw.gdx.BaseGame;
import com.kw.gdx.ads.Constant;
import com.kw.gdx.annotation.ScreenResource;
import com.kw.gdx.listener.OrdinaryButtonListener;
import com.kw.gdx.screen.BaseScreen;

import kw.tripeak.dialog.SettingDialog;
import kw.tripeak.dialog.SuccessDialog;
import kw.tripeak.group.GameActor;
import kw.tripeak.pref.TripeakPreferece;

@ScreenResource("cocos/MainScene.json")
public class MainScreen extends BaseScreen {
    private Label coinNum;
    public MainScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
        int currentLevel = TripeakPreferece.getInstance().getCurrentLevel();
        Actor panel_1 = rootView.findActor("scrollpaneBg");
        Group panel_2 = rootView.findActor("mapPoint");
        float v = Constant.GAMEHIGHT / panel_1.getHeight();
        panel_1.setScale(v);
        panel_2.setScale(v);
        Group group = new Group();
        group.addActor(panel_1);
        group.addActor(panel_2);
        group.setSize(panel_1.getWidth(),1080);
        ScrollPane pane = new ScrollPane(group,new ScrollPane.ScrollPaneStyle());
        addActor(pane);
        pane.toBack();
        pane.setHeight(1080);
        pane.setWidth(1920);
        pane.setY(Constant.GAMEHIGHT/2, Align.center);
        SnapshotArray<Actor> children = panel_2.getChildren();
        children.removeIndex(0);
        children.removeIndex(0);
        System.out.println(children.size);
        int index = 0;
        SnapshotArray<Actor> tempChildren = new SnapshotArray<>(children);
//        tempChildren.removeIndex(0);
//        tempChildren.removeIndex(0);
        for (Actor child : tempChildren) {
            index++;
            GameActor actor = new GameActor(child, index, new GameActor.GameActorListener() {
                @Override
                public void click() {
                    setScreen(GameScreen.class);
                }
            });
            panel_2.addActor(actor);
        }
        Actor actor = panel_2.findActor("level"+currentLevel);
        Image point = new Image(new Texture("images/point.png"));
        panel_2.addActor(point);
        point.setPosition(actor.getX(Align.center)+37,actor.getY(Align.center)+20,Align.bottom);
        float baseX = actor.getX(Align.center)+37;
        float baseY = actor.getY(Align.center)+20;
        point.addAction(Actions.forever(
                Actions.sequence(
                    Actions.moveToAligned( baseX ,baseY+10,Align.bottom,0.2F),
                    Actions.moveToAligned(baseX,baseY+0,Align.bottom,0.2F),
                    Actions.moveToAligned(baseX,baseY-10,Align.bottom,0.2F),
                    Actions.moveToAligned(baseX,baseY,Align.bottom,0.2F)
                )));
        point.setTouchable(Touchable.disabled);
        Actor setting_btn = findActor("setting_btn");
        setting_btn.setTouchable(Touchable.enabled);
        setting_btn.addListener(new OrdinaryButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                dialogManager.showDialog(new SettingDialog());
            }
        });

        Group coinGroup = findActor("coinGroup");
        Label coinNum = coinGroup.findActor("coinNum");
        coinNum.setText(TripeakPreferece.getInstance().getScoreNum());
    }
}

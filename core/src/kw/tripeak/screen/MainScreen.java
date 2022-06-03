package kw.tripeak.screen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.utils.SnapshotArray;
import com.kw.gdx.BaseGame;
import com.kw.gdx.annotation.ScreenResource;
import com.kw.gdx.screen.BaseScreen;

import kw.tripeak.group.GameActor;

@ScreenResource("cocos/MainScene.json")
public class MainScreen extends BaseScreen {
    public MainScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
        Actor panel_1 = rootView.findActor("Panel_1");
        Group panel_2 = rootView.findActor("Panel_2");
        Group group = new Group();
        group.addActor(panel_1);
        group.addActor(panel_2);
        group.setSize(panel_1.getWidth(),panel_1.getHeight());
        ScrollPane pane = new ScrollPane(group,new ScrollPane.ScrollPaneStyle());
        addActor(pane);
        pane.setHeight(1024);
        pane.setWidth(1920);

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
            GameActor actor = new GameActor(child,index);
            panel_2.addActor(actor);
        }
    }
}

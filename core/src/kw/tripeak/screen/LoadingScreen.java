package kw.tripeak.screen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.kw.gdx.BaseGame;
import com.kw.gdx.ads.Constant;
import com.kw.gdx.annotation.ScreenResource;
import com.kw.gdx.audio.Asset;
import com.kw.gdx.screen.BaseScreen;

import kw.tripeak.asset.FontResource;

@ScreenResource("cocos/LoadingScreen.json")
public class LoadingScreen extends BaseScreen {
    private Image process;
    public LoadingScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
        FontResource.getInstance().loadRes();
        Actor bg = findActor("bg");
        bg.setScale(Constant.GAMEHIGHT/720.0F);
        process = findActor("loading_process");
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        System.out.println(Asset.assetManager.update());
        process.setWidth(Math.max(60,Asset.assetManager.getProgress() * 585.0F));
        if (Asset.assetManager.update()){
            FontResource.getInstance().getRes();
            Asset.getAsset().getResource(FontResource.class);
            setScreen(MainScreen.class);
        }
    }
}

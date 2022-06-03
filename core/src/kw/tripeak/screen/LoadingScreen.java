package kw.tripeak.screen;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.kw.gdx.BaseGame;
import com.kw.gdx.annotation.ScreenResource;
import com.kw.gdx.audio.Asset;
import com.kw.gdx.screen.BaseScreen;

import kw.tripeak.asset.FontResource;

@ScreenResource("cocos/LoadingScreen.json")
public class LoadingScreen extends BaseScreen {
    public LoadingScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
        FontResource.getInstance().loadRes();
        Asset.assetManager.finishLoading();
        stage.addAction(Actions.delay(1,Actions.run(()->{
            FontResource.getInstance().getRes();
            Asset.getAsset().getResource(FontResource.class);
            setScreen(MainScreen.class);
        })));
    }
}

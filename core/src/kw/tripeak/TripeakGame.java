package kw.tripeak;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.kw.gdx.BaseGame;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.resource.annotation.GameInfo;
import com.kw.gdx.constant.Configuration;
import com.kw.gdx.utils.Assert;

import kw.tripeak.screen.LoadingScreen;


@GameInfo(height = 1920,width = 1080)
public class TripeakGame extends BaseGame {
    @Override
    protected void loadingView() {
        super.loadingView();
        String mas = null;
        Assert.checkInfo(mas != null,"mas is null");
        Configuration.scale =0.2F;
        Configuration.device_state = Configuration.DeviceState.poor;
        Constant.viewColor.set(35.0F/255F,36.0F/255F,51.0F/255F,1);
        setScreen(new LoadingScreen(this));

        ///指定鼠标
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("cursor.png")), 0, 0));
    }
}

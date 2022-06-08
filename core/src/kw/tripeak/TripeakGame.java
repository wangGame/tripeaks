package kw.tripeak;

import com.badlogic.gdx.physics.box2d.Contact;
import com.kw.gdx.BaseGame;
import com.kw.gdx.ads.Constant;
import com.kw.gdx.annotation.GameInfo;
import com.kw.gdx.constant.Configuration;

import kw.tripeak.screen.LoadingScreen;


@GameInfo(width = 1920,height = 1080)
public class TripeakGame extends BaseGame {
    @Override
    protected void loadingView() {
        super.loadingView();
        Configuration.scale =1F;
        Configuration.device_state = Configuration.DeviceState.poor;
        Constant.viewColor.set(35.0F/255F,36.0F/255F,51.0F/255F,1);
        setScreen(new LoadingScreen(this));
    }
}

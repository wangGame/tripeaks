package ogz.tripeaks;

import com.kw.gdx.BaseGame;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.resource.annotation.GameInfo;

import ogz.tripeaks.screen.LoadingScreen;

@GameInfo(width = 1280,height = 720)
public class TripeakGame extends BaseGame {
    @Override
    protected void loadingView() {
        super.loadingView();
        Constant.viewColor.set(35.0F/255F,36.0F/255F,51.0F/255F,1);
        setScreen(new LoadingScreen(this));
    }
}

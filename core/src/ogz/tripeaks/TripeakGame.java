package ogz.tripeaks;

import com.kw.gdx.BaseGame;
import com.kw.gdx.ads.Constant;
import com.kw.gdx.annotation.GameInfo;

import javax.naming.CompositeName;

import ogz.tripeaks.screen.LoadingScreen;

@GameInfo(width = 1280,height = 720)
public class TripeakGame extends BaseGame {
    @Override
    protected void loadingView() {
        super.loadingView();
        Constant.viewColor.set(0.8F,0.2F,0.2F,1);
        setScreen(new LoadingScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }
}

package ogz.tripeaks.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kw.gdx.ads.Constant;
import ogz.tripeaks.TripeakGame;

public class DesktopLauncher {
    public static void main(String[] args) {
        float scale = 4.0F;
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title="TriPeaks";
//        config.setWindowIcon(
//                "images/icon16.png",
//                "images/icon24.png",
//                "images/icon32.png",
//                "images/icon48.png"
//            );
        config.width = (int) (640*1.5F);
        config.height = (int) (360*1.5F);
        new LwjglApplication(new TripeakGame(), config);
    }
}
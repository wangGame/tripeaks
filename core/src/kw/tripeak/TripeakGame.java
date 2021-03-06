package kw.tripeak;

import com.anrutils.example.ANRError;
import com.anrutils.example.ANRWatchDog;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.kw.gdx.BaseGame;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.resource.annotation.GameInfo;
import com.kw.gdx.constant.Configuration;
import com.kw.gdx.utils.Assert;
import com.kw.gdx.utils.log.NLog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import kw.tripeak.screen.LoadingScreen;


@GameInfo(height = 1920,width = 1080)
public class TripeakGame extends BaseGame {
    private ANRWatchDog anrWatchDog;
    @Override
    protected void loadingView() {
        super.loadingView();
        anrWatchDog = new ANRWatchDog(118);
        anrWatchDog
                .setANRListener(new ANRWatchDog.ANRListener() {
                    @Override
                    public void onAppNotResponding(ANRError error) {
                        NLog.e("ANR-Watchdog-Demo", "Detected Application Not Responding!");

                        // Some tools like ACRA are serializing the exception, so we must make sure the exception serializes correctly
                        try {
                            new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(error);
                        }
                        catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                        NLog.i("ANR-Watchdog-Demo", "Error was successfully serialized");
                        try {
                            throw error;
                        } catch (ANRError anrError) {
                            anrError.printStackTrace();
                        }
                    }
                })
                .setANRInterceptor(new ANRWatchDog.ANRInterceptor() {
                    @Override
                    public long intercept(long duration) {
                        long ret = 4 * 1000 - duration;
                        if (ret > 0)
                            NLog.i("ANR-Watchdog-Demo", "Intercepted ANR that is too short (" + duration + " ms), postponing for " + ret + " ms.");
                        return ret;
                    }
                })
        ;
        anrWatchDog.setReportThreadNamePrefix("|ANR-WatchDog|");
        anrWatchDog.start();

        String mas = null;
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Assert.checkInfo(mas != null,"mas is null");
        Configuration.scale =0.2F;
        Configuration.device_state = Configuration.DeviceState.poor;
        Constant.viewColor.set(35.0F/255F,36.0F/255F,51.0F/255F,1);
        setScreen(new LoadingScreen(this));

        ///????????????
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("cursor.png")), 0, 0));
    }

    @Override
    public void render() {
        super.render();
        anrWatchDog.reset();
    }
}

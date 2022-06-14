package ogz.tripeaks.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.BaseGame;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.listener.OrdinaryButtonListener;
import com.kw.gdx.screen.BaseScreen;

import ogz.tripeaks.btn.MyImageButton;
import ogz.tripeaks.dialog.SuccessDialog;

public class MainScreen extends BaseScreen {
    public MainScreen(BaseGame game) {
        super(game);
    }

    private MyImageButton startBtn;
    private MyImageButton selectBtn;
    private MyImageButton exitBtn;

    @Override
    public void initView() {
//        模拟banner
//        BannerManager.setVisible(true);
        Image bg = new Image(new Texture("main/images/light_title.png"));
        addActor(bg);
        bg.setSize(Constant.GAMEWIDTH,Constant.GAMEHIGHT);
        bg.setPosition(Constant.GAMEWIDTH/2,Constant.GAMEHIGHT/2,Align.center);
        Table table = new Table(){{
            startBtn  = getBtn("START");
            selectBtn = getBtn("SELECT");
            exitBtn   = getBtn("EXIT");
            add(startBtn).pad(30).row();
            add(selectBtn).pad(30).row();
            add(exitBtn);
            pack();
        }};
        stage.addActor(table);
        table.setPosition(Constant.GAMEWIDTH/2,Constant.GAMEHIGHT/2,Align.center);
        initListener();
    }

    public void initListener(){
        startBtn.addListener(new OrdinaryButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                setScreen(GameScreen.class);
            }
        });

        selectBtn.addListener(new OrdinaryButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
            }
        });

        exitBtn.addListener(new OrdinaryButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
            }
        });

        dialogManager.showDialog(new SuccessDialog(new Runnable() {
            @Override
            public void run() {

            }
        }));
    }

    private MyImageButton getBtn(String text) {
        Image btnStartImage = new Image(new NinePatch(new Texture("images/ui/buttonUp.png"),5,5,5,5));
        btnStartImage.setSize(400,100);
        Label label = new Label(text,new Label.LabelStyle(){{
            font = new BitmapFont(Gdx.files.internal("main/fonts/gamefont_proportional.fnt"));
        }});
        label.setAlignment(Align.center);
        label.setFontScale(6);
        return new MyImageButton(btnStartImage,label);
    }
}

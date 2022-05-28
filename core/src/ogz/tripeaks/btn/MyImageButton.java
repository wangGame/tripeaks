package ogz.tripeaks.btn;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.utils.Layer;

public class MyImageButton extends Group {
    private Image touDownImage;
    private Label btnLabel;

    public MyImageButton(Image touDownImage,Label btnLabel){
        this.touDownImage = touDownImage;
        this.btnLabel = btnLabel;
        setSize(touDownImage.getWidth(),touDownImage.getHeight());
        btnLabel.setPosition(getWidth()/2,getHeight()/2, Align.center);
        addActor(touDownImage);
        addActor(btnLabel);
        setOrigin(Align.center);
    }
}

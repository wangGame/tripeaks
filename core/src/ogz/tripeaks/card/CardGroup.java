package ogz.tripeaks.card;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.listener.ButtonListener;

public class CardGroup extends Group {
    private Image imageBg;
    private Image imageFront;
    private Image cardData;
    private int posx;
    private int posy;
    private int index;
    private boolean select;

    public CardGroup(Integer integer){
        this.index = integer;
        setSize(75, 101);
        imageBg = new Image(new Texture("images/cards/dark_card.png"));
        imageFront = new Image(new Texture("images/cards/card_back.png"));
        cardData = new Image(new Texture("images/cards/dark_card_"+integer+".png"));
        addActor(cardData);
        addActor(imageBg);
        addActor(imageFront);
        imageBg.setSize(getWidth(),getHeight());
        imageFront.setSize(getWidth(),getHeight());
        cardData.setOrigin(Align.center);
        cardData.setScale(3);
        imageBg.setPosition(getWidth()/2,getHeight()/2, Align.center);
        imageFront.setPosition(getWidth()/2,getHeight()/2,Align.center);
        cardData.setPosition(getWidth()/2,getHeight()/2,Align.center);

        addListener(new ButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                listener.click(CardGroup.this);
            }
        });
        setTouchable(Touchable.disabled);
        setOrigin(Align.center);
    }


    public void select(boolean select){
        this.select = select;
        if (select){
            setTouchable(Touchable.enabled);
            imageFront.setVisible(false);
            cardData.toFront();
        }
    }

    public boolean isSelect() {
        return select;
    }

    public void onlySelect(boolean select){
        this.select = select;
        if (select){
            imageFront.setVisible(false);
            cardData.toFront();
        }
    }

    public void setPosX(int posx) {
        this.posx = posx;
    }

    public void setPosY(int posy) {
        this.posy = posy;
    }

    public int getPosX() {
        return posx;
    }

    public int getPosY(){
        return posy;
    }

    private CardListener listener;
    public void setCardListener(CardListener cardListener){
        this.listener = cardListener;
    }

    public int packNum() {
        return index % 13;
    }

    public int getIndex() {
        return index;
    }

    public void update(int index) {
        int zIndex = cardData.getZIndex();
        cardData.remove();
        cardData = new Image(new Texture("images/cards/dark_card_"+index+".png"));
        addActor(cardData);
        cardData.setZIndex(zIndex);
        cardData.setOrigin(Align.center);
        cardData.setScale(3);
        cardData.setPosition(getWidth()/2,getHeight()/2,Align.center);
    }

    private int id;
    public void setId(int id) {
        this.id = id;
    }

    private int[] arr;
    public void check(int arr[]){
        this.arr = arr;
    }

    public int[] getArr() {
        return arr;
    }

    public int getid() {
        return id;
    }

    public interface CardListener{
        public void click(CardGroup cardGroup);
    }
}

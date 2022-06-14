package kw.tripeak.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Queue;
import com.kw.gdx.BaseGame;
import com.kw.gdx.annotation.ScreenResource;
import com.kw.gdx.dialog.base.BaseDialog;
import com.kw.gdx.listener.ButtonListener;
import com.kw.gdx.listener.OrdinaryButtonListener;
import com.kw.gdx.screen.BaseScreen;
import java.util.ArrayList;

import kw.tripeak.data.Board;
import kw.tripeak.data.GameData;
import kw.tripeak.data.PeakBean;
import kw.tripeak.dialog.SettingDialog;
import kw.tripeak.dialog.SuccessDialog;
import kw.tripeak.group.CardGroup;
import kw.tripeak.dialog.FailedDialog;
import kw.tripeak.pref.TripeakPreferece;

@ScreenResource("cocos/GameScreen.json")
public class GameScreen extends BaseScreen {
    private int current=0;
    private CardGroup baseGroup;
    private Queue<CardGroup> stack = new Queue<>();
    private ArrayList<CardGroup> packAll = new ArrayList<>();
    private ArrayList<String> livePeack = new ArrayList<>();
    private Label label;

    public GameScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
        GameData data = new GameData();
        findActor("starProcess").setVisible(false);
        Group tempGroup = findActor("peakGroup");
        tempGroup.setY(tempGroup.getY() - 140);
        PeakBean data1 = data.getData();
        Group coinGroup = findActor("coinGroup");
        Label coin_num = coinGroup.findActor("coin_num");
        coin_num.setText(TripeakPreferece.getInstance().getScoreNum());
        for (Board board : data1.getBoards()) {
            CardGroup cardGroup = new CardGroup(board);
            tempGroup.addActor(cardGroup);
            cardGroup.setCardListener(new CardGroup.CardListener() {
                @Override
                public void click(CardGroup cardGroup) {
                    int ans1 = (cardGroup.packNum() + 1) % 13;
                    int ans2 = (cardGroup.packNum() - 1) % 13;
                    ans1 = ans1 < 0 ? ans1+13:ans1;
                    ans2 = ans2 < 0 ? ans2+13:ans2;
                    System.out.println(current);
                    if (ans1 == current || ans2 == current) {
//
                        TripeakPreferece.getInstance().updateScore(100);
                        coin_num.setText(TripeakPreferece.getInstance().getScoreNum());
                        current = cardGroup.packNum();

                        Actor basePeak = findActor("basePeak");
                        float x = basePeak.getX(Align.center);
                        float y = basePeak.getY(Align.center);
                        Vector2 vector2 = new Vector2(x,y);
                        rootView.localToStageCoordinates(vector2);
                        tempGroup.stageToLocalCoordinates(vector2);
                        cardGroup.toFront();
                        cardGroup.addAction(Actions.sequence(
                                Actions.moveToAligned(vector2.x,vector2.y,Align.center,0.3f),
                                Actions.run(()->update(cardGroup))));
                    }else {
                        cardGroup.addAction(Actions.repeat(2,
                                Actions.sequence(
                                    Actions.rotateTo(20,0.1F),
                                    Actions.rotateTo(0,0.1F),
                                    Actions.rotateTo(-20,0.1F),
                                    Actions.rotateTo(0,0.1F)
                                )));
                    }
                    if (packAll.size() == 0){
                        System.out.println("success");
                        dialogManager.showDialog(new SuccessDialog(new Runnable(){
                            @Override
                            public void run() {
                                setScreen(GameScreen.class);
                            }
                        }));
                        return;
                    }
                    if (stack.size<=0){
                        checkPeaks();
                    }
                }
            });
            cardGroup.check(board.getCovers());
            livePeack.add(board.getId()+"");
            packAll.add(cardGroup);
        }
        updatePeak();
        Image resetBtn = new Image(new Texture("images/cards/dark_card.png"));
        resetBtn.setSize(75,110);
        resetBtn.setPosition(150,50);
        Group basePeak = findActor("basePeak");


        Board board1 = new Board();
        board1.setRank((int)(Math.random()*12)+1);
        board1.setSuit((int)(Math.random()*4)+1);
        baseGroup = new CardGroup(board1);
        basePeak.addActor(baseGroup);
        basePeak.setX(340);
        baseGroup.setPosition(basePeak.getWidth()/2,basePeak.getHeight()/2,Align.center);
        baseGroup.select(true);
        current = baseGroup.packNum();
        baseGroup.setTouchable(Touchable.disabled);

        Group diPack = findActor("freePeak");
        diPack.setX(960,Align.center);
        Group groupTemp = new Group();
        groupTemp.setHeight(diPack.getHeight());
        int i1 = data.getData().getNum() - 1;
        groupTemp.setWidth(i1*34+144);
        for (int i = 0; i < data.getData().getNum()-1; i++) {
            Board board = new Board();
            board.setRank((int)(Math.random()*12)+1);
            board.setSuit((int)(Math.random()*4)+1);
            CardGroup cardGroup = new CardGroup(board);
            groupTemp.addActor(cardGroup);
            cardGroup.setX(34*i);
            cardGroup.toBack();
            stack.addLast(cardGroup);
        }
        diPack.addActor(groupTemp);
        groupTemp.setX(diPack.getWidth()/2,Align.center);
        Image addBtn = new Image(new Texture("images/cards/peak_back.png"));
        addActor(addBtn);
        addBtn.setOrigin(Align.center);
        addBtn.setPosition(1440,132,Align.left);
        addBtn.addListener(new ButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (stack.size<=0)return;
                CardGroup pop = stack.removeFirst();
                if (pop==null){
                    System.out.println("failed");
                    return;
                }
                pop.remove();
                baseGroup.update(pop.getIndex(),pop.getSuit());
                current = pop.packNum();
                if (stack.size<=0) {
                    checkPeaks();
                }
            }
        });

        Actor setting_btn = findActor("setting_btn_7");
        setting_btn.setTouchable(Touchable.enabled);
        setting_btn.addListener(new OrdinaryButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                dialogManager.showDialog(new SettingDialog());
            }
        });
//        extracted(data);
    }

    public void updatePeak(){
        for (CardGroup cardGroup : packAll) {
            if (!cardGroup.isSelect()) {
                boolean isSelect = true;
                for (int i : cardGroup.getArr()) {
                    if (livePeack.contains(i+"")) {
                        isSelect = false;
                        break;
                    }
                }
                if (isSelect){
                    cardGroup.select(true);
                }
            }
        }
    }

    public void checkPeaks(){
        for (CardGroup cardGroup : packAll) {
            if (!cardGroup.isSelect())continue;
            int ans1 = (cardGroup.packNum() + 1) % 13;
            int ans2 = (cardGroup.packNum() - 1) % 13;
            ans1 = ans1 < 0 ? ans1 + 13 : ans1;
            ans2 = ans2 < 0 ? ans2 + 13 : ans2;
            if (ans1 == current || ans2 == current) {
                return;
            }
        }
        dialogManager.showDialog(new FailedDialog(new Runnable() {
            @Override
            public void run() {
                setScreen(GameScreen.class);
            }
        }));
//        System.out.println("failed ---------------");
        return;
    }

    public void update(CardGroup currentGroup){
        currentGroup.remove();
        packAll.remove(currentGroup);
        livePeack.remove(currentGroup.getid()+"");
        baseGroup.update(currentGroup.getIndex(),currentGroup.getSuit());
        updatePeak();
    }

    @Override
    protected BaseDialog back() {
        BaseDialog back = super.back();
        if (back instanceof SuccessDialog ||back instanceof FailedDialog){
            setScreen(GameScreen.class);
        }else {
            setScreen(MainScreen.class);
        }
        return back;
    }
}

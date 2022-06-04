package kw.tripeak.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Queue;
import com.kw.gdx.BaseGame;
import com.kw.gdx.ads.Constant;
import com.kw.gdx.annotation.ScreenResource;
import com.kw.gdx.listener.ButtonListener;
import com.kw.gdx.screen.BaseScreen;
import java.util.ArrayList;
import java.util.Collections;

import kw.tripeak.data.Board;
import kw.tripeak.data.GameData;
import kw.tripeak.data.PeakBean;
import kw.tripeak.dialog.SuccessDialog;
import kw.tripeak.group.CardGroup;
import ogz.tripeaks.dialog.FailedDialog;

@ScreenResource("cocos/GameScreen.json")
public class GameScreen extends BaseScreen {
    private int current=0;
    private CardGroup baseGroup;
    private Queue<CardGroup> stack = new Queue<>();
    private ArrayList<CardGroup> packAll = new ArrayList<>();
    private ArrayList<String> livePeack = new ArrayList<>();

    public GameScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
        GameData data = new GameData();
        Group tempGroup = findActor("peakGroup");
//        tempGroup.setPosition(Constant.GAMEWIDTH/2,Constant.GAMEHIGHT/2,Align.center);
        PeakBean data1 = data.getData();
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
                    System.out.println(cardGroup.packNum()+"  "+ans1);
                    System.out.println(cardGroup.packNum()+"  "+ans2);
                    System.out.println(current);
                    if (ans1 == current || ans2 == current) {
                        update(cardGroup);
                        current = cardGroup.packNum();
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
        baseGroup = new CardGroup(data1.getBoard());
        basePeak.addActor(baseGroup);
        basePeak.setX(340);
        basePeak.setDebug(true);
        baseGroup.setPosition(basePeak.getWidth()/2,basePeak.getHeight()/2,Align.center);
        baseGroup.select(true);
        current = baseGroup.packNum();
        baseGroup.setTouchable(Touchable.disabled);

        Group diPack = findActor("freePeak");
        diPack.setDebug(true);
        diPack.setX(960,Align.center);
        for (int i = 0; i < 23; i++) {
            Board board = new Board();
            board.setRank((int)(Math.random()*12)+1);
            board.setSuit((int)(Math.random()*4)+1);
            CardGroup cardGroup = new CardGroup(board);
            diPack.addActor(cardGroup);
            cardGroup.setX(34*i);
            cardGroup.toBack();
            stack.addLast(cardGroup);
        }
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
        stack.first().onlySelect(true);

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
        dialogManager.showDialog(new FailedDialog());
//        System.out.println("failed ---------------");
        return;
    }

    public void update(CardGroup currentGroup){
        currentGroup.remove();
        packAll.remove(currentGroup);
        System.out.println(livePeack.size()+"----------------------");
        livePeack.remove(currentGroup.getid()+"");
        System.out.println(livePeack.size()+"----------------------"+currentGroup.getid());
        baseGroup.update(currentGroup.getIndex(),currentGroup.getSuit());
        updatePeak();
    }
}

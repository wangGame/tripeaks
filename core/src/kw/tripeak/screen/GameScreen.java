package kw.tripeak.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Queue;
import com.kw.gdx.BaseGame;
import com.kw.gdx.ads.Constant;
import com.kw.gdx.listener.ButtonListener;
import com.kw.gdx.screen.BaseScreen;
import java.util.ArrayList;

import kw.tripeak.data.Board;
import kw.tripeak.data.GameData;
import kw.tripeak.data.PeakBean;
import ogz.tripeaks.card.CardGroup;
import ogz.tripeaks.dialog.FailedDialog;
import ogz.tripeaks.dialog.SuccessDialog;

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
        Group group = new Group();
        addActor(group);
        Group tempGroup = new Group();
        addActor(tempGroup);
        tempGroup.setPosition(Constant.GAMEWIDTH/2,Constant.GAMEHIGHT/2,Align.center);
        PeakBean data1 = data.getData();
        for (Board board : data1.getBoards()) {
            CardGroup cardGroup = new CardGroup(board.getRank());
            cardGroup.setId(board.getId());
            cardGroup.setRotation(board.getRotation());
            cardGroup.setPosition(board.getPosX()*0.5F,board.getPoxY()*0.5F);
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
                                setScreen(new ogz.tripeaks.screen.GameScreen(game));
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
        group.setSize(480*2-10,342+110);
        group.setDebug(true);
        group.setPosition(Constant.GAMEWIDTH/2,Constant.GAMEHIGHT/2+100,Align.center);
        Image resetBtn = new Image(new Texture("images/cards/dark_card.png"));
        resetBtn.setSize(75,110);
        resetBtn.setPosition(150,50);
        baseGroup = new CardGroup(data1.getHandRank());
        addActor(baseGroup);
        baseGroup.setPosition(250,50);
        baseGroup.select(true);
        current = baseGroup.packNum();
        baseGroup.setTouchable(Touchable.disabled);

        Group diPack = new Group();
        addActor(diPack);
        for (int i = 0; i < 23; i++) {
            CardGroup cardGroup = new CardGroup(1);
            diPack.addActor(cardGroup);
            cardGroup.setX(20*i);
            cardGroup.toBack();
            stack.addLast(cardGroup);
        }
        diPack.setPosition(500,50);
        Image addBtn = new Image(new Texture("images/ui/deal.png"));
        addActor(addBtn);
        addBtn.setSize(75,110);
        addBtn.setPosition(1050,50);
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
                baseGroup.update(pop.getIndex());
                current = pop.packNum();
                if (stack.size>0) {
                    stack.first().onlySelect(true);
                }else {
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
        baseGroup.update(currentGroup.getIndex());
        updatePeak();
    }
}

package ogz.tripeaks.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Queue;
import com.kw.gdx.BaseGame;
import com.kw.gdx.ads.Constant;
import com.kw.gdx.listener.ButtonListener;
import com.kw.gdx.screen.BaseScreen;

import java.util.ArrayList;

import ogz.tripeaks.card.CardGroup;
import ogz.tripeaks.data.GameData;
import ogz.tripeaks.dialog.FailedDialog;
import ogz.tripeaks.dialog.SuccessDialog;

public class GameScreen extends BaseScreen {
    private int current=0;
    private CardGroup baseGroup;
    private Queue<CardGroup> stack = new Queue<>();
    private ArrayList<CardGroup> packAll = new ArrayList<>();

    public GameScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
        GameData data = new GameData();
        int[][] ints = data.parkData();
        ArrayList<Integer> random = data.random();
        Group group = new Group();
        addActor(group);
        CardGroup[][] objects = new CardGroup[ints.length][ints[0].length];
        for (int i = 0; i < ints.length; i++) {
            for (int i1 = 0; i1 < ints[0].length; i1++) {
                int i2 = ints[i][i1];
                if (i2==0)continue;
                CardGroup btn = new CardGroup(random.remove((int)Math.random()*random.size()));
                group.addActor(btn);
                btn.setCardListener(new CardGroup.CardListener() {
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
                            update(objects, cardGroup);
                            current = cardGroup.packNum();
                        }
                        if (packAll.size() == 0){
                            System.out.println("success");
                            dialogManager.showDialog(new SuccessDialog(new Runnable(){
                                @Override
                                public void run() {
                                    setScreen(new GameScreen(game));
                                }
                            }));
                            return;
                        }
                        if (stack.size<=0){
                            checkPeaks();
                        }
                    }
                });
                btn.setSize(75, 101);
                btn.setPosition(i1 * 48, 342 - i * 60);
                objects[i][i1] = btn;
                btn.setPosX(i1);
                btn.setPosY(i);
                packAll.add(btn);
            }
        }
        int length = ints.length;
        for (int i = 0; i < ints[0].length; i++) {
            CardGroup cardGroup = objects[length - 1][i];
            if (cardGroup!=null) {
                cardGroup.select(true);
            }
        }
        group.setSize(480*2-10,342+110);
        group.setDebug(true);
        group.setPosition(Constant.GAMEWIDTH/2,Constant.GAMEHIGHT/2+100,Align.center);

        Image resetBtn = new Image(new Texture("images/cards/dark_card.png"));
//        addActor(resetBtn);
        resetBtn.setSize(75,110);
        resetBtn.setPosition(150,50);

        baseGroup = new CardGroup(random.remove((int)Math.random() * random.size()));
        addActor(baseGroup);
        baseGroup.setPosition(250,50);
        baseGroup.select(true);
        current = baseGroup.packNum();
        baseGroup.setTouchable(Touchable.disabled);

        Group diPack = new Group();
        addActor(diPack);
        for (int i = 0; i < 23; i++) {
            CardGroup cardGroup = new CardGroup(random.remove(random.size() * (int)Math.random()));
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

    public void update(CardGroup[][] cardGroups,CardGroup currentGroup){
        int posx = currentGroup.getPosX()-1;
        int posy = currentGroup.getPosY()-1;
        cardGroups[posy+1][posx+1] = null;
        currentGroup.remove();
        packAll.remove(currentGroup);
        baseGroup.update(currentGroup.getIndex());
        if (posy>=0){
            boolean changePack = true;
            if (posx>0) {
                CardGroup cardGroup = cardGroups[posy][posx];
                if (cardGroup != null) {
                    for (int i = -1; i < 2; i++) {
                        int i1 = posx + i;
                        if (i1 < 0) continue;
                        if (cardGroups[posy+1][i1] != null) {
                            changePack = false;
                            break;
                        }
                    }
                    if (changePack){
                        cardGroup.select(true);
                    }
                }
            }

            changePack = true;
            posx = currentGroup.getPosX()+1;
            if (posx<cardGroups[0].length){
                CardGroup cardGroup = cardGroups[posy][posx];
                if (cardGroup != null) {
                    for (int i = -1; i < 2; i++) {
                        int i1 = posx + i;
                        if (i1 >= cardGroups[0].length) continue;
                        if (cardGroups[posy+1][i1] != null) {
                            changePack = false;
                            break;
                        }
                    }
                    if (changePack){
                        cardGroup.select(true);
                    }
                }
            }
        }

    }

    private void arror(GameData data) {
        ArrayList<GameData.Bean> data1 = data.getData();
        Table table = new Table(){{
            for (int row = 0; row < 3; row++) {
                Group item = new Group(){{
                    for (int i = 0; i < data1.size(); i++) {
                        Image btn = new Image(new Texture("images/cards/dark_card.png"));
                        addActor(btn);
                        GameData.Bean bean = data1.get(i);
                        btn.setSize(75, 101);
                        btn.setPosition(bean.line * 48, 342 - bean.row * 110);
                    }
                    setSize(360,453);
                    setDebug(true);
                }};
                add(item).pad(10);
            }
            setPosition(Constant.GAMEWIDTH/2,Constant.GAMEHIGHT/2,Align.center);
        }};
        addActor(table);
    }
}

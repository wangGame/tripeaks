# gdx工具包使用

gdx工具包，在libGdx的基础上进行封装，加入了spine动画，粒子封装，以及cocos页面布局代码。使用过程中只需要使用注解就可以完成大部分功能

## Game的使用

```java
@GameInfo(width = 1920,height = 1080)
public class TripeakGame extends BaseGame {
    @Override
    protected void loadingView() {
        super.loadingView();
        Constant.viewColor.set(35.0F/255F,36.0F/255F,51.0F/255F,1);
        setScreen(new LoadingScreen(this));
    }
}
```

使用注解的方式配置当前游戏的尺寸，可以省略，使用默认配置的方式。
注解还可以完成 以下的选择：

```java
float width() default Constant.STDWIDTH;
float height() default Constant.STDHIGHT;
int batch() default Constant.SPRITEBATCH;
int viewportType() default Constant.EXTENDVIEWPORT;
```

## cocos的使用

继承BaseScreen

```java
@ScreenResource("cocos/LoadingScreen.json")
public class LoadingScreen extends BaseScreen {
    public LoadingScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
        FontResource.getInstance().loadRes();
        Asset.assetManager.finishLoading();
        stage.addAction(Actions.delay(1,Actions.run(()->{
            FontResource.getInstance().getRes();
            Asset.getAsset().getResource(FontResource.class);
            setScreen(MainScreen.class);
        })));
    }
}
```

然后注解写上cocos文件路径

## font资源的加载

```java
public class FontResource {
    private static FontResource instance;
    @FtResource("fonts/outline.fnt")
    public BitmapFont aoutline_30;

    public static FontResource getInstance(){
        if (instance == null){
            instance = new FontResource();
        }
        return instance;
    }


    public void loadRes(){
        Asset.getAsset().loadAsset(this);
    }

    public void getRes(){
        Asset.getAsset().getResource(this);
    }
}
```

## log的使用


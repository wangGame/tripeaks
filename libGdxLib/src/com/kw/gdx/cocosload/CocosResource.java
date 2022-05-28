package com.kw.gdx.cocosload;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.kw.gdx.audio.Asset;
import com.kw.gdx.log.NLog;
import com.ui.ManagerUIEditor;
import com.ui.loader.ManagerUILoader;

public class CocosResource {

    public static Group loadFile(String resourcePath){
        if (resourcePath!=null) {
            if (Asset.getAsset().assetManager.isLoaded(resourcePath)){
                ManagerUIEditor managerUIEditor1 = Asset.assetManager.get(resourcePath);
                return managerUIEditor1.createGroup();
            }else {
                ManagerUILoader.ManagerUIParameter managerUIParameter1 =
                        new ManagerUILoader.ManagerUIParameter("ccs/", Asset.assetManager);
                Asset.assetManager.load(resourcePath, ManagerUIEditor.class, managerUIParameter1);
                Asset.assetManager.finishLoading();
                ManagerUIEditor managerUIEditor1 = Asset.assetManager.get(resourcePath);
                return managerUIEditor1.createGroup();
            }
        }
        return new Group();
    }

    public static void loadFile1(String resourcePath){
        if (resourcePath!=null) {
            ManagerUILoader.ManagerUIParameter managerUIParameter1 =
                    new ManagerUILoader.ManagerUIParameter("ccs/", Asset.assetManager);
            Asset.assetManager.load(resourcePath, ManagerUIEditor.class, managerUIParameter1);
            Asset.assetManager.finishLoading();
        }
    }

    public static void unLoadFile(String path){
        if (path!=null){
            if (Asset.assetManager.isLoaded(path)){
                NLog.i("%s dispose",path);
                Asset.assetManager.unload(path);
            }
        }
    }
}

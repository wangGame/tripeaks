package kw.tripeak.pref;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import kw.tripeak.constant.LevelConfig;
public class TripeakPreferece {
    private static TripeakPreferece preferece;
    private Preferences preferences;
    private TripeakPreferece(){
        preferences = Gdx.app.getPreferences("TripeakGame");
    }

    public static TripeakPreferece getInstance(){
        if (preferece==null){
            preferece = new TripeakPreferece();
        }
        return preferece;
    }

    public void updateScore(int score){
        preferences.putInteger("score",getScoreNum()+score);
        preferences.flush();
    }

    public int getScoreNum(){
        return preferences.getInteger("score",0);
    }

    public void saveCurrentLevel(int level){
        if (level<getCurrentLevel()) {
            return;
        }
        preferences.putInteger("level_play",level);
        preferences.flush();
    }

    public int getCurrentLevel(){
        return preferences.getInteger("level_play",1);
    }

    public void addLevelNum() {
        LevelConfig.levelNum++;
        LevelConfig.levelNum = LevelConfig.levelNum > 85 ? 85 : LevelConfig.levelNum;
        saveCurrentLevel(LevelConfig.levelNum);
    }

    public boolean isMusic() {
        return preferences.getBoolean("music");
    }

    public void updateMusic(){
        boolean sound = isMusic();
        preferences.putBoolean("music",!sound);
        preferences.flush();
    }

    public boolean isSound() {
        return preferences.getBoolean("sound");
    }

    public void updateSound(){
        boolean sound = isSound();
        preferences.putBoolean("sound",sound);
        preferences.flush();
    }
}

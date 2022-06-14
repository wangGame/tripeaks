package com.anrutils;

import com.anrutils.example.ANRWatchDog;

/**
 * 对https://github.com/SalomonBrys/ANR-WatchDog/进行变化，
 *
 *
 */
public class App {
    public static void main(String[] args) {

        //使用案例
        new ANRWatchDog(34001).start();
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

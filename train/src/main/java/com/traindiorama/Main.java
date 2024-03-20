package com.traindiorama;

import java.util.Timer;
import java.util.TimerTask;

public class Main {
    // 固定実行のデモ
    public static void main(String args[]) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {       
            public void run() {
                System.out.println("aaahhh");
            }
        };
        timer.schedule(task, 1000, 2000);
    };
}
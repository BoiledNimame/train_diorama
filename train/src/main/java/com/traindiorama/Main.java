package com.traindiorama;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.traindiorama.config.ConfigData;
import com.traindiorama.config.yamlLoader;
import com.traindiorama.gui.Controller;

public class Main {
    private static Main instance;
    private final boolean isDebug;
    private final boolean openGUI;

    // インスタンス化(singleton)

    private Main(String[] args) throws InterruptedException {
        if (args.length != 0) {
        List<String> arglist = Arrays.asList(args);
            isDebug = !arglist.isEmpty() ? arglist.contains("-debug") : false;
            openGUI = !arglist.isEmpty() ? arglist.contains("-gui") : false;
            arglist = null;
        } else {
            CountDownLatch latch = new CountDownLatch(1);
            yamlLoader thread = new yamlLoader(latch);

            thread.start();
            latch.await();

            isDebug = ConfigData.getInstance().getConfig().get("debug");
            openGUI = ConfigData.getInstance().getConfig().get("opengui");

            ConfigData.gc();
            thread = null;
            latch = null;
        }
    }

    // main

    public static void main(String[] args) throws InterruptedException {
        instance = new Main(args);
        if (Main.hasGUI()) {
            Controller.launch(Controller.class, args);
        }
    }

    // 後から要りそうになるメソッド

    public static Main getInstance() {
        return instance;
    }

    public static boolean isDebug() {
        return Main.getInstance().isDebug;
    }

    public static boolean hasGUI() {
        return Main.getInstance().openGUI;
    }

    // 停止
    public static void stop(String message, int code) {
        System.out.println(message);
        System.exit(code);
    }
}
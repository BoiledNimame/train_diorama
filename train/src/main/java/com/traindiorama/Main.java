package com.traindiorama;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.traindiorama.config.ConfigData;
import com.traindiorama.config.yamlLoader;
import com.traindiorama.gui.Controller;
import com.traindiorama.pi4j.ContextObjects;

public class Main {
    private static Main instance;
    private final boolean isDebug;
    private final boolean openGUI;
    private final ContextObjects pi4j;
    private final ConfigData config = ConfigData.getInstance();
    private Control controller;

    // インスタンス化(singleton)

    private Main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        yamlLoader thread = new yamlLoader(latch);

        thread.start();
        latch.await();

        if (args.length != 0) {
            List<String> arglist = Arrays.asList(args);

            isDebug = !arglist.isEmpty() ? arglist.contains("-debug") : false;
            openGUI = !arglist.isEmpty() ? arglist.contains("-gui") : false;

            arglist = null;
        } else {
            isDebug = config.getConfig().get("debug");
            openGUI = config.getConfig().get("opengui");
        }

        thread = null;
        latch = null;

        pi4j = new ContextObjects();
    }

    // main

    public static void main(String[] args) throws InterruptedException {
        instance = new Main(args);
        if (Main.hasGUI()) {
            Controller.launch(Controller.class, args);
        } else {
            instance.controller = new Control(instance.pi4j);
        }
    }

    // 後から要りそうになるメソッド

    public static Main getInstance() {
        return instance;
    }

    public static boolean isDebug() {
        return instance.isDebug;
    }

    public static boolean hasGUI() {
        return instance.openGUI;
    }

    public static ContextObjects getContexts() {
        return instance.pi4j;
    }

    public static void setController(Control controller) {
        instance.controller = controller;
    }
    
    public static Control getController() {
        return instance.controller;
    }

    // 停止
    public static void stop(String message, int code) {
        instance.pi4j.console().println(message);
        instance.pi4j.ShutdownContext();
        System.exit(code);
    }
}
package com.traindiorama;

public class Main {
    private static Main instance;
    public final boolean isDebug;

    // インスタンス化(singleton)

    private Main(String[] args) {
        isDebug = args.length != 0 ? args[0].equals("-debug") : false;
    }

    // main

    public static void main(String[] args) {
        instance = new Main(args);
    }

    // 後から要りそうになるメソッド

    public static Main getInstance() {
        return instance;
    }

    public static boolean isDebug() {
        return Main.getInstance().isDebug;
    }
}
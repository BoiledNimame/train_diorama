package com.traindiorama.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.yaml.snakeyaml.Yaml;

import com.traindiorama.Main;

public class yamlLoader extends Thread {
    private final CountDownLatch latch;

    // インスタンス化した時にlatchをここで受け取る
    public yamlLoader(CountDownLatch latch) {
        this.latch = latch;
    }

    // yamlファイルのデータをmapに移す
    @Override
    public void run() {
        Yaml yaml = new Yaml();
        try {
            FileInputStream[] inputStream = new FileInputStream[] {
                    new FileInputStream(Paths.get("./config.yaml").toFile()),
                    new FileInputStream(Paths.get("./gpins.yaml").toFile()) };

            // yamlファイルからデータを抽出する
            Map<String, Boolean> configMap = yaml.load(inputStream[0]);

            Map<String, Integer> gpioNumber = yaml.load(inputStream[1]);

            // メインのクラスに代入しておく
            ConfigData.getInstance().setConfigData(configMap, gpioNumber);

            // ストリームを閉じる
            inputStream[0].close();
            inputStream[1].close();
        } catch (IOException e) {
            e.printStackTrace();
            Main.stop("An error occurred while loading config.yaml. The file does not exist or could not be loaded.",
                    1);
        }
        // このクラスの処理を待たせているので再開させる
        latch.countDown();
    }
}

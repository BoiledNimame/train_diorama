package com.traindiorama.config;



import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.yaml.snakeyaml.Yaml;

import com.traindiorama.Main;

public class yamlLoader extends Thread
{
    private final CountDownLatch latch;

    // インスタンス化した時にlatchをここで受け取る
    public yamlLoader(CountDownLatch latch)
    {
        this.latch = latch;
    }

    // yamlファイルのデータをmapに移す
    @Override
    public void run(){
        Yaml yaml = new Yaml();
        try
        {
            InputStream inputStream = new FileInputStream(Paths.get("./config.yaml").toFile());

            // yamlファイルからデータを抽出する
            @SuppressWarnings("unchecked")
            Map<String, Boolean> configMap = (Map<String, Boolean>) yaml.load(inputStream);

            // メインのクラスに代入しておく
            ConfigData.getInstance().setConfigData(configMap);

            // ストリームを閉じる
            inputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            Main.stop("An error occurred while loading config.yaml. The file does not exist or could not be loaded.", 1);
        }
        // このクラスの処理を待たせているので再開させる
        latch.countDown();
    }
}


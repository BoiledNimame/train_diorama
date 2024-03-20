package com.traindiorama.config;

import java.util.Map;

public class ConfigData {

    private static ConfigData instance = new ConfigData();
    private Map<String, Boolean> config;

    public void setConfigData(Map<String, Boolean> configMap) {
        this.config = configMap;
    }

    public Map<String, Boolean> getConfig() {
        return config;
    }

    public static ConfigData getInstance() {
        return instance;
    }

    public static void gc(){
        getInstance().config = null;
        instance = null;
    }
}

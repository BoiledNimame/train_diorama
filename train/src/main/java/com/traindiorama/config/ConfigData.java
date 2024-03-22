package com.traindiorama.config;

import java.util.Map;

public class ConfigData {

    private static ConfigData instance = new ConfigData();
    private Map<String, Boolean> config;
    private Map<String, Integer> pinNum;

    public void setConfigData(Map<String, Boolean> configMap, Map<String, Integer> pinNum) {
        this.config = configMap;
        this.pinNum = pinNum;
    }

    public Map<String, Integer> getPinNumbers() {
        return pinNum;
    }

    public Map<String, Boolean> getConfig() {
        return config;
    }

    public static ConfigData getInstance() {
        return instance;
    }
}

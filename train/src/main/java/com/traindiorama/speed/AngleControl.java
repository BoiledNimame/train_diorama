package com.traindiorama.speed;

import com.traindiorama.Control;
import com.traindiorama.Main;
import com.traindiorama.pulse.FrequencyData;

public class AngleControl implements State {
    private int state;
    private final Control controller;
    private final int stopMilliseconds;

    public AngleControl(int stopMilliseconds) {
        this.state = STATE_NEUTRAL;
        this.controller = Main.getController();
        this.stopMilliseconds = stopMilliseconds;
    }

    public int getState() {
        return state;
    }

    public void neutral() {
        controller.applyDuty(FrequencyData.id, 41, false);
    }

    public void acceleration() throws InterruptedException {
        for (int i = 1; i <= 65; i++ ) {
            Thread.sleep(i*13);
            controller.applyDuty(FrequencyData.id, Math.toIntExact(Math.round((i)*0.652D)), false);
        }
        Thread.sleep(500);
        for (int i = 1; i <= 65; i++ ) {
            controller.applyDuty(FrequencyData.id, Math.toIntExact(Math.round((65-i)*0.652D)), false);
            Thread.sleep(i*13);
        }
        neutral();
    }

    public void deceleration() throws InterruptedException {
        for (int i = 1; i <= 65; i++ ) {
            Thread.sleep(i*13);
            controller.applyDuty(FrequencyData.id, Math.toIntExact(Math.round((65-i)*0.652D)), false);
        }
        Thread.sleep(stopMilliseconds);
        acceleration();
    }
}

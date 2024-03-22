package com.traindiorama.pulse;

import java.util.TimerTask;

import com.traindiorama.Main;

public class DeferredExecutioner extends TimerTask {
    final int duty;
    public DeferredExecutioner(int duty) {
        this.duty = duty;
    }

    @Override
    public void run() {
        Main.getController().applyDuty(FrequencyData.id, duty, false);
    }
}

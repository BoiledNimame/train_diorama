package com.traindiorama;

import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.io.gpio.digital.PullResistance;
import com.pi4j.io.pwm.PwmType;
import com.traindiorama.config.ConfigData;
import com.traindiorama.pi4j.ContextObjects;
import com.traindiorama.pi4j.Pi4j;
import com.traindiorama.pi4j.Provider;
import com.traindiorama.pulse.MortorData;

public class Control {
    private final ContextObjects pi4j;
    private final ConfigData config = ConfigData.getInstance();

    public Control(ContextObjects context) {
        pi4j = context;
        initalize();
    }

    private void initalize() {
        pi4j.addPWM(MortorData.id, Pi4j.setupGPIO(
                pi4j.getContext(),
                "PWM" + config.getPinNumbers().get("pwm"),
                "mainMortorPwm",
                config.getPinNumbers().get("pwm"),
                PwmType.HARDWARE,
                0,
                0,
                Provider.PIGPIO_PWM));

        for (int i=1; i<=3; i++) {
            if (config.getPinNumbers().get("leadsw"+i)!=0) {
            pi4j.addInput("leadsw"+i, Pi4j.setupGPIO(
                pi4j.getContext(),
                "LSW"+i,
                "LEAD_SWITCH_"+i,
                config.getPinNumbers().get("leadsw"+i),
                PullResistance.PULL_UP,
                2000,
                Provider.PIGPIO_IN));
            }

            // リードスイッチ:ONならPWM制御を停止
            pi4j.getInput("leadsw"+i).addListener(e -> {
                if (e.state()==DigitalState.HIGH) {
                    Main.getController().applyDuty(MortorData.id, 0, false);
                }
            });
        }
    }

    public void applyDuty(String id, int duty, boolean dump) {
        // 最低20以上なので計算挟む
        pi4j.getPwm(id).on(getTrueDuty(duty), MortorData.MaxPulseRangeFrequency);
        if (dump) {
            pi4j.console().println("Duty is Applied, Actually Freq: " + pi4j.getPwm(id).getActualFrequency());
        }
    }

    private int getTrueDuty(int duty) {
        return Math.toIntExact(Math.round(duty*0.8))+20 <= 100 ? Math.toIntExact(Math.round(duty*0.8))+20 : 100;
    }

    public void stopPwm(String id) {
        if (pi4j.getPwm(id).isOn()) {
            pi4j.getPwm(id).off();
        }
    }
}

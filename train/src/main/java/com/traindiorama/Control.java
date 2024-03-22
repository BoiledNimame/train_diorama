package com.traindiorama;

import java.util.Timer;

import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.io.gpio.digital.PullResistance;
import com.pi4j.io.pwm.PwmType;
import com.traindiorama.config.ConfigData;
import com.traindiorama.pi4j.ContextObjects;
import com.traindiorama.pi4j.Pi4j;
import com.traindiorama.pi4j.Provider;
import com.traindiorama.pulse.DeferredExecutioner;
import com.traindiorama.pulse.FrequencyData;

public class Control {
    private final ContextObjects pi4j;
    private final ConfigData config = ConfigData.getInstance();

    public Control(ContextObjects context) {
        pi4j = context;
        initalize();
    }

    private void initalize() {
        pi4j.addPWM(FrequencyData.id, Pi4j.setupGPIO(
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
                    Main.getController().applyDuty(FrequencyData.id, 0, false);
                }
            });
        }
    }

    public void applyDuty(String id, int duty, boolean dump) {
        // 最低20以上なので計算挟む
        pi4j.getPwm(id).on(getTrueDuty(duty), FrequencyData.MaxPulseRangeFrequency);
        if (dump) {
            pi4j.console().println("Duty is Applied, Actually Freq: " + pi4j.getPwm(id).getActualFrequency());
        }
    }

    private int getTrueDuty(int duty) {
        return getIntfromDouble(duty*0.8)+20 <= 100 ? getIntfromDouble(duty*0.8)+20 : 100;
    }

    public void stopPwm(String id) {
        if (pi4j.getPwm(id).isOn()) {
            pi4j.getPwm(id).off();
        }
    }

    private int getIntfromDouble(double d) {
        return Math.toIntExact(Math.round(d));
    }

    //------------------------------------// unimplemented //------------------------------------//

    private void deceleration(int initial, int end, int durationMilliseconds) {
        final int split = 50;
        final int interval = getIntfromDouble(durationMilliseconds/split);

        final Timer timer = new Timer();

        final int variation = initial - end;
        final int[] variations = new int[split];
        if (variation/split < 1D){
            for (int i = 0; i < split; i++) {
                switch (i) {
                    case 0:
                        variations[i] = initial;
                        break;

                    case split-1:
                        variations[i] = end;
                        break;

                    default:
                        if (getIntfromDouble(1/(variation/split))!=1) {
                            variations[i] = variations[i-1];
                        } else {
                            variations[i] = variations[i-1]-1;
                        }
                        break;
                }
            }
        } else {
            for (int i = 0; i < split; i++) {
                variations[i] = getIntfromDouble(variation/split);
            }
        }

        for (int i = 0; i < split; i++) {
            switch (i) {
                case split-1:
                    timer.schedule(new DeferredExecutioner(variations[i]), durationMilliseconds);
                    break;
            
                default:
                    timer.schedule(new DeferredExecutioner(variations[i]), interval*i);
                    break;
            }
        }
    }
}

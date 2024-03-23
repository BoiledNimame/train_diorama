package com.traindiorama.pi4j;

import java.util.HashMap;
import java.util.Map;

import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.pwm.Pwm;
import com.pi4j.platform.Platforms;
import com.pi4j.util.Console;
import com.traindiorama.Main;

public class ContextObjects {
    private final Context context;
    private final Platforms platforms;
    private final Console console;
    private final Map<String, DigitalInput> GPIO_IN;
    private final Map<String, DigitalOutput> GPIO_OUT;
    private final Map<String, Pwm> GPIO_PWM;

    public ContextObjects() {
        context = Pi4j.setupContext();
        platforms = Pi4j.setupPlatforms(context);
        console = Pi4j.setupConsole(platforms);

        GPIO_IN = new HashMap<>();
        GPIO_OUT = new HashMap<>();
        GPIO_PWM = new HashMap<>();
    }

    public Console console() {
        return console;
    }

    public Context getContext() {
        return context;
    }

    public void ShutdownContext() {
        console.println("pi4j Object will be shut down.");
        context.shutdown();
    }

    // 冗長?

    public void addInput(String id, DigitalInput input) {
        GPIO_IN.put(id, input);
        if(Main.isDebug()) {
            System.out.println(GPIO_IN+", "+id+", "+input);
        }
    }
    public DigitalInput getInput(String id) {
        return GPIO_IN.get(id);
    }

    public void addOutput(String id, DigitalOutput output) {
        GPIO_OUT.put(id, output);
    }
    public DigitalOutput getOutput(String id) {
        return GPIO_OUT.get(id);
    }

    public void addPWM(String id, Pwm pwm) {
        GPIO_PWM.put(id, pwm);
    }
    public Pwm getPwm(String id) {
        return GPIO_PWM.get(id);
    }
}

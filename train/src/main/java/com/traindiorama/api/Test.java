package com.traindiorama.api;

import java.util.concurrent.TimeUnit;

import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.io.gpio.digital.PullResistance;
import com.pi4j.platform.Platforms;
import com.pi4j.util.Console;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Context context = Pi4j.setupContext();
        Platforms platforms = Pi4j.setupPlatforms(context);
        Pi4j.setupConsole(platforms);
        String provider = "PiGpio";
        DigitalOutput digitalOutput = Pi4j.setupGPIO(context, "mortor_white", "pmw", 18, DigitalState.LOW, DigitalState.LOW, provider);
        System.out.println(digitalOutput);
        Lchika(digitalOutput);
        Lchika(digitalOutput);
        System.out.println("end");
        context.shutdown();
    }

    private static void Lchika(DigitalOutput digitalOutput) throws InterruptedException {
        digitalOutput.state(DigitalState.HIGH);
        Thread.sleep(100);
        digitalOutput.state(DigitalState.LOW);
        Thread.sleep(100);
    }
}
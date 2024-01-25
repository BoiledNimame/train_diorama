package com.traindiorama.api;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.io.gpio.digital.PullResistance;
import com.pi4j.platform.Platforms;
import com.pi4j.util.Console;

public class Pi4j {
    // ref: https://pi4j.com/getting-started/minimal-example-application/
    public static Context setupContext() {
        return Pi4J.newAutoContext();
    }

    public static Platforms setupPlatforms(Context context) {
        return context.platforms();
    }

    public static Console setupConsole(Platforms platforms) {
        Console console = new Console();
        console.box("Pi4J PLATFORMS");
        console.println();
        platforms.describe().print(System.out);
        console.println();
        return console;
    }

    /**
     * ref: https://pi4j.com/documentation/io-examples/digital-input/
     * 
     * @param context      セットアップされたContextオブジェクトを渡してください.
     * @param id           このデジタル入力オブジェクトを表す一意の文字列です.
     * @param name         {@code toString()}や{@code describe()}で使用されます.
     * @param pinBCMNumber BCM: GPIOの数字で指定する, BCM方式で表されるピンの番号です.
     * @param pr           入力抵抗を表します. UPは回路が繋がるとGPIOはHIGH(3.3V), 切断されるとLOW(0V),
     *                     DOWNはその逆を表します.
     * @param debounce     デバウンス(連続入力を防ぐ)時間を指定します. ミリ秒です.
     * @param provider     ref:https://pi4j.com/documentation/providers/
     *                     推奨:pigpio .
     * @return
     */
    public static DigitalInput setupGPIO(Context context, String id, String name, int pinBCMNumber, PullResistance pr,
            long debounce, String provider) {
        return context.create(DigitalInput.newConfigBuilder(context).id(id).name(name)
                .address(pinBCMNumber).pull(pr).debounce(debounce).provider(provider));
    }

    /**
     * 
     * @param context
     * @param id
     * @param name
     * @param pinBCMNumber
     * @param shutdown
     * @param initial
     * @param provider
     * @return 作成済みのセットアップされたデジタル出力オブジェクトを返します.
     */
    public static DigitalOutput setupGPIO(Context context, String id, String name, int pinBCMNumber,
            DigitalState shutdown, DigitalState initial, String provider) {
        return context.create(DigitalOutput.newConfigBuilder(context).id(id).name(name).address(pinBCMNumber)
                .shutdown(shutdown).initial(initial).provider(provider));
    }
}

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
    // ref-> 読み物, doc-> 専門的
    // ref: https://pi4j.com/getting-started/minimal-example-application/
    // doc:
    // https://javadoc.io/doc/com.pi4j/pi4j-core/latest/com.pi4j/module-summary.html
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
     * @param context      セットアップされたContextオブジェクト.
     * @param id           このデジタル入力オブジェクトを表す一意の文字列.
     * @param name         {@code toString()}や{@code describe()}で使用される.
     * @param pinBCMNumber BCM: GPIOの数字で指定する, BCM方式で表されるピンの番号.
     * @param pr           入力抵抗を表します. UPは回路が繋がるとGPIOはHIGH(3.3V), 切断されるとLOW(0V),
     *                     DOWNはその逆を表す.
     * @param debounce     デバウンス(連続入力を防ぐ)時間を指定(ミリ秒).
     * @param provider     {@see https://pi4j.com/documentation/providers/}
     *                     推奨:pigpio .
     * @return
     */
    public static DigitalInput setupGPIO(Context context, String id, String name, int pinBCMNumber, PullResistance pr,
            long debounce, String provider) {
        return context.create(DigitalInput.newConfigBuilder(context).id(id).name(name)
                .address(pinBCMNumber).pull(pr).debounce(debounce).provider(provider));
    }

    /**
     * ref: https://pi4j.com/documentation/io-examples/digital-output/
     * 
     * @param context      セットアップされたContextオブジェクト.
     * @param id           このデジタル入力オブジェクトを表す一意の文字列.
     * @param name         {@code toString()}や{@code describe()}で使用される文字列.
     * @param pinBCMNumber BCM: GPIOの数字で指定する, BCM方式で表されるピンの番号.
     * @param shutdown     シャットダウン時の電圧を指定.
     * @param initial      初期状態での電圧を指定.
     * @param provider     {@see https://pi4j.com/documentation/providers/}
     *                     推奨:pigpio .
     * @return 作成済みのセットアップされたデジタル出力オブジェクトを返します.
     */
    public static DigitalOutput setupGPIO(Context context, String id, String name, int pinBCMNumber,
            DigitalState shutdown, DigitalState initial, String provider) {
        return context.create(DigitalOutput.newConfigBuilder(context).id(id).name(name).address(pinBCMNumber)
                .shutdown(shutdown).initial(initial).provider(provider));
    }
}

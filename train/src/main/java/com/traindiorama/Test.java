package com.traindiorama;

import com.traindiorama.mortor.PWM;
import com.traindiorama.pulse.MortorData;

public class Test implements MortorData {
    public static void main(String args[]) {
        int angle = 90;
        int result = PWM.buildMortorPulse(angle, MaxPulseRangeMicroseconds);
        System.out.println("angle: " + angle + "°, result: " + result + "μs");
    }
}

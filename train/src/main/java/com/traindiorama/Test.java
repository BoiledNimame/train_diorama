package com.traindiorama;

import com.traindiorama.mortor.PWM;
import com.traindiorama.pulse.MortorData;

public class Test implements MortorData {
    public static void main(String args[]) {
        int[] angle = new int[]{90, 100, 1, 20, 130};
        for (int i = 0; i<angle.length;i++ ) {
            int result = PWM.buildMortorPulse(angle[i], MaxPulseRangeMicroseconds);
            System.out.println("angle: " + angle[i] + "°, result: " + result + "μs");
        }
    }
}

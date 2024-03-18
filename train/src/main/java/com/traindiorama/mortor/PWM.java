package com.traindiorama.mortor;

import com.traindiorama.pulse.MortorData;

public class PWM implements MortorData {
    /**
     * build pulse duration
     * 
     * @param Angle angle of moving mortor
     * @param cycle duration of one pulse {@see com.traindiorama.pulse.MortorData}
     *              require mincroseconds
     * @return pulse time (microseconds)
     */
    public static int buildMortorPulse(int angle, int cycle) {
        if (angle <= Angle_Degree_MaxRangePulse) {
            int result = (int) (Math.round((double) ((angle * 2500) / 270)));
            return result <= cycle ? result : cycle;
        } else {
            throw new IllegalArgumentException("angle:" + angle + " is too big! this value must under or equal MaxRange:" + Angle_Degree_MaxRangePulse);
        }
    }
}

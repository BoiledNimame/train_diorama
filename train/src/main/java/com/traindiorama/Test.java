package com.traindiorama;

import com.traindiorama.mortor.Pmw;

public class Test {
    public static void main(String args[]) {
        long duration = 10000; // ms
        int operation = 50; // %
        int division = 300; // 分割
        int waittime = 30; // ms

        Pmw.pulse(duration, operation, division, waittime, System.out);
    }
}

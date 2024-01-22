package com.traindiorama;

import com.traindiorama.mortor.Pmw;

public class Main {
    public static void main(String args[]) {
        long duration = 10000; // 10s
        int operation = 50; // 50%
        int division = 300; // 20分割
        int waittime = 30; // 30ms

        System.out.println("----------------------------------------------------");
        System.out.print("duration: " + duration + "ms");
        System.out.println("    operation: " + operation + "%");
        System.out.print("division: *" + division);
        System.out.println("        waittime: " + waittime + "ms");
        long[] result = Pmw.pulse(duration, operation, division, waittime);
        System.out.println("result: run, slp, div: " + result[0] + "ms, " + result[1] + "ms, *" + result[2]);
        System.out.println("result_duration: " + ((result[0]+result[1])*result[2]) + "ms");
        System.out.println("err:" + (((result[0]+result[1])*result[2])-duration) + "ms");
        System.out.println("----------------------------------------------------");
    }
}
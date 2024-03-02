package com.traindiorama.mortor;

import java.util.Arrays;

public class SteppingAcceleration {
    public static int[] acccel(int initialOperationPersentage, int lastOperationPersentage, long duration, int division) {
        int[] result = new int[]{};
        /*
         * duration = 全体
         * division = 分割の精度
         * initPsec = 初速
         * lastPsec = 終端
         */
        while (true) {
            
            if (duration < Arrays.stream(result).sum()) {
                break;
            }
        }
        return new int[]{};
    }
}

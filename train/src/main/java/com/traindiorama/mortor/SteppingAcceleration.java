package com.traindiorama.mortor;

import java.util.Arrays;

import org.checkerframework.checker.nullness.qual.Nullable;

public class SteppingAcceleration {
    public static int[] shift(int initialOperationPersentage, int lastOperationPersentage, long durationMilliseconds, @Nullable int division) {
        int[] result = new int[]{};
        /*
         * duration = 全体
         * division = 分割の精度
         * initPsec = 初速
         * lastPsec = 終端
        */

        /*
         * fig1 -> f(x) = (x-d)^(1/2)
         * desc: d=グラフの開始地点 x=変位(persentage)
         * fig2 -> g(x) = (1/h)x - (1/h)d
         * desc: d=グラフの開始地点 x=変位 h=傾き(小さいと上がりやすい)
        */
        while (true) {
            
            if (durationMilliseconds < Arrays.stream(result).sum()) {
                break;
            }
        }
        return new int[]{};
    }
}

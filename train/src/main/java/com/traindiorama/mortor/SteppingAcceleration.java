package com.traindiorama.mortor;

public class SteppingAcceleration {
    public static long[] shift(int initialOperationPersentage, int lastOperationPersentage, long durationMilliseconds, int division, boolean isAcceleration) {
        long[] result = new long[] {};
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
         * 
         * {@value isAcceleration}でxを負にするか決めた方がいい
         */
        long avgOP = Math.round((double) ((initialOperationPersentage + lastOperationPersentage) / 2));
        long divedDuration = Math.round((double) (durationMilliseconds / division));
        long divedDurationOped_on = Math.round((double) (divedDuration * (avgOP/100)));
        long divedDurationOped_off = divedDuration - divedDurationOped_on;

        result[0] = divedDurationOped_on;
        result[1] = divedDurationOped_off;

        //while (true) {
        //    if (durationMilliseconds < Arrays.stream(result).sum()) {
        //        break;
        //    }
        //}
        return result;
    }
}

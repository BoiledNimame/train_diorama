package com.traindiorama.mortor;

import java.io.PrintStream;

public class PWM {
    /**
     * パルス入力による稼働率制御の為のGPIO出力へ渡す数値構築用メソッド
     * この制御はPulse Width Modulationという(勉強用メモ) 以下はdoc
     * 
     * @param duration             {@code long } ms ミリ秒で指定する
     * @param operation_persentage {@code int} 稼働率, %で指定
     * @param max_divisions        {@code int} パルス波形の分割回数 下限は第4引数
     *                             {@code min_WaitTime} に依存するため目安程度
     * @param min_WaitTime         {@code int} ms ミリ秒で指定 目安は3~50ms程度
     *                             そのマシンが正確に計測できる最短のミリ秒を指定する
     * @return {runDuration[ms], sleepDuration[ms], repetitions[number of repeat]}
     * @throws IllegalArgumentException 第二引数 {@code operation_persentage}
     *                                  が101以上だと起こる
     */
    public static long[] pulse(long duration, int operation_persentage, int max_divisions, int min_WaitTime)
            throws IllegalArgumentException {
        if (100 < operation_persentage) {
            throw new IllegalArgumentException("operation_persentageが100%を超えて指定されているため処理を停止します.");
        }
        long runDurationLong = Math.round(duration * ((double) operation_persentage / 100));
        long slpDurationLong = duration - runDurationLong;
        long divided_runDuration = Math.round((double) runDurationLong / max_divisions);
        long divided_slpDuration = Math.round((double) slpDurationLong / max_divisions);
        long repeat = max_divisions;
        if (divided_runDuration < min_WaitTime || divided_slpDuration < min_WaitTime) {
            // 正確に実行できず実際のdurationが間延びしてしまうので, 分割数を減らす
            for (int i = max_divisions; 0 < i; i--) {
                divided_runDuration = Math.round((double) runDurationLong / i);
                divided_slpDuration = Math.round((double) slpDurationLong / i);
                if (min_WaitTime < divided_runDuration && min_WaitTime < divided_slpDuration) {
                    repeat = i;
                    break;
                }
            }
        }
        return new long[] { divided_runDuration, divided_slpDuration, repeat };
    }

    /**
     * Log付きメソッド.
     * @param ps
     * その他の情報は{@code long[] pulse()}を参照してください.
     * {@see com.traindiorama.mortor.Pmw}
     */
    public static long[] pulse(long duration, int operation, int division, int waittime, PrintStream ps) {
        ps.println(repeat(40, "-"));
        ps.print("duration: " + duration + "ms");
        ps.println(repeat(9 - String.valueOf(duration).length(), " ") + "operation: " + operation + "%");
        ps.print("division: *" + division);
        ps.println(repeat(11 - String.valueOf(division).length(), " ") + "waittime: " + waittime + "ms");
        long[] result = PWM.pulse(duration, operation, division, waittime);
        ps.println("result: run, slp, div: " + result[0] + "ms, " + result[1] + "ms, *" + result[2]);
        ps.println("result_duration: " + ((result[0] + result[1]) * result[2]) + "ms"
                + repeat(8 - String.valueOf((result[0] + result[1]) * result[2]).length(), " ") + "err:"
                + (((result[0] + result[1]) * result[2]) - duration) + "ms");
        ps.println(repeat(40, "-"));
        return result;
    }

    private static String repeat(int length, String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(str);
        }
        return result.toString();
    };
}

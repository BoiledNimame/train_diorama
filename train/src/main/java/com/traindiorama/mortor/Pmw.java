package com.traindiorama.mortor;

public class Pmw {
    /**
     * パルス入力による稼働率制御の為の稼働時間情報構築用メソッド
     * 
     * @param duration             {@code long } ms ミリ秒で指定する
     * @param operation_persentage {@code int} %で指定
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
            throw new IllegalArgumentException("operation_persentageが100%を超えたため処理を停止します.");
        }
        long runDurationLong = Math.round(duration * ((double) operation_persentage / 100));
        long slpDurationLong = duration - runDurationLong;
        long divided_runDuration = Math.round((double) runDurationLong / max_divisions);
        long divided_slpDuration = Math.round((double) slpDurationLong / max_divisions);
        long repeat = max_divisions;
        if (divided_runDuration < min_WaitTime || divided_slpDuration < min_WaitTime) {
            // 正確に実行できず実際のdurationが間延びしてしまうので, 分割数を減らす
            for (int i = max_divisions; i < 0; i--) {
                runDurationLong = Math.round((double) duration * (operation_persentage / 100));
                slpDurationLong = duration - runDurationLong;
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
}
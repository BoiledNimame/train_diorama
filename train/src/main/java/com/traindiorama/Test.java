package com.traindiorama;

import com.traindiorama.pulse.MortorData;

public class Test implements MortorData {
    public static void main(String args[]) {
        // pwmというのは角度を保たせる技術
        // よって, 外力による角度変更を防ぐために現在位置を指定するパルスを常に送らなければならない
        // そのため, 別スレを走らせてそれで常にGPIOをON-OFFするパルスを送らせなければならない

        // パルス幅 -> @see com.traindiorama.pulse.MortorData
    }
}

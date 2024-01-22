import RPi.GPIO as GPIO
import time

GPIO.setmode(GPIO.BCM)

mortorDriverOutput:int = 5
switchTrainWorkInput:int = 6

GPIO.setup(mortorDriverOutput, GPIO.OUT)
GPIO.setup(switchTrainWorkInput, GPIO.IN, pull_up_down=GPIO.PUD_HIGH)

# パルス出力によりモータの回転数を制御する
# GPIONum = GPIOの番号
# pulsetime = ミリ秒intで指定する
# duration = ミリ秒intで指定する
def PWM(GPIONum, pulsetime_millis, duration_millis):
    if duration_millis > pulsetime_millis:
        # pulsetime : seconds-> milliseconds
        pulsetime_millis = pulsetime_millis/1000
        duration_millis = duration_millis/1000
        waitTime_millis:float = duration_millis-pulsetime_millis
        for i in range(duration_millis):
            GPIO.output(GPIONum, GPIO.HIGH)
            time.sleep(pulsetime_millis)
            GPIO.output(GPIONum, GPIO.LOW)
            time.sleep(waitTime_millis)
    else:
        print("err: duration_is_too_short")

while True:
    if GPIO.input(switchTrainWorkInput):
        # 駅にいるか判断
        if GPIO.input(staion1_leadswitch|station2_leadswitch|station3_leadswitch):
            # 何秒で加速し切るかを表す
            kasokudo:int = 1000
            # 少しずつ変えていく, その回数を決める
            stepcount:int = 50

            for i in range(kasokudo):
                # パルスの速度を上げる間隔(ミリ秒): 0.5s
                steptime_milliseconds:int = 500
                # 稼働する時間(ミリ秒): 0.0000....
                # これにiを反映させる.
                pulse_milliseconds:int = i
            PWM(mortorDriverOutput, pulse_milliseconds, steptime_milliseconds)
    else:
    


# END
GPIO.cleanup()
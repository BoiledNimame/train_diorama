import RPi.GPIO as GPIO
import time

# GPIOの番号指定には2パターンある

    # 1.BCM-GPIOの数字で指定する
    GPIO.setmode(GPIO.BCM)

    # 2.BOARD-ピン番号で指定する
    GPIO.setmode(GPIO.BOARD)


# GPIOのピン毎にも設定が必要, GPIO(番号, 用途)で指定する

    number: int = 4

    # 1.出力
    GPIO.setup(number, GPIO.OUT)

    # 2.入力
    GPIO.setup(number, GPIO.IN)

    # リスト指定も可能
    numbers: list[int] = [channel, channel...]
    GPIO.setup(numbers, GPIO.OUT)


# 出力用途では電圧をかけることができる

    # 1.電圧をかける(3.3V)
    GPIO.output(channel, GPIO.HIGH)

    # 2.電圧をかけない
    GPIO.output(channel, GPIO.LOW)

    # 使用する引数は次に示すように等しい扱いを受ける
    GPIO.HIGH = 1 = True
    GPIO.LOW = 0 = False


# 入力用途では0=False/1=Trueを出力する

    # 変数へ実行時のピン入力を代入する
    imput_value: bool = GPIO.imput(channel)

    # しかし, setup時にpull_up_downでGPIO.PUD_UPかGPIO.PUD_DOWNを指定したかにより挙動が変わる(プルダウン抵抗.)
    GPIO.setup(number, GPIO.IN, pull_up_down=GPIO.PUD_UP) # 回路が繋がるとGPIOはHIGH, 切断されるとLOW
    GPIO.setup(number, GPIO.IN, pull_up_down=GPIO.PUD_DOWN) # 回路が繋がるとGPIOはLOW, 切断されるとHIGH

    # pull_up_downを指定しない場合はどちらの挙動も起きない.
    # プルダウン抵抗を設定しない場合過電流によりRaspberry piの故障を招く可能性がある


# GPIOの解放

    # GPIOの解放を行う
    GPIO.cleanup()

    # 特定ピンの解放もできる
    GPIO.cleanup(number)
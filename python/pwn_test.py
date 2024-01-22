import RPi.GPIO as GPIO
import threading
import time

#####################################################################
# 参照: https://qiita.com/montblanc18/items/05715730d99d450fd0d3
# scheduler(number interval, method f, bool wait)
# interval: sec
# method: work
# wait: can make next thread at running other thread
#####################################################################

def scheduler(interval, f, wait = True):
    base_time = time.time()
    next_time = 0
    while True:
        t = threading.Thread(target = f)
        t.start()
        if wait:
            t.join()
        next_time = ((base_time - time.time()) % interval) or interval
        time.sleep(next_time)


GPIO.setmode(GPIO.BCM)

mortor_driver_output_forword:int = 5
mortor_driver_output_reverse:int = 6
switch_train_work_input:int = 7

GPIO.setup(mortor_driver_output_forword, GPIO.OUT)
GPIO.setup(switch_train_work_input, GPIO.IN, pull_up_down=GPIO.PUD_HIGH)



# END
GPIO.cleanup()
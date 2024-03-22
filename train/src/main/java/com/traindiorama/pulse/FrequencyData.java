package com.traindiorama.pulse;

public interface FrequencyData {
    public String id = "mortor";
    public int MaxPulseRangeMicroseconds = 2500;
    public int MaxPulseRangeFrequency = 400;
    public int MinPulseRangeMicroseconds = 500;

    public int NeutralPulseMicroseconds = 1500;

    public int NeutralPeriodMicroseconds = 2000;
}

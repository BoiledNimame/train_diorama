package com.traindiorama.pulse;

public interface MortorData {
    public int MaxPulseRangeMicroseconds = 2500;
    public int MinPulseRangeMicroseconds = 500;

    public int NeutralPulseMicroseconds = 1500;

    public int NeutralPeriodMicroseconds = 2000;

    public int Angle_Degree_MaxRangePulse = 270;
    public int Angle_Degree_NeutralPulse = 135;
    public int Angle_Degree_MinRangePulse = 0;
}

package com.traindiorama.pulse;

public interface MortorData {
    public long MaxPulseRangeMicroseconds = 2500;
    public long MinPulseRangeMicroseconds = 500;

    public long NeutralPulseMicroseconds = 1500;

    public long NeutralPeriodMicroseconds = 2000;

    public short Angle_Degree_MaxRangePulse = 270;
    public short Angle_Degree_NeutralPulse = 135;
    public short Angle_Degree_MinRangePulse = 0;
}

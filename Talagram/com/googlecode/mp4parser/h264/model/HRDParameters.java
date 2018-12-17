package com.googlecode.mp4parser.h264.model;

import java.util.Arrays;

public class HRDParameters {
    public int bit_rate_scale;
    public int[] bit_rate_value_minus1;
    public boolean[] cbr_flag;
    public int cpb_cnt_minus1;
    public int cpb_removal_delay_length_minus1;
    public int cpb_size_scale;
    public int[] cpb_size_value_minus1;
    public int dpb_output_delay_length_minus1;
    public int initial_cpb_removal_delay_length_minus1;
    public int time_offset_length;

    public HRDParameters() {
        super();
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder("HRDParameters{cpb_cnt_minus1=");
        v0.append(this.cpb_cnt_minus1);
        v0.append(", bit_rate_scale=");
        v0.append(this.bit_rate_scale);
        v0.append(", cpb_size_scale=");
        v0.append(this.cpb_size_scale);
        v0.append(", bit_rate_value_minus1=");
        v0.append(Arrays.toString(this.bit_rate_value_minus1));
        v0.append(", cpb_size_value_minus1=");
        v0.append(Arrays.toString(this.cpb_size_value_minus1));
        v0.append(", cbr_flag=");
        v0.append(Arrays.toString(this.cbr_flag));
        v0.append(", initial_cpb_removal_delay_length_minus1=");
        v0.append(this.initial_cpb_removal_delay_length_minus1);
        v0.append(", cpb_removal_delay_length_minus1=");
        v0.append(this.cpb_removal_delay_length_minus1);
        v0.append(", dpb_output_delay_length_minus1=");
        v0.append(this.dpb_output_delay_length_minus1);
        v0.append(", time_offset_length=");
        v0.append(this.time_offset_length);
        v0.append('}');
        return v0.toString();
    }
}


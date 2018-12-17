package com.googlecode.mp4parser.authoring.tracks.h265;

import com.googlecode.mp4parser.h264.read.CAVLCReader;
import com.googlecode.mp4parser.util.ByteBufferByteChannel;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;

public class VideoParameterSet {
    ByteBuffer vps;
    int vps_parameter_set_id;

    public VideoParameterSet(ByteBuffer arg13) {
        super();
        this.vps = arg13;
        CAVLCReader v0 = new CAVLCReader(Channels.newInputStream(new ByteBufferByteChannel(arg13.position(0))));
        this.vps_parameter_set_id = v0.readU(4, "vps_parameter_set_id");
        v0.readU(2, "vps_reserved_three_2bits");
        int v1 = 6;
        v0.readU(v1, "vps_max_layers_minus1");
        int v13 = v0.readU(3, "vps_max_sub_layers_minus1");
        v0.readBool("vps_temporal_id_nesting_flag");
        v0.readU(16, "vps_reserved_0xffff_16bits");
        this.profile_tier_level(v13, v0);
        boolean v3 = v0.readBool("vps_sub_layer_ordering_info_present_flag");
        int v5 = v3 ? 1 : v13 + 1;
        int[] v5_1 = new int[v5];
        int v6 = v3 ? 1 : v13 + 1;
        int[] v6_1 = new int[v6];
        int v7 = v3 ? 1 : v13 + 1;
        int[] v7_1 = new int[v7];
        int v3_1;
        for(v3_1 = v3 ? 0 : v13; v3_1 <= v13; ++v3_1) {
            StringBuilder v8 = new StringBuilder("vps_max_dec_pic_buffering_minus1[");
            v8.append(v3_1);
            v8.append("]");
            v5_1[v3_1] = v0.readUE(v8.toString());
            v8 = new StringBuilder("vps_max_dec_pic_buffering_minus1[");
            v8.append(v3_1);
            v8.append("]");
            v6_1[v3_1] = v0.readUE(v8.toString());
            v8 = new StringBuilder("vps_max_dec_pic_buffering_minus1[");
            v8.append(v3_1);
            v8.append("]");
            v7_1[v3_1] = v0.readUE(v8.toString());
        }

        int v8_1 = v0.readU(v1, "vps_max_layer_id");
        int v9 = v0.readUE("vps_num_layer_sets_minus1");
        Object v10 = new boolean[v9][v8_1];
        int v11;
        for(v11 = 1; v11 <= v9; ++v11) {
            for(v1 = 0; v1 <= v8_1; ++v1) {
                Object v3_2 = v10[v11];
                StringBuilder v5_2 = new StringBuilder("layer_id_included_flag[");
                v5_2.append(v11);
                v5_2.append("][");
                v5_2.append(v1);
                v5_2.append("]");
                v3_2[v1] = v0.readBool(v5_2.toString());
            }
        }

        if(v0.readBool("vps_timing_info_present_flag")) {
            v0.readU(32, "vps_num_units_in_tick");
            v0.readU(32, "vps_time_scale");
            if(v0.readBool("vps_poc_proportional_to_timing_flag")) {
                v0.readUE("vps_num_ticks_poc_diff_one_minus1");
            }

            v1 = v0.readUE("vps_num_hrd_parameters");
            int[] v3_3 = new int[v1];
            boolean[] v5_3 = new boolean[v1];
            for(v6 = 0; v6 < v1; ++v6) {
                StringBuilder v7_2 = new StringBuilder("hrd_layer_set_idx[");
                v7_2.append(v6);
                v7_2.append("]");
                v3_3[v6] = v0.readUE(v7_2.toString());
                if(v6 > 0) {
                    v7_2 = new StringBuilder("cprms_present_flag[");
                    v7_2.append(v6);
                    v7_2.append("]");
                    v5_3[v6] = v0.readBool(v7_2.toString());
                }
                else {
                    v5_3[0] = true;
                }

                this.hrd_parameters(v5_3[v6], v13, v0);
            }
        }

        if(v0.readBool("vps_extension_flag")) {
            while(v0.moreRBSPData()) {
                v0.readBool("vps_extension_data_flag");
            }
        }

        v0.readTrailingBits();
    }

    private void hrd_parameters(boolean arg11, int arg12, CAVLCReader arg13) {
        boolean v2;
        boolean v1;
        int v0 = 0;
        if(arg11) {
            arg11 = arg13.readBool("nal_hrd_parameters_present_flag");
            v1 = arg13.readBool("vcl_hrd_parameters_present_flag");
            if(!arg11 && !v1) {
                goto label_38;
            }

            v2 = arg13.readBool("sub_pic_hrd_params_present_flag");
            int v3 = 5;
            if(v2) {
                arg13.readU(8, "tick_divisor_minus2");
                arg13.readU(v3, "du_cpb_removal_delay_increment_length_minus1");
                arg13.readBool("sub_pic_cpb_params_in_pic_timing_sei_flag");
                arg13.readU(v3, "dpb_output_delay_du_length_minus1");
            }

            int v5 = 4;
            arg13.readU(v5, "bit_rate_scale");
            arg13.readU(v5, "cpb_size_scale");
            if(v2) {
                arg13.readU(v5, "cpb_size_du_scale");
            }

            arg13.readU(v3, "initial_cpb_removal_delay_length_minus1");
            arg13.readU(v3, "au_cpb_removal_delay_length_minus1");
            arg13.readU(v3, "dpb_output_delay_length_minus1");
        }
        else {
            arg11 = false;
            v1 = false;
        label_38:
            v2 = false;
        }

        boolean[] v3_1 = new boolean[arg12];
        boolean[] v4 = new boolean[arg12];
        boolean[] v5_1 = new boolean[arg12];
        int[] v6 = new int[arg12];
        int[] v7 = new int[arg12];
        while(v0 <= arg12) {
            StringBuilder v8 = new StringBuilder("fixed_pic_rate_general_flag[");
            v8.append(v0);
            v8.append("]");
            v3_1[v0] = arg13.readBool(v8.toString());
            if(!v3_1[v0]) {
                v8 = new StringBuilder("fixed_pic_rate_within_cvs_flag[");
                v8.append(v0);
                v8.append("]");
                v4[v0] = arg13.readBool(v8.toString());
            }

            if(v4[v0]) {
                v8 = new StringBuilder("elemental_duration_in_tc_minus1[");
                v8.append(v0);
                v8.append("]");
                v7[v0] = arg13.readUE(v8.toString());
            }
            else {
                v8 = new StringBuilder("low_delay_hrd_flag[");
                v8.append(v0);
                v8.append("]");
                v5_1[v0] = arg13.readBool(v8.toString());
            }

            if(!v5_1[v0]) {
                v8 = new StringBuilder("cpb_cnt_minus1[");
                v8.append(v0);
                v8.append("]");
                v6[v0] = arg13.readUE(v8.toString());
            }

            if(arg11) {
                this.sub_layer_hrd_parameters(v0, v6[v0], v2, arg13);
            }

            if(v1) {
                this.sub_layer_hrd_parameters(v0, v6[v0], v2, arg13);
            }

            ++v0;
        }
    }

    public void profile_tier_level(int arg19, CAVLCReader arg20) {
        StringBuilder v0_1;
        int v0 = arg19;
        CAVLCReader v1 = arg20;
        int v3 = 2;
        v1.readU(v3, "general_profile_space ");
        v1.readBool("general_tier_flag");
        v1.readU(5, "general_profile_idc");
        int v2 = 32;
        boolean[] v5 = new boolean[v2];
        int v7 = 0;
        while(v7 < v2) {
            v0_1 = new StringBuilder("general_profile_compatibility_flag[");
            v0_1.append(v7);
            v0_1.append("]");
            v5[v7] = v1.readBool(v0_1.toString());
            ++v7;
            v0 = arg19;
            v2 = 32;
            v3 = 2;
        }

        v1.readBool("general_progressive_source_flag");
        v1.readBool("general_interlaced_source_flag");
        v1.readBool("general_non_packed_constraint_flag");
        v1.readBool("general_frame_only_constraint_flag");
        v1.readU(44, "general_reserved_zero_44bits");
        int v9 = 8;
        v1.readU(v9, "general_level_idc");
        boolean[] v10 = new boolean[v0];
        boolean[] v11 = new boolean[v0];
        int v5_1 = 0;
        while(v5_1 < v0) {
            v0_1 = new StringBuilder("sub_layer_profile_present_flag[");
            v0_1.append(v5_1);
            v0_1.append("]");
            v10[v5_1] = v1.readBool(v0_1.toString());
            v0_1 = new StringBuilder("sub_layer_level_present_flag[");
            v0_1.append(v5_1);
            v0_1.append("]");
            v11[v5_1] = v1.readBool(v0_1.toString());
            ++v5_1;
            v0 = arg19;
            v2 = 32;
            v3 = 2;
            v9 = 8;
        }

        if(v0 > 0) {
            for(v5_1 = v0; v5_1 < v9; ++v5_1) {
                v1.readU(v3, "reserved_zero_2bits");
            }
        }

        int[] v7_1 = new int[v0];
        boolean[] v12 = new boolean[v0];
        int[] v13 = new int[v0];
        Object v14 = new boolean[v0][v2];
        boolean[] v15 = new boolean[v0];
        v5 = new boolean[v0];
        boolean[] v6 = new boolean[v0];
        boolean[] v9_1 = new boolean[v0];
        int[] v8 = new int[v0];
        v2 = 0;
        while(v2 < v0) {
            if(v10[v2]) {
                StringBuilder v4 = new StringBuilder("sub_layer_profile_space[");
                v4.append(v2);
                v4.append("]");
                v7_1[v2] = v1.readU(2, v4.toString());
                StringBuilder v3_1 = new StringBuilder("sub_layer_tier_flag[");
                v3_1.append(v2);
                v3_1.append("]");
                v12[v2] = v1.readBool(v3_1.toString());
                v3_1 = new StringBuilder("sub_layer_profile_idc[");
                v3_1.append(v2);
                v3_1.append("]");
                v13[v2] = v1.readU(5, v3_1.toString());
                for(v3 = 0; v3 < 32; ++v3) {
                    Object v16 = v14[v2];
                    v4 = new StringBuilder("sub_layer_profile_compatibility_flag[");
                    v4.append(v2);
                    v4.append("][");
                    v4.append(v3);
                    v4.append("]");
                    v16[v3] = v1.readBool(v4.toString());
                }

                v3_1 = new StringBuilder("sub_layer_progressive_source_flag[");
                v3_1.append(v2);
                v3_1.append("]");
                v15[v2] = v1.readBool(v3_1.toString());
                v3_1 = new StringBuilder("sub_layer_interlaced_source_flag[");
                v3_1.append(v2);
                v3_1.append("]");
                v5[v2] = v1.readBool(v3_1.toString());
                v3_1 = new StringBuilder("sub_layer_non_packed_constraint_flag[");
                v3_1.append(v2);
                v3_1.append("]");
                v6[v2] = v1.readBool(v3_1.toString());
                v3_1 = new StringBuilder("sub_layer_frame_only_constraint_flag[");
                v3_1.append(v2);
                v3_1.append("]");
                v9_1[v2] = v1.readBool(v3_1.toString());
                v1.readNBit(44, "reserved");
            }

            if(v11[v2]) {
                v8[v2] = v1.readU(8, "sub_layer_level_idc");
            }

            ++v2;
            v0 = arg19;
        }
    }

    void sub_layer_hrd_parameters(int arg8, int arg9, boolean arg10, CAVLCReader arg11) {
        int[] v8 = new int[arg9];
        int[] v0 = new int[arg9];
        int[] v1 = new int[arg9];
        int[] v2 = new int[arg9];
        boolean[] v3 = new boolean[arg9];
        int v4;
        for(v4 = 0; v4 <= arg9; ++v4) {
            StringBuilder v5 = new StringBuilder("bit_rate_value_minus1[");
            v5.append(v4);
            v5.append("]");
            v8[v4] = arg11.readUE(v5.toString());
            v5 = new StringBuilder("cpb_size_value_minus1[");
            v5.append(v4);
            v5.append("]");
            v0[v4] = arg11.readUE(v5.toString());
            if(arg10) {
                v5 = new StringBuilder("cpb_size_du_value_minus1[");
                v5.append(v4);
                v5.append("]");
                v1[v4] = arg11.readUE(v5.toString());
                v5 = new StringBuilder("bit_rate_du_value_minus1[");
                v5.append(v4);
                v5.append("]");
                v2[v4] = arg11.readUE(v5.toString());
            }

            v5 = new StringBuilder("cbr_flag[");
            v5.append(v4);
            v5.append("]");
            v3[v4] = arg11.readBool(v5.toString());
        }
    }

    public ByteBuffer toByteBuffer() {
        return this.vps;
    }
}


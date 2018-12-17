package com.googlecode.mp4parser.authoring.tracks.h265;

import com.googlecode.mp4parser.h264.read.CAVLCReader;
import java.io.InputStream;

public class SequenceParameterSetRbsp {
    public SequenceParameterSetRbsp(InputStream arg9) {
        super();
        CAVLCReader v0 = new CAVLCReader(arg9);
        int v1 = 4;
        v0.readNBit(v1, "sps_video_parameter_set_id");
        int v9 = ((int)v0.readNBit(3, "sps_max_sub_layers_minus1"));
        v0.readBool("sps_temporal_id_nesting_flag");
        this.profile_tier_level(v9, v0);
        v0.readUE("sps_seq_parameter_set_id");
        if(v0.readUE("chroma_format_idc") == 3) {
            v0.read1Bit();
            v0.readUE("pic_width_in_luma_samples");
            v0.readUE("pic_width_in_luma_samples");
            if(v0.readBool("conformance_window_flag")) {
                v0.readUE("conf_win_left_offset");
                v0.readUE("conf_win_right_offset");
                v0.readUE("conf_win_top_offset");
                v0.readUE("conf_win_bottom_offset");
            }
        }

        v0.readUE("bit_depth_luma_minus8");
        v0.readUE("bit_depth_chroma_minus8");
        v0.readUE("log2_max_pic_order_cnt_lsb_minus4");
        boolean v2 = v0.readBool("sps_sub_layer_ordering_info_present_flag");
        int v3 = 0;
        int v4 = v2 ? 0 : v9;
        v4 = v9 - v4 + 1;
        int[] v5 = new int[v4];
        int[] v6 = new int[v4];
        int[] v4_1 = new int[v4];
        if(v2) {
        }
        else {
            v3 = v9;
        }

        while(v3 <= v9) {
            StringBuilder v2_1 = new StringBuilder("sps_max_dec_pic_buffering_minus1[");
            v2_1.append(v3);
            v2_1.append("]");
            v5[v3] = v0.readUE(v2_1.toString());
            v2_1 = new StringBuilder("sps_max_num_reorder_pics[");
            v2_1.append(v3);
            v2_1.append("]");
            v6[v3] = v0.readUE(v2_1.toString());
            v2_1 = new StringBuilder("sps_max_latency_increase_plus1[");
            v2_1.append(v3);
            v2_1.append("]");
            v4_1[v3] = v0.readUE(v2_1.toString());
            ++v3;
        }

        v0.readUE("log2_min_luma_coding_block_size_minus3");
        v0.readUE("log2_diff_max_min_luma_coding_block_size");
        v0.readUE("log2_min_transform_block_size_minus2");
        v0.readUE("log2_diff_max_min_transform_block_size");
        v0.readUE("max_transform_hierarchy_depth_inter");
        v0.readUE("max_transform_hierarchy_depth_intra");
        if((v0.readBool("scaling_list_enabled_flag")) && (v0.readBool("sps_scaling_list_data_present_flag"))) {
            this.scaling_list_data(v0);
        }

        v0.readBool("amp_enabled_flag");
        v0.readBool("sample_adaptive_offset_enabled_flag");
        if(v0.readBool("pcm_enabled_flag")) {
            v0.readNBit(v1, "pcm_sample_bit_depth_luma_minus1");
            v0.readNBit(v1, "pcm_sample_bit_depth_chroma_minus1");
            v0.readUE("log2_min_pcm_luma_coding_block_size_minus3");
        }
    }

    private void profile_tier_level(int arg21, CAVLCReader arg22) {
        StringBuilder v0_1;
        int v0 = arg21;
        CAVLCReader v1 = arg22;
        int v3 = 2;
        v1.readU(v3, "general_profile_space");
        v1.readBool("general_tier_flag");
        v1.readU(5, "general_profile_idc");
        int v2 = 32;
        boolean[] v5 = new boolean[v2];
        int v7 = 0;
        while(v7 < v2) {
            v5[v7] = arg22.readBool();
            ++v7;
            v0 = arg21;
            v2 = 32;
            v3 = 2;
        }

        v1.readBool("general_progressive_source_flag");
        v1.readBool("general_interlaced_source_flag");
        v1.readBool("general_non_packed_constraint_flag");
        v1.readBool("general_frame_only_constraint_flag");
        v1.readNBit(44, "general_reserved_zero_44bits");
        arg22.readByte();
        boolean[] v9 = new boolean[v0];
        boolean[] v10 = new boolean[v0];
        int v5_1 = 0;
        while(v5_1 < v0) {
            v0_1 = new StringBuilder("sub_layer_profile_present_flag[");
            v0_1.append(v5_1);
            v0_1.append("]");
            v9[v5_1] = v1.readBool(v0_1.toString());
            v0_1 = new StringBuilder("sub_layer_level_present_flag[");
            v0_1.append(v5_1);
            v0_1.append("]");
            v10[v5_1] = v1.readBool(v0_1.toString());
            ++v5_1;
            v0 = arg21;
            v2 = 32;
            v3 = 2;
        }

        v7 = 8;
        if(v0 > 0) {
            int[] v5_2 = new int[v7];
            int v11;
            for(v11 = v0; v11 < v7; ++v11) {
                StringBuilder v12 = new StringBuilder("reserved_zero_2bits[");
                v12.append(v11);
                v12.append("]");
                v5_2[v11] = v1.readU(v3, v12.toString());
            }
        }

        int[] v11_1 = new int[v0];
        boolean[] v12_1 = new boolean[v0];
        int[] v13 = new int[v0];
        Object v14 = new boolean[v0][v2];
        boolean[] v15 = new boolean[v0];
        v5 = new boolean[v0];
        boolean[] v6 = new boolean[v0];
        boolean[] v7_1 = new boolean[v0];
        long[] v8 = new long[v0];
        int[] v2_1 = new int[v0];
        int v4 = 0;
        while(v4 < v0) {
            if(v9[v4]) {
                StringBuilder v3_1 = new StringBuilder("sub_layer_profile_space[");
                v3_1.append(v4);
                v3_1.append("]");
                v11_1[v4] = v1.readU(2, v3_1.toString());
                v0_1 = new StringBuilder("sub_layer_tier_flag[");
                v0_1.append(v4);
                v0_1.append("]");
                v12_1[v4] = v1.readBool(v0_1.toString());
                v0_1 = new StringBuilder("sub_layer_profile_idc[");
                v0_1.append(v4);
                v0_1.append("]");
                v13[v4] = v1.readU(5, v0_1.toString());
                v0 = 0;
                while(v0 < 32) {
                    Object v16 = v14[v4];
                    v3_1 = new StringBuilder("sub_layer_profile_compatibility_flag[");
                    v3_1.append(v4);
                    v3_1.append("][");
                    v3_1.append(v0);
                    v3_1.append("]");
                    v16[v0] = v1.readBool(v3_1.toString());
                    ++v0;
                    v5 = v5;
                }

                v0_1 = new StringBuilder("sub_layer_progressive_source_flag[");
                v0_1.append(v4);
                v0_1.append("]");
                v15[v4] = v1.readBool(v0_1.toString());
                v0_1 = new StringBuilder("sub_layer_interlaced_source_flag[");
                v0_1.append(v4);
                v0_1.append("]");
                v5[v4] = v1.readBool(v0_1.toString());
                v0_1 = new StringBuilder("sub_layer_non_packed_constraint_flag[");
                v0_1.append(v4);
                v0_1.append("]");
                v6[v4] = v1.readBool(v0_1.toString());
                v0_1 = new StringBuilder("sub_layer_frame_only_constraint_flag[");
                v0_1.append(v4);
                v0_1.append("]");
                v7_1[v4] = v1.readBool(v0_1.toString());
                v8[v4] = v1.readNBit(44);
            }

            boolean[] v19 = v5;
            if(v10[v4]) {
                v0_1 = new StringBuilder("sub_layer_level_idc[");
                v0_1.append(v4);
                v0_1.append("]");
                v2_1[v4] = v1.readU(8, v0_1.toString());
            }

            ++v4;
            v5 = v19;
            v0 = arg21;
        }
    }

    private void scaling_list_data(CAVLCReader arg17) {
        int v12;
        int v11;
        int v10;
        int v9;
        CAVLCReader v0 = arg17;
        int v1 = 4;
        boolean[][] v2 = new boolean[v1][];
        int[][] v3 = new int[v1][];
        int[][] v5 = new int[2][];
        int[][][] v6 = new int[v1][][];
        int v8 = 0;
        while(true) {
        label_9:
            if(v8 >= v1) {
                return;
            }

            v9 = 0;
            while(true) {
            label_12:
                v10 = 6;
                v11 = 3;
                v12 = v8 == v11 ? 2 : 6;
                if(v9 < v12) {
                    break;
                }

                ++v8;
                goto label_9;
            }
        }

        v12 = v8 == v11 ? 2 : 6;
        v2[v8] = new boolean[v12];
        v12 = v8 == v11 ? 2 : 6;
        v3[v8] = new int[v12];
        if(v8 == v11) {
            v10 = 2;
        }

        v6[v8] = new int[v10][];
        v2[v8][v9] = arg17.readBool();
        if(!v2[v8][v9]) {
            int[] v10_1 = v3[v8];
            StringBuilder v11_1 = new StringBuilder("scaling_list_pred_matrix_id_delta[");
            v11_1.append(v8);
            v11_1.append("][");
            v11_1.append(v9);
            v11_1.append("]");
            v10_1[v9] = v0.readUE(v11_1.toString());
        }
        else {
            v10 = Math.min(64, 1 << (v8 << 1) + v1);
            v11 = 8;
            if(v8 > 1) {
                v12 = v8 - 2;
                int[] v13 = v5[v12];
                StringBuilder v14 = new StringBuilder("scaling_list_dc_coef_minus8[");
                v14.append(v8);
                v14.append("- 2][");
                v14.append(v9);
                v14.append("]");
                v13[v9] = v0.readSE(v14.toString());
                v11 += v5[v12][v9];
            }

            v6[v8][v9] = new int[v10];
            v12 = v11;
            for(v11 = 0; v11 < v10; ++v11) {
                v12 = (v12 + v0.readSE("scaling_list_delta_coef ") + 256) % 256;
                v6[v8][v9][v11] = v12;
            }
        }

        ++v9;
        goto label_12;
    }
}


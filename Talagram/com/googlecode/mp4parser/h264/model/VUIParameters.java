package com.googlecode.mp4parser.h264.model;

public class VUIParameters {
    public class BitstreamRestriction {
        public int log2_max_mv_length_horizontal;
        public int log2_max_mv_length_vertical;
        public int max_bits_per_mb_denom;
        public int max_bytes_per_pic_denom;
        public int max_dec_frame_buffering;
        public boolean motion_vectors_over_pic_boundaries_flag;
        public int num_reorder_frames;

        public BitstreamRestriction() {
            super();
        }

        public String toString() {
            StringBuilder v0 = new StringBuilder("BitstreamRestriction{");
            v0.append("motion_vectors_over_pic_boundaries_flag=");
            v0.append(this.motion_vectors_over_pic_boundaries_flag);
            v0.append(", max_bytes_per_pic_denom=");
            v0.append(this.max_bytes_per_pic_denom);
            v0.append(", max_bits_per_mb_denom=");
            v0.append(this.max_bits_per_mb_denom);
            v0.append(", log2_max_mv_length_horizontal=");
            v0.append(this.log2_max_mv_length_horizontal);
            v0.append(", log2_max_mv_length_vertical=");
            v0.append(this.log2_max_mv_length_vertical);
            v0.append(", num_reorder_frames=");
            v0.append(this.num_reorder_frames);
            v0.append(", max_dec_frame_buffering=");
            v0.append(this.max_dec_frame_buffering);
            v0.append('}');
            return v0.toString();
        }
    }

    public AspectRatio aspect_ratio;
    public boolean aspect_ratio_info_present_flag;
    public BitstreamRestriction bitstreamRestriction;
    public boolean chroma_loc_info_present_flag;
    public int chroma_sample_loc_type_bottom_field;
    public int chroma_sample_loc_type_top_field;
    public boolean colour_description_present_flag;
    public int colour_primaries;
    public boolean fixed_frame_rate_flag;
    public boolean low_delay_hrd_flag;
    public int matrix_coefficients;
    public HRDParameters nalHRDParams;
    public int num_units_in_tick;
    public boolean overscan_appropriate_flag;
    public boolean overscan_info_present_flag;
    public boolean pic_struct_present_flag;
    public int sar_height;
    public int sar_width;
    public int time_scale;
    public boolean timing_info_present_flag;
    public int transfer_characteristics;
    public HRDParameters vclHRDParams;
    public int video_format;
    public boolean video_full_range_flag;
    public boolean video_signal_type_present_flag;

    public VUIParameters() {
        super();
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder("VUIParameters{\naspect_ratio_info_present_flag=");
        v0.append(this.aspect_ratio_info_present_flag);
        v0.append("\n");
        v0.append(", sar_width=");
        v0.append(this.sar_width);
        v0.append("\n");
        v0.append(", sar_height=");
        v0.append(this.sar_height);
        v0.append("\n");
        v0.append(", overscan_info_present_flag=");
        v0.append(this.overscan_info_present_flag);
        v0.append("\n");
        v0.append(", overscan_appropriate_flag=");
        v0.append(this.overscan_appropriate_flag);
        v0.append("\n");
        v0.append(", video_signal_type_present_flag=");
        v0.append(this.video_signal_type_present_flag);
        v0.append("\n");
        v0.append(", video_format=");
        v0.append(this.video_format);
        v0.append("\n");
        v0.append(", video_full_range_flag=");
        v0.append(this.video_full_range_flag);
        v0.append("\n");
        v0.append(", colour_description_present_flag=");
        v0.append(this.colour_description_present_flag);
        v0.append("\n");
        v0.append(", colour_primaries=");
        v0.append(this.colour_primaries);
        v0.append("\n");
        v0.append(", transfer_characteristics=");
        v0.append(this.transfer_characteristics);
        v0.append("\n");
        v0.append(", matrix_coefficients=");
        v0.append(this.matrix_coefficients);
        v0.append("\n");
        v0.append(", chroma_loc_info_present_flag=");
        v0.append(this.chroma_loc_info_present_flag);
        v0.append("\n");
        v0.append(", chroma_sample_loc_type_top_field=");
        v0.append(this.chroma_sample_loc_type_top_field);
        v0.append("\n");
        v0.append(", chroma_sample_loc_type_bottom_field=");
        v0.append(this.chroma_sample_loc_type_bottom_field);
        v0.append("\n");
        v0.append(", timing_info_present_flag=");
        v0.append(this.timing_info_present_flag);
        v0.append("\n");
        v0.append(", num_units_in_tick=");
        v0.append(this.num_units_in_tick);
        v0.append("\n");
        v0.append(", time_scale=");
        v0.append(this.time_scale);
        v0.append("\n");
        v0.append(", fixed_frame_rate_flag=");
        v0.append(this.fixed_frame_rate_flag);
        v0.append("\n");
        v0.append(", low_delay_hrd_flag=");
        v0.append(this.low_delay_hrd_flag);
        v0.append("\n");
        v0.append(", pic_struct_present_flag=");
        v0.append(this.pic_struct_present_flag);
        v0.append("\n");
        v0.append(", nalHRDParams=");
        v0.append(this.nalHRDParams);
        v0.append("\n");
        v0.append(", vclHRDParams=");
        v0.append(this.vclHRDParams);
        v0.append("\n");
        v0.append(", bitstreamRestriction=");
        v0.append(this.bitstreamRestriction);
        v0.append("\n");
        v0.append(", aspect_ratio=");
        v0.append(this.aspect_ratio);
        v0.append("\n");
        v0.append('}');
        return v0.toString();
    }
}


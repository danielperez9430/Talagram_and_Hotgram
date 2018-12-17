package com.googlecode.mp4parser.authoring.tracks;

import com.coremedia.iso.IsoTypeReader;
import com.googlecode.mp4parser.DataSource;
import com.googlecode.mp4parser.FileDataSourceImpl;
import com.googlecode.mp4parser.authoring.Sample;
import com.googlecode.mp4parser.authoring.SampleImpl;
import com.googlecode.mp4parser.h264.read.CAVLCReader;
import com.googlecode.mp4parser.util.ByteBufferByteChannel;
import com.mp4parser.iso14496.part15.b$a;
import com.mp4parser.iso14496.part15.b;
import java.io.EOFException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class H265TrackImplOld {
    class LookAhead {
        ByteBuffer buffer;
        long bufferStartPos;
        DataSource dataSource;
        int inBufferPos;
        long start;

        LookAhead(H265TrackImplOld arg3, DataSource arg4) {
            H265TrackImplOld.this = arg3;
            super();
            this.bufferStartPos = 0;
            this.inBufferPos = 0;
            this.dataSource = arg4;
            this.fillBuffer();
        }

        void discardByte() {
            ++this.inBufferPos;
        }

        void discardNext3AndMarkStart() {
            this.inBufferPos += 3;
            this.start = this.bufferStartPos + (((long)this.inBufferPos));
        }

        public void fillBuffer() {
            this.buffer = this.dataSource.map(this.bufferStartPos, Math.min(this.dataSource.size() - this.bufferStartPos, 1048576));
        }

        public ByteBuffer getNal() {
            if(this.start >= this.bufferStartPos) {
                this.buffer.position(((int)(this.start - this.bufferStartPos)));
                ByteBuffer v0 = this.buffer.slice();
                ((Buffer)v0).limit(((int)((((long)this.inBufferPos)) - (this.start - this.bufferStartPos))));
                return v0;
            }

            throw new RuntimeException("damn! NAL exceeds buffer");
        }

        boolean nextThreeEquals000or001orEof() {
            if(this.buffer.limit() - this.inBufferPos >= 3) {
                if(this.buffer.get(this.inBufferPos) == 0 && this.buffer.get(this.inBufferPos + 1) == 0 && (this.buffer.get(this.inBufferPos + 2) == 0 || this.buffer.get(this.inBufferPos + 2) == 1)) {
                    return 1;
                }

                return 0;
            }

            if(this.bufferStartPos + (((long)this.inBufferPos)) + 3 > this.dataSource.size()) {
                if(this.bufferStartPos + (((long)this.inBufferPos)) == this.dataSource.size()) {
                    return 1;
                }

                return 0;
            }

            this.bufferStartPos = this.start;
            this.inBufferPos = 0;
            this.fillBuffer();
            return this.nextThreeEquals000or001orEof();
        }

        boolean nextThreeEquals001() {
            if(this.buffer.limit() - this.inBufferPos >= 3) {
                if(this.buffer.get(this.inBufferPos) == 0 && this.buffer.get(this.inBufferPos + 1) == 0 && this.buffer.get(this.inBufferPos + 2) == 1) {
                    return 1;
                }

                return 0;
            }

            if(this.bufferStartPos + (((long)this.inBufferPos)) == this.dataSource.size()) {
                throw new EOFException();
            }

            throw new RuntimeException("buffer repositioning require");
        }
    }

    public class NalUnitHeader {
        int forbiddenZeroFlag;
        int nalUnitType;
        int nuhLayerId;
        int nuhTemporalIdPlusOne;

        public NalUnitHeader() {
            super();
        }
    }

    public enum PARSE_STATE {
        public static final enum PARSE_STATE AUD_SEI_SLICE;
        public static final enum PARSE_STATE SEI_SLICE;
        public static final enum PARSE_STATE SLICE_OES_EOB;

        static {
            PARSE_STATE.AUD_SEI_SLICE = new PARSE_STATE("AUD_SEI_SLICE", 0);
            PARSE_STATE.SEI_SLICE = new PARSE_STATE("SEI_SLICE", 1);
            PARSE_STATE.SLICE_OES_EOB = new PARSE_STATE("SLICE_OES_EOB", 2);
            PARSE_STATE.ENUM$VALUES = new PARSE_STATE[]{PARSE_STATE.AUD_SEI_SLICE, PARSE_STATE.SEI_SLICE, PARSE_STATE.SLICE_OES_EOB};
        }

        private PARSE_STATE(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static PARSE_STATE valueOf(String arg1) {
            return Enum.valueOf(PARSE_STATE.class, arg1);
        }

        public static PARSE_STATE[] values() {
            PARSE_STATE[] v0 = PARSE_STATE.ENUM$VALUES;
            int v1 = v0.length;
            PARSE_STATE[] v2 = new PARSE_STATE[v1];
            System.arraycopy(v0, 0, v2, 0, v1);
            return v2;
        }
    }

    public static final int AUD_NUT = 35;
    private static final int BLA_N_LP = 18;
    private static final int BLA_W_LP = 16;
    private static final int BLA_W_RADL = 17;
    private static final long BUFFER = 1048576;
    private static final int CRA_NUT = 21;
    private static final int IDR_N_LP = 20;
    private static final int IDR_W_RADL = 19;
    public static final int PPS_NUT = 34;
    public static final int PREFIX_SEI_NUT = 39;
    private static final int RADL_N = 6;
    private static final int RADL_R = 7;
    private static final int RASL_N = 8;
    private static final int RASL_R = 9;
    public static final int RSV_NVCL41 = 41;
    public static final int RSV_NVCL42 = 42;
    public static final int RSV_NVCL43 = 43;
    public static final int RSV_NVCL44 = 44;
    public static final int SPS_NUT = 33;
    private static final int STSA_N = 4;
    private static final int STSA_R = 5;
    private static final int TRAIL_N = 0;
    private static final int TRAIL_R = 1;
    private static final int TSA_N = 2;
    private static final int TSA_R = 3;
    public static final int UNSPEC48 = 48;
    public static final int UNSPEC49 = 49;
    public static final int UNSPEC50 = 50;
    public static final int UNSPEC51 = 51;
    public static final int UNSPEC52 = 52;
    public static final int UNSPEC53 = 53;
    public static final int UNSPEC54 = 54;
    public static final int UNSPEC55 = 55;
    public static final int VPS_NUT = 32;
    LinkedHashMap pictureParamterSets;
    List samples;
    LinkedHashMap sequenceParamterSets;
    List syncSamples;
    LinkedHashMap videoParamterSets;

    public H265TrackImplOld(DataSource arg17) {
        H265TrackImplOld v0 = this;
        super();
        v0.videoParamterSets = new LinkedHashMap();
        v0.sequenceParamterSets = new LinkedHashMap();
        v0.pictureParamterSets = new LinkedHashMap();
        v0.syncSamples = new ArrayList();
        v0.samples = new ArrayList();
        LookAhead v1 = new LookAhead(v0, arg17);
        ArrayList v2 = new ArrayList();
        long v3 = 1;
        long v6 = v3;
        int v8 = 0;
        while(true) {
            ByteBuffer v9 = v0.findNextNal(v1);
            if(v9 == null) {
                break;
            }

            NalUnitHeader v10 = v0.getNalUnitHeader(v9);
            switch(v10.nalUnitType) {
                case 32: {
                    goto label_45;
                }
                case 33: {
                    goto label_43;
                }
                case 34: {
                    goto label_41;
                }
            }

            goto label_48;
        label_41:
            LinkedHashMap v11 = v0.pictureParamterSets;
            goto label_46;
        label_43:
            v11 = v0.sequenceParamterSets;
            goto label_46;
        label_45:
            v11 = v0.videoParamterSets;
        label_46:
            v11.put(Long.valueOf(v6), v9);
        label_48:
            if(v10.nalUnitType < 32) {
                v8 = v10.nalUnitType;
            }

            if((v0.isFirstOfAU(v10.nalUnitType, v9, ((List)v2))) && !((List)v2).isEmpty()) {
                System.err.println("##########################");
                Iterator v10_1 = ((List)v2).iterator();
                while(v10_1.hasNext()) {
                    Object v11_1 = v10_1.next();
                    NalUnitHeader v12 = v0.getNalUnitHeader(((ByteBuffer)v11_1));
                    System.err.println(String.format("type: %3d - layer: %3d - tempId: %3d - size: %3d", Integer.valueOf(v12.nalUnitType), Integer.valueOf(v12.nuhLayerId), Integer.valueOf(v12.nuhTemporalIdPlusOne), Integer.valueOf(((ByteBuffer)v11_1).limit())));
                    v3 = 1;
                }

                System.err.println("                          ##########################");
                v0.samples.add(v0.createSample(((List)v2)));
                ((List)v2).clear();
                v6 += v3;
            }

            ((List)v2).add(v9);
            if(v8 >= 16 && v8 <= 21) {
                v0.syncSamples.add(Long.valueOf(v6));
            }

            v3 = 1;
        }

        System.err.println("");
        b v1_1 = new b();
        v1_1.a(this.getArrays());
        v1_1.a(0);
    }

    protected Sample createSample(List arg7) {
        int v1 = 4;
        byte[] v0 = new byte[arg7.size() * 4];
        ByteBuffer v2 = ByteBuffer.wrap(v0);
        Iterator v3 = arg7.iterator();
        while(v3.hasNext()) {
            v2.putInt(v3.next().remaining());
        }

        ByteBuffer[] v4 = new ByteBuffer[arg7.size() * 2];
        int v2_1;
        for(v2_1 = 0; v2_1 < arg7.size(); ++v2_1) {
            int v3_1 = v2_1 * 2;
            v4[v3_1] = ByteBuffer.wrap(v0, v2_1 * 4, v1);
            v4[v3_1 + 1] = arg7.get(v2_1);
        }

        return new SampleImpl(v4);
    }

    private ByteBuffer findNextNal(LookAhead arg2) {
        try {
            while(!arg2.nextThreeEquals001()) {
                arg2.discardByte();
            }

            arg2.discardNext3AndMarkStart();
            while(!arg2.nextThreeEquals000or001orEof()) {
                arg2.discardByte();
            }

            return arg2.getNal();
        }
        catch(EOFException ) {
            return null;
        }
    }

    private List getArrays() {
        a v0 = new a();
        v0.a = true;
        v0.c = 32;
        v0.d = new ArrayList();
        Iterator v2 = this.videoParamterSets.values().iterator();
        while(v2.hasNext()) {
            Object v3 = v2.next();
            byte[] v5 = new byte[((ByteBuffer)v3).limit()];
            ((ByteBuffer)v3).position(0);
            ((ByteBuffer)v3).get(v5);
            v0.d.add(v5);
        }

        a v3_1 = new a();
        v3_1.a = true;
        int v5_1 = 33;
        v3_1.c = v5_1;
        v3_1.d = new ArrayList();
        Iterator v6 = this.sequenceParamterSets.values().iterator();
        while(v6.hasNext()) {
            Object v2_1 = v6.next();
            byte[] v7 = new byte[((ByteBuffer)v2_1).limit()];
            ((ByteBuffer)v2_1).position(0);
            ((ByteBuffer)v2_1).get(v7);
            v3_1.d.add(v7);
        }

        a v2_2 = new a();
        v2_2.a = true;
        v2_2.c = v5_1;
        v2_2.d = new ArrayList();
        Iterator v7_1 = this.pictureParamterSets.values().iterator();
        while(v7_1.hasNext()) {
            Object v5_2 = v7_1.next();
            byte[] v6_1 = new byte[((ByteBuffer)v5_2).limit()];
            ((ByteBuffer)v5_2).position(0);
            ((ByteBuffer)v5_2).get(v6_1);
            v2_2.d.add(v6_1);
        }

        return Arrays.asList(new a[]{v0, v3_1, v2_2});
    }

    public int getFrameRate(ByteBuffer arg13) {
        CAVLCReader v0 = new CAVLCReader(Channels.newInputStream(new ByteBufferByteChannel(arg13.position(0))));
        v0.readU(4, "vps_parameter_set_id");
        v0.readU(2, "vps_reserved_three_2bits");
        int v1 = 6;
        v0.readU(v1, "vps_max_layers_minus1");
        int v13 = v0.readU(3, "vps_max_sub_layers_minus1");
        v0.readBool("vps_temporal_id_nesting_flag");
        v0.readU(16, "vps_reserved_0xffff_16bits");
        this.profile_tier_level(v13, v0);
        boolean v3 = v0.readBool("vps_sub_layer_ordering_info_present_flag");
        int v4 = v3 ? 0 : v13;
        int[] v4_1 = new int[v4];
        int v5 = v3 ? 0 : v13;
        int[] v5_1 = new int[v5];
        int v6 = v3 ? 0 : v13;
        int[] v6_1 = new int[v6];
        int v3_1;
        for(v3_1 = v3 ? 0 : v13; v3_1 <= v13; ++v3_1) {
            StringBuilder v7 = new StringBuilder("vps_max_dec_pic_buffering_minus1[");
            v7.append(v3_1);
            v7.append("]");
            v4_1[v3_1] = v0.readUE(v7.toString());
            v7 = new StringBuilder("vps_max_dec_pic_buffering_minus1[");
            v7.append(v3_1);
            v7.append("]");
            v5_1[v3_1] = v0.readUE(v7.toString());
            v7 = new StringBuilder("vps_max_dec_pic_buffering_minus1[");
            v7.append(v3_1);
            v7.append("]");
            v6_1[v3_1] = v0.readUE(v7.toString());
        }

        int v7_1 = v0.readU(v1, "vps_max_layer_id");
        int v8 = v0.readUE("vps_num_layer_sets_minus1");
        Object v9 = new boolean[v8][v7_1];
        int v11;
        for(v11 = 1; v11 <= v8; ++v11) {
            for(v1 = 0; v1 <= v7_1; ++v1) {
                Object v3_2 = v9[v11];
                StringBuilder v4_2 = new StringBuilder("layer_id_included_flag[");
                v4_2.append(v11);
                v4_2.append("][");
                v4_2.append(v1);
                v4_2.append("]");
                v3_2[v1] = v0.readBool(v4_2.toString());
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
            boolean[] v4_3 = new boolean[v1];
            for(v5 = 0; v5 < v1; ++v5) {
                StringBuilder v6_2 = new StringBuilder("hrd_layer_set_idx[");
                v6_2.append(v5);
                v6_2.append("]");
                v3_3[v5] = v0.readUE(v6_2.toString());
                if(v5 > 0) {
                    v6_2 = new StringBuilder("cprms_present_flag[");
                    v6_2.append(v5);
                    v6_2.append("]");
                    v4_3[v5] = v0.readBool(v6_2.toString());
                }
                else {
                    v4_3[0] = true;
                }

                this.hrd_parameters(v4_3[v5], v13, v0);
            }
        }

        if(v0.readBool("vps_extension_flag")) {
            while(v0.moreRBSPData()) {
                v0.readBool("vps_extension_data_flag");
            }
        }

        v0.readTrailingBits();
        return 0;
    }

    public NalUnitHeader getNalUnitHeader(ByteBuffer arg3) {
        arg3.position(0);
        int v3 = IsoTypeReader.readUInt16(arg3);
        NalUnitHeader v0 = new NalUnitHeader();
        v0.forbiddenZeroFlag = (32768 & v3) >> 15;
        v0.nalUnitType = (v3 & 32256) >> 9;
        v0.nuhLayerId = (v3 & 504) >> 3;
        v0.nuhTemporalIdPlusOne = v3 & 7;
        return v0;
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

    boolean isFirstOfAU(int arg4, ByteBuffer arg5, List arg6) {
        if(arg6.isEmpty()) {
            return 1;
        }

        int v6 = this.getNalUnitHeader(arg6.get(arg6.size() - 1)).nalUnitType <= 31 ? 1 : 0;
        switch(arg4) {
            case 32: 
            case 33: 
            case 34: 
            case 35: 
            case 39: 
            case 41: 
            case 42: 
            case 43: 
            case 44: 
            case 48: 
            case 49: 
            case 50: 
            case 51: 
            case 52: 
            case 53: 
            case 54: 
            case 55: {
                if(v6 != 0) {
                    return 1;
                }

                break;
            }
            default: {
                break;
            }
        }

        switch(arg4) {
            case 0: 
            case 1: 
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: 
            case 9: {
                break;
            }
            default: {
                switch(arg4) {
                    case 16: 
                    case 17: 
                    case 18: 
                    case 19: 
                    case 20: 
                    case 21: {
                        goto label_22;
                    }
                    default: {
                        return 0;
                    }
                }
            }
        }

    label_22:
        byte[] v4 = new byte[50];
        arg5.position(0);
        arg5.get(v4);
        arg5.position(2);
        arg4 = IsoTypeReader.readUInt8(arg5);
        if(v6 != 0 && (arg4 & 128) > 0) {
            return 1;
        }

        return 0;
    }

    public static void main(String[] arg2) {
        new H265TrackImplOld(new FileDataSourceImpl("c:\\content\\test-UHD-HEVC_01_FMV_Med_track1.hvc"));
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
}


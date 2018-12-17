package com.google.android.exoplayer2.text.cea;

import android.text.Layout$Alignment;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.SubtitleInputBuffer;
import com.google.android.exoplayer2.text.SubtitleOutputBuffer;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.List;

public final class Cea608Decoder extends CeaDecoder {
    class CueBuilder {
        class CueStyle {
            public int start;
            public final int style;
            public final boolean underline;

            public CueStyle(int arg1, boolean arg2, int arg3) {
                super();
                this.style = arg1;
                this.underline = arg2;
                this.start = arg3;
            }
        }

        private static final int BASE_ROW = 15;
        private static final int SCREEN_CHARWIDTH = 32;
        private int captionMode;
        private int captionRowCount;
        private final StringBuilder captionStringBuilder;
        private final List cueStyles;
        private int indent;
        private final List rolledUpCaptions;
        private int row;
        private int tabOffset;

        public CueBuilder(int arg2, int arg3) {
            super();
            this.cueStyles = new ArrayList();
            this.rolledUpCaptions = new ArrayList();
            this.captionStringBuilder = new StringBuilder();
            this.reset(arg2);
            this.setCaptionRowCount(arg3);
        }

        public void append(char arg2) {
            this.captionStringBuilder.append(arg2);
        }

        public void backspace() {
            int v0 = this.captionStringBuilder.length();
            if(v0 > 0) {
                this.captionStringBuilder.delete(v0 - 1, v0);
                int v1;
                for(v1 = this.cueStyles.size() - 1; v1 >= 0; --v1) {
                    Object v2 = this.cueStyles.get(v1);
                    if(((CueStyle)v2).start != v0) {
                        return;
                    }

                    --((CueStyle)v2).start;
                }
            }
        }

        public Cue build() {
            int v5;
            int v9;
            float v8;
            SpannableStringBuilder v1 = new SpannableStringBuilder();
            int v2;
            for(v2 = 0; v2 < this.rolledUpCaptions.size(); ++v2) {
                v1.append(this.rolledUpCaptions.get(v2));
                v1.append('\n');
            }

            v1.append(this.buildSpannableString());
            if(v1.length() == 0) {
                return null;
            }

            v2 = this.indent + this.tabOffset;
            int v3 = 32 - v2 - v1.length();
            int v4 = v2 - v3;
            int v7 = 2;
            if(this.captionMode == v7) {
                if(Math.abs(v4) >= 3 && v3 >= 0) {
                    goto label_39;
                }

                v8 = 0.5f;
                v9 = 1;
            }
            else {
            label_39:
                v8 = 0.1f;
                float v9_1 = 0.8f;
                float v10 = 32f;
                if(this.captionMode == v7 && v4 > 0) {
                    v8 = (((float)(32 - v3))) / v10 * v9_1 + v8;
                    v9 = 2;
                    goto label_59;
                }

                v8 = (((float)v2)) / v10 * v9_1 + v8;
                v9 = 0;
            }

        label_59:
            if(this.captionMode == 1 || this.row > 7) {
                v2 = this.row - 17;
                v5 = 2;
            }
            else {
                v2 = this.row;
                v5 = 0;
            }

            return new Cue(((CharSequence)v1), Layout$Alignment.ALIGN_NORMAL, ((float)v2), 1, v5, v8, v9, 0f);
        }

        public SpannableString buildSpannableString() {
            SpannableStringBuilder v0 = new SpannableStringBuilder(this.captionStringBuilder);
            int v1 = v0.length();
            int v3 = -1;
            int v4 = 0;
            int v5 = -1;
            int v6 = -1;
            int v7 = 0;
            int v8 = -1;
            int v9 = -1;
            int v10 = 0;
            while(v4 < this.cueStyles.size()) {
                Object v11 = this.cueStyles.get(v4);
                boolean v12 = ((CueStyle)v11).underline;
                int v13 = ((CueStyle)v11).style;
                if(v13 != 8) {
                    v10 = 7;
                    int v14 = v13 == v10 ? 1 : 0;
                    if(v13 == v10) {
                    }
                    else {
                        v9 = Cea608Decoder.STYLE_COLORS[v13];
                    }

                    v10 = v14;
                }

                int v11_1 = ((CueStyle)v11).start;
                ++v4;
                v13 = v4 < this.cueStyles.size() ? this.cueStyles.get(v4).start : v1;
                if(v11_1 == v13) {
                    continue;
                }

                if(v5 != v3 && !v12) {
                    CueBuilder.setUnderlineSpan(v0, v5, v11_1);
                    v5 = -1;
                }
                else if(v5 == v3 && (v12)) {
                    v5 = v11_1;
                }

                if(v6 != v3 && v10 == 0) {
                    CueBuilder.setItalicSpan(v0, v6, v11_1);
                    v6 = -1;
                }
                else if(v6 == v3 && v10 != 0) {
                    v6 = v11_1;
                }

                if(v9 == v8) {
                    continue;
                }

                CueBuilder.setColorSpan(v0, v7, v11_1, v8);
                v8 = v9;
                v7 = v11_1;
            }

            if(v5 != v3 && v5 != v1) {
                CueBuilder.setUnderlineSpan(v0, v5, v1);
            }

            if(v6 != v3 && v6 != v1) {
                CueBuilder.setItalicSpan(v0, v6, v1);
            }

            if(v7 != v1) {
                CueBuilder.setColorSpan(v0, v7, v1, v8);
            }

            return new SpannableString(((CharSequence)v0));
        }

        public int getRow() {
            return this.row;
        }

        public boolean isEmpty() {
            boolean v0 = !this.cueStyles.isEmpty() || !this.rolledUpCaptions.isEmpty() || this.captionStringBuilder.length() != 0 ? false : true;
            return v0;
        }

        public void reset(int arg2) {
            this.captionMode = arg2;
            this.cueStyles.clear();
            this.rolledUpCaptions.clear();
            this.captionStringBuilder.setLength(0);
            this.row = 15;
            this.indent = 0;
            this.tabOffset = 0;
        }

        public void rollUp() {
            this.rolledUpCaptions.add(this.buildSpannableString());
            this.captionStringBuilder.setLength(0);
            this.cueStyles.clear();
            int v0 = Math.min(this.captionRowCount, this.row);
            while(this.rolledUpCaptions.size() >= v0) {
                this.rolledUpCaptions.remove(0);
            }
        }

        public void setCaptionRowCount(int arg1) {
            this.captionRowCount = arg1;
        }

        private static void setColorSpan(SpannableStringBuilder arg1, int arg2, int arg3, int arg4) {
            if(arg4 == -1) {
                return;
            }

            arg1.setSpan(new ForegroundColorSpan(arg4), arg2, arg3, 33);
        }

        public void setIndent(int arg1) {
            this.indent = arg1;
        }

        private static void setItalicSpan(SpannableStringBuilder arg2, int arg3, int arg4) {
            arg2.setSpan(new StyleSpan(2), arg3, arg4, 33);
        }

        public void setRow(int arg1) {
            this.row = arg1;
        }

        public void setStyle(int arg4, boolean arg5) {
            this.cueStyles.add(new CueStyle(arg4, arg5, this.captionStringBuilder.length()));
        }

        public void setTab(int arg1) {
            this.tabOffset = arg1;
        }

        private static void setUnderlineSpan(SpannableStringBuilder arg2, int arg3, int arg4) {
            arg2.setSpan(new UnderlineSpan(), arg3, arg4, 33);
        }

        public String toString() {
            return this.captionStringBuilder.toString();
        }
    }

    private static final int[] BASIC_CHARACTER_SET = null;
    private static final int CC_FIELD_FLAG = 1;
    private static final byte CC_IMPLICIT_DATA_HEADER = -4;
    private static final int CC_MODE_PAINT_ON = 3;
    private static final int CC_MODE_POP_ON = 2;
    private static final int CC_MODE_ROLL_UP = 1;
    private static final int CC_MODE_UNKNOWN = 0;
    private static final int CC_TYPE_FLAG = 2;
    private static final int CC_VALID_608_ID = 4;
    private static final int CC_VALID_FLAG = 4;
    private static final int[] COLUMN_INDICES = null;
    private static final byte CTRL_BACKSPACE = 33;
    private static final byte CTRL_CARRIAGE_RETURN = 45;
    private static final byte CTRL_DELETE_TO_END_OF_ROW = 36;
    private static final byte CTRL_END_OF_CAPTION = 47;
    private static final byte CTRL_ERASE_DISPLAYED_MEMORY = 44;
    private static final byte CTRL_ERASE_NON_DISPLAYED_MEMORY = 46;
    private static final byte CTRL_RESUME_CAPTION_LOADING = 32;
    private static final byte CTRL_RESUME_DIRECT_CAPTIONING = 41;
    private static final byte CTRL_ROLL_UP_CAPTIONS_2_ROWS = 37;
    private static final byte CTRL_ROLL_UP_CAPTIONS_3_ROWS = 38;
    private static final byte CTRL_ROLL_UP_CAPTIONS_4_ROWS = 39;
    private static final int DEFAULT_CAPTIONS_ROW_COUNT = 4;
    private static final int NTSC_CC_FIELD_1 = 0;
    private static final int NTSC_CC_FIELD_2 = 1;
    private static final int[] ROW_INDICES = null;
    private static final int[] SPECIAL_CHARACTER_SET = null;
    private static final int[] SPECIAL_ES_FR_CHARACTER_SET = null;
    private static final int[] SPECIAL_PT_DE_CHARACTER_SET = null;
    private static final int[] STYLE_COLORS = null;
    private static final int STYLE_ITALICS = 7;
    private static final int STYLE_UNCHANGED = 8;
    private int captionMode;
    private int captionRowCount;
    private final ParsableByteArray ccData;
    private final ArrayList cueBuilders;
    private List cues;
    private CueBuilder currentCueBuilder;
    private List lastCues;
    private final int packetLength;
    private byte repeatableControlCc1;
    private byte repeatableControlCc2;
    private boolean repeatableControlSet;
    private final int selectedField;

    static {
        Cea608Decoder.ROW_INDICES = new int[]{11, 1, 3, 12, 14, 5, 7, 9};
        Cea608Decoder.COLUMN_INDICES = new int[]{0, 4, 8, 12, 16, 20, 24, 28};
        Cea608Decoder.STYLE_COLORS = new int[]{-1, -16711936, -16776961, -16711681, -65536, -256, -65281};
        Cea608Decoder.BASIC_CHARACTER_SET = new int[]{32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 225, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 233, 93, 237, 243, 250, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 231, 247, 209, 241, 9632};
        Cea608Decoder.SPECIAL_CHARACTER_SET = new int[]{174, 176, 189, 191, 8482, 162, 163, 9834, 224, 32, 232, 226, 234, 238, 244, 251};
        Cea608Decoder.SPECIAL_ES_FR_CHARACTER_SET = new int[]{193, 201, 211, 218, 220, 252, 8216, 161, 42, 39, 8212, 169, 8480, 8226, 8220, 8221, 192, 194, 199, 200, 202, 203, 235, 206, 207, 239, 212, 217, 249, 219, 171, 187};
        Cea608Decoder.SPECIAL_PT_DE_CHARACTER_SET = new int[]{195, 227, 205, 204, 236, 210, 242, 213, 245, 123, 125, 92, 94, 95, 124, 126, 196, 228, 214, 246, 223, 165, 164, 9474, 197, 229, 216, 248, 9484, 9488, 9492, 9496};
    }

    public Cea608Decoder(String arg4, int arg5) {
        super();
        this.ccData = new ParsableByteArray();
        this.cueBuilders = new ArrayList();
        this.currentCueBuilder = new CueBuilder(0, 4);
        int v0 = 2;
        int v4 = "application/x-mp4-cea-608".equals(arg4) ? 2 : 3;
        this.packetLength = v4;
        switch(arg5) {
            case 3: 
            case 4: {
                this.selectedField = v0;
                break;
            }
            default: {
                this.selectedField = 1;
                break;
            }
        }

        this.setCaptionMode(0);
        this.resetCueBuilders();
    }

    static int[] access$000() {
        return Cea608Decoder.STYLE_COLORS;
    }

    protected Subtitle createSubtitle() {
        this.lastCues = this.cues;
        return new CeaSubtitle(this.cues);
    }

    protected void decode(SubtitleInputBuffer arg10) {
        char v3_2;
        CueBuilder v1_1;
        this.ccData.reset(arg10.data.array(), arg10.data.limit());
        int v1 = 0;
        boolean v2 = false;
        while(this.ccData.bytesLeft() >= this.packetLength) {
            int v4 = 2;
            if(this.packetLength == v4) {
                int v3 = -4;
            }
            else {
                byte v3_1 = ((byte)this.ccData.readUnsignedByte());
            }

            byte v5 = ((byte)(this.ccData.readUnsignedByte() & 127));
            byte v6 = ((byte)(this.ccData.readUnsignedByte() & 127));
            if((v3_1 & 6) != 4) {
                continue;
            }

            if(this.selectedField == 1 && (v3_1 & 1) != 0) {
                continue;
            }

            if(this.selectedField == v4 && (v3_1 & 1) != 1) {
                continue;
            }

            if(v5 == 0 && v6 == 0) {
                continue;
            }

            if((v5 & 247) != 17 || (v6 & 240) != 48) {
                if((v5 & 246) == 18 && (v6 & 224) == 32) {
                    this.currentCueBuilder.backspace();
                    if((v5 & 1) == 0) {
                        v1_1 = this.currentCueBuilder;
                        v3_2 = Cea608Decoder.getExtendedEsFrChar(v6);
                    }
                    else {
                        v1_1 = this.currentCueBuilder;
                        v3_2 = Cea608Decoder.getExtendedPtDeChar(v6);
                    }

                    goto label_54;
                }

                if((v5 & 224) == 0) {
                    v2 = this.handleCtrl(v5, v6);
                    goto label_84;
                }

                this.currentCueBuilder.append(Cea608Decoder.getChar(v5));
                if((v6 & 224) == 0) {
                    goto label_84;
                }

                v1_1 = this.currentCueBuilder;
                v3_2 = Cea608Decoder.getChar(v6);
            label_54:
                v1_1.append(v3_2);
            }
            else {
                v1_1 = this.currentCueBuilder;
                v3_2 = Cea608Decoder.getSpecialChar(v6);
                goto label_54;
            }

        label_84:
            v1 = 1;
        }

        if(v1 != 0) {
            if(!v2) {
                this.repeatableControlSet = false;
            }

            if(this.captionMode != 1 && this.captionMode != 3) {
                return;
            }

            this.cues = this.getDisplayCues();
        }
    }

    public SubtitleInputBuffer dequeueInputBuffer() {
        return super.dequeueInputBuffer();
    }

    public SubtitleOutputBuffer dequeueOutputBuffer() {
        return super.dequeueOutputBuffer();
    }

    public void flush() {
        super.flush();
        this.cues = null;
        this.lastCues = null;
        this.setCaptionMode(0);
        this.setCaptionRowCount(4);
        this.resetCueBuilders();
        this.repeatableControlSet = false;
        this.repeatableControlCc1 = 0;
        this.repeatableControlCc2 = 0;
    }

    private static char getChar(byte arg1) {
        return ((char)Cea608Decoder.BASIC_CHARACTER_SET[(arg1 & 127) - 32]);
    }

    private List getDisplayCues() {
        ArrayList v0 = new ArrayList();
        int v1;
        for(v1 = 0; v1 < this.cueBuilders.size(); ++v1) {
            Cue v2 = this.cueBuilders.get(v1).build();
            if(v2 != null) {
                ((List)v0).add(v2);
            }
        }

        return ((List)v0);
    }

    private static char getExtendedEsFrChar(byte arg1) {
        return ((char)Cea608Decoder.SPECIAL_ES_FR_CHARACTER_SET[arg1 & 31]);
    }

    private static char getExtendedPtDeChar(byte arg1) {
        return ((char)Cea608Decoder.SPECIAL_PT_DE_CHARACTER_SET[arg1 & 31]);
    }

    public String getName() {
        return "Cea608Decoder";
    }

    private static char getSpecialChar(byte arg1) {
        return ((char)Cea608Decoder.SPECIAL_CHARACTER_SET[arg1 & 15]);
    }

    private boolean handleCtrl(byte arg4, byte arg5) {
        boolean v0 = Cea608Decoder.isRepeatable(arg4);
        if(v0) {
            if((this.repeatableControlSet) && this.repeatableControlCc1 == arg4 && this.repeatableControlCc2 == arg5) {
                this.repeatableControlSet = false;
                return 1;
            }

            this.repeatableControlSet = true;
            this.repeatableControlCc1 = arg4;
            this.repeatableControlCc2 = arg5;
        }

        if(Cea608Decoder.isMidrowCtrlCode(arg4, arg5)) {
            this.handleMidrowCtrl(arg5);
        }
        else if(Cea608Decoder.isPreambleAddressCode(arg4, arg5)) {
            this.handlePreambleAddressCode(arg4, arg5);
        }
        else if(Cea608Decoder.isTabCtrlCode(arg4, arg5)) {
            this.currentCueBuilder.setTab(arg5 - 32);
        }
        else if(Cea608Decoder.isMiscCode(arg4, arg5)) {
            this.handleMiscCode(arg5);
        }

        return v0;
    }

    private void handleMidrowCtrl(byte arg3) {
        this.currentCueBuilder.append(' ');
        boolean v0 = (arg3 & 1) == 1 ? true : false;
        this.currentCueBuilder.setStyle(arg3 >> 1 & 7, v0);
    }

    private void handleMiscCode(byte arg4) {
        int v1 = 2;
        if(arg4 == 32) {
            goto label_51;
        }

        int v2 = 3;
        if(arg4 == 41) {
            goto label_49;
        }

        switch(arg4) {
            case 37: {
                goto label_46;
            }
            case 38: {
                goto label_43;
            }
            case 39: {
                goto label_39;
            }
        }

        if(this.captionMode == 0) {
            return;
        }

        if(arg4 == 33) {
            this.currentCueBuilder.backspace();
        }
        else if(arg4 != 36) {
            switch(arg4) {
                case 44: {
                    goto label_29;
                }
                case 45: {
                    goto label_21;
                }
                case 46: {
                    goto label_19;
                }
                case 47: {
                    goto label_17;
                }
            }

            return;
        label_17:
            this.cues = this.getDisplayCues();
            goto label_19;
        label_21:
            if(this.captionMode == 1 && !this.currentCueBuilder.isEmpty()) {
                this.currentCueBuilder.rollUp();
                return;
            label_29:
                this.cues = null;
                if(this.captionMode == 1 || this.captionMode == v2) {
                label_19:
                    this.resetCueBuilders();
                }
            }
        }

        return;
    label_39:
        this.setCaptionMode(1);
        this.setCaptionRowCount(4);
        return;
    label_43:
        this.setCaptionMode(1);
        this.setCaptionRowCount(v2);
        return;
    label_46:
        this.setCaptionMode(1);
        this.setCaptionRowCount(v1);
        return;
    label_49:
        this.setCaptionMode(v2);
        return;
    label_51:
        this.setCaptionMode(v1);
    }

    private void handlePreambleAddressCode(byte arg6, byte arg7) {
        int v6 = Cea608Decoder.ROW_INDICES[arg6 & 7];
        boolean v1 = false;
        int v0 = (arg7 & 32) != 0 ? 1 : 0;
        if(v0 != 0) {
            ++v6;
        }

        if(v6 != this.currentCueBuilder.getRow()) {
            if(this.captionMode != 1 && !this.currentCueBuilder.isEmpty()) {
                this.currentCueBuilder = new CueBuilder(this.captionMode, this.captionRowCount);
                this.cueBuilders.add(this.currentCueBuilder);
            }

            this.currentCueBuilder.setRow(v6);
        }

        v6 = (arg7 & 16) == 16 ? 1 : 0;
        if((arg7 & 1) == 1) {
            v1 = true;
        }

        int v7 = arg7 >> 1 & 7;
        CueBuilder v0_1 = this.currentCueBuilder;
        int v2 = v6 != 0 ? 8 : v7;
        v0_1.setStyle(v2, v1);
        if(v6 != 0) {
            this.currentCueBuilder.setIndent(Cea608Decoder.COLUMN_INDICES[v7]);
        }
    }

    private static boolean isMidrowCtrlCode(byte arg1, byte arg2) {
        boolean v1 = (arg1 & 247) != 17 || (arg2 & 240) != 32 ? false : true;
        return v1;
    }

    private static boolean isMiscCode(byte arg1, byte arg2) {
        boolean v1 = (arg1 & 247) != 20 || (arg2 & 240) != 32 ? false : true;
        return v1;
    }

    protected boolean isNewSubtitleDataAvailable() {
        boolean v0 = this.cues != this.lastCues ? true : false;
        return v0;
    }

    private static boolean isPreambleAddressCode(byte arg1, byte arg2) {
        boolean v1 = (arg1 & 240) != 16 || (arg2 & 192) != 64 ? false : true;
        return v1;
    }

    private static boolean isRepeatable(byte arg1) {
        boolean v1 = (arg1 & 240) == 16 ? true : false;
        return v1;
    }

    private static boolean isTabCtrlCode(byte arg1, byte arg2) {
        boolean v1 = (arg1 & 247) != 23 || arg2 < 33 || arg2 > 35 ? false : true;
        return v1;
    }

    public void queueInputBuffer(SubtitleInputBuffer arg1) {
        super.queueInputBuffer(arg1);
    }

    public void release() {
    }

    private void resetCueBuilders() {
        this.currentCueBuilder.reset(this.captionMode);
        this.cueBuilders.clear();
        this.cueBuilders.add(this.currentCueBuilder);
    }

    private void setCaptionMode(int arg3) {
        if(this.captionMode == arg3) {
            return;
        }

        int v0 = this.captionMode;
        this.captionMode = arg3;
        this.resetCueBuilders();
        if(v0 == 3 || arg3 == 1 || arg3 == 0) {
            this.cues = null;
        }
    }

    private void setCaptionRowCount(int arg2) {
        this.captionRowCount = arg2;
        this.currentCueBuilder.setCaptionRowCount(arg2);
    }

    public void setPositionUs(long arg1) {
        super.setPositionUs(arg1);
    }
}


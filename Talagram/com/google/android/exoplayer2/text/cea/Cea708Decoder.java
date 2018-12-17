package com.google.android.exoplayer2.text.cea;

import android.graphics.Color;
import android.text.Layout$Alignment;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.SubtitleInputBuffer;
import com.google.android.exoplayer2.text.SubtitleOutputBuffer;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Cea708Decoder extends CeaDecoder {
    final class CueBuilder {
        private static final int BORDER_AND_EDGE_TYPE_NONE = 0;
        private static final int BORDER_AND_EDGE_TYPE_UNIFORM = 3;
        public static final int COLOR_SOLID_BLACK = 0;
        public static final int COLOR_SOLID_WHITE = 0;
        public static final int COLOR_TRANSPARENT = 0;
        private static final int DEFAULT_PRIORITY = 4;
        private static final int DIRECTION_BOTTOM_TO_TOP = 3;
        private static final int DIRECTION_LEFT_TO_RIGHT = 0;
        private static final int DIRECTION_RIGHT_TO_LEFT = 1;
        private static final int DIRECTION_TOP_TO_BOTTOM = 2;
        private static final int HORIZONTAL_SIZE = 209;
        private static final int JUSTIFICATION_CENTER = 2;
        private static final int JUSTIFICATION_FULL = 3;
        private static final int JUSTIFICATION_LEFT = 0;
        private static final int JUSTIFICATION_RIGHT = 1;
        private static final int MAXIMUM_ROW_COUNT = 15;
        private static final int PEN_FONT_STYLE_DEFAULT = 0;
        private static final int PEN_FONT_STYLE_MONOSPACED_WITHOUT_SERIFS = 3;
        private static final int PEN_FONT_STYLE_MONOSPACED_WITH_SERIFS = 1;
        private static final int PEN_FONT_STYLE_PROPORTIONALLY_SPACED_WITHOUT_SERIFS = 4;
        private static final int PEN_FONT_STYLE_PROPORTIONALLY_SPACED_WITH_SERIFS = 2;
        private static final int PEN_OFFSET_NORMAL = 1;
        private static final int PEN_SIZE_STANDARD = 1;
        private static final int[] PEN_STYLE_BACKGROUND = null;
        private static final int[] PEN_STYLE_EDGE_TYPE = null;
        private static final int[] PEN_STYLE_FONT_STYLE = null;
        private static final int RELATIVE_CUE_SIZE = 99;
        private static final int VERTICAL_SIZE = 74;
        private static final int[] WINDOW_STYLE_FILL;
        private static final int[] WINDOW_STYLE_JUSTIFICATION;
        private static final int[] WINDOW_STYLE_PRINT_DIRECTION;
        private static final int[] WINDOW_STYLE_SCROLL_DIRECTION;
        private static final boolean[] WINDOW_STYLE_WORD_WRAP;
        private int anchorId;
        private int backgroundColor;
        private int backgroundColorStartPosition;
        private final SpannableStringBuilder captionStringBuilder;
        private boolean defined;
        private int foregroundColor;
        private int foregroundColorStartPosition;
        private int horizontalAnchor;
        private int italicsStartPosition;
        private int justification;
        private int penStyleId;
        private int priority;
        private boolean relativePositioning;
        private final List rolledUpCaptions;
        private int row;
        private int rowCount;
        private boolean rowLock;
        private int underlineStartPosition;
        private int verticalAnchor;
        private boolean visible;
        private int windowFillColor;
        private int windowStyleId;

        static {
            CueBuilder.COLOR_SOLID_WHITE = CueBuilder.getArgbColorFromCeaColor(2, 2, 2, 0);
            CueBuilder.COLOR_SOLID_BLACK = CueBuilder.getArgbColorFromCeaColor(0, 0, 0, 0);
            CueBuilder.COLOR_TRANSPARENT = CueBuilder.getArgbColorFromCeaColor(0, 0, 0, 3);
            CueBuilder.WINDOW_STYLE_JUSTIFICATION = new int[]{0, 0, 0, 0, 0, 2, 0};
            CueBuilder.WINDOW_STYLE_PRINT_DIRECTION = new int[]{0, 0, 0, 0, 0, 0, 2};
            CueBuilder.WINDOW_STYLE_SCROLL_DIRECTION = new int[]{3, 3, 3, 3, 3, 3, 1};
            CueBuilder.WINDOW_STYLE_WORD_WRAP = new boolean[]{false, false, false, true, true, true, false};
            CueBuilder.WINDOW_STYLE_FILL = new int[]{CueBuilder.COLOR_SOLID_BLACK, CueBuilder.COLOR_TRANSPARENT, CueBuilder.COLOR_SOLID_BLACK, CueBuilder.COLOR_SOLID_BLACK, CueBuilder.COLOR_TRANSPARENT, CueBuilder.COLOR_SOLID_BLACK, CueBuilder.COLOR_SOLID_BLACK};
            CueBuilder.PEN_STYLE_FONT_STYLE = new int[]{0, 1, 2, 3, 4, 3, 4};
            CueBuilder.PEN_STYLE_EDGE_TYPE = new int[]{0, 0, 0, 0, 0, 3, 3};
            CueBuilder.PEN_STYLE_BACKGROUND = new int[]{CueBuilder.COLOR_SOLID_BLACK, CueBuilder.COLOR_SOLID_BLACK, CueBuilder.COLOR_SOLID_BLACK, CueBuilder.COLOR_SOLID_BLACK, CueBuilder.COLOR_SOLID_BLACK, CueBuilder.COLOR_TRANSPARENT, CueBuilder.COLOR_TRANSPARENT};
        }

        public CueBuilder() {
            super();
            this.rolledUpCaptions = new ArrayList();
            this.captionStringBuilder = new SpannableStringBuilder();
            this.reset();
        }

        public void append(char arg3) {
            if(arg3 == 10) {
                this.rolledUpCaptions.add(this.buildSpannableString());
                this.captionStringBuilder.clear();
                int v0 = -1;
                if(this.italicsStartPosition != v0) {
                    this.italicsStartPosition = 0;
                }

                if(this.underlineStartPosition != v0) {
                    this.underlineStartPosition = 0;
                }

                if(this.foregroundColorStartPosition != v0) {
                    this.foregroundColorStartPosition = 0;
                }

                if(this.backgroundColorStartPosition != v0) {
                    this.backgroundColorStartPosition = 0;
                }

                while((this.rowLock) && this.rolledUpCaptions.size() >= this.rowCount || this.rolledUpCaptions.size() >= 15) {
                    this.rolledUpCaptions.remove(0);
                }
            }
            else {
                this.captionStringBuilder.append(arg3);
            }
        }

        public void backspace() {
            int v0 = this.captionStringBuilder.length();
            if(v0 > 0) {
                this.captionStringBuilder.delete(v0 - 1, v0);
            }
        }

        public Cea708Cue build() {
            int v9;
            int v8;
            float v5;
            float v1_3;
            if(this.isEmpty()) {
                return null;
            }

            SpannableStringBuilder v2 = new SpannableStringBuilder();
            int v1;
            for(v1 = 0; v1 < this.rolledUpCaptions.size(); ++v1) {
                v2.append(this.rolledUpCaptions.get(v1));
                v2.append('\n');
            }

            v2.append(this.buildSpannableString());
            switch(this.justification) {
                case 1: {
                    goto label_34;
                }
                case 2: {
                    goto label_32;
                }
                case 0: 
                case 3: {
                    goto label_36;
                }
            }

            StringBuilder v1_1 = new StringBuilder();
            v1_1.append("Unexpected justification value: ");
            v1_1.append(this.justification);
            throw new IllegalArgumentException(v1_1.toString());
        label_34:
            Layout$Alignment v1_2 = Layout$Alignment.ALIGN_OPPOSITE;
            goto label_37;
        label_36:
            v1_2 = Layout$Alignment.ALIGN_NORMAL;
            goto label_37;
        label_32:
            v1_2 = Layout$Alignment.ALIGN_CENTER;
        label_37:
            Layout$Alignment v3 = v1_2;
            if(this.relativePositioning) {
                v1_3 = (((float)this.horizontalAnchor)) / 99f;
                v5 = (((float)this.verticalAnchor)) / 99f;
            }
            else {
                v1_3 = (((float)this.horizontalAnchor)) / 209f;
                v5 = (((float)this.verticalAnchor)) / 74f;
            }

            float v7 = v1_3 * 0.9f + 0.05f;
            float v4 = v5 * 0.9f + 0.05f;
            if(this.anchorId % 3 == 0) {
                v8 = 0;
            }
            else if(this.anchorId % 3 == 1) {
                v8 = 1;
            }
            else {
                v8 = 2;
            }

            if(this.anchorId / 3 == 0) {
                v9 = 0;
            }
            else if(this.anchorId / 3 == 1) {
                v9 = 1;
            }
            else {
                v9 = 2;
            }

            boolean v10 = this.windowFillColor != CueBuilder.COLOR_SOLID_BLACK ? true : false;
            return new Cea708Cue(((CharSequence)v2), v3, v4, 0, v8, v7, v9, 0f, v10, this.windowFillColor, this.priority);
        }

        public SpannableString buildSpannableString() {
            SpannableStringBuilder v0 = new SpannableStringBuilder(this.captionStringBuilder);
            int v1 = v0.length();
            if(v1 > 0) {
                int v3 = 33;
                int v4 = -1;
                if(this.italicsStartPosition != v4) {
                    v0.setSpan(new StyleSpan(2), this.italicsStartPosition, v1, v3);
                }

                if(this.underlineStartPosition != v4) {
                    v0.setSpan(new UnderlineSpan(), this.underlineStartPosition, v1, v3);
                }

                if(this.foregroundColorStartPosition != v4) {
                    v0.setSpan(new ForegroundColorSpan(this.foregroundColor), this.foregroundColorStartPosition, v1, v3);
                }

                if(this.backgroundColorStartPosition == v4) {
                    goto label_34;
                }

                v0.setSpan(new BackgroundColorSpan(this.backgroundColor), this.backgroundColorStartPosition, v1, v3);
            }

        label_34:
            return new SpannableString(((CharSequence)v0));
        }

        public void clear() {
            this.rolledUpCaptions.clear();
            this.captionStringBuilder.clear();
            this.italicsStartPosition = -1;
            this.underlineStartPosition = -1;
            this.foregroundColorStartPosition = -1;
            this.backgroundColorStartPosition = -1;
            this.row = 0;
        }

        public void defineWindow(boolean arg12, boolean arg13, boolean arg14, int arg15, boolean arg16, int arg17, int arg18, int arg19, int arg20, int arg21, int arg22, int arg23) {
            int v1_1;
            CueBuilder v0 = this;
            boolean v1 = arg13;
            int v2 = arg22;
            int v3 = arg23;
            v0.defined = true;
            v0.visible = arg12;
            v0.rowLock = v1;
            v0.priority = arg15;
            v0.relativePositioning = arg16;
            v0.verticalAnchor = arg17;
            v0.horizontalAnchor = arg18;
            v0.anchorId = arg21;
            int v6 = arg19 + 1;
            if(v0.rowCount != v6) {
                v0.rowCount = v6;
                while((v1) && v0.rolledUpCaptions.size() >= v0.rowCount || v0.rolledUpCaptions.size() >= 15) {
                    v0.rolledUpCaptions.remove(0);
                }
            }

            if(v2 != 0 && v0.windowStyleId != v2) {
                v0.windowStyleId = v2;
                v1_1 = v2 - 1;
                this.setWindowAttributes(CueBuilder.WINDOW_STYLE_FILL[v1_1], CueBuilder.COLOR_TRANSPARENT, CueBuilder.WINDOW_STYLE_WORD_WRAP[v1_1], 0, CueBuilder.WINDOW_STYLE_PRINT_DIRECTION[v1_1], CueBuilder.WINDOW_STYLE_SCROLL_DIRECTION[v1_1], CueBuilder.WINDOW_STYLE_JUSTIFICATION[v1_1]);
            }

            if(v3 != 0 && v0.penStyleId != v3) {
                v0.penStyleId = v3;
                v1_1 = v3 - 1;
                this.setPenAttributes(0, 1, 1, false, false, CueBuilder.PEN_STYLE_EDGE_TYPE[v1_1], CueBuilder.PEN_STYLE_FONT_STYLE[v1_1]);
                this.setPenColor(CueBuilder.COLOR_SOLID_WHITE, CueBuilder.PEN_STYLE_BACKGROUND[v1_1], CueBuilder.COLOR_SOLID_BLACK);
            }
        }

        public static int getArgbColorFromCeaColor(int arg3, int arg4, int arg5, int arg6) {
            Assertions.checkIndex(arg3, 0, 4);
            Assertions.checkIndex(arg4, 0, 4);
            Assertions.checkIndex(arg5, 0, 4);
            Assertions.checkIndex(arg6, 0, 4);
            int v0 = 255;
            switch(arg6) {
                case 2: {
                    arg6 = 127;
                    break;
                }
                case 3: {
                    arg6 = 0;
                    break;
                }
                default: {
                    arg6 = 255;
                    break;
                }
            }

            arg3 = arg3 > 1 ? 255 : 0;
            arg4 = arg4 > 1 ? 255 : 0;
            if(arg5 > 1) {
            }
            else {
                v0 = 0;
            }

            return Color.argb(arg6, arg3, arg4, v0);
        }

        public static int getArgbColorFromCeaColor(int arg1, int arg2, int arg3) {
            return CueBuilder.getArgbColorFromCeaColor(arg1, arg2, arg3, 0);
        }

        public boolean isDefined() {
            return this.defined;
        }

        public boolean isEmpty() {
            boolean v0;
            if(this.isDefined()) {
                if((this.rolledUpCaptions.isEmpty()) && this.captionStringBuilder.length() == 0) {
                    goto label_11;
                }

                v0 = false;
            }
            else {
            label_11:
                v0 = true;
            }

            return v0;
        }

        public boolean isVisible() {
            return this.visible;
        }

        public void reset() {
            this.clear();
            this.defined = false;
            this.visible = false;
            this.priority = 4;
            this.relativePositioning = false;
            this.verticalAnchor = 0;
            this.horizontalAnchor = 0;
            this.anchorId = 0;
            this.rowCount = 15;
            this.rowLock = true;
            this.justification = 0;
            this.windowStyleId = 0;
            this.penStyleId = 0;
            this.windowFillColor = CueBuilder.COLOR_SOLID_BLACK;
            this.foregroundColor = CueBuilder.COLOR_SOLID_WHITE;
            this.backgroundColor = CueBuilder.COLOR_SOLID_BLACK;
        }

        public void setPenAttributes(int arg1, int arg2, int arg3, boolean arg4, boolean arg5, int arg6, int arg7) {
            arg2 = 33;
            arg3 = -1;
            if(this.italicsStartPosition != arg3) {
                if(!arg4) {
                    this.captionStringBuilder.setSpan(new StyleSpan(2), this.italicsStartPosition, this.captionStringBuilder.length(), arg2);
                    this.italicsStartPosition = arg3;
                }
            }
            else if(arg4) {
                this.italicsStartPosition = this.captionStringBuilder.length();
            }

            if(this.underlineStartPosition != arg3) {
                if(!arg5) {
                    this.captionStringBuilder.setSpan(new UnderlineSpan(), this.underlineStartPosition, this.captionStringBuilder.length(), arg2);
                    this.underlineStartPosition = arg3;
                }
            }
            else if(arg5) {
                this.underlineStartPosition = this.captionStringBuilder.length();
            }
        }

        public void setPenColor(int arg6, int arg7, int arg8) {
            int v0 = 33;
            int v1 = -1;
            if(this.foregroundColorStartPosition != v1 && this.foregroundColor != arg6) {
                this.captionStringBuilder.setSpan(new ForegroundColorSpan(this.foregroundColor), this.foregroundColorStartPosition, this.captionStringBuilder.length(), v0);
            }

            if(arg6 != CueBuilder.COLOR_SOLID_WHITE) {
                this.foregroundColorStartPosition = this.captionStringBuilder.length();
                this.foregroundColor = arg6;
            }

            if(this.backgroundColorStartPosition != v1 && this.backgroundColor != arg7) {
                this.captionStringBuilder.setSpan(new BackgroundColorSpan(this.backgroundColor), this.backgroundColorStartPosition, this.captionStringBuilder.length(), v0);
            }

            if(arg7 != CueBuilder.COLOR_SOLID_BLACK) {
                this.backgroundColorStartPosition = this.captionStringBuilder.length();
                this.backgroundColor = arg7;
            }
        }

        public void setPenLocation(int arg1, int arg2) {
            if(this.row != arg1) {
                this.append('\n');
            }

            this.row = arg1;
        }

        public void setVisibility(boolean arg1) {
            this.visible = arg1;
        }

        public void setWindowAttributes(int arg1, int arg2, boolean arg3, int arg4, int arg5, int arg6, int arg7) {
            this.windowFillColor = arg1;
            this.justification = arg7;
        }
    }

    final class DtvCcPacket {
        int currentIndex;
        public final byte[] packetData;
        public final int packetSize;
        public final int sequenceNumber;

        public DtvCcPacket(int arg1, int arg2) {
            super();
            this.sequenceNumber = arg1;
            this.packetSize = arg2;
            this.packetData = new byte[arg2 * 2 - 1];
            this.currentIndex = 0;
        }
    }

    private static final int CC_VALID_FLAG = 4;
    private static final int CHARACTER_BIG_CARONS = 42;
    private static final int CHARACTER_BIG_OE = 44;
    private static final int CHARACTER_BOLD_BULLET = 53;
    private static final int CHARACTER_CLOSE_DOUBLE_QUOTE = 52;
    private static final int CHARACTER_CLOSE_SINGLE_QUOTE = 50;
    private static final int CHARACTER_DIAERESIS_Y = 63;
    private static final int CHARACTER_ELLIPSIS = 37;
    private static final int CHARACTER_FIVE_EIGHTHS = 120;
    private static final int CHARACTER_HORIZONTAL_BORDER = 125;
    private static final int CHARACTER_LOWER_LEFT_BORDER = 124;
    private static final int CHARACTER_LOWER_RIGHT_BORDER = 126;
    private static final int CHARACTER_MN = 127;
    private static final int CHARACTER_NBTSP = 33;
    private static final int CHARACTER_ONE_EIGHTH = 118;
    private static final int CHARACTER_OPEN_DOUBLE_QUOTE = 51;
    private static final int CHARACTER_OPEN_SINGLE_QUOTE = 49;
    private static final int CHARACTER_SEVEN_EIGHTHS = 121;
    private static final int CHARACTER_SM = 61;
    private static final int CHARACTER_SMALL_CARONS = 58;
    private static final int CHARACTER_SMALL_OE = 60;
    private static final int CHARACTER_SOLID_BLOCK = 48;
    private static final int CHARACTER_THREE_EIGHTHS = 119;
    private static final int CHARACTER_TM = 57;
    private static final int CHARACTER_TSP = 32;
    private static final int CHARACTER_UPPER_LEFT_BORDER = 127;
    private static final int CHARACTER_UPPER_RIGHT_BORDER = 123;
    private static final int CHARACTER_VERTICAL_BORDER = 122;
    private static final int COMMAND_BS = 8;
    private static final int COMMAND_CLW = 136;
    private static final int COMMAND_CR = 13;
    private static final int COMMAND_CW0 = 128;
    private static final int COMMAND_CW1 = 129;
    private static final int COMMAND_CW2 = 130;
    private static final int COMMAND_CW3 = 131;
    private static final int COMMAND_CW4 = 132;
    private static final int COMMAND_CW5 = 133;
    private static final int COMMAND_CW6 = 134;
    private static final int COMMAND_CW7 = 135;
    private static final int COMMAND_DF0 = 152;
    private static final int COMMAND_DF1 = 153;
    private static final int COMMAND_DF2 = 154;
    private static final int COMMAND_DF3 = 155;
    private static final int COMMAND_DF4 = 156;
    private static final int COMMAND_DF5 = 157;
    private static final int COMMAND_DF6 = 158;
    private static final int COMMAND_DF7 = 159;
    private static final int COMMAND_DLC = 142;
    private static final int COMMAND_DLW = 140;
    private static final int COMMAND_DLY = 141;
    private static final int COMMAND_DSW = 137;
    private static final int COMMAND_ETX = 3;
    private static final int COMMAND_EXT1 = 16;
    private static final int COMMAND_EXT1_END = 23;
    private static final int COMMAND_EXT1_START = 17;
    private static final int COMMAND_FF = 12;
    private static final int COMMAND_HCR = 14;
    private static final int COMMAND_HDW = 138;
    private static final int COMMAND_NUL = 0;
    private static final int COMMAND_P16_END = 31;
    private static final int COMMAND_P16_START = 24;
    private static final int COMMAND_RST = 143;
    private static final int COMMAND_SPA = 144;
    private static final int COMMAND_SPC = 145;
    private static final int COMMAND_SPL = 146;
    private static final int COMMAND_SWA = 151;
    private static final int COMMAND_TGW = 139;
    private static final int DTVCC_PACKET_DATA = 2;
    private static final int DTVCC_PACKET_START = 3;
    private static final int GROUP_C0_END = 31;
    private static final int GROUP_C1_END = 159;
    private static final int GROUP_C2_END = 31;
    private static final int GROUP_C3_END = 159;
    private static final int GROUP_G0_END = 127;
    private static final int GROUP_G1_END = 255;
    private static final int GROUP_G2_END = 127;
    private static final int GROUP_G3_END = 255;
    private static final int NUM_WINDOWS = 8;
    private static final String TAG = "Cea708Decoder";
    private final ParsableByteArray ccData;
    private final CueBuilder[] cueBuilders;
    private List cues;
    private CueBuilder currentCueBuilder;
    private DtvCcPacket currentDtvCcPacket;
    private int currentWindow;
    private List lastCues;
    private final int selectedServiceNumber;
    private final ParsableBitArray serviceBlockPacket;

    public Cea708Decoder(int arg4, List arg5) {
        super();
        this.ccData = new ParsableByteArray();
        this.serviceBlockPacket = new ParsableBitArray();
        this.selectedServiceNumber = 1;
        arg4 = 8;
        this.cueBuilders = new CueBuilder[arg4];
        int v0;
        for(v0 = 0; v0 < arg4; ++v0) {
            this.cueBuilders[v0] = new CueBuilder();
        }

        this.currentCueBuilder = this.cueBuilders[0];
        this.resetCueBuilders();
    }

    protected Subtitle createSubtitle() {
        this.lastCues = this.cues;
        return new CeaSubtitle(this.cues);
    }

    protected void decode(SubtitleInputBuffer arg8) {
        DtvCcPacket v0_1;
        byte[] v8_1;
        this.ccData.reset(arg8.data.array(), arg8.data.limit());
        while(true) {
            int v0 = 3;
            if(this.ccData.bytesLeft() < v0) {
                return;
            }

            int v8 = this.ccData.readUnsignedByte() & 7;
            int v1 = v8 & 3;
            boolean v3 = false;
            v8 = (v8 & 4) == 4 ? 1 : 0;
            byte v2 = ((byte)this.ccData.readUnsignedByte());
            byte v5 = ((byte)this.ccData.readUnsignedByte());
            int v6 = 2;
            if(v1 != v6 && v1 != v0) {
                continue;
            }

            if(v8 == 0) {
                continue;
            }

            if(v1 == v0) {
                this.finalizeCurrentPacket();
                v8 = (v2 & 192) >> 6;
                v0 = v2 & 63;
                if(v0 == 0) {
                    v0 = 64;
                }

                this.currentDtvCcPacket = new DtvCcPacket(v8, v0);
                v8_1 = this.currentDtvCcPacket.packetData;
                v0_1 = this.currentDtvCcPacket;
                v1 = v0_1.currentIndex;
                v0_1.currentIndex = v1 + 1;
                v8_1[v1] = v5;
            }
            else {
                if(v1 == v6) {
                    v3 = true;
                }

                Assertions.checkArgument(v3);
                if(this.currentDtvCcPacket == null) {
                    Log.e("Cea708Decoder", "Encountered DTVCC_PACKET_DATA before DTVCC_PACKET_START");
                    continue;
                }

                v8_1 = this.currentDtvCcPacket.packetData;
                v0_1 = this.currentDtvCcPacket;
                v1 = v0_1.currentIndex;
                v0_1.currentIndex = v1 + 1;
                v8_1[v1] = v2;
                v8_1 = this.currentDtvCcPacket.packetData;
                v0_1 = this.currentDtvCcPacket;
                v1 = v0_1.currentIndex;
                v0_1.currentIndex = v1 + 1;
                v8_1[v1] = v5;
            }

            if(this.currentDtvCcPacket.currentIndex != this.currentDtvCcPacket.packetSize * 2 - 1) {
                continue;
            }

            this.finalizeCurrentPacket();
        }
    }

    public SubtitleInputBuffer dequeueInputBuffer() {
        return super.dequeueInputBuffer();
    }

    public SubtitleOutputBuffer dequeueOutputBuffer() {
        return super.dequeueOutputBuffer();
    }

    private void finalizeCurrentPacket() {
        if(this.currentDtvCcPacket == null) {
            return;
        }

        this.processCurrentPacket();
        this.currentDtvCcPacket = null;
    }

    public void flush() {
        super.flush();
        this.cues = null;
        this.lastCues = null;
        this.currentWindow = 0;
        this.currentCueBuilder = this.cueBuilders[this.currentWindow];
        this.resetCueBuilders();
        this.currentDtvCcPacket = null;
    }

    private List getDisplayCues() {
        ArrayList v0 = new ArrayList();
        int v1;
        for(v1 = 0; v1 < 8; ++v1) {
            if(!this.cueBuilders[v1].isEmpty() && (this.cueBuilders[v1].isVisible())) {
                ((List)v0).add(this.cueBuilders[v1].build());
            }
        }

        Collections.sort(((List)v0));
        return Collections.unmodifiableList(((List)v0));
    }

    public String getName() {
        return "Cea708Decoder";
    }

    private void handleC0Command(int arg5) {
        ParsableBitArray v5;
        if(arg5 != 0) {
            if(arg5 != 3) {
                int v0 = 8;
                if(arg5 != v0) {
                    switch(arg5) {
                        case 12: {
                            goto label_49;
                        }
                        case 13: {
                            goto label_45;
                        }
                        case 14: {
                            return;
                        }
                    }

                    if(arg5 < 17 || arg5 > 23) {
                        if(arg5 >= 24 && arg5 <= 31) {
                            Log.w("Cea708Decoder", "Currently unsupported COMMAND_P16 Command: " + arg5);
                            v5 = this.serviceBlockPacket;
                            v0 = 16;
                            goto label_19;
                        }

                        goto label_36;
                    }
                    else {
                        Log.w("Cea708Decoder", "Currently unsupported COMMAND_EXT1 Command: " + arg5);
                        v5 = this.serviceBlockPacket;
                    }

                label_19:
                    v5.skipBits(v0);
                    return;
                label_36:
                    Log.w("Cea708Decoder", "Invalid C0 command: " + arg5);
                    return;
                label_49:
                    this.resetCueBuilders();
                    return;
                label_45:
                    this.currentCueBuilder.append('\n');
                }
                else {
                    this.currentCueBuilder.backspace();
                }
            }
            else {
                this.cues = this.getDisplayCues();
            }
        }
    }

    private void handleC1Command(int arg5) {
        ParsableBitArray v5;
        int v0 = 16;
        int v1 = 8;
        int v2 = 1;
        switch(arg5) {
            case 128: 
            case 129: 
            case 130: 
            case 131: 
            case 132: 
            case 133: 
            case 134: 
            case 135: {
                goto label_112;
            }
            case 136: {
                goto label_102;
            }
            case 137: {
                goto label_91;
            }
            case 138: {
                goto label_80;
            }
            case 139: {
                goto label_67;
            }
            case 140: {
                goto label_57;
            }
            case 141: {
                goto label_54;
            }
            case 142: {
                break;
            }
            case 143: {
                goto label_52;
            }
            case 144: {
                goto label_44;
            }
            case 145: {
                goto label_36;
            }
            case 146: {
                goto label_30;
            }
            default: {
                switch(arg5) {
                    case 151: {
                        if(!this.currentCueBuilder.isDefined()) {
                            v5 = this.serviceBlockPacket;
                            v0 = 32;
                            goto label_48;
                        }

                        this.handleSetWindowAttributes();
                        return;
                    }
                    case 152: 
                    case 153: 
                    case 154: 
                    case 155: 
                    case 156: 
                    case 157: 
                    case 158: 
                    case 159: {
                        arg5 += -152;
                        this.handleDefineWindow(arg5);
                        if(this.currentWindow == arg5) {
                            return;
                        }

                        this.currentWindow = arg5;
                        CueBuilder v5_1 = this.cueBuilders[arg5];
                        goto label_118;
                    label_67:
                        for(arg5 = 1; true; ++arg5) {
                            if(arg5 > v1) {
                                return;
                            }

                            if(this.serviceBlockPacket.readBit()) {
                                this.cueBuilders[8 - arg5].setVisibility(this.cueBuilders[8 - arg5].isVisible() ^ 1);
                            }
                        }

                    label_36:
                        if(!this.currentCueBuilder.isDefined()) {
                            v5 = this.serviceBlockPacket;
                            v0 = 24;
                            goto label_48;
                        }

                        this.handleSetPenColor();
                        return;
                        while(true) {
                        label_102:
                            if(v2 > v1) {
                                return;
                            }

                            if(this.serviceBlockPacket.readBit()) {
                                this.cueBuilders[8 - v2].clear();
                            }

                            ++v2;
                        }

                    label_44:
                        if(!this.currentCueBuilder.isDefined()) {
                            goto label_47;
                        }

                        this.handleSetPenAttributes();
                        return;
                    label_112:
                        arg5 += -128;
                        if(this.currentWindow == arg5) {
                            return;
                        }

                        this.currentWindow = arg5;
                        v5_1 = this.cueBuilders[arg5];
                    label_118:
                        this.currentCueBuilder = v5_1;
                        return;
                        while(true) {
                        label_80:
                            if(v2 > v1) {
                                return;
                            }

                            if(this.serviceBlockPacket.readBit()) {
                                this.cueBuilders[8 - v2].setVisibility(false);
                            }

                            ++v2;
                        }

                    label_52:
                        this.resetCueBuilders();
                        return;
                    label_54:
                        this.serviceBlockPacket.skipBits(v1);
                        return;
                        while(true) {
                        label_57:
                            if(v2 > v1) {
                                return;
                            }

                            if(this.serviceBlockPacket.readBit()) {
                                this.cueBuilders[8 - v2].reset();
                            }

                            ++v2;
                        }

                    label_91:
                        for(arg5 = 1; true; ++arg5) {
                            if(arg5 > v1) {
                                return;
                            }

                            if(this.serviceBlockPacket.readBit()) {
                                this.cueBuilders[8 - arg5].setVisibility(true);
                            }
                        }

                    label_30:
                        if(!this.currentCueBuilder.isDefined()) {
                        label_47:
                            v5 = this.serviceBlockPacket;
                        label_48:
                            v5.skipBits(v0);
                            return;
                        }

                        this.handleSetPenLocation();
                        return;
                    }
                    default: {
                        Log.w("Cea708Decoder", "Invalid C1 command: " + arg5);
                        return;
                    }
                }
            }
        }
    }

    private void handleC2Command(int arg2) {
        int v0;
        ParsableBitArray v2;
        if(arg2 <= 7) {
        }
        else {
            if(arg2 <= 15) {
                v2 = this.serviceBlockPacket;
                v0 = 8;
            }
            else if(arg2 <= 23) {
                v2 = this.serviceBlockPacket;
                v0 = 16;
            }
            else if(arg2 <= 31) {
                v2 = this.serviceBlockPacket;
                v0 = 24;
            }
            else {
                return;
            }

            v2.skipBits(v0);
        }
    }

    private void handleC3Command(int arg2) {
        int v0;
        ParsableBitArray v2;
        if(arg2 <= 135) {
            v2 = this.serviceBlockPacket;
            v0 = 32;
            goto label_4;
        }
        else if(arg2 <= 143) {
            v2 = this.serviceBlockPacket;
            v0 = 40;
        label_4:
            v2.skipBits(v0);
        }
        else if(arg2 <= 159) {
            this.serviceBlockPacket.skipBits(2);
            this.serviceBlockPacket.skipBits(this.serviceBlockPacket.readBits(6) * 8);
        }
    }

    private void handleDefineWindow(int arg15) {
        CueBuilder v1 = this.cueBuilders[arg15];
        this.serviceBlockPacket.skipBits(2);
        boolean v2 = this.serviceBlockPacket.readBit();
        boolean v3 = this.serviceBlockPacket.readBit();
        boolean v4 = this.serviceBlockPacket.readBit();
        arg15 = this.serviceBlockPacket.readBits(3);
        boolean v6 = this.serviceBlockPacket.readBit();
        int v7 = this.serviceBlockPacket.readBits(7);
        int v8 = this.serviceBlockPacket.readBits(8);
        int v11 = this.serviceBlockPacket.readBits(4);
        int v9 = this.serviceBlockPacket.readBits(4);
        this.serviceBlockPacket.skipBits(2);
        int v10 = this.serviceBlockPacket.readBits(6);
        this.serviceBlockPacket.skipBits(2);
        v1.defineWindow(v2, v3, v4, arg15, v6, v7, v8, v9, v10, v11, this.serviceBlockPacket.readBits(3), this.serviceBlockPacket.readBits(3));
    }

    private void handleG0Character(int arg2) {
        if(arg2 == 127) {
            this.currentCueBuilder.append('♫');
        }
        else {
            this.currentCueBuilder.append(((char)(arg2 & 255)));
        }
    }

    private void handleG1Character(int arg2) {
        this.currentCueBuilder.append(((char)(arg2 & 255)));
    }

    private void handleG2Character(int arg4) {
        char v0;
        CueBuilder v4;
        if(arg4 == 37) {
            v4 = this.currentCueBuilder;
            v0 = '…';
        label_87:
            v4.append(v0);
        }
        else if(arg4 != 42) {
            if(arg4 == 44) {
                v4 = this.currentCueBuilder;
                v0 = 'Œ';
            }
            else if(arg4 != 63) {
                switch(arg4) {
                    case 32: {
                        goto label_85;
                    }
                    case 33: {
                        goto label_82;
                    }
                }

                switch(arg4) {
                    case 48: {
                        goto label_79;
                    }
                    case 49: {
                        goto label_76;
                    }
                    case 50: {
                        goto label_73;
                    }
                    case 51: {
                        goto label_70;
                    }
                    case 52: {
                        goto label_67;
                    }
                    case 53: {
                        goto label_64;
                    }
                }

                switch(arg4) {
                    case 57: {
                        goto label_61;
                    }
                    case 58: {
                        goto label_58;
                    }
                }

                switch(arg4) {
                    case 60: {
                        goto label_55;
                    }
                    case 61: {
                        goto label_52;
                    }
                }

                switch(arg4) {
                    case 118: {
                        goto label_49;
                    }
                    case 119: {
                        goto label_46;
                    }
                    case 120: {
                        goto label_43;
                    }
                    case 121: {
                        goto label_40;
                    }
                    case 122: {
                        goto label_37;
                    }
                    case 123: {
                        goto label_34;
                    }
                    case 124: {
                        goto label_31;
                    }
                    case 125: {
                        goto label_28;
                    }
                    case 126: {
                        goto label_25;
                    }
                    case 127: {
                        goto label_22;
                    }
                }

                Log.w("Cea708Decoder", "Invalid G2 character: " + arg4);
                return;
            label_49:
                v4 = this.currentCueBuilder;
                v0 = '⅛';
                goto label_87;
            label_34:
                v4 = this.currentCueBuilder;
                v0 = '┐';
                goto label_87;
            label_37:
                v4 = this.currentCueBuilder;
                v0 = '│';
                goto label_87;
            label_22:
                v4 = this.currentCueBuilder;
                v0 = '┌';
                goto label_87;
            label_40:
                v4 = this.currentCueBuilder;
                v0 = '⅞';
                goto label_87;
            label_25:
                v4 = this.currentCueBuilder;
                v0 = '┘';
                goto label_87;
            label_43:
                v4 = this.currentCueBuilder;
                v0 = '⅝';
                goto label_87;
            label_28:
                v4 = this.currentCueBuilder;
                v0 = '─';
                goto label_87;
            label_46:
                v4 = this.currentCueBuilder;
                v0 = '⅜';
                goto label_87;
            label_31:
                v4 = this.currentCueBuilder;
                v0 = '└';
                goto label_87;
            label_52:
                v4 = this.currentCueBuilder;
                v0 = '℠';
                goto label_87;
            label_55:
                v4 = this.currentCueBuilder;
                v0 = 'œ';
                goto label_87;
            label_58:
                v4 = this.currentCueBuilder;
                v0 = 'š';
                goto label_87;
            label_61:
                v4 = this.currentCueBuilder;
                v0 = '™';
                goto label_87;
            label_67:
                v4 = this.currentCueBuilder;
                v0 = '”';
                goto label_87;
            label_70:
                v4 = this.currentCueBuilder;
                v0 = '“';
                goto label_87;
            label_73:
                v4 = this.currentCueBuilder;
                v0 = '’';
                goto label_87;
            label_76:
                v4 = this.currentCueBuilder;
                v0 = '‘';
                goto label_87;
            label_79:
                v4 = this.currentCueBuilder;
                v0 = '█';
                goto label_87;
            label_64:
                v4 = this.currentCueBuilder;
                v0 = '•';
                goto label_87;
            label_82:
                v4 = this.currentCueBuilder;
                v0 = ' ';
                goto label_87;
            label_85:
                v4 = this.currentCueBuilder;
                v0 = ' ';
            }
            else {
                v4 = this.currentCueBuilder;
                v0 = 'Ÿ';
            }

            goto label_87;
        }
        else {
            v4 = this.currentCueBuilder;
            v0 = 'Š';
            goto label_87;
        }
    }

    private void handleG3Character(int arg4) {
        char v0;
        CueBuilder v4;
        if(arg4 == 160) {
            v4 = this.currentCueBuilder;
            v0 = '㏄';
        }
        else {
            Log.w("Cea708Decoder", "Invalid G3 character: " + arg4);
            v4 = this.currentCueBuilder;
            v0 = '_';
        }

        v4.append(v0);
    }

    private void handleSetPenAttributes() {
        this.currentCueBuilder.setPenAttributes(this.serviceBlockPacket.readBits(4), this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBit(), this.serviceBlockPacket.readBit(), this.serviceBlockPacket.readBits(3), this.serviceBlockPacket.readBits(3));
    }

    private void handleSetPenColor() {
        int v0 = CueBuilder.getArgbColorFromCeaColor(this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2));
        int v2 = CueBuilder.getArgbColorFromCeaColor(this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2));
        this.serviceBlockPacket.skipBits(2);
        this.currentCueBuilder.setPenColor(v0, v2, CueBuilder.getArgbColorFromCeaColor(this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2), this.serviceBlockPacket.readBits(2)));
    }

    private void handleSetPenLocation() {
        this.serviceBlockPacket.skipBits(4);
        int v0 = this.serviceBlockPacket.readBits(4);
        this.serviceBlockPacket.skipBits(2);
        this.currentCueBuilder.setPenLocation(v0, this.serviceBlockPacket.readBits(6));
    }

    private void handleSetWindowAttributes() {
        int v1 = 2;
        int v6 = CueBuilder.getArgbColorFromCeaColor(this.serviceBlockPacket.readBits(v1), this.serviceBlockPacket.readBits(v1), this.serviceBlockPacket.readBits(v1), this.serviceBlockPacket.readBits(v1));
        int v0 = this.serviceBlockPacket.readBits(v1);
        int v7 = CueBuilder.getArgbColorFromCeaColor(this.serviceBlockPacket.readBits(v1), this.serviceBlockPacket.readBits(v1), this.serviceBlockPacket.readBits(v1));
        if(this.serviceBlockPacket.readBit()) {
            v0 |= 4;
        }

        boolean v8 = this.serviceBlockPacket.readBit();
        int v10 = this.serviceBlockPacket.readBits(v1);
        int v11 = this.serviceBlockPacket.readBits(v1);
        int v12 = this.serviceBlockPacket.readBits(v1);
        this.serviceBlockPacket.skipBits(8);
        this.currentCueBuilder.setWindowAttributes(v6, v7, v8, v0, v10, v11, v12);
    }

    protected boolean isNewSubtitleDataAvailable() {
        boolean v0 = this.cues != this.lastCues ? true : false;
        return v0;
    }

    private void processCurrentPacket() {
        String v5_1;
        StringBuilder v4;
        String v2_2;
        int v2 = 2;
        if(this.currentDtvCcPacket.currentIndex != this.currentDtvCcPacket.packetSize * 2 - 1) {
            Log.w("Cea708Decoder", "DtvCcPacket ended prematurely; size is " + (this.currentDtvCcPacket.packetSize * 2 - 1) + ", but current index is " + this.currentDtvCcPacket.currentIndex + " (sequence number " + this.currentDtvCcPacket.sequenceNumber + "); ignoring packet");
            return;
        }

        this.serviceBlockPacket.reset(this.currentDtvCcPacket.packetData, this.currentDtvCcPacket.currentIndex);
        int v0 = this.serviceBlockPacket.readBits(3);
        int v1_1 = this.serviceBlockPacket.readBits(5);
        if(v0 == 7) {
            this.serviceBlockPacket.skipBits(v2);
            v0 += this.serviceBlockPacket.readBits(6);
        }

        if(v1_1 == 0) {
            if(v0 != 0) {
                Log.w("Cea708Decoder", "serviceNumber is non-zero (" + v0 + ") when blockSize is 0");
            }

            return;
        }

        if(v0 != this.selectedServiceNumber) {
            return;
        }

        v0 = 0;
        while(this.serviceBlockPacket.bitsLeft() > 0) {
            v2 = 8;
            v1_1 = this.serviceBlockPacket.readBits(v2);
            int v5 = 255;
            int v6 = 159;
            int v7 = 127;
            int v8 = 31;
            if(v1_1 == 16) {
                v1_1 = this.serviceBlockPacket.readBits(v2);
                if(v1_1 <= v8) {
                    this.handleC2Command(v1_1);
                    continue;
                }
                else if(v1_1 <= v7) {
                    this.handleG2Character(v1_1);
                    goto label_88;
                }
                else if(v1_1 <= v6) {
                    this.handleC3Command(v1_1);
                    continue;
                }
                else if(v1_1 <= v5) {
                    this.handleG3Character(v1_1);
                label_88:
                    v0 = 1;
                    continue;
                }
                else {
                    v2_2 = "Cea708Decoder";
                    v4 = new StringBuilder();
                    v5_1 = "Invalid extended command: ";
                }
            }
            else if(v1_1 <= v8) {
                this.handleC0Command(v1_1);
                continue;
            }
            else if(v1_1 <= v7) {
                this.handleG0Character(v1_1);
                goto label_88;
            }
            else if(v1_1 <= v6) {
                this.handleC1Command(v1_1);
                goto label_88;
            }
            else if(v1_1 <= v5) {
                this.handleG1Character(v1_1);
                goto label_88;
            }
            else {
                v2_2 = "Cea708Decoder";
                v4 = new StringBuilder();
                v5_1 = "Invalid base command: ";
            }

            v4.append(v5_1);
            v4.append(v1_1);
            Log.w(v2_2, v4.toString());
        }

        if(v0 != 0) {
            this.cues = this.getDisplayCues();
        }
    }

    public void queueInputBuffer(SubtitleInputBuffer arg1) {
        super.queueInputBuffer(arg1);
    }

    public void release() {
        super.release();
    }

    private void resetCueBuilders() {
        int v0;
        for(v0 = 0; v0 < 8; ++v0) {
            this.cueBuilders[v0].reset();
        }
    }

    public void setPositionUs(long arg1) {
        super.setPositionUs(arg1);
    }
}


package com.google.android.exoplayer2.text.webvtt;

import android.text.Layout$Alignment;
import android.text.SpannableStringBuilder;
import android.util.Log;
import com.google.android.exoplayer2.text.Cue;

public final class WebvttCue extends Cue {
    class com.google.android.exoplayer2.text.webvtt.WebvttCue$1 {
        static {
            com.google.android.exoplayer2.text.webvtt.WebvttCue$1.$SwitchMap$android$text$Layout$Alignment = new int[Layout$Alignment.values().length];
            try {
                com.google.android.exoplayer2.text.webvtt.WebvttCue$1.$SwitchMap$android$text$Layout$Alignment[Layout$Alignment.ALIGN_NORMAL.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    com.google.android.exoplayer2.text.webvtt.WebvttCue$1.$SwitchMap$android$text$Layout$Alignment[Layout$Alignment.ALIGN_CENTER.ordinal()] = 2;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                    try {
                    label_14:
                        com.google.android.exoplayer2.text.webvtt.WebvttCue$1.$SwitchMap$android$text$Layout$Alignment[Layout$Alignment.ALIGN_OPPOSITE.ordinal()] = 3;
                        return;
                    }
                    catch(NoSuchFieldError ) {
                        return;
                    }
                }
            }
        }
    }

    public class Builder {
        private static final String TAG = "WebvttCueBuilder";
        private long endTime;
        private float line;
        private int lineAnchor;
        private int lineType;
        private float position;
        private int positionAnchor;
        private long startTime;
        private SpannableStringBuilder text;
        private Layout$Alignment textAlignment;
        private float width;

        public Builder() {
            super();
            this.reset();
        }

        public WebvttCue build() {
            if(this.position != 0f && this.positionAnchor == -2147483648) {
                this.derivePositionAnchorFromAlignment();
            }

            return new WebvttCue(this.startTime, this.endTime, this.text, this.textAlignment, this.line, this.lineType, this.lineAnchor, this.position, this.positionAnchor, this.width);
        }

        private Builder derivePositionAnchorFromAlignment() {
            int v0;
            if(this.textAlignment == null) {
                v0 = -2147483648;
                goto label_3;
            }
            else {
                switch(com.google.android.exoplayer2.text.webvtt.WebvttCue$1.$SwitchMap$android$text$Layout$Alignment[this.textAlignment.ordinal()]) {
                    case 1: {
                        goto label_20;
                    }
                    case 2: {
                        goto label_24;
                    }
                    case 3: {
                        goto label_22;
                    }
                }

                Log.w("WebvttCueBuilder", "Unrecognized alignment: " + this.textAlignment);
            label_20:
                this.positionAnchor = 0;
                return this;
            label_22:
                v0 = 2;
                goto label_3;
            label_24:
                v0 = 1;
            label_3:
                this.positionAnchor = v0;
            }

            return this;
        }

        public void reset() {
            this.startTime = 0;
            this.endTime = 0;
            this.text = null;
            this.textAlignment = null;
            this.line = 0f;
            this.lineType = -2147483648;
            this.lineAnchor = -2147483648;
            this.position = 0f;
            this.positionAnchor = -2147483648;
            this.width = 0f;
        }

        public Builder setEndTime(long arg1) {
            this.endTime = arg1;
            return this;
        }

        public Builder setLine(float arg1) {
            this.line = arg1;
            return this;
        }

        public Builder setLineAnchor(int arg1) {
            this.lineAnchor = arg1;
            return this;
        }

        public Builder setLineType(int arg1) {
            this.lineType = arg1;
            return this;
        }

        public Builder setPosition(float arg1) {
            this.position = arg1;
            return this;
        }

        public Builder setPositionAnchor(int arg1) {
            this.positionAnchor = arg1;
            return this;
        }

        public Builder setStartTime(long arg1) {
            this.startTime = arg1;
            return this;
        }

        public Builder setText(SpannableStringBuilder arg1) {
            this.text = arg1;
            return this;
        }

        public Builder setTextAlignment(Layout$Alignment arg1) {
            this.textAlignment = arg1;
            return this;
        }

        public Builder setWidth(float arg1) {
            this.width = arg1;
            return this;
        }
    }

    public final long endTime;
    public final long startTime;

    public WebvttCue(long arg14, long arg16, CharSequence arg18) {
        this(arg14, arg16, arg18, null, 0f, -2147483648, -2147483648, 0f, -2147483648, 0f);
    }

    public WebvttCue(long arg11, long arg13, CharSequence arg15, Layout$Alignment arg16, float arg17, int arg18, int arg19, float arg20, int arg21, float arg22) {
        super(arg15, arg16, arg17, arg18, arg19, arg20, arg21, arg22);
        this.startTime = arg11;
        this.endTime = arg13;
    }

    public WebvttCue(CharSequence arg7) {
        this(0, 0, arg7);
    }

    public boolean isNormalCue() {
        boolean v0 = this.line != 0f || this.position != 0f ? false : true;
        return v0;
    }
}


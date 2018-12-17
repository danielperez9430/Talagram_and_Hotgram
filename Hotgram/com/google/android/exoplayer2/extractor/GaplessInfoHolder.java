package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.metadata.Metadata$Entry;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.id3.CommentFrame;
import com.google.android.exoplayer2.metadata.id3.Id3Decoder$FramePredicate;
import com.google.android.exoplayer2.metadata.id3.InternalFrame;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class GaplessInfoHolder {
    final class com.google.android.exoplayer2.extractor.GaplessInfoHolder$1 implements FramePredicate {
        com.google.android.exoplayer2.extractor.GaplessInfoHolder$1() {
            super();
        }

        public boolean evaluate(int arg2, int arg3, int arg4, int arg5, int arg6) {
            boolean v2;
            if(arg3 != 67 || arg4 != 79) {
            label_11:
                v2 = false;
            }
            else {
                arg3 = 77;
                if(arg5 == arg3) {
                    if(arg6 != arg3 && arg2 != 2) {
                        goto label_11;
                    }

                    v2 = true;
                }
                else {
                    goto label_11;
                }
            }

            return v2;
        }
    }

    private static final Pattern GAPLESS_COMMENT_PATTERN = null;
    private static final String GAPLESS_DESCRIPTION = "iTunSMPB";
    private static final String GAPLESS_DOMAIN = "com.apple.iTunes";
    public static final FramePredicate GAPLESS_INFO_ID3_FRAME_PREDICATE;
    public int encoderDelay;
    public int encoderPadding;

    static {
        GaplessInfoHolder.GAPLESS_INFO_ID3_FRAME_PREDICATE = new com.google.android.exoplayer2.extractor.GaplessInfoHolder$1();
        GaplessInfoHolder.GAPLESS_COMMENT_PATTERN = Pattern.compile("^ [0-9a-fA-F]{8} ([0-9a-fA-F]{8}) ([0-9a-fA-F]{8})");
    }

    public GaplessInfoHolder() {
        super();
        this.encoderDelay = -1;
        this.encoderPadding = -1;
    }

    public boolean hasGaplessInfo() {
        int v1 = -1;
        boolean v0 = this.encoderDelay == v1 || this.encoderPadding == v1 ? false : true;
        return v0;
    }

    private boolean setFromComment(String arg5) {
        Matcher v5 = GaplessInfoHolder.GAPLESS_COMMENT_PATTERN.matcher(((CharSequence)arg5));
        if(v5.find()) {
            try {
                int v1 = Integer.parseInt(v5.group(1), 16);
                int v5_1 = Integer.parseInt(v5.group(2), 16);
                if(v1 <= 0 && v5_1 <= 0) {
                    return 0;
                }

                this.encoderDelay = v1;
                this.encoderPadding = v5_1;
                return 1;
            }
            catch(NumberFormatException ) {
                return 0;
            }
        }

        return 0;
    }

    public boolean setFromMetadata(Metadata arg7) {
        int v1;
        for(v1 = 0; v1 < arg7.length(); ++v1) {
            Entry v2 = arg7.get(v1);
            if((v2 instanceof CommentFrame)) {
                if(("iTunSMPB".equals(((CommentFrame)v2).description)) && (this.setFromComment(((CommentFrame)v2).text))) {
                    return 1;
                }
            }
            else if(((v2 instanceof InternalFrame)) && ("com.apple.iTunes".equals(((InternalFrame)v2).domain)) && ("iTunSMPB".equals(((InternalFrame)v2).description)) && (this.setFromComment(((InternalFrame)v2).text))) {
                return 1;
            }
        }

        return 0;
    }

    public boolean setFromXingHeaderValue(int arg2) {
        int v0 = arg2 >> 12;
        arg2 &= 4095;
        if(v0 <= 0) {
            if(arg2 > 0) {
            }
            else {
                return 0;
            }
        }

        this.encoderDelay = v0;
        this.encoderPadding = arg2;
        return 1;
    }
}


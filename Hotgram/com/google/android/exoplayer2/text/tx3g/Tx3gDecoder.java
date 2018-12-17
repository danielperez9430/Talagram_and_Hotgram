package com.google.android.exoplayer2.text.tx3g;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.nio.charset.Charset;
import java.util.List;

public final class Tx3gDecoder extends SimpleSubtitleDecoder {
    private static final char BOM_UTF16_BE = '﻿';
    private static final char BOM_UTF16_LE = '\uFFFE';
    private static final int DEFAULT_COLOR = -1;
    private static final int DEFAULT_FONT_FACE = 0;
    private static final String DEFAULT_FONT_FAMILY = "sans-serif";
    private static final float DEFAULT_VERTICAL_PLACEMENT = 0.85f;
    private static final int FONT_FACE_BOLD = 1;
    private static final int FONT_FACE_ITALIC = 2;
    private static final int FONT_FACE_UNDERLINE = 4;
    private static final int SIZE_ATOM_HEADER = 8;
    private static final int SIZE_BOM_UTF16 = 2;
    private static final int SIZE_SHORT = 2;
    private static final int SIZE_STYLE_RECORD = 12;
    private static final int SPAN_PRIORITY_HIGH = 0;
    private static final int SPAN_PRIORITY_LOW = 16711680;
    private static final String TX3G_SERIF = "Serif";
    private static final int TYPE_STYL;
    private static final int TYPE_TBOX;
    private int calculatedVideoTrackHeight;
    private boolean customVerticalPlacement;
    private int defaultColorRgba;
    private int defaultFontFace;
    private String defaultFontFamily;
    private float defaultVerticalPlacement;
    private final ParsableByteArray parsableByteArray;

    static {
        Tx3gDecoder.TYPE_STYL = Util.getIntegerCodeForString("styl");
        Tx3gDecoder.TYPE_TBOX = Util.getIntegerCodeForString("tbox");
    }

    public Tx3gDecoder(List arg2) {
        super("Tx3gDecoder");
        this.parsableByteArray = new ParsableByteArray();
        this.decodeInitializationData(arg2);
    }

    private void applyStyleRecord(ParsableByteArray arg10, SpannableStringBuilder arg11) {
        boolean v0 = arg10.bytesLeft() >= 12 ? true : false;
        Tx3gDecoder.assertTrue(v0);
        int v0_1 = arg10.readUnsignedShort();
        int v8 = arg10.readUnsignedShort();
        arg10.skipBytes(2);
        int v3 = arg10.readUnsignedByte();
        arg10.skipBytes(1);
        int v10 = arg10.readInt();
        Tx3gDecoder.attachFontFace(arg11, v3, this.defaultFontFace, v0_1, v8, 0);
        Tx3gDecoder.attachColor(arg11, v10, this.defaultColorRgba, v0_1, v8, 0);
    }

    private static void assertTrue(boolean arg1) {
        if(arg1) {
            return;
        }

        throw new SubtitleDecoderException("Unexpected subtitle format.");
    }

    private static void attachColor(SpannableStringBuilder arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
        if(arg1 != arg2) {
            arg0.setSpan(new ForegroundColorSpan(arg1 >>> 8 | (arg1 & 255) << 24), arg3, arg4, arg5 | 33);
        }
    }

    private static void attachFontFace(SpannableStringBuilder arg5, int arg6, int arg7, int arg8, int arg9, int arg10) {
        StyleSpan v3;
        if(arg6 != arg7) {
            arg7 = arg10 | 33;
            int v1 = 1;
            arg10 = (arg6 & 1) != 0 ? 1 : 0;
            int v2 = (arg6 & 2) != 0 ? 1 : 0;
            if(arg10 != 0) {
                v3 = v2 != 0 ? new StyleSpan(3) : new StyleSpan(1);
                goto label_19;
            }
            else if(v2 != 0) {
                v3 = new StyleSpan(2);
            label_19:
                arg5.setSpan(v3, arg8, arg9, arg7);
            }

            if((arg6 & 4) != 0) {
            }
            else {
                v1 = 0;
            }

            if(v1 != 0) {
                arg5.setSpan(new UnderlineSpan(), arg8, arg9, arg7);
            }

            if(v1 != 0) {
                return;
            }

            if(arg10 != 0) {
                return;
            }

            if(v2 != 0) {
                return;
            }

            arg5.setSpan(new StyleSpan(0), arg8, arg9, arg7);
        }
    }

    private static void attachFontFamily(SpannableStringBuilder arg0, String arg1, String arg2, int arg3, int arg4, int arg5) {
        if(arg1 != arg2) {
            arg0.setSpan(new TypefaceSpan(arg1), arg3, arg4, arg5 | 33);
        }
    }

    protected Subtitle decode(byte[] arg10, int arg11, boolean arg12) {
        this.parsableByteArray.reset(arg10, arg11);
        String v10 = Tx3gDecoder.readSubtitleText(this.parsableByteArray);
        if(v10.isEmpty()) {
            return Tx3gSubtitle.EMPTY;
        }

        SpannableStringBuilder v11 = new SpannableStringBuilder(((CharSequence)v10));
        Tx3gDecoder.attachFontFace(v11, this.defaultFontFace, 0, 0, v11.length(), 16711680);
        Tx3gDecoder.attachColor(v11, this.defaultColorRgba, -1, 0, v11.length(), 16711680);
        Tx3gDecoder.attachFontFamily(v11, this.defaultFontFamily, "sans-serif", 0, v11.length(), 16711680);
        float v3 = this.defaultVerticalPlacement;
        while(this.parsableByteArray.bytesLeft() >= 8) {
            int v10_1 = this.parsableByteArray.getPosition();
            int v12 = this.parsableByteArray.readInt();
            int v0 = this.parsableByteArray.readInt();
            int v2 = 2;
            int v4 = 0;
            boolean v5 = true;
            if(v0 == Tx3gDecoder.TYPE_STYL) {
                if(this.parsableByteArray.bytesLeft() >= v2) {
                }
                else {
                    v5 = false;
                }

                Tx3gDecoder.assertTrue(v5);
                v0 = this.parsableByteArray.readUnsignedShort();
                while(v4 < v0) {
                    this.applyStyleRecord(this.parsableByteArray, v11);
                    ++v4;
                }
            }
            else {
                if(v0 != Tx3gDecoder.TYPE_TBOX) {
                    goto label_73;
                }

                if(!this.customVerticalPlacement) {
                    goto label_73;
                }

                if(this.parsableByteArray.bytesLeft() >= v2) {
                    boolean v4_1 = true;
                }

                Tx3gDecoder.assertTrue(((boolean)v4));
                v3 = Util.constrainValue((((float)this.parsableByteArray.readUnsignedShort())) / (((float)this.calculatedVideoTrackHeight)), 0f, 0.95f);
            }

        label_73:
            this.parsableByteArray.setPosition(v10_1 + v12);
        }

        return new Tx3gSubtitle(new Cue(v11, null, v3, 0, 0, 0f, -2147483648, 0f));
    }

    private void decodeInitializationData(List arg6) {
        float v0 = 0.85f;
        boolean v1 = false;
        if(arg6 == null || arg6.size() != 1) {
        label_78:
            this.defaultFontFace = 0;
            this.defaultColorRgba = -1;
            this.defaultFontFamily = "sans-serif";
            this.customVerticalPlacement = false;
        label_84:
            this.defaultVerticalPlacement = v0;
        }
        else {
            if(arg6.get(0).length != 48 && arg6.get(0).length != 53) {
                goto label_78;
            }

            Object v6 = arg6.get(0);
            this.defaultFontFace = v6[24];
            this.defaultColorRgba = (v6[26] & 255) << 24 | (v6[27] & 255) << 16 | (v6[28] & 255) << 8 | v6[29] & 255;
            String v2 = "Serif".equals(Util.fromUtf8Bytes(((byte[])v6), 43, v6.length - 43)) ? "serif" : "sans-serif";
            this.defaultFontFamily = v2;
            this.calculatedVideoTrackHeight = v6[25] * 20;
            if((v6[0] & 32) != 0) {
                v1 = true;
            }

            this.customVerticalPlacement = v1;
            if(!this.customVerticalPlacement) {
                goto label_84;
            }

            this.defaultVerticalPlacement = (((float)(v6[11] & 255 | (v6[10] & 255) << 8))) / (((float)this.calculatedVideoTrackHeight));
            this.defaultVerticalPlacement = Util.constrainValue(this.defaultVerticalPlacement, 0f, 0.95f);
        }
    }

    private static String readSubtitleText(ParsableByteArray arg3) {
        String v1_1;
        int v1 = 2;
        boolean v0 = arg3.bytesLeft() >= v1 ? true : false;
        Tx3gDecoder.assertTrue(v0);
        int v0_1 = arg3.readUnsignedShort();
        if(v0_1 == 0) {
            return "";
        }

        if(arg3.bytesLeft() >= v1) {
            v1 = arg3.peekChar();
            if(v1 != 65279 && v1 != 65534) {
                goto label_22;
            }

            v1_1 = "UTF-16";
        }
        else {
        label_22:
            v1_1 = "UTF-8";
        }

        return arg3.readString(v0_1, Charset.forName(v1_1));
    }
}


package com.google.android.exoplayer2.text.ssa;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.LongArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SsaDecoder extends SimpleSubtitleDecoder {
    private static final String DIALOGUE_LINE_PREFIX = "Dialogue: ";
    private static final String FORMAT_LINE_PREFIX = "Format: ";
    private static final Pattern SSA_TIMECODE_PATTERN = null;
    private static final String TAG = "SsaDecoder";
    private int formatEndIndex;
    private int formatKeyCount;
    private int formatStartIndex;
    private int formatTextIndex;
    private final boolean haveInitializationData;

    static {
        SsaDecoder.SSA_TIMECODE_PATTERN = Pattern.compile("(?:(\\d+):)?(\\d+):(\\d+)(?::|\\.)(\\d+)");
    }

    public SsaDecoder(List arg4) {
        super("SsaDecoder");
        if(arg4 == null || (arg4.isEmpty())) {
            this.haveInitializationData = false;
        }
        else {
            this.haveInitializationData = true;
            String v0 = Util.fromUtf8Bytes(arg4.get(0));
            Assertions.checkArgument(v0.startsWith("Format: "));
            this.parseFormatLine(v0);
            this.parseHeader(new ParsableByteArray(arg4.get(1)));
        }
    }

    public SsaDecoder() {
        this(null);
    }

    protected Subtitle decode(byte[] arg1, int arg2, boolean arg3) {
        return this.decode(arg1, arg2, arg3);
    }

    protected SsaSubtitle decode(byte[] arg3, int arg4, boolean arg5) {
        ArrayList v5 = new ArrayList();
        LongArray v0 = new LongArray();
        ParsableByteArray v1 = new ParsableByteArray(arg3, arg4);
        if(!this.haveInitializationData) {
            this.parseHeader(v1);
        }

        this.parseEventBody(v1, ((List)v5), v0);
        Cue[] v3 = new Cue[v5.size()];
        v5.toArray(((Object[])v3));
        return new SsaSubtitle(v3, v0.toArray());
    }

    private void parseDialogueLine(String arg9, List arg10, LongArray arg11) {
        long v5_1;
        long v3;
        long v1;
        String[] v0_1;
        String v0;
        StringBuilder v11;
        String v10;
        if(this.formatKeyCount == 0) {
            v10 = "SsaDecoder";
            v11 = new StringBuilder();
            v0 = "Skipping dialogue line before complete format: ";
        }
        else {
            v0_1 = arg9.substring("Dialogue: ".length()).split(",", this.formatKeyCount);
            if(v0_1.length != this.formatKeyCount) {
                v10 = "SsaDecoder";
                v11 = new StringBuilder();
                v0 = "Skipping dialogue line with fewer columns than format: ";
            }
            else {
                v1 = SsaDecoder.parseTimecodeUs(v0_1[this.formatStartIndex]);
                v3 = -9223372036854775807L;
                if(v1 == v3) {
                    v10 = "SsaDecoder";
                    v11 = new StringBuilder();
                }
                else {
                    String v5 = v0_1[this.formatEndIndex];
                    if(!v5.trim().isEmpty()) {
                        v5_1 = SsaDecoder.parseTimecodeUs(v5);
                        if(v5_1 == v3) {
                            v10 = "SsaDecoder";
                            v11 = new StringBuilder();
                            goto label_33;
                        }
                    }
                    else {
                        goto label_46;
                    }

                    goto label_47;
                }

            label_33:
                v0 = "Skipping invalid timing: ";
            }
        }

        v11.append(v0);
        v11.append(arg9);
        Log.w(v10, v11.toString());
        return;
    label_46:
        v5_1 = v3;
    label_47:
        arg10.add(new Cue(v0_1[this.formatTextIndex].replaceAll("\\{.*?\\}", "").replaceAll("\\\\N", "\n").replaceAll("\\\\n", "\n")));
        arg11.add(v1);
        if(v5_1 != v3) {
            arg10.add(null);
            arg11.add(v5_1);
        }
    }

    private void parseEventBody(ParsableByteArray arg3, List arg4, LongArray arg5) {
        while(true) {
            String v0 = arg3.readLine();
            if(v0 == null) {
                return;
            }

            if(!this.haveInitializationData && (v0.startsWith("Format: "))) {
                this.parseFormatLine(v0);
                continue;
            }

            if(!v0.startsWith("Dialogue: ")) {
                continue;
            }

            this.parseDialogueLine(v0, arg4, arg5);
        }
    }

    private void parseFormatLine(String arg7) {
        int v3_1;
        String[] v7 = TextUtils.split(arg7.substring("Format: ".length()), ",");
        this.formatKeyCount = v7.length;
        int v0 = -1;
        this.formatStartIndex = v0;
        this.formatEndIndex = v0;
        this.formatTextIndex = v0;
        int v2;
        for(v2 = 0; v2 < this.formatKeyCount; ++v2) {
            String v3 = Util.toLowerInvariant(v7[v2].trim());
            int v4 = v3.hashCode();
            if(v4 != 100571) {
                if(v4 != 3556653) {
                    if(v4 != 109757538) {
                        goto label_41;
                    }
                    else if(v3.equals("start")) {
                        v3_1 = 0;
                    }
                    else {
                        goto label_41;
                    }
                }
                else if(v3.equals("text")) {
                    v3_1 = 2;
                }
                else {
                    goto label_41;
                }
            }
            else if(v3.equals("end")) {
                v3_1 = 1;
            }
            else {
            label_41:
                v3_1 = -1;
            }

            switch(v3_1) {
                case 0: {
                    this.formatStartIndex = v2;
                    break;
                }
                case 1: {
                    this.formatEndIndex = v2;
                    break;
                }
                case 2: {
                    this.formatTextIndex = v2;
                    break;
                }
                default: {
                    break;
                }
            }
        }

        if(this.formatStartIndex == v0 || this.formatEndIndex == v0 || this.formatTextIndex == v0) {
            this.formatKeyCount = 0;
        }
    }

    private void parseHeader(ParsableByteArray arg3) {
        do {
            String v0 = arg3.readLine();
            if(v0 == null) {
                return;
            }
        }
        while(!v0.startsWith("[Events]"));
    }

    public static long parseTimecodeUs(String arg8) {
        Matcher v8 = SsaDecoder.SSA_TIMECODE_PATTERN.matcher(((CharSequence)arg8));
        if(!v8.matches()) {
            return -9223372036854775807L;
        }

        return Long.parseLong(v8.group(1)) * 60 * 60 * 1000000 + Long.parseLong(v8.group(2)) * 60 * 1000000 + Long.parseLong(v8.group(3)) * 1000000 + Long.parseLong(v8.group(4)) * 10000;
    }
}


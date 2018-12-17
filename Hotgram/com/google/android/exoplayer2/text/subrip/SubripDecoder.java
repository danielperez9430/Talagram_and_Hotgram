package com.google.android.exoplayer2.text.subrip;

import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.util.LongArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SubripDecoder extends SimpleSubtitleDecoder {
    private static final String SUBRIP_TIMECODE = "(?:(\\d+):)?(\\d+):(\\d+),(\\d+)";
    private static final Pattern SUBRIP_TIMING_LINE = null;
    private static final String TAG = "SubripDecoder";
    private final StringBuilder textBuilder;

    static {
        SubripDecoder.SUBRIP_TIMING_LINE = Pattern.compile("\\s*((?:(\\d+):)?(\\d+):(\\d+),(\\d+))\\s*-->\\s*((?:(\\d+):)?(\\d+):(\\d+),(\\d+))?\\s*");
    }

    public SubripDecoder() {
        super("SubripDecoder");
        this.textBuilder = new StringBuilder();
    }

    protected Subtitle decode(byte[] arg1, int arg2, boolean arg3) {
        return this.decode(arg1, arg2, arg3);
    }

    protected SubripSubtitle decode(byte[] arg6, int arg7, boolean arg8) {
        String v3;
        StringBuilder v2;
        String v7;
        ArrayList v8 = new ArrayList();
        LongArray v0 = new LongArray();
        ParsableByteArray v1 = new ParsableByteArray(arg6, arg7);
        while(true) {
        label_6:
            String v6 = v1.readLine();
            if(v6 != null) {
                if(v6.length() == 0) {
                    continue;
                }
                else {
                    try {
                        Integer.parseInt(v6);
                    }
                    catch(NumberFormatException ) {
                        v7 = "SubripDecoder";
                        v2 = new StringBuilder();
                        v3 = "Skipping invalid index: ";
                        goto label_68;
                    }

                    v6 = v1.readLine();
                    if(v6 == null) {
                        Log.w("SubripDecoder", "Unexpected end");
                    }
                    else {
                        Matcher v7_1 = SubripDecoder.SUBRIP_TIMING_LINE.matcher(((CharSequence)v6));
                        if(v7_1.matches()) {
                            int v6_1 = 1;
                            v0.add(SubripDecoder.parseTimecode(v7_1, 1));
                            int v2_1 = 6;
                            if(!TextUtils.isEmpty(v7_1.group(v2_1))) {
                                v0.add(SubripDecoder.parseTimecode(v7_1, v2_1));
                            }
                            else {
                                v6_1 = 0;
                            }

                            this.textBuilder.setLength(0);
                            goto label_36;
                        }
                        else {
                            v7 = "SubripDecoder";
                            v2 = new StringBuilder();
                            v3 = "Skipping invalid timing: ";
                        label_68:
                            v2.append(v3);
                            v2.append(v6);
                            Log.w(v7, v2.toString());
                            continue;
                        }
                    }
                }
            }

            break;
        }

        Cue[] v6_2 = new Cue[v8.size()];
        v8.toArray(((Object[])v6_2));
        return new SubripSubtitle(v6_2, v0.toArray());
        while(true) {
        label_36:
            v7 = v1.readLine();
            if(TextUtils.isEmpty(((CharSequence)v7))) {
                break;
            }

            if(this.textBuilder.length() > 0) {
                this.textBuilder.append("<br>");
            }

            this.textBuilder.append(v7.trim());
        }

        v8.add(new Cue(Html.fromHtml(this.textBuilder.toString())));
        if(v6_1 == 0) {
            goto label_6;
        }

        v8.add(null);
        goto label_6;
    }

    private static long parseTimecode(Matcher arg8, int arg9) {
        return (Long.parseLong(arg8.group(arg9 + 1)) * 60 * 60 * 1000 + Long.parseLong(arg8.group(arg9 + 2)) * 60 * 1000 + Long.parseLong(arg8.group(arg9 + 3)) * 1000 + Long.parseLong(arg8.group(arg9 + 4))) * 1000;
    }
}


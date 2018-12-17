package com.google.ads.interactivemedia.v3.internal;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.Arrays;

@TargetApi(value=21) public final class bs {
    public static final bs a;
    private final int[] b;
    private final int c;

    static {
        bs.a = new bs(new int[]{2}, 2);
    }

    bs(int[] arg2, int arg3) {
        super();
        if(arg2 != null) {
            this.b = Arrays.copyOf(arg2, arg2.length);
            Arrays.sort(this.b);
        }
        else {
            this.b = new int[0];
        }

        this.c = arg3;
    }

    public static bs a(Context arg2) {
        return bs.a(arg2.registerReceiver(null, new IntentFilter("android.media.action.HDMI_AUDIO_PLUG")));
    }

    @SuppressLint(value={"InlinedApi"}) static bs a(Intent arg4) {
        if(arg4 != null) {
            if(arg4.getIntExtra("android.media.extra.AUDIO_PLUG_STATE", 0) == 0) {
            }
            else {
                return new bs(arg4.getIntArrayExtra("android.media.extra.ENCODINGS"), arg4.getIntExtra("android.media.extra.MAX_CHANNEL_COUNT", 0));
            }
        }

        return bs.a;
    }

    public boolean a(int arg2) {
        boolean v2 = Arrays.binarySearch(this.b, arg2) >= 0 ? true : false;
        return v2;
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if(this == (((bs)arg5))) {
            return 1;
        }

        if(!(arg5 instanceof bs)) {
            return 0;
        }

        if(!Arrays.equals(this.b, ((bs)arg5).b) || this.c != ((bs)arg5).c) {
            v0 = false;
        }
        else {
        }

        return v0;
    }

    public int hashCode() {
        return this.c + Arrays.hashCode(this.b) * 31;
    }

    public String toString() {
        int v0 = this.c;
        String v1 = Arrays.toString(this.b);
        StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 67);
        v3.append("AudioCapabilities[maxChannelCount=");
        v3.append(v0);
        v3.append(", supportedEncodings=");
        v3.append(v1);
        v3.append("]");
        return v3.toString();
    }
}


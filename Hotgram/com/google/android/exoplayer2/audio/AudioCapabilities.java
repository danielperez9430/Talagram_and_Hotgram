package com.google.android.exoplayer2.audio;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.Arrays;

@TargetApi(value=21) public final class AudioCapabilities {
    public static final AudioCapabilities DEFAULT_AUDIO_CAPABILITIES;
    private final int maxChannelCount;
    private final int[] supportedEncodings;

    static {
        AudioCapabilities.DEFAULT_AUDIO_CAPABILITIES = new AudioCapabilities(new int[]{2}, 2);
    }

    AudioCapabilities(int[] arg2, int arg3) {
        super();
        if(arg2 != null) {
            this.supportedEncodings = Arrays.copyOf(arg2, arg2.length);
            Arrays.sort(this.supportedEncodings);
        }
        else {
            this.supportedEncodings = new int[0];
        }

        this.maxChannelCount = arg3;
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if(this == (((AudioCapabilities)arg5))) {
            return 1;
        }

        if(!(arg5 instanceof AudioCapabilities)) {
            return 0;
        }

        if(!Arrays.equals(this.supportedEncodings, ((AudioCapabilities)arg5).supportedEncodings) || this.maxChannelCount != ((AudioCapabilities)arg5).maxChannelCount) {
            v0 = false;
        }
        else {
        }

        return v0;
    }

    public static AudioCapabilities getCapabilities(Context arg2) {
        return AudioCapabilities.getCapabilities(arg2.registerReceiver(null, new IntentFilter("android.media.action.HDMI_AUDIO_PLUG")));
    }

    @SuppressLint(value={"InlinedApi"}) static AudioCapabilities getCapabilities(Intent arg4) {
        if(arg4 != null) {
            if(arg4.getIntExtra("android.media.extra.AUDIO_PLUG_STATE", 0) == 0) {
            }
            else {
                return new AudioCapabilities(arg4.getIntArrayExtra("android.media.extra.ENCODINGS"), arg4.getIntExtra("android.media.extra.MAX_CHANNEL_COUNT", 0));
            }
        }

        return AudioCapabilities.DEFAULT_AUDIO_CAPABILITIES;
    }

    public int getMaxChannelCount() {
        return this.maxChannelCount;
    }

    public int hashCode() {
        return this.maxChannelCount + Arrays.hashCode(this.supportedEncodings) * 31;
    }

    public boolean supportsEncoding(int arg2) {
        boolean v2 = Arrays.binarySearch(this.supportedEncodings, arg2) >= 0 ? true : false;
        return v2;
    }

    public String toString() {
        return "AudioCapabilities[maxChannelCount=" + this.maxChannelCount + ", supportedEncodings=" + Arrays.toString(this.supportedEncodings) + "]";
    }
}


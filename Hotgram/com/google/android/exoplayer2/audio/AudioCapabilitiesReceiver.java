package com.google.android.exoplayer2.audio;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

public final class AudioCapabilitiesReceiver {
    class com.google.android.exoplayer2.audio.AudioCapabilitiesReceiver$1 {
    }

    final class HdmiAudioPlugBroadcastReceiver extends BroadcastReceiver {
        HdmiAudioPlugBroadcastReceiver(AudioCapabilitiesReceiver arg1, com.google.android.exoplayer2.audio.AudioCapabilitiesReceiver$1 arg2) {
            this(arg1);
        }

        private HdmiAudioPlugBroadcastReceiver(AudioCapabilitiesReceiver arg1) {
            AudioCapabilitiesReceiver.this = arg1;
            super();
        }

        public void onReceive(Context arg1, Intent arg2) {
            if(!this.isInitialStickyBroadcast()) {
                AudioCapabilities v1 = AudioCapabilities.getCapabilities(arg2);
                if(!v1.equals(AudioCapabilitiesReceiver.this.audioCapabilities)) {
                    AudioCapabilitiesReceiver.this.audioCapabilities = v1;
                    AudioCapabilitiesReceiver.this.listener.onAudioCapabilitiesChanged(v1);
                }
            }
        }
    }

    public interface Listener {
        void onAudioCapabilitiesChanged(AudioCapabilities arg1);
    }

    AudioCapabilities audioCapabilities;
    private final Context context;
    private final Listener listener;
    private final BroadcastReceiver receiver;

    public AudioCapabilitiesReceiver(Context arg2, Listener arg3) {
        BroadcastReceiver v2_1;
        super();
        this.context = Assertions.checkNotNull(arg2);
        this.listener = Assertions.checkNotNull(arg3);
        com.google.android.exoplayer2.audio.AudioCapabilitiesReceiver$1 v3 = null;
        if(Util.SDK_INT >= 21) {
            HdmiAudioPlugBroadcastReceiver v2 = new HdmiAudioPlugBroadcastReceiver(this, v3);
        }
        else {
            v2_1 = ((BroadcastReceiver)v3);
        }

        this.receiver = v2_1;
    }

    static Listener access$100(AudioCapabilitiesReceiver arg0) {
        return arg0.listener;
    }

    public AudioCapabilities register() {
        Intent v0 = this.receiver == null ? null : this.context.registerReceiver(this.receiver, new IntentFilter("android.media.action.HDMI_AUDIO_PLUG"));
        this.audioCapabilities = AudioCapabilities.getCapabilities(v0);
        return this.audioCapabilities;
    }

    public void unregister() {
        if(this.receiver != null) {
            this.context.unregisterReceiver(this.receiver);
        }
    }
}


package com.google.android.exoplayer2.audio;

public interface AudioListener {
    void onAudioAttributesChanged(AudioAttributes arg1);

    void onAudioSessionId(int arg1);

    void onVolumeChanged(float arg1);
}


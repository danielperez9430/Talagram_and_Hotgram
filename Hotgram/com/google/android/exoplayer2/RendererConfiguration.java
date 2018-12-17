package com.google.android.exoplayer2;

public final class RendererConfiguration {
    public static final RendererConfiguration DEFAULT;
    public final int tunnelingAudioSessionId;

    static {
        RendererConfiguration.DEFAULT = new RendererConfiguration(0);
    }

    public RendererConfiguration(int arg1) {
        super();
        this.tunnelingAudioSessionId = arg1;
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if(this == (((RendererConfiguration)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else {
                if(this.tunnelingAudioSessionId == ((RendererConfiguration)arg5).tunnelingAudioSessionId) {
                }
                else {
                    v0 = false;
                }

                return v0;
            }
        }

        return 0;
    }

    public int hashCode() {
        return this.tunnelingAudioSessionId;
    }
}


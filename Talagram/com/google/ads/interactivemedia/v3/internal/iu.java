package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import java.util.SortedSet;

public class iu implements b {
    private final SortedSet a;
    private jd b;
    private String c;
    private float d;

    public iu(jd arg2, SortedSet arg3, String arg4) {
        super();
        this.d = 0f;
        this.b = arg2;
        this.c = arg4;
        this.a = arg3;
    }

    private SortedSet a(float arg3) {
        if(this.d < arg3) {
            return this.a.subSet(Float.valueOf(this.d), Float.valueOf(arg3));
        }

        return this.a.subSet(Float.valueOf(arg3), Float.valueOf(this.d));
    }

    public void a(VideoProgressUpdate arg6) {
        if(arg6 != null) {
            if(arg6.getDuration() < 0f) {
            }
            else {
                int v0 = this.a(arg6.getCurrentTime()).isEmpty() ^ 1;
                this.d = arg6.getCurrentTime();
                if(v0 != 0) {
                    this.b.b(new jc(a.contentTimeUpdate, com.google.ads.interactivemedia.v3.internal.jc$b.contentTimeUpdate, this.c, arg6));
                }
            }
        }
    }
}


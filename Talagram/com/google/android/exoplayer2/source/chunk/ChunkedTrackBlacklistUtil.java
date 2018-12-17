package com.google.android.exoplayer2.source.chunk;

import android.util.Log;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.HttpDataSource$InvalidResponseCodeException;

public final class ChunkedTrackBlacklistUtil {
    public static final long DEFAULT_TRACK_BLACKLIST_MS = 60000;
    private static final String TAG = "ChunkedTrackBlacklist";

    private ChunkedTrackBlacklistUtil() {
        super();
    }

    public static boolean maybeBlacklistTrack(TrackSelection arg2, int arg3, Exception arg4) {
        return ChunkedTrackBlacklistUtil.maybeBlacklistTrack(arg2, arg3, arg4, 60000);
    }

    public static boolean maybeBlacklistTrack(TrackSelection arg4, int arg5, Exception arg6, long arg7) {
        if(ChunkedTrackBlacklistUtil.shouldBlacklist(arg6)) {
            boolean v0 = arg4.blacklist(arg5, arg7);
            int v6 = ((InvalidResponseCodeException)arg6).responseCode;
            if(v0) {
                Log.w("ChunkedTrackBlacklist", "Blacklisted: duration=" + arg7 + ", responseCode=" + v6 + ", format=" + arg4.getFormat(arg5));
            }
            else {
                Log.w("ChunkedTrackBlacklist", "Blacklisting failed (cannot blacklist last enabled track): responseCode=" + v6 + ", format=" + arg4.getFormat(arg5));
            }

            return v0;
        }

        return 0;
    }

    public static boolean shouldBlacklist(Exception arg2) {
        boolean v1 = false;
        if((arg2 instanceof InvalidResponseCodeException)) {
            int v2 = ((InvalidResponseCodeException)arg2).responseCode;
            if(v2 != 404 && v2 != 410) {
                return v1;
            }

            v1 = true;
        }

        return v1;
    }
}


package com.google.android.exoplayer2.source.hls.playlist;

import android.net.Uri;
import com.google.android.exoplayer2.source.MediaSourceEventListener$EventDispatcher;
import java.io.IOException;

public interface HlsPlaylistTracker {
    public interface PlaylistEventListener {
        void onPlaylistChanged();

        boolean onPlaylistError(HlsUrl arg1, boolean arg2);
    }

    public final class PlaylistResetException extends IOException {
        public final String url;

        public PlaylistResetException(String arg1) {
            super();
            this.url = arg1;
        }
    }

    public final class PlaylistStuckException extends IOException {
        public final String url;

        public PlaylistStuckException(String arg1) {
            super();
            this.url = arg1;
        }
    }

    public interface PrimaryPlaylistListener {
        void onPrimaryPlaylistRefreshed(HlsMediaPlaylist arg1);
    }

    void addListener(PlaylistEventListener arg1);

    long getInitialStartTimeUs();

    HlsMasterPlaylist getMasterPlaylist();

    HlsMediaPlaylist getPlaylistSnapshot(HlsUrl arg1);

    boolean isLive();

    boolean isSnapshotValid(HlsUrl arg1);

    void maybeThrowPlaylistRefreshError(HlsUrl arg1);

    void maybeThrowPrimaryPlaylistRefreshError();

    void refreshPlaylist(HlsUrl arg1);

    void removeListener(PlaylistEventListener arg1);

    void start(Uri arg1, EventDispatcher arg2, PrimaryPlaylistListener arg3);

    void stop();
}


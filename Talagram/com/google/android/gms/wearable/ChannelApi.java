package com.google.android.gms.wearable;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.Nullable;

@Deprecated public interface ChannelApi {
    @Deprecated public interface ChannelListener {
        public static final int CLOSE_REASON_DISCONNECTED = 1;
        public static final int CLOSE_REASON_LOCAL_CLOSE = 3;
        public static final int CLOSE_REASON_NORMAL = 0;
        public static final int CLOSE_REASON_REMOTE_CLOSE = 2;

        void onChannelClosed(Channel arg1, int arg2, int arg3);

        void onChannelOpened(Channel arg1);

        void onInputClosed(Channel arg1, int arg2, int arg3);

        void onOutputClosed(Channel arg1, int arg2, int arg3);
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface CloseReason {
    }

    @Deprecated public interface OpenChannelResult extends Result {
        @Nullable Channel getChannel();
    }

    public static final String ACTION_CHANNEL_EVENT = "com.google.android.gms.wearable.CHANNEL_EVENT";

    PendingResult addListener(GoogleApiClient arg1, ChannelListener arg2);

    PendingResult openChannel(GoogleApiClient arg1, String arg2, String arg3);

    PendingResult removeListener(GoogleApiClient arg1, ChannelListener arg2);
}


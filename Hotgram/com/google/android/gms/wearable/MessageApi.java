package com.google.android.gms.wearable;

import android.net.Uri;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Deprecated public interface MessageApi {
    @Retention(value=RetentionPolicy.SOURCE) @public interface FilterType {
    }

    @Deprecated public interface MessageListener {
        void onMessageReceived(MessageEvent arg1);
    }

    @Deprecated public interface SendMessageResult extends Result {
        int getRequestId();
    }

    public static final String ACTION_MESSAGE_RECEIVED = "com.google.android.gms.wearable.MESSAGE_RECEIVED";
    public static final int FILTER_LITERAL = 0;
    public static final int FILTER_PREFIX = 1;
    public static final int UNKNOWN_REQUEST_ID = -1;

    PendingResult addListener(GoogleApiClient arg1, MessageListener arg2);

    PendingResult addListener(GoogleApiClient arg1, MessageListener arg2, Uri arg3, int arg4);

    PendingResult removeListener(GoogleApiClient arg1, MessageListener arg2);

    PendingResult sendMessage(GoogleApiClient arg1, String arg2, String arg3, byte[] arg4);
}


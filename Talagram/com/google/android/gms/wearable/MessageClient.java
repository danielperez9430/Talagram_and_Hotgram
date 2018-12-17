package com.google.android.gms.wearable;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import com.google.android.gms.common.api.GoogleApi$Settings;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class MessageClient extends GoogleApi {
    @Retention(value=RetentionPolicy.SOURCE) @public interface FilterType {
    }

    public interface OnMessageReceivedListener extends MessageListener {
        void onMessageReceived(MessageEvent arg1);
    }

    public static final String ACTION_MESSAGE_RECEIVED = "com.google.android.gms.wearable.MESSAGE_RECEIVED";
    public static final int FILTER_LITERAL = 0;
    public static final int FILTER_PREFIX = 1;

    public MessageClient(Activity arg3, Settings arg4) {
        super(arg3, Wearable.API, null, arg4);
    }

    public MessageClient(Context arg3, Settings arg4) {
        super(arg3, Wearable.API, null, arg4);
    }

    public abstract Task addListener(OnMessageReceivedListener arg1);

    public abstract Task addListener(OnMessageReceivedListener arg1, Uri arg2, int arg3);

    public abstract Task removeListener(OnMessageReceivedListener arg1);

    public abstract Task sendMessage(String arg1, String arg2, byte[] arg3);
}


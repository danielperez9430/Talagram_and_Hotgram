package com.google.android.gms.wearable;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.api.GoogleApi$Settings;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class ChannelClient extends GoogleApi {
    public interface Channel extends Parcelable {
        String getNodeId();

        String getPath();
    }

    public class ChannelCallback {
        public static final int CLOSE_REASON_DISCONNECTED = 1;
        public static final int CLOSE_REASON_LOCAL_CLOSE = 3;
        public static final int CLOSE_REASON_NORMAL = 0;
        public static final int CLOSE_REASON_REMOTE_CLOSE = 2;

        public ChannelCallback() {
            super();
        }

        public void onChannelClosed(Channel arg1, int arg2, int arg3) {
        }

        public void onChannelOpened(Channel arg1) {
        }

        public void onInputClosed(Channel arg1, int arg2, int arg3) {
        }

        public void onOutputClosed(Channel arg1, int arg2, int arg3) {
        }
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface CloseReason {
    }

    public static final String ACTION_CHANNEL_EVENT = "com.google.android.gms.wearable.CHANNEL_EVENT";

    public ChannelClient(Activity arg3, Settings arg4) {
        super(arg3, Wearable.API, null, arg4);
    }

    public ChannelClient(Context arg3, Settings arg4) {
        super(arg3, Wearable.API, null, arg4);
    }

    public abstract Task close(Channel arg1);

    public abstract Task close(Channel arg1, int arg2);

    public abstract Task getInputStream(Channel arg1);

    public abstract Task getOutputStream(Channel arg1);

    public abstract Task openChannel(String arg1, String arg2);

    public abstract Task receiveFile(Channel arg1, Uri arg2, boolean arg3);

    public abstract Task registerChannelCallback(Channel arg1, ChannelCallback arg2);

    public abstract Task registerChannelCallback(ChannelCallback arg1);

    public abstract Task sendFile(Channel arg1, Uri arg2);

    public abstract Task sendFile(Channel arg1, Uri arg2, long arg3, long arg4);

    public abstract Task unregisterChannelCallback(Channel arg1, ChannelCallback arg2);

    public abstract Task unregisterChannelCallback(ChannelCallback arg1);
}


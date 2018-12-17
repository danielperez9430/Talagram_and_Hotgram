package com.google.android.gms.wearable;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import com.google.android.gms.common.api.GoogleApi$Settings;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class CapabilityClient extends GoogleApi {
    @Retention(value=RetentionPolicy.SOURCE) @public interface CapabilityFilterType {
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface NodeFilterType {
    }

    public interface OnCapabilityChangedListener extends CapabilityListener {
        void onCapabilityChanged(CapabilityInfo arg1);
    }

    public static final String ACTION_CAPABILITY_CHANGED = "com.google.android.gms.wearable.CAPABILITY_CHANGED";
    public static final int FILTER_ALL = 0;
    public static final int FILTER_LITERAL = 0;
    public static final int FILTER_PREFIX = 1;
    public static final int FILTER_REACHABLE = 1;

    public CapabilityClient(Activity arg3, Settings arg4) {
        super(arg3, Wearable.API, null, arg4);
    }

    public CapabilityClient(Context arg3, Settings arg4) {
        super(arg3, Wearable.API, null, arg4);
    }

    public abstract Task addListener(OnCapabilityChangedListener arg1, Uri arg2, int arg3);

    public abstract Task addListener(OnCapabilityChangedListener arg1, String arg2);

    public abstract Task addLocalCapability(String arg1);

    public abstract Task getAllCapabilities(int arg1);

    public abstract Task getCapability(String arg1, int arg2);

    public abstract Task removeListener(OnCapabilityChangedListener arg1);

    public abstract Task removeListener(OnCapabilityChangedListener arg1, String arg2);

    public abstract Task removeLocalCapability(String arg1);
}


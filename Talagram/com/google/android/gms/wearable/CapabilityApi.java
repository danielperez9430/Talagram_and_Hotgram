package com.google.android.gms.wearable;

import android.net.Uri;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;

@Deprecated public interface CapabilityApi {
    @Deprecated public interface AddLocalCapabilityResult extends Result {
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface CapabilityFilterType {
    }

    @Deprecated public interface CapabilityListener {
        void onCapabilityChanged(CapabilityInfo arg1);
    }

    @Deprecated public interface GetAllCapabilitiesResult extends Result {
        Map getAllCapabilities();
    }

    @Deprecated public interface GetCapabilityResult extends Result {
        CapabilityInfo getCapability();
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface NodeFilterType {
    }

    @Deprecated public interface RemoveLocalCapabilityResult extends Result {
    }

    public static final String ACTION_CAPABILITY_CHANGED = "com.google.android.gms.wearable.CAPABILITY_CHANGED";
    public static final int FILTER_ALL = 0;
    public static final int FILTER_LITERAL = 0;
    public static final int FILTER_PREFIX = 1;
    public static final int FILTER_REACHABLE = 1;

    PendingResult addCapabilityListener(GoogleApiClient arg1, CapabilityListener arg2, String arg3);

    PendingResult addListener(GoogleApiClient arg1, CapabilityListener arg2, Uri arg3, int arg4);

    PendingResult addLocalCapability(GoogleApiClient arg1, String arg2);

    PendingResult getAllCapabilities(GoogleApiClient arg1, int arg2);

    PendingResult getCapability(GoogleApiClient arg1, String arg2, int arg3);

    PendingResult removeCapabilityListener(GoogleApiClient arg1, CapabilityListener arg2, String arg3);

    PendingResult removeListener(GoogleApiClient arg1, CapabilityListener arg2);

    PendingResult removeLocalCapability(GoogleApiClient arg1, String arg2);
}


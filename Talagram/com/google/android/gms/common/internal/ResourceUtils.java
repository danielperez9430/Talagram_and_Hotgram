package com.google.android.gms.common.internal;

import android.net.Uri$Builder;
import android.net.Uri;

public final class ResourceUtils {
    @Deprecated public interface SignInResources {
        public static final String BUTTON_DARK_TEXT_DEFAULT = "common_google_signin_btn_text_dark_normal";

    }

    private static final Uri zzuw;

    static {
        ResourceUtils.zzuw = new Uri$Builder().scheme("android.resource").authority("com.google.android.gms").appendPath("drawable").build();
    }

    private ResourceUtils() {
        super();
    }

    public static Uri getDrawableUri(String arg1) {
        Preconditions.checkNotNull(arg1, "Resource name must not be null.");
        return ResourceUtils.zzuw.buildUpon().appendPath(arg1).build();
    }
}


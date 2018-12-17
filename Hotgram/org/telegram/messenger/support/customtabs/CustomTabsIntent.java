package org.telegram.messenger.support.customtabs;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.v4.app.c;
import android.support.v4.app.f;
import android.support.v4.content.a;
import android.widget.RemoteViews;
import java.util.ArrayList;

public final class CustomTabsIntent {
    class org.telegram.messenger.support.customtabs.CustomTabsIntent$1 {
    }

    public final class Builder {
        private ArrayList mActionButtons;
        private boolean mInstantAppsEnabled;
        private final Intent mIntent;
        private ArrayList mMenuItems;
        private Bundle mStartAnimationBundle;

        public Builder(CustomTabsSession arg4) {
            super();
            this.mIntent = new Intent("android.intent.action.VIEW");
            ArrayList v0 = null;
            this.mMenuItems = v0;
            this.mStartAnimationBundle = ((Bundle)v0);
            this.mActionButtons = v0;
            this.mInstantAppsEnabled = true;
            if(arg4 != null) {
                this.mIntent.setPackage(arg4.getComponentName().getPackageName());
            }

            Bundle v1 = new Bundle();
            String v2 = "android.support.customtabs.extra.SESSION";
            if(arg4 == null) {
            }
            else {
                IBinder v0_1 = arg4.getBinder();
            }

            f.a(v1, v2, ((IBinder)v0));
            this.mIntent.putExtras(v1);
        }

        public Builder() {
            this(null);
        }

        public Builder addDefaultShareMenuItem() {
            this.mIntent.putExtra("android.support.customtabs.extra.SHARE_MENU_ITEM", true);
            return this;
        }

        public Builder addMenuItem(String arg3, PendingIntent arg4) {
            if(this.mMenuItems == null) {
                this.mMenuItems = new ArrayList();
            }

            Bundle v0 = new Bundle();
            v0.putString("android.support.customtabs.customaction.MENU_ITEM_TITLE", arg3);
            v0.putParcelable("android.support.customtabs.customaction.PENDING_INTENT", ((Parcelable)arg4));
            this.mMenuItems.add(v0);
            return this;
        }

        @Deprecated public Builder addToolbarItem(int arg3, Bitmap arg4, String arg5, PendingIntent arg6) {
            if(this.mActionButtons == null) {
                this.mActionButtons = new ArrayList();
            }

            if(this.mActionButtons.size() < 5) {
                Bundle v0 = new Bundle();
                v0.putInt("android.support.customtabs.customaction.ID", arg3);
                v0.putParcelable("android.support.customtabs.customaction.ICON", ((Parcelable)arg4));
                v0.putString("android.support.customtabs.customaction.DESCRIPTION", arg5);
                v0.putParcelable("android.support.customtabs.customaction.PENDING_INTENT", ((Parcelable)arg6));
                this.mActionButtons.add(v0);
                return this;
            }

            throw new IllegalStateException("Exceeded maximum toolbar item count of 5");
        }

        public CustomTabsIntent build() {
            if(this.mMenuItems != null) {
                this.mIntent.putParcelableArrayListExtra("android.support.customtabs.extra.MENU_ITEMS", this.mMenuItems);
            }

            if(this.mActionButtons != null) {
                this.mIntent.putParcelableArrayListExtra("android.support.customtabs.extra.TOOLBAR_ITEMS", this.mActionButtons);
            }

            this.mIntent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", this.mInstantAppsEnabled);
            return new CustomTabsIntent(this.mIntent, this.mStartAnimationBundle, null);
        }

        public Builder enableUrlBarHiding() {
            this.mIntent.putExtra("android.support.customtabs.extra.ENABLE_URLBAR_HIDING", true);
            return this;
        }

        public Builder setActionButton(Bitmap arg4, String arg5, PendingIntent arg6, boolean arg7) {
            Bundle v0 = new Bundle();
            v0.putInt("android.support.customtabs.customaction.ID", 0);
            v0.putParcelable("android.support.customtabs.customaction.ICON", ((Parcelable)arg4));
            v0.putString("android.support.customtabs.customaction.DESCRIPTION", arg5);
            v0.putParcelable("android.support.customtabs.customaction.PENDING_INTENT", ((Parcelable)arg6));
            this.mIntent.putExtra("android.support.customtabs.extra.ACTION_BUTTON_BUNDLE", v0);
            this.mIntent.putExtra("android.support.customtabs.extra.TINT_ACTION_BUTTON", arg7);
            return this;
        }

        public Builder setActionButton(Bitmap arg2, String arg3, PendingIntent arg4) {
            return this.setActionButton(arg2, arg3, arg4, false);
        }

        public Builder setCloseButtonIcon(Bitmap arg3) {
            this.mIntent.putExtra("android.support.customtabs.extra.CLOSE_BUTTON_ICON", ((Parcelable)arg3));
            return this;
        }

        public Builder setExitAnimations(Context arg1, int arg2, int arg3) {
            this.mIntent.putExtra("android.support.customtabs.extra.EXIT_ANIMATION_BUNDLE", c.a(arg1, arg2, arg3).a());
            return this;
        }

        public Builder setInstantAppsEnabled(boolean arg1) {
            this.mInstantAppsEnabled = arg1;
            return this;
        }

        public Builder setSecondaryToolbarColor(int arg3) {
            this.mIntent.putExtra("android.support.customtabs.extra.SECONDARY_TOOLBAR_COLOR", arg3);
            return this;
        }

        public Builder setSecondaryToolbarViews(RemoteViews arg3, int[] arg4, PendingIntent arg5) {
            this.mIntent.putExtra("android.support.customtabs.extra.EXTRA_REMOTEVIEWS", ((Parcelable)arg3));
            this.mIntent.putExtra("android.support.customtabs.extra.EXTRA_REMOTEVIEWS_VIEW_IDS", arg4);
            this.mIntent.putExtra("android.support.customtabs.extra.EXTRA_REMOTEVIEWS_PENDINGINTENT", ((Parcelable)arg5));
            return this;
        }

        public Builder setShowTitle(boolean arg3) {
            this.mIntent.putExtra("android.support.customtabs.extra.TITLE_VISIBILITY", ((int)arg3));
            return this;
        }

        public Builder setStartAnimations(Context arg1, int arg2, int arg3) {
            this.mStartAnimationBundle = c.a(arg1, arg2, arg3).a();
            return this;
        }

        public Builder setToolbarColor(int arg3) {
            this.mIntent.putExtra("android.support.customtabs.extra.TOOLBAR_COLOR", arg3);
            return this;
        }
    }

    public static final String EXTRA_ACTION_BUTTON_BUNDLE = "android.support.customtabs.extra.ACTION_BUTTON_BUNDLE";
    public static final String EXTRA_CLOSE_BUTTON_ICON = "android.support.customtabs.extra.CLOSE_BUTTON_ICON";
    public static final String EXTRA_DEFAULT_SHARE_MENU_ITEM = "android.support.customtabs.extra.SHARE_MENU_ITEM";
    public static final String EXTRA_ENABLE_INSTANT_APPS = "android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS";
    public static final String EXTRA_ENABLE_URLBAR_HIDING = "android.support.customtabs.extra.ENABLE_URLBAR_HIDING";
    public static final String EXTRA_EXIT_ANIMATION_BUNDLE = "android.support.customtabs.extra.EXIT_ANIMATION_BUNDLE";
    public static final String EXTRA_MENU_ITEMS = "android.support.customtabs.extra.MENU_ITEMS";
    public static final String EXTRA_REMOTEVIEWS = "android.support.customtabs.extra.EXTRA_REMOTEVIEWS";
    public static final String EXTRA_REMOTEVIEWS_CLICKED_ID = "android.support.customtabs.extra.EXTRA_REMOTEVIEWS_CLICKED_ID";
    public static final String EXTRA_REMOTEVIEWS_PENDINGINTENT = "android.support.customtabs.extra.EXTRA_REMOTEVIEWS_PENDINGINTENT";
    public static final String EXTRA_REMOTEVIEWS_VIEW_IDS = "android.support.customtabs.extra.EXTRA_REMOTEVIEWS_VIEW_IDS";
    public static final String EXTRA_SECONDARY_TOOLBAR_COLOR = "android.support.customtabs.extra.SECONDARY_TOOLBAR_COLOR";
    public static final String EXTRA_SESSION = "android.support.customtabs.extra.SESSION";
    public static final String EXTRA_TINT_ACTION_BUTTON = "android.support.customtabs.extra.TINT_ACTION_BUTTON";
    public static final String EXTRA_TITLE_VISIBILITY_STATE = "android.support.customtabs.extra.TITLE_VISIBILITY";
    public static final String EXTRA_TOOLBAR_COLOR = "android.support.customtabs.extra.TOOLBAR_COLOR";
    public static final String EXTRA_TOOLBAR_ITEMS = "android.support.customtabs.extra.TOOLBAR_ITEMS";
    private static final String EXTRA_USER_OPT_OUT_FROM_CUSTOM_TABS = "android.support.customtabs.extra.user_opt_out";
    public static final String KEY_DESCRIPTION = "android.support.customtabs.customaction.DESCRIPTION";
    public static final String KEY_ICON = "android.support.customtabs.customaction.ICON";
    public static final String KEY_ID = "android.support.customtabs.customaction.ID";
    public static final String KEY_MENU_ITEM_TITLE = "android.support.customtabs.customaction.MENU_ITEM_TITLE";
    public static final String KEY_PENDING_INTENT = "android.support.customtabs.customaction.PENDING_INTENT";
    private static final int MAX_TOOLBAR_ITEMS = 5;
    public static final int NO_TITLE = 0;
    public static final int SHOW_PAGE_TITLE = 1;
    public static final int TOOLBAR_ACTION_BUTTON_ID;
    public final Intent intent;
    public final Bundle startAnimationBundle;

    private CustomTabsIntent(Intent arg1, Bundle arg2) {
        super();
        this.intent = arg1;
        this.startAnimationBundle = arg2;
    }

    CustomTabsIntent(Intent arg1, Bundle arg2, org.telegram.messenger.support.customtabs.CustomTabsIntent$1 arg3) {
        this(arg1, arg2);
    }

    public static int getMaxToolbarItems() {
        return 5;
    }

    public void launchUrl(Context arg2, Uri arg3) {
        this.intent.setData(arg3);
        a.a(arg2, this.intent, this.startAnimationBundle);
    }

    public static Intent setAlwaysUseBrowserUI(Intent arg2) {
        if(arg2 == null) {
            arg2 = new Intent("android.intent.action.VIEW");
        }

        arg2.addFlags(268435456);
        arg2.putExtra("android.support.customtabs.extra.user_opt_out", true);
        return arg2;
    }

    public void setUseNewTask() {
        this.intent.addFlags(268435456);
    }

    public static boolean shouldAlwaysUseBrowserUI(Intent arg2) {
        boolean v1 = false;
        if((arg2.getBooleanExtra("android.support.customtabs.extra.user_opt_out", false)) && (arg2.getFlags() & 268435456) != 0) {
            v1 = true;
        }

        return v1;
    }
}


package com.google.ads.interactivemedia.v3.api;

import java.util.Map;

public interface AdEvent {
    public interface AdEventListener {
        void onAdEvent(AdEvent arg1);
    }

    public enum AdEventType {
        public static final enum AdEventType AD_BREAK_ENDED;
        public static final enum AdEventType AD_BREAK_READY;
        public static final enum AdEventType AD_BREAK_STARTED;
        public static final enum AdEventType AD_PROGRESS;
        public static final enum AdEventType ALL_ADS_COMPLETED;
        public static final enum AdEventType CLICKED;
        public static final enum AdEventType COMPLETED;
        public static final enum AdEventType CONTENT_PAUSE_REQUESTED;
        public static final enum AdEventType CONTENT_RESUME_REQUESTED;
        public static final enum AdEventType CUEPOINTS_CHANGED;
        public static final enum AdEventType FIRST_QUARTILE;
        public static final enum AdEventType ICON_TAPPED;
        public static final enum AdEventType LOADED;
        public static final enum AdEventType LOG;
        public static final enum AdEventType MIDPOINT;
        public static final enum AdEventType PAUSED;
        public static final enum AdEventType RESUMED;
        public static final enum AdEventType SKIPPABLE_STATE_CHANGED;
        public static final enum AdEventType SKIPPED;
        public static final enum AdEventType STARTED;
        public static final enum AdEventType TAPPED;
        public static final enum AdEventType THIRD_QUARTILE;

        static {
            AdEventType.ALL_ADS_COMPLETED = new AdEventType("ALL_ADS_COMPLETED", 0);
            AdEventType.CLICKED = new AdEventType("CLICKED", 1);
            AdEventType.COMPLETED = new AdEventType("COMPLETED", 2);
            AdEventType.CUEPOINTS_CHANGED = new AdEventType("CUEPOINTS_CHANGED", 3);
            AdEventType.CONTENT_PAUSE_REQUESTED = new AdEventType("CONTENT_PAUSE_REQUESTED", 4);
            AdEventType.CONTENT_RESUME_REQUESTED = new AdEventType("CONTENT_RESUME_REQUESTED", 5);
            AdEventType.FIRST_QUARTILE = new AdEventType("FIRST_QUARTILE", 6);
            AdEventType.LOG = new AdEventType("LOG", 7);
            AdEventType.AD_BREAK_READY = new AdEventType("AD_BREAK_READY", 8);
            AdEventType.MIDPOINT = new AdEventType("MIDPOINT", 9);
            AdEventType.PAUSED = new AdEventType("PAUSED", 10);
            AdEventType.RESUMED = new AdEventType("RESUMED", 11);
            AdEventType.SKIPPABLE_STATE_CHANGED = new AdEventType("SKIPPABLE_STATE_CHANGED", 12);
            AdEventType.SKIPPED = new AdEventType("SKIPPED", 13);
            AdEventType.STARTED = new AdEventType("STARTED", 14);
            AdEventType.TAPPED = new AdEventType("TAPPED", 15);
            AdEventType.ICON_TAPPED = new AdEventType("ICON_TAPPED", 16);
            AdEventType.THIRD_QUARTILE = new AdEventType("THIRD_QUARTILE", 17);
            AdEventType.LOADED = new AdEventType("LOADED", 18);
            AdEventType.AD_PROGRESS = new AdEventType("AD_PROGRESS", 19);
            AdEventType.AD_BREAK_STARTED = new AdEventType("AD_BREAK_STARTED", 20);
            AdEventType.AD_BREAK_ENDED = new AdEventType("AD_BREAK_ENDED", 21);
            AdEventType.a = new AdEventType[]{AdEventType.ALL_ADS_COMPLETED, AdEventType.CLICKED, AdEventType.COMPLETED, AdEventType.CUEPOINTS_CHANGED, AdEventType.CONTENT_PAUSE_REQUESTED, AdEventType.CONTENT_RESUME_REQUESTED, AdEventType.FIRST_QUARTILE, AdEventType.LOG, AdEventType.AD_BREAK_READY, AdEventType.MIDPOINT, AdEventType.PAUSED, AdEventType.RESUMED, AdEventType.SKIPPABLE_STATE_CHANGED, AdEventType.SKIPPED, AdEventType.STARTED, AdEventType.TAPPED, AdEventType.ICON_TAPPED, AdEventType.THIRD_QUARTILE, AdEventType.LOADED, AdEventType.AD_PROGRESS, AdEventType.AD_BREAK_STARTED, AdEventType.AD_BREAK_ENDED};
        }

        private AdEventType(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static AdEventType valueOf(String arg1) {
            return Enum.valueOf(AdEventType.class, arg1);
        }

        public static AdEventType[] values() {
            // Method was not decompiled
        }
    }

    Ad getAd();

    Map getAdData();

    AdEventType getType();
}


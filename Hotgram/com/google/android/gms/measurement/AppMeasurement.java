package com.google.android.gms.measurement;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.v4.f.a;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.internal.zzbt;
import com.google.android.gms.measurement.internal.zzfh;
import com.google.android.gms.measurement.internal.zzfk;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Deprecated public class AppMeasurement {
    @KeepForSdk public class ConditionalUserProperty {
        @Keep @KeepForSdk public boolean mActive;
        @Keep @KeepForSdk public String mAppId;
        @Keep @KeepForSdk public long mCreationTimestamp;
        @Keep public String mExpiredEventName;
        @Keep public Bundle mExpiredEventParams;
        @Keep @KeepForSdk public String mName;
        @Keep @KeepForSdk public String mOrigin;
        @Keep @KeepForSdk public long mTimeToLive;
        @Keep public String mTimedOutEventName;
        @Keep public Bundle mTimedOutEventParams;
        @Keep @KeepForSdk public String mTriggerEventName;
        @Keep @KeepForSdk public long mTriggerTimeout;
        @Keep public String mTriggeredEventName;
        @Keep public Bundle mTriggeredEventParams;
        @Keep @KeepForSdk public long mTriggeredTimestamp;
        @Keep @KeepForSdk public Object mValue;

        public ConditionalUserProperty() {
            super();
        }

        public ConditionalUserProperty(ConditionalUserProperty arg3) {
            super();
            Preconditions.checkNotNull(arg3);
            this.mAppId = arg3.mAppId;
            this.mOrigin = arg3.mOrigin;
            this.mCreationTimestamp = arg3.mCreationTimestamp;
            this.mName = arg3.mName;
            if(arg3.mValue != null) {
                this.mValue = zzfk.zzf(arg3.mValue);
                if(this.mValue == null) {
                    this.mValue = arg3.mValue;
                }
            }

            this.mActive = arg3.mActive;
            this.mTriggerEventName = arg3.mTriggerEventName;
            this.mTriggerTimeout = arg3.mTriggerTimeout;
            this.mTimedOutEventName = arg3.mTimedOutEventName;
            if(arg3.mTimedOutEventParams != null) {
                this.mTimedOutEventParams = new Bundle(arg3.mTimedOutEventParams);
            }

            this.mTriggeredEventName = arg3.mTriggeredEventName;
            if(arg3.mTriggeredEventParams != null) {
                this.mTriggeredEventParams = new Bundle(arg3.mTriggeredEventParams);
            }

            this.mTriggeredTimestamp = arg3.mTriggeredTimestamp;
            this.mTimeToLive = arg3.mTimeToLive;
            this.mExpiredEventName = arg3.mExpiredEventName;
            if(arg3.mExpiredEventParams != null) {
                this.mExpiredEventParams = new Bundle(arg3.mExpiredEventParams);
            }
        }
    }

    @KeepForSdk public final class Event {
        @KeepForSdk public static final String AD_REWARD = "_ar";
        @KeepForSdk public static final String APP_EXCEPTION = "_ae";
        public static final String[] zzadk;
        public static final String[] zzadl;

        static {
            Event.zzadk = new String[]{"app_clear_data", "app_exception", "app_remove", "app_upgrade", "app_install", "app_update", "firebase_campaign", "error", "first_open", "first_visit", "in_app_purchase", "notification_dismiss", "notification_foreground", "notification_open", "notification_receive", "os_update", "session_start", "user_engagement", "ad_exposure", "adunit_exposure", "ad_query", "ad_activeview", "ad_impression", "ad_click", "ad_reward", "screen_view", "ga_extra_parameter"};
            Event.zzadl = new String[]{"_cd", "_ae", "_ui", "_ug", "_in", "_au", "_cmp", "_err", "_f", "_v", "_iap", "_nd", "_nf", "_no", "_nr", "_ou", "_s", "_e", "_xa", "_xu", "_aq", "_aa", "_ai", "_ac", "_ar", "_vs", "_ep"};
        }

        private Event() {
            super();
        }

        public static String zzak(String arg2) {
            return zzfk.zza(arg2, Event.zzadl, Event.zzadk);
        }

        public static String zzal(String arg2) {
            return zzfk.zza(arg2, Event.zzadk, Event.zzadl);
        }
    }

    @KeepForSdk public interface EventInterceptor {
        @KeepForSdk void interceptEvent(String arg1, String arg2, Bundle arg3, long arg4);
    }

    @KeepForSdk public interface OnEventListener {
        @KeepForSdk void onEvent(String arg1, String arg2, Bundle arg3, long arg4);
    }

    @KeepForSdk public final class Param {
        @KeepForSdk public static final String FATAL = "fatal";
        @KeepForSdk public static final String TIMESTAMP = "timestamp";
        @KeepForSdk public static final String TYPE = "type";
        public static final String[] zzadm;
        public static final String[] zzadn;

        static {
            Param.zzadm = new String[]{"firebase_conversion", "engagement_time_msec", "exposure_time", "ad_event_id", "ad_unit_id", "firebase_error", "firebase_error_value", "firebase_error_length", "firebase_event_origin", "firebase_screen", "firebase_screen_class", "firebase_screen_id", "firebase_previous_screen", "firebase_previous_class", "firebase_previous_id", "message_device_time", "message_id", "message_name", "message_time", "previous_app_version", "previous_os_version", "topic", "update_with_analytics", "previous_first_open_count", "system_app", "system_app_update", "previous_install_count", "ga_event_id", "ga_extra_params_ct", "ga_group_name", "ga_list_length", "ga_index", "ga_event_name", "campaign_info_source", "deferred_analytics_collection", "session_number", "session_id"};
            Param.zzadn = new String[]{"_c", "_et", "_xt", "_aeid", "_ai", "_err", "_ev", "_el", "_o", "_sn", "_sc", "_si", "_pn", "_pc", "_pi", "_ndt", "_nmid", "_nmn", "_nmt", "_pv", "_po", "_nt", "_uwa", "_pfo", "_sys", "_sysu", "_pin", "_eid", "_epc", "_gn", "_ll", "_i", "_en", "_cis", "_dac", "_sno", "_sid"};
        }

        private Param() {
            super();
        }

        public static String zzal(String arg2) {
            return zzfk.zza(arg2, Param.zzadm, Param.zzadn);
        }
    }

    @KeepForSdk public final class UserProperty {
        @KeepForSdk public static final String FIREBASE_LAST_NOTIFICATION = "_ln";
        public static final String[] zzado;
        public static final String[] zzadp;

        static {
            UserProperty.zzado = new String[]{"firebase_last_notification", "first_open_time", "first_visit_time", "last_deep_link_referrer", "user_id", "first_open_after_install", "lifetime_user_engagement", "google_allow_ad_personalization_signals", "session_number", "session_id"};
            UserProperty.zzadp = new String[]{"_ln", "_fot", "_fvt", "_ldl", "_id", "_fi", "_lte", "_ap", "_sno", "_sid"};
        }

        private UserProperty() {
            super();
        }

        public static String zzal(String arg2) {
            return zzfk.zza(arg2, UserProperty.zzado, UserProperty.zzadp);
        }
    }

    @KeepForSdk public static final String CRASH_ORIGIN = "crash";
    @KeepForSdk public static final String FCM_ORIGIN = "fcm";
    @KeepForSdk public static final String FIAM_ORIGIN = "fiam";
    private final zzbt zzadj;

    public AppMeasurement(zzbt arg1) {
        super();
        Preconditions.checkNotNull(arg1);
        this.zzadj = arg1;
    }

    @Keep public void beginAdUnitExposure(String arg4) {
        this.zzadj.zzgd().beginAdUnitExposure(arg4, this.zzadj.zzbx().elapsedRealtime());
    }

    @Keep @KeepForSdk public void clearConditionalUserProperty(String arg2, String arg3, Bundle arg4) {
        this.zzadj.zzge().clearConditionalUserProperty(arg2, arg3, arg4);
    }

    @Keep @VisibleForTesting protected void clearConditionalUserPropertyAs(String arg2, String arg3, String arg4, Bundle arg5) {
        this.zzadj.zzge().clearConditionalUserPropertyAs(arg2, arg3, arg4, arg5);
    }

    @Keep public void endAdUnitExposure(String arg4) {
        this.zzadj.zzgd().endAdUnitExposure(arg4, this.zzadj.zzbx().elapsedRealtime());
    }

    @Keep public long generateEventId() {
        return this.zzadj.zzgm().zzmc();
    }

    @Keep public String getAppInstanceId() {
        return this.zzadj.zzge().zzfx();
    }

    @KeepForSdk public Boolean getBoolean() {
        return this.zzadj.zzge().zzkt();
    }

    @Keep @KeepForSdk public List getConditionalUserProperties(String arg2, String arg3) {
        return this.zzadj.zzge().getConditionalUserProperties(arg2, arg3);
    }

    @Keep @VisibleForTesting protected List getConditionalUserPropertiesAs(String arg2, String arg3, String arg4) {
        return this.zzadj.zzge().getConditionalUserPropertiesAs(arg2, arg3, arg4);
    }

    @Keep public String getCurrentScreenClass() {
        return this.zzadj.zzge().getCurrentScreenClass();
    }

    @Keep public String getCurrentScreenName() {
        return this.zzadj.zzge().getCurrentScreenName();
    }

    @KeepForSdk public Double getDouble() {
        return this.zzadj.zzge().zzkx();
    }

    @Keep public String getGmpAppId() {
        return this.zzadj.zzge().getGmpAppId();
    }

    @Keep @Deprecated public static AppMeasurement getInstance(Context arg1) {
        return zzbt.zza(arg1, null).zzki();
    }

    @KeepForSdk public Integer getInteger() {
        return this.zzadj.zzge().zzkw();
    }

    @KeepForSdk public Long getLong() {
        return this.zzadj.zzge().zzkv();
    }

    @Keep @KeepForSdk public int getMaxUserProperties(String arg2) {
        this.zzadj.zzge();
        Preconditions.checkNotEmpty(arg2);
        return 25;
    }

    @KeepForSdk public String getString() {
        return this.zzadj.zzge().zzku();
    }

    @KeepForSdk public Map getUserProperties(boolean arg4) {
        List v4 = this.zzadj.zzge().zzl(arg4);
        a v0 = new a(v4.size());
        Iterator v4_1 = v4.iterator();
        while(v4_1.hasNext()) {
            Object v1 = v4_1.next();
            ((Map)v0).put(((zzfh)v1).name, ((zzfh)v1).getValue());
        }

        return ((Map)v0);
    }

    @Keep @VisibleForTesting protected Map getUserProperties(String arg2, String arg3, boolean arg4) {
        return this.zzadj.zzge().getUserProperties(arg2, arg3, arg4);
    }

    @Keep @VisibleForTesting protected Map getUserPropertiesAs(String arg2, String arg3, String arg4, boolean arg5) {
        return this.zzadj.zzge().getUserPropertiesAs(arg2, arg3, arg4, arg5);
    }

    public final void logEvent(String arg4, Bundle arg5) {
        this.zzadj.zzge().zza("app", arg4, arg5, true);
    }

    @Keep public void logEventInternal(String arg2, String arg3, Bundle arg4) {
        this.zzadj.zzge().logEvent(arg2, arg3, arg4);
    }

    @KeepForSdk public void logEventInternalNoInterceptor(String arg10, String arg11, Bundle arg12, long arg13) {
        this.zzadj.zzge().logEvent(arg10, arg11, arg12, true, false, arg13);
    }

    @KeepForSdk public void registerOnMeasurementEventListener(OnEventListener arg2) {
        this.zzadj.zzge().registerOnMeasurementEventListener(arg2);
    }

    @Keep @KeepForSdk public void setConditionalUserProperty(ConditionalUserProperty arg2) {
        this.zzadj.zzge().setConditionalUserProperty(arg2);
    }

    @Keep @VisibleForTesting protected void setConditionalUserPropertyAs(ConditionalUserProperty arg2) {
        this.zzadj.zzge().setConditionalUserPropertyAs(arg2);
    }

    @KeepForSdk public void setEventInterceptor(EventInterceptor arg2) {
        this.zzadj.zzge().setEventInterceptor(arg2);
    }

    @KeepForSdk @Deprecated public void setMeasurementEnabled(boolean arg2) {
        this.zzadj.zzge().setMeasurementEnabled(arg2);
    }

    public final void setMinimumSessionDuration(long arg2) {
        this.zzadj.zzge().setMinimumSessionDuration(arg2);
    }

    public final void setSessionTimeoutDuration(long arg2) {
        this.zzadj.zzge().setSessionTimeoutDuration(arg2);
    }

    public final void setUserProperty(String arg4, String arg5) {
        this.zzadj.zzge().zzb("app", arg4, arg5, false);
    }

    @KeepForSdk public void setUserPropertyInternal(String arg3, String arg4, Object arg5) {
        Preconditions.checkNotEmpty(arg3);
        this.zzadj.zzge().zzb(arg3, arg4, arg5, true);
    }

    @KeepForSdk public void unregisterOnMeasurementEventListener(OnEventListener arg2) {
        this.zzadj.zzge().unregisterOnMeasurementEventListener(arg2);
    }

    public final void zzd(boolean arg2) {
        this.zzadj.zzge().zzd(arg2);
    }
}


package com.google.android.gms.flags.impl;

import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import android.util.Log;
import com.google.android.gms.common.config.GservicesValue;
import com.google.android.gms.flags.Flag$BooleanFlag;
import com.google.android.gms.flags.Flag$IntegerFlag;
import com.google.android.gms.flags.Flag$LongFlag;
import com.google.android.gms.flags.Flag$StringFlag;
import com.google.android.gms.flags.Flag;
import com.google.android.gms.flags.impl.util.StrictModeUtil;
import org.json.JSONObject;

public abstract class DataUtils {
    public class BooleanUtils extends DataUtils {
        private final BooleanFlag zzack;

        BooleanUtils(BooleanFlag arg1) {
            super();
            this.zzack = arg1;
        }

        public Boolean getFromJSONObject(JSONObject arg3) {
            return Boolean.valueOf(arg3.optBoolean(this.zzack.getKey(), this.zzack.getDefault().booleanValue()));
        }

        public Object getFromJSONObject(JSONObject arg1) {
            return this.getFromJSONObject(arg1);
        }

        public Boolean getFromSharedPreferences(SharedPreferences arg3) {
            return Boolean.valueOf(arg3.getBoolean(this.zzack.getKey(), this.zzack.getDefault().booleanValue()));
        }

        public Object getFromSharedPreferences(SharedPreferences arg1) {
            return this.getFromSharedPreferences(arg1);
        }

        public static Boolean getFromSharedPreferencesNoStrict(SharedPreferences arg2, String arg3, Boolean arg4) {
            try {
                return StrictModeUtil.runWithLaxStrictMode(new zza(arg2, arg3, arg4));
            }
            catch(Exception v2) {
                arg3 = "FlagDataUtils";
                String v0 = "Flag value not available, returning default: ";
                String v2_1 = String.valueOf(v2.getMessage());
                v2_1 = v2_1.length() != 0 ? v0.concat(v2_1) : new String(v0);
                Log.w(arg3, v2_1);
                return arg4;
            }
        }

        public GservicesValue getGservicesValue() {
            return GservicesValue.value(this.zzack.getKey(), this.zzack.getDefault().booleanValue());
        }

        public void putInSharedPreferences(SharedPreferences$Editor arg2, Boolean arg3) {
            arg2.putBoolean(this.zzack.getKey(), arg3.booleanValue());
        }

        public void putInSharedPreferences(SharedPreferences$Editor arg1, Object arg2) {
            this.putInSharedPreferences(arg1, ((Boolean)arg2));
        }

        public void putStringOverrideInSharedPreferences(SharedPreferences$Editor arg2, String arg3) {
            arg2.putBoolean(this.zzack.getKey(), Boolean.parseBoolean(arg3));
        }
    }

    public class IntegerUtils extends DataUtils {
        private final IntegerFlag zzaco;

        IntegerUtils(IntegerFlag arg1) {
            super();
            this.zzaco = arg1;
        }

        public Integer getFromJSONObject(JSONObject arg3) {
            return Integer.valueOf(arg3.optInt(this.zzaco.getKey(), this.zzaco.getDefault().intValue()));
        }

        public Object getFromJSONObject(JSONObject arg1) {
            return this.getFromJSONObject(arg1);
        }

        public Integer getFromSharedPreferences(SharedPreferences arg3) {
            return Integer.valueOf(arg3.getInt(this.zzaco.getKey(), this.zzaco.getDefault().intValue()));
        }

        public Object getFromSharedPreferences(SharedPreferences arg1) {
            return this.getFromSharedPreferences(arg1);
        }

        public static Integer getFromSharedPreferencesNoStrict(SharedPreferences arg2, String arg3, Integer arg4) {
            try {
                return StrictModeUtil.runWithLaxStrictMode(new zzb(arg2, arg3, arg4));
            }
            catch(Exception v2) {
                arg3 = "FlagDataUtils";
                String v0 = "Flag value not available, returning default: ";
                String v2_1 = String.valueOf(v2.getMessage());
                v2_1 = v2_1.length() != 0 ? v0.concat(v2_1) : new String(v0);
                Log.w(arg3, v2_1);
                return arg4;
            }
        }

        public GservicesValue getGservicesValue() {
            return GservicesValue.value(this.zzaco.getKey(), this.zzaco.getDefault());
        }

        public void putInSharedPreferences(SharedPreferences$Editor arg2, Integer arg3) {
            arg2.putInt(this.zzaco.getKey(), arg3.intValue());
        }

        public void putInSharedPreferences(SharedPreferences$Editor arg1, Object arg2) {
            this.putInSharedPreferences(arg1, ((Integer)arg2));
        }

        public void putStringOverrideInSharedPreferences(SharedPreferences$Editor arg2, String arg3) {
            arg2.putInt(this.zzaco.getKey(), Integer.parseInt(arg3));
        }
    }

    public class LongUtils extends DataUtils {
        private final LongFlag zzacq;

        LongUtils(LongFlag arg1) {
            super();
            this.zzacq = arg1;
        }

        public Long getFromJSONObject(JSONObject arg4) {
            return Long.valueOf(arg4.optLong(this.zzacq.getKey(), this.zzacq.getDefault().longValue()));
        }

        public Object getFromJSONObject(JSONObject arg1) {
            return this.getFromJSONObject(arg1);
        }

        public Long getFromSharedPreferences(SharedPreferences arg4) {
            return Long.valueOf(arg4.getLong(this.zzacq.getKey(), this.zzacq.getDefault().longValue()));
        }

        public Object getFromSharedPreferences(SharedPreferences arg1) {
            return this.getFromSharedPreferences(arg1);
        }

        public static Long getFromSharedPreferencesNoStrict(SharedPreferences arg2, String arg3, Long arg4) {
            try {
                return StrictModeUtil.runWithLaxStrictMode(new zzc(arg2, arg3, arg4));
            }
            catch(Exception v2) {
                arg3 = "FlagDataUtils";
                String v0 = "Flag value not available, returning default: ";
                String v2_1 = String.valueOf(v2.getMessage());
                v2_1 = v2_1.length() != 0 ? v0.concat(v2_1) : new String(v0);
                Log.w(arg3, v2_1);
                return arg4;
            }
        }

        public GservicesValue getGservicesValue() {
            return GservicesValue.value(this.zzacq.getKey(), this.zzacq.getDefault());
        }

        public void putInSharedPreferences(SharedPreferences$Editor arg4, Long arg5) {
            arg4.putLong(this.zzacq.getKey(), arg5.longValue());
        }

        public void putInSharedPreferences(SharedPreferences$Editor arg1, Object arg2) {
            this.putInSharedPreferences(arg1, ((Long)arg2));
        }

        public void putStringOverrideInSharedPreferences(SharedPreferences$Editor arg4, String arg5) {
            arg4.putLong(this.zzacq.getKey(), Long.parseLong(arg5));
        }
    }

    public class StringUtils extends DataUtils {
        private final StringFlag zzacs;

        StringUtils(StringFlag arg1) {
            super();
            this.zzacs = arg1;
        }

        public Object getFromJSONObject(JSONObject arg1) {
            return this.getFromJSONObject(arg1);
        }

        public String getFromJSONObject(JSONObject arg3) {
            return arg3.optString(this.zzacs.getKey(), this.zzacs.getDefault());
        }

        public Object getFromSharedPreferences(SharedPreferences arg1) {
            return this.getFromSharedPreferences(arg1);
        }

        public String getFromSharedPreferences(SharedPreferences arg3) {
            return arg3.getString(this.zzacs.getKey(), this.zzacs.getDefault());
        }

        public static String getFromSharedPreferencesNoStrict(SharedPreferences arg2, String arg3, String arg4) {
            try {
                return StrictModeUtil.runWithLaxStrictMode(new zzd(arg2, arg3, arg4));
            }
            catch(Exception v2) {
                arg3 = "FlagDataUtils";
                String v0 = "Flag value not available, returning default: ";
                String v2_1 = String.valueOf(v2.getMessage());
                v2_1 = v2_1.length() != 0 ? v0.concat(v2_1) : new String(v0);
                Log.w(arg3, v2_1);
                return arg4;
            }
        }

        public GservicesValue getGservicesValue() {
            return GservicesValue.value(this.zzacs.getKey(), this.zzacs.getDefault());
        }

        public void putInSharedPreferences(SharedPreferences$Editor arg1, Object arg2) {
            this.putInSharedPreferences(arg1, ((String)arg2));
        }

        public void putInSharedPreferences(SharedPreferences$Editor arg2, String arg3) {
            arg2.putString(this.zzacs.getKey(), arg3);
        }

        public void putStringOverrideInSharedPreferences(SharedPreferences$Editor arg2, String arg3) {
            arg2.putString(this.zzacs.getKey(), String.valueOf(arg3));
        }
    }

    public DataUtils() {
        super();
    }

    public static DataUtils forFlag(Flag arg3) {
        if((arg3 instanceof BooleanFlag)) {
            return new BooleanUtils(((BooleanFlag)arg3));
        }

        if((arg3 instanceof IntegerFlag)) {
            return new IntegerUtils(((IntegerFlag)arg3));
        }

        if((arg3 instanceof LongFlag)) {
            return new LongUtils(((LongFlag)arg3));
        }

        if((arg3 instanceof StringFlag)) {
            return new StringUtils(((StringFlag)arg3));
        }

        String v1 = "Unexpected flag type: ";
        String v3 = String.valueOf(arg3.getClass().getName());
        v3 = v3.length() != 0 ? v1.concat(v3) : new String(v1);
        throw new IllegalArgumentException(v3);
    }

    public abstract Object getFromJSONObject(JSONObject arg1);

    public abstract Object getFromSharedPreferences(SharedPreferences arg1);

    public abstract GservicesValue getGservicesValue();

    public abstract void putInSharedPreferences(SharedPreferences$Editor arg1, Object arg2);

    public abstract void putStringOverrideInSharedPreferences(SharedPreferences$Editor arg1, String arg2);
}


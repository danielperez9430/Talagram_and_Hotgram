package com.google.ads.interactivemedia.v3.impl.data;

import android.util.Log;
import com.google.ads.interactivemedia.v3.internal.lx;
import com.google.ads.interactivemedia.v3.internal.lz;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

public class p {
    public class a {
        public int errorCode;
        public String errorMessage;
        public String innerError;
        public String type;

        public a() {
            super();
        }

        public Map constructMap() {
            HashMap v0 = new HashMap();
            ((Map)v0).put("type", this.type);
            ((Map)v0).put("errorCode", String.valueOf(this.errorCode));
            ((Map)v0).put("errorMessage", this.errorMessage);
            if(this.innerError != null) {
                ((Map)v0).put("innerError", this.innerError);
            }

            return ((Map)v0);
        }

        public String toString() {
            return String.format("Log[type=%s, errorCode=%s, errorMessage=%s, innerError=%s]", this.type, Integer.valueOf(this.errorCode), this.errorMessage, this.innerError);
        }
    }

    public double adBreakDuration;
    public String adBreakTime;
    public List adCuePoints;
    public b adData;
    public int adPosition;
    public long adTimeUpdateMs;
    public double bufferedTime;
    public String clickThroughUrl;
    public Map companions;
    public List cuepoints;
    public double currentTime;
    public double duration;
    public int errorCode;
    public String errorMessage;
    public String eventId;
    public String innerError;
    public SortedSet internalCuePoints;
    public String ln;
    public a logData;
    public String m;
    public String minutes;
    public boolean monitorAppLifecycle;
    public String n;
    public String queryId;
    public String seconds;
    public String streamId;
    public String streamUrl;
    public List subtitles;
    public int totalAds;
    public String translation;
    public String vastEvent;
    public String videoUrl;

    public p() {
        super();
    }

    public boolean equals(Object arg2) {
        return lx.a(this, arg2, new String[0]);
    }

    public int hashCode() {
        return lz.a(this, new String[0]);
    }

    public String toString() {
        String v6;
        String v5_1;
        StringBuilder v0 = new StringBuilder();
        v0.append("JavaScriptMsgData[");
        Field[] v1 = p.class.getFields();
        int v2 = v1.length;
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            Field v4 = v1[v3];
            try {
                Object v5 = v4.get(this);
                v0.append(v4.getName());
                v0.append(":");
                v0.append(v5);
                v0.append(",");
                goto label_27;
            }
            catch(IllegalAccessException v4_1) {
                v5_1 = "IMASDK";
                v6 = "IllegalAccessException occurred";
            }
            catch(IllegalArgumentException v4_2) {
                v5_1 = "IMASDK";
                v6 = "IllegalArgumentException occurred";
            }

            Log.e(v5_1, v6, ((Throwable)v4_1));
        label_27:
        }

        v0.append("]");
        return v0.toString();
    }
}


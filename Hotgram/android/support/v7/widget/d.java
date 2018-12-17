package android.support.v7.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

class d extends DataSetObservable {
    public final class a implements Comparable {
        public final ResolveInfo a;
        public float b;

        public a(ResolveInfo arg1) {
            super();
            this.a = arg1;
        }

        public int a(a arg2) {
            return Float.floatToIntBits(arg2.b) - Float.floatToIntBits(this.b);
        }

        public int compareTo(Object arg1) {
            return this.a(((a)arg1));
        }

        public boolean equals(Object arg5) {
            if(this == (((a)arg5))) {
                return 1;
            }

            if(arg5 == null) {
                return 0;
            }

            if(this.getClass() != arg5.getClass()) {
                return 0;
            }

            if(Float.floatToIntBits(this.b) != Float.floatToIntBits(((a)arg5).b)) {
                return 0;
            }

            return 1;
        }

        public int hashCode() {
            return Float.floatToIntBits(this.b) + 31;
        }

        public String toString() {
            return "[" + "resolveInfo:" + this.a.toString() + "; weight:" + new BigDecimal(((double)this.b)) + "]";
        }
    }

    public interface b {
        void a(Intent arg1, List arg2, List arg3);
    }

    public final class c {
        public final ComponentName a;
        public final long b;
        public final float c;

        public c(String arg1, long arg2, float arg4) {
            this(ComponentName.unflattenFromString(arg1), arg2, arg4);
        }

        public c(ComponentName arg1, long arg2, float arg4) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg4;
        }

        public boolean equals(Object arg8) {
            if(this == (((c)arg8))) {
                return 1;
            }

            if(arg8 == null) {
                return 0;
            }

            if(this.getClass() != arg8.getClass()) {
                return 0;
            }

            if(this.a == null) {
                if(((c)arg8).a != null) {
                    return 0;
                }
            }
            else if(!this.a.equals(((c)arg8).a)) {
                return 0;
            }

            if(this.b != ((c)arg8).b) {
                return 0;
            }

            if(Float.floatToIntBits(this.c) != Float.floatToIntBits(((c)arg8).c)) {
                return 0;
            }

            return 1;
        }

        public int hashCode() {
            int v0 = this.a == null ? 0 : this.a.hashCode();
            return ((v0 + 31) * 31 + (((int)(this.b ^ this.b >>> 32)))) * 31 + Float.floatToIntBits(this.c);
        }

        public String toString() {
            return "[" + "; activity:" + this.a + "; time:" + this.b + "; weight:" + new BigDecimal(((double)this.c)) + "]";
        }
    }

    public interface android.support.v7.widget.d$d {
        boolean a(d arg1, Intent arg2);
    }

    final class e extends AsyncTask {
        e(d arg1) {
            this.a = arg1;
            super();
        }

        public Void a(Object[] arg12) {
            String v0_1;
            FileOutputStream v4;
            Object v1 = arg12[0];
            Object v12 = arg12[1];
            String v3 = null;
            try {
                v4 = this.a.b.openFileOutput(((String)v12), 0);
            }
            catch(FileNotFoundException v0) {
                String v1_1 = d.a;
                Log.e(v1_1, "Error writing historical record file: " + (((String)v12)), ((Throwable)v0));
                return ((Void)v3);
            }

            XmlSerializer v12_1 = Xml.newSerializer();
            try {
                v12_1.setOutput(((OutputStream)v4), v3);
                v12_1.startDocument("UTF-8", Boolean.valueOf(true));
                v12_1.startTag(v3, "historical-records");
                int v5 = ((List)v1).size();
                int v6;
                for(v6 = 0; v6 < v5; ++v6) {
                    Object v7 = ((List)v1).remove(0);
                    v12_1.startTag(v3, "historical-record");
                    v12_1.attribute(v3, "activity", ((c)v7).a.flattenToString());
                    v12_1.attribute(v3, "time", String.valueOf(((c)v7).b));
                    v12_1.attribute(v3, "weight", String.valueOf(((c)v7).c));
                    v12_1.endTag(v3, "historical-record");
                }

                v12_1.endTag(v3, "historical-records");
                v12_1.endDocument();
            }
            catch(IllegalArgumentException v12_2) {
                goto label_78;
            }
            catch(Throwable v12_3) {
                goto label_93;
            }
            catch(IllegalStateException v12_4) {
                goto label_63;
            }
            catch(IOException v12_5) {
                goto label_48;
            }

            this.a.d = true;
            if(v4 == null) {
                goto label_92;
            }

            goto label_43;
            try {
            label_78:
                v0_1 = d.a;
                Log.e(v0_1, "Error writing historical record file: " + this.a.c, ((Throwable)v12_2));
            }
            catch(Throwable v12_3) {
                goto label_93;
            }

            this.a.d = true;
            if(v4 == null) {
                goto label_92;
            }

            goto label_43;
            try {
            label_63:
                v0_1 = d.a;
                Log.e(v0_1, "Error writing historical record file: " + this.a.c, ((Throwable)v12_4));
            }
            catch(Throwable v12_3) {
                goto label_93;
            }

            this.a.d = true;
            if(v4 == null) {
                goto label_92;
            }

            goto label_43;
            try {
            label_48:
                v0_1 = d.a;
                Log.e(v0_1, "Error writing historical record file: " + this.a.c, ((Throwable)v12_5));
            }
            catch(Throwable v12_3) {
                goto label_93;
            }

            this.a.d = true;
            if(v4 == null) {
                goto label_92;
            }

            try {
            label_43:
                v4.close();
                goto label_92;
            }
            catch(IOException ) {
            label_92:
                return ((Void)v3);
            }

        label_93:
            this.a.d = true;
            if(v4 != null) {
                try {
                    v4.close();
                    goto label_97;
                }
                catch(IOException ) {
                label_97:
                    throw v12_3;
                }
            }

            goto label_97;
        }

        public Object doInBackground(Object[] arg1) {
            return this.a(arg1);
        }
    }

    static final String a = "d";
    final Context b;
    final String c;
    boolean d;
    private static final Object e;
    private static final Map f;
    private final Object g;
    private final List h;
    private final List i;
    private Intent j;
    private b k;
    private int l;
    private boolean m;
    private boolean n;
    private boolean o;
    private android.support.v7.widget.d$d p;

    static {
        d.e = new Object();
        d.f = new HashMap();
    }

    public int a() {
        Object v0 = this.g;
        __monitor_enter(v0);
        try {
            this.d();
            __monitor_exit(v0);
            return this.h.size();
        label_8:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_8;
        }

        throw v1;
    }

    public ResolveInfo a(int arg3) {
        Object v0 = this.g;
        __monitor_enter(v0);
        try {
            this.d();
            __monitor_exit(v0);
            return this.h.get(arg3).a;
        label_9:
            __monitor_exit(v0);
        }
        catch(Throwable v3) {
            goto label_9;
        }

        throw v3;
    }

    public int a(ResolveInfo arg6) {
        Object v0 = this.g;
        __monitor_enter(v0);
        try {
            this.d();
            List v1 = this.h;
            int v2 = v1.size();
            int v3;
            for(v3 = 0; v3 < v2; ++v3) {
                if(v1.get(v3).a == arg6) {
                    __monitor_exit(v0);
                    return v3;
                }
            }

            __monitor_exit(v0);
            return -1;
        label_18:
            __monitor_exit(v0);
        }
        catch(Throwable v6) {
            goto label_18;
        }

        throw v6;
    }

    private boolean a(c arg2) {
        boolean v2 = this.i.add(arg2);
        if(v2) {
            this.n = true;
            this.h();
            this.c();
            this.e();
            this.notifyChanged();
        }

        return v2;
    }

    public ResolveInfo b() {
        Object v0 = this.g;
        __monitor_enter(v0);
        try {
            this.d();
            if(!this.h.isEmpty()) {
                __monitor_exit(v0);
                return this.h.get(0).a;
            }

            __monitor_exit(v0);
            ResolveInfo v0_1 = null;
            return v0_1;
        label_16:
            __monitor_exit(v0);
        }
        catch(Throwable v1) {
            goto label_16;
        }

        throw v1;
    }

    public Intent b(int arg7) {
        Object v0 = this.g;
        __monitor_enter(v0);
        try {
            Intent v2 = null;
            if(this.j == null) {
                __monitor_exit(v0);
                return v2;
            }

            this.d();
            Object v7_1 = this.h.get(arg7);
            ComponentName v1 = new ComponentName(((a)v7_1).a.activityInfo.packageName, ((a)v7_1).a.activityInfo.name);
            Intent v7_2 = new Intent(this.j);
            v7_2.setComponent(v1);
            if(this.p != null && (this.p.a(this, new Intent(v7_2)))) {
                __monitor_exit(v0);
                return v2;
            }

            this.a(new c(v1, System.currentTimeMillis(), 1f));
            __monitor_exit(v0);
            return v7_2;
        label_39:
            __monitor_exit(v0);
        }
        catch(Throwable v7) {
            goto label_39;
        }

        throw v7;
    }

    public void c(int arg6) {
        Object v0 = this.g;
        __monitor_enter(v0);
        try {
            this.d();
            Object v6_1 = this.h.get(arg6);
            Object v1 = this.h.get(0);
            float v1_1 = v1 != null ? ((a)v1).b - ((a)v6_1).b + 5f : 1f;
            this.a(new c(new ComponentName(((a)v6_1).a.activityInfo.packageName, ((a)v6_1).a.activityInfo.name), System.currentTimeMillis(), v1_1));
            __monitor_exit(v0);
            return;
        label_31:
            __monitor_exit(v0);
        }
        catch(Throwable v6) {
            goto label_31;
        }

        throw v6;
    }

    private void c() {
        if(this.m) {
            if(!this.n) {
                return;
            }

            this.n = false;
            if(!TextUtils.isEmpty(this.c)) {
                new e(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[]{new ArrayList(this.i), this.c});
            }

            return;
        }

        throw new IllegalStateException("No preceding call to #readHistoricalData");
    }

    private void d() {
        int v0 = this.f() | this.g();
        this.h();
        if(v0 != 0) {
            this.e();
            this.notifyChanged();
        }
    }

    private boolean e() {
        if(this.k != null && this.j != null && !this.h.isEmpty() && !this.i.isEmpty()) {
            this.k.a(this.j, this.h, Collections.unmodifiableList(this.i));
            return 1;
        }

        return 0;
    }

    private boolean f() {
        int v1 = 0;
        if((this.o) && this.j != null) {
            this.o = false;
            this.h.clear();
            List v0 = this.b.getPackageManager().queryIntentActivities(this.j, 0);
            int v2 = v0.size();
            while(v1 < v2) {
                this.h.add(new a(v0.get(v1)));
                ++v1;
            }

            return 1;
        }

        return 0;
    }

    private boolean g() {
        if((this.d) && (this.n) && !TextUtils.isEmpty(this.c)) {
            this.d = false;
            this.m = true;
            this.i();
            return 1;
        }

        return 0;
    }

    private void h() {
        int v0 = this.i.size() - this.l;
        if(v0 <= 0) {
            return;
        }

        this.n = true;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            this.i.remove(0);
        }
    }

    private void i() {
        String v2_2;
        List v2_1;
        XmlPullParser v1_3;
        FileInputStream v0;
        try {
            v0 = this.b.openFileInput(this.c);
        }
        catch(FileNotFoundException ) {
            return;
        }

        try {
            v1_3 = Xml.newPullParser();
            v1_3.setInput(((InputStream)v0), "UTF-8");
            int v2;
            for(v2 = 0; v2 != 1; v2 = v1_3.next()) {
                if(v2 == 2) {
                    break;
                }
            }

            if("historical-records".equals(v1_3.getName())) {
                v2_1 = this.i;
                v2_1.clear();
                goto label_19;
            }

            throw new XmlPullParserException("Share records file does not start with historical-records tag.");
            while(true) {
            label_19:
                int v4 = v1_3.next();
                if(v4 != 1) {
                    break;
                }

                goto label_21;
            }
        }
        catch(XmlPullParserException v1) {
            goto label_69;
        }
        catch(Throwable v1_1) {
            goto label_81;
        }
        catch(IOException v1_2) {
            goto label_57;
        }

        if(v4 == 3) {
            goto label_19;
        }

        if(v4 == 4) {
            goto label_19;
        }

        try {
            if("historical-record".equals(v1_3.getName())) {
                v2_1.add(new c(v1_3.getAttributeValue(null, "activity"), Long.parseLong(v1_3.getAttributeValue(null, "time")), Float.parseFloat(v1_3.getAttributeValue(null, "weight"))));
                goto label_19;
            }

            throw new XmlPullParserException("Share records file not well-formed.");
        }
        catch(XmlPullParserException v1) {
            goto label_69;
        }
        catch(Throwable v1_1) {
            goto label_81;
        }
        catch(IOException v1_2) {
            goto label_57;
        }

    label_21:
        if(v0 == null) {
            return;
        }

        goto label_22;
        try {
        label_69:
            v2_2 = d.a;
            Log.e(v2_2, "Error reading historical recrod file: " + this.c, ((Throwable)v1));
            if(v0 == null) {
                return;
            }
        }
        catch(Throwable v1_1) {
            goto label_81;
        }

        goto label_22;
        try {
        label_57:
            v2_2 = d.a;
            Log.e(v2_2, "Error reading historical recrod file: " + this.c, ((Throwable)v1_2));
            if(v0 == null) {
                return;
            }
        }
        catch(Throwable v1_1) {
            goto label_81;
        }

        try {
        label_22:
            v0.close();
            return;
        }
        catch(IOException ) {
            return;
        }

    label_81:
        if(v0 != null) {
            try {
                v0.close();
                goto label_83;
            }
            catch(IOException ) {
            label_83:
                throw v1_1;
            }
        }

        goto label_83;
    }
}


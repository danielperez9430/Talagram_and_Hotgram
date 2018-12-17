package android.support.v4.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface$Builder;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.support.v4.content.a.c$c;
import android.support.v4.d.b$b;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Map;

public class f extends d {
    protected final Class a;
    protected final Constructor b;
    protected final Method c;
    protected final Method d;
    protected final Method e;
    protected final Method f;
    protected final Method g;

    public f() {
        Class v7_1;
        Class v3_2;
        Class v2_1;
        Class v0;
        Method v6;
        Method v5;
        Method v4;
        super();
        try {
            Class v1_1 = this.a();
            Constructor v2 = this.a(v1_1);
            Method v3 = this.b(v1_1);
            v4 = this.c(v1_1);
            v5 = this.d(v1_1);
            v6 = this.e(v1_1);
            Method v7 = this.f(v1_1);
            v0 = v1_1;
        }
        catch(NoSuchMethodException v1) {
            Log.e("TypefaceCompatApi26Impl", "Unable to collect necessary methods for class " + v1.getClass().getName(), ((Throwable)v1));
            v2_1 = v0;
            v3_2 = v2_1;
            Class v4_1 = v3_2;
            Class v5_1 = v4_1;
            Class v6_1 = v5_1;
            v7_1 = v6_1;
        }

        this.a = v0;
        this.b = ((Constructor)v2_1);
        this.c = ((Method)v3_2);
        this.d = v4;
        this.e = v5;
        this.f = v6;
        this.g = ((Method)v7_1);
    }

    protected Class a() {
        return Class.forName("android.graphics.FontFamily");
    }

    protected Constructor a(Class arg2) {
        return arg2.getConstructor();
    }

    private boolean a(Context arg4, Object arg5, String arg6, int arg7, int arg8, int arg9, FontVariationAxis[] arg10) {
        try {
            return this.c.invoke(arg5, arg4.getAssets(), arg6, Integer.valueOf(0), Boolean.valueOf(false), Integer.valueOf(arg7), Integer.valueOf(arg8), Integer.valueOf(arg9), arg10).booleanValue();
        }
        catch(InvocationTargetException v4) {
            throw new RuntimeException(((Throwable)v4));
        }
    }

    private boolean a(Object arg4, ByteBuffer arg5, int arg6, int arg7, int arg8) {
        try {
            return this.d.invoke(arg4, arg5, Integer.valueOf(arg6), null, Integer.valueOf(arg7), Integer.valueOf(arg8)).booleanValue();
        }
        catch(InvocationTargetException v4) {
            throw new RuntimeException(((Throwable)v4));
        }
    }

    public Typeface a(Context arg9, Resources arg10, int arg11, String arg12, int arg13) {
        if(!this.b()) {
            return super.a(arg9, arg10, arg11, arg12, arg13);
        }

        Object v10 = this.c();
        Typeface v11 = null;
        if(!this.a(arg9, v10, arg12, 0, -1, -1, null)) {
            this.c(v10);
            return v11;
        }

        if(!this.b(v10)) {
            return v11;
        }

        return this.a(v10);
    }

    protected Typeface a(Object arg6) {
        try {
            Object v0 = Array.newInstance(this.a, 1);
            Array.set(v0, 0, arg6);
            return this.g.invoke(null, v0, Integer.valueOf(-1), Integer.valueOf(-1));
        }
        catch(InvocationTargetException v6) {
            throw new RuntimeException(((Throwable)v6));
        }
    }

    public Typeface a(Context arg12, CancellationSignal arg13, b[] arg14, int arg15) {
        Typeface v13_1;
        ParcelFileDescriptor v12_1;
        Typeface v2 = null;
        if(arg14.length < 1) {
            return v2;
        }

        if(this.b()) {
            goto label_43;
        }

        b v14 = this.a(arg14, arg15);
        ContentResolver v12 = arg12.getContentResolver();
        try {
            v12_1 = v12.openFileDescriptor(v14.a(), "r", arg13);
            if(v12_1 != null) {
                goto label_16;
            }

            if(v12_1 != null) {
                v12_1.close();
            }
        }
        catch(IOException ) {
            return v2;
        }

        return v2;
        try {
        label_16:
            v13_1 = new Typeface$Builder(v12_1.getFileDescriptor()).setWeight(v14.c()).setItalic(v14.d()).build();
            if(v12_1 == null) {
                return v13_1;
            }

            goto label_25;
        }
        catch(Throwable v13) {
            v14_1 = ((Throwable)v2);
        }
        catch(Throwable v13) {
            try {
                throw v13;
            }
            catch(Throwable v14_1) {
                Throwable v10 = v14_1;
                v14_1 = v13;
                v13 = v10;
            }
        }

        if(v12_1 != null) {
            if(v14_1 != null) {
                try {
                    v12_1.close();
                    goto label_41;
                }
                catch(Throwable ) {
                    goto label_41;
                }
                catch(IOException ) {
                    return v2;
                }
            }
            else {
                try {
                    v12_1.close();
                label_41:
                    throw v13;
                label_25:
                    v12_1.close();
                    return v13_1;
                }
                catch(IOException ) {
                    return v2;
                }
            }
        }

        goto label_41;
        return v13_1;
    label_43:
        Map v12_2 = android.support.v4.d.b.a(arg12, arg14, arg13);
        Object v13_2 = this.c();
        int v0 = arg14.length;
        int v3 = 0;
        int v9;
        for(v9 = 0; v9 < v0; ++v9) {
            b v4 = arg14[v9];
            Object v5 = v12_2.get(v4.a());
            if(v5 == null) {
            }
            else if(!this.a(v13_2, ((ByteBuffer)v5), v4.b(), v4.c(), v4.d())) {
                this.c(v13_2);
                return v2;
            }
            else {
                v3 = 1;
            }
        }

        if(v3 == 0) {
            this.c(v13_2);
            return v2;
        }

        if(!this.b(v13_2)) {
            return v2;
        }

        return Typeface.create(this.a(v13_2), arg15);
    }

    public Typeface a(Context arg11, android.support.v4.content.a.c$b arg12, Resources arg13, int arg14) {
        Typeface v9;
        if(!this.b()) {
            return super.a(arg11, arg12, arg13, arg14);
        }

        Object v13 = this.c();
        c[] v12 = arg12.a();
        arg14 = v12.length;
        int v8;
        for(v8 = 0; true; ++v8) {
            v9 = null;
            if(v8 >= arg14) {
                break;
            }

            if(!this.a(arg11, v13, v12[v8].a(), v12[v8].e(), v12[v8].b(), v12[v8].c(), FontVariationAxis.fromFontVariationSettings(v12[v8].d()))) {
                this.c(v13);
                return v9;
            }
        }

        if(!this.b(v13)) {
            return v9;
        }

        return this.a(v13);
    }

    protected Method b(Class arg5) {
        return arg5.getMethod("addFontFromAssetManager", AssetManager.class, String.class, Integer.TYPE, Boolean.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, FontVariationAxis[].class);
    }

    private boolean b() {
        if(this.c == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }

        boolean v0 = this.c != null ? true : false;
        return v0;
    }

    private boolean b(Object arg3) {
        try {
            return this.e.invoke(arg3).booleanValue();
        }
        catch(InvocationTargetException v3) {
            throw new RuntimeException(((Throwable)v3));
        }
    }

    protected Method c(Class arg5) {
        return arg5.getMethod("addFontFromBuffer", ByteBuffer.class, Integer.TYPE, FontVariationAxis[].class, Integer.TYPE, Integer.TYPE);
    }

    private Object c() {
        try {
            return this.b.newInstance();
        }
        catch(InvocationTargetException v0) {
            throw new RuntimeException(((Throwable)v0));
        }
    }

    private void c(Object arg3) {
        try {
            this.f.invoke(arg3);
            return;
        }
        catch(InvocationTargetException v3) {
            throw new RuntimeException(((Throwable)v3));
        }
    }

    protected Method d(Class arg3) {
        return arg3.getMethod("freeze");
    }

    protected Method e(Class arg3) {
        return arg3.getMethod("abortCreation");
    }

    protected Method f(Class arg6) {
        Method v6 = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", Array.newInstance(arg6, 1).getClass(), Integer.TYPE, Integer.TYPE);
        v6.setAccessible(true);
        return v6;
    }
}


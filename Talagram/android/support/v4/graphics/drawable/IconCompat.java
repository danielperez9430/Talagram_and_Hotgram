package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.Bitmap$CompressFormat;
import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff$Mode;
import android.graphics.Shader$TileMode;
import android.graphics.Shader;
import android.graphics.drawable.Icon;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class IconCompat extends CustomVersionedParcelable {
    public int a;
    Object b;
    public byte[] c;
    public Parcelable d;
    public int e;
    public int f;
    public ColorStateList g;
    static final PorterDuff$Mode h;
    PorterDuff$Mode i;
    public String j;

    static {
        IconCompat.h = PorterDuff$Mode.SRC_IN;
    }

    public IconCompat() {
        super();
        this.g = null;
        this.i = IconCompat.h;
    }

    private IconCompat(int arg2) {
        super();
        this.g = null;
        this.i = IconCompat.h;
        this.a = arg2;
    }

    public void a(boolean arg4) {
        this.j = this.i.name();
        int v0 = this.a;
        if(v0 != -1) {
            switch(v0) {
                case 2: {
                    goto label_13;
                }
                case 3: {
                    goto label_11;
                }
                case 4: {
                    goto label_8;
                }
                case 1: 
                case 5: {
                    goto label_19;
                }
            }

            return;
        label_19:
            if(arg4) {
                Object v4 = this.b;
                ByteArrayOutputStream v0_1 = new ByteArrayOutputStream();
                ((Bitmap)v4).compress(Bitmap$CompressFormat.PNG, 90, ((OutputStream)v0_1));
                byte[] v4_1 = v0_1.toByteArray();
                goto label_17;
            label_8:
                String v4_2 = this.b.toString();
                goto label_14;
            label_11:
                v4 = this.b;
                goto label_17;
            label_13:
                v4 = this.b;
            label_14:
                v4_1 = ((String)v4).getBytes(Charset.forName("UTF-16"));
            label_17:
                this.c = ((byte[])v4);
            }
            else {
                goto label_29;
            }
        }
        else if(!arg4) {
        label_29:
            this.d = this.b;
        }
        else {
            goto label_32;
        }

        return;
    label_32:
        throw new IllegalArgumentException("Can\'t serialize Icon created with IconCompat#createFromIcon");
    }

    static Bitmap a(Bitmap arg9, boolean arg10) {
        int v0 = ((int)((((float)Math.min(arg9.getWidth(), arg9.getHeight()))) * 0.666667f));
        Bitmap v1 = Bitmap.createBitmap(v0, v0, Bitmap$Config.ARGB_8888);
        Canvas v2 = new Canvas(v1);
        Paint v3 = new Paint(3);
        float v4 = ((float)v0);
        float v5 = 0.5f * v4;
        float v6 = 0.916667f * v5;
        if(arg10) {
            float v10 = 0.010417f * v4;
            v3.setColor(0);
            v3.setShadowLayer(v10, 0f, v4 * 0.020833f, 1023410176);
            v2.drawCircle(v5, v5, v6, v3);
            v3.setShadowLayer(v10, 0f, 0f, 503316480);
            v2.drawCircle(v5, v5, v6, v3);
            v3.clearShadowLayer();
        }

        v3.setColor(-16777216);
        BitmapShader v10_1 = new BitmapShader(arg9, Shader$TileMode.CLAMP, Shader$TileMode.CLAMP);
        Matrix v4_1 = new Matrix();
        v4_1.setTranslate(((float)(-(arg9.getWidth() - v0) / 2)), ((float)(-(arg9.getHeight() - v0) / 2)));
        v10_1.setLocalMatrix(v4_1);
        v3.setShader(((Shader)v10_1));
        v2.drawCircle(v5, v5, v6, v3);
        v2.setBitmap(null);
        return v1;
    }

    public static IconCompat a(Bitmap arg2) {
        if(arg2 != null) {
            IconCompat v0 = new IconCompat(1);
            v0.b = arg2;
            return v0;
        }

        throw new IllegalArgumentException("Bitmap must not be null.");
    }

    private static String a(int arg0) {
        switch(arg0) {
            case 1: {
                return "BITMAP";
            }
            case 2: {
                return "RESOURCE";
            }
            case 3: {
                return "DATA";
            }
            case 4: {
                return "URI";
            }
            case 5: {
                return "BITMAP_MASKABLE";
            }
        }

        return "UNKNOWN";
    }

    private static String a(Icon arg5) {
        if(Build$VERSION.SDK_INT >= 28) {
            return arg5.getResPackage();
        }

        String v0 = null;
        try {
            return arg5.getClass().getMethod("getResPackage").invoke(arg5);
        }
        catch(NoSuchMethodException v5) {
            Log.e("IconCompat", "Unable to get icon package", ((Throwable)v5));
            return v0;
        }
    }

    public String a() {
        int v1 = -1;
        if(this.a == v1 && Build$VERSION.SDK_INT >= 23) {
            return IconCompat.a(this.b);
        }

        if(this.a == 2) {
            return this.b.split(":", v1)[0];
        }

        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("called getResPackage() on ");
        v1_1.append(this);
        throw new IllegalStateException(v1_1.toString());
    }

    private static int b(Icon arg4) {
        if(Build$VERSION.SDK_INT >= 28) {
            return arg4.getResId();
        }

        try {
            return arg4.getClass().getMethod("getResId").invoke(arg4).intValue();
        }
        catch(NoSuchMethodException v4) {
            Log.e("IconCompat", "Unable to get icon resource", ((Throwable)v4));
            return 0;
        }
    }

    public int b() {
        if(this.a == -1 && Build$VERSION.SDK_INT >= 23) {
            return IconCompat.b(this.b);
        }

        if(this.a == 2) {
            return this.e;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("called getResId() on ");
        v1.append(this);
        throw new IllegalStateException(v1.toString());
    }

    public Icon c() {
        int v0 = this.a;
        if(v0 == -1) {
            goto label_42;
        }

        switch(v0) {
            case 1: {
                goto label_30;
            }
            case 2: {
                goto label_26;
            }
            case 3: {
                goto label_21;
            }
            case 4: {
                goto label_18;
            }
            case 5: {
                goto label_8;
            }
        }

        throw new IllegalArgumentException("Unknown type");
    label_18:
        Icon v0_1 = Icon.createWithContentUri(this.b);
        goto label_32;
    label_21:
        v0_1 = Icon.createWithData(this.b, this.e, this.f);
        goto label_32;
    label_8:
        if(Build$VERSION.SDK_INT >= 26) {
            v0_1 = Icon.createWithAdaptiveBitmap(this.b);
        }
        else {
            Bitmap v0_2 = IconCompat.a(this.b, false);
            goto label_31;
        label_26:
            v0_1 = Icon.createWithResource(this.a(), this.e);
            goto label_32;
        label_30:
            Object v0_3 = this.b;
        label_31:
            v0_1 = Icon.createWithBitmap(v0_2);
        }

    label_32:
        if(this.g != null) {
            v0_1.setTintList(this.g);
        }

        if(this.i != IconCompat.h) {
            v0_1.setTintMode(this.i);
        }

        return v0_1;
    label_42:
        return this.b;
    }

    public Bundle d() {
        Object v2;
        String v1_1;
        Bundle v0 = new Bundle();
        int v1 = this.a;
        if(v1 != -1) {
            switch(v1) {
                case 3: {
                    goto label_10;
                }
                case 2: 
                case 4: {
                    goto label_14;
                }
                case 1: 
                case 5: {
                    goto label_18;
                }
            }

            throw new IllegalArgumentException("Invalid icon");
        label_18:
            v1_1 = "obj";
            v2 = this.b;
            goto label_23;
        label_10:
            v0.putByteArray("obj", this.b);
            goto label_24;
        label_14:
            v0.putString("obj", this.b);
        }
        else {
            v1_1 = "obj";
            v2 = this.b;
        label_23:
            v0.putParcelable(v1_1, ((Parcelable)v2));
        }

    label_24:
        v0.putInt("type", this.a);
        v0.putInt("int1", this.e);
        v0.putInt("int2", this.f);
        if(this.g != null) {
            v0.putParcelable("tint_list", this.g);
        }

        if(this.i != IconCompat.h) {
            v0.putString("tint_mode", this.i.name());
        }

        return v0;
    }

    public void e() {
        byte[] v0_1;
        this.i = PorterDuff$Mode.valueOf(this.j);
        int v0 = this.a;
        if(v0 != -1) {
            switch(v0) {
                case 3: {
                    goto label_8;
                }
                case 2: 
                case 4: {
                    goto label_10;
                }
                case 1: 
                case 5: {
                    goto label_17;
                }
            }

            return;
        label_17:
            if(this.d != null) {
                goto label_32;
            }
            else {
                this.b = this.c;
                this.a = 3;
                this.e = 0;
                this.f = this.c.length;
                return;
            label_8:
                v0_1 = this.c;
                goto label_15;
            label_10:
                String v0_2 = new String(this.c, Charset.forName("UTF-16"));
                goto label_15;
            }
        }
        else if(this.d != null) {
        label_32:
            Parcelable v0_3 = this.d;
        label_15:
            this.b = v0_1;
        }
        else {
            goto label_35;
        }

        return;
    label_35:
        throw new IllegalArgumentException("Invalid icon");
    }

    public String toString() {
        int v1;
        if(this.a == -1) {
            return String.valueOf(this.b);
        }

        StringBuilder v0 = new StringBuilder("Icon(typ=");
        v0.append(IconCompat.a(this.a));
        switch(this.a) {
            case 2: {
                v0.append(" pkg=");
                v0.append(this.a());
                v0.append(" id=");
                v0.append(String.format("0x%08x", Integer.valueOf(this.b())));
                break;
            }
            case 3: {
                v0.append(" len=");
                v0.append(this.e);
                if(this.f == 0) {
                    goto label_56;
                }

                v0.append(" off=");
                v1 = this.f;
                goto label_55;
            }
            case 4: {
                v0.append(" uri=");
                v0.append(this.b);
                break;
            }
            case 1: 
            case 5: {
                v0.append(" size=");
                v0.append(this.b.getWidth());
                v0.append("x");
                v1 = this.b.getHeight();
            label_55:
                v0.append(v1);
                break;
            }
            default: {
                break;
            }
        }

    label_56:
        if(this.g != null) {
            v0.append(" tint=");
            v0.append(this.g);
        }

        if(this.i != IconCompat.h) {
            v0.append(" mode=");
            v0.append(this.i);
        }

        v0.append(")");
        return v0.toString();
    }
}


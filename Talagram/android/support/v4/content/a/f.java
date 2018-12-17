package android.support.v4.content.a;

import android.content.Context;
import android.content.res.Resources$NotFoundException;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.graphics.c;
import android.util.Log;
import android.util.TypedValue;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

public final class f {
    public abstract class a {
        public a() {
            super();
        }

        public final void a(int arg2, Handler arg3) {
            if(arg3 == null) {
                arg3 = new Handler(Looper.getMainLooper());
            }

            arg3.post(new Runnable(arg2) {
                public void run() {
                    this.b.a(this.a);
                }
            });
        }

        public final void a(Typeface arg2, Handler arg3) {
            if(arg3 == null) {
                arg3 = new Handler(Looper.getMainLooper());
            }

            arg3.post(new Runnable(arg2) {
                public void run() {
                    this.b.a(this.a);
                }
            });
        }

        public abstract void a(int arg1);

        public abstract void a(Typeface arg1);
    }

    public static Typeface a(Context arg7, int arg8, TypedValue arg9, int arg10, a arg11) {
        if(arg7.isRestricted()) {
            return null;
        }

        return f.a(arg7, arg8, arg9, arg10, arg11, null, true);
    }

    private static Typeface a(Context arg8, int arg9, TypedValue arg10, int arg11, a arg12, Handler arg13, boolean arg14) {
        Resources v1 = arg8.getResources();
        v1.getValue(arg9, arg10, true);
        Typeface v8 = f.a(arg8, v1, arg10, arg9, arg11, arg12, arg13, arg14);
        if(v8 == null) {
            if(arg12 != null) {
            }
            else {
                StringBuilder v10 = new StringBuilder();
                v10.append("Font resource ID #0x");
                v10.append(Integer.toHexString(arg9));
                v10.append(" could not be retrieved.");
                throw new Resources$NotFoundException(v10.toString());
            }
        }

        return v8;
    }

    private static Typeface a(Context arg14, Resources arg15, TypedValue arg16, int arg17, int arg18, a arg19, Handler arg20, boolean arg21) {
        String v3;
        StringBuilder v2_1;
        String v1_2;
        TypedValue v1 = arg16;
        int v4 = arg17;
        int v5 = arg18;
        a v9 = arg19;
        Handler v10 = arg20;
        if(v1.string != null) {
            String v11 = v1.string.toString();
            Typeface v12 = null;
            int v13 = -3;
            if(!v11.startsWith("res/")) {
                if(v9 != null) {
                    v9.a(v13, v10);
                }

                return v12;
            }

            Typeface v1_1 = c.a(arg15, v4, v5);
            if(v1_1 != null) {
                if(v9 != null) {
                    v9.a(v1_1, v10);
                }

                return v1_1;
            }

            try {
                if(v11.toLowerCase().endsWith(".xml")) {
                    android.support.v4.content.a.c$a v2 = android.support.v4.content.a.c.a(arg15.getXml(v4), arg15);
                    if(v2 == null) {
                        Log.e("ResourcesCompat", "Failed to find font-family tag");
                        if(v9 != null) {
                            v9.a(v13, v10);
                        }

                        return v12;
                    }

                    return c.a(arg14, v2, arg15, arg17, arg18, arg19, arg20, arg21);
                }

                Typeface v0_2 = c.a(arg14, arg15, v4, v11, v5);
                if(v9 != null) {
                    if(v0_2 != null) {
                        v9.a(v0_2, v10);
                    }
                    else {
                        v9.a(v13, v10);
                    }
                }

                return v0_2;
            }
            catch(IOException v0) {
                v1_2 = "ResourcesCompat";
                v2_1 = new StringBuilder();
                v3 = "Failed to read xml resource ";
            }
            catch(XmlPullParserException v0_1) {
                v1_2 = "ResourcesCompat";
                v2_1 = new StringBuilder();
                v3 = "Failed to parse xml resource ";
            }

            v2_1.append(v3);
            v2_1.append(v11);
            Log.e(v1_2, v2_1.toString(), ((Throwable)v0));
            if(v9 != null) {
                v9.a(v13, v10);
            }

            return v12;
        }

        StringBuilder v3_1 = new StringBuilder();
        v3_1.append("Resource \"");
        v3_1.append(arg15.getResourceName(v4));
        v3_1.append("\" (");
        v3_1.append(Integer.toHexString(arg17));
        v3_1.append(") is not a Font: ");
        v3_1.append(v1);
        throw new Resources$NotFoundException(v3_1.toString());
    }

    public static Drawable a(Resources arg2, int arg3, Resources$Theme arg4) {
        if(Build$VERSION.SDK_INT >= 21) {
            return arg2.getDrawable(arg3, arg4);
        }

        return arg2.getDrawable(arg3);
    }
}


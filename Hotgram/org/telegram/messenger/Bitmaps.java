package org.telegram.messenger;

import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory$Options;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build$VERSION;

public class Bitmaps {
    final class org.telegram.messenger.Bitmaps$1 extends ThreadLocal {
        org.telegram.messenger.Bitmaps$1() {
            super();
        }

        protected Object initialValue() {
            return this.initialValue();
        }

        protected byte[] initialValue() {
            return new byte[]{-1, -40, -1, -37, 0, 67, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -64, 0, 17, 8, 0, 0, 0, 0, 3, 1, 34, 0, 2, 17, 0, 3, 17, 0, -1, -60, 0, 31, 0, 0, 1, 5, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, -1, -60, 0, -75, 16, 0, 2, 1, 3, 3, 2, 4, 3, 5, 5, 4, 4, 0, 0, 1, 125, 1, 2, 3, 0, 4, 17, 5, 18, 33, 49, 65, 6, 19, 81, 97, 7, 34, 113, 20, 50, -127, -111, -95, 8, 35, 66, -79, -63, 21, 82, -47, -16, 36, 51, 98, 114, -126, 9, 10, 22, 23, 24, 25, 26, 37, 38, 39, 40, 41, 42, 52, 53, 54, 55, 56, 57, 58, 67, 68, 69, 70, 71, 72, 73, 74, 83, 84, 85, 86, 87, 88, 89, 90, 99, 100, 101, 102, 103, 104, 105, 106, 115, 116, 117, 118, 119, 120, 121, 122, -125, -124, -123, -122, -121, -120, -119, -118, -110, -109, -108, -107, -106, -105, -104, -103, -102, -94, -93, -92, -91, -90, -89, -88, -87, -86, -78, -77, -76, -75, -74, -73, -72, -71, -70, -62, -61, -60, -59, -58, -57, -56, -55, -54, -46, -45, -44, -43, -42, -41, -40, -39, -38, -31, -30, -29, -28, -27, -26, -25, -24, -23, -22, -15, -14, -13, -12, -11, -10, -9, -8, -7, -6, -1, -60, 0, 31, 1, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, -1, -60, 0, -75, 17, 0, 2, 1, 2, 4, 4, 3, 4, 7, 5, 4, 4, 0, 1, 2, 119, 0, 1, 2, 3, 17, 4, 5, 33, 49, 6, 18, 65, 81, 7, 97, 113, 19, 34, 50, -127, 8, 20, 66, -111, -95, -79, -63, 9, 35, 51, 82, -16, 21, 98, 114, -47, 10, 22, 36, 52, -31, 37, -15, 23, 24, 25, 26, 38, 39, 40, 41, 42, 53, 54, 55, 56, 57, 58, 67, 68, 69, 70, 71, 72, 73, 74, 83, 84, 85, 86, 87, 88, 89, 90, 99, 100, 101, 102, 103, 104, 105, 106, 115, 116, 117, 118, 119, 120, 121, 122, -126, -125, -124, -123, -122, -121, -120, -119, -118, -110, -109, -108, -107, -106, -105, -104, -103, -102, -94, -93, -92, -91, -90, -89, -88, -87, -86, -78, -77, -76, -75, -74, -73, -72, -71, -70, -62, -61, -60, -59, -58, -57, -56, -55, -54, -46, -45, -44, -43, -42, -41, -40, -39, -38, -30, -29, -28, -27, -26, -25, -24, -23, -22, -14, -13, -12, -11, -10, -9, -8, -7, -6, -1, -38, 0, 12, 3, 1, 0, 2, 17, 3, 17, 0, 63, 0, -114, -118, 40, -96, 15, -1, -39};
        }
    }

    class org.telegram.messenger.Bitmaps$2 {
        static {
            org.telegram.messenger.Bitmaps$2.$SwitchMap$android$graphics$Bitmap$Config = new int[Bitmap$Config.values().length];
            try {
                org.telegram.messenger.Bitmaps$2.$SwitchMap$android$graphics$Bitmap$Config[Bitmap$Config.RGB_565.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    org.telegram.messenger.Bitmaps$2.$SwitchMap$android$graphics$Bitmap$Config[Bitmap$Config.ALPHA_8.ordinal()] = 2;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                    try {
                    label_14:
                        org.telegram.messenger.Bitmaps$2.$SwitchMap$android$graphics$Bitmap$Config[Bitmap$Config.ARGB_4444.ordinal()] = 3;
                        goto label_19;
                    }
                    catch(NoSuchFieldError ) {
                        try {
                        label_19:
                            org.telegram.messenger.Bitmaps$2.$SwitchMap$android$graphics$Bitmap$Config[Bitmap$Config.ARGB_8888.ordinal()] = 4;
                            return;
                        }
                        catch(NoSuchFieldError ) {
                            return;
                        }
                    }
                }
            }
        }
    }

    private static final ThreadLocal jpegData;
    private static volatile Matrix sScaleMatrix;

    static {
        Bitmaps.jpegData = new org.telegram.messenger.Bitmaps$1();
    }

    public Bitmaps() {
        super();
    }

    private static void checkWidthHeight(int arg0, int arg1) {
        if(arg0 > 0) {
            if(arg1 > 0) {
                return;
            }

            throw new IllegalArgumentException("height must be > 0");
        }

        throw new IllegalArgumentException("width must be > 0");
    }

    private static void checkXYSign(int arg0, int arg1) {
        if(arg0 >= 0) {
            if(arg1 >= 0) {
                return;
            }

            throw new IllegalArgumentException("y must be >= 0");
        }

        throw new IllegalArgumentException("x must be >= 0");
    }

    public static Bitmap createBitmap(int arg6, int arg7, Bitmap$Config arg8) {
        Bitmap v6;
        if(Build$VERSION.SDK_INT < 21) {
            BitmapFactory$Options v0 = new BitmapFactory$Options();
            v0.inDither = true;
            v0.inPreferredConfig = arg8;
            v0.inPurgeable = true;
            v0.inSampleSize = 1;
            v0.inMutable = true;
            Object v3 = Bitmaps.jpegData.get();
            v3[76] = ((byte)(arg7 >> 8));
            v3[77] = ((byte)(arg7 & 255));
            v3[78] = ((byte)(arg6 >> 8));
            v3[79] = ((byte)(arg6 & 255));
            v6 = BitmapFactory.decodeByteArray(((byte[])v3), 0, v3.length, v0);
            Utilities.pinBitmap(v6);
            v6.setHasAlpha(true);
            v6.eraseColor(0);
        }
        else {
            v6 = Bitmap.createBitmap(arg6, arg7, arg8);
        }

        if(arg8 == Bitmap$Config.ARGB_8888 || arg8 == Bitmap$Config.ARGB_4444) {
            v6.eraseColor(0);
        }

        return v6;
    }

    public static Bitmap createBitmap(Bitmap arg7, int arg8, int arg9, int arg10, int arg11) {
        return Bitmaps.createBitmap(arg7, arg8, arg9, arg10, arg11, null, false);
    }

    public static Bitmap createBitmap(Bitmap arg6, int arg7, int arg8, int arg9, int arg10, Matrix arg11, boolean arg12) {
        Paint v11;
        Bitmap v8_1;
        if(Build$VERSION.SDK_INT >= 21) {
            return Bitmap.createBitmap(arg6, arg7, arg8, arg9, arg10, arg11, arg12);
        }

        Bitmaps.checkXYSign(arg7, arg8);
        Bitmaps.checkWidthHeight(arg9, arg10);
        int v0 = arg7 + arg9;
        if(v0 <= arg6.getWidth()) {
            int v1 = arg8 + arg10;
            if(v1 <= arg6.getHeight()) {
                if(!arg6.isMutable() && arg7 == 0 && arg8 == 0 && arg9 == arg6.getWidth() && arg10 == arg6.getHeight() && (arg11 == null || (arg11.isIdentity()))) {
                    return arg6;
                }

                Canvas v2 = new Canvas();
                Rect v3 = new Rect(arg7, arg8, v0, v1);
                RectF v7 = new RectF(0f, 0f, ((float)arg9), ((float)arg10));
                Bitmap$Config v8 = Bitmap$Config.ARGB_8888;
                Bitmap$Config v0_1 = arg6.getConfig();
                if(v0_1 != null) {
                    switch(org.telegram.messenger.Bitmaps$2.$SwitchMap$android$graphics$Bitmap$Config[v0_1.ordinal()]) {
                        case 2: {
                            v8 = Bitmap$Config.ALPHA_8;
                            goto label_44;
                        }
                        default: {
                            v8 = Bitmap$Config.ARGB_8888;
                            goto label_44;
                        }
                    }
                }

            label_44:
                Bitmap v0_2 = null;
                if(arg11 == null || (arg11.isIdentity())) {
                    v8_1 = Bitmaps.createBitmap(arg9, arg10, v8);
                    v11 = ((Paint)v0_2);
                }
                else {
                    arg9 = arg11.rectStaysRect() ^ 1;
                    RectF v1_1 = new RectF();
                    arg11.mapRect(v1_1, v7);
                    int v4 = Math.round(v1_1.width());
                    int v5 = Math.round(v1_1.height());
                    if(arg9 != 0) {
                        v8 = Bitmap$Config.ARGB_8888;
                    }

                    v8_1 = Bitmaps.createBitmap(v4, v5, v8);
                    v2.translate(-v1_1.left, -v1_1.top);
                    v2.concat(arg11);
                    v11 = new Paint();
                    v11.setFilterBitmap(arg12);
                    if(arg9 == 0) {
                        goto label_76;
                    }

                    v11.setAntiAlias(true);
                }

            label_76:
                v8_1.setDensity(arg6.getDensity());
                v8_1.setHasAlpha(arg6.hasAlpha());
                if(Build$VERSION.SDK_INT >= 19) {
                    v8_1.setPremultiplied(arg6.isPremultiplied());
                }

                v2.setBitmap(v8_1);
                v2.drawBitmap(arg6, v3, v7, v11);
                try {
                    v2.setBitmap(v0_2);
                    return v8_1;
                }
                catch(Exception ) {
                    return v8_1;
                }
            }

            throw new IllegalArgumentException("y + height must be <= bitmap.height()");
        }

        throw new IllegalArgumentException("x + width must be <= bitmap.width()");
    }

    public static Bitmap createScaledBitmap(Bitmap arg9, int arg10, int arg11, boolean arg12) {
        Matrix v1;
        if(Build$VERSION.SDK_INT >= 21) {
            return Bitmap.createScaledBitmap(arg9, arg10, arg11, arg12);
        }

        Class v0 = Bitmap.class;
        __monitor_enter(v0);
        try {
            v1 = Bitmaps.sScaleMatrix;
            Bitmaps.sScaleMatrix = null;
            __monitor_exit(v0);
            if(v1 != null) {
                goto label_14;
            }
        }
        catch(Throwable v9) {
            try {
            label_40:
                __monitor_exit(v0);
            }
            catch(Throwable v9) {
                goto label_40;
            }

            throw v9;
        }

        v1 = new Matrix();
    label_14:
        int v5 = arg9.getWidth();
        int v6 = arg9.getHeight();
        v1.setScale((((float)arg10)) / (((float)v5)), (((float)arg11)) / (((float)v6)));
        arg9 = Bitmaps.createBitmap(arg9, 0, 0, v5, v6, v1, arg12);
        Class v10 = Bitmap.class;
        __monitor_enter(v10);
        try {
            if(Bitmaps.sScaleMatrix == null) {
                Bitmaps.sScaleMatrix = v1;
            }

            __monitor_exit(v10);
            return arg9;
        label_37:
            __monitor_exit(v10);
        }
        catch(Throwable v9) {
            goto label_37;
        }

        throw v9;
    }
}


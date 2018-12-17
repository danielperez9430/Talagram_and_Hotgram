package net.hockeyapp.android.e;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory$Options;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class g {
    public static int a(Context arg1, Uri arg2) {
        int v2_2;
        InputStream v1;
        InputStream v0 = null;
        try {
            v1 = arg1.getContentResolver().openInputStream(arg2);
            goto label_3;
        }
        catch(Throwable v2) {
        }
        catch(IOException v2_1) {
            goto label_20;
            try {
            label_3:
                v2_2 = g.a(v1);
                if(v1 == null) {
                    return v2_2;
                }

                goto label_5;
            }
            catch(Throwable v2) {
                v0 = v1;
            }
            catch(IOException v2_1) {
                v0 = v1;
                try {
                label_20:
                    e.b("Unable to determine necessary screen orientation.", ((Throwable)v2_1));
                    if(v0 == null) {
                        return 1;
                    }
                }
                catch(Throwable v2) {
                    goto label_30;
                }

                try {
                    v0.close();
                }
                catch(IOException v2_1) {
                    e.b("Unable to close input stream.", ((Throwable)v2_1));
                }

                return 1;
            }
        }

    label_30:
        if(v0 != null) {
            try {
                v0.close();
            }
            catch(IOException v1_1) {
                e.b("Unable to close input stream.", ((Throwable)v1_1));
            }
        }

        throw v2;
        try {
        label_5:
            v1.close();
        }
        catch(IOException v1_1) {
            e.b("Unable to close input stream.", ((Throwable)v1_1));
        }

        return v2_2;
    }

    public static int a(File arg2) {
        int v2_1;
        FileInputStream v1;
        FileInputStream v0 = null;
        try {
            v1 = new FileInputStream(arg2);
        }
        catch(Throwable v2) {
            goto label_10;
        }

        try {
            v2_1 = g.a(((InputStream)v1));
            goto label_4;
        }
        catch(Throwable v2) {
            v0 = v1;
        }

    label_10:
        if(v0 != null) {
            ((InputStream)v0).close();
        }

        throw v2;
    label_4:
        ((InputStream)v1).close();
        return v2_1;
    }

    public static Bitmap a(File arg2, int arg3, int arg4) {
        BitmapFactory$Options v0 = new BitmapFactory$Options();
        v0.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(arg2.getAbsolutePath(), v0);
        v0.inSampleSize = g.a(v0, arg3, arg4);
        v0.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(arg2.getAbsolutePath(), v0);
    }

    public static int a(InputStream arg3) {
        BitmapFactory$Options v0 = new BitmapFactory$Options();
        int v1 = 1;
        v0.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(arg3, null, v0);
        int v2 = -1;
        if(v0.outWidth != v2) {
            if(v0.outHeight == v2) {
            }
            else if((((float)v0.outWidth)) / (((float)v0.outHeight)) > 1f) {
                v1 = 0;
            }
        }

        return v1;
    }

    private static int a(BitmapFactory$Options arg3, int arg4, int arg5) {
        int v0 = arg3.outHeight;
        int v3 = arg3.outWidth;
        int v1 = 1;
        if(v0 > arg5 || v3 > arg4) {
            v0 /= 2;
            v3 /= 2;
            while(v0 / v1 > arg5) {
                if(v3 / v1 <= arg4) {
                    return v1;
                }

                v1 *= 2;
            }
        }

        return v1;
    }

    public static Bitmap a(Context arg3, Uri arg4, int arg5, int arg6) {
        InputStream v0_1;
        Bitmap v4_1;
        InputStream v3;
        InputStream v2;
        BitmapFactory$Options v1;
        Rect v0 = null;
        try {
            v1 = new BitmapFactory$Options();
            v1.inJustDecodeBounds = true;
            v2 = arg3.getContentResolver().openInputStream(arg4);
        }
        catch(Throwable v4) {
            v2 = ((InputStream)v0);
            goto label_27;
        }

        try {
            BitmapFactory.decodeStream(v2, v0, v1);
            v1.inSampleSize = g.a(v1, arg5, arg6);
            v1.inJustDecodeBounds = false;
            v3 = arg3.getContentResolver().openInputStream(arg4);
        }
        catch(Throwable v4) {
            goto label_27;
        }

        try {
            v4_1 = BitmapFactory.decodeStream(v3, v0, v1);
            if(v2 == null) {
                goto label_17;
            }

            goto label_16;
        }
        catch(Throwable v4) {
            v0_1 = v3;
        }

    label_27:
        if(v2 != null) {
            v2.close();
        }

        if(v0_1 != null) {
            v0_1.close();
        }

        throw v4;
    label_16:
        v2.close();
    label_17:
        if(v3 != null) {
            v3.close();
        }

        return v4_1;
    }
}


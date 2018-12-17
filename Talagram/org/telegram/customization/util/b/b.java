package org.telegram.customization.util.b;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class b {
    public interface a {
        void onCanceled(org.telegram.customization.util.b.b$b arg1, int arg2);

        void onImagePickerError(Exception arg1, org.telegram.customization.util.b.b$b arg2, int arg3);

        void onImagesPicked(List arg1, org.telegram.customization.util.b.b$b arg2, int arg3);
    }

    public enum org.telegram.customization.util.b.b$b {
        public static final enum org.telegram.customization.util.b.b$b a;
        public static final enum org.telegram.customization.util.b.b$b b;
        public static final enum org.telegram.customization.util.b.b$b c;
        public static final enum org.telegram.customization.util.b.b$b d;

        static {
            org.telegram.customization.util.b.b$b.a = new org.telegram.customization.util.b.b$b("GALLERY", 0);
            org.telegram.customization.util.b.b$b.b = new org.telegram.customization.util.b.b$b("DOCUMENTS", 1);
            org.telegram.customization.util.b.b$b.c = new org.telegram.customization.util.b.b$b("CAMERA_IMAGE", 2);
            org.telegram.customization.util.b.b$b.d = new org.telegram.customization.util.b.b$b("CAMERA_VIDEO", 3);
            org.telegram.customization.util.b.b$b.e = new org.telegram.customization.util.b.b$b[]{org.telegram.customization.util.b.b$b.a, org.telegram.customization.util.b.b$b.b, org.telegram.customization.util.b.b$b.c, org.telegram.customization.util.b.b$b.d};
        }

        private org.telegram.customization.util.b.b$b(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static org.telegram.customization.util.b.b$b valueOf(String arg1) {
            return Enum.valueOf(org.telegram.customization.util.b.b$b.class, arg1);
        }

        public static org.telegram.customization.util.b.b$b[] values() {
            // Method was not decompiled
        }
    }

    public static c a(Context arg1) {
        return new c(arg1);
    }

    public static void a(int arg5, int arg6, Intent arg7, Activity arg8, a arg9) {
        org.telegram.customization.util.b.b$b v5;
        int v0 = (arg5 & 876) > 0 ? 1 : 0;
        if(v0 != 0) {
            arg5 &= -32769;
            v0 = 17260;
            int v1 = 9068;
            int v2 = 2924;
            int v3 = 4972;
            if(arg5 != v3 && arg5 != v1 && arg5 != v0 && arg5 != v2) {
                return;
            }

            if(arg6 == -1) {
                if(arg5 != v2 || (b.a(arg7))) {
                    if(arg5 == v3 && !b.a(arg7)) {
                        b.b(arg7, arg8, arg9);
                        return;
                    }

                    if(arg5 != v1) {
                        if(arg5 == v0) {
                            b.b(arg8, arg9);
                            return;
                        }
                        else if(b.a(arg7)) {
                        }
                        else {
                            goto label_21;
                        }
                    }

                    b.a(arg8, arg9);
                    return;
                }

            label_21:
                b.a(arg7, arg8, arg9);
                return;
            }

            if(arg5 == v2) {
                v5 = org.telegram.customization.util.b.b$b.b;
            }
            else if(arg5 == v3) {
                v5 = org.telegram.customization.util.b.b$b.a;
            }
            else {
                v5 = org.telegram.customization.util.b.b$b.c;
            }

            arg9.onCanceled(v5, b.b(((Context)arg8)));
        }
    }

    private static boolean a(Intent arg1) {
        boolean v1;
        if(arg1 != null) {
            if(arg1.getData() == null && arg1.getClipData() == null) {
                goto label_8;
            }

            v1 = false;
        }
        else {
        label_8:
            v1 = true;
        }

        return v1;
    }

    private static void a(Activity arg3, a arg4) {
        try {
            String v0_1 = PreferenceManager.getDefaultSharedPreferences(((Context)arg3)).getString("pl.aprilapps.easyphotopicker.photo_uri", null);
            if(!TextUtils.isEmpty(((CharSequence)v0_1))) {
                b.a(((Context)arg3), Uri.parse(v0_1));
            }

            File v0_2 = b.c(((Context)arg3));
            ArrayList v1 = new ArrayList();
            ((List)v1).add(v0_2);
            if(v0_2 == null) {
                arg4.onImagePickerError(new IllegalStateException("Unable to get the picture returned from camera"), org.telegram.customization.util.b.b$b.c, b.b(((Context)arg3)));
            }
            else {
                if(b.a(((Context)arg3)).b()) {
                    d.a(((Context)arg3), d.a(v0_2));
                }

                arg4.onImagesPicked(((List)v1), org.telegram.customization.util.b.b$b.c, b.b(((Context)arg3)));
            }

            PreferenceManager.getDefaultSharedPreferences(((Context)arg3)).edit().remove("pl.aprilapps.easyphotopicker.last_photo").remove("pl.aprilapps.easyphotopicker.photo_uri").apply();
        }
        catch(Exception v0) {
            v0.printStackTrace();
            arg4.onImagePickerError(v0, org.telegram.customization.util.b.b$b.c, b.b(((Context)arg3)));
        }
    }

    private static void a(Intent arg3, Activity arg4, a arg5) {
        try {
            File v3_1 = d.a(((Context)arg4), arg3.getData());
            arg5.onImagesPicked(d.a(v3_1), org.telegram.customization.util.b.b$b.b, b.b(((Context)arg4)));
            if(!b.a(((Context)arg4)).c()) {
                return;
            }

            d.a(((Context)arg4), d.a(v3_1));
        }
        catch(Exception v3) {
            v3.printStackTrace();
            arg5.onImagePickerError(v3, org.telegram.customization.util.b.b$b.b, b.b(((Context)arg4)));
        }
    }

    private static void a(Context arg1, Uri arg2) {
        arg1.revokeUriPermission(arg2, 3);
    }

    private static void b(Intent arg3, Activity arg4, a arg5) {
        try {
            ClipData v0 = arg3.getClipData();
            ArrayList v1 = new ArrayList();
            if(v0 == null) {
                ((List)v1).add(d.a(((Context)arg4), arg3.getData()));
            }
            else {
                int v3_1;
                for(v3_1 = 0; v3_1 < v0.getItemCount(); ++v3_1) {
                    ((List)v1).add(d.a(((Context)arg4), v0.getItemAt(v3_1).getUri()));
                }
            }

            if(b.a(((Context)arg4)).c()) {
                d.a(((Context)arg4), ((List)v1));
            }

            arg5.onImagesPicked(((List)v1), org.telegram.customization.util.b.b$b.a, b.b(((Context)arg4)));
        }
        catch(Exception v3) {
            v3.printStackTrace();
            arg5.onImagePickerError(v3, org.telegram.customization.util.b.b$b.a, b.b(((Context)arg4)));
        }
    }

    private static void b(Activity arg3, a arg4) {
        try {
            String v0_1 = PreferenceManager.getDefaultSharedPreferences(((Context)arg3)).getString("pl.aprilapps.easyphotopicker.video_uri", null);
            if(!TextUtils.isEmpty(((CharSequence)v0_1))) {
                b.a(((Context)arg3), Uri.parse(v0_1));
            }

            File v0_2 = b.d(((Context)arg3));
            ArrayList v1 = new ArrayList();
            ((List)v1).add(v0_2);
            if(v0_2 == null) {
                arg4.onImagePickerError(new IllegalStateException("Unable to get the video returned from camera"), org.telegram.customization.util.b.b$b.d, b.b(((Context)arg3)));
            }
            else {
                if(b.a(((Context)arg3)).b()) {
                    d.a(((Context)arg3), d.a(v0_2));
                }

                arg4.onImagesPicked(((List)v1), org.telegram.customization.util.b.b$b.d, b.b(((Context)arg3)));
            }

            PreferenceManager.getDefaultSharedPreferences(((Context)arg3)).edit().remove("pl.aprilapps.easyphotopicker.last_video").remove("pl.aprilapps.easyphotopicker.video_uri").apply();
        }
        catch(Exception v0) {
            v0.printStackTrace();
            arg4.onImagePickerError(v0, org.telegram.customization.util.b.b$b.d, b.b(((Context)arg3)));
        }
    }

    private static int b(Context arg2) {
        return PreferenceManager.getDefaultSharedPreferences(arg2).getInt("pl.aprilapps.easyphotopicker.type", 0);
    }

    private static File c(Context arg2) {
        String v1 = null;
        String v2 = PreferenceManager.getDefaultSharedPreferences(arg2).getString("pl.aprilapps.easyphotopicker.last_photo", v1);
        if(v2 != null) {
            return new File(v2);
        }

        return ((File)v1);
    }

    private static File d(Context arg2) {
        String v1 = null;
        String v2 = PreferenceManager.getDefaultSharedPreferences(arg2).getString("pl.aprilapps.easyphotopicker.last_video", v1);
        if(v2 != null) {
            return new File(v2);
        }

        return ((File)v1);
    }
}


package com.d.a.b.d;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap$CompressFormat;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build$VERSION;
import android.provider.ContactsContract$Contacts;
import android.provider.MediaStore$Video$Thumbnails;
import android.webkit.MimeTypeMap;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class a implements b {
    class com.d.a.b.d.a$1 {
        static {
            com.d.a.b.d.a$1.a = new int[com.d.a.b.d.b$a.values().length];
            try {
                com.d.a.b.d.a$1.a[com.d.a.b.d.b$a.a.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    com.d.a.b.d.a$1.a[com.d.a.b.d.b$a.b.ordinal()] = 2;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                    try {
                    label_14:
                        com.d.a.b.d.a$1.a[com.d.a.b.d.b$a.c.ordinal()] = 3;
                        goto label_19;
                    }
                    catch(NoSuchFieldError ) {
                        try {
                        label_19:
                            com.d.a.b.d.a$1.a[com.d.a.b.d.b$a.d.ordinal()] = 4;
                            goto label_24;
                        }
                        catch(NoSuchFieldError ) {
                            try {
                            label_24:
                                com.d.a.b.d.a$1.a[com.d.a.b.d.b$a.e.ordinal()] = 5;
                                goto label_29;
                            }
                            catch(NoSuchFieldError ) {
                                try {
                                label_29:
                                    com.d.a.b.d.a$1.a[com.d.a.b.d.b$a.f.ordinal()] = 6;
                                    goto label_34;
                                }
                                catch(NoSuchFieldError ) {
                                    try {
                                    label_34:
                                        com.d.a.b.d.a$1.a[com.d.a.b.d.b$a.g.ordinal()] = 7;
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
            }
        }
    }

    protected final Context a;
    protected final int b;
    protected final int c;

    public a(Context arg3) {
        this(arg3, 5000, 20000);
    }

    public a(Context arg1, int arg2, int arg3) {
        super();
        this.a = arg1.getApplicationContext();
        this.b = arg2;
        this.c = arg3;
    }

    @TargetApi(value=8) private InputStream a(String arg4) {
        if(Build$VERSION.SDK_INT >= 8) {
            Bitmap v4 = ThumbnailUtils.createVideoThumbnail(arg4, 2);
            if(v4 != null) {
                ByteArrayOutputStream v0 = new ByteArrayOutputStream();
                v4.compress(Bitmap$CompressFormat.PNG, 0, ((OutputStream)v0));
                return new ByteArrayInputStream(v0.toByteArray());
            }
        }

        return null;
    }

    @TargetApi(value=14) protected InputStream a(Uri arg4) {
        ContentResolver v0 = this.a.getContentResolver();
        if(Build$VERSION.SDK_INT >= 14) {
            return ContactsContract$Contacts.openContactPhotoInputStream(v0, arg4, true);
        }

        return ContactsContract$Contacts.openContactPhotoInputStream(v0, arg4);
    }

    public InputStream a(String arg3, Object arg4) {
        switch(com.d.a.b.d.a$1.a[com.d.a.b.d.b$a.a(arg3).ordinal()]) {
            case 1: 
            case 2: {
                goto label_15;
            }
            case 3: {
                goto label_13;
            }
            case 4: {
                goto label_11;
            }
            case 5: {
                goto label_9;
            }
            case 6: {
                goto label_7;
            }
        }

        return this.h(arg3, arg4);
    label_7:
        return this.g(arg3, arg4);
    label_9:
        return this.f(arg3, arg4);
    label_11:
        return this.e(arg3, arg4);
    label_13:
        return this.d(arg3, arg4);
    label_15:
        return this.b(arg3, arg4);
    }

    protected boolean a(HttpURLConnection arg2) {
        boolean v2 = arg2.getResponseCode() == 200 ? true : false;
        return v2;
    }

    private boolean b(Uri arg2) {
        String v2 = this.a.getContentResolver().getType(arg2);
        boolean v2_1 = v2 == null || !v2.startsWith("video/") ? false : true;
        return v2_1;
    }

    private boolean b(String arg2) {
        arg2 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(arg2));
        boolean v2 = arg2 == null || !arg2.startsWith("video/") ? false : true;
        return v2;
    }

    protected InputStream b(String arg4, Object arg5) {
        InputStream v5_1;
        HttpURLConnection v4 = this.c(arg4, arg5);
        int v0;
        for(v0 = 0; v4.getResponseCode() / 100 == 3; ++v0) {
            if(v0 >= 5) {
                break;
            }

            v4 = this.c(v4.getHeaderField("Location"), arg5);
        }

        try {
            v5_1 = v4.getInputStream();
        }
        catch(IOException v5) {
            com.d.a.c.b.a(v4.getErrorStream());
            throw v5;
        }

        if(this.a(v4)) {
            return new com.d.a.b.a.a(new BufferedInputStream(v5_1, 32768), v4.getContentLength());
        }

        com.d.a.c.b.a(((Closeable)v5_1));
        StringBuilder v0_1 = new StringBuilder();
        v0_1.append("Image request failed with response code ");
        v0_1.append(v4.getResponseCode());
        throw new IOException(v0_1.toString());
    }

    protected HttpURLConnection c(String arg1, Object arg2) {
        URLConnection v1 = new URL(Uri.encode(arg1, "@#&=*+-_.,:!?()/~\'%")).openConnection();
        ((HttpURLConnection)v1).setConnectTimeout(this.b);
        ((HttpURLConnection)v1).setReadTimeout(this.c);
        return ((HttpURLConnection)v1);
    }

    protected InputStream d(String arg4, Object arg5) {
        String v5 = com.d.a.b.d.b$a.c.c(arg4);
        if(this.b(arg4)) {
            return this.a(v5);
        }

        return new com.d.a.b.a.a(new BufferedInputStream(new FileInputStream(v5), 32768), ((int)new File(v5).length()));
    }

    protected InputStream e(String arg5, Object arg6) {
        ContentResolver v6 = this.a.getContentResolver();
        Uri v0 = Uri.parse(arg5);
        if(this.b(v0)) {
            Bitmap v5 = MediaStore$Video$Thumbnails.getThumbnail(v6, Long.valueOf(v0.getLastPathSegment()).longValue(), 1, null);
            if(v5 != null) {
                ByteArrayOutputStream v6_1 = new ByteArrayOutputStream();
                v5.compress(Bitmap$CompressFormat.PNG, 0, ((OutputStream)v6_1));
                return new ByteArrayInputStream(v6_1.toByteArray());
            }
        }
        else if(arg5.startsWith("content://com.android.contacts/")) {
            return this.a(v0);
        }

        return v6.openInputStream(v0);
    }

    protected InputStream f(String arg1, Object arg2) {
        return this.a.getAssets().open(com.d.a.b.d.b$a.e.c(arg1));
    }

    protected InputStream g(String arg1, Object arg2) {
        return this.a.getResources().openRawResource(Integer.parseInt(com.d.a.b.d.b$a.f.c(arg1)));
    }

    protected InputStream h(String arg3, Object arg4) {
        throw new UnsupportedOperationException(String.format("UIL doesn\'t support scheme(protocol) by default [%s]. You should implement this support yourself (BaseImageDownloader.getStreamFromOtherSource(...))", arg3));
    }
}


package org.telegram.customization.util.b;

import android.content.Context;
import android.media.MediaScannerConnection$OnScanCompletedListener;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

class d {
    static void a(Context arg2, List arg3) {
        new Thread(new Runnable(arg3, arg2) {
            public void run() {
                ArrayList v0 = new ArrayList();
                Iterator v1 = this.a.iterator();
                int v3;
                for(v3 = 1; v1.hasNext(); ++v3) {
                    Object v4 = v1.next();
                    File v5 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), d.a(this.b));
                    if(!v5.exists()) {
                        v5.mkdirs();
                    }

                    String[] v6 = ((File)v4).getName().split("\\.");
                    StringBuilder v7 = new StringBuilder();
                    v7.append(".");
                    v7.append(v6[v6.length - 1]);
                    File v7_1 = new File(v5, String.format("IMG_%s_%d.%s", new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()), Integer.valueOf(v3), v7.toString()));
                    try {
                        v7_1.createNewFile();
                        d.a(((File)v4), v7_1);
                        ((List)v0).add(v7_1);
                    }
                    catch(IOException v4_1) {
                        v4_1.printStackTrace();
                    }
                }

                d.b(this.b, ((List)v0));
            }
        }).run();
    }

    static List a(File arg1) {
        ArrayList v0 = new ArrayList();
        ((List)v0).add(arg1);
        return ((List)v0);
    }

    static File a(Context arg5, Uri arg6) {
        InputStream v0 = arg5.getContentResolver().openInputStream(arg6);
        File v1 = d.c(arg5);
        StringBuilder v3 = new StringBuilder();
        v3.append(UUID.randomUUID().toString());
        v3.append(".");
        v3.append(d.b(arg5, arg6));
        File v2 = new File(v1, v3.toString());
        v2.createNewFile();
        d.a(v0, v2);
        return v2;
    }

    private static void a(InputStream arg3, File arg4) {
        try {
            FileOutputStream v0 = new FileOutputStream(arg4);
            byte[] v4 = new byte[1024];
            while(true) {
                int v1 = arg3.read(v4);
                if(v1 <= 0) {
                    break;
                }

                ((OutputStream)v0).write(v4, 0, v1);
            }

            ((OutputStream)v0).close();
            arg3.close();
        }
        catch(Exception v3) {
            v3.printStackTrace();
        }
    }

    static String a(Context arg0) {
        return d.b(arg0);
    }

    static void a(File arg0, File arg1) {
        d.b(arg0, arg1);
    }

    private static String b(Context arg2, Uri arg3) {
        String v2 = arg3.getScheme().equals("content") ? MimeTypeMap.getSingleton().getExtensionFromMimeType(arg2.getContentResolver().getType(arg3)) : MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(new File(arg3.getPath())).toString());
        return v2;
    }

    private static String b(Context arg0) {
        return b.a(arg0).a();
    }

    static void b(Context arg3, List arg4) {
        String[] v0 = new String[arg4.size()];
        int v1;
        for(v1 = 0; v1 < arg4.size(); ++v1) {
            v0[v1] = arg4.get(v1).toString();
        }

        MediaScannerConnection.scanFile(arg3, v0, null, new MediaScannerConnection$OnScanCompletedListener() {
            public void onScanCompleted(String arg4, Uri arg5) {
                String v0 = this.getClass().getSimpleName();
                Log.d(v0, "Scanned " + arg4 + ":");
                arg4 = this.getClass().getSimpleName();
                Log.d(arg4, "-> uri=" + arg5);
            }
        });
    }

    private static void b(File arg1, File arg2) {
        d.a(new FileInputStream(arg1), arg2);
    }

    private static File c(Context arg2) {
        File v0 = new File(arg2.getCacheDir(), "EasyImage");
        if(!v0.exists()) {
            v0.mkdirs();
        }

        return v0;
    }
}


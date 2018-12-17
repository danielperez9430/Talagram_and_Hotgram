package android.support.e;

import android.content.Context;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import android.os.Build$VERSION;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

final class b {
    class a extends File {
        public long a;

        public a(File arg1, String arg2) {
            super(arg1, arg2);
            this.a = -1;
        }
    }

    static List a(Context arg14, File arg15, File arg16, String arg17, boolean arg18) {
        List v0_3;
        FileLock v12;
        FileChannel v11;
        String v2 = arg17;
        boolean v0 = arg18;
        Log.i("MultiDex", "MultiDexExtractor.load(" + arg15.getPath() + ", " + v0 + ", " + v2 + ")");
        long v5 = b.b(arg15);
        File v8 = new File(arg16, "MultiDex.lock");
        RandomAccessFile v9 = new RandomAccessFile(v8, "rw");
        IOException v10 = null;
        try {
            v11 = v9.getChannel();
        }
        catch(Throwable v0_1) {
            v11 = ((FileChannel)v10);
            v12 = ((FileLock)v11);
            goto label_116;
        }

        try {
            Log.i("MultiDex", "Blocking on lock " + v8.getPath());
            v12 = v11.lock();
        }
        catch(Throwable v0_1) {
            v12 = ((FileLock)v10);
            goto label_116;
        }

        try {
            Log.i("MultiDex", v8.getPath() + " locked");
            if(v0) {
            }
            else if(!b.a(arg14, arg15, v5, v2)) {
                try {
                    v0_3 = b.a(arg14, arg15, arg16, arg17);
                    goto label_54;
                }
                catch(IOException v0_2) {
                    IOException v7 = v0_2;
                    try {
                        Log.w("MultiDex", "Failed to reload existing extracted secondary dex files, falling back to fresh extraction", ((Throwable)v7));
                        v0_3 = b.a(arg15, arg16);
                        long v3_1 = b.a(arg15);
                        goto label_63;
                    label_70:
                        Log.i("MultiDex", "Detected that extraction must be performed.");
                        v0_3 = b.a(arg15, arg16);
                        v3_1 = b.a(arg15);
                    label_63:
                        b.a(arg14, arg17, v3_1, v5, v0_3);
                        goto label_54;
                    }
                    catch(Throwable v0_1) {
                    label_109:
                        goto label_116;
                    }
                }
            }

            goto label_70;
        }
        catch(Throwable v0_1) {
            goto label_109;
        }

    label_116:
        if(v12 != null) {
            try {
                v12.release();
            }
            catch(IOException ) {
                Log.e("MultiDex", "Failed to release lock on " + v8.getPath());
            }
        }

        if(v11 != null) {
            b.a(((Closeable)v11));
        }

        b.a(((Closeable)v9));
        throw v0_1;
    label_54:
        List v1 = v0_3;
        if(v12 != null) {
            try {
                v12.release();
            }
            catch(IOException v0_2) {
                Log.e("MultiDex", "Failed to release lock on " + v8.getPath());
                goto label_91;
            }
        }

        v0_2 = v10;
    label_91:
        if(v11 != null) {
            b.a(((Closeable)v11));
        }

        b.a(((Closeable)v9));
        if(v0_2 == null) {
            Log.i("MultiDex", "load found " + v1.size() + " secondary dex files");
            return v1;
        }

        throw v0_2;
    }

    private static long a(File arg4) {
        long v0 = arg4.lastModified();
        if(v0 == -1) {
            --v0;
        }

        return v0;
    }

    private static SharedPreferences a(Context arg3) {
        String v0 = "multidex.version";
        int v1 = Build$VERSION.SDK_INT < 11 ? 0 : 4;
        return arg3.getSharedPreferences(v0, v1);
    }

    private static List a(Context arg17, File arg18, File arg19, String arg20) {
        long v13;
        long v9;
        long v11;
        a v7;
        String v0 = arg20;
        Log.i("MultiDex", "loading existing secondary dex files");
        String v1_1 = arg18.getName() + ".classes";
        SharedPreferences v2 = b.a(arg17);
        StringBuilder v3 = new StringBuilder();
        v3.append(v0);
        v3.append("dex.number");
        int v3_1 = v2.getInt(v3.toString(), 1);
        ArrayList v4 = new ArrayList(v3_1 - 1);
        int v5 = 2;
        while(true) {
            if(v5 > v3_1) {
                goto label_104;
            }

            StringBuilder v6 = new StringBuilder();
            v6.append(v1_1);
            v6.append(v5);
            v6.append(".zip");
            v7 = new a(arg19, v6.toString());
            if(!v7.isFile()) {
                goto label_92;
            }

            v7.a = b.b(((File)v7));
            v6 = new StringBuilder();
            v6.append(v0);
            v6.append("dex.crc.");
            v6.append(v5);
            v11 = v2.getLong(v6.toString(), -1);
            v6 = new StringBuilder();
            v6.append(v0);
            v6.append("dex.time.");
            v6.append(v5);
            v9 = v2.getLong(v6.toString(), -1);
            v13 = v7.lastModified();
            if(v9 == v13) {
                String v15 = v1_1;
                SharedPreferences v16 = v2;
                if(v11 == v7.a) {
                    ((List)v4).add(v7);
                    ++v5;
                    v1_1 = v15;
                    v2 = v16;
                    continue;
                }
            }

            break;
        }

        StringBuilder v2_1 = new StringBuilder();
        v2_1.append("Invalid extracted dex: ");
        v2_1.append(v7);
        v2_1.append(" (key \"");
        v2_1.append(v0);
        v2_1.append("\"), expected modification time: ");
        v2_1.append(v9);
        v2_1.append(", modification time: ");
        v2_1.append(v13);
        v2_1.append(", expected crc: ");
        v2_1.append(v11);
        v2_1.append(", file crc: ");
        v2_1.append(v7.a);
        throw new IOException(v2_1.toString());
    label_92:
        v1 = new StringBuilder();
        v1.append("Missing extracted secondary dex file \'");
        v1.append(v7.getPath());
        v1.append("\'");
        throw new IOException(v1.toString());
    label_104:
        return ((List)v4);
    }

    private static boolean a(Context arg7, File arg8, long arg9, String arg11) {
        boolean v7_1;
        SharedPreferences v7 = b.a(arg7);
        StringBuilder v0 = new StringBuilder();
        v0.append(arg11);
        v0.append("timestamp");
        long v1 = -1;
        if(v7.getLong(v0.toString(), v1) == b.a(arg8)) {
            StringBuilder v8 = new StringBuilder();
            v8.append(arg11);
            v8.append("crc");
            if(v7.getLong(v8.toString(), v1) != arg9) {
                goto label_22;
            }
            else {
                v7_1 = false;
            }
        }
        else {
        label_22:
            v7_1 = true;
        }

        return v7_1;
    }

    private static List a(File arg12, File arg13) {
        String v0_1 = arg12.getName() + ".classes";
        b.a(arg13, v0_1);
        ArrayList v1 = new ArrayList();
        ZipFile v2 = new ZipFile(arg12);
        int v12 = 2;
        try {
            String v3_1 = "classes" + v12 + ".dex";
            while(true) {
            label_21:
                ZipEntry v3_2 = v2.getEntry(v3_1);
                if(v3_2 == null) {
                    break;
                }

                StringBuilder v4 = new StringBuilder();
                v4.append(v0_1);
                v4.append(v12);
                v4.append(".zip");
                a v5 = new a(arg13, v4.toString());
                ((List)v1).add(v5);
                Log.i("MultiDex", "Extraction is needed for file " + v5);
                int v6_1 = 0;
                int v7 = 0;
                while(true) {
                    if(v6_1 < 3 && v7 == 0) {
                        ++v6_1;
                        b.a(v2, v3_2, ((File)v5), v0_1);
                        try {
                            v5.a = b.b(((File)v5));
                            v7 = 1;
                            goto label_64;
                        }
                        catch(IOException v7_1) {
                            try {
                                Log.w("MultiDex", "Failed to read crc from " + v5.getAbsolutePath(), ((Throwable)v7_1));
                                v7 = 0;
                            label_64:
                                String v8 = "MultiDex";
                                v9 = new StringBuilder();
                                v9.append("Extraction ");
                                String v10 = v7 != 0 ? "succeeded" : "failed";
                                v9.append(v10);
                                v9.append(" - length ");
                                v9.append(v5.getAbsolutePath());
                                v9.append(": ");
                                v9.append(v5.length());
                                v9.append(" - crc: ");
                                v9.append(v5.a);
                                Log.i(v8, v9.toString());
                                if(v7 != 0) {
                                    continue;
                                }

                                v5.delete();
                                if(!v5.exists()) {
                                    continue;
                                }

                                Log.w("MultiDex", "Failed to delete corrupted secondary dex \'" + v5.getPath() + "\'");
                                continue;
                            label_104:
                                if(v7 != 0) {
                                    ++v12;
                                    v3_1 = "classes" + v12 + ".dex";
                                    goto label_21;
                                }

                                v0 = new StringBuilder();
                                v0.append("Could not create zip file ");
                                v0.append(v5.getAbsolutePath());
                                v0.append(" for secondary dex (");
                                v0.append(v12);
                                v0.append(")");
                                throw new IOException(v0.toString());
                            }
                            catch(Throwable v12_1) {
                                goto label_138;
                            }
                        }
                    }

                    goto label_104;
                }
            }
        }
        catch(Throwable v12_1) {
            goto label_138;
        }

        try {
            v2.close();
        }
        catch(IOException v12_2) {
            Log.w("MultiDex", "Failed to close resource", ((Throwable)v12_2));
        }

        return ((List)v1);
        try {
        label_138:
            v2.close();
        }
        catch(IOException v13) {
            Log.w("MultiDex", "Failed to close resource", ((Throwable)v13));
        }

        throw v12_1;
    }

    private static void a(Context arg2, String arg3, long arg4, long arg6, List arg8) {
        SharedPreferences$Editor v2 = b.a(arg2).edit();
        v2.putLong(arg3 + "timestamp", arg4);
        v2.putLong(arg3 + "crc", arg6);
        v2.putInt(arg3 + "dex.number", arg8.size() + 1);
        Iterator v4_1 = arg8.iterator();
        int v5;
        for(v5 = 2; v4_1.hasNext(); ++v5) {
            Object v6 = v4_1.next();
            v2.putLong(arg3 + "dex.crc." + v5, ((a)v6).a);
            v2.putLong(arg3 + "dex.time." + v5, ((a)v6).lastModified());
        }

        v2.commit();
    }

    private static void a(Closeable arg2) {
        try {
            arg2.close();
        }
        catch(IOException v2) {
            Log.w("MultiDex", "Failed to close resource", ((Throwable)v2));
        }
    }

    private static void a(File arg6, String arg7) {
        File[] v7 = arg6.listFiles(new FileFilter(arg7) {
            public boolean accept(File arg2) {
                String v2 = arg2.getName();
                boolean v2_1 = (v2.startsWith(this.a)) || (v2.equals("MultiDex.lock")) ? false : true;
                return v2_1;
            }
        });
        if(v7 == null) {
            Log.w("MultiDex", "Failed to list secondary dex dir content (" + arg6.getPath() + ").");
            return;
        }

        int v6 = v7.length;
        int v0_1;
        for(v0_1 = 0; v0_1 < v6; ++v0_1) {
            File v1 = v7[v0_1];
            Log.i("MultiDex", "Trying to delete old file " + v1.getPath() + " of size " + v1.length());
            if(!v1.delete()) {
                Log.w("MultiDex", "Failed to delete old file " + v1.getPath());
            }
            else {
                Log.i("MultiDex", "Deleted old file " + v1.getPath());
            }
        }
    }

    private static void a(ZipFile arg4, ZipEntry arg5, File arg6, String arg7) {
        ZipOutputStream v0_1;
        InputStream v4 = arg4.getInputStream(arg5);
        StringBuilder v0 = new StringBuilder();
        v0.append("tmp-");
        v0.append(arg7);
        File v7 = File.createTempFile(v0.toString(), ".zip", arg6.getParentFile());
        Log.i("MultiDex", "Extracting " + v7.getPath());
        try {
            v0_1 = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(v7)));
        }
        catch(Throwable v5) {
            goto label_93;
        }

        try {
            ZipEntry v1_1 = new ZipEntry("classes.dex");
            v1_1.setTime(arg5.getTime());
            v0_1.putNextEntry(v1_1);
            byte[] v5_1 = new byte[16384];
            while(true) {
                int v1_2 = v4.read(v5_1);
                if(v1_2 == -1) {
                    break;
                }

                v0_1.write(v5_1, 0, v1_2);
            }

            v0_1.closeEntry();
        }
        catch(Throwable v5) {
            goto label_90;
        }

        try {
            v0_1.close();
            if(!v7.setReadOnly()) {
                goto label_73;
            }

            Log.i("MultiDex", "Renaming to " + arg6.getPath());
            if(!v7.renameTo(arg6)) {
                goto label_57;
            }
        }
        catch(Throwable v5) {
            goto label_93;
        }

        b.a(((Closeable)v4));
        v7.delete();
        return;
        try {
        label_57:
            v0 = new StringBuilder();
            v0.append("Failed to rename \"");
            v0.append(v7.getAbsolutePath());
            v0.append("\" to \"");
            v0.append(arg6.getAbsolutePath());
            v0.append("\"");
            throw new IOException(v0.toString());
        label_73:
            v0 = new StringBuilder();
            v0.append("Failed to mark readonly \"");
            v0.append(v7.getAbsolutePath());
            v0.append("\" (tmp of \"");
            v0.append(arg6.getAbsolutePath());
            v0.append("\")");
            throw new IOException(v0.toString());
        label_90:
            v0_1.close();
            throw v5;
        }
        catch(Throwable v5) {
        label_93:
            b.a(((Closeable)v4));
            v7.delete();
            throw v5;
        }
    }

    private static long b(File arg4) {
        long v0 = c.a(arg4);
        if(v0 == -1) {
            --v0;
        }

        return v0;
    }
}


package com.google.android.gms.common.util;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Arrays;
import javax.annotation.Nullable;

public final class IOUtils {
    final class zza extends ByteArrayOutputStream {
        zza(zzb arg1) {
            this();
        }

        private zza() {
            super();
        }

        final void zza(byte[] arg4, int arg5) {
            System.arraycopy(this.buf, 0, arg4, arg5, this.count);
        }
    }

    final class com.google.android.gms.common.util.IOUtils$zzb {
        private final File file;

        com.google.android.gms.common.util.IOUtils$zzb(File arg1, zzb arg2) {
            this(arg1);
        }

        private com.google.android.gms.common.util.IOUtils$zzb(File arg1) {
            super();
            this.file = Preconditions.checkNotNull(arg1);
        }

        public final byte[] zzdd() {
            byte[] v0_2;
            Throwable v0_1;
            Closeable v1_2;
            FileInputStream v1_1;
            Closeable v0 = null;
            try {
                v1_1 = new FileInputStream(this.file);
            }
            catch(Throwable v1) {
                Throwable v4 = v1;
                v1_2 = v0;
                v0_1 = v4;
                goto label_15;
            }

            try {
                v0_2 = IOUtils.zzb(((InputStream)v1_1), v1_1.getChannel().size());
                goto label_7;
            }
            catch(Throwable v0_1) {
            }

        label_15:
            IOUtils.closeQuietly(v1_2);
            throw v0_1;
        label_7:
            IOUtils.closeQuietly(((Closeable)v1_1));
            return v0_2;
        }
    }

    private IOUtils() {
        super();
    }

    public static void close(@Nullable Closeable arg0, String arg1, String arg2) {
        if(arg0 != null) {
            try {
                arg0.close();
                return;
            }
            catch(IOException v0) {
                Log.d(arg1, arg2, ((Throwable)v0));
            }
        }
    }

    public static void closeQuietly(@Nullable ParcelFileDescriptor arg0) {
        if(arg0 != null) {
            try {
                arg0.close();
                return;
            }
            catch(IOException ) {
                return;
            }
        }
    }

    public static void closeQuietly(@Nullable Closeable arg0) {
        if(arg0 != null) {
            try {
                arg0.close();
                return;
            }
            catch(IOException ) {
                return;
            }
        }
    }

    public static void closeQuietly(@Nullable ServerSocket arg0) {
        if(arg0 != null) {
            try {
                arg0.close();
                return;
            }
            catch(IOException ) {
                return;
            }
        }
    }

    public static void closeQuietly(@Nullable Socket arg0) {
        if(arg0 != null) {
            try {
                arg0.close();
                return;
            }
            catch(IOException ) {
                return;
            }
        }
    }

    public static long copyStream(InputStream arg1, OutputStream arg2) {
        return IOUtils.copyStream(arg1, arg2, false);
    }

    public static long copyStream(InputStream arg1, OutputStream arg2, boolean arg3) {
        return IOUtils.copyStream(arg1, arg2, arg3, 1024);
    }

    public static long copyStream(InputStream arg7, OutputStream arg8, boolean arg9, int arg10) {
        byte[] v0 = new byte[arg10];
        long v1 = 0;
        try {
            while(true) {
                int v4 = arg7.read(v0, 0, arg10);
                if(v4 == -1) {
                    break;
                }

                v1 += ((long)v4);
                arg8.write(v0, 0, v4);
            }
        }
        catch(Throwable v10) {
            if(arg9) {
                IOUtils.closeQuietly(((Closeable)arg7));
                IOUtils.closeQuietly(((Closeable)arg8));
            }

            throw v10;
        }

        if(arg9) {
            IOUtils.closeQuietly(((Closeable)arg7));
            IOUtils.closeQuietly(((Closeable)arg8));
        }

        return v1;
    }

    public static boolean isGzipByteBuffer(byte[] arg3) {
        if(arg3.length > 1 && ((arg3[1] & 255) << 8 | arg3[0] & 255) == 35615) {
            return 1;
        }

        return 0;
    }

    public static void lockAndTruncateFile(File arg5) {
        long v3;
        FileLock v2;
        FileChannel v5_1;
        RandomAccessFile v1;
        if(!arg5.exists()) {
            goto label_30;
        }

        FileLock v0 = null;
        try {
            v1 = new RandomAccessFile(arg5, "rw");
        }
        catch(Throwable v5) {
            v1 = ((RandomAccessFile)v0);
            goto label_23;
        }

        try {
            v5_1 = v1.getChannel();
            v2 = v5_1.lock();
            v3 = 0;
        }
        catch(Throwable v5) {
            goto label_23;
        }

        try {
            v5_1.truncate(v3);
            if(v2 == null) {
                goto label_14;
            }

            goto label_11;
        }
        catch(Throwable v5) {
            v0 = v2;
        }

    label_23:
        if(v0 != null && (v0.isValid())) {
            try {
                v0.release();
                goto label_27;
            }
            catch(IOException ) {
            label_27:
                if(v1 != null) {
                    IOUtils.closeQuietly(((Closeable)v1));
                }

                throw v5;
            }
        }

        goto label_27;
    label_11:
        if(!v2.isValid()) {
            goto label_14;
        }

        try {
            v2.release();
            goto label_14;
        }
        catch(IOException ) {
        label_14:
            IOUtils.closeQuietly(((Closeable)v1));
            return;
        }

    label_30:
        throw new FileNotFoundException();
    }

    public static byte[] readInputStreamFully(InputStream arg1) {
        return IOUtils.readInputStreamFully(arg1, true);
    }

    public static byte[] readInputStreamFully(InputStream arg1, boolean arg2) {
        ByteArrayOutputStream v0 = new ByteArrayOutputStream();
        IOUtils.copyStream(arg1, ((OutputStream)v0), arg2);
        return v0.toByteArray();
    }

    public static byte[] toByteArray(File arg2) {
        return new com.google.android.gms.common.util.IOUtils$zzb(arg2, null).zzdd();
    }

    public static byte[] toByteArray(InputStream arg1) {
        ByteArrayOutputStream v0 = new ByteArrayOutputStream();
        IOUtils.zza(arg1, ((OutputStream)v0));
        return v0.toByteArray();
    }

    private static long zza(InputStream arg5, OutputStream arg6) {
        Preconditions.checkNotNull(arg5);
        Preconditions.checkNotNull(arg6);
        byte[] v0 = new byte[4096];
        long v1;
        for(v1 = 0; true; v1 += ((long)v3)) {
            int v3 = arg5.read(v0);
            if(v3 == -1) {
                return v1;
            }

            arg6.write(v0, 0, v3);
        }

        return v1;
    }

    private static byte[] zza(InputStream arg4, long arg5) {
        if(arg5 <= 2147483647) {
            if(arg5 == 0) {
                return IOUtils.toByteArray(arg4);
            }

            int v5 = ((int)arg5);
            byte[] v6 = new byte[v5];
            int v0;
            for(v0 = v5; true; v0 -= v3) {
                int v1 = -1;
                if(v0 <= 0) {
                    break;
                }

                int v2 = v5 - v0;
                int v3 = arg4.read(v6, v2, v0);
                if(v3 == v1) {
                    return Arrays.copyOf(v6, v2);
                }
            }

            v5 = arg4.read();
            if(v5 == v1) {
                return v6;
            }

            zza v0_1 = new zza(null);
            v0_1.write(v5);
            IOUtils.zza(arg4, ((OutputStream)v0_1));
            byte[] v4 = Arrays.copyOf(v6, v6.length + v0_1.size());
            v0_1.zza(v4, v6.length);
            return v4;
        }

        StringBuilder v1_1 = new StringBuilder(68);
        v1_1.append("file is too large to fit in a byte array: ");
        v1_1.append(arg5);
        v1_1.append(" bytes");
        throw new OutOfMemoryError(v1_1.toString());
    }

    static byte[] zzb(InputStream arg0, long arg1) {
        return IOUtils.zza(arg0, arg1);
    }
}


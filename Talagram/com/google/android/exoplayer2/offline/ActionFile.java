package com.google.android.exoplayer2.offline;

import com.google.android.exoplayer2.util.AtomicFile;
import com.google.android.exoplayer2.util.Util;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class ActionFile {
    static final int VERSION;
    private final File actionFile;
    private final AtomicFile atomicFile;

    public ActionFile(File arg2) {
        super();
        this.actionFile = arg2;
        this.atomicFile = new AtomicFile(arg2);
    }

    public DownloadAction[] load(Deserializer[] arg7) {
        DownloadAction[] v4;
        int v3;
        InputStream v2;
        int v1 = 0;
        if(!this.actionFile.exists()) {
            return new DownloadAction[0];
        }

        Closeable v0 = null;
        try {
            v2 = this.atomicFile.openRead();
        }
        catch(Throwable v7) {
            Closeable v2_1 = v0;
            goto label_35;
        }

        try {
            DataInputStream v0_1 = new DataInputStream(v2);
            v3 = v0_1.readInt();
            if(v3 > 0) {
                goto label_22;
            }

            v3 = v0_1.readInt();
            v4 = new DownloadAction[v3];
            while(v1 < v3) {
                v4[v1] = DownloadAction.deserializeFromStream(arg7, ((InputStream)v0_1));
                ++v1;
            }
        }
        catch(Throwable v7) {
            goto label_32;
        }

        Util.closeQuietly(((Closeable)v2));
        return v4;
        try {
        label_22:
            StringBuilder v0_2 = new StringBuilder();
            v0_2.append("Unsupported action file version: ");
            v0_2.append(v3);
            throw new IOException(v0_2.toString());
        }
        catch(Throwable v7) {
        label_32:
        }

    label_35:
        Util.closeQuietly(((Closeable)v2));
        throw v7;
    }

    public void store(DownloadAction[] arg6) {
        Closeable v1_1;
        int v2;
        DataOutputStream v1;
        Closeable v0 = null;
        try {
            v1 = new DataOutputStream(this.atomicFile.startWrite());
            v2 = 0;
        }
        catch(Throwable v6) {
            v1_1 = v0;
            goto label_23;
        }

        try {
            v1.writeInt(0);
            v1.writeInt(arg6.length);
            int v3 = arg6.length;
            while(v2 < v3) {
                DownloadAction.serializeToStream(arg6[v2], ((OutputStream)v1));
                ++v2;
            }

            this.atomicFile.endWrite(((OutputStream)v1));
        }
        catch(Throwable v6) {
            goto label_23;
        }

        Util.closeQuietly(v0);
        return;
    label_23:
        Util.closeQuietly(v1_1);
        throw v6;
    }
}


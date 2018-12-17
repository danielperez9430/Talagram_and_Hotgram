package com.google.android.exoplayer2.util;

import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class AtomicFile {
    final class AtomicFileOutputStream extends OutputStream {
        private boolean closed;
        private final FileOutputStream fileOutputStream;

        public AtomicFileOutputStream(File arg2) {
            super();
            this.closed = false;
            this.fileOutputStream = new FileOutputStream(arg2);
        }

        public void close() {
            if(this.closed) {
                return;
            }

            this.closed = true;
            this.flush();
            try {
                this.fileOutputStream.getFD().sync();
            }
            catch(IOException v0) {
                Log.w("AtomicFile", "Failed to sync file descriptor:", ((Throwable)v0));
            }

            this.fileOutputStream.close();
        }

        public void flush() {
            this.fileOutputStream.flush();
        }

        public void write(int arg2) {
            this.fileOutputStream.write(arg2);
        }

        public void write(byte[] arg2) {
            this.fileOutputStream.write(arg2);
        }

        public void write(byte[] arg2, int arg3, int arg4) {
            this.fileOutputStream.write(arg2, arg3, arg4);
        }
    }

    private static final String TAG = "AtomicFile";
    private final File backupName;
    private final File baseName;

    public AtomicFile(File arg3) {
        super();
        this.baseName = arg3;
        StringBuilder v1 = new StringBuilder();
        v1.append(arg3.getPath());
        v1.append(".bak");
        this.backupName = new File(v1.toString());
    }

    public void delete() {
        this.baseName.delete();
        this.backupName.delete();
    }

    public void endWrite(OutputStream arg1) {
        arg1.close();
        this.backupName.delete();
    }

    public InputStream openRead() {
        this.restoreBackup();
        return new FileInputStream(this.baseName);
    }

    private void restoreBackup() {
        if(this.backupName.exists()) {
            this.baseName.delete();
            this.backupName.renameTo(this.baseName);
        }
    }

    public OutputStream startWrite() {
        StringBuilder v2;
        AtomicFileOutputStream v0_1;
        if(this.baseName.exists()) {
            if(this.backupName.exists()) {
                this.baseName.delete();
            }
            else if(!this.baseName.renameTo(this.backupName)) {
                Log.w("AtomicFile", "Couldn\'t rename file " + this.baseName + " to backup file " + this.backupName);
            }
        }

        try {
            v0_1 = new AtomicFileOutputStream(this.baseName);
        }
        catch(FileNotFoundException v0) {
            if(this.baseName.getParentFile().mkdirs()) {
                try {
                    v0_1 = new AtomicFileOutputStream(this.baseName);
                    goto label_38;
                }
                catch(FileNotFoundException v0) {
                    v2 = new StringBuilder();
                    v2.append("Couldn\'t create ");
                    v2.append(this.baseName);
                    throw new IOException(v2.toString(), ((Throwable)v0));
                }
            }

            v2 = new StringBuilder();
            v2.append("Couldn\'t create directory ");
            v2.append(this.baseName);
            throw new IOException(v2.toString(), ((Throwable)v0));
        }

    label_38:
        return ((OutputStream)v0_1);
    }
}


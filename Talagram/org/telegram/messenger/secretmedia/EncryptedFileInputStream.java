package org.telegram.messenger.secretmedia;

import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import org.telegram.messenger.SecureDocumentKey;
import org.telegram.messenger.Utilities;

public class EncryptedFileInputStream extends FileInputStream {
    private static final int MODE_CBC = 1;
    private static final int MODE_CTR;
    private int currentMode;
    private int fileOffset;
    private byte[] iv;
    private byte[] key;

    public EncryptedFileInputStream(File arg5, File arg6) {
        super(arg5);
        this.key = new byte[32];
        this.iv = new byte[16];
        this.currentMode = 0;
        RandomAccessFile v2 = new RandomAccessFile(arg6, "r");
        v2.read(this.key, 0, 32);
        v2.read(this.iv, 0, 16);
        v2.close();
    }

    public EncryptedFileInputStream(File arg4, SecureDocumentKey arg5) {
        super(arg4);
        this.key = new byte[32];
        this.iv = new byte[16];
        this.currentMode = 1;
        System.arraycopy(arg5.file_key, 0, this.key, 0, this.key.length);
        System.arraycopy(arg5.file_iv, 0, this.iv, 0, this.iv.length);
    }

    public static void decryptBytesWithKeyFile(byte[] arg7, int arg8, int arg9, SecureDocumentKey arg10) {
        Utilities.aesCbcEncryptionByteArraySafe(arg7, arg10.file_key, arg10.file_iv, arg8, arg9, 0, 0);
    }

    public static void decryptBytesWithKeyFile(byte[] arg7, int arg8, int arg9, File arg10) {
        byte[] v2 = new byte[32];
        byte[] v3 = new byte[16];
        RandomAccessFile v4 = new RandomAccessFile(arg10, "r");
        v4.read(v2, 0, 32);
        v4.read(v3, 0, 16);
        v4.close();
        Utilities.aesCtrDecryptionByteArray(arg7, v2, v3, arg8, arg9, 0);
    }

    public int read(byte[] arg12, int arg13, int arg14) {
        if(this.currentMode == 1 && this.fileOffset == 0) {
            byte[] v2 = new byte[32];
            super.read(v2, 0, 32);
            Utilities.aesCbcEncryptionByteArraySafe(arg12, this.key, this.iv, arg13, arg14, this.fileOffset, 0);
            this.fileOffset += 32;
            this.skip(((long)((v2[0] & 255) - 32)));
        }

        int v0 = super.read(arg12, arg13, arg14);
        if(this.currentMode == 1) {
            Utilities.aesCbcEncryptionByteArraySafe(arg12, this.key, this.iv, arg13, arg14, this.fileOffset, 0);
        }
        else if(this.currentMode == 0) {
            Utilities.aesCtrDecryptionByteArray(arg12, this.key, this.iv, arg13, arg14, this.fileOffset);
        }

        this.fileOffset += arg14;
        return v0;
    }

    public long skip(long arg3) {
        this.fileOffset = ((int)((((long)this.fileOffset)) + arg3));
        return super.skip(arg3);
    }
}


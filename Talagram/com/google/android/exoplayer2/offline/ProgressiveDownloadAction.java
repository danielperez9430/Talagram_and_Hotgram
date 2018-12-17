package com.google.android.exoplayer2.offline;

import android.net.Uri;
import com.google.android.exoplayer2.upstream.cache.CacheUtil;
import com.google.android.exoplayer2.util.Util;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public final class ProgressiveDownloadAction extends DownloadAction {
    final class com.google.android.exoplayer2.offline.ProgressiveDownloadAction$1 extends Deserializer {
        com.google.android.exoplayer2.offline.ProgressiveDownloadAction$1(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public DownloadAction readFromStream(int arg1, DataInputStream arg2) {
            return this.readFromStream(arg1, arg2);
        }

        public ProgressiveDownloadAction readFromStream(int arg4, DataInputStream arg5) {
            Uri v4 = Uri.parse(arg5.readUTF());
            boolean v0 = arg5.readBoolean();
            byte[] v1 = new byte[arg5.readInt()];
            arg5.readFully(v1);
            String v5 = arg5.readBoolean() ? arg5.readUTF() : null;
            return new ProgressiveDownloadAction(v4, v0, v1, v5);
        }
    }

    public static final Deserializer DESERIALIZER = null;
    private static final String TYPE = "progressive";
    private static final int VERSION;
    private final String customCacheKey;

    static {
        ProgressiveDownloadAction.DESERIALIZER = new com.google.android.exoplayer2.offline.ProgressiveDownloadAction$1("progressive", 0);
    }

    @Deprecated public ProgressiveDownloadAction(Uri arg7, boolean arg8, byte[] arg9, String arg10) {
        super("progressive", 0, arg7, arg8, arg9);
        this.customCacheKey = arg10;
    }

    public static ProgressiveDownloadAction createDownloadAction(Uri arg2, byte[] arg3, String arg4) {
        return new ProgressiveDownloadAction(arg2, false, arg3, arg4);
    }

    public Downloader createDownloader(DownloaderConstructorHelper arg1) {
        return this.createDownloader(arg1);
    }

    public ProgressiveDownloader createDownloader(DownloaderConstructorHelper arg4) {
        return new ProgressiveDownloader(this.uri, this.customCacheKey, arg4);
    }

    public static ProgressiveDownloadAction createRemoveAction(Uri arg2, byte[] arg3, String arg4) {
        return new ProgressiveDownloadAction(arg2, true, arg3, arg4);
    }

    public boolean equals(Object arg2) {
        if(this == (((ProgressiveDownloadAction)arg2))) {
            return 1;
        }

        if(!super.equals(arg2)) {
            return 0;
        }

        return Util.areEqual(this.customCacheKey, ((ProgressiveDownloadAction)arg2).customCacheKey);
    }

    private String getCacheKey() {
        String v0 = this.customCacheKey != null ? this.customCacheKey : CacheUtil.generateKey(this.uri);
        return v0;
    }

    public int hashCode() {
        int v0 = super.hashCode() * 31;
        int v1 = this.customCacheKey != null ? this.customCacheKey.hashCode() : 0;
        return v0 + v1;
    }

    public boolean isSameMedia(DownloadAction arg2) {
        boolean v2 = !(arg2 instanceof ProgressiveDownloadAction) || !this.getCacheKey().equals(((ProgressiveDownloadAction)arg2).getCacheKey()) ? false : true;
        return v2;
    }

    protected void writeToStream(DataOutputStream arg2) {
        arg2.writeUTF(this.uri.toString());
        arg2.writeBoolean(this.isRemoveAction);
        arg2.writeInt(this.data.length);
        arg2.write(this.data);
        boolean v0 = this.customCacheKey != null ? true : false;
        arg2.writeBoolean(v0);
        if(v0) {
            arg2.writeUTF(this.customCacheKey);
        }
    }
}


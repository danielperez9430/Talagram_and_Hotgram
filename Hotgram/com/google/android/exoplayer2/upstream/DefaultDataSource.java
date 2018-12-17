package com.google.android.exoplayer2.upstream;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class DefaultDataSource implements DataSource {
    private static final String SCHEME_ASSET = "asset";
    private static final String SCHEME_CONTENT = "content";
    private static final String SCHEME_RAW = "rawresource";
    private static final String SCHEME_RTMP = "rtmp";
    private static final String TAG = "DefaultDataSource";
    private DataSource assetDataSource;
    private final DataSource baseDataSource;
    private DataSource contentDataSource;
    private final Context context;
    private DataSource dataSchemeDataSource;
    private DataSource dataSource;
    private DataSource fileDataSource;
    private DataSource rawResourceDataSource;
    private DataSource rtmpDataSource;
    private final List transferListeners;

    public DefaultDataSource(Context arg1, TransferListener arg2, DataSource arg3) {
        super();
        this.context = arg1.getApplicationContext();
        this.baseDataSource = Assertions.checkNotNull(arg3);
        this.transferListeners = new ArrayList();
        if(arg2 != null) {
            this.transferListeners.add(arg2);
        }
    }

    public DefaultDataSource(Context arg10, TransferListener arg11, String arg12, int arg13, int arg14, boolean arg15) {
        this(arg10, arg11, new DefaultHttpDataSource(arg12, null, arg11, arg13, arg14, arg15, null));
    }

    public DefaultDataSource(Context arg8, TransferListener arg9, String arg10, boolean arg11) {
        this(arg8, arg9, arg10, 8000, 8000, arg11);
    }

    private void addListenersToDataSource(DataSource arg3) {
        int v0;
        for(v0 = 0; v0 < this.transferListeners.size(); ++v0) {
            arg3.addTransferListener(this.transferListeners.get(v0));
        }
    }

    public void addTransferListener(TransferListener arg2) {
        this.baseDataSource.addTransferListener(arg2);
        this.transferListeners.add(arg2);
        this.maybeAddListenerToDataSource(this.fileDataSource, arg2);
        this.maybeAddListenerToDataSource(this.assetDataSource, arg2);
        this.maybeAddListenerToDataSource(this.contentDataSource, arg2);
        this.maybeAddListenerToDataSource(this.rtmpDataSource, arg2);
        this.maybeAddListenerToDataSource(this.dataSchemeDataSource, arg2);
        this.maybeAddListenerToDataSource(this.rawResourceDataSource, arg2);
    }

    public void close() {
        if(this.dataSource != null) {
            DataSource v0 = null;
            try {
                this.dataSource.close();
            }
            catch(Throwable v1) {
                this.dataSource = v0;
                throw v1;
            }

            this.dataSource = v0;
        }
    }

    private DataSource getAssetDataSource() {
        if(this.assetDataSource == null) {
            this.assetDataSource = new AssetDataSource(this.context);
            this.addListenersToDataSource(this.assetDataSource);
        }

        return this.assetDataSource;
    }

    private DataSource getContentDataSource() {
        if(this.contentDataSource == null) {
            this.contentDataSource = new ContentDataSource(this.context);
            this.addListenersToDataSource(this.contentDataSource);
        }

        return this.contentDataSource;
    }

    private DataSource getDataSchemeDataSource() {
        if(this.dataSchemeDataSource == null) {
            this.dataSchemeDataSource = new DataSchemeDataSource();
            this.addListenersToDataSource(this.dataSchemeDataSource);
        }

        return this.dataSchemeDataSource;
    }

    private DataSource getFileDataSource() {
        if(this.fileDataSource == null) {
            this.fileDataSource = new FileDataSource();
            this.addListenersToDataSource(this.fileDataSource);
        }

        return this.fileDataSource;
    }

    private DataSource getRawResourceDataSource() {
        if(this.rawResourceDataSource == null) {
            this.rawResourceDataSource = new RawResourceDataSource(this.context);
            this.addListenersToDataSource(this.rawResourceDataSource);
        }

        return this.rawResourceDataSource;
    }

    public Map getResponseHeaders() {
        Map v0 = this.dataSource == null ? DataSource$-CC.$default$getResponseHeaders(((DataSource)this)) : this.dataSource.getResponseHeaders();
        return v0;
    }

    private DataSource getRtmpDataSource() {
        if(this.rtmpDataSource == null) {
            try {
                this.rtmpDataSource = Class.forName("com.google.android.exoplayer2.ext.rtmp.RtmpDataSource").getConstructor().newInstance();
                this.addListenersToDataSource(this.rtmpDataSource);
            }
            catch(Exception v0) {
                throw new RuntimeException("Error instantiating RTMP extension", ((Throwable)v0));
            }
            catch(ClassNotFoundException ) {
                Log.w("DefaultDataSource", "Attempting to play RTMP stream without depending on the RTMP extension");
            }

            if(this.rtmpDataSource != null) {
                goto label_25;
            }

            this.rtmpDataSource = this.baseDataSource;
        }

    label_25:
        return this.rtmpDataSource;
    }

    public Uri getUri() {
        Uri v0 = this.dataSource == null ? null : this.dataSource.getUri();
        return v0;
    }

    private void maybeAddListenerToDataSource(DataSource arg1, TransferListener arg2) {
        if(arg1 != null) {
            arg1.addTransferListener(arg2);
        }
    }

    public long open(DataSpec arg3) {
        DataSource v0_2;
        boolean v0 = this.dataSource == null ? true : false;
        Assertions.checkState(v0);
        String v0_1 = arg3.uri.getScheme();
        if(Util.isLocalFileUri(arg3.uri)) {
            if(!arg3.uri.getPath().startsWith("/android_asset/")) {
                v0_2 = this.getFileDataSource();
            }
            else {
                goto label_16;
            }
        }
        else if("asset".equals(v0_1)) {
        label_16:
            v0_2 = this.getAssetDataSource();
        }
        else if("content".equals(v0_1)) {
            v0_2 = this.getContentDataSource();
        }
        else if("rtmp".equals(v0_1)) {
            v0_2 = this.getRtmpDataSource();
        }
        else if("data".equals(v0_1)) {
            v0_2 = this.getDataSchemeDataSource();
        }
        else if("rawresource".equals(v0_1)) {
            v0_2 = this.getRawResourceDataSource();
        }
        else {
            v0_2 = this.baseDataSource;
        }

        this.dataSource = v0_2;
        return this.dataSource.open(arg3);
    }

    public int read(byte[] arg2, int arg3, int arg4) {
        return Assertions.checkNotNull(this.dataSource).read(arg2, arg3, arg4);
    }
}


package com.google.android.gms.wearable;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.api.GoogleApi$Settings;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.tasks.Task;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class DataClient extends GoogleApi {
    @Retention(value=RetentionPolicy.SOURCE) @public interface FilterType {
    }

    public abstract class GetFdForAssetResponse implements Releasable {
        public GetFdForAssetResponse() {
            super();
        }

        public abstract ParcelFileDescriptor getFdForAsset();

        public abstract InputStream getInputStream();
    }

    public interface OnDataChangedListener extends DataListener {
        void onDataChanged(DataEventBuffer arg1);
    }

    public static final String ACTION_DATA_CHANGED = "com.google.android.gms.wearable.DATA_CHANGED";
    public static final int FILTER_LITERAL = 0;
    public static final int FILTER_PREFIX = 1;

    public DataClient(Activity arg3, Settings arg4) {
        super(arg3, Wearable.API, null, arg4);
    }

    public DataClient(Context arg3, Settings arg4) {
        super(arg3, Wearable.API, null, arg4);
    }

    public abstract Task addListener(OnDataChangedListener arg1);

    public abstract Task addListener(OnDataChangedListener arg1, Uri arg2, int arg3);

    public abstract Task deleteDataItems(Uri arg1);

    public abstract Task deleteDataItems(Uri arg1, int arg2);

    public abstract Task getDataItem(Uri arg1);

    public abstract Task getDataItems();

    public abstract Task getDataItems(Uri arg1);

    public abstract Task getDataItems(Uri arg1, int arg2);

    public abstract Task getFdForAsset(Asset arg1);

    public abstract Task getFdForAsset(DataItemAsset arg1);

    public abstract Task putDataItem(PutDataRequest arg1);

    public abstract Task removeListener(OnDataChangedListener arg1);
}


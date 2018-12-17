package com.google.android.gms.wearable;

import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Deprecated public interface DataApi {
    @Deprecated public interface DataItemResult extends Result {
        DataItem getDataItem();
    }

    @Deprecated public interface DataListener {
        void onDataChanged(DataEventBuffer arg1);
    }

    @Deprecated public interface DeleteDataItemsResult extends Result {
        int getNumDeleted();
    }

    @Retention(value=RetentionPolicy.SOURCE) @public interface FilterType {
    }

    @Deprecated public interface GetFdForAssetResult extends Releasable, Result {
        ParcelFileDescriptor getFd();

        InputStream getInputStream();
    }

    public static final String ACTION_DATA_CHANGED = "com.google.android.gms.wearable.DATA_CHANGED";
    public static final int FILTER_LITERAL = 0;
    public static final int FILTER_PREFIX = 1;

    PendingResult addListener(GoogleApiClient arg1, DataListener arg2);

    PendingResult addListener(GoogleApiClient arg1, DataListener arg2, Uri arg3, int arg4);

    PendingResult deleteDataItems(GoogleApiClient arg1, Uri arg2);

    PendingResult deleteDataItems(GoogleApiClient arg1, Uri arg2, int arg3);

    PendingResult getDataItem(GoogleApiClient arg1, Uri arg2);

    PendingResult getDataItems(GoogleApiClient arg1);

    PendingResult getDataItems(GoogleApiClient arg1, Uri arg2);

    PendingResult getDataItems(GoogleApiClient arg1, Uri arg2, int arg3);

    PendingResult getFdForAsset(GoogleApiClient arg1, Asset arg2);

    PendingResult getFdForAsset(GoogleApiClient arg1, DataItemAsset arg2);

    PendingResult putDataItem(GoogleApiClient arg1, PutDataRequest arg2);

    PendingResult removeListener(GoogleApiClient arg1, DataListener arg2);
}


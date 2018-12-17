package com.google.android.gms.wearable;

import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import java.io.InputStream;
import java.io.OutputStream;
import javax.annotation.Nullable;

@Deprecated public interface Channel extends Parcelable {
    @Deprecated public interface GetInputStreamResult extends Releasable, Result {
        @Nullable InputStream getInputStream();
    }

    @Deprecated public interface GetOutputStreamResult extends Releasable, Result {
        @Nullable OutputStream getOutputStream();
    }

    PendingResult addListener(GoogleApiClient arg1, ChannelListener arg2);

    PendingResult close(GoogleApiClient arg1);

    PendingResult close(GoogleApiClient arg1, int arg2);

    PendingResult getInputStream(GoogleApiClient arg1);

    String getNodeId();

    PendingResult getOutputStream(GoogleApiClient arg1);

    String getPath();

    PendingResult receiveFile(GoogleApiClient arg1, Uri arg2, boolean arg3);

    PendingResult removeListener(GoogleApiClient arg1, ChannelListener arg2);

    PendingResult sendFile(GoogleApiClient arg1, Uri arg2);

    PendingResult sendFile(GoogleApiClient arg1, Uri arg2, long arg3, long arg4);
}


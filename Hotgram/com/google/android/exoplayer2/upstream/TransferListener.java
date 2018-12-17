package com.google.android.exoplayer2.upstream;

public interface TransferListener {
    void onBytesTransferred(DataSource arg1, DataSpec arg2, boolean arg3, int arg4);

    void onTransferEnd(DataSource arg1, DataSpec arg2, boolean arg3);

    void onTransferInitializing(DataSource arg1, DataSpec arg2, boolean arg3);

    void onTransferStart(DataSource arg1, DataSpec arg2, boolean arg3);
}


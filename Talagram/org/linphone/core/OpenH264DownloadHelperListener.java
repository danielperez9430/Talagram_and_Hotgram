package org.linphone.core;

public interface OpenH264DownloadHelperListener {
    void OnError(String arg1);

    void OnProgress(int arg1, int arg2);
}


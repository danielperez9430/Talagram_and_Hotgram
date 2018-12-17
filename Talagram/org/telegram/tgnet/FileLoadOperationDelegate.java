package org.telegram.tgnet;

public interface FileLoadOperationDelegate {
    void onFailed(int arg1);

    void onFinished(String arg1);

    void onProgressChanged(float arg1);
}


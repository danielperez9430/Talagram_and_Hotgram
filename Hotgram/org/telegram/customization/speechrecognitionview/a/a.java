package org.telegram.customization.speechrecognitionview.a;

import android.os.Bundle;
import android.speech.RecognitionListener;

public abstract class a implements RecognitionListener {
    public a() {
        super();
    }

    public void onBeginningOfSpeech() {
    }

    public void onBufferReceived(byte[] arg1) {
    }

    public void onEndOfSpeech() {
    }

    public void onError(int arg1) {
    }

    public void onEvent(int arg1, Bundle arg2) {
    }

    public void onPartialResults(Bundle arg1) {
    }

    public void onReadyForSpeech(Bundle arg1) {
    }

    public void onResults(Bundle arg1) {
    }

    public void onRmsChanged(float arg1) {
    }
}


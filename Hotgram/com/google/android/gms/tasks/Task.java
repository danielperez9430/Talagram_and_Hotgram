package com.google.android.gms.tasks;

import android.app.Activity;
import java.util.concurrent.Executor;

public abstract class Task {
    public Task() {
        super();
    }

    public Task addOnCanceledListener(Activity arg1, OnCanceledListener arg2) {
        throw new UnsupportedOperationException("addOnCanceledListener is not implemented.");
    }

    public Task addOnCanceledListener(OnCanceledListener arg2) {
        throw new UnsupportedOperationException("addOnCanceledListener is not implemented.");
    }

    public Task addOnCanceledListener(Executor arg1, OnCanceledListener arg2) {
        throw new UnsupportedOperationException("addOnCanceledListener is not implemented");
    }

    public Task addOnCompleteListener(OnCompleteListener arg2) {
        throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
    }

    public Task addOnCompleteListener(Activity arg1, OnCompleteListener arg2) {
        throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
    }

    public Task addOnCompleteListener(Executor arg1, OnCompleteListener arg2) {
        throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
    }

    public abstract Task addOnFailureListener(Activity arg1, OnFailureListener arg2);

    public abstract Task addOnFailureListener(OnFailureListener arg1);

    public abstract Task addOnFailureListener(Executor arg1, OnFailureListener arg2);

    public abstract Task addOnSuccessListener(Activity arg1, OnSuccessListener arg2);

    public abstract Task addOnSuccessListener(OnSuccessListener arg1);

    public abstract Task addOnSuccessListener(Executor arg1, OnSuccessListener arg2);

    public Task continueWith(Continuation arg2) {
        throw new UnsupportedOperationException("continueWith is not implemented");
    }

    public Task continueWith(Executor arg1, Continuation arg2) {
        throw new UnsupportedOperationException("continueWith is not implemented");
    }

    public Task continueWithTask(Continuation arg2) {
        throw new UnsupportedOperationException("continueWithTask is not implemented");
    }

    public Task continueWithTask(Executor arg1, Continuation arg2) {
        throw new UnsupportedOperationException("continueWithTask is not implemented");
    }

    public abstract Exception getException();

    public abstract Object getResult();

    public abstract Object getResult(Class arg1);

    public abstract boolean isCanceled();

    public abstract boolean isComplete();

    public abstract boolean isSuccessful();

    public Task onSuccessTask(SuccessContinuation arg2) {
        throw new UnsupportedOperationException("onSuccessTask is not implemented");
    }

    public Task onSuccessTask(Executor arg1, SuccessContinuation arg2) {
        throw new UnsupportedOperationException("onSuccessTask is not implemented");
    }
}


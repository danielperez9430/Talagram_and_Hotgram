package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

public class PendingResultUtil {
    public interface ResultConverter {
        Object convert(Result arg1);
    }

    public interface StatusConverter {
        ApiException convert(Status arg1);
    }

    private static final StatusConverter zzun;

    static {
        PendingResultUtil.zzun = new zzk();
    }

    public PendingResultUtil() {
        super();
    }

    public static Task toResponseTask(PendingResult arg1, Response arg2) {
        return PendingResultUtil.toTask(arg1, new zzm(arg2));
    }

    public static Task toTask(PendingResult arg1, ResultConverter arg2) {
        return PendingResultUtil.toTask(arg1, arg2, PendingResultUtil.zzun);
    }

    public static Task toTask(PendingResult arg2, ResultConverter arg3, StatusConverter arg4) {
        TaskCompletionSource v0 = new TaskCompletionSource();
        arg2.addStatusListener(new zzl(arg2, v0, arg3, arg4));
        return v0.getTask();
    }

    public static Task toVoidTask(PendingResult arg1) {
        return PendingResultUtil.toTask(arg1, new zzn());
    }
}


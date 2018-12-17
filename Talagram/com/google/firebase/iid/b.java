package com.google.firebase.iid;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.tasks.Task;
import javax.annotation.Nullable;

@KeepForSdk public interface b {
    @KeepForSdk Task a(String arg1, @Nullable String arg2, String arg3, String arg4);

    @KeepForSdk Task a(String arg1, String arg2, String arg3);

    @KeepForSdk boolean a();

    @KeepForSdk Task a(String arg1, @Nullable String arg2);

    @KeepForSdk boolean b();

    @KeepForSdk Task b(String arg1, String arg2, String arg3);
}


package android.support.v4.app;

import android.app.RemoteInput$Builder;
import android.app.RemoteInput;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.util.Log;
import java.util.HashSet;
import java.util.Set;

public final class ac {
    public final class a {
        private final String a;
        private final Set b;
        private final Bundle c;
        private CharSequence d;
        private CharSequence[] e;
        private boolean f;

        public a(String arg2) {
            super();
            this.b = new HashSet();
            this.c = new Bundle();
            this.f = true;
            if(arg2 != null) {
                this.a = arg2;
                return;
            }

            throw new IllegalArgumentException("Result key can\'t be null");
        }

        public a a(CharSequence arg1) {
            this.d = arg1;
            return this;
        }

        public ac a() {
            return new ac(this.a, this.d, this.e, this.f, this.c, this.b);
        }
    }

    private final String a;
    private final CharSequence b;
    private final CharSequence[] c;
    private final boolean d;
    private final Bundle e;
    private final Set f;

    ac(String arg1, CharSequence arg2, CharSequence[] arg3, boolean arg4, Bundle arg5, Set arg6) {
        super();
        this.a = arg1;
        this.b = arg2;
        this.c = arg3;
        this.d = arg4;
        this.e = arg5;
        this.f = arg6;
    }

    static RemoteInput a(ac arg2) {
        return new RemoteInput$Builder(arg2.a()).setLabel(arg2.b()).setChoices(arg2.c()).setAllowFreeFormInput(arg2.f()).addExtras(arg2.g()).build();
    }

    public String a() {
        return this.a;
    }

    public static Bundle a(Intent arg3) {
        if(Build$VERSION.SDK_INT >= 20) {
            return RemoteInput.getResultsFromIntent(arg3);
        }

        Bundle v2 = null;
        if(Build$VERSION.SDK_INT >= 16) {
            arg3 = ac.b(arg3);
            if(arg3 == null) {
                return v2;
            }

            return arg3.getExtras().getParcelable("android.remoteinput.resultsData");
        }

        Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
        return v2;
    }

    static RemoteInput[] a(ac[] arg3) {
        if(arg3 == null) {
            return null;
        }

        RemoteInput[] v0 = new RemoteInput[arg3.length];
        int v1;
        for(v1 = 0; v1 < arg3.length; ++v1) {
            v0[v1] = ac.a(arg3[v1]);
        }

        return v0;
    }

    public CharSequence b() {
        return this.b;
    }

    private static Intent b(Intent arg3) {
        ClipData v3 = arg3.getClipData();
        Intent v0 = null;
        if(v3 == null) {
            return v0;
        }

        ClipDescription v1 = v3.getDescription();
        if(!v1.hasMimeType("text/vnd.android.intent")) {
            return v0;
        }

        if(!v1.getLabel().equals("android.remoteinput.results")) {
            return v0;
        }

        return v3.getItemAt(0).getIntent();
    }

    public CharSequence[] c() {
        return this.c;
    }

    public Set d() {
        return this.f;
    }

    public boolean e() {
        boolean v0;
        if(!this.f()) {
            if(this.c() != null && this.c().length != 0) {
                goto label_14;
            }

            if(this.d() == null) {
                goto label_14;
            }

            if(this.d().isEmpty()) {
                goto label_14;
            }

            v0 = true;
        }
        else {
        label_14:
            v0 = false;
        }

        return v0;
    }

    public boolean f() {
        return this.d;
    }

    public Bundle g() {
        return this.e;
    }
}


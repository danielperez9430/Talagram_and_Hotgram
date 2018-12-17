package com.google.android.gms.location.places.internal;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

public final class zzh {
    public static CharSequence zzb(String arg4, List arg5, @Nullable CharacterStyle arg6) {
        if(arg6 == null) {
            return arg4;
        }

        SpannableString v0 = new SpannableString(((CharSequence)arg4));
        Iterator v4 = arg5.iterator();
        while(v4.hasNext()) {
            Object v5 = v4.next();
            v0.setSpan(CharacterStyle.wrap(arg6), ((zzc)v5).offset, ((zzc)v5).offset + ((zzc)v5).length, 0);
        }

        return ((CharSequence)v0);
    }

    @Nullable public static String zzg(@Nullable Collection arg1) {
        if(arg1 != null) {
            if(arg1.isEmpty()) {
            }
            else {
                return TextUtils.join(", ", ((Iterable)arg1));
            }
        }

        return null;
    }
}


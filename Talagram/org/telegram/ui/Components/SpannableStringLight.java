package org.telegram.ui.Components;

import android.text.SpannableString;
import java.lang.reflect.Field;
import org.telegram.messenger.FileLog;

public class SpannableStringLight extends SpannableString {
    private static boolean fieldsAvailable;
    private static Field mSpanCountField;
    private int mSpanCountOverride;
    private static Field mSpanDataField;
    private int[] mSpanDataOverride;
    private static Field mSpansField;
    private Object[] mSpansOverride;
    private int num;

    public SpannableStringLight(CharSequence arg1) {
        super(arg1);
        try {
            this.mSpansOverride = SpannableStringLight.mSpansField.get(this);
            this.mSpanDataOverride = SpannableStringLight.mSpanDataField.get(this);
            this.mSpanCountOverride = SpannableStringLight.mSpanCountField.get(this).intValue();
        }
        catch(Throwable v1) {
            FileLog.e(v1);
        }
    }

    public static boolean isFieldsAvailable() {
        boolean v1 = true;
        if(!SpannableStringLight.fieldsAvailable && SpannableStringLight.mSpansField == null) {
            try {
                SpannableStringLight.mSpansField = SpannableString.class.getSuperclass().getDeclaredField("mSpans");
                SpannableStringLight.mSpansField.setAccessible(true);
                SpannableStringLight.mSpanDataField = SpannableString.class.getSuperclass().getDeclaredField("mSpanData");
                SpannableStringLight.mSpanDataField.setAccessible(true);
                SpannableStringLight.mSpanCountField = SpannableString.class.getSuperclass().getDeclaredField("mSpanCount");
                SpannableStringLight.mSpanCountField.setAccessible(true);
            }
            catch(Throwable v0) {
                FileLog.e(v0);
            }

            SpannableStringLight.fieldsAvailable = true;
        }

        if(SpannableStringLight.mSpansField != null) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    public void removeSpan(Object arg1) {
        super.removeSpan(arg1);
    }

    public void setSpanLight(Object arg3, int arg4, int arg5, int arg6) {
        this.mSpansOverride[this.num] = arg3;
        this.mSpanDataOverride[this.num * 3] = arg4;
        this.mSpanDataOverride[this.num * 3 + 1] = arg5;
        this.mSpanDataOverride[this.num * 3 + 2] = arg6;
        ++this.num;
    }

    public void setSpansCount(int arg2) {
        arg2 += this.mSpanCountOverride;
        this.mSpansOverride = new Object[arg2];
        this.mSpanDataOverride = new int[arg2 * 3];
        this.num = this.mSpanCountOverride;
        this.mSpanCountOverride = arg2;
        try {
            SpannableStringLight.mSpansField.set(this, this.mSpansOverride);
            SpannableStringLight.mSpanDataField.set(this, this.mSpanDataOverride);
            SpannableStringLight.mSpanCountField.set(this, Integer.valueOf(this.mSpanCountOverride));
        }
        catch(Throwable v2) {
            FileLog.e(v2);
        }
    }
}


package com.mohamadamin.persianmaterialdatetimepicker.date;

import android.content.Context;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ViewAnimator;
import com.mohamadamin.persianmaterialdatetimepicker.a.a;
import com.mohamadamin.persianmaterialdatetimepicker.a.b;

public class AccessibleDateAnimator extends ViewAnimator {
    private long a;

    public AccessibleDateAnimator(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent arg4) {
        if(arg4.getEventType() == 32) {
            arg4.getText().clear();
            b v0 = new b();
            v0.setTimeInMillis(this.a);
            StringBuilder v1 = new StringBuilder();
            v1.append(v0.d());
            v1.append(" ");
            v1.append(v0.b());
            arg4.getText().add(a.a(v1.toString()));
            return 1;
        }

        return super.dispatchPopulateAccessibilityEvent(arg4);
    }

    public void setDateMillis(long arg1) {
        this.a = arg1;
    }
}


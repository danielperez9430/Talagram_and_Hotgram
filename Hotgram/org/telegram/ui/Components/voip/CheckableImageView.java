package org.telegram.ui.Components.voip;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.ImageView;

public class CheckableImageView extends ImageView implements Checkable {
    private static final int[] CHECKED_STATE_SET;
    private boolean mChecked;

    static {
        CheckableImageView.CHECKED_STATE_SET = new int[]{16842912};
    }

    public CheckableImageView(Context arg2) {
        this(arg2, null);
    }

    public CheckableImageView(Context arg2, AttributeSet arg3) {
        this(arg2, arg3, 0);
    }

    public CheckableImageView(Context arg1, AttributeSet arg2, int arg3) {
        super(arg1, arg2, arg3);
    }

    public boolean isChecked() {
        return this.mChecked;
    }

    public int[] onCreateDrawableState(int arg2) {
        int[] v2 = super.onCreateDrawableState(arg2 + 1);
        if(this.isChecked()) {
            CheckableImageView.mergeDrawableStates(v2, CheckableImageView.CHECKED_STATE_SET);
        }

        return v2;
    }

    public void setChecked(boolean arg2) {
        if(this.mChecked != arg2) {
            this.mChecked = arg2;
            this.refreshDrawableState();
        }
    }

    public void toggle() {
        this.setChecked(this.mChecked ^ 1);
    }
}


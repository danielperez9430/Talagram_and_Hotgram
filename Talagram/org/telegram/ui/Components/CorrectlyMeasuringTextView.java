package org.telegram.ui.Components;

import android.content.Context;
import android.text.Layout;
import android.widget.TextView;

public class CorrectlyMeasuringTextView extends TextView {
    public CorrectlyMeasuringTextView(Context arg1) {
        super(arg1);
    }

    public void onMeasure(int arg6, int arg7) {
        super.onMeasure(arg6, arg7);
        try {
            Layout v6 = this.getLayout();
            if(v6.getLineCount() <= 1) {
                return;
            }

            arg7 = 0;
            int v1;
            for(v1 = v6.getLineCount() - 1; v1 >= 0; --v1) {
                arg7 = Math.max(arg7, Math.round(v6.getPaint().measureText(this.getText(), v6.getLineStart(v1), v6.getLineEnd(v1))));
            }

            super.onMeasure(Math.min(arg7 + this.getPaddingLeft() + this.getPaddingRight(), this.getMeasuredWidth()) | 1073741824, 1073741824 | this.getMeasuredHeight());
            return;
        }
        catch(Exception ) {
            return;
        }
    }
}


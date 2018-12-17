package com.mohamadamin.persianmaterialdatetimepicker.date;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint$Align;
import android.graphics.Paint$Style;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;
import com.mohamadamin.persianmaterialdatetimepicker.b$a;
import com.mohamadamin.persianmaterialdatetimepicker.b$b;
import com.mohamadamin.persianmaterialdatetimepicker.b$f;

public class TextViewWithCircularIndicator extends TextView {
    Paint a;
    private final int b;
    private final int c;
    private final String d;
    private boolean e;

    public TextViewWithCircularIndicator(Context arg2, AttributeSet arg3) {
        super(arg2, arg3);
        this.a = new Paint();
        Resources v3 = arg2.getResources();
        this.c = v3.getColor(a.mdtp_accent_color);
        this.b = v3.getDimensionPixelOffset(b.mdtp_month_select_circle_radius);
        this.d = arg2.getResources().getString(f.mdtp_item_is_selected);
        this.a();
    }

    private void a() {
        this.a.setFakeBoldText(true);
        this.a.setAntiAlias(true);
        this.a.setColor(this.c);
        this.a.setTextAlign(Paint$Align.CENTER);
        this.a.setStyle(Paint$Style.FILL);
        this.a.setAlpha(255);
    }

    public void a(boolean arg1) {
        this.e = arg1;
    }

    public CharSequence getContentDescription() {
        String v0 = com.mohamadamin.persianmaterialdatetimepicker.a.a.a(this.getText().toString());
        if(this.e) {
            v0 = String.format(this.d, v0);
        }

        return ((CharSequence)v0);
    }

    public void onDraw(Canvas arg5) {
        if(this.e) {
            int v0 = this.getWidth();
            int v1 = this.getHeight();
            arg5.drawCircle(((float)(v0 / 2)), ((float)(v1 / 2)), ((float)(Math.min(v0, v1) / 2)), this.a);
        }

        this.setSelected(this.e);
        super.onDraw(arg5);
    }
}


package org.telegram.customization.util;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View$OnClickListener;
import android.view.View;
import android.widget.EditText;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.a.a.b;
import utils.view.FarsiTextView;

public class NumericEditText extends EditText {
    class org.telegram.customization.util.NumericEditText$1 implements TextWatcher {
        private boolean b;

        org.telegram.customization.util.NumericEditText$1(NumericEditText arg1) {
            this.a = arg1;
            super();
            this.b = false;
        }

        public void afterTextChanged(Editable arg3) {
            if(this.b) {
                return;
            }

            if(b.a(arg3.toString(), String.valueOf(NumericEditText.a(this.a))) > 1) {
                this.b = true;
                this.a.setText(NumericEditText.b(this.a));
                this.a.setSelection(NumericEditText.b(this.a).length());
                this.b = false;
                return;
            }

            if(arg3.length() == 0) {
                NumericEditText.c(this.a);
                return;
            }

            NumericEditText.b(this.a, NumericEditText.a(this.a, arg3.toString()));
            this.a.setSelection(this.a.getText().length());
            NumericEditText.d(this.a);
        }

        public void beforeTextChanged(CharSequence arg1, int arg2, int arg3, int arg4) {
        }

        public void onTextChanged(CharSequence arg1, int arg2, int arg3, int arg4) {
        }
    }

    public interface a {
        void a();

        void a(double arg1);
    }

    private final char a;
    private final char b;
    private final String c;
    private String d;
    private String e;
    private String f;
    private char g;
    private boolean h;
    private static Typeface i;
    private List j;
    private final TextWatcher k;

    public NumericEditText(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
        this.a = DecimalFormatSymbols.getInstance().getGroupingSeparator();
        this.b = DecimalFormatSymbols.getInstance().getDecimalSeparator();
        this.c = "^0+(?!$)";
        this.d = null;
        this.e = "";
        this.f = "[^\\d\\" + this.b + "]";
        this.g = this.b;
        this.h = false;
        this.j = new ArrayList();
        this.k = new org.telegram.customization.util.NumericEditText$1(this);
        this.addTextChangedListener(this.k);
        this.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg2) {
                this.a.setSelection(this.a.getText().length());
            }
        });
        this.setTypeface(NumericEditText.a(this.getContext()));
        this.setGravity(5);
    }

    public static Typeface a(Context arg1) {
        if(NumericEditText.i == null) {
            NumericEditText.i = FarsiTextView.a(arg1);
        }

        return NumericEditText.i;
    }

    static char a(NumericEditText arg0) {
        return arg0.g;
    }

    private String a(String arg5) {
        StringBuilder v0 = new StringBuilder();
        v0.append("\\");
        v0.append(this.g);
        String[] v5 = arg5.split(v0.toString(), -1);
        String v0_1 = v5[0].replaceAll(this.f, "").replaceFirst("^0+(?!$)", "");
        if(!this.h) {
            v0_1 = b.a(v0_1);
            StringBuilder v2 = new StringBuilder();
            v2.append("$1");
            v2.append(this.a);
            v0_1 = b.a(b.a(v0_1.replaceAll("(.{3})", v2.toString())), String.valueOf(this.a));
        }

        if(v5.length > 1) {
            v0_1 = v0_1 + this.g + v5[1];
        }

        return v0_1;
    }

    static String a(NumericEditText arg0, String arg1) {
        return arg0.a(arg1);
    }

    private void a() {
        this.e = "";
        Iterator v0 = this.j.iterator();
        while(v0.hasNext()) {
            v0.next().a();
        }
    }

    static String b(NumericEditText arg0) {
        return arg0.e;
    }

    private void b() {
        this.e = this.getText().toString();
        Iterator v0 = this.j.iterator();
        while(v0.hasNext()) {
            v0.next().a(this.getNumericValue());
        }
    }

    static void b(NumericEditText arg0, String arg1) {
        arg0.setTextInternal(arg1);
    }

    static void c(NumericEditText arg0) {
        arg0.a();
    }

    static void d(NumericEditText arg0) {
        arg0.b();
    }

    public String getClearText() {
        return this.getText().toString().replaceAll(this.f, "");
    }

    public double getNumericValue() {
        String v0 = this.getText().toString().replaceAll(this.f, "");
        if(this.h) {
            v0 = b.a(v0, String.valueOf(this.g), String.valueOf(this.b));
        }

        try {
            return NumberFormat.getInstance().parse(v0).doubleValue();
        }
        catch(ParseException ) {
            return NaN;
        }
    }

    public void setCustomDecimalSeparator(char arg2) {
        this.g = arg2;
        this.h = true;
        this.f = "[^\\d\\" + this.g + "]";
    }

    private void setTextInternal(String arg2) {
        this.removeTextChangedListener(this.k);
        this.setText(((CharSequence)arg2));
        this.setTypeface(NumericEditText.a(this.getContext()));
        this.addTextChangedListener(this.k);
    }
}


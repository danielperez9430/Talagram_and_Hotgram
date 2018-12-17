package com.persianswitch.sdk.payment.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import com.persianswitch.sdk.R$drawable;
import com.persianswitch.sdk.base.utils.strings.StringUtils;
import com.persianswitch.sdk.base.widgets.edittext.ApLabelEditText;
import com.persianswitch.sdk.payment.model.Bank;

public class CardTextWatcher implements TextWatcher {
    private final ApLabelEditText a;

    public CardTextWatcher(ApLabelEditText arg2) {
        super();
        this.a = arg2;
        this.a.setStartImage(drawable.asanpardakht_ic_bank_empty);
    }

    private String a(CharSequence arg6) {
        String v6_1;
        String v2 = "";
        int v0 = 0;
        int v3 = 0;
        while(v0 < arg6.length() - 1) {
            v2 = v2 + arg6.charAt(v0);
            ++v3;
            if(v3 == 4) {
                v2 = v2 + "-";
                v3 = 0;
            }

            ++v0;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append(v2);
        if(v0 <= arg6.length() - 1) {
            Character v6 = Character.valueOf(arg6.charAt(v0));
        }
        else {
            v6_1 = "";
        }

        v1.append(v6_1);
        return v1.toString();
    }

    public void a(long arg1) {
    }

    public void afterTextChanged(Editable arg4) {
        String v4 = arg4.toString();
        TextView v0 = this.a.getInnerInput();
        if(v4.startsWith("*")) {
        }
        else {
            if(v4.length() > 0) {
                String v1 = this.a(StringUtils.a(((CharSequence)v4)));
                ((EditText)v0).removeTextChangedListener(((TextWatcher)this));
                ((EditText)v0).setText(((CharSequence)v1));
                ((EditText)v0).setSelection(((EditText)v0).getText().length());
                ((EditText)v0).addTextChangedListener(((TextWatcher)this));
            }

            int v2 = -1;
            if(v4.length() > 7) {
                Bank v4_1 = Bank.a(StringUtils.a(((CharSequence)v4)));
                if(v4_1 != null && v4_1 != Bank.a) {
                    this.a(v4_1.a());
                }

                int v0_1 = 0;
                if(v4_1 != null) {
                    v0_1 = v4_1.d();
                }

                if(v0_1 <= 0) {
                    goto label_35;
                }

                this.a.setStartImage(v0_1);
                return;
            }

        label_35:
            this.a.setStartImage(v2);
        }
    }

    public void beforeTextChanged(CharSequence arg1, int arg2, int arg3, int arg4) {
    }

    public void onTextChanged(CharSequence arg1, int arg2, int arg3, int arg4) {
    }
}


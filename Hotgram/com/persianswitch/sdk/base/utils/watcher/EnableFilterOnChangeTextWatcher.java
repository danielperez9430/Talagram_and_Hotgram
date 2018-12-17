package com.persianswitch.sdk.base.utils.watcher;

import android.text.Editable;
import android.text.TextWatcher;
import com.persianswitch.sdk.base.widgets.edittext.APAutoCompleteTextView;

public class EnableFilterOnChangeTextWatcher implements TextWatcher {
    private final APAutoCompleteTextView a;

    public void afterTextChanged(Editable arg1) {
    }

    public void beforeTextChanged(CharSequence arg1, int arg2, int arg3, int arg4) {
    }

    public void onTextChanged(CharSequence arg1, int arg2, int arg3, int arg4) {
        this.a.setFilterEnabled(true);
        this.a.removeTextChangedListener(((TextWatcher)this));
    }
}


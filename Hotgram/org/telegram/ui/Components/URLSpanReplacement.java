package org.telegram.ui.Components;

import android.net.Uri;
import android.text.style.URLSpan;
import android.view.View;
import org.telegram.messenger.browser.Browser;

public class URLSpanReplacement extends URLSpan {
    public URLSpanReplacement(String arg1) {
        super(arg1);
    }

    public void onClick(View arg2) {
        Browser.openUrl(arg2.getContext(), Uri.parse(this.getURL()));
    }
}


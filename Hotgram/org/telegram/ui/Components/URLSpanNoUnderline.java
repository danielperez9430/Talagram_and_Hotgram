package org.telegram.ui.Components;

import android.net.Uri;
import android.text.TextPaint;
import android.text.style.URLSpan;
import android.view.View;
import org.telegram.messenger.browser.Browser;

public class URLSpanNoUnderline extends URLSpan {
    public URLSpanNoUnderline(String arg1) {
        super(arg1);
    }

    public void onClick(View arg4) {
        String v0 = this.getURL();
        if(v0.startsWith("@")) {
            StringBuilder v1 = new StringBuilder();
            v1.append("https://t.me/");
            v1.append(v0.substring(1));
            Browser.openUrl(arg4.getContext(), Uri.parse(v1.toString()));
        }
        else {
            Browser.openUrl(arg4.getContext(), v0);
        }
    }

    public void updateDrawState(TextPaint arg2) {
        super.updateDrawState(arg2);
        arg2.setUnderlineText(false);
    }
}


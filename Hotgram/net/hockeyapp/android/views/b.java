package net.hockeyapp.android.views;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;
import net.hockeyapp.android.e.e;
import net.hockeyapp.android.f$c;

public class b extends LinearLayout {
    private TextView a;
    private TextView b;
    private TextView c;
    private AttachmentListView d;
    private final Context e;

    public b(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
        this.e = arg1;
        LayoutInflater.from(arg1).inflate(c.hockeyapp_view_feedback_message, ((ViewGroup)this));
        this.a = this.findViewById(net.hockeyapp.android.f$b.label_author);
        this.b = this.findViewById(net.hockeyapp.android.f$b.label_date);
        this.c = this.findViewById(net.hockeyapp.android.f$b.label_text);
        this.d = this.findViewById(net.hockeyapp.android.f$b.list_attachments);
    }

    public void setFeedbackMessage(net.hockeyapp.android.c.c arg6) {
        try {
            SimpleDateFormat v0_1 = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss\'Z\'", Locale.US);
            ((DateFormat)v0_1).setTimeZone(TimeZone.getTimeZone("UTC"));
            DateFormat v1 = DateFormat.getDateTimeInstance(3, 3);
            Date v0_2 = ((DateFormat)v0_1).parse(arg6.b());
            this.b.setText(v1.format(v0_2));
            this.b.setContentDescription(v1.format(v0_2));
        }
        catch(ParseException v0) {
            e.b("Failed to set feedback message", ((Throwable)v0));
        }

        this.a.setText(arg6.d());
        this.a.setContentDescription(arg6.d());
        this.c.setText(arg6.a());
        this.c.setContentDescription(arg6.a());
        this.d.removeAllViews();
        Iterator v6 = arg6.e().iterator();
        while(v6.hasNext()) {
            Object v0_3 = v6.next();
            a v1_1 = new a(this.e, this.d, ((net.hockeyapp.android.c.b)v0_3), false);
            net.hockeyapp.android.d.a.a().a(((net.hockeyapp.android.c.b)v0_3), v1_1);
            this.d.addView(((View)v1_1));
        }
    }

    public void setIndex(int arg2) {
        int v0;
        Resources v2;
        if(arg2 % 2 == 0) {
            v2 = this.getResources();
            v0 = net.hockeyapp.android.f$a.hockeyapp_background_light;
        }
        else {
            v2 = this.getResources();
            v0 = net.hockeyapp.android.f$a.hockeyapp_background_white;
        }

        this.setBackgroundColor(v2.getColor(v0));
    }
}


package net.hockeyapp.android.e;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager$NameNotFoundException;
import android.text.TextUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;
import net.hockeyapp.android.f$d;
import net.hockeyapp.android.h;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class l {
    private ArrayList a;
    private JSONObject b;
    private Context c;
    private h d;
    private int e;

    public l(Context arg1, String arg2, h arg3) {
        super();
        this.c = arg1;
        this.d = arg3;
        this.b(arg2);
        this.d();
    }

    public static boolean a(Context arg5, long arg6) {
        boolean v0 = false;
        if(arg5 == null) {
            return 0;
        }

        try {
            if(arg6 <= new File(arg5.getPackageManager().getApplicationInfo(arg5.getPackageName(), 0).sourceDir).lastModified() / 1000 + 1800) {
                return v0;
            }
        }
        catch(PackageManager$NameNotFoundException v5) {
            e.b("Failed to get application info", ((Throwable)v5));
            return 0;
        }

        return true;
    }

    public static int a(String arg5, String arg6) {
        int v2;
        if(arg5 != null) {
            if(arg6 == null) {
            }
            else {
                try {
                    Scanner v1 = new Scanner(arg5.replaceAll("\\-.*", ""));
                    Scanner v5 = new Scanner(arg6.replaceAll("\\-.*", ""));
                    v1.useDelimiter("\\.");
                    v5.useDelimiter("\\.");
                    do {
                        v2 = -1;
                        if((v1.hasNextInt()) && (v5.hasNextInt())) {
                            int v6 = v1.nextInt();
                            int v4 = v5.nextInt();
                            if(v6 < v4) {
                                return v2;
                            }
                            else {
                                if(v6 <= v4) {
                                    continue;
                                }

                                return 1;
                            }
                        }

                        goto label_30;
                    }
                    while(true);

                    return 1;
                label_30:
                    if(v1.hasNextInt()) {
                        return 1;
                    }

                    if(!v5.hasNextInt()) {
                        return 0;
                    }
                }
                catch(Exception ) {
                    return 0;
                }

                return v2;
            }
        }

        return 0;
    }

    public static String a(String arg1) {
        if(arg1 != null) {
            if(arg1.equalsIgnoreCase("L")) {
            }
            else if(arg1.equalsIgnoreCase("M")) {
                return "6.0";
            }
            else if(arg1.equalsIgnoreCase("N")) {
                return "7.0";
            }
            else if(arg1.equalsIgnoreCase("O")) {
                return "8.0";
            }
            else {
                if(Pattern.matches("^[a-zA-Z]+", ((CharSequence)arg1))) {
                    arg1 = "99.0";
                }

                return arg1;
            }
        }

        return "5.0";
    }

    private static long a(JSONObject arg0, String arg1, long arg2) {
        try {
            return arg0.getLong(arg1);
        }
        catch(JSONException ) {
            return arg2;
        }
    }

    private String a(int arg7, JSONObject arg8) {
        StringBuilder v0 = new StringBuilder();
        int v1 = this.c(this.b);
        int v2 = this.c(arg8);
        String v8 = this.d(arg8);
        v0.append("<div style=\'padding: 20px 10px 10px;\'><strong>");
        if(arg7 == 0) {
            v0.append(this.c.getString(d.hockeyapp_update_newest_version));
            v0.append(':');
        }
        else {
            v0.append(String.format("%s (%s): ", String.format(this.c.getString(d.hockeyapp_update_version), v8), Integer.valueOf(v2)));
            if(v2 != v1 && v2 == this.e) {
                this.e = -1;
                v0.append(String.format("[%s]", this.c.getString(d.hockeyapp_update_already_installed)));
            }
        }

        v0.append("</strong></div>");
        return v0.toString();
    }

    private String a(JSONObject arg6) {
        StringBuilder v0 = new StringBuilder();
        String v6 = this.b(arg6);
        if(!TextUtils.isEmpty(((CharSequence)v6))) {
            v0.append(String.format("<a href=\'restore:%s\' style=\'%s\'>%s</a>", v6, "background: #c8c8c8; color: #000; display: block; float: right; padding: 7px; margin: 0px 10px 10px; text-decoration: none;", this.c.getString(d.hockeyapp_update_restore)));
        }

        return v0.toString();
    }

    private static String a(JSONObject arg0, String arg1, String arg2) {
        try {
            return arg0.getString(arg1);
        }
        catch(JSONException ) {
            return arg2;
        }
    }

    public String a() {
        return l.a(this.b, "shortversion", "") + " (" + l.a(this.b, "version", "") + ")";
    }

    public String a(boolean arg6) {
        StringBuilder v0 = new StringBuilder();
        v0.append("<html>");
        v0.append("<body style=\'padding: 0px 0px 20px 0px\'>");
        Iterator v1 = this.a.iterator();
        int v2;
        for(v2 = 0; v1.hasNext(); ++v2) {
            Object v3 = v1.next();
            if(v2 > 0) {
                v0.append(this.e());
                if(arg6) {
                    v0.append(this.a(((JSONObject)v3)));
                }
            }

            v0.append(this.a(v2, ((JSONObject)v3)));
            v0.append(this.e(((JSONObject)v3)));
        }

        v0.append("</body>");
        v0.append("</html>");
        return v0.toString();
    }

    private void b(String arg10) {
        this.b = new JSONObject();
        this.a = new ArrayList();
        this.e = this.d.getCurrentVersionCode();
        try {
            JSONArray v0 = new JSONArray(arg10);
            int v2 = this.e;
            int v10;
            for(v10 = 0; true; ++v10) {
                if(v10 >= v0.length()) {
                    return;
                }

                JSONObject v3 = v0.getJSONObject(v10);
                int v5 = 1;
                int v4 = v3.getInt("version") > v2 ? 1 : 0;
                if(v3.getInt("version") != v2 || !l.a(this.c, v3.getLong("timestamp"))) {
                    v5 = 0;
                }
                else {
                }

                if(v4 != 0 || v5 != 0) {
                    this.b = v3;
                    v2 = v3.getInt("version");
                }

                this.a.add(v3);
            }
        }
        catch(NullPointerException ) {
            return;
        }
    }

    private String b(JSONObject arg3) {
        String v3;
        String v0 = "";
        try {
            v3 = arg3.getString("id");
        }
        catch(JSONException ) {
            v3 = v0;
        }

        return v3;
    }

    @SuppressLint(value={"SimpleDateFormat"}) public String b() {
        return new SimpleDateFormat("dd.MM.yyyy").format(new Date(l.a(this.b, "timestamp", 0) * 1000));
    }

    private int c(JSONObject arg2) {
        int v2;
        try {
            v2 = arg2.getInt("version");
        }
        catch(JSONException ) {
            v2 = 0;
        }

        return v2;
    }

    public long c() {
        boolean v0 = Boolean.valueOf(l.a(this.b, "external", "false")).booleanValue();
        long v3 = 0;
        long v1 = l.a(this.b, "appsize", v3);
        if((v0) && v1 == v3) {
            v1 = -1;
        }

        return v1;
    }

    private void d() {
        Collections.sort(this.a, new Comparator() {
            public int a(JSONObject arg3, JSONObject arg4) {
                try {
                    arg3.getInt("version");
                    arg4.getInt("version");
                    return 0;
                }
                catch(NullPointerException ) {
                    return 0;
                }
            }

            public int compare(Object arg1, Object arg2) {
                return this.a(((JSONObject)arg1), ((JSONObject)arg2));
            }
        });
    }

    private String d(JSONObject arg3) {
        String v3;
        String v0 = "";
        try {
            v3 = arg3.getString("shortversion");
        }
        catch(JSONException ) {
            v3 = v0;
        }

        return v3;
    }

    private Object e() {
        return "<hr style=\'border-top: 1px solid #c8c8c8; border-bottom: 0px; margin: 40px 10px 0px 10px;\' />";
    }

    private String e(JSONObject arg6) {
        StringBuilder v0 = new StringBuilder();
        String v6 = l.a(arg6, "notes", "");
        v0.append("<div style=\'padding: 0px 10px;\'>");
        if(v6.trim().length() == 0) {
            v6 = String.format("<em>%s</em>", this.c.getString(d.hockeyapp_update_no_info));
        }

        v0.append(v6);
        v0.append("</div>");
        return v0.toString();
    }
}


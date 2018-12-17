package com.crashlytics.android.a;

import android.os.Bundle;
import c.a.a.a.c;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;
import java.util.Set;

public class t {
    private static final Set a;

    static {
        t.a = new HashSet(Arrays.asList(new String[]{"app_clear_data", "app_exception", "app_remove", "app_upgrade", "app_install", "app_update", "firebase_campaign", "error", "first_open", "first_visit", "in_app_purchase", "notification_dismiss", "notification_foreground", "notification_open", "notification_receive", "os_update", "session_start", "user_engagement", "ad_exposure", "adunit_exposure", "ad_query", "ad_activeview", "ad_impression", "ad_click", "screen_view", "firebase_extra_parameter"}));
    }

    public t() {
        super();
    }

    public s a(ad arg6) {
        String v6;
        Bundle v0_1;
        boolean v1 = true;
        int v0 = !b.g.equals(arg6.c) || arg6.e == null ? 0 : 1;
        int v3 = !b.h.equals(arg6.c) || arg6.g == null ? 0 : 1;
        if(v0 == 0 && v3 == 0) {
            return null;
        }

        if(v3 != 0) {
            v0_1 = this.b(arg6);
        }
        else {
            v0_1 = new Bundle();
            if(arg6.f != null) {
                this.a(v0_1, arg6.f);
            }
        }

        if(v3 != 0) {
            Object v3_1 = arg6.h.get("success");
            if(v3_1 == null || (Boolean.parseBoolean(((String)v3_1)))) {
                v1 = false;
            }
            else {
            }

            v6 = this.a(arg6.g, v1);
        }
        else {
            v6 = this.a(arg6.e);
        }

        c.h().a("Answers", "Logging event into firebase...");
        return new s(v6, v0_1);
    }

    private Double a(Object arg1) {
        String v1 = String.valueOf(arg1);
        if(v1 == null) {
            return null;
        }

        return Double.valueOf(v1);
    }

    private String a(String arg4) {
        if(arg4 != null) {
            if(arg4.length() == 0) {
            }
            else if(t.a.contains(arg4)) {
                return "fabric_" + arg4;
            }
            else {
                arg4 = arg4.replaceAll("[^\\p{Alnum}_]+", "_");
                if((arg4.startsWith("ga_")) || (arg4.startsWith("google_")) || (arg4.startsWith("firebase_")) || !Character.isLetter(arg4.charAt(0))) {
                    arg4 = "fabric_" + arg4;
                }

                int v2 = 40;
                if(arg4.length() > v2) {
                    arg4 = arg4.substring(0, v2);
                }

                return arg4;
            }
        }

        return "fabric_unnamed_event";
    }

    private String a(String arg6, boolean arg7) {
        int v0 = 0;
        if(arg7) {
            int v7 = arg6.hashCode();
            if(v7 != -902468296) {
                if(v7 != 103149417) {
                    if(v7 != 1743324417) {
                        goto label_28;
                    }
                    else if(arg6.equals("purchase")) {
                        v7 = 0;
                    }
                    else {
                        goto label_28;
                    }
                }
                else if(arg6.equals("login")) {
                    v7 = 2;
                }
                else {
                    goto label_28;
                }
            }
            else if(arg6.equals("signUp")) {
                v7 = 1;
            }
            else {
            label_28:
                v7 = -1;
            }

            switch(v7) {
                case 0: {
                    return "failed_ecommerce_purchase";
                }
                case 1: {
                    return "failed_sign_up";
                }
                case 2: {
                    return "failed_login";
                }
            }

            goto label_37;
            return "failed_sign_up";
        }

    label_37:
        switch(arg6.hashCode()) {
            case -389087554: {
                if(!arg6.equals("contentView")) {
                    goto label_99;
                }

                v0 = 3;
                break;
            }
            case 23457852: {
                if(arg6.equals("addToCart")) {
                    v0 = 1;
                    goto label_100;
                }

            label_99:
                v0 = -1;
                break;
            }
            case 103149417: {
                if(!arg6.equals("login")) {
                    goto label_99;
                }

                v0 = 8;
                break;
            }
            case 109400031: {
                if(!arg6.equals("share")) {
                    goto label_99;
                }

                v0 = 5;
                break;
            }
            case 196004670: {
                if(!arg6.equals("levelStart")) {
                    goto label_99;
                }

                v0 = 10;
                break;
            }
            case 1664021448: {
                if(!arg6.equals("startCheckout")) {
                    goto label_99;
                }

                v0 = 2;
                break;
            }
            case 1743324417: {
                if(!arg6.equals("purchase")) {
                    goto label_99;
                }

                break;
            }
            case -2131650889: {
                if(!arg6.equals("levelEnd")) {
                    goto label_99;
                }

                v0 = 11;
                break;
            }
            case -1183699191: {
                if(!arg6.equals("invite")) {
                    goto label_99;
                }

                v0 = 9;
                break;
            }
            case -938102371: {
                if(!arg6.equals("rating")) {
                    goto label_99;
                }

                v0 = 6;
                break;
            }
            case -906336856: {
                if(!arg6.equals("search")) {
                    goto label_99;
                }

                v0 = 4;
                break;
            }
            case -902468296: {
                if(!arg6.equals("signUp")) {
                    goto label_99;
                }

                v0 = 7;
                break;
            }
            default: {
                goto label_99;
            }
        }

    label_100:
        switch(v0) {
            case 0: {
                return "ecommerce_purchase";
            }
            case 1: {
                return "add_to_cart";
            }
            case 2: {
                return "begin_checkout";
            }
            case 3: {
                return "select_content";
            }
            case 4: {
                return "search";
            }
            case 5: {
                return "share";
            }
            case 6: {
                return "rate_content";
            }
            case 7: {
                return "sign_up";
            }
            case 8: {
                return "login";
            }
            case 9: {
                return "invite";
            }
            case 10: {
                return "level_start";
            }
            case 11: {
                return "level_end";
            }
        }

        return this.a(arg6);
    }

    private void a(Bundle arg3, String arg4, Double arg5) {
        arg5 = this.a(arg5);
        if(arg5 == null) {
            return;
        }

        arg3.putDouble(arg4, arg5.doubleValue());
    }

    private void a(Bundle arg1, String arg2, Integer arg3) {
        if(arg3 == null) {
            return;
        }

        arg1.putInt(arg2, arg3.intValue());
    }

    private void a(Bundle arg3, String arg4, Long arg5) {
        if(arg5 == null) {
            return;
        }

        arg3.putLong(arg4, arg5.longValue());
    }

    private void a(Bundle arg1, String arg2, String arg3) {
        if(arg3 == null) {
            return;
        }

        arg1.putString(arg2, arg3);
    }

    private void a(Bundle arg5, Map arg6) {
        Iterator v6 = arg6.entrySet().iterator();
        while(v6.hasNext()) {
            Object v0 = v6.next();
            Object v1 = ((Map$Entry)v0).getValue();
            String v2 = this.b(((Map$Entry)v0).getKey());
            if((v1 instanceof String)) {
                arg5.putString(v2, ((Map$Entry)v0).getValue().toString());
                continue;
            }

            if((v1 instanceof Double)) {
                arg5.putDouble(v2, ((Map$Entry)v0).getValue().doubleValue());
                continue;
            }

            if((v1 instanceof Long)) {
                arg5.putLong(v2, ((Map$Entry)v0).getValue().longValue());
                continue;
            }

            if(!(v1 instanceof Integer)) {
                continue;
            }

            arg5.putInt(v2, ((Map$Entry)v0).getValue().intValue());
        }
    }

    private String b(String arg4) {
        if(arg4 != null) {
            if(arg4.length() == 0) {
            }
            else {
                arg4 = arg4.replaceAll("[^\\p{Alnum}_]+", "_");
                if((arg4.startsWith("ga_")) || (arg4.startsWith("google_")) || (arg4.startsWith("firebase_")) || !Character.isLetter(arg4.charAt(0))) {
                    arg4 = "fabric_" + arg4;
                }

                int v2 = 40;
                if(arg4.length() > v2) {
                    arg4 = arg4.substring(0, v2);
                }

                return arg4;
            }
        }

        return "fabric_unnamed_parameter";
    }

    private Bundle b(ad arg5) {
        Object v2_1;
        String v3;
        Map v2;
        String v1;
        Bundle v0 = new Bundle();
        if("purchase".equals(arg5.g)) {
            this.a(v0, "item_id", arg5.h.get("itemId"));
            this.a(v0, "item_name", arg5.h.get("itemName"));
            this.a(v0, "item_category", arg5.h.get("itemType"));
            v1 = "value";
            v2 = arg5.h;
            v3 = "itemPrice";
            goto label_24;
        }
        else if("addToCart".equals(arg5.g)) {
            this.a(v0, "item_id", arg5.h.get("itemId"));
            this.a(v0, "item_name", arg5.h.get("itemName"));
            this.a(v0, "item_category", arg5.h.get("itemType"));
            this.a(v0, "price", this.b(arg5.h.get("itemPrice")));
            this.a(v0, "value", this.b(arg5.h.get("itemPrice")));
            this.a(v0, "currency", arg5.h.get("currency"));
            v0.putLong("quantity", 1);
        }
        else if("startCheckout".equals(arg5.g)) {
            this.a(v0, "quantity", Long.valueOf(((long)arg5.h.get("itemCount").intValue())));
            v1 = "value";
            v2 = arg5.h;
            v3 = "totalPrice";
        label_24:
            this.a(v0, v1, this.b(v2.get(v3)));
            v1 = "currency";
            v2 = arg5.h;
            v3 = "currency";
            goto label_30;
        }
        else {
            if(!"contentView".equals(arg5.g)) {
                if("search".equals(arg5.g)) {
                    v1 = "search_term";
                    v2 = arg5.h;
                    v3 = "query";
                    goto label_30;
                }
                else {
                    if("share".equals(arg5.g)) {
                        v1 = "method";
                        v2_1 = arg5.h.get("method");
                    }
                    else if("rating".equals(arg5.g)) {
                        v1 = "rating";
                        String v2_2 = String.valueOf(arg5.h.get("rating"));
                    }
                    else {
                        goto label_135;
                    }

                    this.a(v0, v1, ((String)v2_1));
                }
            }

            this.a(v0, "content_type", arg5.h.get("contentType"));
            this.a(v0, "item_id", arg5.h.get("contentId"));
            v1 = "item_name";
            v2 = arg5.h;
            v3 = "contentName";
            goto label_30;
        label_135:
            if(!"signUp".equals(arg5.g)) {
                if("login".equals(arg5.g)) {
                }
                else if("invite".equals(arg5.g)) {
                }
                else {
                    goto label_153;
                }
            }

            v1 = "method";
            v2 = arg5.h;
            v3 = "method";
            goto label_30;
        label_153:
            if("levelStart".equals(arg5.g)) {
                v1 = "level_name";
                v2 = arg5.h;
                v3 = "levelName";
            label_30:
                this.a(v0, v1, v2.get(v3));
                goto label_182;
            }

            if(!"levelEnd".equals(arg5.g)) {
                goto label_182;
            }

            this.a(v0, "score", this.a(arg5.h.get("score")));
            this.a(v0, "level_name", arg5.h.get("levelName"));
            this.a(v0, "success", this.c(arg5.h.get("success")));
        }

    label_182:
        this.a(v0, arg5.f);
        return v0;
    }

    private Double b(Object arg4) {
        if(arg4 == null) {
            return null;
        }

        return Double.valueOf(new BigDecimal(((Long)arg4).longValue()).divide(a.a).doubleValue());
    }

    private Integer c(String arg2) {
        if(arg2 == null) {
            return null;
        }

        return Integer.valueOf(arg2.equals("true"));
    }
}


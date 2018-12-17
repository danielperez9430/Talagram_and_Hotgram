package c.a.a.a.a.g;

import org.json.JSONObject;

class k implements v {
    k() {
        super();
    }

    private long a(c.a.a.a.a.b.k arg5, long arg6, JSONObject arg8) {
        long v5 = arg8.has("expires_at") ? arg8.getLong("expires_at") : arg6 * 1000 + arg5.a();
        return v5;
    }

    private e a(JSONObject arg10) {
        String v2 = arg10.getString("identifier");
        String v3 = arg10.getString("status");
        String v4 = arg10.getString("url");
        String v5 = arg10.getString("reports_url");
        String v6 = arg10.getString("ndk_reports_url");
        boolean v7 = arg10.optBoolean("update_required", false);
        c v10 = !arg10.has("icon") || !arg10.getJSONObject("icon").has("hash") ? null : this.b(arg10.getJSONObject("icon"));
        return new e(v2, v3, v4, v5, v6, v7, v10);
    }

    public t a(c.a.a.a.a.b.k arg14, JSONObject arg15) {
        int v11 = arg15.optInt("settings_version", 0);
        int v12 = arg15.optInt("cache_duration", 3600);
        return new t(this.a(arg14, ((long)v12), arg15), this.a(arg15.getJSONObject("app")), this.e(arg15.getJSONObject("session")), this.f(arg15.getJSONObject("prompt")), this.c(arg15.getJSONObject("features")), this.d(arg15.getJSONObject("analytics")), this.g(arg15.getJSONObject("beta")), v11, v12);
    }

    private c b(JSONObject arg4) {
        return new c(arg4.getString("hash"), arg4.getInt("width"), arg4.getInt("height"));
    }

    private m c(JSONObject arg6) {
        return new m(arg6.optBoolean("prompt_enabled", false), arg6.optBoolean("collect_logged_exceptions", true), arg6.optBoolean("collect_reports", true), arg6.optBoolean("collect_analytics", false));
    }

    private b d(JSONObject arg15) {
        return new b(arg15.optString("url", "https://e.crashlytics.com/spi/v2/events"), arg15.optInt("flush_interval_secs", 600), arg15.optInt("max_byte_size_per_file", 8000), arg15.optInt("max_file_count_per_send", 1), arg15.optInt("max_pending_send_file_count", 100), arg15.optBoolean("forward_to_google_analytics", false), arg15.optBoolean("include_purchase_events_in_forwarded_events", false), arg15.optBoolean("track_custom_events", true), arg15.optBoolean("track_predefined_events", true), arg15.optInt("sampling_rate", 1), arg15.optBoolean("flush_on_background", true));
    }

    private p e(JSONObject arg11) {
        return new p(arg11.optInt("log_buffer_size", 64000), arg11.optInt("max_chained_exception_depth", 8), arg11.optInt("max_custom_exception_events", 64), arg11.optInt("max_custom_key_value_pairs", 64), arg11.optInt("identifier_mask", 255), arg11.optBoolean("send_session_without_crash", false), arg11.optInt("max_complete_sessions_count", 4));
    }

    private o f(JSONObject arg11) {
        return new o(arg11.optString("title", "Send Crash Report?"), arg11.optString("message", "Looks like we crashed! Please help us fix the problem by sending a crash report."), arg11.optString("send_button_title", "Send"), arg11.optBoolean("show_cancel_button", true), arg11.optString("cancel_button_title", "Don\'t Send"), arg11.optBoolean("show_always_send_button", true), arg11.optString("always_send_button_title", "Always Send"));
    }

    private f g(JSONObject arg4) {
        return new f(arg4.optString("update_endpoint", u.a), arg4.optInt("update_suspend_duration", 3600));
    }
}


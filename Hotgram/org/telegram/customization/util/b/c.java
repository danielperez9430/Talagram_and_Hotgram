package org.telegram.customization.util.b;

import android.content.Context;
import android.preference.PreferenceManager;

public class c {
    private Context a;

    c(Context arg1) {
        super();
        this.a = arg1;
    }

    public String a() {
        return PreferenceManager.getDefaultSharedPreferences(this.a).getString("pl.aprilapps.folder_name", "EasyImage");
    }

    public boolean b() {
        return PreferenceManager.getDefaultSharedPreferences(this.a).getBoolean("pl.aprilapps.easyimage.copy_taken_photos", false);
    }

    public boolean c() {
        return PreferenceManager.getDefaultSharedPreferences(this.a).getBoolean("pl.aprilapps.easyimage.copy_picked_images", false);
    }
}


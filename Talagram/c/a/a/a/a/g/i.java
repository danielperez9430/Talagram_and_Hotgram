package c.a.a.a.a.g;

import c.a.a.a.a.f.b;
import c.a.a.a.c;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import org.json.JSONObject;

class i implements g {
    private final c.a.a.a.i a;

    public i(c.a.a.a.i arg1) {
        super();
        this.a = arg1;
    }

    public JSONObject a() {
        JSONObject v0_3;
        FileInputStream v0_2;
        JSONObject v3;
        FileInputStream v2;
        c.h().a("Fabric", "Reading cached settings...");
        Closeable v0 = null;
        try {
            File v1_2 = new File(new b(this.a).a(), "com.crashlytics.settings.json");
            if(v1_2.exists()) {
                v2 = new FileInputStream(v1_2);
            }
            else {
                goto label_23;
            }
        }
        catch(Throwable v1) {
            goto label_33;
        }
        catch(Exception v1_1) {
            goto label_35;
        }

        try {
            v3 = new JSONObject(c.a.a.a.a.b.i.a(((InputStream)v2)));
            v0_2 = v2;
            goto label_28;
        }
        catch(Throwable v0_1) {
        }
        catch(Exception v1_1) {
            goto label_36;
            try {
            label_23:
                c.h().a("Fabric", "No cached settings found.");
                v3 = ((JSONObject)v0);
                goto label_28;
            }
            catch(Throwable v1) {
            label_33:
            }
            catch(Exception v1_1) {
            label_35:
                Closeable v2_1 = v0;
                try {
                label_36:
                    c.h().e("Fabric", "Failed to fetch cached settings", ((Throwable)v1_1));
                }
                catch(Throwable v0_1) {
                    v1 = v0_1;
                    v0_2 = ((FileInputStream)v2_1);
                    goto label_46;
                }

                c.a.a.a.a.b.i.a(v2_1, "Error while closing settings cache file.");
                return v0_3;
            }
        }

    label_46:
        c.a.a.a.a.b.i.a(v0, "Error while closing settings cache file.");
        throw v1;
    label_28:
        c.a.a.a.a.b.i.a(v0, "Error while closing settings cache file.");
        v0_3 = v3;
        return v0_3;
    }

    public void a(long arg4, JSONObject arg6) {
        FileWriter v0_1;
        FileWriter v4;
        c.h().a("Fabric", "Writing settings to cache file...");
        if(arg6 == null) {
            return;
        }

        Closeable v0 = null;
        try {
            arg6.put("expires_at", arg4);
            v4 = new FileWriter(new File(new b(this.a).a(), "com.crashlytics.settings.json"));
            goto label_17;
        }
        catch(Throwable v5) {
        }
        catch(Exception v5_1) {
            goto label_32;
            try {
            label_17:
                v4.write(arg6.toString());
                v4.flush();
                goto label_20;
            }
            catch(Throwable v5) {
                v0_1 = v4;
            }
            catch(Exception v5_1) {
                v0_1 = v4;
                try {
                label_32:
                    c.h().e("Fabric", "Failed to cache settings", ((Throwable)v5_1));
                }
                catch(Throwable v5) {
                    goto label_39;
                }

                c.a.a.a.a.b.i.a(((Closeable)v0_1), "Failed to close settings writer.");
                return;
            }
        }

    label_39:
        c.a.a.a.a.b.i.a(((Closeable)v0_1), "Failed to close settings writer.");
        throw v5;
    label_20:
        c.a.a.a.a.b.i.a(((Closeable)v4), "Failed to close settings writer.");
    }
}


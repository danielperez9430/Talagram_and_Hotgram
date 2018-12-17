package androidx.work.impl.b;

import android.net.Uri;
import androidx.work.a;
import androidx.work.d;
import androidx.work.k;
import androidx.work.o;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Iterator;

public class p {
    class androidx.work.impl.b.p$1 {
        static {
            androidx.work.impl.b.p$1.c = new int[k.values().length];
            try {
                androidx.work.impl.b.p$1.c[k.a.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
            label_9:
                int v1 = 2;
                try {
                    androidx.work.impl.b.p$1.c[k.b.ordinal()] = v1;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                label_14:
                    int v2 = 3;
                    try {
                        androidx.work.impl.b.p$1.c[k.c.ordinal()] = v2;
                        goto label_19;
                    }
                    catch(NoSuchFieldError ) {
                    label_19:
                        int v3 = 4;
                        try {
                            androidx.work.impl.b.p$1.c[k.d.ordinal()] = v3;
                            goto label_24;
                        }
                        catch(NoSuchFieldError ) {
                        label_24:
                            int v4 = 5;
                            try {
                                androidx.work.impl.b.p$1.c[k.e.ordinal()] = v4;
                                goto label_29;
                            }
                            catch(NoSuchFieldError ) {
                            label_29:
                                androidx.work.impl.b.p$1.b = new int[a.values().length];
                                try {
                                    androidx.work.impl.b.p$1.b[a.a.ordinal()] = 1;
                                    goto label_37;
                                }
                                catch(NoSuchFieldError ) {
                                    try {
                                    label_37:
                                        androidx.work.impl.b.p$1.b[a.b.ordinal()] = v1;
                                        goto label_41;
                                    }
                                    catch(NoSuchFieldError ) {
                                    label_41:
                                        androidx.work.impl.b.p$1.a = new int[o.values().length];
                                        try {
                                            androidx.work.impl.b.p$1.a[o.a.ordinal()] = 1;
                                            goto label_49;
                                        }
                                        catch(NoSuchFieldError ) {
                                            try {
                                            label_49:
                                                androidx.work.impl.b.p$1.a[o.b.ordinal()] = v1;
                                                goto label_53;
                                            }
                                            catch(NoSuchFieldError ) {
                                                try {
                                                label_53:
                                                    androidx.work.impl.b.p$1.a[o.c.ordinal()] = v2;
                                                    goto label_57;
                                                }
                                                catch(NoSuchFieldError ) {
                                                    try {
                                                    label_57:
                                                        androidx.work.impl.b.p$1.a[o.d.ordinal()] = v3;
                                                        goto label_61;
                                                    }
                                                    catch(NoSuchFieldError ) {
                                                        try {
                                                        label_61:
                                                            androidx.work.impl.b.p$1.a[o.e.ordinal()] = v4;
                                                            goto label_65;
                                                        }
                                                        catch(NoSuchFieldError ) {
                                                            try {
                                                            label_65:
                                                                androidx.work.impl.b.p$1.a[o.f.ordinal()] = 6;
                                                                return;
                                                            }
                                                            catch(NoSuchFieldError ) {
                                                                return;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static int a(a arg3) {
        switch(androidx.work.impl.b.p$1.b[arg3.ordinal()]) {
            case 1: {
                return 0;
            }
            case 2: {
                return 1;
            }
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Could not convert ");
        v1.append(arg3);
        v1.append(" to int");
        throw new IllegalArgumentException(v1.toString());
        return 0;
    }

    public static int a(k arg3) {
        switch(androidx.work.impl.b.p$1.c[arg3.ordinal()]) {
            case 1: {
                return 0;
            }
            case 2: {
                return 1;
            }
            case 3: {
                return 2;
            }
            case 4: {
                return 3;
            }
            case 5: {
                return 4;
            }
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Could not convert ");
        v1.append(arg3);
        v1.append(" to int");
        throw new IllegalArgumentException(v1.toString());
        return 3;
    }

    public static int a(o arg3) {
        switch(androidx.work.impl.b.p$1.a[arg3.ordinal()]) {
            case 1: {
                return 0;
            }
            case 2: {
                return 1;
            }
            case 3: {
                return 2;
            }
            case 4: {
                return 3;
            }
            case 5: {
                return 4;
            }
            case 6: {
                return 5;
            }
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Could not convert ");
        v1.append(arg3);
        v1.append(" to int");
        throw new IllegalArgumentException(v1.toString());
        return 4;
    }

    public static d a(byte[] arg6) {
        IOException v6_1;
        Throwable v6_2;
        ObjectInputStream v2_1;
        d v0 = new d();
        if(arg6 == null) {
            return v0;
        }

        ByteArrayInputStream v1 = new ByteArrayInputStream(arg6);
        ObjectInputStream v6 = null;
        try {
            v2_1 = new ObjectInputStream(((InputStream)v1));
        }
        catch(Throwable v0_1) {
            v2_1 = v6;
            v6_2 = v0_1;
            goto label_45;
        }
        catch(IOException v2) {
            IOException v5 = v2;
            v2_1 = v6;
            v6_1 = v5;
            goto label_33;
        }

        try {
            int v6_3;
            for(v6_3 = v2_1.readInt(); v6_3 > 0; --v6_3) {
                v0.a(Uri.parse(v2_1.readUTF()), v2_1.readBoolean());
            }
        }
        catch(IOException v6_1) {
            goto label_24;
        }
        catch(Throwable v6_2) {
            goto label_45;
        }

        try {
            v2_1.close();
        }
        catch(IOException v6_1) {
            v6_1.printStackTrace();
        }

        try {
            v1.close();
            return v0;
        }
        catch(IOException v6_1) {
            goto label_42;
        }

    label_24:
        try {
        label_33:
            v6_1.printStackTrace();
            if(v2_1 == null) {
                goto label_39;
            }
        }
        catch(Throwable v6_2) {
            goto label_45;
        }

        try {
            v2_1.close();
        }
        catch(IOException v6_1) {
            v6_1.printStackTrace();
        }

        try {
        label_39:
            v1.close();
        }
        catch(IOException v6_1) {
        label_42:
            v6_1.printStackTrace();
        }

        return v0;
    label_45:
        if(v2_1 == null) {
            goto label_50;
        }

        try {
            v2_1.close();
        }
        catch(IOException v0_2) {
            v0_2.printStackTrace();
        }

        try {
        label_50:
            v1.close();
        }
        catch(IOException v0_2) {
            v0_2.printStackTrace();
        }

        throw v6_2;
    }

    public static o a(int arg3) {
        switch(arg3) {
            case 0: {
                goto label_22;
            }
            case 1: {
                goto label_20;
            }
            case 2: {
                goto label_18;
            }
            case 3: {
                goto label_16;
            }
            case 4: {
                goto label_14;
            }
            case 5: {
                goto label_12;
            }
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Could not convert ");
        v1.append(arg3);
        v1.append(" to State");
        throw new IllegalArgumentException(v1.toString());
    label_18:
        return o.c;
    label_20:
        return o.b;
    label_22:
        return o.a;
    label_12:
        return o.f;
    label_14:
        return o.e;
    label_16:
        return o.d;
    }

    public static byte[] a(d arg4) {
        ObjectOutputStream v1_2;
        ObjectOutputStream v2;
        byte[] v1 = null;
        if(arg4.a() == 0) {
            return v1;
        }

        ByteArrayOutputStream v0 = new ByteArrayOutputStream();
        try {
            v2 = new ObjectOutputStream(((OutputStream)v0));
            goto label_8;
        }
        catch(Throwable v4) {
        }
        catch(IOException v4_1) {
            goto label_35;
            try {
            label_8:
                v2.writeInt(arg4.a());
                Iterator v4_2 = arg4.iterator();
                while(v4_2.hasNext()) {
                    Object v1_1 = v4_2.next();
                    v2.writeUTF(((androidx.work.d$a)v1_1).a().toString());
                    v2.writeBoolean(((androidx.work.d$a)v1_1).b());
                }
            }
            catch(Throwable v4) {
                goto label_47;
            }
            catch(IOException v4_1) {
                goto label_29;
            }

            try {
                v2.close();
            }
            catch(IOException v4_1) {
                v4_1.printStackTrace();
            }

            try {
                v0.close();
                goto label_45;
            }
            catch(IOException v4_1) {
                goto label_44;
            }

        label_29:
            v1_2 = v2;
            try {
            label_35:
                v4_1.printStackTrace();
                if(v1_2 == null) {
                    goto label_41;
                }
            }
            catch(Throwable v4) {
                v2 = v1_2;
                goto label_47;
            }
        }

        try {
            v1_2.close();
        }
        catch(IOException v4_1) {
            v4_1.printStackTrace();
        }

        try {
        label_41:
            v0.close();
        }
        catch(IOException v4_1) {
        label_44:
            v4_1.printStackTrace();
        }

    label_45:
        return v0.toByteArray();
    label_47:
        if(v2 == null) {
            goto label_52;
        }

        try {
            v2.close();
        }
        catch(IOException v1_3) {
            v1_3.printStackTrace();
        }

        try {
        label_52:
            v0.close();
        }
        catch(IOException v0_1) {
            v0_1.printStackTrace();
        }

        throw v4;
    }

    public static a b(int arg3) {
        switch(arg3) {
            case 0: {
                goto label_14;
            }
            case 1: {
                goto label_12;
            }
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Could not convert ");
        v1.append(arg3);
        v1.append(" to BackoffPolicy");
        throw new IllegalArgumentException(v1.toString());
    label_12:
        return a.b;
    label_14:
        return a.a;
    }

    public static k c(int arg3) {
        switch(arg3) {
            case 0: {
                goto label_20;
            }
            case 1: {
                goto label_18;
            }
            case 2: {
                goto label_16;
            }
            case 3: {
                goto label_14;
            }
            case 4: {
                goto label_12;
            }
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Could not convert ");
        v1.append(arg3);
        v1.append(" to NetworkType");
        throw new IllegalArgumentException(v1.toString());
    label_18:
        return k.b;
    label_20:
        return k.a;
    label_12:
        return k.e;
    label_14:
        return k.d;
    label_16:
        return k.c;
    }
}


package android.support.v4.e;

import android.text.SpannableStringBuilder;
import java.util.Locale;

public final class a {
    public final class android.support.v4.e.a$a {
        private boolean a;
        private int b;
        private d c;

        public android.support.v4.e.a$a() {
            super();
            this.a(a.a(Locale.getDefault()));
        }

        public a a() {
            if(this.b == 2 && this.c == a.a) {
                return android.support.v4.e.a$a.b(this.a);
            }

            return new a(this.a, this.b, this.c);
        }

        private void a(boolean arg1) {
            this.a = arg1;
            this.c = a.a;
            this.b = 2;
        }

        private static a b(boolean arg0) {
            a v0 = arg0 ? a.c : a.b;
            return v0;
        }
    }

    class b {
        private static final byte[] a;
        private final CharSequence b;
        private final boolean c;
        private final int d;
        private int e;
        private char f;

        static {
            int v0 = 1792;
            b.a = new byte[v0];
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                b.a[v1] = Character.getDirectionality(v1);
            }
        }

        b(CharSequence arg1, boolean arg2) {
            super();
            this.b = arg1;
            this.c = arg2;
            this.d = arg1.length();
        }

        int a() {
            this.e = 0;
            int v1 = -1;
            int v3 = 0;
            int v4 = 0;
            int v5 = 0;
        label_7:
            while(this.e < this.d) {
                if(v3 != 0) {
                    break;
                }

                int v6 = this.c();
                if(v6 == 9) {
                    continue;
                }

                switch(v6) {
                    case 0: {
                        goto label_28;
                    }
                    case 1: 
                    case 2: {
                        goto label_26;
                    }
                    default: {
                        switch(v6) {
                            case 14: 
                            case 15: {
                                ++v5;
                                v4 = -1;
                                goto label_7;
                            label_26:
                                if(v5 == 0) {
                                    return 1;
                                label_28:
                                    if(v5 == 0) {
                                        return v1;
                                    }
                                    else {
                                        goto label_30;
                                    }
                                }
                                else {
                                    goto label_30;
                                }

                                goto label_32;
                            }
                            case 16: 
                            case 17: {
                                ++v5;
                                v4 = 1;
                                goto label_7;
                            }
                            case 18: {
                                --v5;
                                v4 = 0;
                                goto label_7;
                            }
                            default: {
                                goto label_30;
                            }
                        }
                    }
                }

            label_30:
                v3 = v5;
            }

        label_32:
            if(v3 == 0) {
                return 0;
            }

            if(v4 != 0) {
                return v4;
            }

            while(this.e > 0) {
                switch(this.d()) {
                    case 14: 
                    case 15: {
                        goto label_45;
                    }
                    case 16: 
                    case 17: {
                        goto label_43;
                    }
                    case 18: {
                        goto label_41;
                    }
                }

                continue;
            label_41:
                ++v5;
                continue;
            label_43:
                if(v3 != v5) {
                    goto label_47;
                }

                return 1;
            label_45:
                if(v3 == v5) {
                    return v1;
                }

            label_47:
                --v5;
            }

            return 0;
        }

        private static byte a(char arg1) {
            byte v1 = arg1 < 1792 ? b.a[arg1] : Character.getDirectionality(arg1);
            return v1;
        }

        int b() {
            this.e = this.d;
            int v1 = 0;
            int v2 = 0;
        label_5:
            while(this.e > 0) {
                int v3 = this.d();
                if(v3 == 9) {
                    continue;
                }

                int v5 = -1;
                switch(v3) {
                    case 0: {
                        goto label_28;
                    }
                    case 1: 
                    case 2: {
                        goto label_24;
                    }
                    default: {
                        switch(v3) {
                            case 14: 
                            case 15: {
                                if(v1 == v2) {
                                    return v5;
                                }

                            label_22:
                                --v2;
                                goto label_5;
                            }
                            case 16: 
                            case 17: {
                                if(v1 != v2) {
                                    goto label_22;
                                }

                                return 1;
                            }
                            case 18: {
                                ++v2;
                                goto label_5;
                            label_24:
                                if(v2 == 0) {
                                    return 1;
                                }

                                if(v1 != 0) {
                                    goto label_5;
                                }

                                goto label_31;
                            label_28:
                                if(v2 == 0) {
                                    return v5;
                                }

                                if(v1 != 0) {
                                    goto label_5;
                                }

                                goto label_31;
                            }
                            default: {
                                if(v1 != 0) {
                                    goto label_5;
                                }

                                goto label_31;
                            }
                        }
                    }
                }

            label_31:
                v1 = v2;
            }

            return 0;
        }

        byte c() {
            this.f = this.b.charAt(this.e);
            if(Character.isHighSurrogate(this.f)) {
                int v0 = Character.codePointAt(this.b, this.e);
                this.e += Character.charCount(v0);
                return Character.getDirectionality(v0);
            }

            ++this.e;
            byte v0_1 = b.a(this.f);
            if(this.c) {
                if(this.f == 60) {
                    v0_1 = this.e();
                }
                else if(this.f == 38) {
                    v0_1 = this.g();
                }
            }

            return v0_1;
        }

        byte d() {
            this.f = this.b.charAt(this.e - 1);
            if(Character.isLowSurrogate(this.f)) {
                int v0 = Character.codePointBefore(this.b, this.e);
                this.e -= Character.charCount(v0);
                return Character.getDirectionality(v0);
            }

            --this.e;
            byte v0_1 = b.a(this.f);
            if(this.c) {
                if(this.f == 62) {
                    v0_1 = this.f();
                }
                else if(this.f == 59) {
                    v0_1 = this.h();
                }
            }

            return v0_1;
        }

        private byte e() {
            int v0 = this.e;
            do {
            label_1:
                if(this.e >= this.d) {
                    goto label_33;
                }

                CharSequence v1 = this.b;
                int v2 = this.e;
                this.e = v2 + 1;
                this.f = v1.charAt(v2);
                if(this.f == 62) {
                    return 12;
                }

                if(this.f == 34) {
                    break;
                }
            }
            while(this.f != 39);

            char v1_1 = this.f;
            goto label_22;
        label_33:
            this.e = v0;
            this.f = '<';
            return 13;
            while(true) {
            label_22:
                if(this.e >= this.d) {
                    goto label_1;
                }

                CharSequence v2_1 = this.b;
                int v3 = this.e;
                this.e = v3 + 1;
                char v2_2 = v2_1.charAt(v3);
                this.f = v2_2;
                if(v2_2 == v1_1) {
                    goto label_1;
                }
            }
        }

        private byte f() {
            int v3;
            char v2;
            int v0 = this.e;
            do {
            label_1:
                v2 = '>';
                if(this.e > 0) {
                    CharSequence v1 = this.b;
                    v3 = this.e - 1;
                    this.e = v3;
                    this.f = v1.charAt(v3);
                    if(this.f == 60) {
                        return 12;
                    }
                    else if(this.f == v2) {
                    }
                    else {
                        if(this.f != 34 && this.f != 39) {
                            continue;
                        }

                        break;
                    }
                }

                goto label_35;
            }
            while(true);

            char v1_1 = this.f;
            goto label_25;
        label_35:
            this.e = v0;
            this.f = v2;
            return 13;
            while(true) {
            label_25:
                if(this.e <= 0) {
                    goto label_1;
                }

                CharSequence v2_1 = this.b;
                v3 = this.e - 1;
                this.e = v3;
                v2 = v2_1.charAt(v3);
                this.f = v2;
                if(v2 == v1_1) {
                    goto label_1;
                }
            }
        }

        private byte g() {
            while(this.e < this.d) {
                CharSequence v0 = this.b;
                int v1 = this.e;
                this.e = v1 + 1;
                char v0_1 = v0.charAt(v1);
                this.f = v0_1;
                if(v0_1 == 59) {
                    return 12;
                }
            }

            return 12;
        }

        private byte h() {
            char v2;
            int v0 = this.e;
            do {
                v2 = ';';
                if(this.e <= 0) {
                    break;
                }

                CharSequence v1 = this.b;
                int v3 = this.e - 1;
                this.e = v3;
                this.f = v1.charAt(v3);
                if(this.f == 38) {
                    return 12;
                }
                else if(this.f != v2) {
                    continue;
                }

                break;
            }
            while(true);

            this.e = v0;
            this.f = v2;
            return 13;
        }
    }

    static final d a;
    static final a b;
    static final a c;
    private static final String d;
    private static final String e;
    private final boolean f;
    private final int g;
    private final d h;

    static {
        a.a = e.c;
        a.d = Character.toString('‎');
        a.e = Character.toString('‏');
        a.b = new a(false, 2, a.a);
        a.c = new a(true, 2, a.a);
    }

    a(boolean arg1, int arg2, d arg3) {
        super();
        this.f = arg1;
        this.g = arg2;
        this.h = arg3;
    }

    public static a a() {
        return new android.support.v4.e.a$a().a();
    }

    public CharSequence a(CharSequence arg3) {
        return this.a(arg3, this.h, true);
    }

    private String a(CharSequence arg3, d arg4) {
        boolean v4 = arg4.a(arg3, 0, arg3.length());
        if(!this.f && ((v4) || a.b(arg3) == 1)) {
            return a.d;
        }

        if((this.f) && (!v4 || a.b(arg3) == -1)) {
            return a.e;
        }

        return "";
    }

    static boolean a(Locale arg1) {
        boolean v0 = true;
        if(f.a(arg1) == 1) {
        }
        else {
            v0 = false;
        }

        return v0;
    }

    public CharSequence a(CharSequence arg3, d arg4, boolean arg5) {
        if(arg3 == null) {
            return null;
        }

        boolean v4 = arg4.a(arg3, 0, arg3.length());
        SpannableStringBuilder v0 = new SpannableStringBuilder();
        if((this.b()) && (arg5)) {
            d v1 = v4 ? e.b : e.a;
            v0.append(this.b(arg3, v1));
        }

        if(v4 != this.f) {
            char v1_1 = v4 ? '‫' : '‪';
            v0.append(v1_1);
            v0.append(arg3);
            v0.append('‬');
        }
        else {
            v0.append(arg3);
        }

        if(arg5) {
            arg4 = v4 ? e.b : e.a;
            v0.append(this.a(arg3, arg4));
        }

        return ((CharSequence)v0);
    }

    private static int b(CharSequence arg2) {
        return new b(arg2, false).b();
    }

    private String b(CharSequence arg3, d arg4) {
        boolean v4 = arg4.a(arg3, 0, arg3.length());
        if(!this.f && ((v4) || a.c(arg3) == 1)) {
            return a.d;
        }

        if((this.f) && (!v4 || a.c(arg3) == -1)) {
            return a.e;
        }

        return "";
    }

    public boolean b() {
        boolean v0 = (this.g & 2) != 0 ? true : false;
        return v0;
    }

    private static int c(CharSequence arg2) {
        return new b(arg2, false).a();
    }
}


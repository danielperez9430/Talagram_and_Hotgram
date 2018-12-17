package com.persianswitch.a.a.d;

import javax.security.auth.x500.X500Principal;

final class c {
    private final String a;
    private final int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private char[] g;

    public c(X500Principal arg2) {
        super();
        this.a = arg2.getName("RFC2253");
        this.b = this.a.length();
    }

    private int a(int arg9) {
        int v0 = arg9 + 1;
        if(v0 >= this.b) {
            goto label_58;
        }

        arg9 = this.g[arg9];
        int v1 = 70;
        int v2 = 65;
        int v3 = 102;
        int v4 = 97;
        int v5 = 57;
        int v6 = 48;
        if(arg9 < v6 || arg9 > v5) {
            if(arg9 >= v4 && arg9 <= v3) {
                arg9 += -87;
                goto label_22;
            }

            if(arg9 >= v2 && arg9 <= v1) {
                arg9 += -55;
                goto label_22;
            }

            goto label_48;
        }
        else {
            arg9 -= v6;
        }

    label_22:
        v0 = this.g[v0];
        if(v0 < v6 || v0 > v5) {
            if(v0 >= v4 && v0 <= v3) {
                v0 += -87;
                goto label_35;
            }

            if(v0 >= v2 && v0 <= v1) {
                v0 += -55;
                goto label_35;
            }

            goto label_38;
        }
        else {
            v0 -= v6;
        }

    label_35:
        return (arg9 << 4) + v0;
    label_38:
        StringBuilder v0_1 = new StringBuilder();
        v0_1.append("Malformed DN: ");
        v0_1.append(this.a);
        throw new IllegalStateException(v0_1.toString());
    label_48:
        v0_1 = new StringBuilder();
        v0_1.append("Malformed DN: ");
        v0_1.append(this.a);
        throw new IllegalStateException(v0_1.toString());
    label_58:
        v0_1 = new StringBuilder();
        v0_1.append("Malformed DN: ");
        v0_1.append(this.a);
        throw new IllegalStateException(v0_1.toString());
    }

    private String a() {
        StringBuilder v1;
        while(true) {
            int v2 = 32;
            if(this.c < this.b && this.g[this.c] == v2) {
                ++this.c;
                continue;
            }

            break;
        }

        if(this.c == this.b) {
            return null;
        }

        this.d = this.c;
        while(true) {
            ++this.c;
            int v3 = 61;
            if(this.c < this.b && this.g[this.c] != v3 && this.g[this.c] != v2) {
                continue;
            }

            break;
        }

        if(this.c < this.b) {
            this.e = this.c;
            if(this.g[this.c] != v2) {
                goto label_77;
            }
        }
        else {
            v1 = new StringBuilder();
            v1.append("Unexpected end of DN: ");
            v1.append(this.a);
            throw new IllegalStateException(v1.toString());
        }

        while(this.c < this.b) {
            if(this.g[this.c] == v3) {
                break;
            }

            if(this.g[this.c] != v2) {
                break;
            }

            ++this.c;
        }

        if(this.g[this.c] != v3 || this.c == this.b) {
            v1 = new StringBuilder();
            v1.append("Unexpected end of DN: ");
            v1.append(this.a);
            throw new IllegalStateException(v1.toString());
        }

        while(true) {
        label_77:
            ++this.c;
            if(this.c < this.b && this.g[this.c] == v2) {
                continue;
            }

            break;
        }

        int v1_1 = 4;
        if(this.e - this.d > v1_1 && this.g[this.d + 3] == 46 && (this.g[this.d] == 79 || this.g[this.d] == 111) && (this.g[this.d + 1] == 73 || this.g[this.d + 1] == 105) && (this.g[this.d + 2] == 68 || this.g[this.d + 2] == 100)) {
            this.d += v1_1;
        }

        return new String(this.g, this.d, this.e - this.d);
    }

    public String a(String arg6) {
        StringBuilder v0_1;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = this.a.toCharArray();
        String v0 = this.a();
        String v1 = null;
        if(v0 == null) {
            return v1;
        }

        while(true) {
            String v2 = "";
            if(this.c == this.b) {
                return v1;
            }

            switch(this.g[this.c]) {
                case 34: {
                    v2 = this.b();
                    break;
                }
                case 35: {
                    v2 = this.c();
                    break;
                }
                case 43: 
                case 44: 
                case 59: {
                    break;
                }
                default: {
                    v2 = this.d();
                    break;
                }
            }

            if(arg6.equalsIgnoreCase(v0)) {
                return v2;
            }

            if(this.c >= this.b) {
                return v1;
            }

            if(this.g[this.c] != 44) {
                if(this.g[this.c] == 59) {
                }
                else if(this.g[this.c] == 43) {
                }
                else {
                    v0_1 = new StringBuilder();
                    v0_1.append("Malformed DN: ");
                    v0_1.append(this.a);
                    throw new IllegalStateException(v0_1.toString());
                }
            }

            ++this.c;
            v0 = this.a();
            if(v0 == null) {
                break;
            }
        }

        v0_1 = new StringBuilder();
        v0_1.append("Malformed DN: ");
        v0_1.append(this.a);
        throw new IllegalStateException(v0_1.toString());
        return v1;
    }

    private String b() {
        ++this.c;
        this.d = this.c;
        int v0 = this.d;
        while(true) {
            this.e = v0;
            if(this.c == this.b) {
                break;
            }

            if(this.g[this.c] != 34) {
                this.g[this.e] = this.g[this.c] == 92 ? this.e() : this.g[this.c];
                ++this.c;
                v0 = this.e + 1;
                continue;
            }

            goto label_15;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Unexpected end of DN: ");
        v1.append(this.a);
        throw new IllegalStateException(v1.toString());
        while(true) {
        label_15:
            ++this.c;
            if(this.c < this.b && this.g[this.c] == 32) {
                continue;
            }

            break;
        }

        return new String(this.g, this.d, this.e - this.d);
    }

    private String c() {
        if(this.c + 4 >= this.b) {
            goto label_101;
        }

        this.d = this.c;
        int v0 = this.c;
        while(true) {
            this.c = v0 + 1;
            if(this.c == this.b) {
                break;
            }
            else if(this.g[this.c] == 43) {
                break;
            }
            else if(this.g[this.c] == 44) {
                break;
            }
            else if(this.g[this.c] == 59) {
                break;
            }
            else {
                int v1 = 32;
                if(this.g[this.c] == v1) {
                    this.e = this.c;
                    goto label_35;
                }
                else {
                    if(this.g[this.c] >= 65 && this.g[this.c] <= 70) {
                        this.g[this.c] = ((char)(this.g[this.c] + v1));
                    }

                    v0 = this.c;
                    continue;
                }
            }

            goto label_66;
        }

        this.e = this.c;
        goto label_66;
        while(true) {
        label_35:
            ++this.c;
            if(this.c >= this.b) {
                break;
            }

            if(this.g[this.c] != v1) {
                break;
            }
        }

    label_66:
        v0 = this.e - this.d;
        if(v0 >= 5 && (v0 & 1) != 0) {
            byte[] v1_1 = new byte[v0 / 2];
            int v2 = 0;
            int v3 = this.d + 1;
            while(v2 < v1_1.length) {
                v1_1[v2] = ((byte)this.a(v3));
                v3 += 2;
                ++v2;
            }

            return new String(this.g, this.d, v0);
        }

        StringBuilder v1_2 = new StringBuilder();
        v1_2.append("Unexpected end of DN: ");
        v1_2.append(this.a);
        throw new IllegalStateException(v1_2.toString());
    label_101:
        v1_2 = new StringBuilder();
        v1_2.append("Unexpected end of DN: ");
        v1_2.append(this.a);
        throw new IllegalStateException(v1_2.toString());
    }

    private String d() {
        char[] v0_1;
        char v2;
        this.d = this.c;
        this.e = this.c;
        while(true) {
        label_4:
            if(this.c >= this.b) {
                goto label_7;
            }

            int v0 = this.g[this.c];
            int v1 = 59;
            v2 = ' ';
            if(v0 == v2) {
                goto label_52;
            }

            if(v0 != v1) {
                if(v0 != 92) {
                    switch(v0) {
                        case 43: 
                        case 44: {
                            goto label_44;
                        }
                        default: {
                            v0_1 = this.g;
                            v1 = this.e;
                            this.e = v1 + 1;
                            v0_1[v1] = this.g[this.c];
                            goto label_33;
                        label_37:
                            v0_1 = this.g;
                            v1 = this.e;
                            this.e = v1 + 1;
                            v0_1[v1] = this.e();
                        label_33:
                            ++this.c;
                            goto label_4;
                        }
                    }
                }
                else {
                    goto label_37;
                }

                goto label_33;
            }

            break;
        }

    label_44:
        return new String(this.g, this.d, this.e - this.d);
    label_52:
        this.f = this.e;
        ++this.c;
        v0_1 = this.g;
        int v3 = this.e;
        this.e = v3 + 1;
        v0_1[v3] = v2;
        goto label_62;
    label_7:
        return new String(this.g, this.d, this.e - this.d);
    label_62:
        while(this.c < this.b) {
            if(this.g[this.c] != v2) {
                break;
            }

            v0_1 = this.g;
            v3 = this.e;
            this.e = v3 + 1;
            v0_1[v3] = v2;
            ++this.c;
        }

        if(this.c != this.b && this.g[this.c] != 44 && this.g[this.c] != 43 && this.g[this.c] != v1) {
            goto label_4;
        }

        return new String(this.g, this.d, this.f - this.d);
    }

    private char e() {
        ++this.c;
        if(this.c != this.b) {
            int v0 = this.g[this.c];
            if(v0 != 32 && v0 != 37 && v0 != 92 && v0 != 95) {
                switch(v0) {
                    case 34: 
                    case 35: {
                        goto label_22;
                    }
                    default: {
                        switch(v0) {
                            case 42: 
                            case 43: 
                            case 44: {
                                goto label_22;
                            }
                            default: {
                                switch(v0) {
                                    case 59: 
                                    case 60: 
                                    case 61: 
                                    case 62: {
                                        goto label_22;
                                    }
                                    default: {
                                        return this.f();
                                    }
                                }
                            }
                        }
                    }
                }
            }

        label_22:
            return this.g[this.c];
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Unexpected end of DN: ");
        v1.append(this.a);
        throw new IllegalStateException(v1.toString());
    }

    private char f() {
        int v3;
        int v0 = this.a(this.c);
        ++this.c;
        int v1 = 128;
        if(v0 < v1) {
            return ((char)v0);
        }

        char v4 = '?';
        if(v0 >= 192 && v0 <= 247) {
            if(v0 <= 223) {
                v0 &= 31;
                v3 = 1;
            }
            else if(v0 <= 239) {
                v3 = 2;
                v0 &= 15;
            }
            else {
                v3 = 3;
                v0 &= 7;
            }

            int v5 = 0;
            while(true) {
                if(v5 >= v3) {
                    goto label_58;
                }

                ++this.c;
                if(this.c != this.b) {
                    if(this.g[this.c] != 92) {
                    }
                    else {
                        ++this.c;
                        int v6 = this.a(this.c);
                        ++this.c;
                        if((v6 & 192) != v1) {
                            return v4;
                        }
                        else {
                            v0 = (v0 << 6) + (v6 & 63);
                            ++v5;
                            continue;
                        }
                    }
                }

                return v4;
            }

            return v4;
        label_58:
            return ((char)v0);
        }

        return v4;
    }
}


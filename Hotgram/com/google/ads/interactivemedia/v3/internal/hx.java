package com.google.ads.interactivemedia.v3.internal;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

public class hx implements Closeable {
    final class com.google.ads.interactivemedia.v3.internal.hx$1 extends ha {
        com.google.ads.interactivemedia.v3.internal.hx$1() {
            super();
        }

        public void a(hx arg4) {
            if((arg4 instanceof hl)) {
                ((hl)arg4).o();
                return;
            }

            int v0 = arg4.a;
            if(v0 == 0) {
                v0 = arg4.r();
            }

            if(v0 == 13) {
                v0 = 9;
            }
            else if(v0 == 12) {
                v0 = 8;
            }
            else if(v0 == 14) {
                v0 = 10;
            }
            else {
                goto label_21;
            }

            arg4.a = v0;
            return;
        label_21:
            StringBuilder v1 = new StringBuilder();
            v1.append("Expected a name but was ");
            v1.append(arg4.f());
            v1.append(hx.a(arg4));
            throw new IllegalStateException(v1.toString());
        }
    }

    int a;
    private static final char[] b;
    private final Reader c;
    private boolean d;
    private final char[] e;
    private int f;
    private int g;
    private int h;
    private int i;
    private long j;
    private int k;
    private String l;
    private int[] m;
    private int n;
    private String[] o;
    private int[] p;

    static {
        hx.b = ")]}\'\n".toCharArray();
        ha.a = new com.google.ads.interactivemedia.v3.internal.hx$1();
    }

    public hx(Reader arg5) {
        super();
        this.d = false;
        this.e = new char[1024];
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.a = 0;
        this.m = new int[32];
        this.n = 0;
        int[] v0 = this.m;
        int v2 = this.n;
        this.n = v2 + 1;
        v0[v2] = 6;
        this.o = new String[32];
        this.p = new int[32];
        if(arg5 != null) {
            this.c = arg5;
            return;
        }

        throw new NullPointerException("in == null");
    }

    public void a() {
        int v0 = this.a;
        if(v0 == 0) {
            v0 = this.r();
        }

        if(v0 == 3) {
            this.a(1);
            this.p[this.n - 1] = 0;
            this.a = 0;
            return;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Expected BEGIN_ARRAY but was ");
        v1.append(this.f());
        v1.append(this.x());
        throw new IllegalStateException(v1.toString());
    }

    public final void a(boolean arg1) {
        this.d = arg1;
    }

    static String a(hx arg0) {
        return arg0.x();
    }

    private void a(int arg7) {
        int[] v0;
        if(this.n == this.m.length) {
            v0 = new int[this.n * 2];
            int[] v1 = new int[this.n * 2];
            String[] v2 = new String[this.n * 2];
            System.arraycopy(this.m, 0, v0, 0, this.n);
            System.arraycopy(this.p, 0, v1, 0, this.n);
            System.arraycopy(this.o, 0, v2, 0, this.n);
            this.m = v0;
            this.p = v1;
            this.o = v2;
        }

        v0 = this.m;
        int v1_1 = this.n;
        this.n = v1_1 + 1;
        v0[v1_1] = arg7;
    }

    private boolean a(char arg1) {
        switch(arg1) {
            case 35: 
            case 47: 
            case 59: 
            case 61: 
            case 92: {
                goto label_3;
            }
            case 9: 
            case 10: 
            case 12: 
            case 13: 
            case 32: 
            case 44: 
            case 58: 
            case 91: 
            case 93: 
            case 123: 
            case 125: {
                return 0;
            }
        }

        return 1;
    label_3:
        this.v();
        return 0;
    }

    private boolean a(String arg5) {
        while(true) {
            int v2 = 0;
            if(this.f + arg5.length() > this.g) {
                if(this.b(arg5.length())) {
                }
                else {
                    return 0;
                }
            }

            if(this.e[this.f] == 10) {
                ++this.h;
                this.i = this.f + 1;
            }
            else {
                while(true) {
                    if(v2 >= arg5.length()) {
                        return 1;
                    }
                    else if(this.e[this.f + v2] == arg5.charAt(v2)) {
                        ++v2;
                        continue;
                    }

                    goto label_32;
                }

                return 1;
            }

        label_32:
            ++this.f;
        }
    }

    public void b() {
        int v0 = this.a;
        if(v0 == 0) {
            v0 = this.r();
        }

        if(v0 == 4) {
            --this.n;
            int[] v0_1 = this.p;
            int v1 = this.n - 1;
            ++((int[])v0)[v1];
            this.a = 0;
            return;
        }

        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("Expected END_ARRAY but was ");
        v1_1.append(this.f());
        v1_1.append(this.x());
        throw new IllegalStateException(v1_1.toString());
    }

    private boolean b(int arg7) {
        char[] v0 = this.e;
        this.i -= this.f;
        if(this.g != this.f) {
            this.g -= this.f;
            System.arraycopy(v0, this.f, v0, 0, this.g);
        }
        else {
            this.g = 0;
        }

        this.f = 0;
        do {
            int v1 = this.c.read(v0, this.g, v0.length - this.g);
            if(v1 == -1) {
                return 0;
            }

            this.g += v1;
            if(this.h == 0 && this.i == 0 && this.g > 0 && v0[0] == 65279) {
                ++this.f;
                ++this.i;
                ++arg7;
            }
        }
        while(this.g < arg7);

        return 1;
    }

    private int b(boolean arg8) {
        int v4;
        int v1;
        char[] v0 = this.e;
        while(true) {
            v1 = this.f;
            while(true) {
            label_2:
                int v2 = this.g;
                while(true) {
                label_3:
                    if(v1 == v2) {
                        this.f = v1;
                        if(this.b(1)) {
                            v1 = this.f;
                            v2 = this.g;
                        }
                        else if(!arg8) {
                            return -1;
                        }
                        else {
                            StringBuilder v0_1 = new StringBuilder();
                            v0_1.append("End of input");
                            v0_1.append(this.x());
                            throw new EOFException(v0_1.toString());
                        }
                    }

                    v4 = v1 + 1;
                    v1 = v0[v1];
                    if(v1 == 10) {
                        ++this.h;
                        this.i = v4;
                    }
                    else if(v1 != 32 && v1 != 13) {
                        if(v1 == 9) {
                        }
                        else {
                            int v5 = 47;
                            if(v1 == v5) {
                                this.f = v4;
                                int v6 = 2;
                                if(v4 == v2) {
                                    --this.f;
                                    boolean v2_1 = this.b(v6);
                                    ++this.f;
                                    if(!v2_1) {
                                        return v1;
                                    }
                                }

                                this.v();
                                v2 = v0[this.f];
                                if(v2 != 42) {
                                    if(v2 != v5) {
                                        return v1;
                                    }

                                    ++this.f;
                                    goto label_63;
                                }

                                ++this.f;
                                if(!this.a("*/")) {
                                    break;
                                }

                                v1 = this.f + v6;
                                goto label_2;
                            }
                            else {
                                goto label_77;
                            }
                        }
                    }

                    goto label_84;
                }
            }

            throw this.b("Unterminated comment");
        label_77:
            if(v1 != 35) {
                break;
            }

            this.f = v4;
            this.v();
        label_63:
            this.w();
        }

        this.f = v4;
        return v1;
    label_84:
        v1 = v4;
        goto label_3;
    }

    private IOException b(String arg3) {
        StringBuilder v1 = new StringBuilder();
        v1.append(arg3);
        v1.append(this.x());
        throw new ia(v1.toString());
    }

    private String b(char arg9) {
        char[] v0 = this.e;
        StringBuilder v1 = new StringBuilder();
        while(true) {
        label_3:
            int v2 = this.f;
            int v3 = this.g;
            int v4 = v2;
            while(v2 < v3) {
                int v6 = v2 + 1;
                v2 = v0[v2];
                if(v2 == arg9) {
                    this.f = v6;
                    v1.append(v0, v4, v6 - v4 - 1);
                    return v1.toString();
                }

                if(v2 == 92) {
                    this.f = v6;
                    v1.append(v0, v4, v6 - v4 - 1);
                    v1.append(this.y());
                    goto label_3;
                }

                if(v2 == 10) {
                    ++this.h;
                    this.i = v6;
                }

                v2 = v6;
            }

            v1.append(v0, v4, v2 - v4);
            this.f = v2;
            if(!this.b(1)) {
                break;
            }
        }

        throw this.b("Unterminated string");
    }

    public void c() {
        int v0 = this.a;
        if(v0 == 0) {
            v0 = this.r();
        }

        if(v0 == 1) {
            this.a(3);
            this.a = 0;
            return;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Expected BEGIN_OBJECT but was ");
        v1.append(this.f());
        v1.append(this.x());
        throw new IllegalStateException(v1.toString());
    }

    private void c(char arg7) {
        char[] v0 = this.e;
        while(true) {
        label_1:
            int v1 = this.f;
            int v2 = this.g;
            while(v1 < v2) {
                int v4 = v1 + 1;
                v1 = v0[v1];
                if(v1 == arg7) {
                    this.f = v4;
                    return;
                }

                if(v1 == 92) {
                    this.f = v4;
                    this.y();
                    goto label_1;
                }

                if(v1 == 10) {
                    ++this.h;
                    this.i = v4;
                }

                v1 = v4;
            }

            this.f = v1;
            if(!this.b(1)) {
                break;
            }
        }

        throw this.b("Unterminated string");
    }

    public void close() {
        this.a = 0;
        this.m[0] = 8;
        this.n = 1;
        this.c.close();
    }

    public void d() {
        int v0 = this.a;
        if(v0 == 0) {
            v0 = this.r();
        }

        if(v0 == 2) {
            --this.n;
            this.o[this.n] = null;
            int[] v0_1 = this.p;
            int v1 = this.n - 1;
            ++((int[])v0)[v1];
            this.a = 0;
            return;
        }

        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("Expected END_OBJECT but was ");
        v1_1.append(this.f());
        v1_1.append(this.x());
        throw new IllegalStateException(v1_1.toString());
    }

    public boolean e() {
        int v0 = this.a;
        if(v0 == 0) {
            v0 = this.r();
        }

        boolean v0_1 = v0 == 2 || v0 == 4 ? false : true;
        return v0_1;
    }

    public hy f() {
        int v0 = this.a;
        if(v0 == 0) {
            v0 = this.r();
        }

        switch(v0) {
            case 1: {
                goto label_25;
            }
            case 2: {
                goto label_23;
            }
            case 3: {
                goto label_21;
            }
            case 4: {
                goto label_19;
            }
            case 5: 
            case 6: {
                goto label_17;
            }
            case 7: {
                goto label_15;
            }
            case 8: 
            case 9: 
            case 10: 
            case 11: {
                goto label_13;
            }
            case 12: 
            case 13: 
            case 14: {
                goto label_11;
            }
            case 15: 
            case 16: {
                goto label_9;
            }
            case 17: {
                goto label_7;
            }
        }

        throw new AssertionError();
    label_7:
        return hy.j;
    label_9:
        return hy.g;
    label_11:
        return hy.e;
    label_13:
        return hy.f;
    label_15:
        return hy.i;
    label_17:
        return hy.h;
    label_19:
        return hy.b;
    label_21:
        return hy.a;
    label_23:
        return hy.d;
    label_25:
        return hy.c;
    }

    public String g() {
        char v0_2;
        String v0_1;
        int v0 = this.a;
        if(v0 == 0) {
            v0 = this.r();
        }

        if(v0 == 14) {
            v0_1 = this.t();
        }
        else {
            if(v0 == 12) {
                v0_2 = '\'';
            }
            else if(v0 == 13) {
                v0_2 = '\"';
            }
            else {
                goto label_23;
            }

            v0_1 = this.b(v0_2);
        }

        this.a = 0;
        this.o[this.n - 1] = v0_1;
        return v0_1;
    label_23:
        StringBuilder v1 = new StringBuilder();
        v1.append("Expected a name but was ");
        v1.append(this.f());
        v1.append(this.x());
        throw new IllegalStateException(v1.toString());
    }

    public String h() {
        char v0_2;
        String v0_1;
        int v0 = this.a;
        if(v0 == 0) {
            v0 = this.r();
        }

        if(v0 == 10) {
            v0_1 = this.t();
        }
        else {
            if(v0 == 8) {
                v0_2 = '\'';
            }
            else if(v0 == 9) {
                v0_2 = '\"';
            }
            else {
                goto label_16;
            }

            v0_1 = this.b(v0_2);
            goto label_38;
        label_16:
            if(v0 == 11) {
                v0_1 = this.l;
                this.l = null;
                goto label_38;
            }

            if(v0 == 15) {
                v0_1 = Long.toString(this.j);
                goto label_38;
            }

            if(v0 != 16) {
                goto label_47;
            }

            v0_1 = new String(this.e, this.f, this.k);
            this.f += this.k;
        }

    label_38:
        this.a = 0;
        int[] v1 = this.p;
        int v2 = this.n - 1;
        ++v1[v2];
        return v0_1;
    label_47:
        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("Expected a string but was ");
        v1_1.append(this.f());
        v1_1.append(this.x());
        throw new IllegalStateException(v1_1.toString());
    }

    public boolean i() {
        int v1;
        int[] v0_1;
        int v0 = this.a;
        if(v0 == 0) {
            v0 = this.r();
        }

        if(v0 == 5) {
            this.a = 0;
            v0_1 = this.p;
            v1 = this.n - 1;
            ++((int[])v0)[v1];
            return 1;
        }

        if(v0 == 6) {
            this.a = 0;
            v0_1 = this.p;
            v1 = this.n - 1;
            ++((int[])v0)[v1];
            return 0;
        }

        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("Expected a boolean but was ");
        v1_1.append(this.f());
        v1_1.append(this.x());
        throw new IllegalStateException(v1_1.toString());
    }

    public void j() {
        int v0 = this.a;
        if(v0 == 0) {
            v0 = this.r();
        }

        if(v0 == 7) {
            this.a = 0;
            int[] v0_1 = this.p;
            int v1 = this.n - 1;
            ++((int[])v0)[v1];
            return;
        }

        StringBuilder v1_1 = new StringBuilder();
        v1_1.append("Expected null but was ");
        v1_1.append(this.f());
        v1_1.append(this.x());
        throw new IllegalStateException(v1_1.toString());
    }

    public double k() {
        String v0_2;
        int v1;
        int v0 = this.a;
        if(v0 == 0) {
            v0 = this.r();
        }

        if(v0 == 15) {
            this.a = 0;
            int[] v0_1 = this.p;
            v1 = this.n - 1;
            ++v0_1[v1];
            return ((double)this.j);
        }

        int v3 = 11;
        if(v0 == 16) {
            this.l = new String(this.e, this.f, this.k);
            this.f += this.k;
        }
        else {
            v1 = 8;
            if(v0 == v1 || v0 == 9) {
                char v0_3 = v0 == v1 ? '\'' : '\"';
                v0_2 = this.b(v0_3);
            }
            else if(v0 == 10) {
                v0_2 = this.t();
            }
            else if(v0 == v3) {
                goto label_60;
            }
            else {
                StringBuilder v1_1 = new StringBuilder();
                v1_1.append("Expected a double but was ");
                v1_1.append(this.f());
                v1_1.append(this.x());
                throw new IllegalStateException(v1_1.toString());
            }

            this.l = v0_2;
        }

    label_60:
        this.a = v3;
        double v0_4 = Double.parseDouble(this.l);
        if(!this.d && ((Double.isNaN(v0_4)) || (Double.isInfinite(v0_4)))) {
            StringBuilder v3_1 = new StringBuilder();
            v3_1.append("JSON forbids NaN and infinities: ");
            v3_1.append(v0_4);
            v3_1.append(this.x());
            throw new ia(v3_1.toString());
        }

        this.l = null;
        this.a = 0;
        int[] v2 = this.p;
        v3 = this.n - 1;
        ++v2[v3];
        return v0_4;
    }

    public long l() {
        String v0_2;
        StringBuilder v1_1;
        int v1;
        int[] v0_1;
        int v0 = this.a;
        if(v0 == 0) {
            v0 = this.r();
        }

        if(v0 == 15) {
            this.a = 0;
            v0_1 = this.p;
            v1 = this.n - 1;
            ++((int[])v0)[v1];
            return this.j;
        }

        if(v0 == 16) {
            this.l = new String(this.e, this.f, this.k);
            this.f += this.k;
            goto label_67;
        }

        v1 = 10;
        int v3 = 8;
        if(v0 != v3 && v0 != 9) {
            if(v0 == v1) {
            }
            else {
                v1_1 = new StringBuilder();
                v1_1.append("Expected a long but was ");
                v1_1.append(this.f());
                v1_1.append(this.x());
                throw new IllegalStateException(v1_1.toString());
            }
        }

        if(v0 == v1) {
            v0_2 = this.t();
        }
        else {
            char v0_3 = v0 == v3 ? '\'' : '\"';
            v0_2 = this.b(v0_3);
        }

        this.l = v0_2;
        try {
            long v0_4 = Long.parseLong(this.l);
            this.a = 0;
            int[] v3_1 = this.p;
            int v4 = this.n - 1;
            ++v3_1[v4];
            return v0_4;
        }
        catch(NumberFormatException ) {
        label_67:
            this.a = 11;
            double v0_5 = Double.parseDouble(this.l);
            long v3_2 = ((long)v0_5);
            if((((double)v3_2)) == v0_5) {
                this.l = null;
                this.a = 0;
                v0_1 = this.p;
                v1 = this.n - 1;
                ++v0_1[v1];
                return v3_2;
            }

            v1_1 = new StringBuilder();
            v1_1.append("Expected a long but was ");
            v1_1.append(this.l);
            v1_1.append(this.x());
            throw new NumberFormatException(v1_1.toString());
        }
    }

    public int m() {
        String v0_1;
        StringBuilder v1_1;
        int[] v1;
        int v0 = this.a;
        if(v0 == 0) {
            v0 = this.r();
        }

        if(v0 == 15) {
            v0 = ((int)this.j);
            if(this.j == (((long)v0))) {
                this.a = 0;
                v1 = this.p;
                int v2 = this.n - 1;
                ++v1[v2];
                return v0;
            }

            v1_1 = new StringBuilder();
            v1_1.append("Expected an int but was ");
            v1_1.append(this.j);
            v1_1.append(this.x());
            throw new NumberFormatException(v1_1.toString());
        }

        if(v0 == 16) {
            this.l = new String(this.e, this.f, this.k);
            this.f += this.k;
            goto label_83;
        }

        int v1_2 = 10;
        int v3 = 8;
        if(v0 != v3 && v0 != 9) {
            if(v0 == v1_2) {
            }
            else {
                v1_1 = new StringBuilder();
                v1_1.append("Expected an int but was ");
                v1_1.append(this.f());
                v1_1.append(this.x());
                throw new IllegalStateException(v1_1.toString());
            }
        }

        if(v0 == v1_2) {
            v0_1 = this.t();
        }
        else {
            char v0_2 = v0 == v3 ? '\'' : '\"';
            v0_1 = this.b(v0_2);
        }

        this.l = v0_1;
        try {
            v0 = Integer.parseInt(this.l);
            this.a = 0;
            v1 = this.p;
            v3 = this.n - 1;
            ++v1[v3];
            return v0;
        }
        catch(NumberFormatException ) {
        label_83:
            this.a = 11;
            double v0_3 = Double.parseDouble(this.l);
            v3 = ((int)v0_3);
            if((((double)v3)) == v0_3) {
                this.l = null;
                this.a = 0;
                int[] v0_4 = this.p;
                v1_2 = this.n - 1;
                ++((int[])v0_3)[v1_2];
                return v3;
            }

            v1_1 = new StringBuilder();
            v1_1.append("Expected an int but was ");
            v1_1.append(this.l);
            v1_1.append(this.x());
            throw new NumberFormatException(v1_1.toString());
        }
    }

    public void n() {
        char v2_1;
        int v1 = 0;
        do {
            int v2 = this.a;
            if(v2 == 0) {
                v2 = this.r();
            }

            int v3 = 3;
            if(v2 == v3) {
                this.a(1);
                goto label_9;
            }
            else if(v2 == 1) {
                this.a(v3);
            label_9:
                ++v1;
            }
            else {
                if(v2 != 4) {
                    if(v2 == 2) {
                    }
                    else {
                        goto label_24;
                    }
                }

                --this.n;
                --v1;
                goto label_52;
            label_24:
                if(v2 != 14) {
                    if(v2 == 10) {
                    }
                    else {
                        if(v2 == 8 || v2 == 12) {
                            v2_1 = '\'';
                        }
                        else {
                            if(v2 != 9) {
                                if(v2 == 13) {
                                }
                                else {
                                    if(v2 == 16) {
                                        this.f += this.k;
                                    }
                                    else {
                                    }

                                    goto label_52;
                                }
                            }

                            v2_1 = '\"';
                        }

                        this.c(v2_1);
                        goto label_52;
                    }
                }

                this.u();
            }

        label_52:
            this.a = 0;
        }
        while(v1 != 0);

        int[] v0 = this.p;
        v1 = this.n - 1;
        ++v0[v1];
        this.o[this.n - 1] = "null";
    }

    private int o() {
        int v3;
        String v2;
        String v0_1;
        int v0 = this.e[this.f];
        if(v0 == 116 || v0 == 84) {
            v0_1 = "true";
            v2 = "TRUE";
            v3 = 5;
        }
        else {
            if(v0 != 102) {
                if(v0 == 70) {
                }
                else {
                    if(v0 != 110) {
                        if(v0 == 78) {
                        }
                        else {
                            return 0;
                        }
                    }

                    v0_1 = "null";
                    v2 = "NULL";
                    v3 = 7;
                    goto label_31;
                }
            }

            v0_1 = "false";
            v2 = "FALSE";
            v3 = 6;
        }

    label_31:
        int v4 = v0_1.length();
        int v5;
        for(v5 = 1; v5 < v4; ++v5) {
            if(this.f + v5 >= this.g && !this.b(v5 + 1)) {
                return 0;
            }

            int v6 = this.e[this.f + v5];
            if(v6 != v0_1.charAt(v5) && v6 != v2.charAt(v5)) {
                return 0;
            }
        }

        if((this.f + v4 < this.g || (this.b(v4 + 1))) && (this.a(this.e[this.f + v4]))) {
            return 0;
        }

        this.f += v4;
        this.a = v3;
        return v3;
    }

    public String p() {
        StringBuilder v0 = new StringBuilder();
        v0.append('$');
        int v1 = this.n;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            switch(this.m[v2]) {
                case 1: 
                case 2: {
                    v0.append('[');
                    v0.append(this.p[v2]);
                    v0.append(']');
                    break;
                }
                case 3: 
                case 4: 
                case 5: {
                    v0.append('.');
                    if(this.o[v2] == null) {
                        goto label_27;
                    }

                    v0.append(this.o[v2]);
                    break;
                }
                default: {
                    break;
                }
            }

        label_27:
        }

        return v0.toString();
    }

    public final boolean q() {
        return this.d;
    }

    int r() {
        int v12;
        int v0 = this.m[this.n - 1];
        int v1 = 8;
        int v3 = 3;
        int v4 = 93;
        int v5 = 39;
        int v6 = 34;
        int v7 = 7;
        int v8 = 59;
        int v9 = 44;
        int v10 = 4;
        int v11 = 2;
        if(v0 == 1) {
            this.m[this.n - 1] = v11;
            goto label_88;
        }
        else if(v0 == v11) {
            v12 = this.b(true);
            if(v12 == v9) {
                goto label_88;
            }
            else if(v12 == v8) {
                this.v();
                goto label_88;
            }
            else if(v12 == v4) {
                this.a = v10;
                return v10;
            }
            else {
                throw this.b("Unterminated array");
            }
        }
        else {
            v12 = 5;
            if(v0 != v3) {
                if(v0 == v12) {
                }
                else {
                    if(v0 == v10) {
                        this.m[this.n - 1] = v12;
                        v12 = this.b(true);
                        if(v12 == 58) {
                            goto label_88;
                        }
                        else if(v12 == 61) {
                            this.v();
                            if(this.f >= this.g && !this.b(1)) {
                                goto label_88;
                            }

                            if(this.e[this.f] != 62) {
                                goto label_88;
                            }

                            v12 = this.f + 1;
                        }
                        else {
                            throw this.b("Expected \':\'");
                        }
                    }
                    else {
                        if(v0 == 6) {
                            if(this.d) {
                                this.z();
                            }

                            this.m[this.n - 1] = v7;
                            goto label_88;
                        }

                        if(v0 != v7) {
                            goto label_87;
                        }

                        if(this.b(false) == -1) {
                            v0 = 17;
                            goto label_80;
                        }

                        this.v();
                        v12 = this.f - 1;
                    }

                    this.f = v12;
                    goto label_88;
                label_87:
                    if(v0 != v1) {
                    label_88:
                        v12 = this.b(true);
                        if(v12 != v6) {
                            if(v12 != v5) {
                                if(v12 != v9 && v12 != v8) {
                                    if(v12 == 91) {
                                        this.a = v3;
                                        return v3;
                                    }
                                    else if(v12 != v4) {
                                        if(v12 != 123) {
                                            --this.f;
                                            v0 = this.o();
                                            if(v0 != 0) {
                                                return v0;
                                            }
                                            else {
                                                v0 = this.s();
                                                if(v0 != 0) {
                                                    return v0;
                                                }
                                                else if(this.a(this.e[this.f])) {
                                                    this.v();
                                                    v0 = 10;
                                                    goto label_80;
                                                }
                                                else {
                                                    throw this.b("Expected value");
                                                }
                                            }
                                        }
                                        else {
                                            this.a = 1;
                                            return 1;
                                        }
                                    }
                                    else if(v0 == 1) {
                                        this.a = v10;
                                        return v10;
                                    }
                                }

                                if(v0 != 1) {
                                    if(v0 == v11) {
                                    }
                                    else {
                                        throw this.b("Unexpected value");
                                    }
                                }

                                this.v();
                                --this.f;
                                this.a = v7;
                                return v7;
                            }

                            this.v();
                            this.a = v1;
                            return v1;
                        }

                        v0 = 9;
                        goto label_80;
                    }

                    throw new IllegalStateException("JsonReader is closed");
                }
            }

            this.m[this.n - 1] = v10;
            v1 = 125;
            if(v0 == v12) {
                v3 = this.b(true);
                if(v3 != v9) {
                    if(v3 == v8) {
                        this.v();
                    }
                    else if(v3 == v1) {
                        this.a = v11;
                        return v11;
                    }
                    else {
                        throw this.b("Unterminated object");
                    }
                }
            }

            v3 = this.b(true);
            if(v3 != v6) {
                if(v3 != v5) {
                    if(v3 != v1) {
                        this.v();
                        --this.f;
                        if(this.a(((char)v3))) {
                            v0 = 14;
                            goto label_80;
                        }

                        throw this.b("Expected name");
                    }

                    if(v0 != v12) {
                        this.a = v11;
                        return v11;
                    }

                    throw this.b("Expected name");
                }

                this.v();
                v0 = 12;
                goto label_80;
            }

            v0 = 13;
        }

    label_80:
        this.a = v0;
        return v0;
    }

    private int s() {
        int v1_1;
        hx v0 = this;
        char[] v1 = v0.e;
        int v2 = v0.f;
        int v7 = 0;
        int v8 = v0.g;
        int v3 = 0;
        int v9 = 0;
        int v10 = 1;
        long v11 = 0;
        int v13 = 0;
        while(true) {
            int v15 = 2;
            if(v2 + v3 != v8) {
            label_24:
                char v14 = v1[v2 + v3];
                int v4 = 3;
                int v5 = 5;
                if(v14 != 43) {
                    if(v14 != 69 && v14 != 101) {
                        switch(v14) {
                            case 45: {
                                goto label_123;
                            }
                            case 46: {
                                goto label_117;
                            }
                        }

                        if(v14 >= 48) {
                            if(v14 > 57) {
                            }
                            else {
                                if(v9 != 1) {
                                    if(v9 == 0) {
                                    }
                                    else {
                                        if(v9 != v15) {
                                            if(v9 == v4) {
                                                v7 = 0;
                                                v9 = 4;
                                                goto label_147;
                                            }

                                            if(v9 != v5) {
                                                if(v9 == 6) {
                                                }
                                                else {
                                                    goto label_74;
                                                }
                                            }

                                            goto label_76;
                                        }
                                        else if(v11 == 0) {
                                            return 0;
                                        }
                                        else {
                                            long v4_1 = 10 * v11 - (((long)(v14 - 48)));
                                            long v14_1 = -922337203685477580L;
                                            if(v11 <= v14_1) {
                                                if(v11 == v14_1 && v4_1 < v11) {
                                                    goto label_60;
                                                }

                                                v7 = 0;
                                            }
                                            else {
                                            label_60:
                                                v7 = 1;
                                            }

                                            v11 = v4_1;
                                            v10 &= v7;
                                        }

                                    label_74:
                                        v7 = 0;
                                        goto label_147;
                                    label_76:
                                        v7 = 0;
                                        v9 = 7;
                                        goto label_147;
                                    }
                                }

                                v11 = ((long)(-(v14 - 48)));
                                v7 = 0;
                                v9 = 2;
                                goto label_147;
                            }
                        }

                        if(v0.a(v14)) {
                            return 0;
                        }

                        goto label_89;
                    }

                    goto label_133;
                }
                else {
                    goto label_142;
                }
            }
            else if(v3 == v1.length) {
                return v7;
            }
            else if(!v0.b(v3 + 1)) {
            }
            else {
                v2 = v0.f;
                v8 = v0.g;
                goto label_24;
            }

        label_89:
            if(v9 != v15 || v10 == 0) {
            label_104:
                if(v9 != v15 && v9 != 4) {
                    if(v9 == 7) {
                    }
                    else {
                        return 0;
                    }
                }

                v0.k = v3;
                v1_1 = 16;
            }
            else {
                if(v11 == -9223372036854775808L && v13 == 0) {
                    goto label_104;
                }

                if(v13 != 0) {
                }
                else {
                    v11 = -v11;
                }

                v0.j = v11;
                v0.f += v3;
                v1_1 = 15;
            }

            v0.a = v1_1;
            return v1_1;
        label_117:
            v7 = 0;
            if(v9 == v15) {
                v9 = 3;
                goto label_147;
            }

            return 0;
        label_123:
            v7 = 0;
            if(v9 == 0) {
                v9 = 1;
                v13 = 1;
                goto label_147;
            }

            if(v9 == v5) {
                goto label_146;
            }

            return 0;
        label_133:
            v7 = 0;
            if(v9 != v15) {
                if(v9 == 4) {
                }
                else {
                    return 0;
                }
            }

            v9 = 5;
            goto label_147;
        label_142:
            v7 = 0;
            if(v9 != v5) {
                return 0;
            }

        label_146:
            v9 = 6;
        label_147:
            ++v3;
        }

        return 0;
    }

    private String t() {
        String v1_1;
        int v0 = 0;
        StringBuilder v2 = null;
        do {
            int v1 = 0;
            while(true) {
                if(this.f + v1 < this.g) {
                    switch(this.e[this.f + v1]) {
                        case 35: 
                        case 47: 
                        case 59: 
                        case 61: 
                        case 92: {
                            goto label_15;
                        }
                        case 9: 
                        case 10: 
                        case 12: 
                        case 13: 
                        case 32: 
                        case 44: 
                        case 58: 
                        case 91: 
                        case 93: 
                        case 123: 
                        case 125: {
                            goto label_24;
                        }
                    }

                    ++v1;
                    continue;
                label_15:
                    this.v();
                    break;
                }
                else if(v1 >= this.e.length) {
                    goto label_26;
                }
                else if(this.b(v1 + 1)) {
                    continue;
                }
                else {
                    break;
                }
            }

        label_24:
            v0 = v1;
            break;
        label_26:
            if(v2 == null) {
                v2 = new StringBuilder();
            }

            v2.append(this.e, this.f, v1);
            this.f += v1;
        }
        while(this.b(1));

        if(v2 == null) {
            v1_1 = new String(this.e, this.f, v0);
        }
        else {
            v2.append(this.e, this.f, v0);
            v1_1 = v2.toString();
        }

        this.f += v0;
        return v1_1;
    }

    public String toString() {
        return this.getClass().getSimpleName() + this.x();
    }

    private void u() {
        do {
            int v0;
            for(v0 = 0; true; ++v0) {
                if(this.f + v0 >= this.g) {
                    goto label_17;
                }

                switch(this.e[this.f + v0]) {
                    case 35: 
                    case 47: 
                    case 59: 
                    case 61: 
                    case 92: {
                        goto label_12;
                    }
                    case 9: 
                    case 10: 
                    case 12: 
                    case 13: 
                    case 32: 
                    case 44: 
                    case 58: 
                    case 91: 
                    case 93: 
                    case 123: 
                    case 125: {
                        goto label_13;
                    }
                }
            }

        label_12:
            this.v();
        label_13:
            this.f += v0;
            return;
        label_17:
            this.f += v0;
        }
        while(this.b(1));
    }

    private void v() {
        if(this.d) {
            return;
        }

        throw this.b("Use JsonReader.setLenient(true) to accept malformed JSON");
    }

    private void w() {
        do {
            if(this.f >= this.g && !this.b(1)) {
                return;
            }

            char[] v0 = this.e;
            int v1 = this.f;
            this.f = v1 + 1;
            int v0_1 = v0[v1];
            if(v0_1 == 10) {
                ++this.h;
                this.i = this.f;
            }
            else if(v0_1 != 13) {
                continue;
            }

            return;
        }
        while(true);
    }

    private String x() {
        int v0 = this.h + 1;
        int v1 = this.f - this.i + 1;
        return " at line " + v0 + " column " + v1 + " path " + this.p();
    }

    private char y() {
        if(this.f == this.g) {
            if(this.b(1)) {
            }
            else {
                throw this.b("Unterminated escape sequence");
            }
        }

        char[] v0 = this.e;
        int v1 = this.f;
        this.f = v1 + 1;
        char v0_1 = v0[v1];
        char v1_1 = '\n';
        if(v0_1 == v1_1) {
            ++this.h;
            this.i = this.f;
        }
        else if(v0_1 != 34 && v0_1 != 39 && v0_1 != 47 && v0_1 != 92) {
            if(v0_1 != 98) {
                int v2 = 102;
                if(v0_1 == v2) {
                    return '\f';
                }
                else if(v0_1 == 110) {
                    return v1_1;
                }
                else if(v0_1 != 114) {
                    switch(v0_1) {
                        case 116: {
                            return '\t';
                        }
                        case 117: {
                            goto label_37;
                        }
                    }

                    throw this.b("Invalid escape sequence");
                label_37:
                    int v3 = 4;
                    if(this.f + v3 > this.g) {
                        if(this.b(v3)) {
                        }
                        else {
                            throw this.b("Unterminated escape sequence");
                        }
                    }

                    v0_1 = '\u0000';
                    int v4 = this.f;
                    int v5 = v4 + 4;
                    while(true) {
                        if(v4 >= v5) {
                            goto label_91;
                        }

                        int v6 = this.e[v4];
                        v0_1 = ((char)(v0_1 << 4));
                        if(v6 < 48 || v6 > 57) {
                            if(v6 < 97 || v6 > v2) {
                                if(v6 >= 65 && v6 <= 70) {
                                    v6 += -65;
                                    goto label_68;
                                }

                                break;
                            }
                            else {
                                v6 += -97;
                            }

                        label_68:
                            v6 += v1_1;
                        }
                        else {
                            v6 += -48;
                        }

                        v0_1 = ((char)(v0_1 + v6));
                        ++v4;
                    }

                    StringBuilder v1_2 = new StringBuilder();
                    v1_2.append("\\u");
                    v1_2.append(new String(this.e, this.f, v3));
                    throw new NumberFormatException(v1_2.toString());
                label_91:
                    this.f += v3;
                    return v0_1;
                }
                else {
                    return '\r';
                }
            }
            else {
                return '\b';
            }
        }

        return v0_1;
    }

    private void z() {
        this.b(true);
        --this.f;
        if(this.f + hx.b.length > this.g && !this.b(hx.b.length)) {
            return;
        }

        int v0;
        for(v0 = 0; v0 < hx.b.length; ++v0) {
            if(this.e[this.f + v0] != hx.b[v0]) {
                return;
            }
        }

        this.f += hx.b.length;
    }
}


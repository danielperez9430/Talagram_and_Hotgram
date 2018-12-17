package org.telegram.customization.util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class h extends Calendar {
    public class a {
        private int a;
        private int b;
        private int c;

        public a(int arg1, int arg2, int arg3) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
        }

        public void a(int arg1) {
            this.a = arg1;
        }

        public int a() {
            return this.a;
        }

        public int b() {
            return this.b;
        }

        public void b(int arg1) {
            this.c = arg1;
        }

        public int c() {
            return this.c;
        }

        public String toString() {
            return this.a() + "/" + this.b() + "/" + this.c();
        }
    }

    public static int[] a;
    public static int[] b;
    static final int[] c;
    static final int[] d;
    static final int[] e;
    private static TimeZone f;
    private static boolean g;
    private GregorianCalendar h;

    static {
        h.a = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        h.b = new int[]{31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29};
        h.f = TimeZone.getDefault();
        h.g = false;
        h.c = new int[]{0, 1, 0, 1, 0, 1, 1, 7, 1, 0, 0, 0, 0, 0, 0, -46800000, 0};
        h.d = new int[]{1, 292269054, 11, 52, 4, 28, 365, 6, 4, 1, 11, 23, 59, 59, 999, 50400000, 1200000};
        h.e = new int[]{1, 292278994, 11, 53, 6, 31, 366, 6, 6, 1, 11, 23, 59, 59, 999, 50400000, 7200000};
    }

    public static int a(int arg3, int arg4) {
        switch(h.c(h.b(new a(arg4, 0, 1)))) {
            case 2: {
                ++arg3;
                break;
            }
            case 3: {
                arg3 += 2;
                break;
            }
            case 4: {
                arg3 += 3;
                break;
            }
            case 5: {
                arg3 += 4;
                break;
            }
            case 6: {
                arg3 += 5;
                break;
            }
            case 7: {
                --arg3;
                break;
            }
            default: {
                break;
            }
        }

        return (((int)Math.floor(((double)(arg3 / 7))))) + 1;
    }

    public static a a(a arg7) {
        int v1 = 11;
        if(arg7.b() <= v1 && arg7.b() >= -11) {
            arg7.a(arg7.a() - 1600);
            arg7.b(arg7.c() - 1);
            int v3 = 0;
            int v4 = arg7.a() * 365 + (((int)Math.floor(((double)((arg7.a() + 3) / 4))))) - (((int)Math.floor(((double)((arg7.a() + 99) / 100))))) + (((int)Math.floor(((double)((arg7.a() + 399) / 400)))));
            int v0;
            for(v0 = 0; v0 < arg7.b(); ++v0) {
                v4 += h.a[v0];
            }

            if(arg7.b() > 1 && (arg7.a() % 4 == 0 && arg7.a() % 100 != 0 || arg7.a() % 400 == 0)) {
                ++v4;
            }

            v4 = v4 + arg7.c() - 79;
            int v7 = ((int)Math.floor(((double)(v4 / 12053))));
            v4 %= 12053;
            v7 = v7 * 33 + 979 + v4 / 1461 * 4;
            v4 %= 1461;
            if(v4 >= 366) {
                --v4;
                v7 += ((int)Math.floor(((double)(v4 / 365))));
                v4 %= 365;
            }

            while(v3 < v1) {
                if(v4 < h.b[v3]) {
                    break;
                }

                v4 -= h.b[v3];
                ++v3;
            }

            return new a(v7, v3, v4 + 1);
        }

        throw new IllegalArgumentException();
    }

    public static boolean a(int arg2) {
        arg2 %= 33;
        if(arg2 != 1 && arg2 != 5 && arg2 != 9 && arg2 != 13 && arg2 != 17 && arg2 != 22 && arg2 != 26) {
            if(arg2 == 30) {
            }
            else {
                return 0;
            }
        }

        return 1;
    }

    public void add(int arg18, int arg19) {
        h v0 = this;
        int v1 = arg18;
        int v2 = arg19;
        int v3 = 30;
        int v4 = 11;
        int v5 = 12;
        int v6 = 5;
        int v7 = 2;
        if(v1 == v7) {
            v1 = v0.get(v7) + v2;
            v0.add(1, v1 / 12);
            v1 %= v5;
            super.set(v7, v1);
            if(v0.get(v6) > h.b[v1]) {
                super.set(v6, h.b[v1]);
                if(v0.get(v7) == v4 && (h.a(v0.get(1)))) {
                    super.set(v6, v3);
                }
            }
        }
        else if(v1 == 1) {
            super.set(1, v0.get(1) + v2);
            if(v0.get(v6) == v3 && v0.get(v7) == v4 && !h.a(v0.get(1))) {
                super.set(v6, 29);
            }
        }
        else {
            a v3_1 = h.b(new a(v0.get(1), v0.get(v7), v0.get(v6)));
            GregorianCalendar v3_2 = new GregorianCalendar(v3_1.a(), v3_1.b(), v3_1.c(), v0.get(v4), v0.get(v5), v0.get(13));
            ((Calendar)v3_2).add(v1, v2);
            a v1_1 = h.a(new a(((Calendar)v3_2).get(1), ((Calendar)v3_2).get(v7), ((Calendar)v3_2).get(v6)));
            super.set(1, v1_1.a());
            super.set(v7, v1_1.b());
            super.set(v6, v1_1.c());
            super.set(v4, ((Calendar)v3_2).get(v4));
            super.set(v5, ((Calendar)v3_2).get(v5));
            super.set(13, ((Calendar)v3_2).get(13));
        }

        this.complete();
    }

    public static a b(a arg7) {
        int v7;
        if(arg7.b() <= 11 && arg7.b() >= -11) {
            arg7.a(arg7.a() - 979);
            arg7.b(arg7.c() - 1);
            int v2 = 365;
            int v4 = arg7.a() * 365 + arg7.a() / 33 * 8 + (((int)Math.floor(((double)((arg7.a() % 33 + 3) / 4)))));
            int v0;
            for(v0 = 0; v0 < arg7.b(); ++v0) {
                v4 += h.b[v0];
            }

            v4 = v4 + arg7.c() + 79;
            v0 = (((int)Math.floor(((double)(v4 / 146097))))) * 400 + 1600;
            v4 %= 146097;
            if(v4 >= 36525) {
                --v4;
                v0 += (((int)Math.floor(((double)(v4 / 36524))))) * 100;
                v4 %= 36524;
                if(v4 >= v2) {
                    ++v4;
                    goto label_65;
                }
                else {
                    v7 = 0;
                }
            }
            else {
            label_65:
                v7 = 1;
            }

            v0 += (((int)Math.floor(((double)(v4 / 1461))))) * 4;
            v4 %= 1461;
            if(v4 >= 366) {
                --v4;
                v0 += ((int)Math.floor(((double)(v4 / 365))));
                v4 %= v2;
                v7 = 0;
            }

            for(v2 = 0; true; ++v2) {
                int v5 = h.a[v2];
                int v6 = v2 != 1 || v7 != 1 ? 0 : v2;
                if(v4 < v5 + v6) {
                    break;
                }

                v5 = h.a[v2];
                v6 = v2 != 1 || v7 != 1 ? 0 : v2;
                v4 -= v5 + v6;
            }

            return new a(v0, v2, v4 + 1);
        }

        throw new IllegalArgumentException();
    }

    public static int c(a arg3) {
        return new GregorianCalendar(arg3.a(), arg3.b(), arg3.c()).get(7);
    }

    protected void computeFields() {
        int v6;
        boolean v0 = this.isTimeSet;
        if(!this.areFieldsSet) {
            this.setMinimalDaysInFirstWeek(1);
            int v2 = 7;
            this.setFirstDayOfWeek(v2);
            int v3 = 0;
            int v4 = 0;
            while(true) {
                v6 = 2;
                if(v3 >= this.fields[v6]) {
                    break;
                }

                v4 += h.b[v3];
                ++v3;
            }

            int v5 = 5;
            v3 = 6;
            super.set(v3, v4 + this.fields[v5]);
            super.set(v2, h.c(h.b(new a(this.fields[1], this.fields[v6], this.fields[v5]))));
            int v7 = 8;
            if(this.fields[v5] > 0 && this.fields[v5] < v7) {
                super.set(v7, 1);
            }

            if(v2 < this.fields[v5] && this.fields[v5] < 15) {
                super.set(v7, v6);
            }

            v6 = 3;
            if(14 < this.fields[v5] && this.fields[v5] < 22) {
                super.set(v7, v6);
            }

            int v8 = 4;
            if(21 < this.fields[v5] && this.fields[v5] < 29) {
                super.set(v7, v8);
            }

            if(28 < this.fields[v5] && this.fields[v5] < 32) {
                super.set(v7, v5);
            }

            super.set(v6, h.a(this.fields[v3], this.fields[1]));
            super.set(v8, h.a(this.fields[v3], this.fields[1]) - h.a(this.fields[v3] - this.fields[v5], this.fields[1]) + 1);
            this.isTimeSet = v0;
        }
    }

    protected void computeTime() {
        long v1_1;
        h v0 = this;
        int v2 = 5;
        int v3 = 2;
        int v5 = 23;
        int v6 = 16;
        int v7 = 15;
        int v9 = 13;
        int v10 = 9;
        int v11 = 10;
        int v12 = 12;
        int v13 = 11;
        if((v0.isTimeSet) || (h.g)) {
            if(v0.isTimeSet) {
                return;
            }

            if(!h.g) {
                return;
            }

            if(v0.internalGet(v13) < v12 || v0.internalGet(v13) > v5) {
                super.set(v11, v0.internalGet(v13));
                super.set(v10, 0);
            }
            else {
                super.set(v10, 1);
                super.set(v11, v0.internalGet(v13) - v12);
            }

            v0.h = new GregorianCalendar();
            super.set(v7, h.f.getRawOffset());
            super.set(v6, h.f.getDSTSavings());
            a v1_2 = h.b(new a(v0.internalGet(1), v0.internalGet(v3), v0.internalGet(v2)));
            v0.h.set(v1_2.a(), v1_2.b(), v1_2.c(), v0.internalGet(v13), v0.internalGet(v12), v0.internalGet(v9));
            v1_1 = v0.h.getTimeInMillis();
        label_121:
            v0.time = v1_1;
        }
        else {
            Calendar v1 = GregorianCalendar.getInstance(h.f);
            if(!v0.isSet(v13)) {
                super.set(v13, v1.get(v13));
            }

            if(!v0.isSet(v11)) {
                super.set(v11, v1.get(v11));
            }

            if(!v0.isSet(v12)) {
                super.set(v12, v1.get(v12));
            }

            if(!v0.isSet(v9)) {
                super.set(v9, v1.get(v9));
            }

            int v14 = 14;
            if(!v0.isSet(v14)) {
                super.set(v14, v1.get(v14));
            }

            if(!v0.isSet(v7)) {
                super.set(v7, v1.get(v7));
            }

            if(!v0.isSet(v6)) {
                super.set(v6, v1.get(v6));
            }

            if(!v0.isSet(v10)) {
                super.set(v10, v1.get(v10));
            }

            if(v0.internalGet(v13) < v12 || v0.internalGet(v13) > v5) {
                super.set(v11, v0.internalGet(v13));
                super.set(v10, 0);
            }
            else {
                super.set(v10, 1);
                super.set(v11, v0.internalGet(v13) - v12);
            }

            a v2_1 = h.b(new a(v0.internalGet(1), v0.internalGet(v3), v0.internalGet(v2)));
            v1.set(v2_1.a(), v2_1.b(), v2_1.c(), v0.internalGet(v13), v0.internalGet(v12), v0.internalGet(v9));
            v1_1 = v1.getTimeInMillis();
            goto label_121;
        }
    }

    public int getGreatestMinimum(int arg2) {
        return h.c[arg2];
    }

    public int getLeastMaximum(int arg2) {
        return h.d[arg2];
    }

    public int getMaximum(int arg2) {
        return h.e[arg2];
    }

    public int getMinimum(int arg2) {
        return h.c[arg2];
    }

    public void roll(int arg11, int arg12) {
        if(arg12 == 0) {
            return;
        }

        if(arg11 >= 0 && arg11 < 15) {
            this.complete();
            int v0 = 6;
            int v1 = 30;
            int v2 = 0;
            int v3 = 9;
            int v4 = 5;
            int v5 = 12;
            int v6 = 10;
            int v8 = 2;
            int v9 = 11;
            switch(arg11) {
                case 1: {
                    goto label_186;
                }
                case 2: {
                    goto label_169;
                }
                case 3: {
                    return;
                }
                case 5: {
                    goto label_142;
                }
                case 6: {
                    goto label_114;
                }
                case 7: {
                    goto label_101;
                }
                case 9: {
                    goto label_86;
                }
                case 10: {
                    goto label_68;
                }
                case 11: {
                    goto label_42;
                }
                case 12: {
                    goto label_35;
                }
                case 13: {
                    goto label_27;
                }
                case 14: {
                    goto label_20;
                }
            }

            throw new IllegalArgumentException();
        label_35:
            arg11 = (this.internalGet(v5) + arg12) % 60;
            if(arg11 < 0) {
                arg11 += 60;
            }

            super.set(v5, arg11);
            return;
        label_68:
            super.set(v6, (this.internalGet(v6) + arg12) % v5);
            if(this.internalGet(v6) < 0) {
                this.fields[v6] += v5;
            }

            if(this.internalGet(v3) == 0) {
                arg11 = this.internalGet(v6);
                goto label_81;
            }

            arg11 = this.internalGet(v6);
            goto label_84;
        label_101:
            arg11 = 7;
            arg12 %= arg11;
            if(arg12 < 0) {
                arg12 += 7;
            }

            while(true) {
                if(v2 == arg12) {
                    return;
                }

                if(this.internalGet(arg11) == v0) {
                    this.add(v4, -6);
                }
                else {
                    this.add(v4, 1);
                }

                ++v2;
            }

        label_42:
            this.fields[v9] = (this.internalGet(v9) + arg12) % 24;
            if(this.internalGet(v9) < 0) {
                this.fields[v9] += 24;
            }

            if(this.internalGet(v9) < v5) {
                this.fields[v3] = 0;
                this.fields[v6] = this.internalGet(v9);
            }
            else {
                this.fields[v3] = 1;
                this.fields[v6] = this.internalGet(v9) - v5;
            }

        label_169:
            arg11 = (this.internalGet(v8) + arg12) % v5;
            if(arg11 < 0) {
                arg11 += 12;
            }

            super.set(v8, arg11);
            arg11 = h.b[arg11];
            if(this.internalGet(v8) == v9 && (h.a(this.internalGet(1)))) {
                arg11 = 30;
            }

            if(this.internalGet(v4) <= arg11) {
                return;
            }

            goto label_197;
        label_142:
            arg11 = this.get(v8) < 0 || this.get(v8) > v4 ? 0 : 31;
            if(v0 <= this.get(v8) && this.get(v8) <= v6) {
                arg11 = 30;
            }

            if(this.get(v8) != v9) {
                v1 = arg11;
            }
            else if(h.a(this.get(1))) {
            }
            else {
                v1 = 29;
            }

            arg11 = (this.get(v4) + arg12) % v1;
            if(arg11 >= 0) {
                goto label_197;
            }

            arg11 += v1;
            goto label_197;
        label_114:
            arg11 = h.a(this.internalGet(1)) ? 366 : 365;
            v0 = (this.internalGet(v0) + arg12) % arg11;
            if(v0 > 0) {
            }
            else {
                v0 += arg11;
            }

            for(arg11 = 0; v0 > v2; ++arg11) {
                v2 += h.b[arg11];
            }

            super.set(v8, arg11 - 1);
            arg11 = h.b[this.internalGet(v8)] - (v2 - v0);
            goto label_197;
        label_20:
            arg11 = 14;
            v0 = (this.internalGet(arg11) + arg12) % 1000;
            if(v0 >= 0) {
                goto label_33;
            }

            v0 += 1000;
            goto label_33;
        label_86:
            if(arg12 % v8 == 0) {
                return;
            }

            this.fields[v3] = this.internalGet(v3) == 0 ? 1 : 0;
            if(this.get(v3) == 0) {
                arg11 = this.get(v6);
            }
            else {
                arg11 = this.get(v6);
            label_84:
                arg11 += v5;
            }

        label_81:
            super.set(v9, arg11);
            return;
        label_186:
            super.set(1, this.internalGet(1) + arg12);
            if(this.internalGet(v8) != v9) {
                return;
            }

            if(this.internalGet(v4) != v1) {
                return;
            }

            if(h.a(this.internalGet(1))) {
                return;
            }

            arg11 = 29;
        label_197:
            super.set(v4, arg11);
            return;
        label_27:
            arg11 = 13;
            v0 = (this.internalGet(arg11) + arg12) % 60;
            if(v0 < 0) {
                v0 += 60;
            }

        label_33:
            super.set(arg11, v0);
            return;
        }

        throw new IllegalArgumentException();
    }

    public void roll(int arg1, boolean arg2) {
        int v2 = arg2 ? 1 : -1;
        this.roll(arg1, v2);
    }

    public void set(int arg18, int arg19) {
        int v3;
        h v0 = this;
        int v1 = arg18;
        int v2 = arg19;
        int v4 = 11;
        int v5 = 5;
        int v6 = 2;
        switch(v1) {
            case 2: {
                if(v2 > v4) {
                    super.set(v1, v4);
                    goto label_97;
                }

                if(v2 >= 0) {
                    goto label_9;
                }

                goto label_96;
            }
            case 3: {
                if(!v0.isSet(1)) {
                    goto label_9;
                }

                if(!v0.isSet(v6)) {
                    goto label_9;
                }

                if(!v0.isSet(v5)) {
                    goto label_9;
                }

                v3 = 3;
            label_114:
                v0.get(v3);
                goto label_97;
            }
            case 4: {
                if(!v0.isSet(1)) {
                    goto label_9;
                }

                if(!v0.isSet(v6)) {
                    goto label_9;
                }

                if(!v0.isSet(v5)) {
                    goto label_9;
                }

                v3 = 4;
                goto label_114;
            }
            case 5: {
                goto label_96;
            }
            case 6: {
                if(!v0.isSet(1)) {
                    goto label_9;
                }

                if(!v0.isSet(v6)) {
                    goto label_9;
                }

                if(!v0.isSet(v5)) {
                    goto label_9;
                }

                super.set(1, v0.internalGet(1));
                super.set(v6, 0);
                super.set(v5, 0);
                goto label_97;
            }
            case 7: {
                if(!v0.isSet(1)) {
                    goto label_9;
                }

                if(!v0.isSet(v6)) {
                    goto label_9;
                }

                if(!v0.isSet(v5)) {
                    goto label_9;
                }

                v0.get(7);
                goto label_97;
            }
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 14: 
            case 15: 
            case 16: {
                if(!v0.isSet(1)) {
                    goto label_9;
                }

                if(!v0.isSet(v6)) {
                    goto label_9;
                }

                if(!v0.isSet(v5)) {
                    goto label_9;
                }

                if(!v0.isSet(10)) {
                    goto label_9;
                }

                if(!v0.isSet(v4)) {
                    goto label_9;
                }

                v3 = 12;
                if(!v0.isSet(v3)) {
                    goto label_9;
                }

                int v8 = 13;
                if(!v0.isSet(v8)) {
                    goto label_9;
                }

                if(!v0.isSet(14)) {
                    goto label_9;
                }

                v0.h = new GregorianCalendar();
                a v9 = h.b(new a(v0.internalGet(1), v0.internalGet(v6), v0.internalGet(v5)));
                v0.h.set(v9.a(), v9.b(), v9.c(), v0.internalGet(v4), v0.internalGet(v3), v0.internalGet(v8));
                v0.h.set(v1, v2);
                a v1_1 = h.a(new a(v0.h.get(1), v0.h.get(v6), v0.h.get(v5)));
                super.set(1, v1_1.a());
                super.set(v6, v1_1.b());
                super.set(v5, v1_1.c());
                super.set(v4, v0.h.get(v4));
                super.set(v3, v0.h.get(v3));
                super.set(v8, v0.h.get(v8));
                return;
            }
        }

    label_9:
        super.set(arg18, arg19);
        return;
    label_96:
        super.set(v1, 0);
    label_97:
        this.add(arg18, arg19);
    }
}


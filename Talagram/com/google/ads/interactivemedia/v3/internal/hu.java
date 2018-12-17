package com.google.ads.interactivemedia.v3.internal;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map$Entry;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public final class hu {
    final class com.google.ads.interactivemedia.v3.internal.hu$10 extends gp {
        com.google.ads.interactivemedia.v3.internal.hu$10() {
            super();
        }

        public BigInteger a(hx arg3) {
            if(arg3.f() == hy.i) {
                arg3.j();
                return null;
            }

            try {
                return new BigInteger(arg3.h());
            }
            catch(NumberFormatException v3) {
                throw new gn(((Throwable)v3));
            }
        }

        public void a(hz arg1, BigInteger arg2) {
            arg1.a(((Number)arg2));
        }

        public Object read(hx arg1) {
            return this.a(arg1);
        }

        public void write(hz arg1, Object arg2) {
            this.a(arg1, ((BigInteger)arg2));
        }
    }

    final class com.google.ads.interactivemedia.v3.internal.hu$11 extends gp {
        com.google.ads.interactivemedia.v3.internal.hu$11() {
            super();
        }

        public StringBuilder a(hx arg3) {
            if(arg3.f() == hy.i) {
                arg3.j();
                return null;
            }

            return new StringBuilder(arg3.h());
        }

        public void a(hz arg1, StringBuilder arg2) {
            String v2 = arg2 == null ? null : arg2.toString();
            arg1.b(v2);
        }

        public Object read(hx arg1) {
            return this.a(arg1);
        }

        public void write(hz arg1, Object arg2) {
            this.a(arg1, ((StringBuilder)arg2));
        }
    }

    final class com.google.ads.interactivemedia.v3.internal.hu$12 extends gp {
        com.google.ads.interactivemedia.v3.internal.hu$12() {
            super();
        }

        public BitSet a(hx arg7) {
            StringBuilder v0_1;
            if(arg7.f() == hy.i) {
                arg7.j();
                return null;
            }

            BitSet v0 = new BitSet();
            arg7.a();
            hy v1 = arg7.f();
            int v3 = 0;
            while(v1 != hy.b) {
                boolean v5 = true;
                switch(com.google.ads.interactivemedia.v3.internal.hu$30.a[v1.ordinal()]) {
                    case 1: {
                        if(arg7.m() != 0) {
                            goto label_47;
                        }

                    label_32:
                        v5 = false;
                        break;
                    }
                    case 2: {
                        v5 = arg7.i();
                        break;
                    }
                    case 3: {
                        String v1_1 = arg7.h();
                        try {
                            if(Integer.parseInt(v1_1) == 0) {
                                goto label_32;
                            }
                        }
                        catch(NumberFormatException ) {
                            v0_1 = new StringBuilder();
                            v0_1.append("Error: Expecting: bitset number value (1, 0), Found: ");
                            v0_1.append(v1_1);
                            throw new gn(v0_1.toString());
                        }

                        break;
                    }
                    default: {
                        v0_1 = new StringBuilder();
                        v0_1.append("Invalid bitset value type: ");
                        v0_1.append(v1);
                        throw new gn(v0_1.toString());
                    }
                }

            label_47:
                if(v5) {
                    v0.set(v3);
                }

                ++v3;
                v1 = arg7.f();
            }

            arg7.b();
            return v0;
        }

        public void a(hz arg4, BitSet arg5) {
            if(arg5 == null) {
                arg4.f();
                return;
            }

            arg4.b();
            int v0;
            for(v0 = 0; v0 < arg5.length(); ++v0) {
                arg4.a(((long)arg5.get(v0)));
            }

            arg4.c();
        }

        public Object read(hx arg1) {
            return this.a(arg1);
        }

        public void write(hz arg1, Object arg2) {
            this.a(arg1, ((BitSet)arg2));
        }
    }

    final class com.google.ads.interactivemedia.v3.internal.hu$13 extends gp {
        com.google.ads.interactivemedia.v3.internal.hu$13() {
            super();
        }

        public StringBuffer a(hx arg3) {
            if(arg3.f() == hy.i) {
                arg3.j();
                return null;
            }

            return new StringBuffer(arg3.h());
        }

        public void a(hz arg1, StringBuffer arg2) {
            String v2 = arg2 == null ? null : arg2.toString();
            arg1.b(v2);
        }

        public Object read(hx arg1) {
            return this.a(arg1);
        }

        public void write(hz arg1, Object arg2) {
            this.a(arg1, ((StringBuffer)arg2));
        }
    }

    final class com.google.ads.interactivemedia.v3.internal.hu$14 extends gp {
        com.google.ads.interactivemedia.v3.internal.hu$14() {
            super();
        }

        public URL a(hx arg4) {
            URL v2 = null;
            if(arg4.f() == hy.i) {
                arg4.j();
                return v2;
            }

            String v4 = arg4.h();
            if("null".equals(v4)) {
            }
            else {
                v2 = new URL(v4);
            }

            return v2;
        }

        public void a(hz arg1, URL arg2) {
            String v2 = arg2 == null ? null : arg2.toExternalForm();
            arg1.b(v2);
        }

        public Object read(hx arg1) {
            return this.a(arg1);
        }

        public void write(hz arg1, Object arg2) {
            this.a(arg1, ((URL)arg2));
        }
    }

    final class com.google.ads.interactivemedia.v3.internal.hu$15 extends gp {
        com.google.ads.interactivemedia.v3.internal.hu$15() {
            super();
        }

        public URI a(hx arg4) {
            URI v2 = null;
            if(arg4.f() == hy.i) {
                arg4.j();
                return v2;
            }

            try {
                String v4_1 = arg4.h();
                if("null".equals(v4_1)) {
                }
                else {
                    v2 = new URI(v4_1);
                }
            }
            catch(URISyntaxException v4) {
                throw new gg(((Throwable)v4));
            }

            return v2;
        }

        public void a(hz arg1, URI arg2) {
            String v2 = arg2 == null ? null : arg2.toASCIIString();
            arg1.b(v2);
        }

        public Object read(hx arg1) {
            return this.a(arg1);
        }

        public void write(hz arg1, Object arg2) {
            this.a(arg1, ((URI)arg2));
        }
    }

    final class com.google.ads.interactivemedia.v3.internal.hu$16 extends gp {
        com.google.ads.interactivemedia.v3.internal.hu$16() {
            super();
        }

        public InetAddress a(hx arg3) {
            if(arg3.f() == hy.i) {
                arg3.j();
                return null;
            }

            return InetAddress.getByName(arg3.h());
        }

        public void a(hz arg1, InetAddress arg2) {
            String v2 = arg2 == null ? null : arg2.getHostAddress();
            arg1.b(v2);
        }

        public Object read(hx arg1) {
            return this.a(arg1);
        }

        public void write(hz arg1, Object arg2) {
            this.a(arg1, ((InetAddress)arg2));
        }
    }

    final class com.google.ads.interactivemedia.v3.internal.hu$17 extends gp {
        com.google.ads.interactivemedia.v3.internal.hu$17() {
            super();
        }

        public UUID a(hx arg3) {
            if(arg3.f() == hy.i) {
                arg3.j();
                return null;
            }

            return UUID.fromString(arg3.h());
        }

        public void a(hz arg1, UUID arg2) {
            String v2 = arg2 == null ? null : arg2.toString();
            arg1.b(v2);
        }

        public Object read(hx arg1) {
            return this.a(arg1);
        }

        public void write(hz arg1, Object arg2) {
            this.a(arg1, ((UUID)arg2));
        }
    }

    final class com.google.ads.interactivemedia.v3.internal.hu$18 extends gp {
        com.google.ads.interactivemedia.v3.internal.hu$18() {
            super();
        }

        public Currency a(hx arg1) {
            return Currency.getInstance(arg1.h());
        }

        public void a(hz arg1, Currency arg2) {
            arg1.b(arg2.getCurrencyCode());
        }

        public Object read(hx arg1) {
            return this.a(arg1);
        }

        public void write(hz arg1, Object arg2) {
            this.a(arg1, ((Currency)arg2));
        }
    }

    final class com.google.ads.interactivemedia.v3.internal.hu$19 implements gq {
        com.google.ads.interactivemedia.v3.internal.hu$19() {
            super();
        }

        public gp a(fz arg2, hw arg3) {
            if(arg3.a() != Timestamp.class) {
                return null;
            }

            return new gp(arg2.a(Date.class)) {
                public Timestamp a(hx arg4) {
                    Object v4 = this.a.read(arg4);
                    Timestamp v0 = v4 != null ? new Timestamp(((Date)v4).getTime()) : null;
                    return v0;
                }

                public void a(hz arg2, Timestamp arg3) {
                    this.a.write(arg2, arg3);
                }

                public Object read(hx arg1) {
                    return this.a(arg1);
                }

                public void write(hz arg1, Object arg2) {
                    this.a(arg1, ((Timestamp)arg2));
                }
            };
        }
    }

    final class com.google.ads.interactivemedia.v3.internal.hu$1 extends gp {
        com.google.ads.interactivemedia.v3.internal.hu$1() {
            super();
        }

        public Class a(hx arg3) {
            if(arg3.f() == hy.i) {
                arg3.j();
                return null;
            }

            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }

        public void a(hz arg3, Class arg4) {
            if(arg4 == null) {
                arg3.f();
                return;
            }

            StringBuilder v0 = new StringBuilder();
            v0.append("Attempted to serialize java.lang.Class: ");
            v0.append(arg4.getName());
            v0.append(". Forgot to register a type adapter?");
            throw new UnsupportedOperationException(v0.toString());
        }

        public Object read(hx arg1) {
            return this.a(arg1);
        }

        public void write(hz arg1, Object arg2) {
            this.a(arg1, ((Class)arg2));
        }
    }

    final class com.google.ads.interactivemedia.v3.internal.hu$20 extends gp {
        com.google.ads.interactivemedia.v3.internal.hu$20() {
            super();
        }

        public Calendar a(hx arg10) {
            if(arg10.f() == hy.i) {
                arg10.j();
                return null;
            }

            arg10.c();
            int v2 = 0;
            int v3 = 0;
            int v4 = 0;
            int v5 = 0;
            int v6 = 0;
            int v7;
            for(v7 = 0; arg10.f() != hy.d; v7 = v1) {
                String v0 = arg10.g();
                int v1 = arg10.m();
                if("year".equals(v0)) {
                    v2 = v1;
                    continue;
                }

                if("month".equals(v0)) {
                    v3 = v1;
                    continue;
                }

                if("dayOfMonth".equals(v0)) {
                    v4 = v1;
                    continue;
                }

                if("hourOfDay".equals(v0)) {
                    v5 = v1;
                    continue;
                }

                if("minute".equals(v0)) {
                    v6 = v1;
                    continue;
                }

                if(!"second".equals(v0)) {
                    continue;
                }
            }

            arg10.d();
            return new GregorianCalendar(v2, v3, v4, v5, v6, v7);
        }

        public void a(hz arg3, Calendar arg4) {
            if(arg4 == null) {
                arg3.f();
                return;
            }

            arg3.d();
            arg3.a("year");
            arg3.a(((long)arg4.get(1)));
            arg3.a("month");
            arg3.a(((long)arg4.get(2)));
            arg3.a("dayOfMonth");
            arg3.a(((long)arg4.get(5)));
            arg3.a("hourOfDay");
            arg3.a(((long)arg4.get(11)));
            arg3.a("minute");
            arg3.a(((long)arg4.get(12)));
            arg3.a("second");
            arg3.a(((long)arg4.get(13)));
            arg3.e();
        }

        public Object read(hx arg1) {
            return this.a(arg1);
        }

        public void write(hz arg1, Object arg2) {
            this.a(arg1, ((Calendar)arg2));
        }
    }

    final class com.google.ads.interactivemedia.v3.internal.hu$21 extends gp {
        com.google.ads.interactivemedia.v3.internal.hu$21() {
            super();
        }

        public Locale a(hx arg5) {
            Locale v2 = null;
            if(arg5.f() == hy.i) {
                arg5.j();
                return v2;
            }

            StringTokenizer v0 = new StringTokenizer(arg5.h(), "_");
            String v5 = v0.hasMoreElements() ? v0.nextToken() : ((String)v2);
            String v1 = v0.hasMoreElements() ? v0.nextToken() : ((String)v2);
            if(v0.hasMoreElements()) {
                String v2_1 = v0.nextToken();
            }

            if(v1 == null && (((String)v2)) == null) {
                return new Locale(v5);
            }

            if((((String)v2)) == null) {
                return new Locale(v5, v1);
            }

            return new Locale(v5, v1, ((String)v2));
        }

        public void a(hz arg1, Locale arg2) {
            String v2 = arg2 == null ? null : arg2.toString();
            arg1.b(v2);
        }

        public Object read(hx arg1) {
            return this.a(arg1);
        }

        public void write(hz arg1, Object arg2) {
            this.a(arg1, ((Locale)arg2));
        }
    }

    final class com.google.ads.interactivemedia.v3.internal.hu$22 extends gp {
        com.google.ads.interactivemedia.v3.internal.hu$22() {
            super();
        }

        public gf a(hx arg4) {
            switch(com.google.ads.interactivemedia.v3.internal.hu$30.a[arg4.f().ordinal()]) {
                case 1: {
                    goto label_41;
                }
                case 2: {
                    goto label_36;
                }
                case 3: {
                    goto label_32;
                }
                case 4: {
                    goto label_29;
                }
                case 5: {
                    goto label_19;
                }
                case 6: {
                    goto label_8;
                }
            }

            throw new IllegalArgumentException();
        label_19:
            gc v0 = new gc();
            arg4.a();
            while(arg4.e()) {
                v0.a(this.a(arg4));
            }

            arg4.b();
            return ((gf)v0);
        label_36:
            return new gk(Boolean.valueOf(arg4.i()));
        label_8:
            gi v0_1 = new gi();
            arg4.c();
            while(arg4.e()) {
                v0_1.a(arg4.g(), this.a(arg4));
            }

            arg4.d();
            return ((gf)v0_1);
        label_41:
            return new gk(new hb(arg4.h()));
        label_29:
            arg4.j();
            return gh.a;
        label_32:
            return new gk(arg4.h());
        }

        public void a(hz arg3, gf arg4) {
            Iterator v4_1;
            if(arg4 == null || (arg4.j())) {
                arg3.f();
            }
            else if(arg4.i()) {
                gk v4 = arg4.m();
                if(v4.p()) {
                    arg3.a(v4.a());
                }
                else if(v4.o()) {
                    arg3.a(v4.f());
                }
                else {
                    arg3.b(v4.b());
                }
            }
            else if(arg4.g()) {
                arg3.b();
                v4_1 = arg4.l().iterator();
                while(v4_1.hasNext()) {
                    this.a(arg3, v4_1.next());
                }

                arg3.c();
            }
            else if(arg4.h()) {
                arg3.d();
                v4_1 = arg4.k().o().iterator();
                while(v4_1.hasNext()) {
                    Object v0 = v4_1.next();
                    arg3.a(((Map$Entry)v0).getKey());
                    this.a(arg3, ((Map$Entry)v0).getValue());
                }

                arg3.e();
            }
            else {
                StringBuilder v0_1 = new StringBuilder();
                v0_1.append("Couldn\'t write ");
                v0_1.append(arg4.getClass());
                throw new IllegalArgumentException(v0_1.toString());
            }
        }

        public Object read(hx arg1) {
            return this.a(arg1);
        }

        public void write(hz arg1, Object arg2) {
            this.a(arg1, ((gf)arg2));
        }
    }

    final class com.google.ads.interactivemedia.v3.internal.hu$23 extends gp {
        com.google.ads.interactivemedia.v3.internal.hu$23() {
            super();
        }

        public Boolean a(hx arg3) {
            if(arg3.f() == hy.i) {
                arg3.j();
                return null;
            }

            if(arg3.f() == hy.f) {
                return Boolean.valueOf(Boolean.parseBoolean(arg3.h()));
            }

            return Boolean.valueOf(arg3.i());
        }

        public void a(hz arg1, Boolean arg2) {
            arg1.a(arg2);
        }

        public Object read(hx arg1) {
            return this.a(arg1);
        }

        public void write(hz arg1, Object arg2) {
            this.a(arg1, ((Boolean)arg2));
        }
    }

    final class com.google.ads.interactivemedia.v3.internal.hu$24 implements gq {
        com.google.ads.interactivemedia.v3.internal.hu$24() {
            super();
        }

        public gp a(fz arg1, hw arg2) {
            Class v1 = arg2.a();
            if(Enum.class.isAssignableFrom(v1)) {
                if(v1 == Enum.class) {
                }
                else {
                    if(!v1.isEnum()) {
                        v1 = v1.getSuperclass();
                    }

                    return new a(v1);
                }
            }

            return null;
        }
    }

    final class com.google.ads.interactivemedia.v3.internal.hu$2 extends gp {
        com.google.ads.interactivemedia.v3.internal.hu$2() {
            super();
        }

        public AtomicIntegerArray a(hx arg5) {
            ArrayList v0 = new ArrayList();
            arg5.a();
            while(arg5.e()) {
                try {
                    ((List)v0).add(Integer.valueOf(arg5.m()));
                    continue;
                }
                catch(NumberFormatException v5) {
                    throw new gn(((Throwable)v5));
                }
            }

            arg5.b();
            int v5_1 = ((List)v0).size();
            AtomicIntegerArray v1 = new AtomicIntegerArray(v5_1);
            int v2;
            for(v2 = 0; v2 < v5_1; ++v2) {
                v1.set(v2, ((List)v0).get(v2).intValue());
            }

            return v1;
        }

        public void a(hz arg5, AtomicIntegerArray arg6) {
            arg5.b();
            int v0 = arg6.length();
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                arg5.a(((long)arg6.get(v1)));
            }

            arg5.c();
        }

        public Object read(hx arg1) {
            return this.a(arg1);
        }

        public void write(hz arg1, Object arg2) {
            this.a(arg1, ((AtomicIntegerArray)arg2));
        }
    }

    class com.google.ads.interactivemedia.v3.internal.hu$30 {
        static {
            com.google.ads.interactivemedia.v3.internal.hu$30.a = new int[hy.values().length];
            try {
                com.google.ads.interactivemedia.v3.internal.hu$30.a[hy.g.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    com.google.ads.interactivemedia.v3.internal.hu$30.a[hy.h.ordinal()] = 2;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                    try {
                    label_14:
                        com.google.ads.interactivemedia.v3.internal.hu$30.a[hy.f.ordinal()] = 3;
                        goto label_19;
                    }
                    catch(NoSuchFieldError ) {
                        try {
                        label_19:
                            com.google.ads.interactivemedia.v3.internal.hu$30.a[hy.i.ordinal()] = 4;
                            goto label_24;
                        }
                        catch(NoSuchFieldError ) {
                            try {
                            label_24:
                                com.google.ads.interactivemedia.v3.internal.hu$30.a[hy.a.ordinal()] = 5;
                                goto label_29;
                            }
                            catch(NoSuchFieldError ) {
                                try {
                                label_29:
                                    com.google.ads.interactivemedia.v3.internal.hu$30.a[hy.c.ordinal()] = 6;
                                    goto label_34;
                                }
                                catch(NoSuchFieldError ) {
                                    try {
                                    label_34:
                                        com.google.ads.interactivemedia.v3.internal.hu$30.a[hy.j.ordinal()] = 7;
                                        goto label_39;
                                    }
                                    catch(NoSuchFieldError ) {
                                        try {
                                        label_39:
                                            com.google.ads.interactivemedia.v3.internal.hu$30.a[hy.e.ordinal()] = 8;
                                            goto label_44;
                                        }
                                        catch(NoSuchFieldError ) {
                                            try {
                                            label_44:
                                                com.google.ads.interactivemedia.v3.internal.hu$30.a[hy.d.ordinal()] = 9;
                                                goto label_49;
                                            }
                                            catch(NoSuchFieldError ) {
                                                try {
                                                label_49:
                                                    com.google.ads.interactivemedia.v3.internal.hu$30.a[hy.b.ordinal()] = 10;
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

    final class com.google.ads.interactivemedia.v3.internal.hu$31 extends gp {
        com.google.ads.interactivemedia.v3.internal.hu$31() {
            super();
        }

        public Boolean a(hx arg3) {
            if(arg3.f() == hy.i) {
                arg3.j();
                return null;
            }

            return Boolean.valueOf(arg3.h());
        }

        public void a(hz arg1, Boolean arg2) {
            String v2 = arg2 == null ? "null" : arg2.toString();
            arg1.b(v2);
        }

        public Object read(hx arg1) {
            return this.a(arg1);
        }

        public void write(hz arg1, Object arg2) {
            this.a(arg1, ((Boolean)arg2));
        }
    }

    final class com.google.ads.interactivemedia.v3.internal.hu$32 extends gp {
        com.google.ads.interactivemedia.v3.internal.hu$32() {
            super();
        }

        public Number a(hx arg3) {
            if(arg3.f() == hy.i) {
                arg3.j();
                return null;
            }

            try {
                return Byte.valueOf(((byte)arg3.m()));
            }
            catch(NumberFormatException v3) {
                throw new gn(((Throwable)v3));
            }
        }

        public void a(hz arg1, Number arg2) {
            arg1.a(arg2);
        }

        public Object read(hx arg1) {
            return this.a(arg1);
        }

        public void write(hz arg1, Object arg2) {
            this.a(arg1, ((Number)arg2));
        }
    }

    final class com.google.ads.interactivemedia.v3.internal.hu$33 extends gp {
        com.google.ads.interactivemedia.v3.internal.hu$33() {
            super();
        }

        public Number a(hx arg3) {
            if(arg3.f() == hy.i) {
                arg3.j();
                return null;
            }

            try {
                return Short.valueOf(((short)arg3.m()));
            }
            catch(NumberFormatException v3) {
                throw new gn(((Throwable)v3));
            }
        }

        public void a(hz arg1, Number arg2) {
            arg1.a(arg2);
        }

        public Object read(hx arg1) {
            return this.a(arg1);
        }

        public void write(hz arg1, Object arg2) {
            this.a(arg1, ((Number)arg2));
        }
    }

    final class com.google.ads.interactivemedia.v3.internal.hu$34 extends gp {
        com.google.ads.interactivemedia.v3.internal.hu$34() {
            super();
        }

        public Number a(hx arg3) {
            if(arg3.f() == hy.i) {
                arg3.j();
                return null;
            }

            try {
                return Integer.valueOf(arg3.m());
            }
            catch(NumberFormatException v3) {
                throw new gn(((Throwable)v3));
            }
        }

        public void a(hz arg1, Number arg2) {
            arg1.a(arg2);
        }

        public Object read(hx arg1) {
            return this.a(arg1);
        }

        public void write(hz arg1, Object arg2) {
            this.a(arg1, ((Number)arg2));
        }
    }

    final class com.google.ads.interactivemedia.v3.internal.hu$35 extends gp {
        com.google.ads.interactivemedia.v3.internal.hu$35() {
            super();
        }

        public AtomicInteger a(hx arg2) {
            try {
                return new AtomicInteger(arg2.m());
            }
            catch(NumberFormatException v2) {
                throw new gn(((Throwable)v2));
            }
        }

        public void a(hz arg3, AtomicInteger arg4) {
            arg3.a(((long)arg4.get()));
        }

        public Object read(hx arg1) {
            return this.a(arg1);
        }

        public void write(hz arg1, Object arg2) {
            this.a(arg1, ((AtomicInteger)arg2));
        }
    }

    final class com.google.ads.interactivemedia.v3.internal.hu$36 extends gp {
        com.google.ads.interactivemedia.v3.internal.hu$36() {
            super();
        }

        public AtomicBoolean a(hx arg2) {
            return new AtomicBoolean(arg2.i());
        }

        public void a(hz arg1, AtomicBoolean arg2) {
            arg1.a(arg2.get());
        }

        public Object read(hx arg1) {
            return this.a(arg1);
        }

        public void write(hz arg1, Object arg2) {
            this.a(arg1, ((AtomicBoolean)arg2));
        }
    }

    final class com.google.ads.interactivemedia.v3.internal.hu$3 extends gp {
        com.google.ads.interactivemedia.v3.internal.hu$3() {
            super();
        }

        public Number a(hx arg3) {
            if(arg3.f() == hy.i) {
                arg3.j();
                return null;
            }

            try {
                return Long.valueOf(arg3.l());
            }
            catch(NumberFormatException v3) {
                throw new gn(((Throwable)v3));
            }
        }

        public void a(hz arg1, Number arg2) {
            arg1.a(arg2);
        }

        public Object read(hx arg1) {
            return this.a(arg1);
        }

        public void write(hz arg1, Object arg2) {
            this.a(arg1, ((Number)arg2));
        }
    }

    final class com.google.ads.interactivemedia.v3.internal.hu$4 extends gp {
        com.google.ads.interactivemedia.v3.internal.hu$4() {
            super();
        }

        public Number a(hx arg3) {
            if(arg3.f() == hy.i) {
                arg3.j();
                return null;
            }

            return Float.valueOf(((float)arg3.k()));
        }

        public void a(hz arg1, Number arg2) {
            arg1.a(arg2);
        }

        public Object read(hx arg1) {
            return this.a(arg1);
        }

        public void write(hz arg1, Object arg2) {
            this.a(arg1, ((Number)arg2));
        }
    }

    final class com.google.ads.interactivemedia.v3.internal.hu$5 extends gp {
        com.google.ads.interactivemedia.v3.internal.hu$5() {
            super();
        }

        public Number a(hx arg3) {
            if(arg3.f() == hy.i) {
                arg3.j();
                return null;
            }

            return Double.valueOf(arg3.k());
        }

        public void a(hz arg1, Number arg2) {
            arg1.a(arg2);
        }

        public Object read(hx arg1) {
            return this.a(arg1);
        }

        public void write(hz arg1, Object arg2) {
            this.a(arg1, ((Number)arg2));
        }
    }

    final class com.google.ads.interactivemedia.v3.internal.hu$6 extends gp {
        com.google.ads.interactivemedia.v3.internal.hu$6() {
            super();
        }

        public Number a(hx arg4) {
            hy v0 = arg4.f();
            int v1 = com.google.ads.interactivemedia.v3.internal.hu$30.a[v0.ordinal()];
            if(v1 != 1) {
                if(v1 == 4) {
                    arg4.j();
                    return null;
                }

                StringBuilder v1_1 = new StringBuilder();
                v1_1.append("Expecting number, got: ");
                v1_1.append(v0);
                throw new gn(v1_1.toString());
            }

            return new hb(arg4.h());
        }

        public void a(hz arg1, Number arg2) {
            arg1.a(arg2);
        }

        public Object read(hx arg1) {
            return this.a(arg1);
        }

        public void write(hz arg1, Object arg2) {
            this.a(arg1, ((Number)arg2));
        }
    }

    final class com.google.ads.interactivemedia.v3.internal.hu$7 extends gp {
        com.google.ads.interactivemedia.v3.internal.hu$7() {
            super();
        }

        public Character a(hx arg4) {
            if(arg4.f() == hy.i) {
                arg4.j();
                return null;
            }

            String v4 = arg4.h();
            if(v4.length() == 1) {
                return Character.valueOf(v4.charAt(0));
            }

            StringBuilder v1 = new StringBuilder();
            v1.append("Expecting character, got: ");
            v1.append(v4);
            throw new gn(v1.toString());
        }

        public void a(hz arg1, Character arg2) {
            String v2 = arg2 == null ? null : String.valueOf(arg2);
            arg1.b(v2);
        }

        public Object read(hx arg1) {
            return this.a(arg1);
        }

        public void write(hz arg1, Object arg2) {
            this.a(arg1, ((Character)arg2));
        }
    }

    final class com.google.ads.interactivemedia.v3.internal.hu$8 extends gp {
        com.google.ads.interactivemedia.v3.internal.hu$8() {
            super();
        }

        public String a(hx arg3) {
            hy v0 = arg3.f();
            if(v0 == hy.i) {
                arg3.j();
                return null;
            }

            if(v0 == hy.h) {
                return Boolean.toString(arg3.i());
            }

            return arg3.h();
        }

        public void a(hz arg1, String arg2) {
            arg1.b(arg2);
        }

        public Object read(hx arg1) {
            return this.a(arg1);
        }

        public void write(hz arg1, Object arg2) {
            this.a(arg1, ((String)arg2));
        }
    }

    final class com.google.ads.interactivemedia.v3.internal.hu$9 extends gp {
        com.google.ads.interactivemedia.v3.internal.hu$9() {
            super();
        }

        public BigDecimal a(hx arg3) {
            if(arg3.f() == hy.i) {
                arg3.j();
                return null;
            }

            try {
                return new BigDecimal(arg3.h());
            }
            catch(NumberFormatException v3) {
                throw new gn(((Throwable)v3));
            }
        }

        public void a(hz arg1, BigDecimal arg2) {
            arg1.a(((Number)arg2));
        }

        public Object read(hx arg1) {
            return this.a(arg1);
        }

        public void write(hz arg1, Object arg2) {
            this.a(arg1, ((BigDecimal)arg2));
        }
    }

    final class a extends gp {
        private final Map a;
        private final Map b;

        public a(Class arg12) {
            super();
            this.a = new HashMap();
            this.b = new HashMap();
            try {
                Object[] v0 = arg12.getEnumConstants();
                int v1 = v0.length;
                int v3;
                for(v3 = 0; v3 < v1; ++v3) {
                    Object v4 = v0[v3];
                    String v5 = ((Enum)v4).name();
                    Annotation v6 = arg12.getField(v5).getAnnotation(gt.class);
                    if(v6 != null) {
                        v5 = ((gt)v6).a();
                        String[] v6_1 = ((gt)v6).b();
                        int v7 = v6_1.length;
                        int v8;
                        for(v8 = 0; v8 < v7; ++v8) {
                            this.a.put(v6_1[v8], v4);
                        }
                    }

                    this.a.put(v5, v4);
                    this.b.put(v4, v5);
                }
            }
            catch(NoSuchFieldException v12) {
                goto label_38;
            }

            return;
        label_38:
            throw new AssertionError(v12);
        }

        public Enum a(hx arg3) {
            if(arg3.f() == hy.i) {
                arg3.j();
                return null;
            }

            return this.a.get(arg3.h());
        }

        public void a(hz arg2, Enum arg3) {
            Object v3_1;
            if(arg3 == null) {
                String v3 = null;
            }
            else {
                v3_1 = this.b.get(arg3);
            }

            arg2.b(((String)v3_1));
        }

        public Object read(hx arg1) {
            return this.a(arg1);
        }

        public void write(hz arg1, Object arg2) {
            this.a(arg1, ((Enum)arg2));
        }
    }

    public static final gp A;
    public static final gp B;
    public static final gp C;
    public static final gq D;
    public static final gp E;
    public static final gq F;
    public static final gp G;
    public static final gq H;
    public static final gp I;
    public static final gq J;
    public static final gp K;
    public static final gq L;
    public static final gp M;
    public static final gq N;
    public static final gp O;
    public static final gq P;
    public static final gp Q;
    public static final gq R;
    public static final gq S;
    public static final gp T;
    public static final gq U;
    public static final gp V;
    public static final gq W;
    public static final gp X;
    public static final gq Y;
    public static final gq Z;
    public static final gp a;
    public static final gq b;
    public static final gp c;
    public static final gq d;
    public static final gp e;
    public static final gp f;
    public static final gq g;
    public static final gp h;
    public static final gq i;
    public static final gp j;
    public static final gq k;
    public static final gp l;
    public static final gq m;
    public static final gp n;
    public static final gq o;
    public static final gp p;
    public static final gq q;
    public static final gp r;
    public static final gq s;
    public static final gp t;
    public static final gp u;
    public static final gp v;
    public static final gp w;
    public static final gq x;
    public static final gp y;
    public static final gq z;

    static {
        hu.a = new com.google.ads.interactivemedia.v3.internal.hu$1();
        hu.b = hu.a(Class.class, hu.a);
        hu.c = new com.google.ads.interactivemedia.v3.internal.hu$12();
        hu.d = hu.a(BitSet.class, hu.c);
        hu.e = new com.google.ads.interactivemedia.v3.internal.hu$23();
        hu.f = new com.google.ads.interactivemedia.v3.internal.hu$31();
        hu.g = hu.a(Boolean.TYPE, Boolean.class, hu.e);
        hu.h = new com.google.ads.interactivemedia.v3.internal.hu$32();
        hu.i = hu.a(Byte.TYPE, Byte.class, hu.h);
        hu.j = new com.google.ads.interactivemedia.v3.internal.hu$33();
        hu.k = hu.a(Short.TYPE, Short.class, hu.j);
        hu.l = new com.google.ads.interactivemedia.v3.internal.hu$34();
        hu.m = hu.a(Integer.TYPE, Integer.class, hu.l);
        hu.n = new com.google.ads.interactivemedia.v3.internal.hu$35().nullSafe();
        hu.o = hu.a(AtomicInteger.class, hu.n);
        hu.p = new com.google.ads.interactivemedia.v3.internal.hu$36().nullSafe();
        hu.q = hu.a(AtomicBoolean.class, hu.p);
        hu.r = new com.google.ads.interactivemedia.v3.internal.hu$2().nullSafe();
        hu.s = hu.a(AtomicIntegerArray.class, hu.r);
        hu.t = new com.google.ads.interactivemedia.v3.internal.hu$3();
        hu.u = new com.google.ads.interactivemedia.v3.internal.hu$4();
        hu.v = new com.google.ads.interactivemedia.v3.internal.hu$5();
        hu.w = new com.google.ads.interactivemedia.v3.internal.hu$6();
        hu.x = hu.a(Number.class, hu.w);
        hu.y = new com.google.ads.interactivemedia.v3.internal.hu$7();
        hu.z = hu.a(Character.TYPE, Character.class, hu.y);
        hu.A = new com.google.ads.interactivemedia.v3.internal.hu$8();
        hu.B = new com.google.ads.interactivemedia.v3.internal.hu$9();
        hu.C = new com.google.ads.interactivemedia.v3.internal.hu$10();
        hu.D = hu.a(String.class, hu.A);
        hu.E = new com.google.ads.interactivemedia.v3.internal.hu$11();
        hu.F = hu.a(StringBuilder.class, hu.E);
        hu.G = new com.google.ads.interactivemedia.v3.internal.hu$13();
        hu.H = hu.a(StringBuffer.class, hu.G);
        hu.I = new com.google.ads.interactivemedia.v3.internal.hu$14();
        hu.J = hu.a(URL.class, hu.I);
        hu.K = new com.google.ads.interactivemedia.v3.internal.hu$15();
        hu.L = hu.a(URI.class, hu.K);
        hu.M = new com.google.ads.interactivemedia.v3.internal.hu$16();
        hu.N = hu.b(InetAddress.class, hu.M);
        hu.O = new com.google.ads.interactivemedia.v3.internal.hu$17();
        hu.P = hu.a(UUID.class, hu.O);
        hu.Q = new com.google.ads.interactivemedia.v3.internal.hu$18().nullSafe();
        hu.R = hu.a(Currency.class, hu.Q);
        hu.S = new com.google.ads.interactivemedia.v3.internal.hu$19();
        hu.T = new com.google.ads.interactivemedia.v3.internal.hu$20();
        hu.U = hu.b(Calendar.class, GregorianCalendar.class, hu.T);
        hu.V = new com.google.ads.interactivemedia.v3.internal.hu$21();
        hu.W = hu.a(Locale.class, hu.V);
        hu.X = new com.google.ads.interactivemedia.v3.internal.hu$22();
        hu.Y = hu.b(gf.class, hu.X);
        hu.Z = new com.google.ads.interactivemedia.v3.internal.hu$24();
    }

    public static gq a(Class arg1, Class arg2, gp arg3) {
        return new gq(arg1, arg2, arg3) {
            public gp a(fz arg1, hw arg2) {
                Class v1 = arg2.a();
                gp v1_1 = v1 == this.a || v1 == this.b ? this.c : null;
                return v1_1;
            }

            public String toString() {
                return "Factory[type=" + this.b.getName() + "+" + this.a.getName() + ",adapter=" + this.c + "]";
            }
        };
    }

    public static gq a(Class arg1, gp arg2) {
        return new gq(arg1, arg2) {
            public gp a(fz arg1, hw arg2) {
                gp v1 = arg2.a() == this.a ? this.b : null;
                return v1;
            }

            public String toString() {
                return "Factory[type=" + this.a.getName() + ",adapter=" + this.b + "]";
            }
        };
    }

    public static gq a(hw arg1, gp arg2) {
        return new gq(arg1, arg2) {
            public gp a(fz arg1, hw arg2) {
                gp v1 = arg2.equals(this.a) ? this.b : null;
                return v1;
            }
        };
    }

    public static gq b(Class arg1, gp arg2) {
        return new gq(arg1, arg2) {
            public gp a(fz arg1, hw arg2) {
                Class v1 = arg2.a();
                if(!this.a.isAssignableFrom(v1)) {
                    return null;
                }

                return new gp(v1) {
                    public Object read(hx arg4) {
                        Object v4 = this.b.b.read(arg4);
                        if(v4 != null) {
                            if(this.a.isInstance(v4)) {
                            }
                            else {
                                StringBuilder v1 = new StringBuilder();
                                v1.append("Expected a ");
                                v1.append(this.a.getName());
                                v1.append(" but was ");
                                v1.append(v4.getClass().getName());
                                throw new gn(v1.toString());
                            }
                        }

                        return v4;
                    }

                    public void write(hz arg2, Object arg3) {
                        this.b.b.write(arg2, arg3);
                    }
                };
            }

            public String toString() {
                return "Factory[typeHierarchy=" + this.a.getName() + ",adapter=" + this.b + "]";
            }
        };
    }

    public static gq b(Class arg1, Class arg2, gp arg3) {
        return new gq(arg1, arg2, arg3) {
            public gp a(fz arg1, hw arg2) {
                Class v1 = arg2.a();
                gp v1_1 = v1 == this.a || v1 == this.b ? this.c : null;
                return v1_1;
            }

            public String toString() {
                return "Factory[type=" + this.a.getName() + "+" + this.b.getName() + ",adapter=" + this.c + "]";
            }
        };
    }
}


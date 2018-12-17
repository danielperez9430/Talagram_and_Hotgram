package com.google.a.b.a;

import com.google.a.d.a;
import com.google.a.d.b;
import com.google.a.d.c;
import com.google.a.f;
import com.google.a.i;
import com.google.a.l;
import com.google.a.m;
import com.google.a.o;
import com.google.a.q;
import com.google.a.t;
import com.google.a.v;
import com.google.a.w;
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

public final class n {
    final class com.google.a.b.a.n$10 extends v {
        com.google.a.b.a.n$10() {
            super();
        }

        public BigInteger a(a arg3) {
            if(arg3.f() == b.i) {
                arg3.j();
                return null;
            }

            try {
                return new BigInteger(arg3.h());
            }
            catch(NumberFormatException v3) {
                throw new t(((Throwable)v3));
            }
        }

        public void a(c arg1, BigInteger arg2) {
            arg1.a(((Number)arg2));
        }

        public Object read(a arg1) {
            return this.a(arg1);
        }

        public void write(c arg1, Object arg2) {
            this.a(arg1, ((BigInteger)arg2));
        }
    }

    final class com.google.a.b.a.n$11 extends v {
        com.google.a.b.a.n$11() {
            super();
        }

        public StringBuilder a(a arg3) {
            if(arg3.f() == b.i) {
                arg3.j();
                return null;
            }

            return new StringBuilder(arg3.h());
        }

        public void a(c arg1, StringBuilder arg2) {
            String v2 = arg2 == null ? null : arg2.toString();
            arg1.b(v2);
        }

        public Object read(a arg1) {
            return this.a(arg1);
        }

        public void write(c arg1, Object arg2) {
            this.a(arg1, ((StringBuilder)arg2));
        }
    }

    final class com.google.a.b.a.n$12 extends v {
        com.google.a.b.a.n$12() {
            super();
        }

        public BitSet a(a arg7) {
            StringBuilder v0_1;
            BitSet v0 = new BitSet();
            arg7.a();
            b v1 = arg7.f();
            int v3 = 0;
            while(v1 != b.b) {
                boolean v5 = true;
                switch(com.google.a.b.a.n$29.a[v1.ordinal()]) {
                    case 1: {
                        if(arg7.m() != 0) {
                            goto label_41;
                        }

                    label_26:
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
                                goto label_26;
                            }
                        }
                        catch(NumberFormatException ) {
                            v0_1 = new StringBuilder();
                            v0_1.append("Error: Expecting: bitset number value (1, 0), Found: ");
                            v0_1.append(v1_1);
                            throw new t(v0_1.toString());
                        }

                        break;
                    }
                    default: {
                        v0_1 = new StringBuilder();
                        v0_1.append("Invalid bitset value type: ");
                        v0_1.append(v1);
                        throw new t(v0_1.toString());
                    }
                }

            label_41:
                if(v5) {
                    v0.set(v3);
                }

                ++v3;
                v1 = arg7.f();
            }

            arg7.b();
            return v0;
        }

        public void a(c arg5, BitSet arg6) {
            arg5.b();
            int v0 = arg6.length();
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                arg5.a(((long)arg6.get(v1)));
            }

            arg5.c();
        }

        public Object read(a arg1) {
            return this.a(arg1);
        }

        public void write(c arg1, Object arg2) {
            this.a(arg1, ((BitSet)arg2));
        }
    }

    final class com.google.a.b.a.n$13 extends v {
        com.google.a.b.a.n$13() {
            super();
        }

        public StringBuffer a(a arg3) {
            if(arg3.f() == b.i) {
                arg3.j();
                return null;
            }

            return new StringBuffer(arg3.h());
        }

        public void a(c arg1, StringBuffer arg2) {
            String v2 = arg2 == null ? null : arg2.toString();
            arg1.b(v2);
        }

        public Object read(a arg1) {
            return this.a(arg1);
        }

        public void write(c arg1, Object arg2) {
            this.a(arg1, ((StringBuffer)arg2));
        }
    }

    final class com.google.a.b.a.n$14 extends v {
        com.google.a.b.a.n$14() {
            super();
        }

        public URL a(a arg4) {
            URL v2 = null;
            if(arg4.f() == b.i) {
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

        public void a(c arg1, URL arg2) {
            String v2 = arg2 == null ? null : arg2.toExternalForm();
            arg1.b(v2);
        }

        public Object read(a arg1) {
            return this.a(arg1);
        }

        public void write(c arg1, Object arg2) {
            this.a(arg1, ((URL)arg2));
        }
    }

    final class com.google.a.b.a.n$15 extends v {
        com.google.a.b.a.n$15() {
            super();
        }

        public URI a(a arg4) {
            URI v2 = null;
            if(arg4.f() == b.i) {
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
                throw new m(((Throwable)v4));
            }

            return v2;
        }

        public void a(c arg1, URI arg2) {
            String v2 = arg2 == null ? null : arg2.toASCIIString();
            arg1.b(v2);
        }

        public Object read(a arg1) {
            return this.a(arg1);
        }

        public void write(c arg1, Object arg2) {
            this.a(arg1, ((URI)arg2));
        }
    }

    final class com.google.a.b.a.n$16 extends v {
        com.google.a.b.a.n$16() {
            super();
        }

        public InetAddress a(a arg3) {
            if(arg3.f() == b.i) {
                arg3.j();
                return null;
            }

            return InetAddress.getByName(arg3.h());
        }

        public void a(c arg1, InetAddress arg2) {
            String v2 = arg2 == null ? null : arg2.getHostAddress();
            arg1.b(v2);
        }

        public Object read(a arg1) {
            return this.a(arg1);
        }

        public void write(c arg1, Object arg2) {
            this.a(arg1, ((InetAddress)arg2));
        }
    }

    final class com.google.a.b.a.n$17 extends v {
        com.google.a.b.a.n$17() {
            super();
        }

        public UUID a(a arg3) {
            if(arg3.f() == b.i) {
                arg3.j();
                return null;
            }

            return UUID.fromString(arg3.h());
        }

        public void a(c arg1, UUID arg2) {
            String v2 = arg2 == null ? null : arg2.toString();
            arg1.b(v2);
        }

        public Object read(a arg1) {
            return this.a(arg1);
        }

        public void write(c arg1, Object arg2) {
            this.a(arg1, ((UUID)arg2));
        }
    }

    final class com.google.a.b.a.n$18 extends v {
        com.google.a.b.a.n$18() {
            super();
        }

        public Currency a(a arg1) {
            return Currency.getInstance(arg1.h());
        }

        public void a(c arg1, Currency arg2) {
            arg1.b(arg2.getCurrencyCode());
        }

        public Object read(a arg1) {
            return this.a(arg1);
        }

        public void write(c arg1, Object arg2) {
            this.a(arg1, ((Currency)arg2));
        }
    }

    final class com.google.a.b.a.n$19 implements w {
        com.google.a.b.a.n$19() {
            super();
        }

        public v create(f arg2, com.google.a.c.a arg3) {
            if(arg3.a() != Timestamp.class) {
                return null;
            }

            return new v(arg2.a(Date.class)) {
                public Timestamp a(a arg4) {
                    Object v4 = this.a.read(arg4);
                    Timestamp v0 = v4 != null ? new Timestamp(((Date)v4).getTime()) : null;
                    return v0;
                }

                public void a(c arg2, Timestamp arg3) {
                    this.a.write(arg2, arg3);
                }

                public Object read(a arg1) {
                    return this.a(arg1);
                }

                public void write(c arg1, Object arg2) {
                    this.a(arg1, ((Timestamp)arg2));
                }
            };
        }
    }

    final class com.google.a.b.a.n$1 extends v {
        com.google.a.b.a.n$1() {
            super();
        }

        public Class a(a arg2) {
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }

        public void a(c arg3, Class arg4) {
            StringBuilder v0 = new StringBuilder();
            v0.append("Attempted to serialize java.lang.Class: ");
            v0.append(arg4.getName());
            v0.append(". Forgot to register a type adapter?");
            throw new UnsupportedOperationException(v0.toString());
        }

        public Object read(a arg1) {
            return this.a(arg1);
        }

        public void write(c arg1, Object arg2) {
            this.a(arg1, ((Class)arg2));
        }
    }

    final class com.google.a.b.a.n$20 extends v {
        com.google.a.b.a.n$20() {
            super();
        }

        public Calendar a(a arg10) {
            if(arg10.f() == b.i) {
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
            for(v7 = 0; arg10.f() != b.d; v7 = v1) {
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

        public void a(c arg3, Calendar arg4) {
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

        public Object read(a arg1) {
            return this.a(arg1);
        }

        public void write(c arg1, Object arg2) {
            this.a(arg1, ((Calendar)arg2));
        }
    }

    final class com.google.a.b.a.n$21 extends v {
        com.google.a.b.a.n$21() {
            super();
        }

        public Locale a(a arg5) {
            String v2_1;
            Locale v2 = null;
            if(arg5.f() == b.i) {
                arg5.j();
                return v2;
            }

            StringTokenizer v0 = new StringTokenizer(arg5.h(), "_");
            String v5 = v0.hasMoreElements() ? v0.nextToken() : ((String)v2);
            String v1 = v0.hasMoreElements() ? v0.nextToken() : ((String)v2);
            if(v0.hasMoreElements()) {
                v2_1 = v0.nextToken();
            }

            if(v1 == null && v2_1 == null) {
                return new Locale(v5);
            }

            if(v2_1 == null) {
                return new Locale(v5, v1);
            }

            return new Locale(v5, v1, v2_1);
        }

        public void a(c arg1, Locale arg2) {
            String v2 = arg2 == null ? null : arg2.toString();
            arg1.b(v2);
        }

        public Object read(a arg1) {
            return this.a(arg1);
        }

        public void write(c arg1, Object arg2) {
            this.a(arg1, ((Locale)arg2));
        }
    }

    final class com.google.a.b.a.n$22 extends v {
        com.google.a.b.a.n$22() {
            super();
        }

        public l a(a arg4) {
            switch(com.google.a.b.a.n$29.a[arg4.f().ordinal()]) {
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
            i v0 = new i();
            arg4.a();
            while(arg4.e()) {
                v0.a(this.a(arg4));
            }

            arg4.b();
            return ((l)v0);
        label_36:
            return new q(Boolean.valueOf(arg4.i()));
        label_8:
            o v0_1 = new o();
            arg4.c();
            while(arg4.e()) {
                v0_1.a(arg4.g(), this.a(arg4));
            }

            arg4.d();
            return ((l)v0_1);
        label_41:
            return new q(new com.google.a.b.f(arg4.h()));
        label_29:
            arg4.j();
            return com.google.a.n.a;
        label_32:
            return new q(arg4.h());
        }

        public void a(c arg3, l arg4) {
            Iterator v4_1;
            if(arg4 == null || (arg4.j())) {
                arg3.f();
            }
            else if(arg4.i()) {
                q v4 = arg4.m();
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

        public Object read(a arg1) {
            return this.a(arg1);
        }

        public void write(c arg1, Object arg2) {
            this.a(arg1, ((l)arg2));
        }
    }

    final class com.google.a.b.a.n$23 extends v {
        com.google.a.b.a.n$23() {
            super();
        }

        public Boolean a(a arg3) {
            if(arg3.f() == b.i) {
                arg3.j();
                return null;
            }

            if(arg3.f() == b.f) {
                return Boolean.valueOf(Boolean.parseBoolean(arg3.h()));
            }

            return Boolean.valueOf(arg3.i());
        }

        public void a(c arg1, Boolean arg2) {
            arg1.a(arg2);
        }

        public Object read(a arg1) {
            return this.a(arg1);
        }

        public void write(c arg1, Object arg2) {
            this.a(arg1, ((Boolean)arg2));
        }
    }

    final class com.google.a.b.a.n$24 implements w {
        com.google.a.b.a.n$24() {
            super();
        }

        public v create(f arg1, com.google.a.c.a arg2) {
            Class v1 = arg2.a();
            if(Enum.class.isAssignableFrom(v1)) {
                if(v1 == Enum.class) {
                }
                else {
                    if(!v1.isEnum()) {
                        v1 = v1.getSuperclass();
                    }

                    return new com.google.a.b.a.n$a(v1);
                }
            }

            return null;
        }
    }

    class com.google.a.b.a.n$29 {
        static {
            com.google.a.b.a.n$29.a = new int[b.values().length];
            try {
                com.google.a.b.a.n$29.a[b.g.ordinal()] = 1;
                goto label_9;
            }
            catch(NoSuchFieldError ) {
                try {
                label_9:
                    com.google.a.b.a.n$29.a[b.h.ordinal()] = 2;
                    goto label_14;
                }
                catch(NoSuchFieldError ) {
                    try {
                    label_14:
                        com.google.a.b.a.n$29.a[b.f.ordinal()] = 3;
                        goto label_19;
                    }
                    catch(NoSuchFieldError ) {
                        try {
                        label_19:
                            com.google.a.b.a.n$29.a[b.i.ordinal()] = 4;
                            goto label_24;
                        }
                        catch(NoSuchFieldError ) {
                            try {
                            label_24:
                                com.google.a.b.a.n$29.a[b.a.ordinal()] = 5;
                                goto label_29;
                            }
                            catch(NoSuchFieldError ) {
                                try {
                                label_29:
                                    com.google.a.b.a.n$29.a[b.c.ordinal()] = 6;
                                    goto label_34;
                                }
                                catch(NoSuchFieldError ) {
                                    try {
                                    label_34:
                                        com.google.a.b.a.n$29.a[b.j.ordinal()] = 7;
                                        goto label_39;
                                    }
                                    catch(NoSuchFieldError ) {
                                        try {
                                        label_39:
                                            com.google.a.b.a.n$29.a[b.e.ordinal()] = 8;
                                            goto label_44;
                                        }
                                        catch(NoSuchFieldError ) {
                                            try {
                                            label_44:
                                                com.google.a.b.a.n$29.a[b.d.ordinal()] = 9;
                                                goto label_49;
                                            }
                                            catch(NoSuchFieldError ) {
                                                try {
                                                label_49:
                                                    com.google.a.b.a.n$29.a[b.b.ordinal()] = 10;
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

    final class com.google.a.b.a.n$2 extends v {
        com.google.a.b.a.n$2() {
            super();
        }

        public AtomicIntegerArray a(a arg5) {
            ArrayList v0 = new ArrayList();
            arg5.a();
            while(arg5.e()) {
                try {
                    ((List)v0).add(Integer.valueOf(arg5.m()));
                    continue;
                }
                catch(NumberFormatException v5) {
                    throw new t(((Throwable)v5));
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

        public void a(c arg5, AtomicIntegerArray arg6) {
            arg5.b();
            int v0 = arg6.length();
            int v1;
            for(v1 = 0; v1 < v0; ++v1) {
                arg5.a(((long)arg6.get(v1)));
            }

            arg5.c();
        }

        public Object read(a arg1) {
            return this.a(arg1);
        }

        public void write(c arg1, Object arg2) {
            this.a(arg1, ((AtomicIntegerArray)arg2));
        }
    }

    final class com.google.a.b.a.n$30 extends v {
        com.google.a.b.a.n$30() {
            super();
        }

        public Boolean a(a arg3) {
            if(arg3.f() == b.i) {
                arg3.j();
                return null;
            }

            return Boolean.valueOf(arg3.h());
        }

        public void a(c arg1, Boolean arg2) {
            String v2 = arg2 == null ? "null" : arg2.toString();
            arg1.b(v2);
        }

        public Object read(a arg1) {
            return this.a(arg1);
        }

        public void write(c arg1, Object arg2) {
            this.a(arg1, ((Boolean)arg2));
        }
    }

    final class com.google.a.b.a.n$31 extends v {
        com.google.a.b.a.n$31() {
            super();
        }

        public Number a(a arg3) {
            if(arg3.f() == b.i) {
                arg3.j();
                return null;
            }

            try {
                return Byte.valueOf(((byte)arg3.m()));
            }
            catch(NumberFormatException v3) {
                throw new t(((Throwable)v3));
            }
        }

        public void a(c arg1, Number arg2) {
            arg1.a(arg2);
        }

        public Object read(a arg1) {
            return this.a(arg1);
        }

        public void write(c arg1, Object arg2) {
            this.a(arg1, ((Number)arg2));
        }
    }

    final class com.google.a.b.a.n$32 extends v {
        com.google.a.b.a.n$32() {
            super();
        }

        public Number a(a arg3) {
            if(arg3.f() == b.i) {
                arg3.j();
                return null;
            }

            try {
                return Short.valueOf(((short)arg3.m()));
            }
            catch(NumberFormatException v3) {
                throw new t(((Throwable)v3));
            }
        }

        public void a(c arg1, Number arg2) {
            arg1.a(arg2);
        }

        public Object read(a arg1) {
            return this.a(arg1);
        }

        public void write(c arg1, Object arg2) {
            this.a(arg1, ((Number)arg2));
        }
    }

    final class com.google.a.b.a.n$33 extends v {
        com.google.a.b.a.n$33() {
            super();
        }

        public Number a(a arg3) {
            if(arg3.f() == b.i) {
                arg3.j();
                return null;
            }

            try {
                return Integer.valueOf(arg3.m());
            }
            catch(NumberFormatException v3) {
                throw new t(((Throwable)v3));
            }
        }

        public void a(c arg1, Number arg2) {
            arg1.a(arg2);
        }

        public Object read(a arg1) {
            return this.a(arg1);
        }

        public void write(c arg1, Object arg2) {
            this.a(arg1, ((Number)arg2));
        }
    }

    final class com.google.a.b.a.n$34 extends v {
        com.google.a.b.a.n$34() {
            super();
        }

        public AtomicInteger a(a arg2) {
            try {
                return new AtomicInteger(arg2.m());
            }
            catch(NumberFormatException v2) {
                throw new t(((Throwable)v2));
            }
        }

        public void a(c arg3, AtomicInteger arg4) {
            arg3.a(((long)arg4.get()));
        }

        public Object read(a arg1) {
            return this.a(arg1);
        }

        public void write(c arg1, Object arg2) {
            this.a(arg1, ((AtomicInteger)arg2));
        }
    }

    final class com.google.a.b.a.n$35 extends v {
        com.google.a.b.a.n$35() {
            super();
        }

        public AtomicBoolean a(a arg2) {
            return new AtomicBoolean(arg2.i());
        }

        public void a(c arg1, AtomicBoolean arg2) {
            arg1.a(arg2.get());
        }

        public Object read(a arg1) {
            return this.a(arg1);
        }

        public void write(c arg1, Object arg2) {
            this.a(arg1, ((AtomicBoolean)arg2));
        }
    }

    final class com.google.a.b.a.n$3 extends v {
        com.google.a.b.a.n$3() {
            super();
        }

        public Number a(a arg3) {
            if(arg3.f() == b.i) {
                arg3.j();
                return null;
            }

            try {
                return Long.valueOf(arg3.l());
            }
            catch(NumberFormatException v3) {
                throw new t(((Throwable)v3));
            }
        }

        public void a(c arg1, Number arg2) {
            arg1.a(arg2);
        }

        public Object read(a arg1) {
            return this.a(arg1);
        }

        public void write(c arg1, Object arg2) {
            this.a(arg1, ((Number)arg2));
        }
    }

    final class com.google.a.b.a.n$4 extends v {
        com.google.a.b.a.n$4() {
            super();
        }

        public Number a(a arg3) {
            if(arg3.f() == b.i) {
                arg3.j();
                return null;
            }

            return Float.valueOf(((float)arg3.k()));
        }

        public void a(c arg1, Number arg2) {
            arg1.a(arg2);
        }

        public Object read(a arg1) {
            return this.a(arg1);
        }

        public void write(c arg1, Object arg2) {
            this.a(arg1, ((Number)arg2));
        }
    }

    final class com.google.a.b.a.n$5 extends v {
        com.google.a.b.a.n$5() {
            super();
        }

        public Number a(a arg3) {
            if(arg3.f() == b.i) {
                arg3.j();
                return null;
            }

            return Double.valueOf(arg3.k());
        }

        public void a(c arg1, Number arg2) {
            arg1.a(arg2);
        }

        public Object read(a arg1) {
            return this.a(arg1);
        }

        public void write(c arg1, Object arg2) {
            this.a(arg1, ((Number)arg2));
        }
    }

    final class com.google.a.b.a.n$6 extends v {
        com.google.a.b.a.n$6() {
            super();
        }

        public Number a(a arg4) {
            b v0 = arg4.f();
            int v1 = com.google.a.b.a.n$29.a[v0.ordinal()];
            if(v1 != 1) {
                switch(v1) {
                    case 3: {
                        goto label_19;
                    }
                    case 4: {
                        goto label_16;
                    }
                }

                StringBuilder v1_1 = new StringBuilder();
                v1_1.append("Expecting number, got: ");
                v1_1.append(v0);
                throw new t(v1_1.toString());
            label_16:
                arg4.j();
                return null;
            }

        label_19:
            return new com.google.a.b.f(arg4.h());
        }

        public void a(c arg1, Number arg2) {
            arg1.a(arg2);
        }

        public Object read(a arg1) {
            return this.a(arg1);
        }

        public void write(c arg1, Object arg2) {
            this.a(arg1, ((Number)arg2));
        }
    }

    final class com.google.a.b.a.n$7 extends v {
        com.google.a.b.a.n$7() {
            super();
        }

        public Character a(a arg4) {
            if(arg4.f() == b.i) {
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
            throw new t(v1.toString());
        }

        public void a(c arg1, Character arg2) {
            String v2 = arg2 == null ? null : String.valueOf(arg2);
            arg1.b(v2);
        }

        public Object read(a arg1) {
            return this.a(arg1);
        }

        public void write(c arg1, Object arg2) {
            this.a(arg1, ((Character)arg2));
        }
    }

    final class com.google.a.b.a.n$8 extends v {
        com.google.a.b.a.n$8() {
            super();
        }

        public String a(a arg3) {
            b v0 = arg3.f();
            if(v0 == b.i) {
                arg3.j();
                return null;
            }

            if(v0 == b.h) {
                return Boolean.toString(arg3.i());
            }

            return arg3.h();
        }

        public void a(c arg1, String arg2) {
            arg1.b(arg2);
        }

        public Object read(a arg1) {
            return this.a(arg1);
        }

        public void write(c arg1, Object arg2) {
            this.a(arg1, ((String)arg2));
        }
    }

    final class com.google.a.b.a.n$9 extends v {
        com.google.a.b.a.n$9() {
            super();
        }

        public BigDecimal a(a arg3) {
            if(arg3.f() == b.i) {
                arg3.j();
                return null;
            }

            try {
                return new BigDecimal(arg3.h());
            }
            catch(NumberFormatException v3) {
                throw new t(((Throwable)v3));
            }
        }

        public void a(c arg1, BigDecimal arg2) {
            arg1.a(((Number)arg2));
        }

        public Object read(a arg1) {
            return this.a(arg1);
        }

        public void write(c arg1, Object arg2) {
            this.a(arg1, ((BigDecimal)arg2));
        }
    }

    final class com.google.a.b.a.n$a extends v {
        private final Map a;
        private final Map b;

        public com.google.a.b.a.n$a(Class arg12) {
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
                    Annotation v6 = arg12.getField(v5).getAnnotation(com.google.a.a.c.class);
                    if(v6 != null) {
                        v5 = ((com.google.a.a.c)v6).a();
                        String[] v6_1 = ((com.google.a.a.c)v6).b();
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

        public Enum a(a arg3) {
            if(arg3.f() == b.i) {
                arg3.j();
                return null;
            }

            return this.a.get(arg3.h());
        }

        public void a(c arg2, Enum arg3) {
            String v3;
            if(arg3 == null) {
                v3 = null;
            }
            else {
                Object v3_1 = this.b.get(arg3);
            }

            arg2.b(v3);
        }

        public Object read(a arg1) {
            return this.a(arg1);
        }

        public void write(c arg1, Object arg2) {
            this.a(arg1, ((Enum)arg2));
        }
    }

    public static final v A;
    public static final v B;
    public static final v C;
    public static final w D;
    public static final v E;
    public static final w F;
    public static final v G;
    public static final w H;
    public static final v I;
    public static final w J;
    public static final v K;
    public static final w L;
    public static final v M;
    public static final w N;
    public static final v O;
    public static final w P;
    public static final v Q;
    public static final w R;
    public static final w S;
    public static final v T;
    public static final w U;
    public static final v V;
    public static final w W;
    public static final v X;
    public static final w Y;
    public static final w Z;
    public static final v a;
    public static final w b;
    public static final v c;
    public static final w d;
    public static final v e;
    public static final v f;
    public static final w g;
    public static final v h;
    public static final w i;
    public static final v j;
    public static final w k;
    public static final v l;
    public static final w m;
    public static final v n;
    public static final w o;
    public static final v p;
    public static final w q;
    public static final v r;
    public static final w s;
    public static final v t;
    public static final v u;
    public static final v v;
    public static final v w;
    public static final w x;
    public static final v y;
    public static final w z;

    static {
        n.a = new com.google.a.b.a.n$1().nullSafe();
        n.b = n.a(Class.class, n.a);
        n.c = new com.google.a.b.a.n$12().nullSafe();
        n.d = n.a(BitSet.class, n.c);
        n.e = new com.google.a.b.a.n$23();
        n.f = new com.google.a.b.a.n$30();
        n.g = n.a(Boolean.TYPE, Boolean.class, n.e);
        n.h = new com.google.a.b.a.n$31();
        n.i = n.a(Byte.TYPE, Byte.class, n.h);
        n.j = new com.google.a.b.a.n$32();
        n.k = n.a(Short.TYPE, Short.class, n.j);
        n.l = new com.google.a.b.a.n$33();
        n.m = n.a(Integer.TYPE, Integer.class, n.l);
        n.n = new com.google.a.b.a.n$34().nullSafe();
        n.o = n.a(AtomicInteger.class, n.n);
        n.p = new com.google.a.b.a.n$35().nullSafe();
        n.q = n.a(AtomicBoolean.class, n.p);
        n.r = new com.google.a.b.a.n$2().nullSafe();
        n.s = n.a(AtomicIntegerArray.class, n.r);
        n.t = new com.google.a.b.a.n$3();
        n.u = new com.google.a.b.a.n$4();
        n.v = new com.google.a.b.a.n$5();
        n.w = new com.google.a.b.a.n$6();
        n.x = n.a(Number.class, n.w);
        n.y = new com.google.a.b.a.n$7();
        n.z = n.a(Character.TYPE, Character.class, n.y);
        n.A = new com.google.a.b.a.n$8();
        n.B = new com.google.a.b.a.n$9();
        n.C = new com.google.a.b.a.n$10();
        n.D = n.a(String.class, n.A);
        n.E = new com.google.a.b.a.n$11();
        n.F = n.a(StringBuilder.class, n.E);
        n.G = new com.google.a.b.a.n$13();
        n.H = n.a(StringBuffer.class, n.G);
        n.I = new com.google.a.b.a.n$14();
        n.J = n.a(URL.class, n.I);
        n.K = new com.google.a.b.a.n$15();
        n.L = n.a(URI.class, n.K);
        n.M = new com.google.a.b.a.n$16();
        n.N = n.b(InetAddress.class, n.M);
        n.O = new com.google.a.b.a.n$17();
        n.P = n.a(UUID.class, n.O);
        n.Q = new com.google.a.b.a.n$18().nullSafe();
        n.R = n.a(Currency.class, n.Q);
        n.S = new com.google.a.b.a.n$19();
        n.T = new com.google.a.b.a.n$20();
        n.U = n.b(Calendar.class, GregorianCalendar.class, n.T);
        n.V = new com.google.a.b.a.n$21();
        n.W = n.a(Locale.class, n.V);
        n.X = new com.google.a.b.a.n$22();
        n.Y = n.b(l.class, n.X);
        n.Z = new com.google.a.b.a.n$24();
    }

    public static w a(Class arg1, v arg2) {
        return new w(arg1, arg2) {
            public v create(f arg1, com.google.a.c.a arg2) {
                v v1 = arg2.a() == this.a ? this.b : null;
                return v1;
            }

            public String toString() {
                return "Factory[type=" + this.a.getName() + ",adapter=" + this.b + "]";
            }
        };
    }

    public static w a(Class arg1, Class arg2, v arg3) {
        return new w(arg1, arg2, arg3) {
            public v create(f arg1, com.google.a.c.a arg2) {
                Class v1 = arg2.a();
                v v1_1 = v1 == this.a || v1 == this.b ? this.c : null;
                return v1_1;
            }

            public String toString() {
                return "Factory[type=" + this.b.getName() + "+" + this.a.getName() + ",adapter=" + this.c + "]";
            }
        };
    }

    public static w b(Class arg1, v arg2) {
        return new w(arg1, arg2) {
            public v create(f arg1, com.google.a.c.a arg2) {
                Class v1 = arg2.a();
                if(!this.a.isAssignableFrom(v1)) {
                    return null;
                }

                return new v(v1) {
                    public Object read(a arg4) {
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
                                throw new t(v1.toString());
                            }
                        }

                        return v4;
                    }

                    public void write(c arg2, Object arg3) {
                        this.b.b.write(arg2, arg3);
                    }
                };
            }

            public String toString() {
                return "Factory[typeHierarchy=" + this.a.getName() + ",adapter=" + this.b + "]";
            }
        };
    }

    public static w b(Class arg1, Class arg2, v arg3) {
        return new w(arg1, arg2, arg3) {
            public v create(f arg1, com.google.a.c.a arg2) {
                Class v1 = arg2.a();
                v v1_1 = v1 == this.a || v1 == this.b ? this.c : null;
                return v1_1;
            }

            public String toString() {
                return "Factory[type=" + this.a.getName() + "+" + this.b.getName() + ",adapter=" + this.c + "]";
            }
        };
    }
}


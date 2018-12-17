package com.persianswitch.sdk.payment.model;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class PaymentProfile implements Parcelable {
    final class com.persianswitch.sdk.payment.model.PaymentProfile$1 implements Parcelable$Creator {
        com.persianswitch.sdk.payment.model.PaymentProfile$1() {
            super();
        }

        public PaymentProfile a(Parcel arg3) {
            return new PaymentProfile(arg3, null);
        }

        public PaymentProfile[] a(int arg1) {
            return new PaymentProfile[arg1];
        }

        public Object createFromParcel(Parcel arg1) {
            return this.a(arg1);
        }

        public Object[] newArray(int arg1) {
            return this.a(arg1);
        }
    }

    public static final Parcelable$Creator CREATOR;
    private int a;
    private TransactionStatus b;
    private long c;
    private UserCard d;
    private String e;
    private CVV2Status f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;
    private int s;
    private String t;
    private boolean u;

    static {
        PaymentProfile.CREATOR = new com.persianswitch.sdk.payment.model.PaymentProfile$1();
    }

    public PaymentProfile() {
        super();
        this.a = 1001;
        this.b = TransactionStatus.c;
        this.f = CVV2Status.a;
    }

    private PaymentProfile(Parcel arg3) {
        super();
        this.a = 1001;
        this.b = TransactionStatus.c;
        this.a = arg3.readInt();
        this.b = TransactionStatus.valueOf(arg3.readString());
        this.c = arg3.readLong();
        this.d = arg3.readParcelable(UserCard.class.getClassLoader());
        this.e = arg3.readString();
        CVV2Status v0 = arg3.readInt() == CVV2Status.a.a() ? CVV2Status.a : CVV2Status.b;
        this.f = v0;
        this.g = arg3.readString();
        this.h = arg3.readString();
        this.i = arg3.readString();
        this.j = arg3.readString();
        this.k = arg3.readString();
        this.l = arg3.readString();
        this.m = arg3.readString();
        this.n = arg3.readString();
        this.o = arg3.readString();
        this.p = arg3.readString();
        this.q = arg3.readString();
        this.r = arg3.readString();
        this.s = arg3.readInt();
        this.t = arg3.readString();
        boolean v3 = arg3.readInt() > 0 ? true : false;
        this.u = v3;
    }

    PaymentProfile(Parcel arg1, com.persianswitch.sdk.payment.model.PaymentProfile$1 arg2) {
        this(arg1);
    }

    public String a() {
        return this.o;
    }

    public void a(int arg1) {
        this.a = arg1;
    }

    public void a(long arg1) {
        this.c = arg1;
    }

    public void a(CVV2Status arg1) {
        this.f = arg1;
    }

    public void a(TransactionStatus arg1) {
        this.b = arg1;
    }

    public void a(UserCard arg1) {
        this.d = arg1;
    }

    public void a(String arg1) {
        this.o = arg1;
    }

    public void a(boolean arg1) {
        this.u = arg1;
    }

    public String b() {
        return this.p;
    }

    public void b(int arg1) {
        this.s = arg1;
    }

    public void b(String arg1) {
        this.p = arg1;
    }

    public int c() {
        return this.a;
    }

    public void c(String arg1) {
        this.e = arg1;
    }

    public CVV2Status d() {
        return this.f;
    }

    public void d(String arg1) {
        this.l = arg1;
    }

    public int describeContents() {
        return 0;
    }

    public String e() {
        return this.e;
    }

    public void e(String arg1) {
        this.m = arg1;
    }

    public UserCard f() {
        return this.d;
    }

    public void f(String arg1) {
        this.i = arg1;
    }

    public String g() {
        return this.l;
    }

    public void g(String arg1) {
        this.j = arg1;
    }

    public String h() {
        return this.m;
    }

    public void h(String arg1) {
        this.k = arg1;
    }

    public int i() {
        return this.s;
    }

    public void i(String arg1) {
        this.g = arg1;
    }

    public TransactionStatus j() {
        return this.b;
    }

    public void j(String arg1) {
        this.h = arg1;
    }

    public String k() {
        return this.i;
    }

    public void k(String arg1) {
        this.t = arg1;
    }

    public String l() {
        return this.j;
    }

    public void l(String arg1) {
        this.r = arg1;
    }

    public String m() {
        return this.k;
    }

    public void m(String arg1) {
        this.q = arg1;
    }

    public String n() {
        return this.g;
    }

    public String o() {
        return this.h;
    }

    public String p() {
        return this.t;
    }

    public String q() {
        return this.r;
    }

    public String r() {
        return this.q;
    }

    public boolean s() {
        boolean v0 = this.b != TransactionStatus.c || !this.u ? false : true;
        return v0;
    }

    public Long t() {
        return Long.valueOf(this.c);
    }

    public void writeToParcel(Parcel arg3, int arg4) {
        arg3.writeInt(this.a);
        arg3.writeString(this.b.toString());
        arg3.writeLong(this.c);
        arg3.writeParcelable(this.d, arg4);
        arg3.writeString(this.e);
        arg3.writeInt(this.f.a());
        arg3.writeString(this.g);
        arg3.writeString(this.h);
        arg3.writeString(this.i);
        arg3.writeString(this.j);
        arg3.writeString(this.k);
        arg3.writeString(this.l);
        arg3.writeString(this.m);
        arg3.writeString(this.n);
        arg3.writeString(this.o);
        arg3.writeString(this.p);
        arg3.writeString(this.q);
        arg3.writeString(this.r);
        arg3.writeInt(this.s);
        arg3.writeString(this.t);
        arg3.writeInt(this.u);
    }
}


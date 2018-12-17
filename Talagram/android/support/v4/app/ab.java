package android.support.v4.app;

import android.app.Person$Builder;
import android.app.Person;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.support.v4.graphics.drawable.IconCompat;

public class ab {
    public class a {
        CharSequence a;
        IconCompat b;
        String c;
        String d;
        boolean e;
        boolean f;

        public a() {
            super();
        }

        public a a(IconCompat arg1) {
            this.b = arg1;
            return this;
        }

        public a a(CharSequence arg1) {
            this.a = arg1;
            return this;
        }

        public ab a() {
            return new ab(this);
        }
    }

    CharSequence a;
    IconCompat b;
    String c;
    String d;
    boolean e;
    boolean f;

    ab(a arg2) {
        super();
        this.a = arg2.a;
        this.b = arg2.b;
        this.c = arg2.c;
        this.d = arg2.d;
        this.e = arg2.e;
        this.f = arg2.f;
    }

    public Bundle a() {
        Bundle v0 = new Bundle();
        v0.putCharSequence("name", this.a);
        String v1 = "icon";
        Bundle v2 = this.b != null ? this.b.d() : null;
        v0.putBundle(v1, v2);
        v0.putString("uri", this.c);
        v0.putString("key", this.d);
        v0.putBoolean("isBot", this.e);
        v0.putBoolean("isImportant", this.f);
        return v0;
    }

    public Person b() {
        Person$Builder v0 = new Person$Builder().setName(this.c());
        Icon v1 = this.d() != null ? this.d().c() : null;
        return v0.setIcon(v1).setUri(this.e()).setKey(this.f()).setBot(this.g()).setImportant(this.h()).build();
    }

    public CharSequence c() {
        return this.a;
    }

    public IconCompat d() {
        return this.b;
    }

    public String e() {
        return this.c;
    }

    public String f() {
        return this.d;
    }

    public boolean g() {
        return this.e;
    }

    public boolean h() {
        return this.f;
    }
}


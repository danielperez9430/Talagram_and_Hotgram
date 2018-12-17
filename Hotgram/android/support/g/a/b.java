package android.support.g.a;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Parcelable;
import android.support.v4.view.q;
import android.view.View;
import android.view.ViewGroup;

@Deprecated public abstract class b extends q {
    private final FragmentManager a;
    private FragmentTransaction b;
    private Fragment c;

    @Deprecated public b(FragmentManager arg2) {
        super();
        this.b = null;
        this.c = null;
        this.a = arg2;
    }

    private static String a(int arg2, long arg3) {
        return "android:switcher:" + arg2 + ":" + arg3;
    }

    @Deprecated public abstract Fragment a(int arg1);

    @Deprecated public long b(int arg3) {
        return ((long)arg3);
    }

    @Deprecated public void destroyItem(ViewGroup arg1, int arg2, Object arg3) {
        if(this.b == null) {
            this.b = this.a.beginTransaction();
        }

        this.b.detach(((Fragment)arg3));
    }

    @Deprecated public void finishUpdate(ViewGroup arg1) {
        if(this.b != null) {
            this.b.commitAllowingStateLoss();
            this.b = null;
            this.a.executePendingTransactions();
        }
    }

    @Deprecated public Object instantiateItem(ViewGroup arg5, int arg6) {
        if(this.b == null) {
            this.b = this.a.beginTransaction();
        }

        long v0 = this.b(arg6);
        Fragment v2 = this.a.findFragmentByTag(b.a(arg5.getId(), v0));
        if(v2 != null) {
            this.b.attach(v2);
        }
        else {
            v2 = this.a(arg6);
            this.b.add(arg5.getId(), v2, b.a(arg5.getId(), v0));
        }

        if(v2 != this.c) {
            v2.setMenuVisibility(false);
            a.a(v2, false);
        }

        return v2;
    }

    @Deprecated public boolean isViewFromObject(View arg1, Object arg2) {
        boolean v1 = ((Fragment)arg2).getView() == arg1 ? true : false;
        return v1;
    }

    @Deprecated public void restoreState(Parcelable arg1, ClassLoader arg2) {
    }

    @Deprecated public Parcelable saveState() {
        return null;
    }

    @Deprecated public void setPrimaryItem(ViewGroup arg1, int arg2, Object arg3) {
        if(arg3 != this.c) {
            if(this.c != null) {
                this.c.setMenuVisibility(false);
                a.a(this.c, false);
            }

            if(arg3 != null) {
                ((Fragment)arg3).setMenuVisibility(true);
                a.a(((Fragment)arg3), true);
            }

            this.c = ((Fragment)arg3);
        }
    }

    @Deprecated public void startUpdate(ViewGroup arg3) {
        if(arg3.getId() != -1) {
            return;
        }

        StringBuilder v0 = new StringBuilder();
        v0.append("ViewPager with adapter ");
        v0.append(this);
        v0.append(" requires a view id");
        throw new IllegalStateException(v0.toString());
    }
}


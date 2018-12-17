package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.v7.view.menu.ListMenuItemView;
import android.support.v7.view.menu.g;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.j;
import android.transition.Transition;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

public class as extends aq implements ar {
    public class a extends aj {
        final int b;
        final int c;
        private ar d;
        private MenuItem e;

        public a(Context arg4, boolean arg5) {
            super(arg4, arg5);
            Configuration v4 = arg4.getResources().getConfiguration();
            int v0 = 21;
            int v1 = 22;
            if(Build$VERSION.SDK_INT < 17 || 1 != v4.getLayoutDirection()) {
                this.b = v1;
                this.c = v0;
            }
            else {
                this.b = v0;
                this.c = v1;
            }
        }

        public int a(int arg1, int arg2, int arg3, int arg4, int arg5) {
            return super.a(arg1, arg2, arg3, arg4, arg5);
        }

        public boolean a(MotionEvent arg1, int arg2) {
            return super.a(arg1, arg2);
        }

        public boolean hasFocus() {
            return super.hasFocus();
        }

        public boolean hasWindowFocus() {
            return super.hasWindowFocus();
        }

        public boolean isFocused() {
            return super.isFocused();
        }

        public boolean isInTouchMode() {
            return super.isInTouchMode();
        }

        public boolean onHoverEvent(MotionEvent arg6) {
            int v1;
            if(this.d != null) {
                ListAdapter v0 = this.getAdapter();
                if((v0 instanceof HeaderViewListAdapter)) {
                    v1 = ((HeaderViewListAdapter)v0).getHeadersCount();
                    v0 = ((HeaderViewListAdapter)v0).getWrappedAdapter();
                }
                else {
                    v1 = 0;
                }

                j v2 = null;
                if(arg6.getAction() != 10) {
                    int v3 = this.pointToPosition(((int)arg6.getX()), ((int)arg6.getY()));
                    if(v3 != -1) {
                        v3 -= v1;
                        if(v3 >= 0 && v3 < ((g)v0).getCount()) {
                            v2 = ((g)v0).a(v3);
                        }
                    }
                }

                MenuItem v1_1 = this.e;
                if((((j)v1_1)) == v2) {
                    goto label_36;
                }

                h v0_1 = ((g)v0).a();
                if(v1_1 != null) {
                    this.d.a(v0_1, v1_1);
                }

                this.e = ((MenuItem)v2);
                if(v2 == null) {
                    goto label_36;
                }

                this.d.b(v0_1, ((MenuItem)v2));
            }

        label_36:
            return super.onHoverEvent(arg6);
        }

        public boolean onKeyDown(int arg5, KeyEvent arg6) {
            View v0 = this.getSelectedView();
            if(v0 != null && arg5 == this.b) {
                if((((ListMenuItemView)v0).isEnabled()) && (((ListMenuItemView)v0).getItemData().hasSubMenu())) {
                    this.performItemClick(v0, this.getSelectedItemPosition(), this.getSelectedItemId());
                }

                return 1;
            }

            if(v0 != null && arg5 == this.c) {
                this.setSelection(-1);
                this.getAdapter().a().a(false);
                return 1;
            }

            return super.onKeyDown(arg5, arg6);
        }

        public boolean onTouchEvent(MotionEvent arg1) {
            return super.onTouchEvent(arg1);
        }

        public void setHoverListener(ar arg1) {
            this.d = arg1;
        }

        public void setSelector(Drawable arg1) {
            super.setSelector(arg1);
        }
    }

    private static Method a;
    private ar b;

    static {
        try {
            as.a = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
        }
        catch(NoSuchMethodException ) {
            Log.i("MenuPopupWindow", "Could not find method setTouchModal() on PopupWindow. Oh well.");
        }
    }

    public as(Context arg1, AttributeSet arg2, int arg3, int arg4) {
        super(arg1, arg2, arg3, arg4);
    }

    public void a(Object arg3) {
        if(Build$VERSION.SDK_INT >= 23) {
            this.g.setEnterTransition(((Transition)arg3));
        }
    }

    public void a(ar arg1) {
        this.b = arg1;
    }

    aj a(Context arg2, boolean arg3) {
        a v0 = new a(arg2, arg3);
        v0.setHoverListener(((ar)this));
        return ((aj)v0);
    }

    public void a(h arg2, MenuItem arg3) {
        if(this.b != null) {
            this.b.a(arg2, arg3);
        }
    }

    public void b(Object arg3) {
        if(Build$VERSION.SDK_INT >= 23) {
            this.g.setExitTransition(((Transition)arg3));
        }
    }

    public void b(h arg2, MenuItem arg3) {
        if(this.b != null) {
            this.b.b(arg2, arg3);
        }
    }

    public void c(boolean arg5) {
        if(as.a != null) {
            try {
                as.a.invoke(this.g, Boolean.valueOf(arg5));
            }
            catch(Exception ) {
                Log.i("MenuPopupWindow", "Could not invoke setTouchModal() on PopupWindow. Oh well.");
            }
        }
    }
}


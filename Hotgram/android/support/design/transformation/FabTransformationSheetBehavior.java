package android.support.design.transformation;

import android.content.Context;
import android.os.Build$VERSION;
import android.support.design.a.h;
import android.support.design.a.j;
import android.support.design.widget.CoordinatorLayout$e;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.t;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import java.util.HashMap;
import java.util.Map;

public class FabTransformationSheetBehavior extends FabTransformationBehavior {
    private Map a;

    public FabTransformationSheetBehavior() {
        super();
    }

    public FabTransformationSheetBehavior(Context arg1, AttributeSet arg2) {
        super(arg1, arg2);
    }

    private void a(View arg9, boolean arg10) {
        ViewParent v0 = arg9.getParent();
        if(!(v0 instanceof CoordinatorLayout)) {
            return;
        }

        int v1 = ((CoordinatorLayout)v0).getChildCount();
        int v3 = 16;
        if(Build$VERSION.SDK_INT >= v3 && (arg10)) {
            this.a = new HashMap(v1);
        }

        int v4;
        for(v4 = 0; v4 < v1; ++v4) {
            View v5 = ((CoordinatorLayout)v0).getChildAt(v4);
            int v6 = !(v5.getLayoutParams() instanceof e) || !(v5.getLayoutParams().b() instanceof FabTransformationScrimBehavior) ? 0 : 1;
            if(v5 != arg9) {
                if(v6 != 0) {
                }
                else {
                    if(arg10) {
                        if(Build$VERSION.SDK_INT >= v3) {
                            this.a.put(v5, Integer.valueOf(v5.getImportantForAccessibility()));
                        }

                        v6 = 4;
                    }
                    else if(this.a == null) {
                        goto label_47;
                    }
                    else if(this.a.containsKey(v5)) {
                        v6 = this.a.get(v5).intValue();
                    }
                    else {
                        goto label_47;
                    }

                    t.b(v5, v6);
                }
            }

        label_47:
        }

        if(!arg10) {
            this.a = null;
        }
    }

    protected a a(Context arg3, boolean arg4) {
        int v4 = arg4 ? android.support.design.a$a.mtrl_fab_transformation_sheet_expand_spec : android.support.design.a$a.mtrl_fab_transformation_sheet_collapse_spec;
        a v0 = new a();
        v0.a = h.a(arg3, v4);
        v0.b = new j(17, 0f, 0f);
        return v0;
    }

    protected boolean a(View arg1, View arg2, boolean arg3, boolean arg4) {
        this.a(arg2, arg3);
        return super.a(arg1, arg2, arg3, arg4);
    }
}


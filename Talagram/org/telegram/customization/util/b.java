package org.telegram.customization.util;

import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.os.Build$VERSION;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.d.a.b.c;
import com.d.a.b.d;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.telegram.messenger.ApplicationLoader;

public class b {
    class a implements com.d.a.b.f.a, com.d.a.b.f.b {
        ProgressBar a;
        static final List b;

        static {
            a.b = Collections.synchronizedList(new LinkedList());
        }

        public a(ProgressBar arg1) {
            super();
            this.a = arg1;
        }

        public void a(String arg1, View arg2, int arg3, int arg4) {
            if(this.a != null) {
                this.a.setProgress(arg3);
            }
        }

        public void onLoadingCancelled(String arg1, View arg2) {
        }

        public void onLoadingComplete(String arg3, View arg4, Bitmap arg5) {
            if(arg5 != null) {
                if((a.b.contains(arg3) ^ 1) != 0) {
                    com.d.a.b.c.b.a(arg4, 500);
                    a.b.add(arg3);
                }

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        if(this.a.a != null) {
                            this.a.a.setVisibility(8);
                        }
                    }
                }, 2000);
            }
        }

        public void onLoadingFailed(String arg1, View arg2, com.d.a.b.a.b arg3) {
            if(arg3 != null) {
                try {
                    if(arg3.a() != null && !TextUtils.isEmpty(arg3.a().getMessage())) {
                        arg3.a().printStackTrace();
                    }

                label_12:
                    ((ImageView)arg2).setImageResource(2131231490);
                    return;
                label_11:
                    goto label_15;
                }
                catch(Exception v1) {
                    goto label_11;
                }
            }

            goto label_12;
        label_15:
            v1.printStackTrace();
        }

        public void onLoadingStarted(String arg2, View arg3) {
            ((ImageView)arg3).setImageResource(2131231489);
            if(this.a != null) {
                this.a.setVisibility(0);
                this.a.startAnimation(AnimationUtils.loadAnimation(ApplicationLoader.applicationContext, 2130771996));
            }
        }
    }

    private static c a;

    public static c a() {
        if(b.a == null) {
            b.a = new com.d.a.b.c$a().a(2131231489).b(2131231490).c(2131231490).a(false).b(true).c(true).a(Bitmap$Config.RGB_565).a();
        }

        return b.a;
    }

    public static void a(ImageView arg4, String arg5) {
        if(arg5 == null || arg5.length() <= 0) {
            arg4.setImageResource(2131231490);
        }
        else {
            d.a().a(arg5, arg4, b.a(), new a(null));
        }
    }

    public static void a(ImageView arg7, String arg8, CircularProgressBar arg9) {
        if(!TextUtils.isEmpty(((CharSequence)arg8))) {
            d v1 = d.a();
            Animation v0 = AnimationUtils.loadAnimation(ApplicationLoader.applicationContext, 2130771996);
            v0.setRepeatCount(-1);
            v1.a(arg8, arg7, b.a(), new com.d.a.b.f.a(arg9, v0) {
                public void onLoadingCancelled(String arg1, View arg2) {
                    this.a.setVisibility(8);
                    this.a.clearAnimation();
                }

                public void onLoadingComplete(String arg1, View arg2, Bitmap arg3) {
                    this.a.clearAnimation();
                    this.a.setVisibility(8);
                }

                public void onLoadingFailed(String arg1, View arg2, com.d.a.b.a.b arg3) {
                    this.a.clearAnimation();
                    this.a.setVisibility(8);
                }

                public void onLoadingStarted(String arg1, View arg2) {
                    this.a.setVisibility(0);
                    this.a.startAnimation(this.b);
                }
            }, new com.d.a.b.f.b(arg9) {
                public void a(String arg1, View arg2, int arg3, int arg4) {
                    if(this.a != null) {
                        this.a.setProgress(((float)(arg3 * 100 / arg4)));
                    }
                }
            });
        }
        else {
            arg7.setImageResource(2131231491);
        }
    }

    public static void b(ImageView arg7, String arg8, CircularProgressBar arg9) {
        if(!TextUtils.isEmpty(((CharSequence)arg8))) {
            d v1 = d.a();
            Animation v0 = AnimationUtils.loadAnimation(ApplicationLoader.applicationContext, 2130771996);
            v0.setRepeatCount(-1);
            v1.a(arg8, arg7, b.a(), new com.d.a.b.f.a(arg9, v0) {
                public void onLoadingCancelled(String arg1, View arg2) {
                    this.a.setVisibility(8);
                    this.a.clearAnimation();
                }

                public void onLoadingComplete(String arg2, View arg3, Bitmap arg4) {
                    this.a.clearAnimation();
                    this.a.setVisibility(8);
                    if(Build$VERSION.SDK_INT >= 17) {
                        Bitmap v2 = org.telegram.customization.util.c.a(arg3.getContext(), arg4, 3);
                        if(v2 == null) {
                            goto label_15;
                        }
                        else {
                            ((ImageView)arg3).setImageBitmap(v2);
                        }
                    }
                    else {
                    label_15:
                        ((ImageView)arg3).setImageBitmap(arg4);
                    }
                }

                public void onLoadingFailed(String arg1, View arg2, com.d.a.b.a.b arg3) {
                    this.a.clearAnimation();
                    this.a.setVisibility(8);
                }

                public void onLoadingStarted(String arg1, View arg2) {
                    this.a.setVisibility(0);
                    this.a.startAnimation(this.b);
                }
            }, new com.d.a.b.f.b(arg9) {
                public void a(String arg1, View arg2, int arg3, int arg4) {
                    if(this.a != null) {
                        this.a.setProgress(((float)(arg3 * 100 / arg4)));
                    }
                }
            });
        }
        else {
            arg7.setImageResource(2131231491);
        }
    }
}


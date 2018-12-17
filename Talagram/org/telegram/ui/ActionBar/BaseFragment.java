package org.telegram.ui.ActionBar;

import android.animation.AnimatorSet;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface$OnDismissListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import org.telegram.customization.i.h;
import org.telegram.customization.i.i;
import org.telegram.customization.i.j;
import org.telegram.customization.util.a.a;
import org.telegram.customization.voip.SipWrapper;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.ConnectionsManager;

public class BaseFragment {
    protected ActionBar actionBar;
    public a adsPrefs;
    public j api;
    private i apiMapper;
    protected Bundle arguments;
    protected int classGuid;
    protected int currentAccount;
    private boolean finishing;
    protected View fragmentView;
    protected boolean hasOwnBackground;
    protected boolean inPreviewMode;
    private boolean isFinished;
    private boolean needsApi;
    protected ActionBarLayout parentLayout;
    public SipWrapper sipWrapper;
    protected boolean swipeBackEnabled;
    protected Dialog visibleDialog;

    public BaseFragment() {
        this(false);
    }

    public BaseFragment(boolean arg2) {
        super();
        this.currentAccount = UserConfig.selectedAccount;
        this.swipeBackEnabled = true;
        this.hasOwnBackground = false;
        this.classGuid = ConnectionsManager.generateClassGuid();
        this.needsApi = arg2;
    }

    public BaseFragment(Bundle arg2) {
        this(arg2, false);
    }

    public BaseFragment(Bundle arg2, boolean arg3) {
        super();
        this.currentAccount = UserConfig.selectedAccount;
        this.swipeBackEnabled = true;
        this.hasOwnBackground = false;
        this.arguments = arg2;
        this.classGuid = ConnectionsManager.generateClassGuid();
        this.needsApi = arg3;
    }

    public void attach() {
        ApplicationLoader.getComponent().a(this);
        this.detach();
        if(this.needsApi) {
            h v0 = this.getApiCallback();
            if(v0 != null) {
                this.apiMapper = new i(v0);
                this.api.a(this.apiMapper);
            }
        }
    }

    protected void clearViews() {
        ViewParent v0;
        View v1 = null;
        if(this.fragmentView != null) {
            v0 = this.fragmentView.getParent();
            if(v0 != null) {
                try {
                    this.onRemoveFromParent();
                    ((ViewGroup)v0).removeView(this.fragmentView);
                }
                catch(Exception v0_1) {
                    FileLog.e(((Throwable)v0_1));
                }
            }

            this.fragmentView = v1;
        }

        if(this.actionBar != null) {
            v0 = this.actionBar.getParent();
            if(v0 != null) {
                try {
                    ((ViewGroup)v0).removeView(this.actionBar);
                }
                catch(Exception v0_1) {
                    FileLog.e(((Throwable)v0_1));
                }
            }

            this.actionBar = ((ActionBar)v1);
        }

        this.parentLayout = ((ActionBarLayout)v1);
    }

    protected ActionBar createActionBar(Context arg4) {
        ActionBar v0 = new ActionBar(arg4);
        v0.setBackgroundColor(Theme.getColor("actionBarDefault"));
        v0.setItemsBackgroundColor(Theme.getColor("actionBarDefaultSelector"), false);
        v0.setItemsBackgroundColor(Theme.getColor("actionBarActionModeDefaultSelector"), true);
        v0.setItemsColor(Theme.getColor("actionBarDefaultIcon"), false);
        v0.setItemsColor(Theme.getColor("actionBarActionModeDefaultIcon"), true);
        if(this.inPreviewMode) {
            v0.setOccupyStatusBar(false);
        }

        return v0;
    }

    public View createView(Context arg1) {
        this.attach();
        return null;
    }

    public void detach() {
        if((this.needsApi) && this.apiMapper != null) {
            this.api.b(this.apiMapper);
        }
    }

    public void dismissCurrentDialig() {
        if(this.visibleDialog == null) {
            return;
        }

        try {
            this.visibleDialog.dismiss();
            this.visibleDialog = null;
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }
    }

    public boolean dismissDialogOnPause(Dialog arg1) {
        return 1;
    }

    public boolean extendActionMode(Menu arg1) {
        return 0;
    }

    public void finishFragment() {
        this.finishFragment(true);
    }

    public void finishFragment(boolean arg2) {
        if(!this.isFinished) {
            if(this.parentLayout == null) {
            }
            else {
                this.finishing = true;
                this.parentLayout.closeLastFragment(arg2);
            }
        }
    }

    public void finishPreviewFragment() {
        this.parentLayout.finishPreviewFragment();
    }

    public ActionBar getActionBar() {
        return this.actionBar;
    }

    protected h getApiCallback() {
        return null;
    }

    public Bundle getArguments() {
        return this.arguments;
    }

    public int getCurrentAccount() {
        return this.currentAccount;
    }

    public BaseFragment getFragmentForAlert(int arg3) {
        if(this.parentLayout != null) {
            if(this.parentLayout.fragmentsStack.size() <= arg3 + 1) {
            }
            else {
                return this.parentLayout.fragmentsStack.get(this.parentLayout.fragmentsStack.size() - 2 - arg3);
            }
        }

        return this;
    }

    public View getFragmentView() {
        return this.fragmentView;
    }

    public Activity getParentActivity() {
        if(this.parentLayout != null) {
            return this.parentLayout.parentActivity;
        }

        return null;
    }

    public ThemeDescription[] getThemeDescriptions() {
        return new ThemeDescription[0];
    }

    public Dialog getVisibleDialog() {
        return this.visibleDialog;
    }

    protected boolean isFinishing() {
        return this.finishing;
    }

    public static void lambda$showDialog$0(BaseFragment arg0, DialogInterface$OnDismissListener arg1, DialogInterface arg2) {
        if(arg1 != null) {
            arg1.onDismiss(arg2);
        }

        arg0.onDialogDismiss(arg0.visibleDialog);
        arg0.visibleDialog = null;
    }

    public void movePreviewFragment(float arg2) {
        this.parentLayout.movePreviewFragment(arg2);
    }

    public void needApi(boolean arg1) {
        this.needsApi = arg1;
    }

    public boolean needDelayOpenAnimation() {
        return 0;
    }

    public void onActivityResultFragment(int arg1, int arg2, Intent arg3) {
    }

    public boolean onBackPressed() {
        return 1;
    }

    protected void onBecomeFullyVisible() {
    }

    public void onBeginSlide() {
        try {
            if(this.visibleDialog == null) {
                goto label_12;
            }

            if(!this.visibleDialog.isShowing()) {
                goto label_12;
            }

            this.visibleDialog.dismiss();
            this.visibleDialog = null;
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }

    label_12:
        if(this.actionBar != null) {
            this.actionBar.onPause();
        }
    }

    public void onConfigurationChanged(Configuration arg1) {
    }

    protected AnimatorSet onCustomTransitionAnimation(boolean arg1, Runnable arg2) {
        return null;
    }

    protected void onDialogDismiss(Dialog arg1) {
    }

    public boolean onFragmentCreate() {
        this.attach();
        return 1;
    }

    public void onFragmentDestroy() {
        ConnectionsManager.getInstance(this.currentAccount).cancelRequestsForGuid(this.classGuid);
        this.isFinished = true;
        if(this.actionBar != null) {
            this.actionBar.setEnabled(false);
        }

        this.detach();
    }

    public void onLowMemory() {
    }

    public void onPause() {
        if(this.actionBar != null) {
            this.actionBar.onPause();
        }

        try {
            if(this.visibleDialog == null) {
                return;
            }

            if(!this.visibleDialog.isShowing()) {
                return;
            }

            if(!this.dismissDialogOnPause(this.visibleDialog)) {
                return;
            }

            this.visibleDialog.dismiss();
            this.visibleDialog = null;
        }
        catch(Exception v0) {
            FileLog.e(((Throwable)v0));
        }
    }

    protected void onRemoveFromParent() {
    }

    public void onRequestPermissionsResultFragment(int arg1, String[] arg2, int[] arg3) {
    }

    public void onResume() {
    }

    protected void onTransitionAnimationEnd(boolean arg1, boolean arg2) {
    }

    protected void onTransitionAnimationStart(boolean arg1, boolean arg2) {
    }

    public boolean presentFragment(BaseFragment arg2, boolean arg3) {
        boolean v2 = this.parentLayout == null || !this.parentLayout.presentFragment(arg2, arg3) ? false : true;
        return v2;
    }

    public boolean presentFragment(BaseFragment arg2) {
        boolean v2 = this.parentLayout == null || !this.parentLayout.presentFragment(arg2) ? false : true;
        return v2;
    }

    public boolean presentFragment(BaseFragment arg8, boolean arg9, boolean arg10) {
        boolean v8 = this.parentLayout == null || !this.parentLayout.presentFragment(arg8, arg9, arg10, true, false) ? false : true;
        return v8;
    }

    public boolean presentFragmentAsPreview(BaseFragment arg2) {
        boolean v2 = this.parentLayout == null || !this.parentLayout.presentFragmentAsPreview(arg2) ? false : true;
        return v2;
    }

    public void removeSelfFromStack() {
        if(!this.isFinished) {
            if(this.parentLayout == null) {
            }
            else {
                this.parentLayout.removeFragmentFromStack(this);
            }
        }
    }

    public void restoreSelfArgs(Bundle arg1) {
    }

    public void saveSelfArgs(Bundle arg1) {
    }

    public void setCurrentAccount(int arg2) {
        if(this.fragmentView == null) {
            this.currentAccount = arg2;
            return;
        }

        throw new IllegalStateException("trying to set current account when fragment UI already created");
    }

    protected void setInPreviewMode(boolean arg4) {
        ActionBar v4;
        this.inPreviewMode = arg4;
        if(this.actionBar != null) {
            boolean v0 = false;
            if(this.inPreviewMode) {
                v4 = this.actionBar;
            }
            else {
                v4 = this.actionBar;
                if(Build$VERSION.SDK_INT >= 21) {
                    v0 = true;
                }
            }

            v4.setOccupyStatusBar(v0);
        }
    }

    public void setParentLayout(ActionBarLayout arg4) {
        if(this.parentLayout != arg4) {
            this.parentLayout = arg4;
            View v0 = null;
            if(this.fragmentView != null) {
                ViewParent v4 = this.fragmentView.getParent();
                if(v4 != null) {
                    try {
                        this.onRemoveFromParent();
                        ((ViewGroup)v4).removeView(this.fragmentView);
                    }
                    catch(Exception v4_1) {
                        FileLog.e(((Throwable)v4_1));
                    }
                }

                if(this.parentLayout == null) {
                    goto label_23;
                }

                if(this.parentLayout.getContext() == this.fragmentView.getContext()) {
                    goto label_23;
                }

                this.fragmentView = v0;
            }

        label_23:
            if(this.actionBar != null) {
                int v4_2 = this.parentLayout == null || this.parentLayout.getContext() == this.actionBar.getContext() ? 0 : 1;
                if((this.actionBar.getAddToContainer()) || v4_2 != 0) {
                    ViewParent v1 = this.actionBar.getParent();
                    if(v1 != null) {
                        try {
                            ((ViewGroup)v1).removeView(this.actionBar);
                        }
                        catch(Exception v1_1) {
                            FileLog.e(((Throwable)v1_1));
                        }
                    }
                }

                if(v4_2 == 0) {
                    goto label_49;
                }

                this.actionBar = ((ActionBar)v0);
            }

        label_49:
            if(this.parentLayout == null) {
                return;
            }

            if(this.actionBar != null) {
                return;
            }

            this.actionBar = this.createActionBar(this.parentLayout.getContext());
            this.actionBar.parentFragment = this;
        }
    }

    public void setVisibleDialog(Dialog arg1) {
        this.visibleDialog = arg1;
    }

    public Dialog showDialog(Dialog arg3) {
        return this.showDialog(arg3, false, null);
    }

    public Dialog showDialog(Dialog arg3, boolean arg4, DialogInterface$OnDismissListener arg5) {
        Dialog v0 = null;
        if(arg3 != null && this.parentLayout != null && !this.parentLayout.animationInProgress && !this.parentLayout.startedTracking && ((arg4) || !this.parentLayout.checkTransitionAnimation())) {
            try {
                if(this.visibleDialog == null) {
                    goto label_23;
                }

                this.visibleDialog.dismiss();
                this.visibleDialog = v0;
            }
            catch(Exception v4) {
                FileLog.e(((Throwable)v4));
            }

            try {
            label_23:
                this.visibleDialog = arg3;
                this.visibleDialog.setCanceledOnTouchOutside(true);
                this.visibleDialog.setOnDismissListener(new -$$Lambda$BaseFragment$vXTvtAK8XZpLjv4Env96FSJndOM(this, arg5));
                this.visibleDialog.show();
                return this.visibleDialog;
            }
            catch(Exception v3) {
                FileLog.e(((Throwable)v3));
            }
        }

        return v0;
    }

    public Dialog showDialog(Dialog arg2, DialogInterface$OnDismissListener arg3) {
        return this.showDialog(arg2, false, arg3);
    }

    public void startActivityForResult(Intent arg2, int arg3) {
        if(this.parentLayout != null) {
            this.parentLayout.startActivityForResult(arg2, arg3);
        }
    }
}


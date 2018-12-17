package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public final class SupportFragmentWrapper extends Stub {
    private Fragment zzabq;

    private SupportFragmentWrapper(Fragment arg1) {
        super();
        this.zzabq = arg1;
    }

    public final IObjectWrapper getActivity() {
        return ObjectWrapper.wrap(this.zzabq.getActivity());
    }

    public final Bundle getArguments() {
        return this.zzabq.getArguments();
    }

    public final int getId() {
        return this.zzabq.getId();
    }

    public final IFragmentWrapper getParentFragment() {
        return SupportFragmentWrapper.wrap(this.zzabq.getParentFragment());
    }

    public final IObjectWrapper getResources() {
        return ObjectWrapper.wrap(this.zzabq.getResources());
    }

    public final boolean getRetainInstance() {
        return this.zzabq.getRetainInstance();
    }

    public final String getTag() {
        return this.zzabq.getTag();
    }

    public final IFragmentWrapper getTargetFragment() {
        return SupportFragmentWrapper.wrap(this.zzabq.getTargetFragment());
    }

    public final int getTargetRequestCode() {
        return this.zzabq.getTargetRequestCode();
    }

    public final boolean getUserVisibleHint() {
        return this.zzabq.getUserVisibleHint();
    }

    public final IObjectWrapper getView() {
        return ObjectWrapper.wrap(this.zzabq.getView());
    }

    public final boolean isAdded() {
        return this.zzabq.isAdded();
    }

    public final boolean isDetached() {
        return this.zzabq.isDetached();
    }

    public final boolean isHidden() {
        return this.zzabq.isHidden();
    }

    public final boolean isInLayout() {
        return this.zzabq.isInLayout();
    }

    public final boolean isRemoving() {
        return this.zzabq.isRemoving();
    }

    public final boolean isResumed() {
        return this.zzabq.isResumed();
    }

    public final boolean isVisible() {
        return this.zzabq.isVisible();
    }

    public final void registerForContextMenu(IObjectWrapper arg2) {
        this.zzabq.registerForContextMenu(ObjectWrapper.unwrap(arg2));
    }

    public final void setHasOptionsMenu(boolean arg2) {
        this.zzabq.setHasOptionsMenu(arg2);
    }

    public final void setMenuVisibility(boolean arg2) {
        this.zzabq.setMenuVisibility(arg2);
    }

    public final void setRetainInstance(boolean arg2) {
        this.zzabq.setRetainInstance(arg2);
    }

    public final void setUserVisibleHint(boolean arg2) {
        this.zzabq.setUserVisibleHint(arg2);
    }

    public final void startActivity(Intent arg2) {
        this.zzabq.startActivity(arg2);
    }

    public final void startActivityForResult(Intent arg2, int arg3) {
        this.zzabq.startActivityForResult(arg2, arg3);
    }

    public final void unregisterForContextMenu(IObjectWrapper arg2) {
        this.zzabq.unregisterForContextMenu(ObjectWrapper.unwrap(arg2));
    }

    public static SupportFragmentWrapper wrap(Fragment arg1) {
        if(arg1 != null) {
            return new SupportFragmentWrapper(arg1);
        }

        return null;
    }
}


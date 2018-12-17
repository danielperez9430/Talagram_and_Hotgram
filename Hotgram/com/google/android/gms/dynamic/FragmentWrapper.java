package com.google.android.gms.dynamic;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

@SuppressLint(value={"NewApi"}) public final class FragmentWrapper extends Stub {
    private Fragment zzabm;

    private FragmentWrapper(Fragment arg1) {
        super();
        this.zzabm = arg1;
    }

    public final IObjectWrapper getActivity() {
        return ObjectWrapper.wrap(this.zzabm.getActivity());
    }

    public final Bundle getArguments() {
        return this.zzabm.getArguments();
    }

    public final int getId() {
        return this.zzabm.getId();
    }

    public final IFragmentWrapper getParentFragment() {
        return FragmentWrapper.wrap(this.zzabm.getParentFragment());
    }

    public final IObjectWrapper getResources() {
        return ObjectWrapper.wrap(this.zzabm.getResources());
    }

    public final boolean getRetainInstance() {
        return this.zzabm.getRetainInstance();
    }

    public final String getTag() {
        return this.zzabm.getTag();
    }

    public final IFragmentWrapper getTargetFragment() {
        return FragmentWrapper.wrap(this.zzabm.getTargetFragment());
    }

    public final int getTargetRequestCode() {
        return this.zzabm.getTargetRequestCode();
    }

    public final boolean getUserVisibleHint() {
        return this.zzabm.getUserVisibleHint();
    }

    public final IObjectWrapper getView() {
        return ObjectWrapper.wrap(this.zzabm.getView());
    }

    public final boolean isAdded() {
        return this.zzabm.isAdded();
    }

    public final boolean isDetached() {
        return this.zzabm.isDetached();
    }

    public final boolean isHidden() {
        return this.zzabm.isHidden();
    }

    public final boolean isInLayout() {
        return this.zzabm.isInLayout();
    }

    public final boolean isRemoving() {
        return this.zzabm.isRemoving();
    }

    public final boolean isResumed() {
        return this.zzabm.isResumed();
    }

    public final boolean isVisible() {
        return this.zzabm.isVisible();
    }

    public final void registerForContextMenu(IObjectWrapper arg2) {
        this.zzabm.registerForContextMenu(ObjectWrapper.unwrap(arg2));
    }

    public final void setHasOptionsMenu(boolean arg2) {
        this.zzabm.setHasOptionsMenu(arg2);
    }

    public final void setMenuVisibility(boolean arg2) {
        this.zzabm.setMenuVisibility(arg2);
    }

    public final void setRetainInstance(boolean arg2) {
        this.zzabm.setRetainInstance(arg2);
    }

    public final void setUserVisibleHint(boolean arg2) {
        this.zzabm.setUserVisibleHint(arg2);
    }

    public final void startActivity(Intent arg2) {
        this.zzabm.startActivity(arg2);
    }

    public final void startActivityForResult(Intent arg2, int arg3) {
        this.zzabm.startActivityForResult(arg2, arg3);
    }

    public final void unregisterForContextMenu(IObjectWrapper arg2) {
        this.zzabm.unregisterForContextMenu(ObjectWrapper.unwrap(arg2));
    }

    public static FragmentWrapper wrap(Fragment arg1) {
        if(arg1 != null) {
            return new FragmentWrapper(arg1);
        }

        return null;
    }
}


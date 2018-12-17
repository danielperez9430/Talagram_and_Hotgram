package android.support.v4.app;

import android.animation.Animator;
import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.d;
import android.arch.lifecycle.g;
import android.arch.lifecycle.h;
import android.arch.lifecycle.u;
import android.arch.lifecycle.v;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable$ClassLoaderCreator;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.support.v4.view.f;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu$ContextMenuInfo;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View$OnCreateContextMenuListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

public class Fragment implements g, v, ComponentCallbacks, View$OnCreateContextMenuListener {
    public class SavedState implements Parcelable {
        final class android.support.v4.app.Fragment$SavedState$1 implements Parcelable$ClassLoaderCreator {
            android.support.v4.app.Fragment$SavedState$1() {
                super();
            }

            public SavedState a(Parcel arg3) {
                return new SavedState(arg3, null);
            }

            public SavedState a(Parcel arg2, ClassLoader arg3) {
                return new SavedState(arg2, arg3);
            }

            public SavedState[] a(int arg1) {
                return new SavedState[arg1];
            }

            public Object createFromParcel(Parcel arg1) {
                return this.a(arg1);
            }

            public Object createFromParcel(Parcel arg1, ClassLoader arg2) {
                return this.a(arg1, arg2);
            }

            public Object[] newArray(int arg1) {
                return this.a(arg1);
            }
        }

        public static final Parcelable$Creator CREATOR;
        final Bundle a;

        static {
            SavedState.CREATOR = new android.support.v4.app.Fragment$SavedState$1();
        }

        SavedState(Parcel arg1, ClassLoader arg2) {
            super();
            this.a = arg1.readBundle();
            if(arg2 != null && this.a != null) {
                this.a.setClassLoader(arg2);
            }
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel arg1, int arg2) {
            arg1.writeBundle(this.a);
        }
    }

    class a {
        View a;
        Animator b;
        int c;
        int d;
        int e;
        int f;
        Object g;
        Object h;
        Object i;
        Object j;
        Object k;
        Object l;
        Boolean m;
        Boolean n;
        ae o;
        ae p;
        boolean q;
        c r;
        boolean s;

        a() {
            super();
            this.g = null;
            this.h = Fragment.USE_DEFAULT_TRANSITION;
            this.i = null;
            this.j = Fragment.USE_DEFAULT_TRANSITION;
            this.k = null;
            this.l = Fragment.USE_DEFAULT_TRANSITION;
            this.o = null;
            this.p = null;
        }
    }

    public class b extends RuntimeException {
        public b(String arg1, Exception arg2) {
            super(arg1, ((Throwable)arg2));
        }
    }

    interface c {
        void a();

        void b();
    }

    static final int ACTIVITY_CREATED = 2;
    static final int CREATED = 1;
    static final int INITIALIZING = 0;
    static final int RESUMED = 4;
    static final int STARTED = 3;
    static final Object USE_DEFAULT_TRANSITION;
    boolean mAdded;
    a mAnimationInfo;
    Bundle mArguments;
    int mBackStackNesting;
    boolean mCalled;
    m mChildFragmentManager;
    n mChildNonConfig;
    ViewGroup mContainer;
    int mContainerId;
    boolean mDeferStart;
    boolean mDetached;
    int mFragmentId;
    m mFragmentManager;
    boolean mFromLayout;
    boolean mHasMenu;
    boolean mHidden;
    boolean mHiddenChanged;
    k mHost;
    boolean mInLayout;
    int mIndex;
    View mInnerView;
    boolean mIsCreated;
    boolean mIsNewlyAdded;
    LayoutInflater mLayoutInflater;
    h mLifecycleRegistry;
    boolean mMenuVisible;
    Fragment mParentFragment;
    boolean mPerformedCreateView;
    float mPostponedAlpha;
    boolean mRemoving;
    boolean mRestored;
    boolean mRetainInstance;
    boolean mRetaining;
    Bundle mSavedFragmentState;
    Boolean mSavedUserVisibleHint;
    SparseArray mSavedViewState;
    int mState;
    String mTag;
    Fragment mTarget;
    int mTargetIndex;
    int mTargetRequestCode;
    boolean mUserVisibleHint;
    View mView;
    g mViewLifecycleOwner;
    android.arch.lifecycle.m mViewLifecycleOwnerLiveData;
    h mViewLifecycleRegistry;
    u mViewModelStore;
    String mWho;
    private static final android.support.v4.f.m sClassMap;

    static {
        Fragment.sClassMap = new android.support.v4.f.m();
        Fragment.USE_DEFAULT_TRANSITION = new Object();
    }

    public Fragment() {
        super();
        this.mState = 0;
        this.mIndex = -1;
        this.mTargetIndex = -1;
        this.mMenuVisible = true;
        this.mUserVisibleHint = true;
        this.mLifecycleRegistry = new h(((g)this));
        this.mViewLifecycleOwnerLiveData = new android.arch.lifecycle.m();
    }

    void callStartTransitionListener() {
        c v0;
        c v1 = null;
        if(this.mAnimationInfo == null) {
            v0 = v1;
        }
        else {
            this.mAnimationInfo.q = false;
            v0 = this.mAnimationInfo.r;
            this.mAnimationInfo.r = v1;
        }

        if(v0 != null) {
            v0.a();
        }
    }

    public void dump(String arg3, FileDescriptor arg4, PrintWriter arg5, String[] arg6) {
        arg5.print(arg3);
        arg5.print("mFragmentId=#");
        arg5.print(Integer.toHexString(this.mFragmentId));
        arg5.print(" mContainerId=#");
        arg5.print(Integer.toHexString(this.mContainerId));
        arg5.print(" mTag=");
        arg5.println(this.mTag);
        arg5.print(arg3);
        arg5.print("mState=");
        arg5.print(this.mState);
        arg5.print(" mIndex=");
        arg5.print(this.mIndex);
        arg5.print(" mWho=");
        arg5.print(this.mWho);
        arg5.print(" mBackStackNesting=");
        arg5.println(this.mBackStackNesting);
        arg5.print(arg3);
        arg5.print("mAdded=");
        arg5.print(this.mAdded);
        arg5.print(" mRemoving=");
        arg5.print(this.mRemoving);
        arg5.print(" mFromLayout=");
        arg5.print(this.mFromLayout);
        arg5.print(" mInLayout=");
        arg5.println(this.mInLayout);
        arg5.print(arg3);
        arg5.print("mHidden=");
        arg5.print(this.mHidden);
        arg5.print(" mDetached=");
        arg5.print(this.mDetached);
        arg5.print(" mMenuVisible=");
        arg5.print(this.mMenuVisible);
        arg5.print(" mHasMenu=");
        arg5.println(this.mHasMenu);
        arg5.print(arg3);
        arg5.print("mRetainInstance=");
        arg5.print(this.mRetainInstance);
        arg5.print(" mRetaining=");
        arg5.print(this.mRetaining);
        arg5.print(" mUserVisibleHint=");
        arg5.println(this.mUserVisibleHint);
        if(this.mFragmentManager != null) {
            arg5.print(arg3);
            arg5.print("mFragmentManager=");
            arg5.println(this.mFragmentManager);
        }

        if(this.mHost != null) {
            arg5.print(arg3);
            arg5.print("mHost=");
            arg5.println(this.mHost);
        }

        if(this.mParentFragment != null) {
            arg5.print(arg3);
            arg5.print("mParentFragment=");
            arg5.println(this.mParentFragment);
        }

        if(this.mArguments != null) {
            arg5.print(arg3);
            arg5.print("mArguments=");
            arg5.println(this.mArguments);
        }

        if(this.mSavedFragmentState != null) {
            arg5.print(arg3);
            arg5.print("mSavedFragmentState=");
            arg5.println(this.mSavedFragmentState);
        }

        if(this.mSavedViewState != null) {
            arg5.print(arg3);
            arg5.print("mSavedViewState=");
            arg5.println(this.mSavedViewState);
        }

        if(this.mTarget != null) {
            arg5.print(arg3);
            arg5.print("mTarget=");
            arg5.print(this.mTarget);
            arg5.print(" mTargetRequestCode=");
            arg5.println(this.mTargetRequestCode);
        }

        if(this.getNextAnim() != 0) {
            arg5.print(arg3);
            arg5.print("mNextAnim=");
            arg5.println(this.getNextAnim());
        }

        if(this.mContainer != null) {
            arg5.print(arg3);
            arg5.print("mContainer=");
            arg5.println(this.mContainer);
        }

        if(this.mView != null) {
            arg5.print(arg3);
            arg5.print("mView=");
            arg5.println(this.mView);
        }

        if(this.mInnerView != null) {
            arg5.print(arg3);
            arg5.print("mInnerView=");
            arg5.println(this.mView);
        }

        if(this.getAnimatingAway() != null) {
            arg5.print(arg3);
            arg5.print("mAnimatingAway=");
            arg5.println(this.getAnimatingAway());
            arg5.print(arg3);
            arg5.print("mStateAfterAnimating=");
            arg5.println(this.getStateAfterAnimating());
        }

        if(this.getContext() != null) {
            t.a(((g)this)).a(arg3, arg4, arg5, arg6);
        }

        if(this.mChildFragmentManager != null) {
            arg5.print(arg3);
            arg5.println("Child " + this.mChildFragmentManager + ":");
            m v0_1 = this.mChildFragmentManager;
            v0_1.a(arg3 + "  ", arg4, arg5, arg6);
        }
    }

    private a ensureAnimationInfo() {
        if(this.mAnimationInfo == null) {
            this.mAnimationInfo = new a();
        }

        return this.mAnimationInfo;
    }

    public final boolean equals(Object arg1) {
        return super.equals(arg1);
    }

    Fragment findFragmentByWho(String arg2) {
        if(arg2.equals(this.mWho)) {
            return this;
        }

        if(this.mChildFragmentManager != null) {
            return this.mChildFragmentManager.b(arg2);
        }

        return null;
    }

    public final android.support.v4.app.h getActivity() {
        android.support.v4.app.h v0;
        if(this.mHost == null) {
            v0 = null;
        }
        else {
            Activity v0_1 = this.mHost.h();
        }

        return v0;
    }

    public boolean getAllowEnterTransitionOverlap() {
        boolean v0 = this.mAnimationInfo == null || this.mAnimationInfo.n == null ? true : this.mAnimationInfo.n.booleanValue();
        return v0;
    }

    public boolean getAllowReturnTransitionOverlap() {
        boolean v0 = this.mAnimationInfo == null || this.mAnimationInfo.m == null ? true : this.mAnimationInfo.m.booleanValue();
        return v0;
    }

    View getAnimatingAway() {
        if(this.mAnimationInfo == null) {
            return null;
        }

        return this.mAnimationInfo.a;
    }

    Animator getAnimator() {
        if(this.mAnimationInfo == null) {
            return null;
        }

        return this.mAnimationInfo.b;
    }

    public final Bundle getArguments() {
        return this.mArguments;
    }

    public final l getChildFragmentManager() {
        if(this.mChildFragmentManager == null) {
            this.instantiateChildFragmentManager();
            if(this.mState >= 4) {
                this.mChildFragmentManager.q();
            }
            else if(this.mState >= 3) {
                this.mChildFragmentManager.p();
            }
            else if(this.mState >= 2) {
                this.mChildFragmentManager.o();
            }
            else if(this.mState >= 1) {
                this.mChildFragmentManager.n();
            }
        }

        return this.mChildFragmentManager;
    }

    public Context getContext() {
        Context v0 = this.mHost == null ? null : this.mHost.i();
        return v0;
    }

    public Object getEnterTransition() {
        if(this.mAnimationInfo == null) {
            return null;
        }

        return this.mAnimationInfo.g;
    }

    ae getEnterTransitionCallback() {
        if(this.mAnimationInfo == null) {
            return null;
        }

        return this.mAnimationInfo.o;
    }

    public Object getExitTransition() {
        if(this.mAnimationInfo == null) {
            return null;
        }

        return this.mAnimationInfo.i;
    }

    ae getExitTransitionCallback() {
        if(this.mAnimationInfo == null) {
            return null;
        }

        return this.mAnimationInfo.p;
    }

    public final l getFragmentManager() {
        return this.mFragmentManager;
    }

    public final Object getHost() {
        Object v0 = this.mHost == null ? null : this.mHost.g();
        return v0;
    }

    public final int getId() {
        return this.mFragmentId;
    }

    public final LayoutInflater getLayoutInflater() {
        if(this.mLayoutInflater == null) {
            return this.performGetLayoutInflater(null);
        }

        return this.mLayoutInflater;
    }

    @Deprecated public LayoutInflater getLayoutInflater(Bundle arg2) {
        if(this.mHost != null) {
            LayoutInflater v2 = this.mHost.b();
            this.getChildFragmentManager();
            f.a(v2, this.mChildFragmentManager.x());
            return v2;
        }

        throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
    }

    public d getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Deprecated public t getLoaderManager() {
        return t.a(((g)this));
    }

    int getNextAnim() {
        if(this.mAnimationInfo == null) {
            return 0;
        }

        return this.mAnimationInfo.d;
    }

    int getNextTransition() {
        if(this.mAnimationInfo == null) {
            return 0;
        }

        return this.mAnimationInfo.e;
    }

    int getNextTransitionStyle() {
        if(this.mAnimationInfo == null) {
            return 0;
        }

        return this.mAnimationInfo.f;
    }

    public final Fragment getParentFragment() {
        return this.mParentFragment;
    }

    public Object getReenterTransition() {
        if(this.mAnimationInfo == null) {
            return null;
        }

        Object v0 = this.mAnimationInfo.j == Fragment.USE_DEFAULT_TRANSITION ? this.getExitTransition() : this.mAnimationInfo.j;
        return v0;
    }

    public final Resources getResources() {
        return this.requireContext().getResources();
    }

    public final boolean getRetainInstance() {
        return this.mRetainInstance;
    }

    public Object getReturnTransition() {
        if(this.mAnimationInfo == null) {
            return null;
        }

        Object v0 = this.mAnimationInfo.h == Fragment.USE_DEFAULT_TRANSITION ? this.getEnterTransition() : this.mAnimationInfo.h;
        return v0;
    }

    public Object getSharedElementEnterTransition() {
        if(this.mAnimationInfo == null) {
            return null;
        }

        return this.mAnimationInfo.k;
    }

    public Object getSharedElementReturnTransition() {
        if(this.mAnimationInfo == null) {
            return null;
        }

        Object v0 = this.mAnimationInfo.l == Fragment.USE_DEFAULT_TRANSITION ? this.getSharedElementEnterTransition() : this.mAnimationInfo.l;
        return v0;
    }

    int getStateAfterAnimating() {
        if(this.mAnimationInfo == null) {
            return 0;
        }

        return this.mAnimationInfo.c;
    }

    public final String getString(int arg2) {
        return this.getResources().getString(arg2);
    }

    public final String getString(int arg2, Object[] arg3) {
        return this.getResources().getString(arg2, arg3);
    }

    public final String getTag() {
        return this.mTag;
    }

    public final Fragment getTargetFragment() {
        return this.mTarget;
    }

    public final int getTargetRequestCode() {
        return this.mTargetRequestCode;
    }

    public final CharSequence getText(int arg2) {
        return this.getResources().getText(arg2);
    }

    public boolean getUserVisibleHint() {
        return this.mUserVisibleHint;
    }

    public View getView() {
        return this.mView;
    }

    public g getViewLifecycleOwner() {
        if(this.mViewLifecycleOwner != null) {
            return this.mViewLifecycleOwner;
        }

        throw new IllegalStateException("Can\'t access the Fragment View\'s LifecycleOwner when getView() is null i.e., before onCreateView() or after onDestroyView()");
    }

    public LiveData getViewLifecycleOwnerLiveData() {
        return this.mViewLifecycleOwnerLiveData;
    }

    public u getViewModelStore() {
        if(this.getContext() != null) {
            if(this.mViewModelStore == null) {
                this.mViewModelStore = new u();
            }

            return this.mViewModelStore;
        }

        throw new IllegalStateException("Can\'t access ViewModels from detached fragment");
    }

    public final boolean hasOptionsMenu() {
        return this.mHasMenu;
    }

    public final int hashCode() {
        return super.hashCode();
    }

    void initState() {
        this.mIndex = -1;
        this.mWho = null;
        this.mAdded = false;
        this.mRemoving = false;
        this.mFromLayout = false;
        this.mInLayout = false;
        this.mRestored = false;
        this.mBackStackNesting = 0;
        this.mFragmentManager = null;
        this.mChildFragmentManager = null;
        this.mHost = null;
        this.mFragmentId = 0;
        this.mContainerId = 0;
        this.mTag = null;
        this.mHidden = false;
        this.mDetached = false;
        this.mRetaining = false;
    }

    public static Fragment instantiate(Context arg2, String arg3, Bundle arg4) {
        StringBuilder v0_2;
        Object v2_5;
        try {
            Object v0 = Fragment.sClassMap.get(arg3);
            if(v0 == null) {
                Class v0_1 = arg2.getClassLoader().loadClass(arg3);
                Fragment.sClassMap.put(arg3, v0_1);
            }

            v2_5 = ((Class)v0).getConstructor().newInstance();
            if(arg4 != null) {
                arg4.setClassLoader(v2_5.getClass().getClassLoader());
                ((Fragment)v2_5).setArguments(arg4);
            }
        }
        catch(InvocationTargetException v2) {
            v0_2 = new StringBuilder();
            v0_2.append("Unable to instantiate fragment ");
            v0_2.append(arg3);
            v0_2.append(": calling Fragment constructor caused an exception");
            throw new b(v0_2.toString(), ((Exception)v2));
        }
        catch(NoSuchMethodException v2_1) {
            v0_2 = new StringBuilder();
            v0_2.append("Unable to instantiate fragment ");
            v0_2.append(arg3);
            v0_2.append(": could not find Fragment constructor");
            throw new b(v0_2.toString(), ((Exception)v2_1));
        }
        catch(IllegalAccessException v2_2) {
            v0_2 = new StringBuilder();
            v0_2.append("Unable to instantiate fragment ");
            v0_2.append(arg3);
            v0_2.append(": make sure class name exists, is public, and has an");
            v0_2.append(" empty constructor that is public");
            throw new b(v0_2.toString(), ((Exception)v2_2));
        }
        catch(InstantiationException v2_3) {
            v0_2 = new StringBuilder();
            v0_2.append("Unable to instantiate fragment ");
            v0_2.append(arg3);
            v0_2.append(": make sure class name exists, is public, and has an");
            v0_2.append(" empty constructor that is public");
            throw new b(v0_2.toString(), ((Exception)v2_3));
        }
        catch(ClassNotFoundException v2_4) {
            v0_2 = new StringBuilder();
            v0_2.append("Unable to instantiate fragment ");
            v0_2.append(arg3);
            v0_2.append(": make sure class name exists, is public, and has an");
            v0_2.append(" empty constructor that is public");
            throw new b(v0_2.toString(), ((Exception)v2_4));
        }

        return ((Fragment)v2_5);
    }

    public static Fragment instantiate(Context arg1, String arg2) {
        return Fragment.instantiate(arg1, arg2, null);
    }

    void instantiateChildFragmentManager() {
        if(this.mHost != null) {
            this.mChildFragmentManager = new m();
            this.mChildFragmentManager.a(this.mHost, new i() {
                public Fragment a(Context arg2, String arg3, Bundle arg4) {
                    return this.a.mHost.a(arg2, arg3, arg4);
                }

                public View a(int arg2) {
                    if(this.a.mView != null) {
                        return this.a.mView.findViewById(arg2);
                    }

                    throw new IllegalStateException("Fragment does not have a view");
                }

                public boolean a() {
                    boolean v0 = this.a.mView != null ? true : false;
                    return v0;
                }
            }, this);
            return;
        }

        throw new IllegalStateException("Fragment has not been attached yet.");
    }

    public final boolean isAdded() {
        boolean v0 = this.mHost == null || !this.mAdded ? false : true;
        return v0;
    }

    public final boolean isDetached() {
        return this.mDetached;
    }

    public final boolean isHidden() {
        return this.mHidden;
    }

    boolean isHideReplaced() {
        if(this.mAnimationInfo == null) {
            return 0;
        }

        return this.mAnimationInfo.s;
    }

    final boolean isInBackStack() {
        boolean v0 = this.mBackStackNesting > 0 ? true : false;
        return v0;
    }

    public final boolean isInLayout() {
        return this.mInLayout;
    }

    public final boolean isMenuVisible() {
        return this.mMenuVisible;
    }

    boolean isPostponed() {
        if(this.mAnimationInfo == null) {
            return 0;
        }

        return this.mAnimationInfo.q;
    }

    public final boolean isRemoving() {
        return this.mRemoving;
    }

    public final boolean isResumed() {
        boolean v0 = this.mState >= 4 ? true : false;
        return v0;
    }

    public final boolean isStateSaved() {
        if(this.mFragmentManager == null) {
            return 0;
        }

        return this.mFragmentManager.d();
    }

    static boolean isSupportFragmentClass(Context arg1, String arg2) {
        try {
            Object v0 = Fragment.sClassMap.get(arg2);
            if(v0 == null) {
                Class v0_1 = arg1.getClassLoader().loadClass(arg2);
                Fragment.sClassMap.put(arg2, v0_1);
            }

            return Fragment.class.isAssignableFrom(((Class)v0));
        }
        catch(ClassNotFoundException ) {
            return 0;
        }
    }

    public final boolean isVisible() {
        boolean v0 = !this.isAdded() || (this.isHidden()) || this.mView == null || this.mView.getWindowToken() == null || this.mView.getVisibility() != 0 ? false : true;
        return v0;
    }

    void noteStateNotSaved() {
        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.m();
        }
    }

    public void onActivityCreated(Bundle arg1) {
        this.mCalled = true;
    }

    public void onActivityResult(int arg1, int arg2, Intent arg3) {
    }

    @Deprecated public void onAttach(Activity arg1) {
        this.mCalled = true;
    }

    public void onAttach(Context arg2) {
        this.mCalled = true;
        Activity v2 = this.mHost == null ? null : this.mHost.h();
        if(v2 != null) {
            this.mCalled = false;
            this.onAttach(v2);
        }
    }

    public void onAttachFragment(Fragment arg1) {
    }

    public void onConfigurationChanged(Configuration arg1) {
        this.mCalled = true;
    }

    public boolean onContextItemSelected(MenuItem arg1) {
        return 0;
    }

    public void onCreate(Bundle arg2) {
        this.mCalled = true;
        this.restoreChildFragmentState(arg2);
        if(this.mChildFragmentManager != null && !this.mChildFragmentManager.a(1)) {
            this.mChildFragmentManager.n();
        }
    }

    public Animation onCreateAnimation(int arg1, boolean arg2, int arg3) {
        return null;
    }

    public Animator onCreateAnimator(int arg1, boolean arg2, int arg3) {
        return null;
    }

    public void onCreateContextMenu(ContextMenu arg2, View arg3, ContextMenu$ContextMenuInfo arg4) {
        this.getActivity().onCreateContextMenu(arg2, arg3, arg4);
    }

    public void onCreateOptionsMenu(Menu arg1, MenuInflater arg2) {
    }

    public View onCreateView(LayoutInflater arg1, ViewGroup arg2, Bundle arg3) {
        return null;
    }

    public void onDestroy() {
        int v0 = 1;
        this.mCalled = true;
        android.support.v4.app.h v1 = this.getActivity();
        if(v1 == null || !v1.isChangingConfigurations()) {
            v0 = 0;
        }
        else {
        }

        if(this.mViewModelStore != null && v0 == 0) {
            this.mViewModelStore.a();
        }
    }

    public void onDestroyOptionsMenu() {
    }

    public void onDestroyView() {
        this.mCalled = true;
        if(this.mView != null) {
            this.mViewLifecycleRegistry.a(android.arch.lifecycle.d$a.ON_DESTROY);
        }
    }

    public void onDetach() {
        this.mCalled = true;
    }

    public LayoutInflater onGetLayoutInflater(Bundle arg1) {
        return this.getLayoutInflater(arg1);
    }

    public void onHiddenChanged(boolean arg1) {
    }

    @Deprecated public void onInflate(Activity arg1, AttributeSet arg2, Bundle arg3) {
        this.mCalled = true;
    }

    public void onInflate(Context arg2, AttributeSet arg3, Bundle arg4) {
        this.mCalled = true;
        Activity v2 = this.mHost == null ? null : this.mHost.h();
        if(v2 != null) {
            this.mCalled = false;
            this.onInflate(v2, arg3, arg4);
        }
    }

    public void onLowMemory() {
        this.mCalled = true;
    }

    public void onMultiWindowModeChanged(boolean arg1) {
    }

    public boolean onOptionsItemSelected(MenuItem arg1) {
        return 0;
    }

    public void onOptionsMenuClosed(Menu arg1) {
    }

    public void onPause() {
        this.mCalled = true;
    }

    public void onPictureInPictureModeChanged(boolean arg1) {
    }

    public void onPrepareOptionsMenu(Menu arg1) {
    }

    public void onRequestPermissionsResult(int arg1, String[] arg2, int[] arg3) {
    }

    public void onResume() {
        this.mCalled = true;
    }

    public void onSaveInstanceState(Bundle arg1) {
    }

    public void onStart() {
        this.mCalled = true;
    }

    public void onStop() {
        this.mCalled = true;
    }

    public void onViewCreated(View arg1, Bundle arg2) {
    }

    public void onViewStateRestored(Bundle arg2) {
        this.mCalled = true;
        if(this.mView != null) {
            this.mViewLifecycleRegistry.a(android.arch.lifecycle.d$a.ON_CREATE);
        }
    }

    l peekChildFragmentManager() {
        return this.mChildFragmentManager;
    }

    void performActivityCreated(Bundle arg3) {
        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.m();
        }

        this.mState = 2;
        this.mCalled = false;
        this.onActivityCreated(arg3);
        if(this.mCalled) {
            if(this.mChildFragmentManager != null) {
                this.mChildFragmentManager.o();
            }

            return;
        }

        StringBuilder v0 = new StringBuilder();
        v0.append("Fragment ");
        v0.append(this);
        v0.append(" did not call through to super.onActivityCreated()");
        throw new af(v0.toString());
    }

    void performConfigurationChanged(Configuration arg2) {
        this.onConfigurationChanged(arg2);
        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.a(arg2);
        }
    }

    boolean performContextItemSelected(MenuItem arg3) {
        if(!this.mHidden) {
            if(this.onContextItemSelected(arg3)) {
                return 1;
            }
            else if(this.mChildFragmentManager != null && (this.mChildFragmentManager.b(arg3))) {
                return 1;
            }
        }

        return 0;
    }

    void performCreate(Bundle arg3) {
        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.m();
        }

        this.mState = 1;
        this.mCalled = false;
        this.onCreate(arg3);
        this.mIsCreated = true;
        if(this.mCalled) {
            this.mLifecycleRegistry.a(android.arch.lifecycle.d$a.ON_CREATE);
            return;
        }

        StringBuilder v0 = new StringBuilder();
        v0.append("Fragment ");
        v0.append(this);
        v0.append(" did not call through to super.onCreate()");
        throw new af(v0.toString());
    }

    boolean performCreateOptionsMenu(Menu arg3, MenuInflater arg4) {
        int v1 = 0;
        if(!this.mHidden) {
            if((this.mHasMenu) && (this.mMenuVisible)) {
                v1 = 1;
                this.onCreateOptionsMenu(arg3, arg4);
            }

            if(this.mChildFragmentManager == null) {
                goto label_14;
            }

            v1 |= this.mChildFragmentManager.a(arg3, arg4);
        }

    label_14:
        return ((boolean)v1);
    }

    void performCreateView(LayoutInflater arg2, ViewGroup arg3, Bundle arg4) {
        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.m();
        }

        this.mPerformedCreateView = true;
        this.mViewLifecycleOwner = new g() {
            public d getLifecycle() {
                if(this.a.mViewLifecycleRegistry == null) {
                    this.a.mViewLifecycleRegistry = new h(this.a.mViewLifecycleOwner);
                }

                return this.a.mViewLifecycleRegistry;
            }
        };
        h v0 = null;
        this.mViewLifecycleRegistry = v0;
        this.mView = this.onCreateView(arg2, arg3, arg4);
        if(this.mView != null) {
            this.mViewLifecycleOwner.getLifecycle();
            this.mViewLifecycleOwnerLiveData.a(this.mViewLifecycleOwner);
        }
        else if(this.mViewLifecycleRegistry == null) {
            this.mViewLifecycleOwner = ((g)v0);
        }
        else {
            goto label_25;
        }

        return;
    label_25:
        throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
    }

    void performDestroy() {
        this.mLifecycleRegistry.a(android.arch.lifecycle.d$a.ON_DESTROY);
        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.u();
        }

        this.mState = 0;
        this.mCalled = false;
        this.mIsCreated = false;
        this.onDestroy();
        if(this.mCalled) {
            this.mChildFragmentManager = null;
            return;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Fragment ");
        v1.append(this);
        v1.append(" did not call through to super.onDestroy()");
        throw new af(v1.toString());
    }

    void performDestroyView() {
        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.t();
        }

        this.mState = 1;
        this.mCalled = false;
        this.onDestroyView();
        if(this.mCalled) {
            t.a(((g)this)).a();
            this.mPerformedCreateView = false;
            return;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Fragment ");
        v1.append(this);
        v1.append(" did not call through to super.onDestroyView()");
        throw new af(v1.toString());
    }

    void performDetach() {
        StringBuilder v1;
        this.mCalled = false;
        this.onDetach();
        LayoutInflater v0 = null;
        this.mLayoutInflater = v0;
        if(this.mCalled) {
            if(this.mChildFragmentManager != null) {
                if(this.mRetaining) {
                    this.mChildFragmentManager.u();
                    this.mChildFragmentManager = ((m)v0);
                }
                else {
                    v1 = new StringBuilder();
                    v1.append("Child FragmentManager of ");
                    v1.append(this);
                    v1.append(" was not ");
                    v1.append(" destroyed and this fragment is not retaining instance");
                    throw new IllegalStateException(v1.toString());
                }
            }

            return;
        }

        v1 = new StringBuilder();
        v1.append("Fragment ");
        v1.append(this);
        v1.append(" did not call through to super.onDetach()");
        throw new af(v1.toString());
    }

    LayoutInflater performGetLayoutInflater(Bundle arg1) {
        this.mLayoutInflater = this.onGetLayoutInflater(arg1);
        return this.mLayoutInflater;
    }

    void performLowMemory() {
        this.onLowMemory();
        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.v();
        }
    }

    void performMultiWindowModeChanged(boolean arg2) {
        this.onMultiWindowModeChanged(arg2);
        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.a(arg2);
        }
    }

    boolean performOptionsItemSelected(MenuItem arg3) {
        if(!this.mHidden) {
            if((this.mHasMenu) && (this.mMenuVisible) && (this.onOptionsItemSelected(arg3))) {
                return 1;
            }

            if(this.mChildFragmentManager == null) {
                return 0;
            }

            if(!this.mChildFragmentManager.a(arg3)) {
                return 0;
            }

            return 1;
        }

        return 0;
    }

    void performOptionsMenuClosed(Menu arg2) {
        if(!this.mHidden) {
            if((this.mHasMenu) && (this.mMenuVisible)) {
                this.onOptionsMenuClosed(arg2);
            }

            if(this.mChildFragmentManager == null) {
                return;
            }

            this.mChildFragmentManager.b(arg2);
        }
    }

    void performPause() {
        if(this.mView != null) {
            this.mViewLifecycleRegistry.a(android.arch.lifecycle.d$a.ON_PAUSE);
        }

        this.mLifecycleRegistry.a(android.arch.lifecycle.d$a.ON_PAUSE);
        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.r();
        }

        this.mState = 3;
        this.mCalled = false;
        this.onPause();
        if(this.mCalled) {
            return;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Fragment ");
        v1.append(this);
        v1.append(" did not call through to super.onPause()");
        throw new af(v1.toString());
    }

    void performPictureInPictureModeChanged(boolean arg2) {
        this.onPictureInPictureModeChanged(arg2);
        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.b(arg2);
        }
    }

    boolean performPrepareOptionsMenu(Menu arg3) {
        int v1 = 0;
        if(!this.mHidden) {
            if((this.mHasMenu) && (this.mMenuVisible)) {
                v1 = 1;
                this.onPrepareOptionsMenu(arg3);
            }

            if(this.mChildFragmentManager == null) {
                goto label_14;
            }

            v1 |= this.mChildFragmentManager.a(arg3);
        }

    label_14:
        return ((boolean)v1);
    }

    void performResume() {
        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.m();
            this.mChildFragmentManager.g();
        }

        this.mState = 4;
        this.mCalled = false;
        this.onResume();
        if(this.mCalled) {
            if(this.mChildFragmentManager != null) {
                this.mChildFragmentManager.q();
                this.mChildFragmentManager.g();
            }

            this.mLifecycleRegistry.a(android.arch.lifecycle.d$a.ON_RESUME);
            if(this.mView != null) {
                this.mViewLifecycleRegistry.a(android.arch.lifecycle.d$a.ON_RESUME);
            }

            return;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Fragment ");
        v1.append(this);
        v1.append(" did not call through to super.onResume()");
        throw new af(v1.toString());
    }

    void performSaveInstanceState(Bundle arg3) {
        this.onSaveInstanceState(arg3);
        if(this.mChildFragmentManager != null) {
            Parcelable v0 = this.mChildFragmentManager.l();
            if(v0 != null) {
                arg3.putParcelable("android:support:fragments", v0);
            }
        }
    }

    void performStart() {
        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.m();
            this.mChildFragmentManager.g();
        }

        this.mState = 3;
        this.mCalled = false;
        this.onStart();
        if(this.mCalled) {
            if(this.mChildFragmentManager != null) {
                this.mChildFragmentManager.p();
            }

            this.mLifecycleRegistry.a(android.arch.lifecycle.d$a.ON_START);
            if(this.mView != null) {
                this.mViewLifecycleRegistry.a(android.arch.lifecycle.d$a.ON_START);
            }

            return;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Fragment ");
        v1.append(this);
        v1.append(" did not call through to super.onStart()");
        throw new af(v1.toString());
    }

    void performStop() {
        this.mLifecycleRegistry.a(android.arch.lifecycle.d$a.ON_STOP);
        if(this.mChildFragmentManager != null) {
            this.mChildFragmentManager.s();
        }

        this.mState = 2;
        this.mCalled = false;
        this.onStop();
        if(this.mCalled) {
            return;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Fragment ");
        v1.append(this);
        v1.append(" did not call through to super.onStop()");
        throw new af(v1.toString());
    }

    public void postponeEnterTransition() {
        this.ensureAnimationInfo().q = true;
    }

    public void registerForContextMenu(View arg1) {
        arg1.setOnCreateContextMenuListener(((View$OnCreateContextMenuListener)this));
    }

    public final void requestPermissions(String[] arg2, int arg3) {
        if(this.mHost != null) {
            this.mHost.a(this, arg2, arg3);
            return;
        }

        StringBuilder v3 = new StringBuilder();
        v3.append("Fragment ");
        v3.append(this);
        v3.append(" not attached to Activity");
        throw new IllegalStateException(v3.toString());
    }

    public final android.support.v4.app.h requireActivity() {
        android.support.v4.app.h v0 = this.getActivity();
        if(v0 != null) {
            return v0;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Fragment ");
        v1.append(this);
        v1.append(" not attached to an activity.");
        throw new IllegalStateException(v1.toString());
    }

    public final Context requireContext() {
        Context v0 = this.getContext();
        if(v0 != null) {
            return v0;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Fragment ");
        v1.append(this);
        v1.append(" not attached to a context.");
        throw new IllegalStateException(v1.toString());
    }

    public final l requireFragmentManager() {
        l v0 = this.getFragmentManager();
        if(v0 != null) {
            return v0;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Fragment ");
        v1.append(this);
        v1.append(" not associated with a fragment manager.");
        throw new IllegalStateException(v1.toString());
    }

    public final Object requireHost() {
        Object v0 = this.getHost();
        if(v0 != null) {
            return v0;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Fragment ");
        v1.append(this);
        v1.append(" not attached to a host.");
        throw new IllegalStateException(v1.toString());
    }

    void restoreChildFragmentState(Bundle arg3) {
        if(arg3 != null) {
            Parcelable v3 = arg3.getParcelable("android:support:fragments");
            if(v3 != null) {
                if(this.mChildFragmentManager == null) {
                    this.instantiateChildFragmentManager();
                }

                this.mChildFragmentManager.a(v3, this.mChildNonConfig);
                this.mChildNonConfig = null;
                this.mChildFragmentManager.n();
            }
        }
    }

    final void restoreViewState(Bundle arg3) {
        if(this.mSavedViewState != null) {
            this.mInnerView.restoreHierarchyState(this.mSavedViewState);
            this.mSavedViewState = null;
        }

        this.mCalled = false;
        this.onViewStateRestored(arg3);
        if(this.mCalled) {
            return;
        }

        StringBuilder v0 = new StringBuilder();
        v0.append("Fragment ");
        v0.append(this);
        v0.append(" did not call through to super.onViewStateRestored()");
        throw new af(v0.toString());
    }

    public void setAllowEnterTransitionOverlap(boolean arg2) {
        this.ensureAnimationInfo().n = Boolean.valueOf(arg2);
    }

    public void setAllowReturnTransitionOverlap(boolean arg2) {
        this.ensureAnimationInfo().m = Boolean.valueOf(arg2);
    }

    void setAnimatingAway(View arg2) {
        this.ensureAnimationInfo().a = arg2;
    }

    void setAnimator(Animator arg2) {
        this.ensureAnimationInfo().b = arg2;
    }

    public void setArguments(Bundle arg2) {
        if(this.mIndex >= 0) {
            if(!this.isStateSaved()) {
            }
            else {
                throw new IllegalStateException("Fragment already active and state has been saved");
            }
        }

        this.mArguments = arg2;
    }

    public void setEnterSharedElementCallback(ae arg2) {
        this.ensureAnimationInfo().o = arg2;
    }

    public void setEnterTransition(Object arg2) {
        this.ensureAnimationInfo().g = arg2;
    }

    public void setExitSharedElementCallback(ae arg2) {
        this.ensureAnimationInfo().p = arg2;
    }

    public void setExitTransition(Object arg2) {
        this.ensureAnimationInfo().i = arg2;
    }

    public void setHasOptionsMenu(boolean arg2) {
        if(this.mHasMenu != arg2) {
            this.mHasMenu = arg2;
            if((this.isAdded()) && !this.isHidden()) {
                this.mHost.d();
            }
        }
    }

    void setHideReplaced(boolean arg2) {
        this.ensureAnimationInfo().s = arg2;
    }

    final void setIndex(int arg1, Fragment arg2) {
        String v2;
        StringBuilder v1;
        this.mIndex = arg1;
        if(arg2 != null) {
            v1 = new StringBuilder();
            v1.append(arg2.mWho);
            v2 = ":";
        }
        else {
            v1 = new StringBuilder();
            v2 = "android:fragment:";
        }

        v1.append(v2);
        v1.append(this.mIndex);
        this.mWho = v1.toString();
    }

    public void setInitialSavedState(SavedState arg2) {
        if(this.mIndex < 0) {
            Bundle v2 = arg2 == null || arg2.a == null ? null : arg2.a;
            this.mSavedFragmentState = v2;
            return;
        }

        throw new IllegalStateException("Fragment already active");
    }

    public void setMenuVisibility(boolean arg2) {
        if(this.mMenuVisible != arg2) {
            this.mMenuVisible = arg2;
            if((this.mHasMenu) && (this.isAdded()) && !this.isHidden()) {
                this.mHost.d();
            }
        }
    }

    void setNextAnim(int arg2) {
        if(this.mAnimationInfo == null && arg2 == 0) {
            return;
        }

        this.ensureAnimationInfo().d = arg2;
    }

    void setNextTransition(int arg2, int arg3) {
        if(this.mAnimationInfo == null && arg2 == 0 && arg3 == 0) {
            return;
        }

        this.ensureAnimationInfo();
        this.mAnimationInfo.e = arg2;
        this.mAnimationInfo.f = arg3;
    }

    void setOnStartEnterTransitionListener(c arg3) {
        this.ensureAnimationInfo();
        if(arg3 == this.mAnimationInfo.r) {
            return;
        }

        if(arg3 != null) {
            if(this.mAnimationInfo.r == null) {
            }
            else {
                StringBuilder v0 = new StringBuilder();
                v0.append("Trying to set a replacement startPostponedEnterTransition on ");
                v0.append(this);
                throw new IllegalStateException(v0.toString());
            }
        }

        if(this.mAnimationInfo.q) {
            this.mAnimationInfo.r = arg3;
        }

        if(arg3 != null) {
            arg3.b();
        }
    }

    public void setReenterTransition(Object arg2) {
        this.ensureAnimationInfo().j = arg2;
    }

    public void setRetainInstance(boolean arg1) {
        this.mRetainInstance = arg1;
    }

    public void setReturnTransition(Object arg2) {
        this.ensureAnimationInfo().h = arg2;
    }

    public void setSharedElementEnterTransition(Object arg2) {
        this.ensureAnimationInfo().k = arg2;
    }

    public void setSharedElementReturnTransition(Object arg2) {
        this.ensureAnimationInfo().l = arg2;
    }

    void setStateAfterAnimating(int arg2) {
        this.ensureAnimationInfo().c = arg2;
    }

    public void setTargetFragment(Fragment arg3, int arg4) {
        StringBuilder v0_1;
        l v0 = this.getFragmentManager();
        l v1 = arg3 != null ? arg3.getFragmentManager() : null;
        if(v0 != null && v1 != null) {
            if(v0 == v1) {
            }
            else {
                v0_1 = new StringBuilder();
                v0_1.append("Fragment ");
                v0_1.append(arg3);
                v0_1.append(" must share the same FragmentManager to be set as a target fragment");
                throw new IllegalArgumentException(v0_1.toString());
            }
        }

        Fragment v0_2;
        for(v0_2 = arg3; true; v0_2 = v0_2.getTargetFragment()) {
            if(v0_2 == null) {
                goto label_39;
            }

            if(v0_2 == this) {
                break;
            }
        }

        v0_1 = new StringBuilder();
        v0_1.append("Setting ");
        v0_1.append(arg3);
        v0_1.append(" as the target of ");
        v0_1.append(this);
        v0_1.append(" would create a target cycle");
        throw new IllegalArgumentException(v0_1.toString());
    label_39:
        this.mTarget = arg3;
        this.mTargetRequestCode = arg4;
    }

    public void setUserVisibleHint(boolean arg3) {
        int v1 = 3;
        if(!this.mUserVisibleHint && (arg3) && this.mState < v1 && this.mFragmentManager != null && (this.isAdded()) && (this.mIsCreated)) {
            this.mFragmentManager.a(this);
        }

        this.mUserVisibleHint = arg3;
        boolean v0 = this.mState >= v1 || (arg3) ? false : true;
        this.mDeferStart = v0;
        if(this.mSavedFragmentState != null) {
            this.mSavedUserVisibleHint = Boolean.valueOf(arg3);
        }
    }

    public boolean shouldShowRequestPermissionRationale(String arg2) {
        if(this.mHost != null) {
            return this.mHost.a(arg2);
        }

        return 0;
    }

    public void startActivity(Intent arg2) {
        this.startActivity(arg2, null);
    }

    public void startActivity(Intent arg3, Bundle arg4) {
        if(this.mHost != null) {
            this.mHost.a(this, arg3, -1, arg4);
            return;
        }

        StringBuilder v4 = new StringBuilder();
        v4.append("Fragment ");
        v4.append(this);
        v4.append(" not attached to Activity");
        throw new IllegalStateException(v4.toString());
    }

    public void startActivityForResult(Intent arg2, int arg3) {
        this.startActivityForResult(arg2, arg3, null);
    }

    public void startActivityForResult(Intent arg2, int arg3, Bundle arg4) {
        if(this.mHost != null) {
            this.mHost.a(this, arg2, arg3, arg4);
            return;
        }

        StringBuilder v3 = new StringBuilder();
        v3.append("Fragment ");
        v3.append(this);
        v3.append(" not attached to Activity");
        throw new IllegalStateException(v3.toString());
    }

    public void startIntentSenderForResult(IntentSender arg11, int arg12, Intent arg13, int arg14, int arg15, int arg16, Bundle arg17) {
        Fragment v9 = this;
        if(v9.mHost != null) {
            v9.mHost.a(this, arg11, arg12, arg13, arg14, arg15, arg16, arg17);
            return;
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Fragment ");
        v1.append(this);
        v1.append(" not attached to Activity");
        throw new IllegalStateException(v1.toString());
    }

    public void startPostponedEnterTransition() {
        if(this.mFragmentManager == null || this.mFragmentManager.m == null) {
            this.ensureAnimationInfo().q = false;
        }
        else if(Looper.myLooper() != this.mFragmentManager.m.j().getLooper()) {
            this.mFragmentManager.m.j().postAtFrontOfQueue(new Runnable() {
                public void run() {
                    this.a.callStartTransitionListener();
                }
            });
        }
        else {
            this.callStartTransitionListener();
        }
    }

    public String toString() {
        StringBuilder v0 = new StringBuilder(128);
        android.support.v4.f.d.a(this, v0);
        if(this.mIndex >= 0) {
            v0.append(" #");
            v0.append(this.mIndex);
        }

        if(this.mFragmentId != 0) {
            v0.append(" id=0x");
            v0.append(Integer.toHexString(this.mFragmentId));
        }

        if(this.mTag != null) {
            v0.append(" ");
            v0.append(this.mTag);
        }

        v0.append('}');
        return v0.toString();
    }

    public void unregisterForContextMenu(View arg2) {
        arg2.setOnCreateContextMenuListener(null);
    }
}


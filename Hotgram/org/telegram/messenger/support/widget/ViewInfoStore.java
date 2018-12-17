package org.telegram.messenger.support.widget;

import android.support.v4.f.f;
import android.support.v4.f.k$a;
import android.support.v4.f.k$b;

class ViewInfoStore {
    class InfoRecord {
        static final int FLAG_APPEAR = 2;
        static final int FLAG_APPEAR_AND_DISAPPEAR = 3;
        static final int FLAG_APPEAR_PRE_AND_POST = 14;
        static final int FLAG_DISAPPEARED = 1;
        static final int FLAG_POST = 8;
        static final int FLAG_PRE = 4;
        static final int FLAG_PRE_AND_POST = 12;
        int flags;
        ItemHolderInfo postInfo;
        ItemHolderInfo preInfo;
        static a sPool;

        static {
            InfoRecord.sPool = new b(20);
        }

        private InfoRecord() {
            super();
        }

        static void drainCache() {
            while(InfoRecord.sPool.a() != null) {
            }
        }

        static InfoRecord obtain() {
            Object v0 = InfoRecord.sPool.a();
            if(v0 == null) {
                InfoRecord v0_1 = new InfoRecord();
            }

            return ((InfoRecord)v0);
        }

        static void recycle(InfoRecord arg1) {
            arg1.flags = 0;
            arg1.preInfo = null;
            arg1.postInfo = null;
            InfoRecord.sPool.a(arg1);
        }
    }

    interface ProcessCallback {
        void processAppeared(ViewHolder arg1, ItemHolderInfo arg2, ItemHolderInfo arg3);

        void processDisappeared(ViewHolder arg1, ItemHolderInfo arg2, ItemHolderInfo arg3);

        void processPersistent(ViewHolder arg1, ItemHolderInfo arg2, ItemHolderInfo arg3);

        void unused(ViewHolder arg1);
    }

    private static final boolean DEBUG = false;
    final android.support.v4.f.a mLayoutHolderMap;
    final f mOldChangedHolders;

    ViewInfoStore() {
        super();
        this.mLayoutHolderMap = new android.support.v4.f.a();
        this.mOldChangedHolders = new f();
    }

    void addToAppearedInPreLayoutHolders(ViewHolder arg3, ItemHolderInfo arg4) {
        InfoRecord v0_1;
        Object v0 = this.mLayoutHolderMap.get(arg3);
        if(v0 == null) {
            v0_1 = InfoRecord.obtain();
            this.mLayoutHolderMap.put(arg3, v0_1);
        }

        v0_1.flags |= 2;
        v0_1.preInfo = arg4;
    }

    void addToDisappearedInLayout(ViewHolder arg3) {
        InfoRecord v0_1;
        Object v0 = this.mLayoutHolderMap.get(arg3);
        if(v0 == null) {
            v0_1 = InfoRecord.obtain();
            this.mLayoutHolderMap.put(arg3, v0_1);
        }

        v0_1.flags |= 1;
    }

    void addToOldChangeHolders(long arg2, ViewHolder arg4) {
        this.mOldChangedHolders.b(arg2, arg4);
    }

    void addToPostLayout(ViewHolder arg3, ItemHolderInfo arg4) {
        Object v0 = this.mLayoutHolderMap.get(arg3);
        if(v0 == null) {
            InfoRecord v0_1 = InfoRecord.obtain();
            this.mLayoutHolderMap.put(arg3, v0_1);
        }

        ((InfoRecord)v0).postInfo = arg4;
        ((InfoRecord)v0).flags |= 8;
    }

    void addToPreLayout(ViewHolder arg3, ItemHolderInfo arg4) {
        Object v0 = this.mLayoutHolderMap.get(arg3);
        if(v0 == null) {
            InfoRecord v0_1 = InfoRecord.obtain();
            this.mLayoutHolderMap.put(arg3, v0_1);
        }

        ((InfoRecord)v0).preInfo = arg4;
        ((InfoRecord)v0).flags |= 4;
    }

    void clear() {
        this.mLayoutHolderMap.clear();
        this.mOldChangedHolders.c();
    }

    ViewHolder getFromOldChangeHolders(long arg2) {
        return this.mOldChangedHolders.a(arg2);
    }

    boolean isDisappearing(ViewHolder arg2) {
        Object v2 = this.mLayoutHolderMap.get(arg2);
        boolean v0 = true;
        if(v2 == null || (((InfoRecord)v2).flags & 1) == 0) {
            v0 = false;
        }
        else {
        }

        return v0;
    }

    boolean isInPreLayout(ViewHolder arg2) {
        Object v2 = this.mLayoutHolderMap.get(arg2);
        boolean v2_1 = v2 == null || (((InfoRecord)v2).flags & 4) == 0 ? false : true;
        return v2_1;
    }

    void onDetach() {
        InfoRecord.drainCache();
    }

    public void onViewDetached(ViewHolder arg1) {
        this.removeFromDisappearedInLayout(arg1);
    }

    private ItemHolderInfo popFromLayoutStep(ViewHolder arg4, int arg5) {
        ItemHolderInfo v5;
        int v4 = this.mLayoutHolderMap.a(arg4);
        ItemHolderInfo v0 = null;
        if(v4 < 0) {
            return v0;
        }

        Object v1 = this.mLayoutHolderMap.c(v4);
        if(v1 != null && (((InfoRecord)v1).flags & arg5) != 0) {
            ((InfoRecord)v1).flags &= arg5 ^ -1;
            if(arg5 == 4) {
                v5 = ((InfoRecord)v1).preInfo;
            }
            else if(arg5 == 8) {
                v5 = ((InfoRecord)v1).postInfo;
            }
            else {
                goto label_29;
            }

            if((((InfoRecord)v1).flags & 12) == 0) {
                this.mLayoutHolderMap.d(v4);
                InfoRecord.recycle(((InfoRecord)v1));
            }

            return v5;
        label_29:
            throw new IllegalArgumentException("Must provide flag PRE or POST");
        }

        return v0;
    }

    ItemHolderInfo popFromPostLayout(ViewHolder arg2) {
        return this.popFromLayoutStep(arg2, 8);
    }

    ItemHolderInfo popFromPreLayout(ViewHolder arg2) {
        return this.popFromLayoutStep(arg2, 4);
    }

    void process(ProcessCallback arg6) {
        int v0;
        for(v0 = this.mLayoutHolderMap.size() - 1; v0 >= 0; --v0) {
            Object v1 = this.mLayoutHolderMap.b(v0);
            Object v2 = this.mLayoutHolderMap.d(v0);
            if((((InfoRecord)v2).flags & 3) == 3 || ((InfoRecord)v2).preInfo == null) {
                arg6.unused(((ViewHolder)v1));
                goto label_51;
            label_20:
                ItemHolderInfo v3 = ((InfoRecord)v2).preInfo;
                ItemHolderInfo v4 = ((InfoRecord)v2).postInfo;
                goto label_22;
            label_24:
                if((((InfoRecord)v2).flags & 14) != 14) {
                    if((((InfoRecord)v2).flags & 12) == 12) {
                        arg6.processPersistent(((ViewHolder)v1), ((InfoRecord)v2).preInfo, ((InfoRecord)v2).postInfo);
                        goto label_51;
                    }
                    else if((((InfoRecord)v2).flags & 4) != 0) {
                        v3 = ((InfoRecord)v2).preInfo;
                        v4 = null;
                    label_22:
                        arg6.processDisappeared(((ViewHolder)v1), v3, v4);
                        goto label_51;
                    }
                    else if((((InfoRecord)v2).flags & 8) != 0) {
                    }
                    else {
                        goto label_51;
                    }
                }

                arg6.processAppeared(((ViewHolder)v1), ((InfoRecord)v2).preInfo, ((InfoRecord)v2).postInfo);
            }
            else if((((InfoRecord)v2).flags & 1) == 0) {
                goto label_24;
            }
            else {
                goto label_20;
            }

        label_51:
            InfoRecord.recycle(((InfoRecord)v2));
        }
    }

    void removeFromDisappearedInLayout(ViewHolder arg2) {
        Object v2 = this.mLayoutHolderMap.get(arg2);
        if(v2 == null) {
            return;
        }

        ((InfoRecord)v2).flags &= -2;
    }

    void removeViewHolder(ViewHolder arg3) {
        int v0 = this.mOldChangedHolders.b() - 1;
        while(v0 >= 0) {
            if(arg3 == this.mOldChangedHolders.c(v0)) {
                this.mOldChangedHolders.a(v0);
            }
            else {
                --v0;
                continue;
            }

            break;
        }

        Object v3 = this.mLayoutHolderMap.remove(arg3);
        if(v3 != null) {
            InfoRecord.recycle(((InfoRecord)v3));
        }
    }
}


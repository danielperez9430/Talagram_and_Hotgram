package org.telegram.messenger.support.widget;

import java.util.List;

class OpReorderer {
    interface Callback {
        UpdateOp obtainUpdateOp(int arg1, int arg2, int arg3, Object arg4);

        void recycleUpdateOp(UpdateOp arg1);
    }

    final Callback mCallback;

    OpReorderer(Callback arg1) {
        super();
        this.mCallback = arg1;
    }

    private int getLastMoveOutOfOrder(List arg6) {
        int v0 = arg6.size() - 1;
        int v2 = 0;
        while(v0 >= 0) {
            if(arg6.get(v0).cmd != 8) {
                v2 = 1;
            }
            else if(v2 != 0) {
                return v0;
            }

            --v0;
        }

        return -1;
    }

    void reorderOps(List arg3) {
        while(true) {
            int v0 = this.getLastMoveOutOfOrder(arg3);
            if(v0 == -1) {
                return;
            }

            this.swapMoveOp(arg3, v0, v0 + 1);
        }
    }

    private void swapMoveAdd(List arg4, int arg5, UpdateOp arg6, int arg7, UpdateOp arg8) {
        int v0 = arg6.itemCount < arg8.positionStart ? -1 : 0;
        if(arg6.positionStart < arg8.positionStart) {
            ++v0;
        }

        if(arg8.positionStart <= arg6.positionStart) {
            arg6.positionStart += arg8.itemCount;
        }

        if(arg8.positionStart <= arg6.itemCount) {
            arg6.itemCount += arg8.itemCount;
        }

        arg8.positionStart += v0;
        arg4.set(arg5, arg8);
        arg4.set(arg7, arg6);
    }

    private void swapMoveOp(List arg8, int arg9, int arg10) {
        Object v4 = arg8.get(arg9);
        Object v6 = arg8.get(arg10);
        int v0 = ((UpdateOp)v6).cmd;
        if(v0 != 4) {
            switch(v0) {
                case 1: {
                    goto label_15;
                }
                case 2: {
                    goto label_9;
                }
            }

            return;
        label_9:
            this.swapMoveRemove(arg8, arg9, ((UpdateOp)v4), arg10, ((UpdateOp)v6));
            return;
        label_15:
            this.swapMoveAdd(arg8, arg9, ((UpdateOp)v4), arg10, ((UpdateOp)v6));
        }
        else {
            this.swapMoveUpdate(arg8, arg9, ((UpdateOp)v4), arg10, ((UpdateOp)v6));
        }
    }

    void swapMoveRemove(List arg9, int arg10, UpdateOp arg11, int arg12, UpdateOp arg13) {
        UpdateOp v6_1;
        int v0;
        int v2 = 0;
        if(arg11.positionStart < arg11.itemCount) {
            if(arg13.positionStart == arg11.positionStart && arg13.itemCount == arg11.itemCount - arg11.positionStart) {
                v0 = 0;
                goto label_14;
            }

            v0 = 0;
        }
        else {
            if(arg13.positionStart == arg11.itemCount + 1 && arg13.itemCount == arg11.positionStart - arg11.itemCount) {
                v0 = 1;
            label_14:
                v2 = 1;
                goto label_30;
            }

            v0 = 1;
        }

    label_30:
        int v5 = 2;
        if(arg11.itemCount < arg13.positionStart) {
            --arg13.positionStart;
        }
        else if(arg11.itemCount < arg13.positionStart + arg13.itemCount) {
            --arg13.itemCount;
            arg11.cmd = v5;
            arg11.itemCount = 1;
            if(arg13.itemCount == 0) {
                arg9.remove(arg12);
                this.mCallback.recycleUpdateOp(arg13);
            }

            return;
        }

        Object v6 = null;
        if(arg11.positionStart <= arg13.positionStart) {
            ++arg13.positionStart;
        }
        else if(arg11.positionStart < arg13.positionStart + arg13.itemCount) {
            v6_1 = this.mCallback.obtainUpdateOp(v5, arg11.positionStart + 1, arg13.positionStart + arg13.itemCount - arg11.positionStart, v6);
            arg13.itemCount = arg11.positionStart - arg13.positionStart;
        }

        if(v2 != 0) {
            arg9.set(arg10, arg13);
            arg9.remove(arg12);
            this.mCallback.recycleUpdateOp(arg11);
            return;
        }

        if(v0 != 0) {
            if(v6_1 != null) {
                if(arg11.positionStart > v6_1.positionStart) {
                    arg11.positionStart -= v6_1.itemCount;
                }

                if(arg11.itemCount <= v6_1.positionStart) {
                    goto label_102;
                }

                arg11.itemCount -= v6_1.itemCount;
            }

        label_102:
            if(arg11.positionStart > arg13.positionStart) {
                arg11.positionStart -= arg13.itemCount;
            }

            if(arg11.itemCount <= arg13.positionStart) {
                goto label_143;
            }

            goto label_112;
        }
        else {
            if(v6_1 != null) {
                if(arg11.positionStart >= v6_1.positionStart) {
                    arg11.positionStart -= v6_1.itemCount;
                }

                if(arg11.itemCount < v6_1.positionStart) {
                    goto label_132;
                }

                arg11.itemCount -= v6_1.itemCount;
            }

        label_132:
            if(arg11.positionStart >= arg13.positionStart) {
                arg11.positionStart -= arg13.itemCount;
            }

            if(arg11.itemCount < arg13.positionStart) {
                goto label_143;
            }

        label_112:
            arg11.itemCount -= arg13.itemCount;
        }

    label_143:
        arg9.set(arg10, arg13);
        if(arg11.positionStart != arg11.itemCount) {
            arg9.set(arg12, arg11);
        }
        else {
            arg9.remove(arg12);
        }

        if(v6_1 != null) {
            arg9.add(arg10, v6_1);
        }
    }

    void swapMoveUpdate(List arg8, int arg9, UpdateOp arg10, int arg11, UpdateOp arg12) {
        UpdateOp v0;
        int v2 = 4;
        UpdateOp v3 = null;
        if(arg10.itemCount < arg12.positionStart) {
            --arg12.positionStart;
            goto label_23;
        }
        else if(arg10.itemCount < arg12.positionStart + arg12.itemCount) {
            --arg12.itemCount;
            v0 = this.mCallback.obtainUpdateOp(v2, arg10.positionStart, 1, arg12.payload);
        }
        else {
        label_23:
            v0 = v3;
        }

        if(arg10.positionStart <= arg12.positionStart) {
            ++arg12.positionStart;
        }
        else if(arg10.positionStart < arg12.positionStart + arg12.itemCount) {
            int v1 = arg12.positionStart + arg12.itemCount - arg10.positionStart;
            v3 = this.mCallback.obtainUpdateOp(v2, arg10.positionStart + 1, v1, arg12.payload);
            arg12.itemCount -= v1;
        }

        arg8.set(arg11, arg10);
        if(arg12.itemCount > 0) {
            arg8.set(arg9, arg12);
        }
        else {
            arg8.remove(arg9);
            this.mCallback.recycleUpdateOp(arg12);
        }

        if(v0 != null) {
            arg8.add(arg9, v0);
        }

        if(v3 != null) {
            arg8.add(arg9, v3);
        }
    }
}


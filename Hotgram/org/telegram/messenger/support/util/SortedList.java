package org.telegram.messenger.support.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class SortedList {
    public class BatchedCallback extends Callback {
        private final BatchingListUpdateCallback mBatchingListUpdateCallback;
        final Callback mWrappedCallback;

        public BatchedCallback(Callback arg2) {
            super();
            this.mWrappedCallback = arg2;
            this.mBatchingListUpdateCallback = new BatchingListUpdateCallback(this.mWrappedCallback);
        }

        public boolean areContentsTheSame(Object arg2, Object arg3) {
            return this.mWrappedCallback.areContentsTheSame(arg2, arg3);
        }

        public boolean areItemsTheSame(Object arg2, Object arg3) {
            return this.mWrappedCallback.areItemsTheSame(arg2, arg3);
        }

        public int compare(Object arg2, Object arg3) {
            return this.mWrappedCallback.compare(arg2, arg3);
        }

        public void dispatchLastEvent() {
            this.mBatchingListUpdateCallback.dispatchLastEvent();
        }

        public Object getChangePayload(Object arg2, Object arg3) {
            return this.mWrappedCallback.getChangePayload(arg2, arg3);
        }

        public void onChanged(int arg3, int arg4) {
            this.mBatchingListUpdateCallback.onChanged(arg3, arg4, null);
        }

        public void onChanged(int arg2, int arg3, Object arg4) {
            this.mBatchingListUpdateCallback.onChanged(arg2, arg3, arg4);
        }

        public void onInserted(int arg2, int arg3) {
            this.mBatchingListUpdateCallback.onInserted(arg2, arg3);
        }

        public void onMoved(int arg2, int arg3) {
            this.mBatchingListUpdateCallback.onMoved(arg2, arg3);
        }

        public void onRemoved(int arg2, int arg3) {
            this.mBatchingListUpdateCallback.onRemoved(arg2, arg3);
        }
    }

    public abstract class Callback implements Comparator, ListUpdateCallback {
        public Callback() {
            super();
        }

        public abstract boolean areContentsTheSame(Object arg1, Object arg2);

        public abstract boolean areItemsTheSame(Object arg1, Object arg2);

        public abstract int compare(Object arg1, Object arg2);

        public Object getChangePayload(Object arg1, Object arg2) {
            return null;
        }

        public void onChanged(int arg1, int arg2, Object arg3) {
            this.onChanged(arg1, arg2);
        }

        public abstract void onChanged(int arg1, int arg2);
    }

    private static final int CAPACITY_GROWTH = 10;
    private static final int DELETION = 2;
    private static final int INSERTION = 1;
    public static final int INVALID_POSITION = -1;
    private static final int LOOKUP = 4;
    private static final int MIN_CAPACITY = 10;
    private BatchedCallback mBatchedCallback;
    private Callback mCallback;
    Object[] mData;
    private int mNewDataStart;
    private Object[] mOldData;
    private int mOldDataSize;
    private int mOldDataStart;
    private int mSize;
    private final Class mTClass;

    public SortedList(Class arg2, Callback arg3) {
        this(arg2, arg3, 10);
    }

    public SortedList(Class arg1, Callback arg2, int arg3) {
        super();
        this.mTClass = arg1;
        this.mData = Array.newInstance(arg1, arg3);
        this.mCallback = arg2;
        this.mSize = 0;
    }

    private int add(Object arg7, boolean arg8) {
        int v0 = this.findIndexOf(arg7, this.mData, 0, this.mSize, 1);
        if(v0 == -1) {
            v0 = 0;
        }
        else if(v0 < this.mSize) {
            Object v2 = this.mData[v0];
            if(this.mCallback.areItemsTheSame(v2, arg7)) {
                if(this.mCallback.areContentsTheSame(v2, arg7)) {
                    this.mData[v0] = arg7;
                    return v0;
                }
                else {
                    this.mData[v0] = arg7;
                    this.mCallback.onChanged(v0, 1, this.mCallback.getChangePayload(v2, arg7));
                    return v0;
                }
            }
        }

        this.addToData(v0, arg7);
        if(arg8) {
            this.mCallback.onInserted(v0, 1);
        }

        return v0;
    }

    public int add(Object arg2) {
        this.throwIfInMutationOperation();
        return this.add(arg2, true);
    }

    public void addAll(Collection arg3) {
        this.addAll(arg3.toArray(Array.newInstance(this.mTClass, arg3.size())), true);
    }

    public void addAll(Object[] arg2, boolean arg3) {
        this.throwIfInMutationOperation();
        if(arg2.length == 0) {
            return;
        }

        if(!arg3) {
            arg2 = this.copyArray(arg2);
        }

        this.addAllInternal(arg2);
    }

    public void addAll(Object[] arg2) {
        this.addAll(arg2, false);
    }

    private void addAllInternal(Object[] arg3) {
        if(arg3.length < 1) {
            return;
        }

        int v0 = this.sortAndDedup(arg3);
        if(this.mSize == 0) {
            this.mData = arg3;
            this.mSize = v0;
            this.mCallback.onInserted(0, v0);
        }
        else {
            this.merge(arg3, v0);
        }
    }

    private void addToData(int arg5, Object arg6) {
        if(arg5 <= this.mSize) {
            if(this.mSize == this.mData.length) {
                Object v0 = Array.newInstance(this.mTClass, this.mData.length + 10);
                System.arraycopy(this.mData, 0, v0, 0, arg5);
                v0[arg5] = arg6;
                System.arraycopy(this.mData, arg5, v0, arg5 + 1, this.mSize - arg5);
                this.mData = ((Object[])v0);
            }
            else {
                System.arraycopy(this.mData, arg5, this.mData, arg5 + 1, this.mSize - arg5);
                this.mData[arg5] = arg6;
            }

            ++this.mSize;
            return;
        }

        StringBuilder v0_1 = new StringBuilder();
        v0_1.append("cannot add item to ");
        v0_1.append(arg5);
        v0_1.append(" because size is ");
        v0_1.append(this.mSize);
        throw new IndexOutOfBoundsException(v0_1.toString());
    }

    public void beginBatchedUpdates() {
        this.throwIfInMutationOperation();
        if((this.mCallback instanceof BatchedCallback)) {
            return;
        }

        if(this.mBatchedCallback == null) {
            this.mBatchedCallback = new BatchedCallback(this.mCallback);
        }

        this.mCallback = this.mBatchedCallback;
    }

    public void clear() {
        this.throwIfInMutationOperation();
        if(this.mSize == 0) {
            return;
        }

        int v0 = this.mSize;
        Arrays.fill(this.mData, 0, v0, null);
        this.mSize = 0;
        this.mCallback.onRemoved(0, v0);
    }

    private Object[] copyArray(Object[] arg4) {
        Object v0 = Array.newInstance(this.mTClass, arg4.length);
        System.arraycopy(arg4, 0, v0, 0, arg4.length);
        return ((Object[])v0);
    }

    public void endBatchedUpdates() {
        this.throwIfInMutationOperation();
        if((this.mCallback instanceof BatchedCallback)) {
            this.mCallback.dispatchLastEvent();
        }

        if(this.mCallback == this.mBatchedCallback) {
            this.mCallback = this.mBatchedCallback.mWrappedCallback;
        }
    }

    private int findIndexOf(Object arg6, Object[] arg7, int arg8, int arg9, int arg10) {
        while(true) {
            int v0 = -1;
            if(arg8 >= arg9) {
                break;
            }

            int v2 = (arg8 + arg9) / 2;
            Object v3 = arg7[v2];
            int v4 = this.mCallback.compare(v3, arg6);
            if(v4 < 0) {
                arg8 = v2 + 1;
                continue;
            }

            if(v4 == 0) {
                if(this.mCallback.areItemsTheSame(v3, arg6)) {
                    return v2;
                }

                int v6 = this.linearEqualitySearch(arg6, v2, arg8, arg9);
                if(arg10 == 1 && v6 == v0) {
                    v6 = v2;
                }

                return v6;
            }

            arg9 = v2;
        }

        if(arg10 == 1) {
        }
        else {
            arg8 = -1;
        }

        return arg8;
    }

    private int findSameItem(Object arg3, Object[] arg4, int arg5, int arg6) {
        while(arg5 < arg6) {
            if(this.mCallback.areItemsTheSame(arg4[arg5], arg3)) {
                return arg5;
            }

            ++arg5;
        }

        return -1;
    }

    public Object get(int arg4) {
        if(arg4 < this.mSize && arg4 >= 0) {
            if(this.mOldData != null && arg4 >= this.mNewDataStart) {
                return this.mOldData[arg4 - this.mNewDataStart + this.mOldDataStart];
            }

            return this.mData[arg4];
        }

        StringBuilder v1 = new StringBuilder();
        v1.append("Asked to get item at ");
        v1.append(arg4);
        v1.append(" but size is ");
        v1.append(this.mSize);
        throw new IndexOutOfBoundsException(v1.toString());
    }

    public int indexOf(Object arg9) {
        if(this.mOldData != null) {
            int v0 = this.findIndexOf(arg9, this.mData, 0, this.mNewDataStart, 4);
            int v1 = -1;
            if(v0 != v1) {
                return v0;
            }

            int v9 = this.findIndexOf(arg9, this.mOldData, this.mOldDataStart, this.mOldDataSize, 4);
            if(v9 != v1) {
                return v9 - this.mOldDataStart + this.mNewDataStart;
            }

            return v1;
        }

        return this.findIndexOf(arg9, this.mData, 0, this.mSize, 4);
    }

    private int linearEqualitySearch(Object arg4, int arg5, int arg6, int arg7) {
        int v0 = arg5 - 1;
        while(v0 >= arg6) {
            Object v1 = this.mData[v0];
            if(this.mCallback.compare(v1, arg4) != 0) {
            }
            else if(this.mCallback.areItemsTheSame(v1, arg4)) {
                return v0;
            }
            else {
                --v0;
                continue;
            }

            break;
        }

        do {
            ++arg5;
            if(arg5 < arg7) {
                Object v6 = this.mData[arg5];
                if(this.mCallback.compare(v6, arg4) != 0) {
                }
                else {
                    if(!this.mCallback.areItemsTheSame(v6, arg4)) {
                        continue;
                    }

                    return arg5;
                }
            }

            return -1;
        }
        while(true);

        return arg5;
    }

    private void merge(Object[] arg9, int arg10) {
        int v1 = 0;
        int v0 = !(this.mCallback instanceof BatchedCallback) ? 1 : 0;
        if(v0 != 0) {
            this.beginBatchedUpdates();
        }

        this.mOldData = this.mData;
        this.mOldDataStart = 0;
        this.mOldDataSize = this.mSize;
        this.mData = Array.newInstance(this.mTClass, this.mSize + arg10 + 10);
        this.mNewDataStart = 0;
        while(true) {
            if(this.mOldDataStart < this.mOldDataSize || v1 < arg10) {
                if(this.mOldDataStart == this.mOldDataSize) {
                    arg10 -= v1;
                    System.arraycopy(arg9, v1, this.mData, this.mNewDataStart, arg10);
                    this.mNewDataStart += arg10;
                    this.mSize += arg10;
                    this.mCallback.onInserted(this.mNewDataStart - arg10, arg10);
                }
                else if(v1 == arg10) {
                    int v9 = this.mOldDataSize - this.mOldDataStart;
                    System.arraycopy(this.mOldData, this.mOldDataStart, this.mData, this.mNewDataStart, v9);
                    this.mNewDataStart += v9;
                }
                else {
                    goto label_61;
                }
            }

            this.mOldData = null;
            if(v0 != 0) {
                this.endBatchedUpdates();
            }

            return;
        label_61:
            Object v3 = this.mOldData[this.mOldDataStart];
            Object v4 = arg9[v1];
            int v5 = this.mCallback.compare(v3, v4);
            if(v5 > 0) {
                Object[] v3_1 = this.mData;
                v5 = this.mNewDataStart;
                this.mNewDataStart = v5 + 1;
                v3_1[v5] = v4;
                ++this.mSize;
                ++v1;
                this.mCallback.onInserted(this.mNewDataStart - 1, 1);
                continue;
            }

            if(v5 == 0 && (this.mCallback.areItemsTheSame(v3, v4))) {
                Object[] v5_1 = this.mData;
                int v6 = this.mNewDataStart;
                this.mNewDataStart = v6 + 1;
                v5_1[v6] = v4;
                ++v1;
                ++this.mOldDataStart;
                if(this.mCallback.areContentsTheSame(v3, v4)) {
                    continue;
                }

                this.mCallback.onChanged(this.mNewDataStart - 1, 1, this.mCallback.getChangePayload(v3, v4));
                continue;
            }

            Object[] v4_1 = this.mData;
            v5 = this.mNewDataStart;
            this.mNewDataStart = v5 + 1;
            v4_1[v5] = v3;
            ++this.mOldDataStart;
        }
    }

    public void recalculatePositionOfItemAt(int arg3) {
        this.throwIfInMutationOperation();
        Object v0 = this.get(arg3);
        this.removeItemAtIndex(arg3, false);
        int v0_1 = this.add(v0, false);
        if(arg3 != v0_1) {
            this.mCallback.onMoved(arg3, v0_1);
        }
    }

    private boolean remove(Object arg7, boolean arg8) {
        int v7 = this.findIndexOf(arg7, this.mData, 0, this.mSize, 2);
        if(v7 == -1) {
            return 0;
        }

        this.removeItemAtIndex(v7, arg8);
        return 1;
    }

    public boolean remove(Object arg2) {
        this.throwIfInMutationOperation();
        return this.remove(arg2, true);
    }

    public Object removeItemAt(int arg3) {
        this.throwIfInMutationOperation();
        Object v0 = this.get(arg3);
        this.removeItemAtIndex(arg3, true);
        return v0;
    }

    private void removeItemAtIndex(int arg6, boolean arg7) {
        System.arraycopy(this.mData, arg6 + 1, this.mData, arg6, this.mSize - arg6 - 1);
        --this.mSize;
        this.mData[this.mSize] = null;
        if(arg7) {
            this.mCallback.onRemoved(arg6, 1);
        }
    }

    public void replaceAll(Collection arg3) {
        this.replaceAll(arg3.toArray(Array.newInstance(this.mTClass, arg3.size())), true);
    }

    public void replaceAll(Object[] arg1, boolean arg2) {
        this.throwIfInMutationOperation();
        if(!arg2) {
            arg1 = this.copyArray(arg1);
        }

        this.replaceAllInternal(arg1);
    }

    public void replaceAll(Object[] arg2) {
        this.replaceAll(arg2, false);
    }

    private void replaceAllInsert(Object arg3) {
        this.mData[this.mNewDataStart] = arg3;
        ++this.mNewDataStart;
        ++this.mSize;
        this.mCallback.onInserted(this.mNewDataStart - 1, 1);
    }

    private void replaceAllInternal(Object[] arg9) {
        int v0 = !(this.mCallback instanceof BatchedCallback) ? 1 : 0;
        if(v0 != 0) {
            this.beginBatchedUpdates();
        }

        this.mOldDataStart = 0;
        this.mOldDataSize = this.mSize;
        this.mOldData = this.mData;
        this.mNewDataStart = 0;
        int v1 = this.sortAndDedup(arg9);
        this.mData = Array.newInstance(this.mTClass, v1);
        while(true) {
            if(this.mNewDataStart < v1 || this.mOldDataStart < this.mOldDataSize) {
                if(this.mOldDataStart >= this.mOldDataSize) {
                    int v2 = this.mNewDataStart;
                    v1 -= this.mNewDataStart;
                    System.arraycopy(arg9, v2, this.mData, v2, v1);
                    this.mNewDataStart += v1;
                    this.mSize += v1;
                    this.mCallback.onInserted(v2, v1);
                }
                else if(this.mNewDataStart >= v1) {
                    int v9 = this.mOldDataSize - this.mOldDataStart;
                    this.mSize -= v9;
                    this.mCallback.onRemoved(this.mNewDataStart, v9);
                }
                else {
                    goto label_58;
                }
            }

            this.mOldData = null;
            if(v0 != 0) {
                this.endBatchedUpdates();
            }

            return;
        label_58:
            Object v3 = this.mOldData[this.mOldDataStart];
            Object v4 = arg9[this.mNewDataStart];
            int v5 = this.mCallback.compare(v3, v4);
            if(v5 < 0) {
                this.replaceAllRemove();
                continue;
            }

            if(v5 <= 0) {
                if(!this.mCallback.areItemsTheSame(v3, v4)) {
                    this.replaceAllRemove();
                }
                else {
                    goto label_76;
                }
            }

            this.replaceAllInsert(v4);
            continue;
        label_76:
            this.mData[this.mNewDataStart] = v4;
            ++this.mOldDataStart;
            ++this.mNewDataStart;
            if(this.mCallback.areContentsTheSame(v3, v4)) {
                continue;
            }

            this.mCallback.onChanged(this.mNewDataStart - 1, 1, this.mCallback.getChangePayload(v3, v4));
        }
    }

    private void replaceAllRemove() {
        --this.mSize;
        ++this.mOldDataStart;
        this.mCallback.onRemoved(this.mNewDataStart, 1);
    }

    public int size() {
        return this.mSize;
    }

    private int sortAndDedup(Object[] arg8) {
        int v1 = 0;
        if(arg8.length == 0) {
            return 0;
        }

        Arrays.sort(arg8, this.mCallback);
        int v0 = 1;
        int v2 = 1;
        while(v0 < arg8.length) {
            Object v3 = arg8[v0];
            if(this.mCallback.compare(arg8[v1], v3) == 0) {
                int v4 = this.findSameItem(v3, arg8, v1, v2);
                if(v4 != -1) {
                    arg8[v4] = v3;
                }
                else {
                    if(v2 != v0) {
                        arg8[v2] = v3;
                    }

                    ++v2;
                }
            }
            else {
                if(v2 != v0) {
                    arg8[v2] = v3;
                }

                int v6 = v2;
                ++v2;
                v1 = v6;
            }

            ++v0;
        }

        return v2;
    }

    private void throwIfInMutationOperation() {
        if(this.mOldData == null) {
            return;
        }

        throw new IllegalStateException("Data cannot be mutated in the middle of a batch update operation such as addAll or replaceAll.");
    }

    public void updateItemAt(int arg6, Object arg7) {
        this.throwIfInMutationOperation();
        Object v0 = this.get(arg6);
        int v3 = v0 == arg7 || !this.mCallback.areContentsTheSame(v0, arg7) ? 1 : 0;
        if(v0 != arg7 && this.mCallback.compare(v0, arg7) == 0) {
            this.mData[arg6] = arg7;
            if(v3 != 0) {
                this.mCallback.onChanged(arg6, 1, this.mCallback.getChangePayload(v0, arg7));
            }

            return;
        }

        if(v3 != 0) {
            this.mCallback.onChanged(arg6, 1, this.mCallback.getChangePayload(v0, arg7));
        }

        this.removeItemAtIndex(arg6, false);
        int v7 = this.add(arg7, false);
        if(arg6 != v7) {
            this.mCallback.onMoved(arg6, v7);
        }
    }
}


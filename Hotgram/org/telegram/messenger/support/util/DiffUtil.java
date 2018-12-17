package org.telegram.messenger.support.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.telegram.messenger.support.widget.RecyclerView$Adapter;

public class DiffUtil {
    final class org.telegram.messenger.support.util.DiffUtil$1 implements Comparator {
        org.telegram.messenger.support.util.DiffUtil$1() {
            super();
        }

        public int compare(Object arg1, Object arg2) {
            return this.compare(((Snake)arg1), ((Snake)arg2));
        }

        public int compare(Snake arg3, Snake arg4) {
            int v0 = arg3.x - arg4.x;
            if(v0 == 0) {
                v0 = arg3.y - arg4.y;
            }

            return v0;
        }
    }

    public abstract class Callback {
        public Callback() {
            super();
        }

        public abstract boolean areContentsTheSame(int arg1, int arg2);

        public abstract boolean areItemsTheSame(int arg1, int arg2);

        public Object getChangePayload(int arg1, int arg2) {
            return null;
        }

        public abstract int getNewListSize();

        public abstract int getOldListSize();
    }

    public class DiffResult {
        private static final int FLAG_CHANGED = 2;
        private static final int FLAG_IGNORE = 16;
        private static final int FLAG_MASK = 31;
        private static final int FLAG_MOVED_CHANGED = 4;
        private static final int FLAG_MOVED_NOT_CHANGED = 8;
        private static final int FLAG_NOT_CHANGED = 1;
        private static final int FLAG_OFFSET = 5;
        private final Callback mCallback;
        private final boolean mDetectMoves;
        private final int[] mNewItemStatuses;
        private final int mNewListSize;
        private final int[] mOldItemStatuses;
        private final int mOldListSize;
        private final List mSnakes;

        DiffResult(Callback arg1, List arg2, int[] arg3, int[] arg4, boolean arg5) {
            super();
            this.mSnakes = arg2;
            this.mOldItemStatuses = arg3;
            this.mNewItemStatuses = arg4;
            Arrays.fill(this.mOldItemStatuses, 0);
            Arrays.fill(this.mNewItemStatuses, 0);
            this.mCallback = arg1;
            this.mOldListSize = arg1.getOldListSize();
            this.mNewListSize = arg1.getNewListSize();
            this.mDetectMoves = arg5;
            this.addRootSnake();
            this.findMatchingItems();
        }

        private void addRootSnake() {
            Object v0 = this.mSnakes.isEmpty() ? null : this.mSnakes.get(0);
            if(v0 == null || ((Snake)v0).x != 0 || ((Snake)v0).y != 0) {
                Snake v0_1 = new Snake();
                v0_1.x = 0;
                v0_1.y = 0;
                v0_1.removal = false;
                v0_1.size = 0;
                v0_1.reverse = false;
                this.mSnakes.add(0, v0_1);
            }
        }

        private void dispatchAdditions(List arg7, ListUpdateCallback arg8, int arg9, int arg10, int arg11) {
            if(!this.mDetectMoves) {
                arg8.onInserted(arg9, arg10);
                return;
            }

            --arg10;
            while(arg10 >= 0) {
                int v2 = arg11 + arg10;
                int v1 = this.mNewItemStatuses[v2] & 31;
                if(v1 != 0) {
                    int v3 = 4;
                    if(v1 != v3 && v1 != 8) {
                        if(v1 == 16) {
                            arg7.add(new PostponedUpdate(v2, arg9, false));
                            goto label_57;
                        }
                        else {
                            StringBuilder v8 = new StringBuilder();
                            v8.append("unknown flag for pos ");
                            v8.append(v2);
                            v8.append(" ");
                            v8.append(Long.toBinaryString(((long)v1)));
                            throw new IllegalStateException(v8.toString());
                        }
                    }

                    int v4 = this.mNewItemStatuses[v2] >> 5;
                    arg8.onMoved(DiffResult.removePostponedUpdate(arg7, v4, true).currentPos, arg9);
                    if(v1 != v3) {
                        goto label_57;
                    }

                    arg8.onChanged(arg9, 1, this.mCallback.getChangePayload(v4, v2));
                }
                else {
                    arg8.onInserted(arg9, 1);
                    Iterator v1_1 = arg7.iterator();
                    while(v1_1.hasNext()) {
                        Object v2_1 = v1_1.next();
                        ++((PostponedUpdate)v2_1).currentPos;
                    }
                }

            label_57:
                --arg10;
            }
        }

        private void dispatchRemovals(List arg9, ListUpdateCallback arg10, int arg11, int arg12, int arg13) {
            if(!this.mDetectMoves) {
                arg10.onRemoved(arg11, arg12);
                return;
            }

            --arg12;
            while(arg12 >= 0) {
                int v2 = arg13 + arg12;
                int v1 = this.mOldItemStatuses[v2] & 31;
                if(v1 != 0) {
                    int v3 = 4;
                    if(v1 != v3 && v1 != 8) {
                        if(v1 == 16) {
                            arg9.add(new PostponedUpdate(v2, arg11 + arg12, true));
                            goto label_63;
                        }
                        else {
                            StringBuilder v10 = new StringBuilder();
                            v10.append("unknown flag for pos ");
                            v10.append(v2);
                            v10.append(" ");
                            v10.append(Long.toBinaryString(((long)v1)));
                            throw new IllegalStateException(v10.toString());
                        }
                    }

                    int v4 = this.mOldItemStatuses[v2] >> 5;
                    PostponedUpdate v5 = DiffResult.removePostponedUpdate(arg9, v4, false);
                    arg10.onMoved(arg11 + arg12, v5.currentPos - 1);
                    if(v1 != v3) {
                        goto label_63;
                    }

                    arg10.onChanged(v5.currentPos - 1, 1, this.mCallback.getChangePayload(v2, v4));
                }
                else {
                    arg10.onRemoved(arg11 + arg12, 1);
                    Iterator v1_1 = arg9.iterator();
                    while(v1_1.hasNext()) {
                        Object v2_1 = v1_1.next();
                        --((PostponedUpdate)v2_1).currentPos;
                    }
                }

            label_63:
                --arg12;
            }
        }

        public void dispatchUpdatesTo(ListUpdateCallback arg15) {
            BatchingListUpdateCallback v15;
            if((arg15 instanceof BatchingListUpdateCallback)) {
            }
            else {
                v15 = new BatchingListUpdateCallback(arg15);
            }

            ArrayList v0 = new ArrayList();
            int v1 = this.mOldListSize;
            int v9 = this.mNewListSize;
            int v8;
            for(v8 = this.mSnakes.size() - 1; v8 >= 0; --v8) {
                Object v10 = this.mSnakes.get(v8);
                int v11 = ((Snake)v10).size;
                int v12 = ((Snake)v10).x + v11;
                int v13 = ((Snake)v10).y + v11;
                if(v12 < v1) {
                    this.dispatchRemovals(v0, ((ListUpdateCallback)v15), v12, v1 - v12, v12);
                }

                if(v13 < v9) {
                    this.dispatchAdditions(v0, ((ListUpdateCallback)v15), v12, v9 - v13, v13);
                }

                --v11;
                while(v11 >= 0) {
                    if((this.mOldItemStatuses[((Snake)v10).x + v11] & 31) == 2) {
                        v15.onChanged(((Snake)v10).x + v11, 1, this.mCallback.getChangePayload(((Snake)v10).x + v11, ((Snake)v10).y + v11));
                    }

                    --v11;
                }

                v1 = ((Snake)v10).x;
                v9 = ((Snake)v10).y;
            }

            v15.dispatchLastEvent();
        }

        public void dispatchUpdatesTo(Adapter arg2) {
            this.dispatchUpdatesTo(new AdapterListUpdateCallback(arg2));
        }

        private void findAddition(int arg3, int arg4, int arg5) {
            if(this.mOldItemStatuses[arg3 - 1] != 0) {
                return;
            }

            this.findMatchingItem(arg3, arg4, arg5, false);
        }

        private boolean findMatchingItem(int arg9, int arg10, int arg11, boolean arg12) {
            int v1;
            int v0;
            if(arg12) {
                --arg10;
                v0 = arg9;
                v1 = arg10;
            }
            else {
                v0 = arg9 - 1;
                v1 = v0;
            }

            while(arg11 >= 0) {
                Object v2 = this.mSnakes.get(arg11);
                int v3 = ((Snake)v2).x + ((Snake)v2).size;
                int v4 = ((Snake)v2).y + ((Snake)v2).size;
                int v5 = 4;
                if(arg12) {
                    --v0;
                    while(v0 >= v3) {
                        if(this.mCallback.areItemsTheSame(v0, v1)) {
                            if(this.mCallback.areContentsTheSame(v0, v1)) {
                                v5 = 8;
                            }

                            this.mNewItemStatuses[v1] = v0 << 5 | 16;
                            this.mOldItemStatuses[v0] = v1 << 5 | v5;
                            return 1;
                        }
                        else {
                            --v0;
                            continue;
                        }

                        break;
                    }
                }
                else {
                    --arg10;
                    while(arg10 >= v4) {
                        if(this.mCallback.areItemsTheSame(v1, arg10)) {
                            if(this.mCallback.areContentsTheSame(v1, arg10)) {
                                v5 = 8;
                            }

                            --arg9;
                            this.mOldItemStatuses[arg9] = arg10 << 5 | 16;
                            this.mNewItemStatuses[arg10] = arg9 << 5 | v5;
                            return 1;
                        }

                        --arg10;
                    }
                }

                v0 = ((Snake)v2).x;
                arg10 = ((Snake)v2).y;
                --arg11;
            }

            return 0;
        }

        private void findMatchingItems() {
            int v0 = this.mOldListSize;
            int v1 = this.mNewListSize;
            int v2;
            for(v2 = this.mSnakes.size() - 1; v2 >= 0; --v2) {
                Object v4 = this.mSnakes.get(v2);
                int v5 = ((Snake)v4).x + ((Snake)v4).size;
                int v6 = ((Snake)v4).y + ((Snake)v4).size;
                if(this.mDetectMoves) {
                    while(v0 > v5) {
                        this.findAddition(v0, v1, v2);
                        --v0;
                    }

                    while(v1 > v6) {
                        this.findRemoval(v0, v1, v2);
                        --v1;
                    }
                }

                for(v0 = 0; v0 < ((Snake)v4).size; ++v0) {
                    v1 = ((Snake)v4).x + v0;
                    v5 = ((Snake)v4).y + v0;
                    v6 = this.mCallback.areContentsTheSame(v1, v5) ? 1 : 2;
                    this.mOldItemStatuses[v1] = v5 << 5 | v6;
                    this.mNewItemStatuses[v5] = v1 << 5 | v6;
                }

                v0 = ((Snake)v4).x;
                v1 = ((Snake)v4).y;
            }
        }

        private void findRemoval(int arg3, int arg4, int arg5) {
            if(this.mNewItemStatuses[arg4 - 1] != 0) {
                return;
            }

            this.findMatchingItem(arg3, arg4, arg5, true);
        }

        List getSnakes() {
            return this.mSnakes;
        }

        private static PostponedUpdate removePostponedUpdate(List arg5, int arg6, boolean arg7) {
            Object v2;
            int v0;
            for(v0 = arg5.size() - 1; v0 >= 0; --v0) {
                v2 = arg5.get(v0);
                if(((PostponedUpdate)v2).posInOwnerList == arg6 && ((PostponedUpdate)v2).removal == arg7) {
                    arg5.remove(v0);
                    goto label_10;
                }
            }

            return null;
        label_10:
            while(v0 < arg5.size()) {
                Object v6 = arg5.get(v0);
                int v3 = ((PostponedUpdate)v6).currentPos;
                int v4 = arg7 ? 1 : -1;
                ((PostponedUpdate)v6).currentPos = v3 + v4;
                ++v0;
            }

            return ((PostponedUpdate)v2);
        }
    }

    public abstract class ItemCallback {
        public ItemCallback() {
            super();
        }

        public abstract boolean areContentsTheSame(Object arg1, Object arg2);

        public abstract boolean areItemsTheSame(Object arg1, Object arg2);

        public Object getChangePayload(Object arg1, Object arg2) {
            return null;
        }
    }

    class PostponedUpdate {
        int currentPos;
        int posInOwnerList;
        boolean removal;

        public PostponedUpdate(int arg1, int arg2, boolean arg3) {
            super();
            this.posInOwnerList = arg1;
            this.currentPos = arg2;
            this.removal = arg3;
        }
    }

    class Range {
        int newListEnd;
        int newListStart;
        int oldListEnd;
        int oldListStart;

        public Range(int arg1, int arg2, int arg3, int arg4) {
            super();
            this.oldListStart = arg1;
            this.oldListEnd = arg2;
            this.newListStart = arg3;
            this.newListEnd = arg4;
        }

        public Range() {
            super();
        }
    }

    class Snake {
        boolean removal;
        boolean reverse;
        int size;
        int x;
        int y;

        Snake() {
            super();
        }
    }

    private static final Comparator SNAKE_COMPARATOR;

    static {
        DiffUtil.SNAKE_COMPARATOR = new org.telegram.messenger.support.util.DiffUtil$1();
    }

    private DiffUtil() {
        super();
    }

    public static DiffResult calculateDiff(Callback arg1) {
        return DiffUtil.calculateDiff(arg1, true);
    }

    public static DiffResult calculateDiff(Callback arg15, boolean arg16) {
        int v6_2;
        int v7;
        Object v6_1;
        int v0 = arg15.getOldListSize();
        int v1 = arg15.getNewListSize();
        ArrayList v4 = new ArrayList();
        ArrayList v2 = new ArrayList();
        ((List)v2).add(new Range(0, v0, 0, v1));
        v0 = Math.abs(v0 - v1) + (v0 + v1);
        v1 = v0 * 2;
        int[] v13 = new int[v1];
        int[] v1_1 = new int[v1];
        ArrayList v3 = new ArrayList();
        while(!((List)v2).isEmpty()) {
            Object v14 = ((List)v2).remove(((List)v2).size() - 1);
            Snake v5 = DiffUtil.diffPartial(arg15, ((Range)v14).oldListStart, ((Range)v14).oldListEnd, ((Range)v14).newListStart, ((Range)v14).newListEnd, v13, v1_1, v0);
            if(v5 != null) {
                if(v5.size > 0) {
                    ((List)v4).add(v5);
                }

                v5.x += ((Range)v14).oldListStart;
                v5.y += ((Range)v14).newListStart;
                if(((List)v3).isEmpty()) {
                    Range v6 = new Range();
                }
                else {
                    v6_1 = ((List)v3).remove(((List)v3).size() - 1);
                }

                ((Range)v6_1).oldListStart = ((Range)v14).oldListStart;
                ((Range)v6_1).newListStart = ((Range)v14).newListStart;
                if(v5.reverse) {
                    v7 = v5.x;
                    goto label_61;
                }
                else if(v5.removal) {
                    v7 = v5.x - 1;
                label_61:
                    ((Range)v6_1).oldListEnd = v7;
                    v7 = v5.y;
                }
                else {
                    ((Range)v6_1).oldListEnd = v5.x;
                    v7 = v5.y - 1;
                }

                ((Range)v6_1).newListEnd = v7;
                ((List)v2).add(v6_1);
                if(!v5.reverse) {
                    v6_2 = v5.x + v5.size;
                label_97:
                    ((Range)v14).oldListStart = v6_2;
                    v6_2 = v5.y + v5.size;
                }
                else if(v5.removal) {
                    v6_2 = v5.x + v5.size + 1;
                    goto label_97;
                }
                else {
                    ((Range)v14).oldListStart = v5.x + v5.size;
                    v6_2 = v5.y + v5.size + 1;
                }

                ((Range)v14).newListStart = v6_2;
                ((List)v2).add(v14);
                continue;
            }

            ((List)v3).add(v14);
        }

        Collections.sort(((List)v4), DiffUtil.SNAKE_COMPARATOR);
        return new DiffResult(arg15, ((List)v4), v13, v1_1, arg16);
    }

    private static Snake diffPartial(Callback arg21, int arg22, int arg23, int arg24, int arg25, int[] arg26, int[] arg27, int arg28) {
        Snake v0_1;
        int v20;
        int v19;
        int v18;
        boolean v12;
        Callback v0 = arg21;
        int[] v3 = arg26;
        int[] v4 = arg27;
        int v5 = arg23 - arg22;
        int v6 = arg25 - arg24;
        int v7 = 1;
        if(v5 >= 1) {
            if(v6 < 1) {
            }
            else {
                int v8 = v5 - v6;
                int v9 = (v5 + v6 + 1) / 2;
                int v10 = arg28 - v9 - 1;
                int v11 = arg28 + v9 + 1;
                Arrays.fill(v3, v10, v11, 0);
                Arrays.fill(v4, v10 + v8, v11 + v8, v5);
                v10 = v8 % 2 != 0 ? 1 : 0;
                v11 = 0;
                while(v11 <= v9) {
                    int v13 = -v11;
                    int v14 = v13;
                    while(v14 <= v11) {
                        if(v14 != v13) {
                            if(v14 != v11) {
                                int v15 = arg28 + v14;
                                if(v3[v15 - 1] < v3[v15 + v7]) {
                                    goto label_48;
                                }
                            }

                            v7 = v3[arg28 + v14 - 1] + 1;
                            v12 = true;
                        }
                        else {
                        label_48:
                            v7 = v3[arg28 + v14 + 1];
                            v12 = false;
                        }

                        v18 = v9;
                        v9 = v7 - v14;
                        while(true) {
                            if(v7 >= v5) {
                                break;
                            }
                            else if(v9 < v6) {
                                v19 = v5;
                                v20 = v6;
                                if(v0.areItemsTheSame(arg22 + v7, arg24 + v9)) {
                                    ++v7;
                                    ++v9;
                                    v5 = v19;
                                    v6 = v20;
                                    continue;
                                }
                            }
                            else {
                                break;
                            }

                            goto label_71;
                        }

                        v19 = v5;
                        v20 = v6;
                    label_71:
                        v5 = arg28 + v14;
                        v3[v5] = v7;
                        if(v10 != 0 && v14 >= v8 - v11 + 1 && v14 <= v8 + v11 - 1 && v3[v5] >= v4[v5]) {
                            v0_1 = new Snake();
                            v0_1.x = v4[v5];
                            v0_1.y = v0_1.x - v14;
                            v0_1.size = v3[v5] - v4[v5];
                            v0_1.removal = v12;
                            v0_1.reverse = false;
                            return v0_1;
                        }

                        v14 += 2;
                        v9 = v18;
                        v5 = v19;
                        v6 = v20;
                        v7 = 1;
                    }

                    v19 = v5;
                    v20 = v6;
                    v18 = v9;
                    for(v6 = v13; v6 <= v11; v6 += 2) {
                        v7 = v6 + v8;
                        if(v7 != v11 + v8) {
                            if(v7 != v13 + v8) {
                                v9 = arg28 + v7;
                                if(v4[v9 - 1] < v4[v9 + 1]) {
                                    goto label_134;
                                }
                            }

                            v9 = v4[arg28 + v7 + 1] - 1;
                            v12 = true;
                        }
                        else {
                        label_134:
                            v9 = v4[arg28 + v7 - 1];
                            v12 = false;
                        }

                        for(v14 = v9 - v7; v9 > 0; --v14) {
                            if(v14 <= 0) {
                                break;
                            }

                            if(!v0.areItemsTheSame(arg22 + v9 - 1, arg24 + v14 - 1)) {
                                break;
                            }

                            --v9;
                        }

                        int v1 = arg28 + v7;
                        v4[v1] = v9;
                        if(v10 == 0 && v7 >= v13 && v7 <= v11 && v3[v1] >= v4[v1]) {
                            v0_1 = new Snake();
                            v0_1.x = v4[v1];
                            v0_1.y = v0_1.x - v7;
                            v0_1.size = v3[v1] - v4[v1];
                            v0_1.removal = v12;
                            v0_1.reverse = true;
                            return v0_1;
                        }
                    }

                    ++v11;
                    v9 = v18;
                    v5 = v19;
                    v6 = v20;
                    v7 = 1;
                }

                throw new IllegalStateException("DiffUtil hit an unexpected case while trying to calculate the optimal path. Please make sure your data is not changing during the diff calculation.");
            }
        }

        return null;
    }
}


package org.telegram.ui.Adapters;

import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Comparator;
import org.telegram.tgnet.TLRPC$User;

public final class -$$Lambda$MentionsAdapter$RtUwcbbmysCllxWaHytbPO9oFzY implements Comparator {
    public -$$Lambda$MentionsAdapter$RtUwcbbmysCllxWaHytbPO9oFzY(SparseArray arg1, ArrayList arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final int compare(Object arg3, Object arg4) {
        return MentionsAdapter.lambda$searchUsernameOrHashtag$5(this.f$0, this.f$1, ((User)arg3), ((User)arg4));
    }
}


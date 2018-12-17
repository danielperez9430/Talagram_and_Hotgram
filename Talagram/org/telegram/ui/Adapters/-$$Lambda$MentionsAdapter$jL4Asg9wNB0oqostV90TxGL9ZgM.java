package org.telegram.ui.Adapters;

import java.util.Comparator;
import org.telegram.messenger.EmojiSuggestion;

public final class -$$Lambda$MentionsAdapter$jL4Asg9wNB0oqostV90TxGL9ZgM implements Comparator {
    static {
        -$$Lambda$MentionsAdapter$jL4Asg9wNB0oqostV90TxGL9ZgM.INSTANCE = new -$$Lambda$MentionsAdapter$jL4Asg9wNB0oqostV90TxGL9ZgM();
    }

    private -$$Lambda$MentionsAdapter$jL4Asg9wNB0oqostV90TxGL9ZgM() {
        super();
    }

    public final int compare(Object arg1, Object arg2) {
        return MentionsAdapter.lambda$searchUsernameOrHashtag$6(((EmojiSuggestion)arg1), ((EmojiSuggestion)arg2));
    }
}


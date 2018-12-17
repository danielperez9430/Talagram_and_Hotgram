package com.google.android.gms.location.places;

import android.text.style.CharacterStyle;
import com.google.android.gms.common.data.Freezable;
import java.util.List;

public interface AutocompletePrediction extends Freezable {
    CharSequence getFullText(CharacterStyle arg1);

    String getPlaceId();

    List getPlaceTypes();

    CharSequence getPrimaryText(CharacterStyle arg1);

    CharSequence getSecondaryText(CharacterStyle arg1);
}


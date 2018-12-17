package com.google.android.gms.location.places.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.R$id;
import com.google.android.gms.location.places.R$layout;
import com.google.android.gms.maps.model.LatLngBounds;

public class SupportPlaceAutocompleteFragment extends Fragment {
    private View zzhn;
    private View zzho;
    private EditText zzhp;
    private boolean zzhq;
    private LatLngBounds zzhr;
    private AutocompleteFilter zzhs;
    private PlaceSelectionListener zzht;

    public SupportPlaceAutocompleteFragment() {
        super();
    }

    public void onActivityResult(int arg3, int arg4, Intent arg5) {
        this.zzhq = false;
        if(arg3 == 30421) {
            if(arg4 == -1) {
                Place v0 = PlaceAutocomplete.getPlace(this.getActivity(), arg5);
                if(this.zzht != null) {
                    this.zzht.onPlaceSelected(v0);
                }

                this.setText(v0.getName().toString());
            }
            else {
                if(arg4 != 2) {
                    goto label_24;
                }

                Status v0_1 = PlaceAutocomplete.getStatus(this.getActivity(), arg5);
                if(this.zzht == null) {
                    goto label_24;
                }

                this.zzht.onError(v0_1);
            }
        }

    label_24:
        super.onActivityResult(arg3, arg4, arg5);
    }

    public View onCreateView(LayoutInflater arg2, ViewGroup arg3, Bundle arg4) {
        View v2 = arg2.inflate(layout.place_autocomplete_fragment, arg3, false);
        this.zzhn = v2.findViewById(id.place_autocomplete_search_button);
        this.zzho = v2.findViewById(id.place_autocomplete_clear_button);
        this.zzhp = v2.findViewById(id.place_autocomplete_search_input);
        zzf v3 = new zzf(this);
        this.zzhn.setOnClickListener(((View$OnClickListener)v3));
        this.zzhp.setOnClickListener(((View$OnClickListener)v3));
        this.zzho.setOnClickListener(new zzg(this));
        this.zzaj();
        return v2;
    }

    public void onDestroyView() {
        this.zzhn = null;
        this.zzho = null;
        this.zzhp = null;
        super.onDestroyView();
    }

    public void setBoundsBias(LatLngBounds arg1) {
        this.zzhr = arg1;
    }

    public void setFilter(AutocompleteFilter arg1) {
        this.zzhs = arg1;
    }

    public void setHint(CharSequence arg2) {
        this.zzhp.setHint(arg2);
        this.zzhn.setContentDescription(arg2);
    }

    public void setOnPlaceSelectedListener(PlaceSelectionListener arg1) {
        this.zzht = arg1;
    }

    public void setText(CharSequence arg2) {
        this.zzhp.setText(arg2);
        this.zzaj();
    }

    private final void zzaj() {
        int v0 = this.zzhp.getText().toString().isEmpty() ^ 1;
        View v1 = this.zzho;
        v0 = v0 != 0 ? 0 : 8;
        v1.setVisibility(v0);
    }

    private final void zzak() {
        int v2;
        int v0 = -1;
        try {
            Intent v1_2 = new IntentBuilder(2).setBoundsBias(this.zzhr).setFilter(this.zzhs).zzh(this.zzhp.getText().toString()).zzg(1).build(this.getActivity());
            this.zzhq = true;
            this.startActivityForResult(v1_2, 30421);
            v2 = -1;
            goto label_29;
        }
        catch(GooglePlayServicesNotAvailableException v1) {
            v2 = v1.errorCode;
        }
        catch(GooglePlayServicesRepairableException v1_1) {
            v2 = v1_1.getConnectionStatusCode();
        }

        Log.e("Places", "Could not open autocomplete activity", ((Throwable)v1));
    label_29:
        if(v2 != v0) {
            GoogleApiAvailability.getInstance().showErrorDialogFragment(this.getActivity(), v2, 30422);
        }
    }

    static boolean zzb(SupportPlaceAutocompleteFragment arg0) {
        return arg0.zzhq;
    }

    static void zzc(SupportPlaceAutocompleteFragment arg0) {
        arg0.zzak();
    }
}


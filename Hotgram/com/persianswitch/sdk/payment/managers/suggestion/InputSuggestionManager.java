package com.persianswitch.sdk.payment.managers.suggestion;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View$OnTouchListener;
import android.view.View;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.AdapterView;
import com.persianswitch.sdk.base.widgets.edittext.ApLabelAutoComplete;
import com.persianswitch.sdk.payment.model.UserCard;
import com.persianswitch.sdk.payment.payment.CardSuggestAdapter;
import com.persianswitch.sdk.payment.repo.CardRepo;

public final class InputSuggestionManager {
    public InputSuggestionManager() {
        super();
    }

    public static void a(ApLabelAutoComplete arg3, UserCard arg4, SuggestionListener arg5) {
        if(arg3 == null) {
            return;
        }

        Context v0 = arg3.getContext();
        arg3.getInnerInput().setAdapter(new CardSuggestAdapter(v0, new CardRepo(v0).a()));
        arg3.getInnerInput().setFilterEnabled(false);
        if(arg4 != null) {
            arg3.setText(arg4.d());
            if(arg5 != null) {
                arg5.a(arg4);
            }

            int v4 = arg4.e() > 0 ? arg4.e() : -1;
            arg3.setStartImage(v4);
            arg3.getInnerInput().setOnTouchListener(new View$OnTouchListener(arg3, arg5) {
                public boolean onTouch(View arg1, MotionEvent arg2) {
                    if(this.a.getText().length() >= 16) {
                        if(this.b != null) {
                            this.b.a();
                        }

                        this.a.setText("");
                        this.a.setStartImage(-1);
                        this.a.getInnerInput().b();
                        return 1;
                    }

                    return 0;
                }
            });
        }

        arg3.getInnerInput().setOnItemClickListener(new AdapterView$OnItemClickListener(arg3, arg5) {
            public void onItemClick(AdapterView arg1, View arg2, int arg3, long arg4) {
                ApLabelAutoComplete v2;
                Object v1 = arg1.getItemAtPosition(arg3);
                this.a.setText(((UserCard)v1).d());
                this.a.getInnerInput().setOnTouchListener(new View$OnTouchListener() {
                    public boolean onTouch(View arg1, MotionEvent arg2) {
                        if(this.a.a.getText().length() >= 16) {
                            if(this.a.b != null) {
                                this.a.b.a();
                            }

                            this.a.a.setText("");
                            this.a.a.setStartImage(-1);
                            this.a.a.getInnerInput().b();
                            return 1;
                        }

                        return 0;
                    }
                });
                if(((UserCard)v1).e() > 0) {
                    v2 = this.a;
                    arg3 = ((UserCard)v1).e();
                }
                else {
                    v2 = this.a;
                    arg3 = -1;
                }

                v2.setStartImage(arg3);
                if(this.b != null) {
                    this.b.a(v1);
                }
            }
        });
    }

    public static void a(ApLabelAutoComplete arg3, boolean arg4, SuggestionListener arg5) {
        Context v0 = arg3.getContext();
        arg3.getInnerInput().setAdapter(new CardSuggestAdapter(v0, new CardRepo(v0).a()));
        arg3.getInnerInput().setFilterEnabled(false);
        if(arg4) {
            arg3.setText(null);
            arg5.a();
        }
    }
}


package com.example.vishwanath.DataBinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.vishwanath.testprojecthttpscenario.BR;

/**
 * Created by android-linux-mv on 8/11/17.
 */

public class TravelGuidance extends BaseObservable {
    String fromcentral;

    public TravelGuidance(String fromcentral) {
        this.fromcentral = fromcentral;
    }

    public void setFromcentral(String fromcentral) {
        this.fromcentral = fromcentral;
        notifyPropertyChanged(BR.fromcentral);
    }

    @Bindable
    public String getFromcentral() {
        return fromcentral;
    }
}

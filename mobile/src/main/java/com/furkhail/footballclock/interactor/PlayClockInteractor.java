package com.furkhail.footballclock.interactor;

import android.util.Log;

import java.text.DecimalFormat;

public class PlayClockInteractor extends ClockInteractor{
    public PlayClockInteractor(Listener listener) {
        super(listener);
    }

    @Override
    public void updateTimer(long milis) {
        DecimalFormat df=new DecimalFormat("00");
        String s = df.format(Math.ceil((double)milis / 1000));
        Log.i("tag", milis + " - " + s);
        mListener.updateTimer(":"+s);
    }
}

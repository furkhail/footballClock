package com.furkhail.footballclock.interactor;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameClockInteractor extends ClockInteractor{

    public GameClockInteractor(Listener listener) {
        super(listener);
    }



    @Override
    public void updateTimer(long milis) {
        Date date = new Date(milis);
        DateFormat formatter = new SimpleDateFormat("mm:ss");
        String s = formatter.format(date);
        Log.i("tag", milis + " - " + s);
        mListener.updateTimer(s);
    }
}

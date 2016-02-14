package com.furkhail.footballclock.interactor;

import android.util.Log;

import java.text.DecimalFormat;

import com.furkhail.footballclock.model.PlayClock;

public class PlayClockInteractor implements PlayClock.Listener{
    

    public interface Listener {
        void updateTimer(String timer);
    }

    private Listener mListener;
    private PlayClock mHandler;

    public PlayClockInteractor(Listener listener) {
        mListener = listener;
        mHandler = new PlayClock(this);
    }

    public void startCount(int seconds){
        mHandler.startCount(seconds);
    }

    public void stopCount(){
        mHandler.stopCount();
    }

    @Override
    public void updateTimer(long milis) {
        DecimalFormat df=new DecimalFormat("00");
        String s = df.format(Math.ceil((double)milis / 1000));
        Log.i("tag", milis + " - " + s);
        mListener.updateTimer(s);
    }
}

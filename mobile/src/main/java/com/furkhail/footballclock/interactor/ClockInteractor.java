package com.furkhail.footballclock.interactor;

import com.furkhail.footballclock.model.Clock;

public abstract class ClockInteractor implements Clock.Listener{
    public interface Listener {
        void updateTimer(String timer);
    }

    protected Listener mListener;
    private Clock mClock;

    public ClockInteractor(Listener listener) {
        mListener = listener;
        mClock = new Clock(this);
    }

    public void startCount(long seconds){
        mClock.startCount(seconds);
    }

    public long pauseCount() {
        return mClock.pauseCount();
    }

    public void stopCount(){
        mClock.stopCount();
    }

    public abstract void updateTimer(long milis);
}

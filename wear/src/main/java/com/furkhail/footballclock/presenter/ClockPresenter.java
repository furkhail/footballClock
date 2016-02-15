package com.furkhail.footballclock.presenter;

import com.furkhail.footballclock.interactor.ClockInteractor;
import com.furkhail.footballclock.model.ClockVibrator;

public abstract class ClockPresenter implements ClockInteractor.Listener{
    public interface Listener {
        void updateTimer(String timer);
        void showLabel(int label);
    }

    protected Listener mListener;
    protected ClockInteractor mInteractor;
    protected ClockVibrator mVibrator;

    @Override
    public void updateTimer(String timer) {
        mListener.updateTimer(timer);
        mVibrator.checkVibration(timer);
    }

    public void stopCount(){
        mInteractor.stopCount();
    }
}

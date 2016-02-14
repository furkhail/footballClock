package com.furkhail.footballclock.presenter;

import android.content.Context;

import com.furkhail.footballclock.interactor.PlayClockInteractor;
import com.furkhail.footballclock.model.PlayClockVibrator;
import com.furkhail.footballclock.view.fragment.PlayClockFragment;

public class PlayClockPresenter implements PlayClockInteractor.Listener {
    public interface Listener {
        void updateTimer(String timer);
        void showLabel(int label);
    }

    private Listener mListener;
    private PlayClockInteractor mInteractor;
    private PlayClockVibrator mVibrator;

    public PlayClockPresenter(Listener listener, Context c) {
        mListener = listener;
        mInteractor = new PlayClockInteractor(this);
        mVibrator = new PlayClockVibrator(c);
    }

    @Override
    public void updateTimer(String seconds) {
        mListener.updateTimer(":"+seconds);
        mVibrator.checkVibration(seconds);
    }

    /**
     * Metodos llamados desde la vista
     */
    public void swipeLeft(){
//        updateTimer("40");
        mInteractor.startCount(40);
        mListener.showLabel(PlayClockFragment.LABEL_LEFT);
    }

    public void swipeRight(){
//        updateTimer("25");
        mInteractor.startCount(25);
        mListener.showLabel(PlayClockFragment.LABEL_RIGHT);
    }

    public void tap(){
        mInteractor.stopCount();
        mListener.showLabel(PlayClockFragment.LABEL_STOP);
    }
}

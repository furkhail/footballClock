package com.furkhail.footballclock.presenter;

import com.furkhail.footballclock.interactor.PlayClockInteractor;
import com.furkhail.footballclock.model.ClockVibrator;
import com.furkhail.footballclock.view.fragment.PlayClockFragment;

public class PlayClockPresenter extends ClockPresenter {

    public PlayClockPresenter(Listener listener) {
        mListener = listener;
        mInteractor = new PlayClockInteractor(this);
        mVibrator = new ClockVibrator(ClockVibrator.PLAY_CLOCK_VIBRATION);
    }

    /**
     * Metodos llamados desde la vista
     */
    public void swipeLeft(){
//        updateTimer("40");
        mInteractor.startCount(40000);
        mListener.showLabel(PlayClockFragment.LABEL_LEFT);
    }

    public void swipeRight(){
//        updateTimer("25");
        mInteractor.startCount(25000);
        mListener.showLabel(PlayClockFragment.LABEL_RIGHT);
    }

    public void tap(){
        stopCount();
        mListener.showLabel(PlayClockFragment.LABEL_STOP);
    }
}

package com.furkhail.footballclock.presenter;

import com.furkhail.footballclock.interactor.GameClockInteractor;
import com.furkhail.footballclock.model.ClockVibrator;

public class GameClockPresenter extends ClockPresenter{
    private static final String TAG = "GameClockPresenter";
    private boolean timeSetting=false;
    private boolean running=false;
    private long millisLeft=720000;

    public interface Listener extends ClockPresenter.Listener{
        void setTimer();
    }

    public GameClockPresenter(Listener listener) {
        mListener = listener;
        mInteractor = new GameClockInteractor(this);
        mVibrator = new ClockVibrator(ClockVibrator.GAME_CLOCK_VIBRATION);
    }

    public void tap(){
        if(running) {
            millisLeft = mInteractor.pauseCount();
            running = false;
        }
        else {
            mInteractor.startCount(millisLeft);
            running = true;
        }
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isTimeSetting() {
        return timeSetting;
    }

    public void timeSet(String minutes, String seconds){
        timeSetting=false;
        millisLeft = (Integer.parseInt(minutes) * 60 + Integer.parseInt(seconds)) * 1000;
    }

    public void reset() {
        running=false;
        millisLeft = 720000;
        updateTimer("12:00");
    }

    public void setTimer() {
        timeSetting=true;
        ((GameClockPresenter.Listener)mListener).setTimer();
    }
}

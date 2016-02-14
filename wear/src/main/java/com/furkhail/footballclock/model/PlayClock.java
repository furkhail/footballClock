package com.furkhail.footballclock.model;

import com.furkhail.footballclock.util.MyCountDownTimer;

public class PlayClock {
    public interface Listener {
        void updateTimer(long milis);
    }

    private MyCountDownTimer timer;
    private Listener mListener;

    public PlayClock(Listener listener) {
        mListener = listener;
    }

    public void startCount(int seconds){
        stopCount();
        timer=new MyCountDownTimer(seconds*1000, 1000) {
            public void onTick(long millisUntilFinished) {
                mListener.updateTimer(millisUntilFinished);
            }

            public void onFinish() {
                mListener.updateTimer(0);
            }
        };
        timer.start();
    }

    public void stopCount(){
        if(timer!=null){
            timer.cancel();
            timer = null;
        }
    }

}

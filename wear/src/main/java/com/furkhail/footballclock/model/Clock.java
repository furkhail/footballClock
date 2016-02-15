package com.furkhail.footballclock.model;

import com.furkhail.footballclock.util.MyCountDownTimer;

public class Clock {
    public interface Listener {
        void updateTimer(long milis);
    }

    private MyCountDownTimer timer;
    private Listener mListener;

    public Clock(Listener listener) {
        mListener = listener;
    }

    public void startCount(long millis){
        stopCount();
        timer=new MyCountDownTimer(millis, 1000) {
            public void onTick(long millisUntilFinished) {
                mListener.updateTimer(millisUntilFinished);
            }

            public void onFinish() {
                mListener.updateTimer(0);
            }
        };
        timer.start();
    }

    public long pauseCount() {
        long millisLeft=0;
        if (timer != null) {
            millisLeft=timer.millisLeft();
            timer.cancel();
            timer = null;
        }
        return millisLeft;
    }

    public void stopCount(){
        if(timer!=null){
            timer.cancel();
            timer = null;
        }
    }


}

package com.furkhail.footballclock.model;

import android.content.Context;
import android.os.Vibrator;

public class PlayClockVibrator {

    private static final int SHORT_VIBRATE=300;
    private static final int LONG_VIBRATE=1000;

    private static final String[] VIBRATE_TIMES={"05","10","15"};

    private Vibrator vibrator;

    public PlayClockVibrator(Context c) {
        vibrator = (Vibrator) c.getSystemService(Context.VIBRATOR_SERVICE);
    }

    private boolean hasVibrator() {
        return vibrator.hasVibrator();
    }

    public void vibrateShort(){
        if(hasVibrator()) {
            vibrator.vibrate(SHORT_VIBRATE);
        }
    }

    public void vibrateLong(){
        if(hasVibrator()) {
            vibrator.vibrate(LONG_VIBRATE);
        }
    }

    public void checkVibration(String seconds){
        if("00".equals(seconds)){
            vibrateLong();
        }
        else {
            for (String i : VIBRATE_TIMES) {
                if (i.equals(seconds)) {
                    vibrateShort();
                }
            }
        }
    }
}

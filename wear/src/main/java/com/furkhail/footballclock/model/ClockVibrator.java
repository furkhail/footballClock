package com.furkhail.footballclock.model;

import android.content.Context;
import android.os.Vibrator;

import com.furkhail.footballclock.App;

public class ClockVibrator {

    private static final int SHORT_VIBRATE=300;
    private static final int LONG_VIBRATE=1000;

    public static final int PLAY_CLOCK_VIBRATION = 0;
    public static final int GAME_CLOCK_VIBRATION = 1;

    private static final String[] PLAY_CLOCK_VIBRATE_TIMES ={"05","10","15"};
    private static final String[] GAME_CLOCK_VIBRATE_TIMES ={"02:00","01:00","00:00"};

    private Vibrator vibrator;
    private String[] vibrateTimes;

    public ClockVibrator(int type) {
        if(App.getAppContext()!=null) {
            vibrator = (Vibrator) App.getAppContext().getSystemService(Context.VIBRATOR_SERVICE);
        }
        vibrateTimes = type == PLAY_CLOCK_VIBRATION ? PLAY_CLOCK_VIBRATE_TIMES : GAME_CLOCK_VIBRATE_TIMES;
    }

    private boolean hasVibrator() {
        return vibrator!=null && vibrator.hasVibrator();
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

    public void checkVibration(String timer){
        if("00".equals(timer) || "00:00".equals(timer)){
            vibrateLong();
        }
        else {
            for (String i : vibrateTimes) {
                if (i.equals(timer)) {
                    vibrateShort();
                }
            }
        }
    }
}

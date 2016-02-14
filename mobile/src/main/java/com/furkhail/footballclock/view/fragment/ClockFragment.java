package com.furkhail.footballclock.view.fragment;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public abstract class ClockFragment extends BaseFragment implements
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    private static final String TAG = "ClockFragment";

    public abstract boolean onFling(MotionEvent e1, MotionEvent e2,
                           float velocityX, float velocityY);

    public abstract void onLongPress(MotionEvent event);

    public abstract boolean onDoubleTap(MotionEvent event);

    public abstract boolean onSingleTapConfirmed(MotionEvent event);

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
//        Log.d(TAG, "onSingleTapUp: " + event.toString());
        return true;
    }

    @Override
    public boolean onDown(MotionEvent event) {
//        Log.d(TAG,"onDown: " + event.toString());
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
//        Log.d(TAG, "onScroll: " + e1.toString()+e2.toString());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
//        Log.d(TAG, "onShowPress: " + event.toString());
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
//        Log.d(TAG, "onDoubleTapEvent: " + event.toString());
        return true;
    }
}

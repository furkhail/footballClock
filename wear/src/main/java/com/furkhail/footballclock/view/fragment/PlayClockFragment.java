package com.furkhail.footballclock.view.fragment;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.furkhail.footballclock.R;
import com.furkhail.footballclock.presenter.ClockPresenter;
import com.furkhail.footballclock.presenter.PlayClockPresenter;
import com.furkhail.footballclock.view.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlayClockFragment extends ClockFragment implements
        ClockPresenter.Listener {

    private static final String TAG = "PlayClockFragment";

    public static final int LABEL_LEFT=0;
    public static final int LABEL_RIGHT=1;
    public static final int LABEL_STOP=2;

    @Bind(R.id.txtPlayClock) TextView txtPlayClock;
    @Bind(R.id.lblForty) TextView lblForty;
    @Bind(R.id.lblTwentyfive) TextView lblTwentyfive;
    @Bind(R.id.lblStop) TextView lblStop;

    PlayClockPresenter mPresenter;
    GestureDetectorCompat mDetector;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_play_clock, container, false);
        ButterKnife.bind(this, rootView);
        mDetector = new GestureDetectorCompat(mContext, this);
        mDetector.setOnDoubleTapListener(this);
        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mDetector.onTouchEvent(event);
            }
        });
        mPresenter = new PlayClockPresenter(this);
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.stopCount();
    }

    private void changeLabels(int label){
        lblForty.setVisibility(label == LABEL_LEFT ? View.VISIBLE : View.INVISIBLE);
        lblTwentyfive.setVisibility(label == LABEL_RIGHT ? View.VISIBLE : View.INVISIBLE);
        lblStop.setVisibility(label == LABEL_STOP ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void updateTimer(String timer) {
        if(mContext!=null) {
            txtPlayClock.setText(timer);
        }
    }

    @Override
    public void showLabel(int label) {
        changeLabels(label);
    }

    /**
     * Movimientos detectados
     */

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2,
                           float velocityX, float velocityY) {
        final int SWIPE_MIN_DISTANCE = 200;
        final int SWIPE_THRESHOLD_VELOCITY = 200;
        try {
            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                mPresenter.swipeLeft();
            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                mPresenter.swipeRight();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent event) {
        if(mContext instanceof MainActivity) {
            mPresenter.tap();
            ((MainActivity) mContext).finish();
        }
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        Log.d(TAG, "onDoubleTap: " + event.toString());
        mPresenter.tap();
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        Log.d(TAG, "onSingleTapConfirmed: " + event.toString());
        return false;
    }
}

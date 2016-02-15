package com.furkhail.footballclock.view.fragment;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.furkhail.footballclock.R;
import com.furkhail.footballclock.presenter.GameClockPresenter;
import com.furkhail.footballclock.view.MainActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameClockFragment extends ClockFragment implements GameClockPresenter.Listener {

    @Bind(R.id.txtGameClockMinutes)TextView txtMinutes;
    @Bind(R.id.txtGameClockSeconds)TextView txtSeconds;

    GameClockPresenter mPresenter;
    GestureDetectorCompat mDetector;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_game_clock, container, false);
        ButterKnife.bind(this, rootView);
        mDetector = new GestureDetectorCompat(mContext, this);
//        mDetector.setOnDoubleTapListener(this);
        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mDetector.onTouchEvent(event);
            }
        });
        mPresenter = new GameClockPresenter(this);
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.stopCount();
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
        Log.i(TAG, "onLongPress: ");
        if (!mPresenter.isRunning()) {
            new MaterialDialog.Builder(mContext)
                    .positiveText(R.string.set)
                    .negativeText(R.string.reset)
                    .neutralText(R.string.exit)
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                            new MaterialDialog.Builder(mContext)
                                    .title(R.string.are_you_sure)
                                    .positiveText(R.string.set)
                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(MaterialDialog dialog, DialogAction which) {
                                            mPresenter.setTimer();
                                        }
                                    })
                                    .negativeText(android.R.string.cancel)
                                    .show();
                        }
                    })
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                            new MaterialDialog.Builder(mContext)
                                    .title(R.string.are_you_sure)
                                    .positiveText(R.string.reset)
                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(MaterialDialog dialog, DialogAction which) {
                                            mPresenter.reset();
                                        }
                                    })
                                    .negativeText(android.R.string.cancel)
                                    .show();
                        }
                    })
                    .onNeutral(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(MaterialDialog dialog, DialogAction which) {
                            new MaterialDialog.Builder(mContext)
                                    .title(R.string.are_you_sure)
                                    .positiveText(R.string.exit)
                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(MaterialDialog dialog, DialogAction which) {
                                            if(mContext instanceof MainActivity) {
                                                mPresenter.stopCount();
                                                ((MainActivity) mContext).finish();
                                            }
                                        }
                                    })
                                    .negativeText(android.R.string.cancel)
                                    .show();
                        }
                    })
                    .show();
        }
        else if(mPresenter.isTimeSetting()){
            timeSet();
        }
    }

    private void timeSet() {
        clearClickListeners();
        mPresenter.timeSet(txtMinutes.getText().toString(),txtSeconds.getText().toString());
        txtMinutes.clearAnimation();
        txtSeconds.clearAnimation();
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        Log.i(TAG, "onSingleTapConfirmed: ");
        if(!mPresenter.isTimeSetting()) {
            mPresenter.tap();
        }
        return true;
    }


    @Override
    public void updateTimer(String timer) {
        if(mContext!=null) {
            String[] s = timer.split(getString(R.string.time_separator));
            txtMinutes.setText(s[0]);
            txtSeconds.setText(s[1]);
        }
    }

    @Override
    public void showLabel(int label) {

    }

    @Override
    public void setTimer() {
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(50); //You can manage the blinking time with this parameter
        anim.setStartOffset(200);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);

        txtMinutes.startAnimation(anim);
        txtSeconds.startAnimation(anim);

        txtMinutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: minutes click");
                if(!mPresenter.isTimeSetting()) {
                    mPresenter.tap();
                }
                else {
                    ArrayList<Integer> minutes = new ArrayList<>();
                    for (int i = 12; i > 0; i--) {
                        minutes.add(i);
                    }
                    new MaterialDialog.Builder(mContext)
                            .items(minutes)
                            .itemsCallback(new MaterialDialog.ListCallback() {
                                @Override
                                public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                    if (Integer.parseInt(text.toString()) < 10) {
                                        text = "0" + text;
                                    }
                                    txtMinutes.setText(text);
                                }
                            })
                            .show();
                }
            }
        });
        txtMinutes.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.i(TAG, "onLongClick: minutes");
                timeSet();
                return false;
            }
        });
        txtSeconds.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.i(TAG, "onLongClick: seconds");
                timeSet();
                return false;
            }
        });

        txtSeconds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: seconds click");
                if(!mPresenter.isTimeSetting()) {
                    mPresenter.tap();
                }
                else {
                    ArrayList<Integer> seconds = new ArrayList<>();
                    for (int i = 60; i > 0; i--) {
                        seconds.add(i);
                    }
                    new MaterialDialog.Builder(mContext)
                            .items(seconds)
                            .itemsCallback(new MaterialDialog.ListCallback() {
                                @Override
                                public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                    if (Integer.parseInt(text.toString()) < 10) {
                                        text = "0" + text;
                                    }
                                    txtSeconds.setText(text);
                                }
                            })
                            .show();
                }
            }
        });

    }

    private static final String TAG = "GameClockFragment";
    private void clearClickListeners(){
//        txtMinutes.setOnLongClickListener(null);
//        txtSeconds.setOnLongClickListener(null);
        Log.i(TAG, "clearClickListeners: ");
        txtMinutes.setOnClickListener(null);
        txtSeconds.setOnClickListener(null);
        txtMinutes.setClickable(false);
        txtSeconds.setClickable(false);
//        txtMinutes.setFocusable(false);
//        txtSeconds.setFocusable(false);
//        txtMinutes.setFocusableInTouchMode(false);
//        txtSeconds.setFocusableInTouchMode(false);
//        txtMinutes.setEnabled(false);
//        txtSeconds.setEnabled(false);

    }
}

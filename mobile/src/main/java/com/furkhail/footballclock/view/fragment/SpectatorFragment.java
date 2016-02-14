package com.furkhail.footballclock.view.fragment;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.furkhail.footballclock.R;
import com.furkhail.footballclock.presenter.SpectatorPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SpectatorFragment extends BaseFragment implements SpectatorPresenter.Listener{
    private static final String TAG = "SpectatorFragment";

    @Bind(R.id.txtPlayClock) TextView txtPlayClock;
    @Bind(R.id.lblStop) TextView lblStop;

    @Bind(R.id.txtGameClockMinutes)TextView txtMinutes;
    @Bind(R.id.txtGameClockSeconds)TextView txtSeconds;

    SpectatorPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_spectator, container, false);
        ButterKnife.bind(this, rootView);
        mPresenter = new SpectatorPresenter(this);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.startListening();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.stopListening();
    }

    @Override
    public void setPlayClock(String timer) {
        if(mContext!=null) {
            txtPlayClock.setText(timer);
        }
    }

    @Override
    public void setGameClock(String timer) {
        if(mContext!=null) {
            String[] s = timer.split(getString(R.string.time_separator));
            txtMinutes.setText(s[0]);
            txtSeconds.setText(s[1]);
        }
    }

    @Override
    public void setStopLabel(boolean visible) {
        lblStop.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }
}

package com.furkhail.footballclock.presenter;

import com.furkhail.footballclock.interactor.SpectatorInteractor;

public class SpectatorPresenter implements SpectatorInteractor.Listener{
    public interface Listener{
        void setPlayClock(String timer);
        void setGameClock(String timer);
        void setStopLabel(boolean visible);
    }

    Listener mListener;
    SpectatorInteractor mInteractor;

    public SpectatorPresenter(Listener listener) {
        mListener = listener;
        mInteractor = new SpectatorInteractor(this);
    }

    @Override
    public void setPlayClock(String timer) {
        mListener.setPlayClock(timer);
    }

    @Override
    public void setGameClock(String timer) {
        mListener.setGameClock(timer);
    }

    @Override
    public void setStopLabel(boolean visible) {
        mListener.setStopLabel(visible);
    }

    public void startListening(){
        mInteractor.startListening();
    }

    public void stopListening(){
        mInteractor.stopListening();
    }
}

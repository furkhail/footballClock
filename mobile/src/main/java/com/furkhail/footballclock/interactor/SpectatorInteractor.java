package com.furkhail.footballclock.interactor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;

import com.furkhail.footballclock.App;

public class SpectatorInteractor {
    public interface Listener{
        void setPlayClock(String timer);
        void setGameClock(String timer);
        void setStopLabel(boolean visible);
    }

    Listener mListener;
    WifiP2pManager mManager;
    WifiP2pManager.Channel mChannel;
    BroadcastReceiver mReceiver;
    IntentFilter mIntentFilter;

    public SpectatorInteractor(Listener listener) {
        mListener = listener;
        Context c = App.getAppContext();
//        mManager = (WifiP2pManager) c.getSystemService(Context.WIFI_P2P_SERVICE);
//        mChannel = mManager.initialize(c, c.getMainLooper(), null);
//        mReceiver = new MyWiFiDirectBroadcastReceiver(mManager, mChannel);
//
//        mIntentFilter = new IntentFilter();
//        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
//        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
//        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
//        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
//
//        mManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {
//            @Override
//            public void onSuccess() {
//
//            }
//
//            @Override
//            public void onFailure(int reason) {
//
//            }
//        });
    }

    public void startListening(){
        App.getAppContext().registerReceiver(mReceiver, mIntentFilter);
    }

    public void stopListening(){
        App.getAppContext().unregisterReceiver(mReceiver);
    }
}

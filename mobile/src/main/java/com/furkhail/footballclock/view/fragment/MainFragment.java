package com.furkhail.footballclock.view.fragment;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;

import com.furkhail.demo.WiFiServiceDiscoveryActivity;
import com.furkhail.footballclock.App;
import com.furkhail.footballclock.R;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainFragment extends BaseFragment {

    public static final int PLAY_CLOCK_TAG = 0;
    public static final int GAME_CLOCK_TAG = 1;
    ArrayList<String> roles = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        initRoles();
        return rootView;
    }

    private void initRoles(){
        roles.add(getString(R.string.play_clock));
        roles.add(getString(R.string.game_clock));
        roles.add(getString(R.string.spectator));
    }

    @OnClick(R.id.btnCreateGame)
    public void createGame() {
        new MaterialDialog.Builder(mContext)
                .title(R.string.select_clock)
                .items(roles)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        FragmentTransaction t=getFragmentManager().beginTransaction();
                        switch (which) {
                            case PLAY_CLOCK_TAG:
                                t=t.replace(R.id.container, new PlayClockFragment());
                                break;
                            case GAME_CLOCK_TAG:
                                t=t.replace(R.id.container, new GameClockFragment());
                                break;
                            case 2:
                                t=t.replace(R.id.container, new SpectatorFragment());
                                break;
                        }
                        t.commit();
                    }
                })
                .negativeText(android.R.string.cancel)
                .show();
    }

    @OnClick(R.id.btnJoinGame)
    public void joinGame(){
        startActivity(new Intent(mContext, WiFiServiceDiscoveryActivity.class));
    }
}

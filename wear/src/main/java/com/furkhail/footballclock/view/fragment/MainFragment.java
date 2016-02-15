package com.furkhail.footballclock.view.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.furkhail.footballclock.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainFragment extends BaseFragment {

    @Bind(R.id.btnCreateGame)
    Button btnCreateGame;
    @Bind(R.id.btnJoinGame)
    Button btnJoinGame;
    boolean create = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.btnCreateGame)
    public void createGame() {
        if(create){
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, new PlayClockFragment())
                    .commit();
        }
        else {
            create = true;
            btnCreateGame.setText(R.string.play_clock);
            btnJoinGame.setEnabled(true);
        }
//        new MaterialDialog.Builder(mContext)
//                .title(getString(R.string.select_clock))
//                .positiveText(getString(R.string.play_clock))
//                .negativeText(getString(R.string.game_clock))
//                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(MaterialDialog dialog, DialogAction which) {
//                        getFragmentManager().beginTransaction()
//                                .replace(R.id.container, new PlayClockFragment())
//                                .commit();
//                    }
//                })
//                .onNegative(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(MaterialDialog dialog, DialogAction which) {
//                        getFragmentManager().beginTransaction()
//                                .replace(R.id.container, new GameClockFragment())
//                                .commit();
//                    }
//                })
//                .show();
    }

    @OnClick(R.id.btnJoinGame)
    public void joinGame(){
//        startActivity(new Intent(mContext, WiFiServiceDiscoveryActivity.class));
        if(create){
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, new GameClockFragment())
                    .commit();
        }
        else {
            btnJoinGame.setText(R.string.game_clock);
        }
    }
}

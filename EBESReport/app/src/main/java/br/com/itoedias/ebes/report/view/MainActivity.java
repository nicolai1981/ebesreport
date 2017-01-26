/**
 * Author: Nicolai Ito
 * Date: 01/23/2017
 **/
package br.com.itoedias.ebes.report.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import br.com.itoedias.ebes.report.R;
import br.com.itoedias.ebes.report.databinding.ActivityMainBinding;
import br.com.itoedias.ebes.report.viewmodel.HomeViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDataBinding.setHomeModel(HomeViewModel.getInstance());
        setCurrentFragment();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public static class UpdateCurrentFragmentEvent {}

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(UpdateCurrentFragmentEvent event) {
        setCurrentFragment();
    }

    private void setCurrentFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment;

        switch (HomeViewModel.getInstance().getState()) {
            case _1_CLIENT:
                fragment = new FragmentClient();
                break;
            default:
                fragment = new FragmentHome();
                break;
        }
        ft.replace(R.id.fragmentContainer, fragment);
        ft.commit();
    }
}

/**
 * Author: Nicolai Ito
 * Date: 01/24/2017
 **/
package br.com.itoedias.ebes.report.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import br.com.itoedias.ebes.report.view.MainActivity;
import br.com.itoedias.ebes.report.R;
import br.com.itoedias.ebes.report.model.HomeModel;

public class HomeViewModel extends BaseObservable {
    private static HomeViewModel sInstance;
    private HomeModel.HomeData mHomeData;

    public static final HomeViewModel getInstance() {
        if (sInstance == null) {
            sInstance = new HomeViewModel();
        }
        return sInstance;
    }

    private HomeViewModel() {
        mHomeData = HomeModel.getHomeData();
    }

    @Bindable
    public int getTitle() {
        int titleId;
        switch (mHomeData.mState) {
            case _0_NONE:
                titleId = R.string.MAIN_TITLE_HOME;
                break;
            case _1_CLIENT:
                titleId = R.string.MAIN_TITLE_CLIENT;
                break;
            default:
                titleId = R.string.MAIN_TITLE_HOME;
                break;
        }
        return titleId;
    }

    public HomeModel.HomeData.REPORT_STATE getState() {
        return mHomeData.mState;
    }

    public int getStateOrdinal() {
        return mHomeData.mState.ordinal();
    }

    private void setState(HomeModel.HomeData.REPORT_STATE state) {
        mHomeData.mState = state;
        HomeModel.setHomeData(mHomeData);
        notifyChange();
        EventBus.getDefault().post(new MainActivity.UpdateCurrentFragmentEvent());
    }

    public void onClickNext(View view) {
        setState(HomeModel.HomeData.REPORT_STATE._1_CLIENT);
    }

    public void onClickPrevious(View view) {
        setState(HomeModel.HomeData.REPORT_STATE._0_NONE);
    }
}

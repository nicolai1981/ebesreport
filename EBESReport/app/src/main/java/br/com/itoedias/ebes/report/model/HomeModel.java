/**
 * Author: Nicolai Ito
 * Date: 01/24/2017
 **/
package br.com.itoedias.ebes.report.model;

import android.content.Context;
import android.content.SharedPreferences;

import br.com.itoedias.ebes.report.ReportApplication;

public class HomeModel {
    private static final String PREFERENCE_FILE = "pref_home";
    private static final String TAG_HOME_STATE = "home_state";

    public static HomeData getHomeData() {
        SharedPreferences pref = ReportApplication.getContext()
                .getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);

        HomeData data = new HomeData();
        data.mState = HomeData.REPORT_STATE.valueOf(pref.getString(TAG_HOME_STATE, HomeData.REPORT_STATE._0_NONE.toString()));
        return data;
    }

    public static void setHomeData(HomeData data) {
        SharedPreferences pref = ReportApplication.getContext()
                .getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(TAG_HOME_STATE, data.mState.toString());
        editor.commit();
    }

    public static class HomeData {
        public enum REPORT_STATE {
            _0_NONE,
            _1_CLIENT,
        }

        public REPORT_STATE mState = REPORT_STATE._0_NONE;
    }
}

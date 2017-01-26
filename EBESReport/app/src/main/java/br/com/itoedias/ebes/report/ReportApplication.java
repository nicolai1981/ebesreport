/**
 * Author: Nicolai Ito
 * Date: 01/24/2017
 **/
package br.com.itoedias.ebes.report;

import android.app.Application;
import android.content.Context;

public class ReportApplication extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static final Context getContext() {
        return sContext;
    }
}

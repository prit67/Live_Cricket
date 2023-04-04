package com.cricket.crickais.crickcode;

import android.app.Application;
import com.onesignal.OneSignal;
public class ApplicationClass extends Application {
    private static final String ONESIGNAL_APP_ID = "4d870ad0-6446-468f-b3a7-5eee14c56bf4";

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
    }
}
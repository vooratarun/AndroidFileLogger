package voora.com.androidfilelogger;

import android.app.Application;

/**
 * Created by tarun on 12/12/17.
 */

public class SampleApplication  extends Application{

    private LogManager logManager;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeTimber();
    }

    private void initializeTimber() {
        logManager = LogManager.getInstance(this);
    }

}

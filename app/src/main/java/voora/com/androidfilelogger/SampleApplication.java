package voora.com.androidfilelogger;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by tarun on 12/12/17.
 */

public class SampleApplication  extends Application{


    @Override
    public void onCreate() {
        super.onCreate();

        initializeTimber();
    }

    private void initializeTimber() {
        LogManager.getInstance(this); // It plants the Timber Trees.

        Timber.d("Print message");
    }

}

package voora.com.androidfilelogger;

import android.content.Context;

import timber.log.Timber;

/**
 * Created by tarun on 11/24/17.
 * Manages All kinds of logs.
 */

public class LogManager {

    private FileLoggerTree fileLoggerTree;
    private static LogManager INSTANCE;

    private LogManager(Context context) {

        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        fileLoggerTree = new FileLoggerTree(context);

        Timber.plant(fileLoggerTree);

    }

    public static LogManager getInstance(Context context) {
        if(INSTANCE == null)
            INSTANCE = new LogManager(context);
        return INSTANCE;
    }

    public void destroy() {
        fileLoggerTree.destroy();
    }
}
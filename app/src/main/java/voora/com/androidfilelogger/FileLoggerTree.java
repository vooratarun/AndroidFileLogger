package voora.com.androidfilelogger;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import rx.Completable;
import rx.functions.Action0;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by tarun on 12/12/17.
 */

public class FileLoggerTree extends Timber.Tree {

    private Context context;
    private File logFile;

    public FileLoggerTree(Context context) {
        this.context = context.getApplicationContext();
        logFile = new File(context.getFilesDir(), getLoggerFileName());

    }

    /**
     * @param priority
     * @param tag
     * @param message
     * @param t
     */
    @Override
    protected void log(int priority, String tag, String message, @Nullable Throwable t) {

        Completable.fromAction(() -> {
            try {
                logSync(priority, tag, message, t);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io()).subscribe(new Action0() {
            @Override
            public void call() {

            }
        });
    }

    protected void logSync(int priority, String tag, String message, @Nullable Throwable throwable)
            throws IOException {

        if (throwable != null) {
            String logMessage = String.format("%s/%s/%s", tag, getLogPriorityString(priority),
                    throwable.getLocalizedMessage());
            writeToFile(logMessage);
        } else {
            String logMessage = String.format("%s/%s/%s", tag, getLogPriorityString(priority), message);
            writeToFile(logMessage);
        }
    }


    private void writeToFile(String data) {

        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(logFile);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(data);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Generates a {@link String} identifier for the given {@link Integer} priority.
     * @param priority
     * @return
     */
    public static String getLogPriorityString(int priority) {
        switch (priority) {
            case Log.VERBOSE:
                return "V";
            case Log.ERROR:
                return "E";
            case Log.ASSERT:
                return "A";
            case Log.WARN:
                return "W";
            case Log.INFO:
                return "I";
            case Log.DEBUG:
                return "D";
            default:
                return "V";
        }
    }

    /**
     * generates the logging file name based on subscriber id and current time.
     * @return Name of the Log file.
     */
    @NonNull
    public static String getLoggerFileName() {
        String dateFormat = "dd-MM-yyyy-hh-mm-ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        return String.format("%s-%s", "uid", simpleDateFormat.format(calendar.getTime()));
    }

    void destroy() {

    }
}
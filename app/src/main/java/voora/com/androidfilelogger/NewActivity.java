package voora.com.androidfilelogger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import timber.log.Timber;


public class NewActivity extends AppCompatActivity {


    private static final String TAG = "NewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        Timber.d("onCreate");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.d("onResume");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Timber.d("onStart");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Timber.d("onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Timber.d("onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.d("onDestroy");

    }
}

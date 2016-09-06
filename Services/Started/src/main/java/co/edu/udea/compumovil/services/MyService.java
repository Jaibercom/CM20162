package co.edu.udea.compumovil.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

    private final String TAG = "MyService";
    private final int seconds = 1;
    private Counter thread;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
        Log.v(TAG, "onStartCommand");

        final int currentId = startId;
        Log.d(TAG, "Service started");

        thread = new Counter(20);
        thread.start();

        // If we get killed, after returning from here, restart
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        thread.setFlag(false);
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
        Log.v(TAG, "onDestroy");
    }

}

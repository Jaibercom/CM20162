package co.edu.udea.computacionmovil.memoryservice;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Un {@link Service} que notifica la cantidad de memoria disponible en el sistema
 */
public class MemoryService extends Service {
    private static final String TAG = MemoryService.class.getSimpleName();
    TimerTask timerTask;

    public MemoryService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        Log.d(TAG, "Servicio creado...");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Servicio iniciado...");

        final ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        final ActivityManager activityManager =
                (ActivityManager) getSystemService(ACTIVITY_SERVICE);


        Timer timer = new Timer();

        timerTask = new TimerTask() {
            @Override
            public void run() {
                activityManager.getMemoryInfo(memoryInfo);
                String availMem = memoryInfo.availMem / 1048576 + "MB";

                Log.d(TAG, availMem);

            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, 1000);

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        timerTask.cancel();
        Log.d(TAG, "Servicio destruido...");
    }

}

package com.example.boixel.projetamio;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.support.annotation.Nullable;
import java.util.Timer;
import java.util.TimerTask;

public class MainService extends Service {
    private Timer myTimer;
    private MyTimerTask myTask;
    public MainService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MainService", "Service lancé!");
        myTask = new MyTimerTask();
        myTimer = new Timer();
        myTimer.schedule(myTask, 3000, 3000);
        return START_STICKY;
    }

    class MyTimerTask extends TimerTask {
        public void run() {

            startService(new Intent(getBaseContext(), WebService.class));
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        myTimer.cancel();
        myTask.cancel();
        Log.d("MainService", "Service terminé.");
    }
}

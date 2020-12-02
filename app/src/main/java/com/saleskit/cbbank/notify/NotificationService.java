package com.saleskit.cbbank.notify;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.saleskit.cbbank.util.NotifyUtils;

public class NotificationService extends Service {
    BroadcastReceiver tickReceiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        tickReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                NotifyUtils.getInstance().checkAndCreateNotify();
            }
        };
        registerReceiver(tickReceiver, new IntentFilter(Intent.ACTION_TIME_TICK));
    }

    @Override
    public void onDestroy() {
        Log.d("NotificationService", "onDestroy");
        unregisterReceiver(tickReceiver);
        super.onDestroy();
    }
}

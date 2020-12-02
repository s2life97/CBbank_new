package com.saleskit.cbbank.notify;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.saleskit.cbbank.util.NotifyUtils;

public class TimeSetReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Khi người dùng thay đổi thời gian trên thiết bị sẽ nhảy vào đây
        NotifyUtils.getInstance().checkAndCreateNotify();
        NotifyUtils.getInstance().restartNotify();
    }
}

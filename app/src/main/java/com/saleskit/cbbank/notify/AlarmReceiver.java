package com.saleskit.cbbank.notify;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.saleskit.cbbank.util.NotifyUtils;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Khi bắt đầu ngày mới sẽ check xem có phải những ngày cần show noti hay ko
        // và restart alarm cho ngày tiếp theo
//        boolean showNoti = HawkHelper.getInstance(context).getstatusNoti();
//        if (showNoti) {
        NotifyUtils.getInstance().checkAndCreateNotify();
//        }
        NotifyUtils.getInstance().restartNotify();
    }
}

package com.saleskit.cbbank.util;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.appointment.SavedAppointModel;
import com.saleskit.cbbank.features.database.HawkHelper;
import com.saleskit.cbbank.features.home.activity.HomeActivity2;
import com.saleskit.cbbank.notify.AlarmReceiver;

import java.util.Calendar;
import java.util.List;

import static android.app.Notification.BADGE_ICON_SMALL;

public class NotifyUtils {
    private static int requestCode = 0;
    private static NotificationManager manager;
    private Context context;
    private static NotifyUtils notifyInstance;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    public static NotifyUtils getInstance() {
        if (notifyInstance == null) {
            notifyInstance = new NotifyUtils();
        }
        return notifyInstance;
    }

    public Context getContext() {
        return context;
    }

    public void init(Context context) {
        this.context = context;
    }

    private void startAlarm(Calendar alarmCalendar) {
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmCalendar.getTimeInMillis(), pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, alarmCalendar.getTimeInMillis(), pendingIntent);
        }

        if (requestCode < 10) {
            requestCode++;
        } else {
            requestCode = 0;
        }
    }

    private void cancelAlarm() {
        if (alarmManager != null && pendingIntent != null) {
            alarmManager.cancel(pendingIntent);
        }
    }

    void createNotification(int notifyId, String content) {
        Uri ringToneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_noti);

        remoteViews.setTextViewText(R.id.tv_content_noti, content);
        remoteViews.setImageViewBitmap(R.id.img_icon, BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_icon_app));

        Intent continueIntent = new Intent(context, HomeActivity2.class);
        continueIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        continueIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        continueIntent.putExtra(Constants.NOTIFY_ID, notifyId);
        PendingIntent continuePendingIntent = PendingIntent.getActivity(context, 1, continueIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        remoteViews.setOnClickPendingIntent(R.id.layout_noti, continuePendingIntent);

        String channelId = "Chanel Id";
        String channelName = "Chanel Name";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(
                    channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
            channel.setShowBadge(true);
            manager.createNotificationChannel(channel);

            @SuppressLint("WrongConstant") Notification notification = new NotificationCompat.Builder(context, channelId)
                    .setOngoing(true)
                    .setAutoCancel(true)
                    .setSmallIcon(R.mipmap.ic_icon_app)
                    .setBadgeIconType(BADGE_ICON_SMALL)
                    .setSound(ringToneUri)
                    .setContent(remoteViews)
                    .setChannelId(channelId)
                    .build();

            manager.notify(notifyId, notification);
        } else {
            NotificationCompat.Builder b = new NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(R.mipmap.ic_icon_app)
                    .setContent(remoteViews)
                    .setAutoCancel(true)
                    .setSound(ringToneUri);

            manager.notify(notifyId, b.build());
        }
    }

    public void clearNotify(int notifyId) {
        if (manager != null) {
            manager.cancel(notifyId);
        }
    }

    public void restartNotify() {
        cancelAlarm();
        Calendar calendar = getStartNextDay();
        startAlarm(calendar);
    }

    private Calendar getStartNextDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return calendar;
    }

    public void checkAndCreateNotify() {
//        Timber.e(savedAppointModels.get(0).getDateTime());
//        Log.d("test by qk", "checkAndCreateNotify:");
//        Calendar calendar = Calendar.getInstance();
//        String currentDate = calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR);
//        SimpleDateFormat format = new SimpleDateFormat(Constants.format, Locale.US);
//        currentDate = AppUtil.convertDate(format, currentDate);
//        if ("13/12/2019".equals(currentDate)) {
//            createNotification(Constants.notifyIds[0], "klaajsd");
//        }

        List<SavedAppointModel> savedAppointModels = HawkHelper.getInstance(context).getListSavedAppoint();
//        Calendar calendar = Calendar.getInstance();
//        for (SavedAppointModel savedAppointModel : savedAppointModels) {
//            Log.d("test by qk", "checkAndCreateNotify: " + savedAppointModel.getDateTime());
//            String dateFromAppointmentString = savedAppointModel.getDateTime();
//            Date dateFromAppointment = new Date(
//                    Integer.parseInt(dateFromAppointmentString.substring(0, 4)) - 1900,
//                    Integer.parseInt(dateFromAppointmentString.substring(5, 7)) - 1,
//                    Integer.parseInt(dateFromAppointmentString.substring(8, 10)),
//                    Integer.parseInt(dateFromAppointmentString.substring(11, 13)),
//                    Integer.parseInt(dateFromAppointmentString.substring(14, 16)),
//                    Integer.parseInt(dateFromAppointmentString.substring(17, 19))
//            );
//            if (calendar.getTimeInMillis() + 30 * 60 * 1000 <= dateFromAppointment.getTime() + 60 * 1000
//                    && calendar.getTimeInMillis() + 30 * 60 * 1000 >= dateFromAppointment.getTime() - 60 * 1000) {
//                createNotification(Constants.notifyIds[0], savedAppointModel.getUserName());
//                savedAppointModels.remove(savedAppointModel);
//                HawkHelper.getInstance(context).saveAppointmentModels(savedAppointModels);
//            }
//        }
    }
}

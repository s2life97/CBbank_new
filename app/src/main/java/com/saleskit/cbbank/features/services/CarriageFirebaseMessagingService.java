
package com.saleskit.cbbank.features.services;

import android.annotation.SuppressLint;
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
import android.widget.RemoteViews;


import androidx.core.app.NotificationCompat;

import com.saleskit.cbbank.Constants;
import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.appointment.AddApointmentActivity;
import com.saleskit.cbbank.features.detail.HDCustomerDetailActivity;
import com.saleskit.cbbank.features.home.NotiDetailActivity;
import com.saleskit.cbbank.features.news.DetailNewActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import timber.log.Timber;

import static android.app.Notification.BADGE_ICON_SMALL;

public class CarriageFirebaseMessagingService extends FirebaseMessagingService {
    private static NotificationManager manager;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getNotification() != null) {
            Map<String, String> data = remoteMessage.getData();
            if (!data.isEmpty()) {
                String typeNoti = data.get("type");
                String typeId = data.get("forwardId");
                String notificationId = data.get("notificationId");
                EventBus.getDefault().postSticky(new NotiEvent(typeNoti));
                sendNotification(remoteMessage.getNotification().getBody(), remoteMessage.getNotification().getTitle(), typeNoti
                        , typeId, notificationId);
            }

        } else {
            Map<String, String> data = remoteMessage.getData();
            if (!data.isEmpty()) {
                String typeNoti = data.get("id");
            }
        }
    }

    private void sendNotification(String messageBody, String title, String type, String id, String notificationId) {
        Uri ringToneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.layout_noti);
        remoteViews.setTextViewText(R.id.tv_content_title, title);
        remoteViews.setTextViewText(R.id.tv_content_noti, messageBody);
        remoteViews.setImageViewBitmap(R.id.img_icon, BitmapFactory.decodeResource(getApplicationContext().getResources(), R.mipmap.ic_icon_app));
        Intent continueIntent = null;
        switch (type) {
            case "1":
                continueIntent = new Intent(getApplicationContext(), DetailNewActivity.class);
                continueIntent.putExtra(Constants.ARTICLE, Integer.valueOf(id));
                continueIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                continueIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                continueIntent.putExtra(Constants.NOTIFY_ID, id);
                continueIntent.putExtra(Constants.READ_NOTI, false);
                continueIntent.putExtra(Constants.NOTIFICATION_ID, notificationId);
                break;
            case "2":
                continueIntent = new Intent(getApplicationContext(), AddApointmentActivity.class);
                continueIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                continueIntent.putExtra(Constants.SEE_DETAIL, Constants.FROM_NOTI);
                continueIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                continueIntent.putExtra(Constants.APPOINT_ID, id);
                continueIntent.putExtra(Constants.READ_NOTI, false);
                continueIntent.putExtra(Constants.NOTIFICATION_ID, notificationId);
                continueIntent.putExtra(Constants.NOTIFICATION_ID, notificationId);
                break;
            case "3":
                continueIntent = new Intent(getApplicationContext(), HDCustomerDetailActivity.class);
                continueIntent.putExtra(Constants.SCREEN_TYPE, true);
                continueIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                continueIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                continueIntent.putExtra(Constants.READ_NOTI, false);
                continueIntent.putExtra(Constants.NOTIFICATION_ID, notificationId);
                continueIntent.putExtra(Constants.CUSTOMER_PROFILEID, Integer.parseInt(id));
                continueIntent.putExtra(Constants.NOTIFICATION_ID, notificationId);
                break;
            default:
                continueIntent = new Intent(getApplicationContext(), NotiDetailActivity.class);
                continueIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                continueIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                continueIntent.putExtra(Constants.READ_NOTI, false);
                continueIntent.putExtra(Constants.NOTIFICATION_ID, notificationId);
                continueIntent.putExtra(Constants.NOTIFY_ID, Integer.parseInt(id));
                continueIntent.putExtra(Constants.NOTIFICATION_ID, notificationId);
                break;
        }

        PendingIntent continuePendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, continueIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        remoteViews.setOnClickPendingIntent(R.id.layout_noti, continuePendingIntent);

        String channelId = "Chanel Id";
        String channelName = "Chanel Name";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(
                    channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
            channel.setShowBadge(true);
            manager.createNotificationChannel(channel);

            @SuppressLint("WrongConstant") Notification notification = new NotificationCompat.Builder(getApplicationContext(), channelId)
                    .setOngoing(false)
                    .setAutoCancel(true)
                    .setSmallIcon(R.mipmap.ic_icon_app)
                    .setBadgeIconType(BADGE_ICON_SMALL)
                    .setSound(ringToneUri)
                    .setContent(remoteViews)
                    .setChannelId(channelId)
                    .build();

            manager.notify(0, notification);
        } else {
            NotificationCompat.Builder b = new NotificationCompat.Builder(getApplicationContext(), channelId)
                    .setSmallIcon(R.mipmap.ic_icon_app)
                    .setContent(remoteViews)
                    .setAutoCancel(true)
                    .setSound(ringToneUri);

            manager.notify(0, b.build());
        }
    }

}

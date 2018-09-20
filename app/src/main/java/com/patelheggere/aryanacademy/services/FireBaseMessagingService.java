package com.patelheggere.aryanacademy.services;

import android.app.ActivityManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.google.common.eventbus.EventBus;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.patelheggere.aryanacademy.R;
import com.patelheggere.aryanacademy.view.main.MainActivity;

import java.util.List;
import java.util.Map;

public class FireBaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        handleNow(remoteMessage.getNotification().getBody(), remoteMessage.getNotification().getTitle());
       /*

       lottery start type =1, raffle start type = 2, raffle end =6

       trivia start type = 4, end type = 5,  trivia result = 7

        */
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "onMessageReceived: event typ:"+remoteMessage.getData().get("event_type"));
        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData().get("nm"));

            if (/* Check if data needs to be processed by long running job */ !true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                scheduleJob();
            } else {
                try {
                    Log.d(TAG, "onMessageReceived: " + remoteMessage.getFrom());

                    Bundle extras = new Bundle();
                    for (Map.Entry<String, String> entry : remoteMessage.getData().entrySet()) {
                        extras.putString(entry.getKey(), entry.getValue());
                    }

                } catch (Exception e) {
                    Log.d(TAG, "onMessageReceived: " + e.getLocalizedMessage());
                }
            }
        }
        if (remoteMessage.getNotification() != null) {

            //pay load structure
            /* {
                    "notification_type": 10,
                        "index": 1,
                        "number": 12,
                        "event_id": 14,
                        "event_type":1
                }*/

            //handleNow(remoteMessage.getNotification().getBody(), "IndiWyn");
            try {
                String notification_type = remoteMessage.getData().get("notification_type");
                Log.d(TAG, "onMessageReceived: nosd:");
                //draw details and winner details for live need not diaplay user tickets for both lottery and raffle
                if (notification_type.equalsIgnoreCase("3")) {
                    String ticket_number = remoteMessage.getData().get("ticket_number");
                    String user_ticket = remoteMessage.getData().get("user_ticket");
                    String event_type = remoteMessage.getData().get("event_type");
                    String raffleTicketID = remoteMessage.getData().get("raffle_id");
                    String raffleWinnerNumber = remoteMessage.getData().get("raffle_number");
                    String raffleYourTicketID = remoteMessage.getData().get("user_raffle_id");
                    String raffleYourNumber = remoteMessage.getData().get("user_raffle_number");
                    Bundle bundle = new Bundle();
                    bundle.putString("L_WINNER_NUMBER", ticket_number);
                    bundle.putString("L_YOUR_NUMBER", user_ticket);
                    bundle.putString("R_WINNER_NUMBER", raffleWinnerNumber);
                    bundle.putString("R_YOUR_NUMBER", raffleYourNumber);
                    bundle.putString("R_WINNER_ID", raffleTicketID);
                    bundle.putString("R_YOUR_ID", raffleYourTicketID);
                    sendNotification2(bundle, 3);
                   // EventBus.getDefault().post(new DrawDetails(ticket_number, user_ticket, raffleWinnerNumber, raffleYourNumber, raffleTicketID, raffleYourTicketID));
                } else if (remoteMessage.getData().get("notification_type").equalsIgnoreCase("4")) {
                    String event_id = remoteMessage.getData().get("event_id");
                 //   EventBus.getDefault().post(new LiveEventDetails(event_id));
                    Bundle bundle = new Bundle();
                    bundle.putString("EVENT_ID", event_id);
                    sendNotification2(bundle, 4);
                }
                //for lottery and raffle winning details update
                else if (remoteMessage.getData().get("notification_type").equalsIgnoreCase("0")) {
                    String index = remoteMessage.getData().get("index");
                    String number = remoteMessage.getData().get("number");
                    String event_id = remoteMessage.getData().get("event_id");
                    int index1 = Integer.valueOf(index);
                    int value = Integer.valueOf(number);
                   // EventBus.getDefault().post(new EventLotteryWinningUpdate(index1, value));
                    //handleNow(remoteMessage.getNotification().getBody(), remoteMessage.getNotification().getTitle());
                }
                //to update lottery event complete
                else if (remoteMessage.getData().get("notification_type").equalsIgnoreCase("1")) {
                    String ticketNumber = remoteMessage.getData().get("ticket_number");
                    //EventBus.getDefault().post(new LotteryComplete(ticketNumber));
                    // handleNow(remoteMessage.getNotification().getBody(), remoteMessage.getNotification().getTitle());
                }

                //lottery winner notification
                else if (remoteMessage.getData().get("notification_type").equalsIgnoreCase("2")) {

                }
                //to update raffle winners along with runner up
                else if(remoteMessage.getData().get("notification_type").equalsIgnoreCase("8"))
                {
                    String participations = remoteMessage.getData().get("participations");
                    String runner1 = remoteMessage.getData().get("runner_1");
                    String runner2 = remoteMessage.getData().get("runner_2");
                    String winner = remoteMessage.getData().get("winner");
                    // String data = remoteMessage.getData().toString();
                  //  EventBus.getDefault().post(new RaffleWinnerDetails(participations, runner1, runner2, winner));
                }

                //to update lottery, raffle or trivia started
                //trivia started type =5,  trivia result type =7,
                else if (remoteMessage.getData().get("notification_type").equalsIgnoreCase("10")) {
                    String type = remoteMessage.getData().get("event_type");
                    String raffleId = remoteMessage.getData().get("raffle_id");
                    String raffleNumber = remoteMessage.getData().get("raffle_number");
                    String lotteryTicket = remoteMessage.getData().get("lottery_winner_ticket");
                    String msg = remoteMessage.getData().get("message");
                    //EventBus.getDefault().post(new EventTypeClass(type, lotteryTicket, raffleId, raffleNumber, msg));
                }
                //to send questions with options
                else if(remoteMessage.getData().get("notification_type").equalsIgnoreCase("11"))
                {
                    String type = remoteMessage.getData().get("event_type");
                    String question = remoteMessage.getData().get("question");
                    String op1 = remoteMessage.getData().get("option1");
                    String op2 = remoteMessage.getData().get("option2");
                    String op3 = remoteMessage.getData().get("option3");
                    String op4 = remoteMessage.getData().get("option4");
                    String status = remoteMessage.getData().get("status");
                    String event_id = remoteMessage.getData().get("event_id");
                    String sr_no = remoteMessage.getData().get("sr_no");
                    //EventBus.getDefault().post(new TriviaQuestionModel(question, op1, op2, op3, op4, Integer.parseInt(event_id), Integer.parseInt(sr_no)));
                }
                //to send answer
                else if(remoteMessage.getData().get("notification_type").equalsIgnoreCase("12"))
                {
                    String type = remoteMessage.getData().get("event_type");
                    String question = remoteMessage.getData().get("question");
                    String ans = remoteMessage.getData().get("ans");
                   // EventBus.getDefault().post(new TriviaAnswerModel(Integer.parseInt(ans), question));
                }

            }catch (Exception e)
            {

            }
            /*try {
                String index = remoteMessage.getData().get("index");
                String number = remoteMessage.getData().get("number");
                String event_id = remoteMessage.getData().get("event_id");
                int index1 = Integer.valueOf(index);
                int value = Integer.valueOf(number);
                EventBus.getDefault().post(new EventLotteryWinningUpdate(index1, value));
                Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody().toString());
                handleNow(remoteMessage.getNotification().getBody(), remoteMessage.getNotification().getTitle());
                //Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            } catch (Exception e) {

            }*/

        }

    }

    // [END receive_message]

    /**
     * Schedule a job using FirebaseJobDispatcher.
     */
    private void scheduleJob() {
        // [START dispatch_job]
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        Job myJob = dispatcher.newJobBuilder()
                .setService(MyJobService.class)
                .setTag("my-job-tag")
                .build();
        dispatcher.schedule(myJob);
        // [END dispatch_job]
    }

    /**
     * Handle time allotted to BroadcastReceivers.
     */
    private void handleNow(String message, String title) {
        Log.d(TAG, "Short lived task is done.");
        sendNotification(message, title);
       /* Intent intent = new Intent(this, HomeBottomNavActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("FROM_DRAW", true);*/
        //startActivity(intent);

    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(String messageBody, String title) {
        if (title == null) {
            title = "Aryan Academy";
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("FROM_DRAW", true);
        Bundle bundle = new Bundle();
        bundle.putString("L_WINNER_NUMBER", "10-11-21");
        bundle.putString("L_YOUR_NUMBER", "20-36-85");
        bundle.putString("R_WINNER_ID", "265987");
        bundle.putString("R_WINNER_NUMBER", "78");
        intent.putExtra("DATA", bundle);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = getString(R.string.default_notification_channel_id);
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        //Uri soundUri = Uri.parse("android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.notification);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                        .setContentTitle(title)
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        //.setSound(soundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);

        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    private void sendNotification2(Bundle bundle, int type) {

        Intent intent = new Intent(this, MainActivity.class);
        String title = "", text = "";
        if (type == 3) {
            title = "India Wyn Draw";
            text = "Draw Details";
            intent.putExtra("FROM_DRAW", true);
            intent.putExtra("DATA", bundle);
        } else if (type == 4) {
            title = "Go Live";
            text = "Go Live";
            intent.putExtra("FROM_LIVE", true);
            intent.putExtra("DATA", bundle);
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);
        String channelId = getString(R.string.default_notification_channel_id);
        //Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

      //  Uri soundUri = Uri.parse("android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.submit_button);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                        .setContentTitle(title)
                        .setContentText(text)
                        .setAutoCancel(true)
                       // .setSound(soundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    private boolean isAppIsInBackground(Context context) {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(context.getPackageName())) {
                            isInBackground = false;
                        }
                    }
                }
            }
        } else {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            if (componentInfo.getPackageName().equals(context.getPackageName())) {
                isInBackground = false;
            }
        }

        return isInBackground;
    }


}
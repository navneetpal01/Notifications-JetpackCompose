package com.example.notificationjetpackcompose.di

import android.annotation.SuppressLint
import android.app.Application
import android.app.Notification.VISIBILITY_PUBLIC
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.example.notificationjetpackcompose.MainActivity
import com.example.notificationjetpackcompose.R
import com.example.notificationjetpackcompose.navigation.MY_ARG
import com.example.notificationjetpackcompose.navigation.MY_URI
import com.example.notificationjetpackcompose.receiver.MyReceiver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {

    @SuppressLint("WrongConstant")
    @Singleton
    @Provides
    fun provideNotificationBuilder(
        application: Application
    ): NotificationCompat.Builder {
        val intent = Intent(application, MyReceiver::class.java).apply {
            putExtra("MESSAGE", "Clicked")
        }
        val flag =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                PendingIntent.FLAG_IMMUTABLE
            else
                0
        val pendingIntent = PendingIntent.getBroadcast(
            application,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val clickIntent = Intent(
            Intent.ACTION_VIEW,
            "$MY_URI/$MY_ARG=Coming from Notification".toUri(),
            application,
            MainActivity::class.java
        )
        val clickPendingIntent : PendingIntent = TaskStackBuilder.create(application).run {
            addNextIntentWithParentStack(clickIntent)
            getPendingIntent(1,flag)
        }


        return NotificationCompat.Builder(
            application, "Main Channel ID"
        )
            .setContentTitle("Welcome")
            .setContentText("Founder of Biona: Navneet Pal")
            .setSmallIcon(R.drawable.notification)
            //For api level lower than 26
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            //How much content want to show on the lock Screen
            .setVisibility(VISIBILITY_PUBLIC)
            //Just for the Private Notification
            /*
                .setVisibility(VISIBILITY_PRIVATE)
                .setPublicVersion(
                     NotificationCompact.Builder(application,"Main Channel ID")
                            .setContentTitle("Hidden")
                            .setContentText("Unlock")
                            .build()
                )
             */
            .addAction(0, "Action", pendingIntent)
            //When we will click the Notification Now we will be able to open our Activity
            .setContentIntent(clickPendingIntent)

    }

    @Singleton
    @Provides
    fun provideNotificationManager(
        application: Application
    ): NotificationManagerCompat {
        val notificationManager = NotificationManagerCompat.from(application)
        //Notification Channel for Api level 26 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "Main Channel ID",
                "Main Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        return notificationManager
    }
}
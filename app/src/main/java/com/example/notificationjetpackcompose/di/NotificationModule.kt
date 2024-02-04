package com.example.notificationjetpackcompose.di

import android.annotation.SuppressLint
import android.app.Application
import android.app.Notification.VISIBILITY_PUBLIC
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.notificationjetpackcompose.R
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
    ) : NotificationCompat.Builder {
        return NotificationCompat.Builder(
            application,"Main Channel ID"
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

    }

    @Singleton
    @Provides
    fun provideNotificationManager(
        application: Application
    ) : NotificationManagerCompat {
        val notificationManager = NotificationManagerCompat.from(application)
        //Notification Channel for Api level 26 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
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
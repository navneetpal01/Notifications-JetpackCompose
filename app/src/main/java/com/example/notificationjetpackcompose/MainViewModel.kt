package com.example.notificationjetpackcompose

import android.app.NotificationManager
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val notificationBuilder : NotificationCompat.Builder,
    private val notificationManager : NotificationManager
) : ViewModel()
{

}
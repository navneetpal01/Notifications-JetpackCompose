package com.example.notificationjetpackcompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainScreen(mainViewModel: MainViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(onClick = {
            mainViewModel.showSimpleNotification()
        }) {
            Text(text = "Simple Notification")
        }
        Button(onClick = {
            mainViewModel.updateSimpleNotification()
        }) {
            Text(text = "Update Notification")
        }
        Button(onClick = {
            mainViewModel.cancelSimpleNotification()
        }) {
            Text(text = "Cancel Notification")
        }
    }
}
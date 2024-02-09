package com.example.notificationjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.notificationjetpackcompose.navigation.SetupNavGraph
import com.example.notificationjetpackcompose.screen.MainScreen
import com.example.notificationjetpackcompose.ui.theme.NotificationJetPackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotificationJetPackComposeTheme {
               SetupNavGraph(navController = rememberNavController() )
            }
        }
    }
}


package com.poqndj.testcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.poqndj.testcompose.ui.theme.MainScreen
import com.poqndj.testcompose.ui.theme.VkNewsClient


class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<NewsFeedViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VkNewsClient {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Blue)
                )
                {
                    MainScreen(viewModel)
                }
            }
        }
    }
}



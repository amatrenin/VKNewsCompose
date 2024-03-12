package com.poqndj.testcompose.ui.theme

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat

@SuppressLint("ConflictingOnColor")
private val DarkColorScheme = darkColors(
    primary = Black900,
    secondary = Black900,
    background = Color.Black,
    onPrimary = Color.White,
    onSecondary = Black500,
    onBackground = Color.White,
    onSurface = Color.White,
)

@SuppressLint("ConflictingOnColor")
private val LightColorScheme = lightColors(
    primary = Color.White,
    secondary = Color.White,
    background = Color.White,
    onPrimary = Black900,
    onSecondary = Black500,
    onBackground = Color.Black,
    onSurface = Color.Black,

//    /* Other default colors to override

//    */
)

@Composable
fun VkNewsClient(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
//    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors =
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
            if (darkTheme) {
                DarkColorScheme
            }else {
                LightColorScheme

    }

    androidx.compose.material.MaterialTheme(
        colors = colors,

//        colorScheme = colors,
        typography = typography,
        content = content
    )
}
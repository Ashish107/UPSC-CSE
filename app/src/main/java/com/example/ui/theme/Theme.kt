package com.example.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = SaffronPrimary,
    onPrimary = NavyCanvasDark,
    primaryContainer = SaffronContainerDark,
    onPrimaryContainer = SaffronLight,
    secondary = SaffronLight,
    onSecondary = NavyCanvasDark,
    background = NavyCanvasDark,
    onBackground = TextPrimaryDark,
    surface = NavySurfaceDark,
    onSurface = TextPrimaryDark,
    surfaceVariant = NavyCardElevatedDark,
    onSurfaceVariant = TextSecondaryDark,
    outline = NavyBorderDark,
    error = ErrorRose
)

private val LightColorScheme = lightColorScheme(
    primary = SaffronDark,
    onPrimary = SlateLightSurface,
    primaryContainer = SaffronContainerLight,
    onPrimaryContainer = SaffronDark,
    secondary = SaffronPrimary,
    onSecondary = SlateLightSurface,
    background = SlateLightBackground,
    onBackground = TextPrimaryLight,
    surface = SlateLightSurface,
    onSurface = TextPrimaryLight,
    surfaceVariant = SlateLightCard,
    onSurfaceVariant = TextSecondaryLight,
    outline = SlateLightBorder,
    error = ErrorRose
)

@Composable
fun UPSCMasterkeyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Use bespoke Scholarly Navy + Saffron theme instead of generic Android 12 dynamic wallpaper color
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            window.navigationBarColor = colorScheme.surface.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

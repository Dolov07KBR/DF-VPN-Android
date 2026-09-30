package com.v2ray.ang.ui.compose

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.v2ray.ang.AppConfig
import com.v2ray.ang.handler.MmkvManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

private val LightColor = lightColorScheme(
    primary = Color(0xFF4B5CFF), onPrimary = Color.White,
    primaryContainer = Color(0xFFE1E5FF), onPrimaryContainer = Color(0xFF101B6B),
    secondary = Color(0xFF00A6A6), onSecondary = Color.White,
    secondaryContainer = Color(0xFFB7F2F0), onSecondaryContainer = Color(0xFF00201F),
    tertiary = Color(0xFF6B4EFF), onTertiary = Color.White,
    background = Color(0xFFF7F8FF), onBackground = Color(0xFF171A2B),
    surface = Color(0xFFFDFBFF), onSurface = Color(0xFF171A2B),
    surfaceVariant = Color(0xFFE3E5F4), onSurfaceVariant = Color(0xFF454654),
    outline = Color(0xFF767785), outlineVariant = Color(0xFFC6C6D4),
    surfaceContainerLow = Color(0xFFF0F1FB), surfaceContainer = Color(0xFFE9EAF5),
    surfaceContainerHigh = Color(0xFFE3E4EF), surfaceContainerHighest = Color(0xFFDCDDE8)
)

private val DarkColor = darkColorScheme(
    primary = Color(0xFFB9C3FF), onPrimary = Color(0xFF14268F),
    primaryContainer = Color(0xFF3043D2), onPrimaryContainer = Color(0xFFE1E5FF),
    secondary = Color(0xFF63DAD6), onSecondary = Color(0xFF003736),
    secondaryContainer = Color(0xFF00504F), onSecondaryContainer = Color(0xFFB7F2F0),
    tertiary = Color(0xFFC7BFFF), onTertiary = Color(0xFF2E168B),
    background = Color(0xFF0D1020), onBackground = Color(0xFFE4E5F5),
    surface = Color(0xFF111426), onSurface = Color(0xFFE4E5F5),
    surfaceVariant = Color(0xFF454654), onSurfaceVariant = Color(0xFFC6C6D4),
    outline = Color(0xFF90909E), outlineVariant = Color(0xFF454654),
    surfaceContainerLow = Color(0xFF15182A), surfaceContainer = Color(0xFF191C2E),
    surfaceContainerHigh = Color(0xFF232638), surfaceContainerHighest = Color(0xFF2E3043)
)

// Semantic Colors
val colorPing = Color(0xFF009966) // Green
val colorPingRed = Color(0xFFFF0099) // Pink Red
val colorConfigType = Color(0xFFf97910) // Orange
val colorFabActive = Color(0xFFf97910) // Orange
val colorFabInactiveLight = Color(0xFF9C9C9C) // Gray
val colorFabInactiveDark = Color(0xFF646464) // Dark Gray
val dividerColorLight = Color(0xFFE0E0E0) // Light Gray
val dividerColorDark = Color(0xFF424242) // Dark Gray

// Toast Colors 85%
val toastNormalBgLight = Color(0xD9353A3E) // Dark Gray
val toastNormalBgDark = Color(0xD94A4F54) // Darker Gray
val toastSuccessBg = Color(0xD9388E3C) // Green
val toastErrorBg = Color(0xD9D50000) // Red
val toastInfoBg = Color(0xD93F51B5) // Indigo Blue
val toastIconCircleBg = Color(0x33FFFFFF) // Semi-transparent White
val toastTextColor = Color.White // White

object ThemeManager {
    private val _themeMode = MutableStateFlow(
        MmkvManager.decodeSettingsString(AppConfig.PREF_UI_MODE_NIGHT, "0") ?: "0"
    )
    val themeMode: StateFlow<String> = _themeMode.asStateFlow()

    private val _dynamicColorEnabled = MutableStateFlow(
        MmkvManager.decodeSettingsBool(AppConfig.PREF_DYNAMIC_COLOR, true)
    )
    val dynamicColorEnabled: StateFlow<Boolean> = _dynamicColorEnabled.asStateFlow()

    fun setThemeMode(mode: String) {
        MmkvManager.encodeSettings(AppConfig.PREF_UI_MODE_NIGHT, mode)
        _themeMode.value = mode
    }

    fun setDynamicColorEnabled(enabled: Boolean) {
        MmkvManager.encodeSettings(AppConfig.PREF_DYNAMIC_COLOR, enabled)
        _dynamicColorEnabled.value = enabled
    }

    fun refresh() {
        _themeMode.value =
            MmkvManager.decodeSettingsString(AppConfig.PREF_UI_MODE_NIGHT, "0") ?: "0"
        _dynamicColorEnabled.value =
            MmkvManager.decodeSettingsBool(AppConfig.PREF_DYNAMIC_COLOR, true)
    }
}

@Composable
fun resolveDarkTheme(): Boolean {
    val mode by ThemeManager.themeMode.collectAsState()
    return when (mode) {
        "1" -> false
        "2" -> true
        else -> isSystemInDarkTheme()
    }
}

val LocalDarkTheme = compositionLocalOf { false }

@Composable
fun AppTheme(
    darkTheme: Boolean = resolveDarkTheme(),
    content: @Composable () -> Unit
) {
    // DF VPN keeps its recognizable palette while still adapting to system light/dark mode.
    val colorScheme = if (darkTheme) DarkColor else LightColor
    val snackbarController = rememberAppSnackbarController()

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val activity = view.context as? Activity ?: return@SideEffect
            val window = activity.window
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = !darkTheme
                isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    CompositionLocalProvider(
        LocalDarkTheme provides darkTheme,
        LocalAppSnackbar provides snackbarController
    ) {
        MaterialTheme(
            colorScheme = colorScheme
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                AppSnackbarBridge(controller = snackbarController)
                content()
                AppSnackbarHost(hostState = snackbarController.hostState)
            }
        }
    }
}

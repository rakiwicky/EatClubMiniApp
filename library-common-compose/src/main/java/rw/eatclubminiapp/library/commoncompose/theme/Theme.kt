package rw.eatclubminiapp.library.commoncompose.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    background = Color(0xFF131313),
    surface = Color(0xFF131313),
    primary = Color(0xFF131313),
    onPrimary = Color(0xFFB0AFAF),
    secondary = Color(0xFFB0AFAF),
    onSecondary = Color(0xFF7A7A7A),
    onTertiary = Color(0xFF9F2222)
)

private val LightColorScheme = lightColorScheme(
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    primary = Color(0xFFFFFBFE),
    onPrimary = Color(0xFF100F0F),
    secondary = Color(0xFFFFFBFE),
    onSecondary = Color(0xFF343434),
    onTertiary = Color(0xFF7E1A1A)
)

@Composable
fun EatClubMiniAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
package com.samkt.fodoo.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.samkt.fodoo.R

val neutrif =
    FontFamily(
//        Font(R.font.neutrif_pro_light, FontWeight.Light),
//        Font(R.font.neutrif_pro_regular, FontWeight.Normal),
//        Font(R.font.neutrif_pro_medium, FontWeight.Medium),
//        Font(R.font.neutrif_pro_semi_bold, FontWeight.SemiBold),
//        Font(R.font.neutrif_pro_bold, FontWeight.Bold),
        Font(R.font.fiesole_bold_italic, FontWeight.Bold),
    )

// Set of Material typography styles to start with
val Typography =
    Typography(
        titleLarge =
            TextStyle(
                fontFamily = neutrif,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                lineHeight = 28.sp,
                letterSpacing = 0.5.sp,
            ),
        bodyLarge =
            TextStyle(
                fontFamily = neutrif,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                lineHeight = 25.sp,
                letterSpacing = 0.5.sp,
            ),
        bodySmall =
            TextStyle(
                fontFamily = neutrif,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 22.sp,
                letterSpacing = 0.5.sp,
            ),
        labelLarge =
            TextStyle(
                fontFamily = neutrif,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                lineHeight = 19.sp,
                letterSpacing = 0.5.sp,
            ),
    )

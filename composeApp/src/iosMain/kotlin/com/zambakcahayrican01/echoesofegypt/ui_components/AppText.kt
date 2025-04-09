package com.zambakcahayrican01.echoesofegypt.ui_components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import echoesofegypt.composeapp.generated.resources.Res
import echoesofegypt.composeapp.generated.resources.pottaone_regular
import org.jetbrains.compose.resources.Font

@Composable
fun AppText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 18.sp,
    color: Color = Color(0xff400000),
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = fontSize,
        color = color,
        fontFamily = FontFamily(Font(Res.font.pottaone_regular)),
        textAlign = textAlign,
        lineHeight = lineHeight
    )
}
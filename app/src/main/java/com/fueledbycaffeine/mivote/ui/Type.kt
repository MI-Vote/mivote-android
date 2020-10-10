package com.fueledbycaffeine.mivote.ui

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.ResourceFont
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.unit.sp
import com.fueledbycaffeine.mivote.R

private val hind = fontFamily(
  fonts = listOf(
    ResourceFont(
      resId = R.font.hind,
      style = FontStyle.Normal
    ),
  )
)

private val montserrat = fontFamily(
  fonts = listOf(
    ResourceFont(
      resId = R.font.montserrat,
      style = FontStyle.Normal,
      weight = FontWeight.Normal
    ),
    ResourceFont(
      resId = R.font.montserrat_bold,
      style = FontStyle.Normal,
      weight = FontWeight.Bold
    ),
  )
)

// Set of Material typography styles to start with
val typography = Typography(
  h1 = TextStyle(
    fontFamily = montserrat,
    fontWeight = FontWeight.Normal,
    fontSize = 21.sp
  ),

  h2 = TextStyle(
    fontFamily = montserrat,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp
  ),

  body1 = TextStyle(
    fontFamily = hind,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp
  ),

  button = TextStyle(
    fontFamily = montserrat,
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp
  ),

  /* Other default text styles to override
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

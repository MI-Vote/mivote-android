package com.fueledbycaffeine.mivote.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

//private val hind = FontFamily(
//  fonts = listOf(
//    Font(
//      resId = R.font.hind,
//      style = FontStyle.Normal
//    ),
//  )
//)
//
//private val montserrat = FontFamily(
//  fonts = listOf(
//    Font(
//      resId = R.font.montserrat,
//      style = FontStyle.Normal,
//      weight = FontWeight.Normal
//    ),
//    Font(
//      resId = R.font.montserrat_bold,
//      style = FontStyle.Normal,
//      weight = FontWeight.Bold
//    ),
//  )
//)

// Set of Material typography styles to start with
val Typography = Typography(
  h1 = TextStyle(
//    fontFamily = montserrat,
    fontWeight = FontWeight.Normal,
    fontSize = 21.sp
  ),

  h2 = TextStyle(
//    fontFamily = montserrat,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp
  ),

  body1 = TextStyle(
//    fontFamily = hind,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp
  ),

  button = TextStyle(
//    fontFamily = montserrat,
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

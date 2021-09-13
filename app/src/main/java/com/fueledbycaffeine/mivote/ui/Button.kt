package com.fueledbycaffeine.mivote.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryButton(
  text: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Button(
    modifier = modifier,
    onClick = onClick
  ) {
    Text(
      text = text,
      color = Color.White
    )
  }
}

@Composable
fun OutlinedButton(
  text: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  OutlinedButton(
    modifier = modifier,
    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
    border = BorderStroke(1.dp, MaterialTheme.colors.primaryVariant),
    onClick = onClick,
  ) {
    Text(
      text = text,
      color = MaterialTheme.colors.primaryVariant,
    )
  }
}

@Preview
@Composable
fun PreviewButtons() {
  Column {
    PrimaryButton(text = "Primary Button", onClick = {})
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedButton(text = "Outlined Button", onClick = {})
  }
}

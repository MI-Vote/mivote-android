package com.fueledbycaffeine.mivote.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview

@Composable
fun PrimaryButton(
  text: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier.fillMaxWidth()
) {
  Button(
    modifier = modifier,
    onClick = onClick
  ) {
    Text(
      text = text,
      style = typography.button,
      color = Color.White
    )
  }
}

@Composable
fun OutlinedButton(
  text: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier.fillMaxWidth()
) {
  OutlinedButton(
    modifier = modifier,
    backgroundColor = Color.Transparent,
    border = BorderStroke(1.dp, MaterialTheme.colors.primaryVariant),
    onClick = onClick,
  ) {
    Text(
      text = text,
      style = typography.button,
      color = MaterialTheme.colors.primaryVariant,
    )
  }
}

@Preview
@Composable
fun PreviewButtons() {
  Column {
    PrimaryButton(text = "Primary Button", onClick = {})
    Spacer(modifier = Modifier.preferredHeight(8.dp))
    OutlinedButton(text = "Outlined Button", onClick = {})
  }
}

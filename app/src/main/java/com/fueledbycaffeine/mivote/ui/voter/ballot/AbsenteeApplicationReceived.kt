package com.fueledbycaffeine.mivote.ui.voter.ballot

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun AbsenteeApplicationReceived() {
  Surface(
    shape = MaterialTheme.shapes.medium,
    elevation = 2.dp) {
    Text(
      text = "Your absentee application has been received!",
      style = MaterialTheme.typography.h1,
      modifier = Modifier.padding(16.dp))
  }
}

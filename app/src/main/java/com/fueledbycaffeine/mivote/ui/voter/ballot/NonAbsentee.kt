package com.fueledbycaffeine.mivote.ui.voter.ballot

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fueledbycaffeine.mivote.R
import com.fueledbycaffeine.mivote.ui.PrimaryButton
import com.fueledbycaffeine.mivote.ui.theme.MIVoteTheme

@Composable
fun NonAbsentee() {
  Surface(
    shape = MaterialTheme.shapes.medium,
    elevation = dimensionResource(R.dimen.elevation_small)
  ) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      Text(
        text = "You are not an absentee voter. Click below to view how to register for absentee voting.",
        style = MaterialTheme.typography.h1,
        modifier = Modifier.padding(dimensionResource(R.dimen.margin_medium))
      )
      val uriHandler = LocalUriHandler.current
      PrimaryButton(
        text = "Register Absentee",
        onClick = { uriHandler.openUri("https://absentee.michiganelections.io/") },
        modifier = Modifier.padding(dimensionResource(R.dimen.margin_small))
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewNonAbsentee() {
  MIVoteTheme {
    NonAbsentee()
  }
}

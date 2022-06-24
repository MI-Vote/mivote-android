package com.fueledbycaffeine.mivote.ui.voter.ballot

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fueledbycaffeine.mivote.R
import com.fueledbycaffeine.mivote.ui.PrimaryButton
import com.fueledbycaffeine.mivote.ui.theme.MIVoteTheme

@Composable
fun NonAbsentee() {
  Card {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      Text(
        text = stringResource(R.string.not_absentee_voter),
        style = MaterialTheme.typography.h1,
        modifier = Modifier.padding(dimensionResource(R.dimen.margin_medium))
      )
      val uriHandler = LocalUriHandler.current
      PrimaryButton(
        text = stringResource(R.string.register_absentee),
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

package com.fueledbycaffeine.mivote.ui.voter.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fueledbycaffeine.mivote.R
import com.fueledbycaffeine.mivote.ui.theme.MIVoteTheme
import io.michiganelections.api.model.VoterRegistration

@Composable
fun VoterRegistrationRegistered(
  voterRegistration: VoterRegistration,
  modifier: Modifier = Modifier,
) {
  Box(contentAlignment = Alignment.Center, modifier = modifier) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(dimensionResource(id = R.dimen.standard_padding)),
      verticalArrangement = Arrangement.Center
    ) {
      Text(
        text = stringResource(id = R.string.already_registered),
        style = MaterialTheme.typography.h1
      )

      val pollingLocation = voterRegistration.pollingLocation?.joinToString("\n")
      if (pollingLocation?.isNotBlank() == true) {
        Text(
          text = stringResource(id = R.string.your_polling_location_is, pollingLocation),
          style = MaterialTheme.typography.h2
        )
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewRegisteredStatus() {
  MIVoteTheme {
    VoterRegistrationRegistered(
      VoterRegistration(
        registered = true,
        ballot = true,
        absentee = false,
        absenteeApplicationReceived = null,
        absenteeBallotSent = null,
        absenteeBallotReceived = null,
        pollingLocation = listOf(
          "The Polling Location",
          "1234 The Street",
          "Grand Rapids, MI 49503"
        ),
        dropboxLocations = null,
        recentlyMoved = false,
        precinct = null,
        districts = null,
      )
    )
  }
}

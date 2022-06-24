package com.fueledbycaffeine.mivote.ui.voter.ballot

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fueledbycaffeine.mivote.ui.core.LoadingIndicator
import com.fueledbycaffeine.mivote.ui.theme.MIVoteTheme
import io.michiganelections.api.model.VoterRegistration

@Composable
fun BallotStatusScreen(
  registration: VoterRegistration?,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Center
  ) {
    when (registration) {
      null -> LoadingIndicator()
      else -> {
        when (registration.absentee) {
          true -> AbsenteeApplicationReceived(registration)
          false -> NonAbsentee()
          else -> {}
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewBallotStatusScreen() {
  MIVoteTheme {
    BallotStatusScreen(
      registration =
      VoterRegistration(
        registered = true,
        ballot = true,
        absentee = true,
        absenteeApplicationReceived = null,
        absenteeBallotSent = null,
        absenteeBallotReceived = null,
        pollingLocation = null,
        dropboxLocations = null,
        recentlyMoved = false,
        precinct = null,
        districts = null,
      )

    )
  }
}

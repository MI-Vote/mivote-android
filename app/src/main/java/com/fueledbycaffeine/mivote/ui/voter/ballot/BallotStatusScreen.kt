package com.fueledbycaffeine.mivote.ui.voter.ballot

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fueledbycaffeine.mivote.ui.core.LoadingIndicator
import com.fueledbycaffeine.mivote.ui.theme.MIVoteTheme
import io.michiganelections.api.model.VoterRegistration
import io.michiganelections.api.model.VoterRegistration.AbsenteeProcessState.*
import java.time.LocalDate

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
        when (registration.absenteeProcessState) {
          NOT_STARTED -> NonAbsentee()
          REQUESTED_BALLOT -> AbsenteeApplicationReceived(registration)
          BALLOT_SENT_BY_CLERK -> AbsenteeBallotSentByClerk(registration)
          BALLOT_RECEIVED_BY_CLERK -> AbsenteeBallotReceivedByClerk(registration)
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewAbsenteeBallotStatus() {
  MIVoteTheme {
    BallotStatusScreen(
      registration =
      VoterRegistration(
        registered = true,
        ballot = true,
        absentee = true,
        absenteeApplicationReceived = LocalDate.now(),
        absenteeBallotSent = LocalDate.now(),
        absenteeBallotReceived = LocalDate.now(),
        pollingLocation = null,
        dropboxLocations = null,
        recentlyMoved = false,
        precinct = null,
        districts = emptyList(),
      )
    )
  }
}

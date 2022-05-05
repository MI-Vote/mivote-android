package com.fueledbycaffeine.mivote.ui.voter.ballot

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fueledbycaffeine.mivote.R
import com.fueledbycaffeine.mivote.ui.theme.MIVoteTheme
import io.michiganelections.api.model.VoterRegistration
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

private val dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)

@Composable
fun AbsenteeBallotReceivedByClerk(registration: VoterRegistration) {
  Column {
    Text(
      text = stringResource(R.string.registered_as_absentee),
      style = MaterialTheme.typography.h1,
    )
    Text(
      text = stringResource(
        R.string.ballot_received_by_clerk,
        dateFormatter.format(registration.absenteeBallotReceived)
      ),
      style = MaterialTheme.typography.h2,
    )
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewAbsenteeBallotReceivedByClerk() {
  MIVoteTheme {
    AbsenteeBallotReceivedByClerk(
      registration = VoterRegistration(
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

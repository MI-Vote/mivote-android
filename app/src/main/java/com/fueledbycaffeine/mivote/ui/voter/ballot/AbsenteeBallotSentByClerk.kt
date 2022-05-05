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
fun AbsenteeBallotSentByClerk(registration: VoterRegistration) {
  Column {
    Text(
      text = stringResource(R.string.registered_as_absentee),
      style = MaterialTheme.typography.h1,
    )
    Text(
      text = stringResource(
        R.string.ballot_sent_by_clerk,
        dateFormatter.format(registration.absenteeBallotSent)
      ),
      style = MaterialTheme.typography.h2,
    )
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewAbsenteeBallotSentByClerk() {
  MIVoteTheme {
    AbsenteeBallotSentByClerk(
      registration = VoterRegistration(
        registered = true,
        ballot = true,
        absentee = true,
        absenteeApplicationReceived = LocalDate.now(),
        absenteeBallotSent = LocalDate.now(),
        absenteeBallotReceived = null,
        pollingLocation = null,
        dropboxLocations = null,
        recentlyMoved = false,
        precinct = null,
        districts = emptyList(),
      )
    )
  }
}

package com.fueledbycaffeine.mivote.ui.voter.ballot

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fueledbycaffeine.mivote.ui.theme.MIVoteTheme
import io.michiganelections.api.model.VoterRegistration


@Composable
fun AbsenteeApplicationReceived(registration: VoterRegistration) {
  Surface(
    shape = MaterialTheme.shapes.medium,
    elevation = 2.dp) {
    Column {
      Text(
        text = "You are registered to vote as absentee!",
        style = MaterialTheme.typography.h1,
        modifier = Modifier.padding(16.dp))
      Text(
        text = "Application last received: ${registration.absenteeApplicationReceived ?: "N/A"}",
        style = MaterialTheme.typography.h1,
        modifier = Modifier.padding(16.dp))
    }
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewAbsenteeApplicationReceived() {
  MIVoteTheme {
    AbsenteeApplicationReceived(
      registration = VoterRegistration(
        registered = true,
        absentee = true,
        absenteeApplicationReceived = null,
        absenteeBallotSent = null,
        absenteeBallotRecieved = null,
        pollingLocation = null,
        dropboxLocation = null,
        recentlyMoved = false,
        precinct = null,
        districts = null,
      )
    )
  }
}

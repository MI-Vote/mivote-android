package com.fueledbycaffeine.mivote.ui.voter.ballot

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fueledbycaffeine.mivote.R
import com.fueledbycaffeine.mivote.ui.theme.MIVoteTheme
import io.michiganelections.api.model.VoterRegistration

@Composable
fun AbsenteeApplicationReceived(registration: VoterRegistration) {
  Surface(
    shape = MaterialTheme.shapes.medium,
    elevation = dimensionResource(R.dimen.elevation_small)
  ) {
    Column {
      Text(
        text = "You are registered to vote as absentee!",
        style = MaterialTheme.typography.h1,
        modifier = Modifier.padding(dimensionResource(R.dimen.margin_medium))
      )
      Text(
        text = "Application last received: ${registration.absenteeApplicationReceived ?: "N/A"}",
        style = MaterialTheme.typography.h1,
        modifier = Modifier.padding(dimensionResource(R.dimen.margin_medium))
      )
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

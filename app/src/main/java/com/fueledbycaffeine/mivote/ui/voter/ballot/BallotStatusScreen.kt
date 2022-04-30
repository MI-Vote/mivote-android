package com.fueledbycaffeine.mivote.ui.voter.ballot

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.fueledbycaffeine.mivote.R
import com.fueledbycaffeine.mivote.ui.core.LoadingIndicator
import com.fueledbycaffeine.mivote.ui.theme.MIVoteTheme
import io.michiganelections.api.model.VoterRegistration

@Composable
fun BallotStatusScreen(
  registration: VoterRegistration?
) {
  Image(
    painter = painterResource(id = R.drawable.background_lakes),
    contentDescription = null,
    modifier = Modifier.fillMaxSize(),
    contentScale = ContentScale.Crop,
    colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
  )

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(dimensionResource(id = R.dimen.standard_padding)),
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

package com.fueledbycaffeine.mivote.ui.voter.status

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fueledbycaffeine.mivote.R
import com.fueledbycaffeine.mivote.ui.PrimaryButton
import com.fueledbycaffeine.mivote.ui.theme.MIVoteTheme
import io.michiganelections.api.model.VoterRegistration

@Composable
fun VoterRegistrationRegistered(
  voterRegistration: VoterRegistration,
) {
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

    PrimaryButton(
      modifier = Modifier.padding(top = dimensionResource(id = R.dimen.margin_small)),
      text = stringResource(id = R.string.registration_continue),
      onClick = {}
    )
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewRegisteredStatus() {
  MIVoteTheme {
    VoterRegistrationRegistered(
      VoterRegistration(
        registered = true,
        absentee = false,
        absenteeApplicationReceived = null,
        absenteeBallotSent = null,
        absenteeBallotReceived = null,
        pollingLocation = listOf("The Polling Location", "1234 The Street", "Grand Rapids, MI 49503"),
        dropboxLocation = null,
        recentlyMoved = false,
        precinct = null,
        districts = null,
      )
    )
  }
}

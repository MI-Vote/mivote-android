package com.fueledbycaffeine.mivote.ui.voter.status

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import com.fueledbycaffeine.mivote.R
import com.fueledbycaffeine.mivote.data.VoterInfo
import com.fueledbycaffeine.mivote.ui.OutlinedButton
import com.fueledbycaffeine.mivote.ui.PrimaryButton
import com.fueledbycaffeine.mivote.ui.theme.MIVoteTheme
import io.michiganelections.api.model.VoterRegistration
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun VoterRegistrationUnregistered(
  voterInfo: VoterInfo
) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(dimensionResource(id = R.dimen.standard_padding)),
    verticalArrangement = Arrangement.Center
  ) {
    Text(
      text = stringResource(id = R.string.registration_not_found),
      style = MaterialTheme.typography.h1
    )
    Text(
      modifier = Modifier.padding(top = dimensionResource(id = R.dimen.margin_small)),
      text = stringResource(id = R.string.registration_verify_info),
      style = MaterialTheme.typography.body1
    )
    Text(
      modifier = Modifier.padding(top = dimensionResource(id = R.dimen.margin_large)),
      text = stringResource(
        R.string.registration_voter_info,
        voterInfo.firstName,
        voterInfo.lastName,
        DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(voterInfo.birthdate),
        voterInfo.zipcode
      ),
      style = MaterialTheme.typography.h2
    )

    PrimaryButton(
      modifier = Modifier.padding(top = dimensionResource(id = R.dimen.margin_large)),
      text = stringResource(id = R.string.register_to_vote),
      onClick = {}
    )
    OutlinedButton(
      modifier = Modifier.padding(top = dimensionResource(id = R.dimen.margin_small)),
      text = stringResource(id = R.string.try_again),
      onClick = {},
    )
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewUnregisteredStatus() {
  MIVoteTheme {
    VoterRegistrationStatus(
      VoterInfo(
        firstName = "John",
        lastName = "Doe",
        birthdate = LocalDate.of(1900, 1, 1),
        zipcode = "12345"
      ),
      MutableLiveData(
        VoterRegistration(
          registered = false,
          absentee = false,
          absenteeApplicationReceived = null,
          absenteeBallotSent = null,
          absenteeBallotRecieved = null,
          pollingLocation = null,
          dropboxLocation = null,
          recentlyMoved = false,
          precinct = null,
          districts = null,
        )
      ).observeAsState()
    )
  }
}


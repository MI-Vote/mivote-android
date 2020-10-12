package com.fueledbycaffeine.mivote.ui.voter

import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.ui.tooling.preview.Preview
import com.fueledbycaffeine.mivote.R
import com.fueledbycaffeine.mivote.data.VoterInfo
import com.fueledbycaffeine.mivote.ui.MIVoteTheme
import com.fueledbycaffeine.mivote.ui.OutlinedButton
import com.fueledbycaffeine.mivote.ui.PrimaryButton
import com.fueledbycaffeine.mivote.ui.typography
import io.michiganelections.api.VoterRegistration
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun VoterRegistrationStatusScreen(
  voterInfo: VoterInfo,
  registrationViewModel: VoterRegistrationViewModel
) {
  val registrationState = registrationViewModel.registrationLiveData.observeAsState()
  VoterRegistrationStatus(voterInfo = voterInfo, registrationState = registrationState)
}

@Composable
fun VoterRegistrationStatus(
  voterInfo: VoterInfo,
  registrationState: State<VoterRegistration?>
) {
  MIVoteTheme {
    ConstraintLayout {
      Image(
        asset = vectorResource(id = R.drawable.background_lakes),
        contentScale = ContentScale.FillWidth,
        modifier = Modifier.fillMaxSize(),
        colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
      )

      when (val state = registrationState.value) {
        null -> Loading()
        else -> {
          when (state.registered) {
            true -> RegisteredVoterStatus()
            false -> UnregisteredVoterStatus(voterInfo = voterInfo)
          }
        }
      }
    }
  }
}

@Composable
fun Loading() {
  Column(
    modifier = Modifier.fillMaxSize().padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    CircularProgressIndicator()
  }
}

@Composable
fun UnregisteredVoterStatus(
  voterInfo: VoterInfo
) {
  Column(
    modifier = Modifier.fillMaxSize().padding(48.dp),
    verticalArrangement = Arrangement.Center
  ) {
    Text(
      text = stringResource(id = R.string.registration_not_found),
      style = typography.h1
    )
    Spacer(modifier = Modifier.preferredHeight(8.dp))
    Text(
      text = stringResource(id = R.string.registration_verify_info),
      style = typography.body1
    )
    Spacer(modifier = Modifier.preferredHeight(24.dp))
    Text(
      text = stringResource(
        R.string.registration_voter_info,
        voterInfo.firstName,
        voterInfo.lastName,
        DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(voterInfo.birthdate),
        voterInfo.zipcode
      ),
      style = typography.h2
    )

    Spacer(modifier = Modifier.preferredHeight(24.dp))
    PrimaryButton(
      text = stringResource(id = R.string.register_to_vote),
      onClick = {}
    )
    Spacer(modifier = Modifier.preferredHeight(8.dp))
    OutlinedButton(
      text = stringResource(id = R.string.try_again),
      onClick = {},
    )
  }
}

@Composable
fun RegisteredVoterStatus() {
  Column(
    modifier = Modifier.fillMaxSize().padding(48.dp),
    verticalArrangement = Arrangement.Center
  ) {
    Text(
      text = stringResource(id = R.string.already_registered),
      style = typography.h1
    )
    Spacer(modifier = Modifier.preferredHeight(24.dp))
    PrimaryButton(
      text = stringResource(id = R.string.registration_continue),
      onClick = {}
    )
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoadingStatus() {
  VoterRegistrationStatus(
    VoterInfo(
      firstName = "John",
      lastName = "Doe",
      birthdate = LocalDate.of(1900, 1, 1),
      zipcode = "12345"
    ),
    MutableLiveData<VoterRegistration>().observeAsState()
  )
}

@Preview(showBackground = true)
@Composable
fun PreviewUnregisteredStatus() {
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

@Preview(showBackground = true)
@Composable
fun PreviewRegisteredStatus() {
  VoterRegistrationStatus(
    VoterInfo(
      firstName = "John",
      lastName = "Doe",
      birthdate = LocalDate.of(1900, 1, 1),
      zipcode = "12345"
    ),
    MutableLiveData(
      VoterRegistration(
        registered = true,
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

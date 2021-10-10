package com.fueledbycaffeine.mivote.ui.voter.ballot

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fueledbycaffeine.mivote.R
import com.fueledbycaffeine.mivote.ui.core.LoadingIndicator
import com.fueledbycaffeine.mivote.ui.theme.MIVoteTheme
import io.michiganelections.api.model.VoterRegistration
import timber.log.Timber

@Composable
fun BallotStatusScreen(
  registrationLiveData: LiveData<VoterRegistration>
) {
  val state = registrationLiveData.observeAsState()
  Timber.d(state.value.toString())

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
    when (val registration = state.value) {
      null -> LoadingIndicator()
      else -> {
        when (registration.absentee) {
          true -> AbsenteeApplicationReceived(registration)
          false -> NonAbsentee()
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
      registrationLiveData = MutableLiveData(
        VoterRegistration(
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
    )
  }
}

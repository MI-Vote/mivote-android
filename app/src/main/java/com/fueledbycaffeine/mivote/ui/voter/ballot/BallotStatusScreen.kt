package com.fueledbycaffeine.mivote.ui.voter.ballot

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.LiveData
import com.fueledbycaffeine.mivote.R
import com.fueledbycaffeine.mivote.ui.core.LoadingIndicator
import com.fueledbycaffeine.mivote.ui.voter.status.VoterRegistrationRegistered
import io.michiganelections.api.model.VoterRegistration

@Composable
fun BallotStatusScreen(
  registrationLiveData: LiveData<VoterRegistration>
) {
  val state = registrationLiveData.observeAsState()

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
          true -> AbsenteeApplicationReceived()
          false -> NonAbsentee()
        }
      }
    }
  }
  //BallotStatus(state.value)
}

package com.fueledbycaffeine.mivote.ui.voter.status

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import com.fueledbycaffeine.mivote.R
import com.fueledbycaffeine.mivote.data.VoterInfo
import com.fueledbycaffeine.mivote.ui.core.LoadingIndicator
import io.michiganelections.api.model.VoterRegistration
import java.time.LocalDate

@Composable
fun VoterRegistrationStatus(
  voterInfo: VoterInfo,
  registrationState: State<VoterRegistration?>
) {
  Image(
    painter = painterResource(id = R.drawable.background_lakes),
    contentDescription = null,
    modifier = Modifier.fillMaxSize(),
    contentScale = ContentScale.Crop,
    colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
  )

  when (val state = registrationState.value) {
    null -> LoadingIndicator()
    else -> {
      when (state.registered) {
        true -> VoterRegistrationRegistered(voterRegistration = state)
        false -> VoterRegistrationUnregistered(voterInfo = voterInfo)
      }
    }
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

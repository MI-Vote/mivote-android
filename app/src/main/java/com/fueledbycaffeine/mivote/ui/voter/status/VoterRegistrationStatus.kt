package com.fueledbycaffeine.mivote.ui.voter.status

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import com.fueledbycaffeine.mivote.R
import com.fueledbycaffeine.mivote.data.VoterInfo
import com.fueledbycaffeine.mivote.ui.core.LoadingIndicator
import com.fueledbycaffeine.mivote.ui.theme.MIVoteTheme
import io.michiganelections.api.model.VoterRegistration
import java.time.LocalDate

@Composable
fun VoterRegistrationStatus(
  voterInfo: VoterInfo,
  voterRegistration: VoterRegistration?,
  modifier: Modifier = Modifier,
  onContinue: () -> Unit,
  onTryAgain: () -> Unit,
) {
  Box(contentAlignment = Alignment.Center, modifier = modifier) {
    Image(
      painter = painterResource(id = R.drawable.background_lakes),
      contentDescription = null,
      modifier = Modifier.fillMaxSize(),
      contentScale = ContentScale.Crop,
      colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
    )

    when (voterRegistration) {
      null -> LoadingIndicator()
      else -> {
        when (voterRegistration.registered) {
          true -> VoterRegistrationRegistered(voterRegistration, onContinue)
          false -> VoterRegistrationUnregistered(voterInfo, onTryAgain = onTryAgain)
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoadingStatus() {
  MIVoteTheme {
    VoterRegistrationStatus(
      VoterInfo(
        firstName = "John",
        lastName = "Doe",
        birthdate = LocalDate.of(1900, 1, 1),
        zipcode = "12345"
      ),
      null,
      onContinue = {},
      onTryAgain = {},
      modifier = Modifier.fillMaxSize()
    )
  }
}

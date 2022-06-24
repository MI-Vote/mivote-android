package com.fueledbycaffeine.mivote.ui.voter.registrationinput

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.fueledbycaffeine.mivote.R
import com.fueledbycaffeine.mivote.ui.core.LoadingIndicator
import com.fueledbycaffeine.mivote.ui.navigation.MIVoteScreen
import com.fueledbycaffeine.mivote.ui.voter.VoterRegistrationViewModel
import com.fueledbycaffeine.mivote.ui.voter.registrationinput.voterinfo.VoterInput
import kotlinx.coroutines.launch

@Composable
fun VoterRegistrationInputScreen(
  voterRegistrationViewModel: VoterRegistrationViewModel,
  navController: NavController,
  modifier: Modifier = Modifier
) {
  val voterInfo = voterRegistrationViewModel.voterInfo
  val voterRegistration by voterRegistrationViewModel.registration
  var registerCheckFailed by remember { mutableStateOf(false) }
  var loading by remember { mutableStateOf(false) }
  val coroutineScope = rememberCoroutineScope()

  Box(contentAlignment = Alignment.Center, modifier = modifier) {
    if (!registerCheckFailed) {
      VoterInput(
        voterInfo,
        checkRegistration = {
          coroutineScope.launch {
            loading = true
            // TODO handle when the registration check fails (actual error)
            voterRegistrationViewModel.checkRegistration(it)
            if (voterRegistration?.registered == true) {
              navController.navigate(MIVoteScreen.Home.route)
            } else {
              registerCheckFailed = true
            }
            loading = false
          }
        },
        modifier = modifier.padding(dimensionResource(R.dimen.margin_large))
      )
    } else {
      Image(
        painter = painterResource(id = R.drawable.background_lakes),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
        colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
      )
      VoterRegistrationUnregistered(
        voterInfo = voterInfo.value,
        modifier = Modifier
          .fillMaxSize()
          .padding(dimensionResource(id = R.dimen.standard_padding)),
        onTryAgain = {
          voterRegistrationViewModel.reset()
          registerCheckFailed = false
        }
      )
    }
    if (loading) {
      LoadingIndicator()
    }
  }
}

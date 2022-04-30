package com.fueledbycaffeine.mivote.ui.voter.status

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fueledbycaffeine.mivote.ui.navigation.MIVoteScreen
import com.fueledbycaffeine.mivote.ui.voter.VoterRegistrationViewModel
import com.fueledbycaffeine.mivote.ui.voter.voterinfo.VoterInputScreen
import kotlinx.coroutines.launch

@Composable
fun VoterRegistrationStatusScreen(
  voterRegistrationViewModel: VoterRegistrationViewModel,
  navController: NavController,
  modifier: Modifier = Modifier
) {
  val voterInfo = voterRegistrationViewModel.voterInfo
  val voterRegistration by voterRegistrationViewModel.registration
  val coroutineScope = rememberCoroutineScope()

  if (voterInfo == null) {
    VoterInputScreen(
      checkRegistration = {
        voterRegistrationViewModel.updateVoterInfo(it)
        coroutineScope.launch {
          voterRegistrationViewModel.checkRegistration(it)
        }
      },
      modifier = modifier.padding(24.dp)
    )
  } else {
    VoterRegistrationStatus(
      voterInfo,
      voterRegistration,
      modifier = modifier,
      onContinue = {
        navController.navigate(MIVoteScreen.BallotStatus.route)
      },
      onTryAgain = {
        voterRegistrationViewModel.reset()
      }
    )
  }
}

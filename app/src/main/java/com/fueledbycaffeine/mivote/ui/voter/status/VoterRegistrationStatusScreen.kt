package com.fueledbycaffeine.mivote.ui.voter.status

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fueledbycaffeine.mivote.R
import com.fueledbycaffeine.mivote.ui.navigation.MIVoteScreen
import com.fueledbycaffeine.mivote.ui.voter.VoterRegistrationViewModel
import com.fueledbycaffeine.mivote.ui.voter.status.voterinfo.VoterInputScreen
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

  if (voterRegistration == null) {
    VoterInputScreen(
      voterInfo,
      checkRegistration = {
        coroutineScope.launch {
          voterRegistrationViewModel.checkRegistration(it)
        }
      },
      modifier = modifier.padding(dimensionResource(R.dimen.margin_large))
    )
  } else {
    VoterRegistrationStatus(
      voterInfo.value,
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

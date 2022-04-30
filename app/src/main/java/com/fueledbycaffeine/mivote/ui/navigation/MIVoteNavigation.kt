package com.fueledbycaffeine.mivote.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fueledbycaffeine.mivote.ui.voter.VoterRegistrationViewModel
import com.fueledbycaffeine.mivote.ui.voter.ballot.BallotStatusScreen
import com.fueledbycaffeine.mivote.ui.voter.status.VoterRegistrationStatusScreen

@Composable
fun MIVoteNavigation(
) {
  val navController = rememberNavController()
//  val items = listOf(
//    MIVoteScreen.VoterRegistration,
//    MIVoteScreen.BallotStatus
//  )
  val voterRegistrationViewModel: VoterRegistrationViewModel = viewModel()
  Scaffold { innerPadding ->
    NavHost(navController, startDestination = MIVoteScreen.VoterRegistration.route) {
      composable(MIVoteScreen.VoterRegistration.route) {
        //val viewModel = hiltViewModel<VoterRegistrationViewModel>(backStackEntry)
        VoterRegistrationStatusScreen(
          voterRegistrationViewModel,
          navController,
          modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
        )
      }
      composable(MIVoteScreen.BallotStatus.route) {
        //val viewModel = hiltViewModel<VoterRegistrationViewModel>(backStackEntry)
        Box(modifier = Modifier.padding(innerPadding)) {
          BallotStatusScreen(
            registration = voterRegistrationViewModel.registration.value
          )
        }
      }
    }
  }
}

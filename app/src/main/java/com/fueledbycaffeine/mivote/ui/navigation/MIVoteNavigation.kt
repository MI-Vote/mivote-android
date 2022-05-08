package com.fueledbycaffeine.mivote.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fueledbycaffeine.mivote.R
import com.fueledbycaffeine.mivote.ui.voter.VoterRegistrationViewModel
import com.fueledbycaffeine.mivote.ui.voter.ballot.BallotStatusScreen
import com.fueledbycaffeine.mivote.ui.voter.registration.VoterRegistrationRegistered
import com.fueledbycaffeine.mivote.ui.voter.registrationinput.VoterRegistrationInputScreen

@Composable
fun MIVoteNavigation(
) {
  val navController = rememberNavController()
  val items = listOf(
    MIVoteScreen.Home.RegistrationInfo,
    MIVoteScreen.Home.BallotStatus
  )
  val voterRegistrationViewModel: VoterRegistrationViewModel = viewModel()
  NavHost(navController, startDestination = MIVoteScreen.RegistrationInput.route) {
    composable(MIVoteScreen.RegistrationInput.route) {
      //val viewModel = hiltViewModel<VoterRegistrationViewModel>(backStackEntry)
      VoterRegistrationInputScreen(
        voterRegistrationViewModel,
        navController,
        modifier = Modifier.fillMaxSize()
      )
    }
    composable(MIVoteScreen.Home.route) {
      //val viewModel = hiltViewModel<VoterRegistrationViewModel>(backStackEntry)
      var selectedBottomNav by remember { mutableStateOf(0) }
      Scaffold(bottomBar = {
        BottomNavigation {
          items.forEachIndexed { index, item ->
            BottomNavigationItem(
              icon = { Icon(item.icon, contentDescription = null) },
              label = { Text(stringResource(item.resourceId)) },
              selected = selectedBottomNav == index,
              onClick = {
                selectedBottomNav = index
              }
            )
          }
        }
      }) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
          Image(
            painter = painterResource(id = R.drawable.background_lakes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
          )
          when (selectedBottomNav) {
            0 ->
              VoterRegistrationRegistered(
                voterRegistration = voterRegistrationViewModel.registration.value!!,
                modifier = Modifier.fillMaxSize()
              )
            1 ->
              BallotStatusScreen(
                registration = voterRegistrationViewModel.registration.value,
                modifier = Modifier
                  .fillMaxSize()
                  .padding(dimensionResource(id = R.dimen.standard_padding))
              )
          }
        }
      }
    }
  }
}

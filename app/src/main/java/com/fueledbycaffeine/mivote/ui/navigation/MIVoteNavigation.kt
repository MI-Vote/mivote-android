package com.fueledbycaffeine.mivote.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fueledbycaffeine.mivote.data.VoterInfo
import com.fueledbycaffeine.mivote.ui.voter.VoterRegistrationViewModel
import com.fueledbycaffeine.mivote.ui.voter.ballot.BallotStatusScreen
import com.fueledbycaffeine.mivote.ui.voter.status.VoterRegistrationStatusScreen
import java.time.LocalDate

@Composable
fun MIVoteNavigation(
) {
  val navController = rememberNavController()
  val items = listOf(
    MIVoteScreen.VoterRegistration,
    MIVoteScreen.BallotStatus
  )

  Scaffold(
    bottomBar = {
      BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { screen ->
          BottomNavigationItem(
            icon = { Icon(screen.icon, contentDescription = null) },
            label = { Text(stringResource(screen.resourceId)) },
            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
            onClick = {
              navController.navigate(screen.route) {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                  saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
              }
            }
          )
        }
      }
    }
  ) { innerPadding ->
    NavHost(navController, startDestination = MIVoteScreen.VoterRegistration.route) {
      composable(MIVoteScreen.VoterRegistration.route) { backStackEntry ->
        val viewModel = hiltViewModel<VoterRegistrationViewModel>(backStackEntry)

        // TODO: DELETE WHEN CAN ENTER
        val voterInfo = VoterInfo(
          firstName = "John",
          lastName = "Doe",
          birthdate = LocalDate.of(2000, 5, 15),
          zipcode = "49401"
        )

        LaunchedEffect(voterInfo) {
          viewModel.checkRegistration(voterInfo = voterInfo)
        }

        VoterRegistrationStatusScreen(
          voterInfo = voterInfo,
          registrationLiveData = viewModel.registrationLiveData,
        )
      }
      composable(MIVoteScreen.BallotStatus.route) { backStackEntry ->
        val viewModel = hiltViewModel<VoterRegistrationViewModel>(backStackEntry)

        // TODO: DELETE WHEN CAN ENTER
        val voterInfo = VoterInfo(
          firstName = "John",
          lastName = "Doe",
          birthdate = LocalDate.of(2000, 5, 15),
          zipcode = "49401"
        )

        LaunchedEffect(voterInfo) {
          viewModel.checkRegistration(voterInfo = voterInfo)
        }
        Box(modifier = Modifier.padding(innerPadding)) {
          BallotStatusScreen(
            registrationLiveData = viewModel.registrationLiveData,
          )
        }
      }
    }
  }
}

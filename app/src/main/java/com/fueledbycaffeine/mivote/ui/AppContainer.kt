package com.fueledbycaffeine.mivote.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.ui.tooling.preview.Preview
import com.fueledbycaffeine.mivote.ui.voter.BallotStatusScreen
import com.fueledbycaffeine.mivote.ui.voter.BallotStatusViewModel
import io.michiganelections.api.VoterRegistration
import com.fueledbycaffeine.mivote.R

@Composable
fun AppContainer(
  navigationViewModel: NavigationViewModel,
  registrationData: LiveData<VoterRegistration>
) {
  val ballotStatusViewModel = BallotStatusViewModel(registrationLiveData = registrationData)

  Crossfade(navigationViewModel.currentScreen) { screen ->
    Surface(color = MaterialTheme.colors.background) {
      when (screen) {
        is Screen.BallotStatus -> BallotStatusScreen(
          navigateTo = navigationViewModel::navigateTo,
          ballotStatusViewModel = ballotStatusViewModel
        )
      }
    }
  }
}

@Composable
fun NavDrawer(
  navigateTo: (Screen) -> Unit,
  currentScreen: Screen,
  closeDrawer: () -> Unit
) {
  Column(modifier = Modifier.fillMaxSize()) {
    Text(
      text = stringResource(id = R.string.app_name),
      style = MaterialTheme.typography.h1,
      modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colors.primary.copy(alpha = 0.5f))
        .padding(all = 12.dp)
    )
    Spacer(modifier = Modifier.preferredHeight(16.dp))
    NavItem(
      text = "Ballot Status",
      isSelected = currentScreen == Screen.BallotStatus,
      click = {
        navigateTo(Screen.BallotStatus)
        closeDrawer()
      }
    )
  }
}

@Composable
fun NavItem(
  text: String,
  isSelected: Boolean,
  click: () -> Unit
) {
  val backgroundColor = when (isSelected) {
    true -> MaterialTheme.colors.primary
    false -> Color.White
  }

  val textColor = when (isSelected) {
    true -> Color.White
    false -> MaterialTheme.colors.primary
  }

  Surface(
    color = backgroundColor,
    modifier = Modifier.padding(all = 6.dp).fillMaxWidth(),
    shape = RoundedCornerShape(3.dp)
  ) {
    TextButton(
      onClick = click,
      modifier = Modifier.fillMaxWidth()
    ) {
      Text(
        text = text,
        style = MaterialTheme.typography.button,
        color = textColor
      )
    }
  }
}

@Preview
@Composable
fun PreviewNav() {
  MIVoteTheme {
    Surface(color = MaterialTheme.colors.background) {
      NavDrawer(
        navigateTo = { },
        currentScreen = Screen.BallotStatus,
        closeDrawer = { }
      )
    }
  }
}

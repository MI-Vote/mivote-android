package com.fueledbycaffeine.mivote.ui.voter

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.ui.tooling.preview.Preview
import com.fueledbycaffeine.mivote.R
import com.fueledbycaffeine.mivote.ui.NavDrawer
import com.fueledbycaffeine.mivote.ui.Screen
import io.michiganelections.api.VoterRegistration

@Composable
fun BallotStatusScreen(
  ballotStatusViewModel: BallotStatusViewModel,
  navigateTo: (Screen) -> Unit,
  scaffoldState: ScaffoldState = rememberScaffoldState()
) {
  BallotStatus(
    registrationState = ballotStatusViewModel.registrationLiveData.observeAsState(),
    navigateTo = navigateTo,
    scaffoldState = scaffoldState
  )
}

@Composable
fun BallotStatus(
  registrationState: State<VoterRegistration?>,
  navigateTo: (Screen) -> Unit,
  scaffoldState: ScaffoldState
) {
  Scaffold(
    scaffoldState = scaffoldState,
    drawerContent = {
      NavDrawer(
        currentScreen = Screen.BallotStatus,
        closeDrawer = { scaffoldState.drawerState.close() },
        navigateTo = navigateTo
      )
    },
    topBar = {
      val title = stringResource(id = R.string.ballot_status)
      TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
          IconButton(onClick = { scaffoldState.drawerState.open() }) {
            Icon(vectorResource(R.drawable.ic_menu))
          }
        }
      )
    },
    bodyContent = { innerPadding ->
      val modifier = Modifier.padding(innerPadding)
      ConstraintLayout(modifier = Modifier.padding(12.dp)) {
        Spacer(modifier = modifier)
        when {
          registrationState.value == null -> {
            Loading()
          }
          registrationState.value!!.absenteeBallotRecieved != null -> {
            BallotReceived()
          }
          registrationState.value!!.absenteeBallotSent != null -> {
            BallotSent()
          }
          registrationState.value!!.absenteeApplicationReceived != null -> {
            BallotApplicationReceived()
          }
          registrationState.value!!.absentee -> {
            BallotAbsentee()
          }
          else -> {
            BallotNotAbsentee()
          }
        }
      }
    }
  )
}

@Composable
fun BallotNotAbsentee() {
  Column {
    Text(
      text = "You are not an absentee voter.",
      style = MaterialTheme.typography.h1
    )
    Spacer(modifier = Modifier.preferredHeight(12.dp))
    Text(
      text = "Request to vote by mail for all future elections below.",
      style = MaterialTheme.typography.body1
    )
  }
}

@Composable
fun BallotAbsentee() {
  Column {
    Text(
      text = "You are ready to send your ballot!",
      style = MaterialTheme.typography.h1
    )
    Spacer(modifier = Modifier.preferredHeight(12.dp))
    Text(
      text = "You're registered as an absentee voter. " +
          "Whenever you're ready, mail your ballot or leave it in a nearby drop box.",
      style = MaterialTheme.typography.body1
    )
  }
}

@Composable
fun BallotApplicationReceived() {
  Column {
    Text(
      text = "Your absentee application has been received.",
      style = MaterialTheme.typography.h1
    )
    Spacer(modifier = Modifier.preferredHeight(12.dp))
    Text(
      text = "Once your application has been processed, " +
          "you can check back here to see the status of your absentee ballot.",
      style = MaterialTheme.typography.body1
    )
  }
}

@Composable
fun BallotSent() {
  Column {
    Text(
      text = "Your ballot has been sent!",
      style = MaterialTheme.typography.h1
    )
    Spacer(modifier = Modifier.preferredHeight(12.dp))
    Text(
      text = "The county clerk mailed your absentee ballot. " +
          "We'll let you know when your ballot has been recorded!",
      style = MaterialTheme.typography.body1
    )
  }
}

@Composable
fun BallotReceived() {
  Column {
    Text(
      text = "Your ballot has been received!",
      style = MaterialTheme.typography.h1
    )
    Spacer(modifier = Modifier.preferredHeight(12.dp))
    Text(
      text = "Hooray! Your vote has been recorded.",
      style = MaterialTheme.typography.body1
    )
  }
}

@Preview
@Composable
fun PreviewBallotStatus() {
  BallotStatusScreen(
    ballotStatusViewModel = BallotStatusViewModel(
      MutableLiveData(
        VoterRegistration(
          registered = true,
          absentee = false,
          absenteeApplicationReceived = null,
          absenteeBallotSent = null,
          absenteeBallotRecieved = null,
          pollingLocation = null,
          dropboxLocation = null,
          recentlyMoved = false,
          precinct = null,
          districts = null,
        )
      )
    ),
    navigateTo = { }
  )
}

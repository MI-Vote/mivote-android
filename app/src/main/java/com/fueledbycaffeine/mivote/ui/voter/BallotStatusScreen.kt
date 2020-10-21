package com.fueledbycaffeine.mivote.ui.voter

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.ui.tooling.preview.Preview
import com.fueledbycaffeine.mivote.R
import com.fueledbycaffeine.mivote.ui.MIVoteTheme
import com.fueledbycaffeine.mivote.ui.NavDrawer
import com.fueledbycaffeine.mivote.ui.Screen
import io.michiganelections.api.VoterRegistration
import timber.log.Timber

@Composable
fun BallotStatusScreen(
    ballotStatusViewModel: BallotStatusViewModel,
    navigateTo: (Screen) -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
   BallotStatus(
       registrationInfo = ballotStatusViewModel.registrationLiveData.value,
       navigateTo = navigateTo,
       scaffoldState = scaffoldState
   )
}

@Composable
fun BallotStatus(
    registrationInfo: VoterRegistration?,
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
            Timber.d(registrationInfo.toString())
            ConstraintLayout (modifier = Modifier.padding(12.dp)) {
                Spacer(modifier = modifier)
                if (registrationInfo == null) {
                    Loading()
                } else if (registrationInfo.absenteeBallotRecieved != null) {
                    BallotReceived()
                } else if (registrationInfo.absenteeBallotSent != null) {
                    BallotSent()
                } else if (registrationInfo.absenteeApplicationReceived != null) {
                    BallotApplicationReceived()
                } else if (registrationInfo.absentee) {
                    BallotAbsentee()
                } else {
                    BallotNotAbsentee()
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
            text = "You're registered as an absentee voter. Whenever you're ready, mail your ballot or leave it in a nearby drop box.",
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
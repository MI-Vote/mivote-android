package com.fueledbycaffeine.mivote.ui.voter

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.fueledbycaffeine.mivote.ui.MIVoteTheme
import io.michiganelections.api.VoterRegistration

@Composable
fun BallotStatusScreen(
    ballotStatusViewModel: BallotStatusViewModel
) {
   BallotStatus(
       registrationInfo = ballotStatusViewModel.registrationLiveData.value
   )


}

@Composable
fun BallotStatus(
    registrationInfo: VoterRegistration?
) {
    MIVoteTheme {
        ConstraintLayout {
            if (registrationInfo == null) {
                Loading()
            } else if (registrationInfo.absenteeBallotRecieved != null) {
                BallotReceived()
            } else if (registrationInfo.absenteeBallotSent != null) {
                BallotSent()
            } else if (registrationInfo.absenteeApplicationReceived !=null) {
                BallotApplicationReceived()
            } else if (!registrationInfo.absentee) {
                BallotNotAbsentee()
            }

        }
    }
}

@Composable
fun BallotNotAbsentee() {
    Text(text = "You are not an absentee voter.", style = MaterialTheme.typography.h1)
    Text(text = "Request to vote by mail for all future elections below.", style = MaterialTheme.typography.body1)
}

@Composable
fun BallotApplicationReceived() {
    Text(text = "Your absentee application has been received.", style = MaterialTheme.typography.h1)
    Text(text = "Once your application has been processed, " +
            "you can check back here to see the status of your absentee ballot.", style = MaterialTheme.typography.body1)
}

@Composable
fun BallotSent() {
    Text(text = "Your ballot has been sent!", style = MaterialTheme.typography.h1)
    Text(text = "The county clerk mailed your absentee ballot. " +
            "We'll let you know when your ballot has been recorded!", style = MaterialTheme.typography.body1)
}

@Composable
fun BallotReceived() {
    Text(text = "Your ballot has been received!", style = MaterialTheme.typography.h1)
    Text(text = "Hooray! Your vote has been recorded.", style = MaterialTheme.typography.body1)
}
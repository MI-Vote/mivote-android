package com.fueledbycaffeine.mivote.ui.voter.ballot

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import io.michiganelections.api.model.VoterRegistration

@Composable
fun BallotStatusScreen(
  registrationLiveData: LiveData<VoterRegistration>
) {
  val state = registrationLiveData.observeAsState()
}

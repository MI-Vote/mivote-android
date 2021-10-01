package com.fueledbycaffeine.mivote.ui.voter.status

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import com.fueledbycaffeine.mivote.data.VoterInfo
import io.michiganelections.api.VoterRegistration

@Composable
fun VoterRegistrationStatusScreen(
  voterInfo: VoterInfo,
  registrationLiveData: LiveData<VoterRegistration>
) {
  val state = registrationLiveData.observeAsState()
  VoterRegistrationStatus(
    voterInfo = voterInfo,
    registrationState = state
  )
}

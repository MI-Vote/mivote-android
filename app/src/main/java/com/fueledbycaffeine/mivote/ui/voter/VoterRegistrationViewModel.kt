package com.fueledbycaffeine.mivote.ui.voter

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.fueledbycaffeine.mivote.data.VoterInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.michiganelections.api.model.SampleBallot
import io.michiganelections.api.model.VoterRegistration
import io.michiganelections.api.service.ApiService
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class VoterRegistrationViewModel @Inject constructor(
  private val api: ApiService
) : ViewModel() {

  private val _registration = mutableStateOf<VoterRegistration?>(null)
  val registration: State<VoterRegistration?> = _registration

  var voterInfo by mutableStateOf<VoterInfo?>(null)
    private set

  fun updateVoterInfo(_voterInfo: VoterInfo?) {
    voterInfo = _voterInfo
  }

  fun reset() {
    _registration.value = null
    voterInfo = null
  }

  suspend fun checkRegistration(voterInfo: VoterInfo) {
    try {
      val registrationResult = api.getVoter(
        firstName = voterInfo.firstName,
        lastName = voterInfo.lastName,
        birthDate = voterInfo.birthdate,
        zipcode = voterInfo.zipcode
      )
      Timber.d("Registration result: $registrationResult")
      _registration.value = registrationResult
    } catch (ex: Exception) {
      Timber.e(ex)
    }
  }

  suspend fun getSampleBallot(precinctId: Int): SampleBallot {
    return api.getSampleBallot(precinctId = precinctId)
  }
}

package com.fueledbycaffeine.mivote.ui.voter

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.fueledbycaffeine.mivote.data.VoterDataStore
import com.fueledbycaffeine.mivote.data.VoterInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.michiganelections.api.model.Ballot
import io.michiganelections.api.model.VoterRegistration
import io.michiganelections.api.service.ApiService
import timber.log.Timber
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class VoterRegistrationViewModel @Inject constructor(
  private val api: ApiService,
  private val dataStore: VoterDataStore
) : ViewModel() {

  private val _registration = mutableStateOf<VoterRegistration?>(null)
  val registration: State<VoterRegistration?> = _registration

  val voterInfo = mutableStateOf(
    dataStore.voterInfo ?: VoterInfo(
      firstName = "",
      lastName = "",
      birthdate = LocalDate.now(),
      zipcode = ""
    )
  )

  fun reset() {
    _registration.value = null
  }

  suspend fun checkRegistration(voterInfo: VoterInfo) {
    // When we check registration, store the new VoterInfo
    dataStore.voterInfo = voterInfo
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

  suspend fun getSampleBallot(electionId: Int, precinctId: Int): Ballot {
    return api.getBallots(electionId = electionId, precinctId = precinctId).results.first()
  }
}

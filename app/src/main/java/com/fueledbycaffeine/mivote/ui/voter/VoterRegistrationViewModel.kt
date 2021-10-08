package com.fueledbycaffeine.mivote.ui.voter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fueledbycaffeine.mivote.data.VoterInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.michiganelections.api.model.VoterRegistration
import io.michiganelections.api.service.ApiService
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class VoterRegistrationViewModel @Inject constructor(
  private val api: ApiService
) : ViewModel() {

  private val registration = MutableLiveData<VoterRegistration>()
  val registrationLiveData: LiveData<VoterRegistration> = registration

  init {
    Timber.e("Initialized the thing at least")
  }

  suspend fun checkRegistration(voterInfo: VoterInfo) {
    val registrationResult = api.getVoter(
      firstName = voterInfo.firstName,
      lastName = voterInfo.lastName,
      birthDate = voterInfo.birthdate,
      zipcode = voterInfo.zipcode
    )
    registration.value = registrationResult
  }
}

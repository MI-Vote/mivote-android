package com.fueledbycaffeine.mivote.ui.voter

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fueledbycaffeine.mivote.data.VoterInfo
import io.michiganelections.api.Api
import io.michiganelections.api.VoterRegistration
import timber.log.Timber

class VoterRegistrationViewModel @ViewModelInject constructor(
  private val api: Api.Service
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

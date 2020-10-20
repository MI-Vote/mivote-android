package com.fueledbycaffeine.mivote.ui.voter

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.michiganelections.api.VoterRegistration

class BallotStatusViewModel constructor(
        val registrationLiveData: LiveData<VoterRegistration>
) : ViewModel() {

}
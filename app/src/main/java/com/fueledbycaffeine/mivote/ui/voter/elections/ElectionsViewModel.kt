package com.fueledbycaffeine.mivote.ui.voter.elections

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.michiganelections.api.model.Election
import io.michiganelections.api.model.ResultPage
import io.michiganelections.api.service.ApiService
import javax.inject.Inject

@HiltViewModel
class ElectionsViewModel @Inject constructor(val api: ApiService) : ViewModel() {
  private val _elections = mutableStateOf<ResultPage<Election>?>(null)
  val elections: State<ResultPage<Election>?> = _elections

  suspend fun getElections(isActive: Boolean = true) {
    _elections.value = api.getElections(isActive = isActive)
  }
}
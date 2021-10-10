package com.fueledbycaffeine.mivote.ui.voter.ballot

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.michiganelections.api.model.VoterRegistration

@Composable
fun BallotStatus(
  registration: VoterRegistration?
) {
  Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
    registration?.let {
      Text(text = registration.toString())
    }
  }
}

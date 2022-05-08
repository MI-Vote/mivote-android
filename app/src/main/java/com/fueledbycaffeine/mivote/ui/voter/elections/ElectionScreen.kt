package com.fueledbycaffeine.mivote.ui.voter.elections

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ElectionScreen(modifier: Modifier = Modifier) {
  val viewModel: ElectionsViewModel = hiltViewModel()
  LaunchedEffect(true) {
    viewModel.getElections(false)
  }
  Column {
    for (election in viewModel.elections.value?.results ?: listOf()) {
      Text(election.name, modifier = modifier)
    }
  }
}
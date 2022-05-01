package com.fueledbycaffeine.mivote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.fueledbycaffeine.mivote.data.VoterDataStore
import com.fueledbycaffeine.mivote.ui.navigation.MIVoteNavigation
import com.fueledbycaffeine.mivote.ui.theme.MIVoteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      MIVoteTheme {
        MIVoteNavigation()
      }
    }
  }
}


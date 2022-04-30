package com.fueledbycaffeine.mivote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//  MIVoteTheme {
//    VoterInputScreen(
//      VoterInfo("Joshua", "Friend", LocalDate.of(1991, 4, 1), "12345")
//    )
//  }
//}

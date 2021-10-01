package com.fueledbycaffeine.mivote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.fueledbycaffeine.mivote.data.VoterInfo
import com.fueledbycaffeine.mivote.ui.theme.MIVoteTheme
import com.fueledbycaffeine.mivote.ui.voter.VoterRegistrationViewModel
import com.fueledbycaffeine.mivote.ui.voter.status.VoterRegistrationStatusScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDate

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  // TODO: DELETE WHEN CAN ENTER
  private val voterInfo = VoterInfo(
    firstName = "John",
    lastName = "Doe",
    birthdate = LocalDate.of(1900, 1, 1),
    zipcode = "00000"
  )

  private val voterRegistrationViewModel by viewModels<VoterRegistrationViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    lifecycleScope.launch {
      voterRegistrationViewModel.checkRegistration(voterInfo = voterInfo)
    }

    setContent {
      MIVoteTheme {
        VoterRegistrationStatusScreen(
          voterInfo = voterInfo,
          registrationLiveData = voterRegistrationViewModel.registrationLiveData
        )
      }
    }
  }
}

@Composable
fun Greeting(name: String) {
  Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  MIVoteTheme {
    Greeting("Android")
  }
}

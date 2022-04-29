package com.fueledbycaffeine.mivote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fueledbycaffeine.mivote.data.VoterInfo
import com.fueledbycaffeine.mivote.ui.navigation.MIVoteNavigation
import com.fueledbycaffeine.mivote.ui.theme.MIVoteTheme
import com.fueledbycaffeine.mivote.ui.voter.VoterInfoForm
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

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

@Composable
fun VoterInfo(voterInfo: VoterInfo?) {
  Column(
    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)
  ) {
    VoterInfoForm(voterInfo = voterInfo)
    Spacer(modifier = Modifier.height(24.dp))

    TextButton(
      onClick = {
      },
      modifier = Modifier.fillMaxWidth()
    ) {
      Text(text = stringResource(id = R.string.check_registration))
    }
  }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  MIVoteTheme {
    VoterInfo(
      VoterInfo("Joshua", "Friend", LocalDate.of(1991, 4, 1), "12345")
    )
  }
}

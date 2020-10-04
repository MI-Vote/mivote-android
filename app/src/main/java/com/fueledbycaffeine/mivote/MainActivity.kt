package com.fueledbycaffeine.mivote

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.fueledbycaffeine.mivote.data.VoterDataStore
import com.fueledbycaffeine.mivote.data.VoterInfo
import com.fueledbycaffeine.mivote.ui.MIVoteTheme
import com.fueledbycaffeine.mivote.ui.voter.VoterInfoForm
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
  private lateinit var voterDataStore: VoterDataStore

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    voterDataStore = VoterDataStore(this)

    setContent {
      MIVoteTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
          VoterInfo(voterDataStore.voterInfo)
        }
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
    Spacer(modifier = Modifier.preferredHeight(24.dp))

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

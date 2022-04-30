package com.fueledbycaffeine.mivote.ui.voter.voterinfo

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
import androidx.compose.ui.unit.dp
import com.fueledbycaffeine.mivote.R
import com.fueledbycaffeine.mivote.data.VoterInfo
import com.fueledbycaffeine.mivote.ui.voter.VoterInfoForm

@Composable
fun VoterInputScreen(voterInfo: VoterInfo?, modifier: Modifier = Modifier) {
  Column(
    modifier = modifier
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